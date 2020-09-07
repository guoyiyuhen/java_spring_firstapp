package com.miiti.firstapp.infrastructure.repository;

import com.miiti.firstapp.domain.model.course.CourseId;
import com.miiti.firstapp.domain.model.courseitem.CourseItem;
import com.miiti.firstapp.domain.model.courseitem.CourseItemId;
import com.miiti.firstapp.domain.model.courseitem.CourseItemRepository;
import com.miiti.firstapp.domain.model.courseitemlist.CourseItemList;
import com.miiti.firstapp.domain.model.courseitemlist.CourseItemListId;
import com.miiti.firstapp.domain.model.courseitemlist.CourseItemListRepository;
import org.hibernate.query.NativeQuery;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class HibernateCourseItemRepository extends HibernateSupport<CourseItem> implements CourseItemRepository {

    private JdbcTemplate jdbcTemplate;

    HibernateCourseItemRepository(EntityManager entityManager, JdbcTemplate jdbcTemplate) {
        super(entityManager);
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public CourseItem findById(CourseItemId courseItemId) {
        return getSession().find(CourseItem.class, courseItemId.value());
    }

    @Override
    public List<CourseItem> findByCourseId(CourseId courseId) {

        String sql = "SELECT cl.* FROM course_item cl LEFT JOIN course_item_list cil ON cl.course_item_list_id = cil.id  WHERE cl.course_id = :courseId";
        NativeQuery<CourseItem> query = getSession().createNativeQuery(sql, CourseItem.class);
        query.setParameter("courseId", courseId.value());
        return query.list();
    }
}

package com.miiti.firstapp.infrastructure.repository;

import com.miiti.firstapp.domain.model.board.BoardId;
import com.miiti.firstapp.domain.model.cardlist.CardList;
import com.miiti.firstapp.domain.model.cardlist.CardListId;
import com.miiti.firstapp.domain.model.cardlist.CardListPosition;
import com.miiti.firstapp.domain.model.cardlist.CardListRepository;
import com.miiti.firstapp.domain.model.course.CourseId;
import com.miiti.firstapp.domain.model.courseitemlist.CourseItemList;
import com.miiti.firstapp.domain.model.courseitemlist.CourseItemListId;
import com.miiti.firstapp.domain.model.courseitemlist.CourseItemListRepository;
import org.hibernate.query.NativeQuery;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class HibernateCourseItemListRepository extends HibernateSupport<CourseItemList> implements CourseItemListRepository {

    private JdbcTemplate jdbcTemplate;

    HibernateCourseItemListRepository(EntityManager entityManager, JdbcTemplate jdbcTemplate) {
        super(entityManager);
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public CourseItemList findById(CourseItemListId courseItemListId) {
        return getSession().find(CourseItemList.class, courseItemListId.value());
    }

    @Override
    public List<CourseItemList> findByCourseId(CourseId courseId) {
        String sql = "SELECT cl.* FROM course_item_list cl WHERE cl.course_id = :courseId";
        NativeQuery<CourseItemList> query = getSession().createNativeQuery(sql, CourseItemList.class);
        query.setParameter("courseId", courseId.value());
        return query.list();
    }
}
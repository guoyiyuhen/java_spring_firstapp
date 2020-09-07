package com.miiti.firstapp.infrastructure.repository;

import com.miiti.firstapp.domain.common.model.Page;
import com.miiti.firstapp.domain.common.model.PageImpl;
import com.miiti.firstapp.domain.model.course.Course;
import com.miiti.firstapp.domain.model.course.CourseId;
import com.miiti.firstapp.domain.model.course.CourseRepository;
import com.miiti.firstapp.domain.model.courseitemuser.CourseItemUser;
import com.miiti.firstapp.domain.model.courseitemuser.CourseItemUserId;
import com.miiti.firstapp.domain.model.courseuser.CourseUser;
import com.miiti.firstapp.domain.model.courseuser.CourseUserId;
import com.miiti.firstapp.domain.model.courseuser.CourseUserRepository;
import com.miiti.firstapp.domain.model.series.SeriesId;
import com.miiti.firstapp.domain.model.user.UserId;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Projections;
import org.hibernate.query.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class HibernateCourseUserRepository extends HibernateSupport<CourseUser> implements CourseUserRepository {

    HibernateCourseUserRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public CourseUser findById(CourseUserId courseUserId) {
        Query<CourseUser> query = getSession().createQuery("from CourseUser where id = :id", CourseUser.class);
        query.setParameter("id", courseUserId.value());
        return query.uniqueResult();
    }

    @Override
    public CourseUser findByCourseIdAndUserId(CourseId courseId, UserId userId) {
        String sql = "from CourseUser where course_id = :courseId and user_id = :userId";
        Query<CourseUser> query = getSession().createQuery(sql, CourseUser.class);
        query.setParameter("courseId", courseId.value());
        query.setParameter("userId", userId.value());
        return query.uniqueResult();
    }
}
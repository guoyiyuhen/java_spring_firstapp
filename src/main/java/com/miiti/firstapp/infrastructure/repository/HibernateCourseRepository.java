package com.miiti.firstapp.infrastructure.repository;

import com.miiti.firstapp.domain.common.model.Page;
import com.miiti.firstapp.domain.common.model.PageImpl;
import com.miiti.firstapp.domain.model.course.Course;
import com.miiti.firstapp.domain.model.course.CourseId;
import com.miiti.firstapp.domain.model.course.CourseRepository;
import com.miiti.firstapp.domain.model.series.Series;
import com.miiti.firstapp.domain.model.series.SeriesId;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Projections;
import org.hibernate.query.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class HibernateCourseRepository extends HibernateSupport<Course> implements CourseRepository {

    HibernateCourseRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Page<Course> findBySeriesId(SeriesId seriesId,Pageable pageable) {

        Criteria criteria=getSession().createCriteria(Course.class);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        //criteria.add(Restrictions.ne("enquiryStatus", ENQUIRY.JOINED));

        criteria.add( Expression.eq("seriesId", seriesId.value() ) );

        criteria.setMaxResults(pageable.getPageSize());
        criteria.setFirstResult((pageable.getPageNumber() - 1) * pageable.getPageSize());
        List<Course> contents = criteria.list();

        Criteria criteriaCount = getSession().createCriteria(Course.class);
        criteriaCount.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteriaCount.setProjection(Projections.rowCount());
        Long totalNumberOfElements = (Long)criteriaCount.uniqueResult();

        return new PageImpl<Course>(
                contents,
                pageable.getPageNumber(),
                pageable.getPageSize(),
                totalNumberOfElements);
    }

    @Override
    public Course findById(CourseId courseId) {
        System.out.println(courseId.value());
        Query<Course> query = getSession().createQuery("from Course where id = :id", Course.class);
        query.setParameter("id", courseId.value());
        return query.uniqueResult();
    }
}

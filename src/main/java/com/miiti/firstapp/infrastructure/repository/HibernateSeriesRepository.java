package com.miiti.firstapp.infrastructure.repository;

import com.miiti.firstapp.domain.common.model.Page;
import com.miiti.firstapp.domain.common.model.PageImpl;

import com.miiti.firstapp.domain.model.series.Series;
import com.miiti.firstapp.domain.model.series.SeriesId;
import com.miiti.firstapp.domain.model.series.SeriesRepository;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.query.Query;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class HibernateSeriesRepository extends HibernateSupport<Series> implements SeriesRepository {

    HibernateSeriesRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Page<Series> findSeries(Pageable pageable) {

        Criteria criteria=getSession().createCriteria(Series.class);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        //criteria.add(Restrictions.ne("enquiryStatus", ENQUIRY.JOINED));
        criteria.setMaxResults(pageable.getPageSize());
        criteria.setFirstResult((pageable.getPageNumber() - 1) * pageable.getPageSize());
        List<Series> contents = criteria.list();

        Criteria criteriaCount = getSession().createCriteria(Series.class);
        criteriaCount.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteriaCount.setProjection(Projections.rowCount());
        Long totalNumberOfElements = (Long)criteriaCount.uniqueResult();

        return new PageImpl<Series>(
                contents,
                pageable.getPageNumber(),
                pageable.getPageSize(),
                totalNumberOfElements);
    }

    @Override
    public Series findById(SeriesId seriesId) {
        Query<Series> query = getSession().createQuery("from Series where id = :id", Series.class);
        query.setParameter("id", seriesId.value());
        return query.uniqueResult();
    }


}
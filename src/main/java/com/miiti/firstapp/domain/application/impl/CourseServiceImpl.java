package com.miiti.firstapp.domain.application.impl;

import com.miiti.firstapp.domain.application.BoardService;
import com.miiti.firstapp.domain.application.CourseService;
import com.miiti.firstapp.domain.common.event.DomainEventPublisher;
import com.miiti.firstapp.domain.common.model.Page;
import com.miiti.firstapp.domain.model.board.*;
import com.miiti.firstapp.domain.model.course.Course;
import com.miiti.firstapp.domain.model.course.CourseId;
import com.miiti.firstapp.domain.model.course.CourseRepository;
import com.miiti.firstapp.domain.model.series.Series;
import com.miiti.firstapp.domain.model.series.SeriesId;
import com.miiti.firstapp.domain.model.user.UserFinder;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    private CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository
                             ) {
        this.courseRepository = courseRepository;

    }

    @Override
    public Page<Course> findBySeriesId(SeriesId seriesId, Pageable pageable) {
        return courseRepository.findBySeriesId(seriesId, pageable);
    }

    @Override
    public Course findById(CourseId courseId) {
        return courseRepository.findById(courseId);
    }
}

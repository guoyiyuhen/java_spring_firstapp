package com.miiti.firstapp.domain.application.impl;

import com.miiti.firstapp.domain.application.CourseService;
import com.miiti.firstapp.domain.application.CourseUserService;
import com.miiti.firstapp.domain.common.model.Page;
import com.miiti.firstapp.domain.model.course.Course;
import com.miiti.firstapp.domain.model.course.CourseId;
import com.miiti.firstapp.domain.model.course.CourseRepository;
import com.miiti.firstapp.domain.model.courseuser.CourseUser;
import com.miiti.firstapp.domain.model.courseuser.CourseUserId;
import com.miiti.firstapp.domain.model.courseuser.CourseUserRepository;
import com.miiti.firstapp.domain.model.series.SeriesId;
import com.miiti.firstapp.domain.model.user.UserId;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CourseUserServiceImpl implements CourseUserService {
    private CourseUserRepository courseUserRepository;

    public CourseUserServiceImpl(CourseUserRepository courseUserRepository) {
        this.courseUserRepository = courseUserRepository;
    }

    @Override
    public CourseUser findById(CourseUserId courseUserId) {
        return courseUserRepository.findById(courseUserId);
    }

    @Override
    public CourseUser findByCourseIdAndUserId(CourseId courseId, UserId userId) {
        return courseUserRepository.findByCourseIdAndUserId(courseId, userId);
    }
}

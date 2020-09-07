package com.miiti.firstapp.domain.application;

import com.miiti.firstapp.domain.model.course.Course;
import com.miiti.firstapp.domain.model.course.CourseId;
import com.miiti.firstapp.domain.model.courseuser.CourseUser;
import com.miiti.firstapp.domain.model.courseuser.CourseUserId;
import com.miiti.firstapp.domain.model.user.UserId;

public interface CourseUserService {

    /**
     * Find board by its id
     *
     * @param courseUserId the id of the board
     * @return the board instance, null if not found
     */
    CourseUser findById(CourseUserId courseUserId);


    /**
     * Find board by its id
     *
     * @param courseId the id of the board
     * @return the board instance, null if not found
     */
    CourseUser findByCourseIdAndUserId(CourseId courseId, UserId userId);

}

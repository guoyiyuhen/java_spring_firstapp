package com.miiti.firstapp.domain.model.courseuser;

import com.miiti.firstapp.domain.common.model.Page;
import com.miiti.firstapp.domain.model.course.Course;
import com.miiti.firstapp.domain.model.course.CourseId;
import com.miiti.firstapp.domain.model.series.SeriesId;
import com.miiti.firstapp.domain.model.user.UserId;
import org.springframework.data.domain.Pageable;

public interface CourseUserRepository {

    /**
     * Find board by its id
     *
     * @param courseUserId the id of the board
     * @return the board instance or null if it doesn't exist
     */
    CourseUser findById(CourseUserId courseUserId);

    /**
     * Find board by its id
     *
     * @param courseId the id of the board
     * @return the board instance or null if it doesn't exist
     */
    CourseUser findByCourseIdAndUserId(CourseId courseId, UserId userId);

    /**
     * Save a course
     *
     * @param courseUser the board to save
     */
    void save(CourseUser courseUser);
}

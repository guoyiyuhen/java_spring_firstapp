package com.miiti.firstapp.domain.model.courseitemuser;

import com.miiti.firstapp.domain.common.model.Page;
import com.miiti.firstapp.domain.model.courseitem.CourseItem;
import com.miiti.firstapp.domain.model.courseitem.CourseItemId;
import com.miiti.firstapp.domain.model.series.SeriesId;
import org.springframework.data.domain.Pageable;

public interface CourseItemUserRepository {
    /**
     * Find the boards that a user is a member, including those boards
     * the user created as well as joined.
     *

     * @return a list of boards or an empty list if none found
     */
    Page<CourseItemUser> findByCourseId(SeriesId seriesId, Pageable pageable);

    /**
     * Find board by its id
     *
     * @param courseItemUserId the id of the board
     * @return the board instance or null if it doesn't exist
     */
    CourseItemUser findById(CourseItemUserId courseItemUserId);

    /**
     * Save a course
     *
     * @param courseItemUser the board to save
     */
    void save(CourseItemUser courseItemUser);
}

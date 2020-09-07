package com.miiti.firstapp.domain.model.courseitem;

import com.miiti.firstapp.domain.common.model.Page;
import com.miiti.firstapp.domain.model.course.CourseId;
import com.miiti.firstapp.domain.model.courseitemlist.CourseItemList;
import com.miiti.firstapp.domain.model.series.SeriesId;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseItemRepository {
    /**
     * Find card lists of a board
     *
     * @param courseId the id of the board
     * @return a list of card lists of a board or an empty list if none found
     */
    List<CourseItem> findByCourseId(CourseId courseId);

    /**
     * Find board by its id
     *
     * @param courseItemId the id of the board
     * @return the board instance or null if it doesn't exist
     */
    CourseItem findById(CourseItemId courseItemId);

    /**
     * Save a course
     *
     * @param courseItem the board to save
     */
    void save(CourseItem courseItem);
}

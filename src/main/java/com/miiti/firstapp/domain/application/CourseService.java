package com.miiti.firstapp.domain.application;

import com.miiti.firstapp.domain.common.model.Page;
import com.miiti.firstapp.domain.model.board.Board;
import com.miiti.firstapp.domain.model.board.BoardId;
import com.miiti.firstapp.domain.model.course.Course;
import com.miiti.firstapp.domain.model.course.CourseId;
import com.miiti.firstapp.domain.model.series.Series;
import com.miiti.firstapp.domain.model.series.SeriesId;
import org.springframework.data.domain.Pageable;

public interface CourseService {

    /**
     * Find the boards that a user is a member, including those boards
     * the user created as well as joined.
     *
     * @return a list of boards or an empty list if none found
     */
    Page<Course> findBySeriesId(SeriesId seriesId,Pageable pageable);

    /**
     * Find board by its id
     *
     * @param courseId the id of the board
     * @return the board instance, null if not found
     */
    Course findById(CourseId courseId);
}

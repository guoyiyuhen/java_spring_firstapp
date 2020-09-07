package com.miiti.firstapp.domain.model.series;

import com.miiti.firstapp.domain.common.model.Page;
import com.miiti.firstapp.domain.model.board.Board;
import com.miiti.firstapp.domain.model.course.Course;
import com.miiti.firstapp.domain.model.course.CourseId;
import com.miiti.firstapp.domain.model.user.UserId;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SeriesRepository  {
    /**
     * Find the boards that a user is a member, including those boards
     * the user created as well as joined.
     *

     * @return a list of boards or an empty list if none found
     */
    Page<Series> findSeries(Pageable pageable);

    /**
     * Find board by its id
     *
     * @param seriesId the id of the board
     * @return the board instance or null if it doesn't exist
     */
    Series findById(SeriesId seriesId);

    /**
     * Save a course
     *
     * @param series the board to save
     */
    void save(Series series);
}

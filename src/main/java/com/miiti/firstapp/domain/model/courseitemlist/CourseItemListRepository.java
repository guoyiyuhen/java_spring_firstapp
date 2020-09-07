package com.miiti.firstapp.domain.model.courseitemlist;

import com.miiti.firstapp.domain.common.model.Page;
import com.miiti.firstapp.domain.model.board.BoardId;
import com.miiti.firstapp.domain.model.cardlist.CardList;
import com.miiti.firstapp.domain.model.course.CourseId;
import com.miiti.firstapp.domain.model.courseitem.CourseItem;
import com.miiti.firstapp.domain.model.courseitem.CourseItemId;
import com.miiti.firstapp.domain.model.series.SeriesId;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseItemListRepository {
    /**
     * Find card lists of a board
     *
     * @param courseId the id of the board
     * @return a list of card lists of a board or an empty list if none found
     */
    List<CourseItemList> findByCourseId(CourseId courseId);

    /**
     * Find board by its id
     *
     * @param courseItemListId the id of the board
     * @return the board instance or null if it doesn't exist
     */
    CourseItemList findById(CourseItemListId courseItemListId);

    /**
     * Save a course
     *
     * @param courseItemList the board to save
     */
    void save(CourseItemList courseItemList);
}

package com.miiti.firstapp.domain.application;

import com.miiti.firstapp.domain.application.commands.AddCardListCommand;
import com.miiti.firstapp.domain.model.board.BoardId;
import com.miiti.firstapp.domain.model.cardlist.CardList;
import com.miiti.firstapp.domain.model.course.CourseId;
import com.miiti.firstapp.domain.model.courseitemlist.CourseItemList;

import java.util.List;

public interface CourseItemListService {
    /**
     * Find card lists of a board
     *
     * @param courseId id of the board
     * @return a list of card list instance or an empty list if none found
     */
    List<CourseItemList> findByCourseId(CourseId courseId);


}

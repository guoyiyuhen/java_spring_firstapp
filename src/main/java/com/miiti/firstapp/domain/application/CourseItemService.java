package com.miiti.firstapp.domain.application;

import com.miiti.firstapp.domain.model.board.BoardId;
import com.miiti.firstapp.domain.model.card.Card;
import com.miiti.firstapp.domain.model.card.CardId;
import com.miiti.firstapp.domain.model.course.CourseId;
import com.miiti.firstapp.domain.model.courseitem.CourseItem;
import com.miiti.firstapp.domain.model.courseitem.CourseItemId;

import java.util.List;

public interface CourseItemService {
    /**
     * Find all the cards of a board
     *
     * @param courseId the id of the board
     * @return a list of card instances or an empty list if none found
     */
    List<CourseItem> findByCourseId(CourseId courseId);

    /**
     * Find card by its id
     *
     * @param courseItemId the id of the card
     * @return a card instance or null if not found
     */
    CourseItem findById(CourseItemId courseItemId);
}

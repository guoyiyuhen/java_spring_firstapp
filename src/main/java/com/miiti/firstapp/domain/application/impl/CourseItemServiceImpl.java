package com.miiti.firstapp.domain.application.impl;

import com.miiti.firstapp.domain.application.CourseItemListService;
import com.miiti.firstapp.domain.application.CourseItemService;
import com.miiti.firstapp.domain.common.event.DomainEventPublisher;
import com.miiti.firstapp.domain.model.board.BoardId;
import com.miiti.firstapp.domain.model.card.Card;
import com.miiti.firstapp.domain.model.card.CardId;
import com.miiti.firstapp.domain.model.cardlist.CardListRepository;
import com.miiti.firstapp.domain.model.course.CourseId;
import com.miiti.firstapp.domain.model.courseitem.CourseItem;
import com.miiti.firstapp.domain.model.courseitem.CourseItemId;
import com.miiti.firstapp.domain.model.courseitem.CourseItemRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CourseItemServiceImpl implements CourseItemService {
    private CourseItemRepository courseItemRepository;
    private DomainEventPublisher domainEventPublisher;

    public CourseItemServiceImpl(CourseItemRepository courseItemRepository,
                               DomainEventPublisher domainEventPublisher) {
        this.courseItemRepository = courseItemRepository;
        this.domainEventPublisher = domainEventPublisher;
    }


    @Override
    public List<CourseItem> findByCourseId(CourseId courseId) {
        return courseItemRepository.findByCourseId(courseId);
    }

    @Override
    public CourseItem findById(CourseItemId courseItemId) {
        return courseItemRepository.findById(courseItemId);
    }
}

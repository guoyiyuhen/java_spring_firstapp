package com.miiti.firstapp.domain.application.impl;

import com.miiti.firstapp.domain.application.CourseItemListService;
import com.miiti.firstapp.domain.application.CourseService;
import com.miiti.firstapp.domain.application.commands.AddCardListCommand;
import com.miiti.firstapp.domain.common.event.DomainEventPublisher;
import com.miiti.firstapp.domain.model.board.BoardId;
import com.miiti.firstapp.domain.model.cardlist.CardList;
import com.miiti.firstapp.domain.model.cardlist.CardListRepository;
import com.miiti.firstapp.domain.model.cardlist.events.CardListAddedEvent;
import com.miiti.firstapp.domain.model.course.CourseId;
import com.miiti.firstapp.domain.model.courseitemlist.CourseItemList;
import com.miiti.firstapp.domain.model.courseitemlist.CourseItemListRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CourseItemListServiceImpl implements CourseItemListService {
    private CourseItemListRepository courseItemListRepository;
    private DomainEventPublisher domainEventPublisher;

    public CourseItemListServiceImpl(CourseItemListRepository courseItemListRepository,
                               DomainEventPublisher domainEventPublisher) {
        this.courseItemListRepository = courseItemListRepository;
        this.domainEventPublisher = domainEventPublisher;
    }

    @Override
    public List<CourseItemList> findByCourseId(CourseId courseId) {
        return courseItemListRepository.findByCourseId(courseId);
    }
}

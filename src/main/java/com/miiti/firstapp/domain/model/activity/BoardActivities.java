package com.miiti.firstapp.domain.model.activity;

import com.miiti.firstapp.domain.model.activity.Activity;
import com.miiti.firstapp.domain.model.activity.ActivityDetail;
import com.miiti.firstapp.domain.model.board.events.BoardCreatedEvent;
import com.miiti.firstapp.domain.model.board.events.BoardMemberAddedEvent;

public class BoardActivities {

  public static Activity from(BoardCreatedEvent event) {
    String detail = ActivityDetail.blank()
      .add("boardName", event.getBoardName())
      .toJson();
    return Activity.from(event.getUserId(), event.getBoardId(), com.miiti.firstapp.domain.model.activity.ActivityType.ADD_BOARD,
      detail, event.getIpAddress());
  }

  public static Activity from(BoardMemberAddedEvent event) {
    String detail = ActivityDetail.blank()
      .add("memberUserId", event.getMemberUserId().value())
      .add("memberName", event.getMemberName())
      .toJson();
    return Activity.from(event.getUserId(), event.getBoardId(), ActivityType.ADD_BOARD_MEMBER,
      detail, event.getIpAddress());
  }

}

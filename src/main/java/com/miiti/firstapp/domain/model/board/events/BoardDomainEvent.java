package com.miiti.firstapp.domain.model.board.events;

import com.miiti.firstapp.domain.model.board.BoardId;
import com.miiti.firstapp.domain.common.event.DomainEvent;
import com.miiti.firstapp.domain.common.event.TriggeredBy;
import com.miiti.firstapp.domain.model.board.BoardId;

public abstract class BoardDomainEvent extends DomainEvent {

  private static final long serialVersionUID = -147308556973863979L;

  private BoardId boardId;

  public BoardDomainEvent(BoardId boardId, TriggeredBy triggeredBy) {
    super(triggeredBy);
    this.boardId = boardId;
  }

  public BoardId getBoardId() {
    return boardId;
  }
}

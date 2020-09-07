package com.miiti.firstapp.domain.application.commands;

import com.miiti.firstapp.domain.model.board.BoardId;
import com.miiti.firstapp.domain.model.card.CardPosition;

import java.util.List;

public class ChangeCardPositionsCommand extends UserCommand {

  private BoardId boardId;
  private List<CardPosition> cardPositions;

  public ChangeCardPositionsCommand(BoardId boardId, List<CardPosition> cardPositions) {
    this.boardId = boardId;
    this.cardPositions = cardPositions;
  }

  public BoardId getBoardId() {
    return boardId;
  }

  public List<CardPosition> getCardPositions() {
    return cardPositions;
  }
}

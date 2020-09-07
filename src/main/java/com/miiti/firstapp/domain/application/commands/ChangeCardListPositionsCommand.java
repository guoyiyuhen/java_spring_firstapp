package com.miiti.firstapp.domain.application.commands;

import com.miiti.firstapp.domain.model.board.BoardId;
import com.miiti.firstapp.domain.model.cardlist.CardListPosition;

import java.util.List;

public class ChangeCardListPositionsCommand extends UserCommand {

  private BoardId boardId;
  private List<CardListPosition> cardListPositions;

  public ChangeCardListPositionsCommand(BoardId boardId, List<CardListPosition> cardListPositions) {
    this.boardId = boardId;
    this.cardListPositions = cardListPositions;
  }

  public BoardId getBoardId() {
    return boardId;
  }

  public List<CardListPosition> getCardListPositions() {
    return cardListPositions;
  }
}

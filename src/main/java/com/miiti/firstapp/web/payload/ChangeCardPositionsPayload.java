package com.miiti.firstapp.web.payload;

import com.miiti.firstapp.domain.application.commands.ChangeCardPositionsCommand;
import com.miiti.firstapp.domain.model.board.BoardId;
import com.miiti.firstapp.domain.model.card.CardPosition;

import java.util.List;

public class ChangeCardPositionsPayload {

    private long boardId;
    private List<CardPosition> cardPositions;

    public ChangeCardPositionsCommand toCommand() {
        return new ChangeCardPositionsCommand(new BoardId(boardId), cardPositions);
    }

    public void setBoardId(long boardId) {
        this.boardId = boardId;
    }

    public void setCardPositions(List<CardPosition> cardPositions) {
        this.cardPositions = cardPositions;
    }
}
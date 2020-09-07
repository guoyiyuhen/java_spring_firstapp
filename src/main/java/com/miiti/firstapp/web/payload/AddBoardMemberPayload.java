package com.miiti.firstapp.web.payload;

import com.miiti.firstapp.domain.application.commands.AddBoardMemberCommand;
import com.miiti.firstapp.domain.model.board.BoardId;

public class AddBoardMemberPayload {

    private String usernameOrEmailAddress;

    public AddBoardMemberCommand toCommand(BoardId boardId) {
        return new AddBoardMemberCommand(boardId, usernameOrEmailAddress);
    }

    public void setUsernameOrEmailAddress(String usernameOrEmailAddress) {
        this.usernameOrEmailAddress = usernameOrEmailAddress;
    }
}


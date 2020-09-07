package com.miiti.firstapp.web.payload;

import com.miiti.firstapp.domain.application.commands.AddCardCommentCommand;
import com.miiti.firstapp.domain.model.card.CardId;

public class AddCardCommentPayload {

  private String comment;

  public AddCardCommentCommand toCommand(CardId cardId) {
    return new AddCardCommentCommand(cardId, comment);
  }

  public void setComment(String comment) {
    this.comment = comment;
  }
}

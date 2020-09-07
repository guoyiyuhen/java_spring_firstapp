package com.miiti.firstapp.web.payload;

import com.miiti.firstapp.domain.application.commands.ChangeCardDescriptionCommand;
import com.miiti.firstapp.domain.model.card.CardId;

public class ChangeCardDescriptionPayload {

  private String description;

  public ChangeCardDescriptionCommand toCommand(long cardId) {
    return new ChangeCardDescriptionCommand(new CardId(cardId), description);
  }

  public void setDescription(String description) {
    this.description = description;
  }
}

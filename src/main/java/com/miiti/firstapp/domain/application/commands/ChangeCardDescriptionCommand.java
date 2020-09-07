package com.miiti.firstapp.domain.application.commands;

import com.miiti.firstapp.domain.model.card.CardId;

public class ChangeCardDescriptionCommand extends UserCommand {

  private CardId cardId;
  private String description;

  public ChangeCardDescriptionCommand(CardId cardId, String description) {
    this.cardId = cardId;
    this.description = description;
  }

  public CardId getCardId() {
    return cardId;
  }

  public String getDescription() {
    return description;
  }
}

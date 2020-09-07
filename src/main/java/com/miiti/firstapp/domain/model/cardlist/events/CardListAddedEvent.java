package com.miiti.firstapp.domain.model.cardlist.events;

import com.miiti.firstapp.domain.common.event.TriggeredBy;
import com.miiti.firstapp.domain.model.board.events.BoardDomainEvent;
import com.miiti.firstapp.domain.model.cardlist.CardList;
import com.miiti.firstapp.domain.model.cardlist.CardListId;

public class CardListAddedEvent extends BoardDomainEvent {

  private static final long serialVersionUID = -877934435476435188L;

  private CardListId cardListId;
  private String cardListName;

  public CardListAddedEvent(CardList cardList, TriggeredBy triggeredBy) {
    super(cardList.getBoardId(), triggeredBy);
    this.cardListId = cardList.getId();
    this.cardListName = cardList.getName();
  }

  public CardListId getCardListId() {
    return cardListId;
  }

  public String getCardListName() {
    return cardListName;
  }

  @Override
  public String toString() {
    return "CardListAddedEvent{" +
            "cardListId=" + cardListId +
            ", cardListName='" + cardListName + '\'' +
            '}';
  }
}

package com.miiti.firstapp.web.results;

import com.miiti.firstapp.domain.model.cardlist.CardList;
import org.springframework.http.ResponseEntity;

public class AddCardListResult {

  public static ResponseEntity<ApiResult> build(CardList cardList) {
    ApiResult apiResult = ApiResult.blank()
      .add("id", cardList.getId().value())
      .add("name", cardList.getName());
    return Result.ok(apiResult);
  }
}

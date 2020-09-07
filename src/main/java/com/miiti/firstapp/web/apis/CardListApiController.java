package com.miiti.firstapp.web.apis;

import com.miiti.firstapp.domain.application.CardListService;
import com.miiti.firstapp.domain.application.commands.AddCardListCommand;
import com.miiti.firstapp.domain.application.commands.ChangeCardListPositionsCommand;
import com.miiti.firstapp.domain.model.cardlist.CardList;
import com.miiti.firstapp.web.payload.AddCardListPayload;
import com.miiti.firstapp.web.payload.ChangeCardListPositionsPayload;
import com.miiti.firstapp.web.results.AddCardListResult;
import com.miiti.firstapp.web.results.ApiResult;
import com.miiti.firstapp.web.results.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CardListApiController extends AbstractBaseController {

    private CardListService cardListService;

    public CardListApiController(CardListService cardListService) {
        this.cardListService = cardListService;
    }

    @PostMapping("/api/card-lists")
    public ResponseEntity<ApiResult> addCardList(@RequestBody AddCardListPayload payload,
                                                 HttpServletRequest request) {
        AddCardListCommand command = payload.toCommand();
        addTriggeredBy(command, request);

        CardList cardList = cardListService.addCardList(command);
        return AddCardListResult.build(cardList);
    }

    @PostMapping("/api/card-lists/positions")
    public ResponseEntity<ApiResult> changeCardListPositions(@RequestBody ChangeCardListPositionsPayload payload,
                                                             HttpServletRequest request) {
        ChangeCardListPositionsCommand command = payload.toCommand();
        addTriggeredBy(command, request);

        cardListService.changePositions(command);
        return Result.ok();
    }
}

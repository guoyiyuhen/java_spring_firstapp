package com.miiti.firstapp.web.apis;

import com.miiti.firstapp.domain.application.CardService;
import com.miiti.firstapp.domain.application.commands.*;
import com.miiti.firstapp.domain.common.file.FileUrlCreator;
import com.miiti.firstapp.domain.common.security.CurrentUser;
import com.miiti.firstapp.domain.model.activity.Activity;
import com.miiti.firstapp.domain.model.attachment.Attachment;
import com.miiti.firstapp.domain.model.card.Card;
import com.miiti.firstapp.domain.model.card.CardId;
import com.miiti.firstapp.domain.model.user.SimpleUser;
import com.miiti.firstapp.web.payload.AddCardPayload;
import com.miiti.firstapp.web.payload.ChangeCardPositionsPayload;
import com.miiti.firstapp.web.payload.ChangeCardTitlePayload;
import com.miiti.firstapp.web.results.*;
import com.miiti.firstapp.web.updater.CardUpdater;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.miiti.firstapp.domain.application.commands.AddCardAttachmentCommand;
import org.springframework.web.multipart.MultipartFile;
import com.miiti.firstapp.web.payload.AddCardCommentPayload;
import com.miiti.firstapp.web.payload.ChangeCardDescriptionPayload;
import com.miiti.firstapp.web.results.CardActivitiesResult;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CardApiController extends AbstractBaseController {

    private CardService cardService;
    private CardUpdater cardUpdater;
    private FileUrlCreator fileUrlCreator;

    public CardApiController(CardService cardService,
                             CardUpdater cardUpdater,
                             FileUrlCreator fileUrlCreator) {
        this.cardService = cardService;
        this.cardUpdater = cardUpdater;
        this.fileUrlCreator = fileUrlCreator;
    }

    @PostMapping("/api/cards")
    public ResponseEntity<ApiResult> addCard(@RequestBody AddCardPayload payload,
                                             HttpServletRequest request) {
        AddCardCommand command = payload.toCommand();
        addTriggeredBy(command, request);

        Card card = cardService.addCard(command);
        cardUpdater.onCardAdded(payload.getBoardId(), card);
        return AddCardResult.build(card);
    }

    @GetMapping("/api/cards/{cardId}")
    public ResponseEntity<ApiResult> getCard(@PathVariable long cardId) {
        Card card = cardService.findById(new CardId(cardId));
        return CardResult.build(card);
    }

    @PostMapping("/api/cards/positions")
    public ResponseEntity<ApiResult> changeCardPositions(@RequestBody ChangeCardPositionsPayload payload,
                                                         HttpServletRequest request) {
        ChangeCardPositionsCommand command = payload.toCommand();
        addTriggeredBy(command, request);

        cardService.changePositions(command);
        return Result.ok();
    }

    @PutMapping("/api/cards/{cardId}/title")
    public ResponseEntity<ApiResult> changeTitle(@PathVariable long cardId,
                                                 @RequestBody ChangeCardTitlePayload payload,
                                                 HttpServletRequest request) {
        ChangeCardTitleCommand command = payload.toCommand(cardId);
        addTriggeredBy(command, request);

        cardService.changeCardTitle(command);
        return Result.ok();
    }

    @PutMapping("/api/cards/{cardId}/description")
    public ResponseEntity<ApiResult> changeDescription(@PathVariable long cardId,
                                                       @RequestBody ChangeCardDescriptionPayload payload,
                                                       HttpServletRequest request) {
        ChangeCardDescriptionCommand command = payload.toCommand(cardId);
        addTriggeredBy(command, request);

        cardService.changeCardDescription(command);
        return Result.ok();
    }

    @PostMapping("/api/cards/{cardId}/comments")
    public ResponseEntity<ApiResult> addCardComment(@PathVariable long cardId,
                                                    @RequestBody AddCardCommentPayload payload,
                                                    HttpServletRequest request) {
        AddCardCommentCommand command = payload.toCommand(new CardId(cardId));
        addTriggeredBy(command, request);

        Activity activity = cardService.addComment(command);
        return com.taskagile.web.results.CommentActivityResult.build(activity);
    }

    @GetMapping("/api/cards/{cardId}/activities")
    public ResponseEntity<ApiResult> getCardActivities(@PathVariable long cardId) {
        List<Activity> activities = cardService.findCardActivities(new CardId(cardId));
        return CardActivitiesResult.build(activities);
    }

    @PostMapping("/api/cards/{cardId}/attachments")
    public ResponseEntity<ApiResult> addAttachment(@PathVariable long cardId,
                                                   @RequestParam("file") MultipartFile file,
                                                   HttpServletRequest request) {
        AddCardAttachmentCommand command = new AddCardAttachmentCommand(cardId, file);
        addTriggeredBy(command, request);

        Attachment attachment = cardService.addAttachment(command);
        return AttachmentResult.build(attachment, fileUrlCreator);
    }

    @GetMapping("/api/cards/{cardId}/attachments")
    public ResponseEntity<ApiResult> getAttachments(@PathVariable long cardId) {
        List<Attachment> attachments = cardService.getAttachments(new CardId(cardId));
        return AttachmentResults.build(attachments, fileUrlCreator);
    }
}


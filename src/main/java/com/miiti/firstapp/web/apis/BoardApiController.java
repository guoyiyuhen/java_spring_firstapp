package com.miiti.firstapp.web.apis;

import com.miiti.firstapp.domain.application.BoardService;
import com.miiti.firstapp.domain.application.CardListService;
import com.miiti.firstapp.domain.application.CardService;
import com.miiti.firstapp.domain.application.TeamService;
import com.miiti.firstapp.domain.application.commands.AddBoardMemberCommand;
import com.miiti.firstapp.domain.application.commands.CreateBoardCommand;
import com.miiti.firstapp.domain.common.file.FileUrlCreator;
import com.miiti.firstapp.domain.model.board.Board;
import com.miiti.firstapp.domain.model.board.BoardId;
import com.miiti.firstapp.domain.model.card.Card;
import com.miiti.firstapp.domain.model.cardlist.CardList;
import com.miiti.firstapp.domain.model.team.Team;
import com.miiti.firstapp.domain.model.user.User;
import com.miiti.firstapp.domain.model.user.UserNotFoundException;
import com.miiti.firstapp.web.payload.AddBoardMemberPayload;
import com.miiti.firstapp.web.payload.CreateBoardPayload;
import com.miiti.firstapp.web.results.ApiResult;
import com.miiti.firstapp.web.results.BoardResult;
import com.miiti.firstapp.web.results.CreateBoardResult;
import com.miiti.firstapp.web.results.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BoardApiController extends AbstractBaseController {

    private BoardService boardService;
    private TeamService teamService;
    private CardListService cardListService;
    private CardService cardService;
    private FileUrlCreator fileUrlCreator;

    public BoardApiController(BoardService boardService,
                              TeamService teamService,
                              CardListService cardListService,
                              CardService cardService,
                              FileUrlCreator fileUrlCreator) {
        this.boardService = boardService;
        this.teamService = teamService;
        this.cardListService = cardListService;
        this.cardService = cardService;
        this.fileUrlCreator = fileUrlCreator;
    }

    @PostMapping("/api/boards")
    public ResponseEntity<ApiResult> createBoard(@RequestBody CreateBoardPayload payload,
                                                 HttpServletRequest request) {
        CreateBoardCommand command = payload.toCommand();
        addTriggeredBy(command, request);

        Board board = boardService.createBoard(command);
        return CreateBoardResult.build(board);
    }

    @GetMapping("/api/boards/{boardId}")
    public ResponseEntity<ApiResult> getBoard(@PathVariable("boardId") long rawBoardId) {
        BoardId boardId = new BoardId(rawBoardId);
        Board board = boardService.findById(boardId);
        if (board == null) {
            return Result.notFound();
        }
        List<User> members = boardService.findMembers(boardId);

        Team team = null;
        if (!board.isPersonal()) {
            team = teamService.findById(board.getTeamId());
        }

        List<CardList> cardLists = cardListService.findByBoardId(boardId);
        List<Card> cards = cardService.findByBoardId(boardId);

        return BoardResult.build(team, board, members, cardLists, cards, fileUrlCreator);
    }

    @PostMapping("/api/boards/{boardId}/members")
    public ResponseEntity<ApiResult> addMember(@PathVariable("boardId") long rawBoardId,
                                               @RequestBody AddBoardMemberPayload payload,
                                               HttpServletRequest request) {
        BoardId boardId = new BoardId(rawBoardId);
        Board board = boardService.findById(boardId);
        if (board == null) {
            return Result.notFound();
        }

        try {
            AddBoardMemberCommand command = payload.toCommand(boardId);
            addTriggeredBy(command, request);

            User member = boardService.addMember(command);

            ApiResult apiResult = ApiResult.blank()
                    .add("id", member.getId().value())
                    .add("shortName", member.getInitials());
            return Result.ok(apiResult);
        } catch (UserNotFoundException e) {
            return Result.failure("No user found");
        }
    }
}
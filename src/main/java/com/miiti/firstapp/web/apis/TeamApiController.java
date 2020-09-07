package com.miiti.firstapp.web.apis;

import com.miiti.firstapp.domain.application.TeamService;
import com.miiti.firstapp.domain.application.commands.CreateTeamCommand;
import com.miiti.firstapp.domain.model.team.Team;
import com.miiti.firstapp.web.payload.CreateTeamPayload;
import com.miiti.firstapp.web.results.ApiResult;
import com.miiti.firstapp.web.results.CreateTeamResult;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TeamApiController extends AbstractBaseController {

    private TeamService teamService;

    public TeamApiController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping("/api/teams")
    public ResponseEntity<ApiResult> createTeam(@RequestBody CreateTeamPayload payload,
                                                HttpServletRequest request) {
        CreateTeamCommand command = payload.toCommand();
        addTriggeredBy(command, request);

        Team team = teamService.createTeam(command);
        return CreateTeamResult.build(team);
    }
}

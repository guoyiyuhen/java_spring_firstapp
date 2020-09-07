package com.miiti.firstapp.web.results;

import com.miiti.firstapp.domain.model.board.Board;
import com.miiti.firstapp.domain.model.card.Card;
import com.miiti.firstapp.domain.model.cardlist.CardList;
import com.miiti.firstapp.domain.model.cardlist.CardListId;
import com.miiti.firstapp.domain.model.course.Course;
import com.miiti.firstapp.domain.model.series.Series;
import com.miiti.firstapp.domain.model.team.Team;
import com.miiti.firstapp.domain.model.user.User;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeriesResult {

    public static ResponseEntity<ApiResult> build(Series series) {
        ApiResult apiResult = ApiResult.blank()
                .add("id", series.getId().value())
                .add("title", series.getTitle())
                .add("description", series.getDescription());

        return Result.ok(apiResult);
    }
}

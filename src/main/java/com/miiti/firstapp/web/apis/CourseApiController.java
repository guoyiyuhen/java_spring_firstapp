package com.miiti.firstapp.web.apis;

import com.miiti.firstapp.domain.application.CourseItemListService;
import com.miiti.firstapp.domain.application.CourseItemService;
import com.miiti.firstapp.domain.application.CourseService;
import com.miiti.firstapp.domain.application.TeamService;
import com.miiti.firstapp.domain.common.file.FileUrlCreator;
import com.miiti.firstapp.domain.common.model.Page;
import com.miiti.firstapp.domain.common.security.CurrentUser;
import com.miiti.firstapp.domain.model.board.BoardId;
import com.miiti.firstapp.domain.model.card.Card;
import com.miiti.firstapp.domain.model.cardlist.CardList;
import com.miiti.firstapp.domain.model.course.Course;
import com.miiti.firstapp.domain.model.course.CourseId;
import com.miiti.firstapp.domain.model.courseitem.CourseItem;
import com.miiti.firstapp.domain.model.courseitemlist.CourseItemList;
import com.miiti.firstapp.domain.model.series.Series;
import com.miiti.firstapp.domain.model.series.SeriesId;
import com.miiti.firstapp.domain.model.team.Team;
import com.miiti.firstapp.domain.model.user.SimpleUser;
import com.miiti.firstapp.web.payload.CreateTeamPayload;
import com.miiti.firstapp.web.results.*;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class CourseApiController {
    private CourseService courseService;
    private CourseItemListService courseItemListService;
    private CourseItemService courseItemService;
    private FileUrlCreator fileUrlCreator;

    public CourseApiController(CourseService courseService,
                               CourseItemListService courseItemListService,
                               CourseItemService courseItemService,
                               FileUrlCreator fileUrlCreator) {
        this.courseService = courseService;
        this.courseItemListService = courseItemListService;
        this.courseItemService = courseItemService;
        this.fileUrlCreator = fileUrlCreator;
    }



    @GetMapping("/api/courses/{courseId}")
    public ResponseEntity<ApiResult> getCourse(@PathVariable("courseId") long rawCourseId) {
        CourseId courseId = new CourseId(rawCourseId);
        Course course = courseService.findById(courseId);
        if (course == null) {
            return Result.notFound();
        }

        List<CourseItemList> cardLists = courseItemListService.findByCourseId(courseId);
        List<CourseItem> cards = courseItemService.findByCourseId(courseId);

        return CourseResult.build(course, cardLists, cards, fileUrlCreator);
    }
}

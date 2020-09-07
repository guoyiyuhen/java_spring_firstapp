package com.miiti.firstapp.web.apis;

import com.miiti.firstapp.domain.application.CourseUserService;
import com.miiti.firstapp.domain.common.security.CurrentUser;
import com.miiti.firstapp.domain.model.course.CourseId;
import com.miiti.firstapp.domain.model.courseuser.CourseUser;
import com.miiti.firstapp.domain.model.user.SimpleUser;
import com.miiti.firstapp.web.results.ApiResult;
import com.miiti.firstapp.web.results.CourseUserResult;
import com.miiti.firstapp.web.results.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CourseUserApiController {
    private CourseUserService courseUserService;

    public CourseUserApiController(CourseUserService courseUserService) {
        this.courseUserService = courseUserService;
    }

    @GetMapping("/api/courseusers/{courseId}")
    public ResponseEntity<ApiResult> getCourse(@PathVariable("courseId") long rawCourseId,
                                               @CurrentUser SimpleUser currentUser) {
        CourseId courseId = new CourseId(rawCourseId);
        CourseUser courseUser = courseUserService.findByCourseIdAndUserId(courseId, currentUser.getUserId());
        if (courseUser == null) {
            return Result.notFound();
        }
        return CourseUserResult.build(courseUser);
    }
}

package com.miiti.firstapp.web.apis;

import com.miiti.firstapp.domain.application.CourseService;
import com.miiti.firstapp.domain.application.SeriesService;
import com.miiti.firstapp.domain.common.model.Page;
import com.miiti.firstapp.domain.model.course.Course;
import com.miiti.firstapp.domain.model.course.CourseId;
import com.miiti.firstapp.domain.model.series.Series;
import com.miiti.firstapp.domain.model.series.SeriesId;
import com.miiti.firstapp.web.results.*;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@CrossOrigin
@Controller
public class SeriesApiController {
    private SeriesService seriesService;
    private CourseService courseService;

    public SeriesApiController(SeriesService seriesService,
                               CourseService courseService) {
        this.seriesService = seriesService;
        this.courseService = courseService;
    }

    @GetMapping("/api/series")
    public ResponseEntity<ApiResult> getSeriesList(Pageable pageable) {

        Page<Series> seriesPage = seriesService.findSeries(pageable);
        return SeriesResults.build(seriesPage);
    }

    @GetMapping("/api/series/{seriesId}")
    public ResponseEntity<ApiResult> getSeries(@PathVariable("seriesId") long rawSeriesId) {
        SeriesId seriesId = new SeriesId(rawSeriesId);
        Series series = seriesService.findById(seriesId);
        return SeriesResult.build(series);
    }

    @GetMapping("/api/series/{seriesId}/courses")
    public ResponseEntity<ApiResult> getCourseList(@PathVariable("seriesId") long rawSeriesId,
                                                   Pageable pageable) {
        SeriesId seriesId = new SeriesId(rawSeriesId);
        Page<Course> coursePage = courseService.findBySeriesId(seriesId, pageable);
        System.out.println("ddd aaa ccc");
        System.out.println(coursePage);
        return CourseResults.build(coursePage);
    }
}

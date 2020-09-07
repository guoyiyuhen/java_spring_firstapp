package com.miiti.firstapp.web.results;

import com.miiti.firstapp.domain.common.model.Page;
import com.miiti.firstapp.domain.model.course.Course;
import com.miiti.firstapp.domain.model.series.Series;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class CourseResults {
    public static ResponseEntity<ApiResult> build(Page<Course> coursePage) {

        List<CourseResults.CourseData> seriesData = new ArrayList<>();
        for (Course course: coursePage.getContent()) {
            seriesData.add(new CourseResults.CourseData(course));
        }

        ApiResult result = ApiResult.blank()
                .add("data", seriesData)
                .add("totalPages", coursePage.getTotalPages())
                .add("currentPage", coursePage.getPageNumber())
                .add("pageSize", coursePage.getPageSize());

        return Result.ok(result);
    }

    private static class CourseData {
        private long id;
        private String title;


        CourseData(Course course) {
            this.id = course.getId().value();
            this.title = course.getTitle();


        }

        public Long getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }


    }
}

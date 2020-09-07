package com.miiti.firstapp.web.results;

import com.miiti.firstapp.domain.common.model.Page;
import com.miiti.firstapp.domain.model.series.Series;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class SeriesResults {
    public static ResponseEntity<ApiResult> build(Page<Series> seriesPage) {

        List<SeriesResults.SeriesData> seriesData = new ArrayList<>();
        for (Series series: seriesPage.getContent()) {
            seriesData.add(new SeriesData(series));
        }

        ApiResult result = ApiResult.blank()
                .add("data", seriesData)
                .add("totalPages", seriesPage.getTotalPages())
                .add("currentPage", seriesPage.getPageNumber())
                .add("pageSize", seriesPage.getPageSize());

        return Result.ok(result);
    }

    private static class SeriesData {
        private long id;
        private String title;
        private String description;

        SeriesData(Series series) {
            this.id = series.getId().value();
            this.title = series.getTitle();
            this.description = series.getDescription();
        }

        public Long getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }
    }
}

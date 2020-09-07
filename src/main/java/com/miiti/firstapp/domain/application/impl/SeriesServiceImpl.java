package com.miiti.firstapp.domain.application.impl;

import com.miiti.firstapp.domain.application.BoardService;
import com.miiti.firstapp.domain.application.SeriesService;
import com.miiti.firstapp.domain.common.model.Page;
import com.miiti.firstapp.domain.model.board.Board;
import com.miiti.firstapp.domain.model.board.BoardRepository;
import com.miiti.firstapp.domain.model.course.Course;
import com.miiti.firstapp.domain.model.course.CourseId;
import com.miiti.firstapp.domain.model.series.Series;
import com.miiti.firstapp.domain.model.series.SeriesId;
import com.miiti.firstapp.domain.model.series.SeriesRepository;
import com.miiti.firstapp.domain.model.user.UserId;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SeriesServiceImpl implements SeriesService {

    private SeriesRepository seriesRepository;

    public SeriesServiceImpl(
            SeriesRepository seriesRepository){
        this.seriesRepository = seriesRepository;
    }

    @Override
    public Page<Series> findSeries(Pageable pageable) {
        return seriesRepository.findSeries(pageable);
    }

    @Override
    public Series findById(SeriesId seriesId) {
        return seriesRepository.findById(seriesId);
    }
}

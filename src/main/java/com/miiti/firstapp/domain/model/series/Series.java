package com.miiti.firstapp.domain.model.series;

import com.miiti.firstapp.domain.common.model.AbstractBaseEntity;
import com.miiti.firstapp.domain.model.course.Course;
import com.miiti.firstapp.domain.model.course.CourseId;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "series")
public class Series extends AbstractBaseEntity {

    private static final long serialVersionUID = -7789177855101967490L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    /**
     * Create new board
     */
    public static Series create(String title, String description) {
        Series series = new Series();
        series.title = description;
        series.description = description;
        return series;
    }

    public SeriesId getId() {
        return new SeriesId(id);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Series)) return false;
        Series series = (Series) o;
        return id == series.id &&
                Objects.equals(title, series.title) &&
                Objects.equals(description, series.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description=" + description +
                ", createdDate=" + createdDate +
                '}';
    }
}

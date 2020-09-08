package com.miiti.firstapp.domain.model.course;

import com.miiti.firstapp.domain.common.model.AbstractBaseEntity;
import com.miiti.firstapp.domain.model.courseitem.CourseItem;
import com.miiti.firstapp.domain.model.series.SeriesId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "course")
public class Course extends AbstractBaseEntity {

    private static final long serialVersionUID = -7789177855101967490L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "series_id")
    private long seriesId;

    @OneToMany(targetEntity=CourseItem.class, mappedBy = "courseId", fetch=FetchType.EAGER)
    private List<CourseItem> courseItemList ;

    @Column(name = "type")
    private int type;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "url")
    private String url;

    @Column(name = "content")
    private String content;

    @Column(name = "sentence")
    private String sentence;

    @Column(name = "word")
    private String word;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    /**
     * Create new board
     */
    public static Course create(int type,int seriesId, String title, String content, String sentence, String word) {
        Course course = new Course();
        course.type = type;
        course.seriesId = seriesId;
        course.title = title;
        course.content = content;
        course.sentence = sentence;
        course.word = word;
        course.createdDate = new Date();
        return course;
    }

    public CourseId getId() {
        return new CourseId(id);
    }

    public SeriesId getSeriesId() {
        return new SeriesId(id);
    }

    public int getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public List<CourseItem> getCourseItem() {
        return courseItemList;
    }

    public String getContent() {
        return content;
    }

    public String getSentence() {
        return sentence;
    }

    public String getWord() {
        return word;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return type == course.type &&
                Objects.equals(title, course.title) &&
                Objects.equals(content, course.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, title, content);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", content=" + content +
                ", createdDate=" + createdDate +
                '}';
    }
}

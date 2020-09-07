package com.miiti.firstapp.domain.model.courseuser;

import com.miiti.firstapp.domain.common.model.AbstractBaseEntity;
import com.miiti.firstapp.domain.model.course.Course;
import com.miiti.firstapp.domain.model.course.CourseId;
import com.miiti.firstapp.domain.model.courseitem.CourseItem;
import com.miiti.firstapp.domain.model.courseitemuser.CourseItemUser;
import com.miiti.firstapp.domain.model.series.SeriesId;
import com.miiti.firstapp.domain.model.user.UserId;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "course_user")
public class CourseUser extends AbstractBaseEntity {

    private static final long serialVersionUID = -7789177855101967490L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "course_id")
    private long courseId;

    @Column(name = "user_id")
    private long userId;

    @OneToMany(targetEntity= CourseItemUser.class, mappedBy = "courseId", fetch=FetchType.EAGER)
    private List<CourseItemUser> courseItemUserList ;

    @Column(name = "type")
    private int type;

    @Column(name = "title", nullable = false)
    private String title;

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
    public static CourseUser create(int type, int courseId, int userId, String title, String content, String sentence, String word) {
        CourseUser courseUser = new CourseUser();
        courseUser.type = type;
        courseUser.courseId = courseId;
        courseUser.userId = userId;
        courseUser.title = title;
        courseUser.content = content;
        courseUser.sentence = sentence;
        courseUser.word = word;
        courseUser.createdDate = new Date();
        return courseUser;
    }

    public CourseUserId getId() {
        return new CourseUserId(id);
    }

    public CourseId getCourseId() {
        return new CourseId(id);
    }

    public UserId getUserId() {
        return new UserId(id);
    }

    public List<CourseItemUser> getCourseItemUser() {
        return courseItemUserList;
    }

    public int getType() {
        return type;
    }

    public String getTitle() {
        return title;
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
        if (!(o instanceof CourseUser)) return false;
        CourseUser courseUser = (CourseUser) o;
        return type == courseUser.type &&
                Objects.equals(title, courseUser.title) &&
                Objects.equals(content, courseUser.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, title, content);
    }

    @Override
    public String toString() {
        return "CourseUser{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", content=" + content +
                ", createdDate=" + createdDate +
                '}';
    }
}

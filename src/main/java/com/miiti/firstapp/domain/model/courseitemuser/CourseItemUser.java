package com.miiti.firstapp.domain.model.courseitemuser;

import com.miiti.firstapp.domain.common.model.AbstractBaseEntity;
import com.miiti.firstapp.domain.model.course.CourseId;
import com.miiti.firstapp.domain.model.courseitem.CourseItem;
import com.miiti.firstapp.domain.model.courseitem.CourseItemId;
import com.miiti.firstapp.domain.model.series.SeriesId;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "course_item_user")
public class CourseItemUser extends AbstractBaseEntity {

    private static final long serialVersionUID = -7789177855101967490L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "course_id")
    private long courseId;

    @Column(name = "course_item_id")
    private long courseItemId;

    @Column(name = "type")
    private int type;

    @Column(name = "content")
    private String content;

    @Column(name = "play_num")
    private int playNum;

    @Column(name = "record_num")
    private int recordNum;

    @Column(name = "write_num")
    private int writeNum;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    /**
     * Create new courseItemUser
     */
    public static CourseItemUser create(int type, int courseId, String content, int playNum, int recordNum, int writeNum) {
        CourseItemUser courseItemUser = new CourseItemUser();
        courseItemUser.type = type;
        courseItemUser.courseId = courseId;
        courseItemUser.playNum = playNum;
        courseItemUser.content = content;
        courseItemUser.recordNum = recordNum;
        courseItemUser.writeNum = writeNum;
        courseItemUser.createdDate = new Date();
        return courseItemUser;
    }

    public CourseId getId() {
        return new CourseId(id);
    }

    public CourseItemId getCourseItemId() {
        return new CourseItemId(id);
    }

    public int getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public int getPlayNum() {
        return playNum;
    }

    public int getRecordNum() {
        return recordNum;
    }

    public int getWriteNum() {
        return writeNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourseItemUser)) return false;
        CourseItemUser courseItemUser = (CourseItemUser) o;
        return type == courseItemUser.type &&
                Objects.equals(courseId, courseItemUser.courseId) &&
                Objects.equals(content, courseItemUser.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, courseId, content);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", content=" + content +
                ", createdDate=" + createdDate +
                '}';
    }
}

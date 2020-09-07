package com.miiti.firstapp.domain.model.courseitem;

import com.miiti.firstapp.domain.common.model.AbstractBaseEntity;
import com.miiti.firstapp.domain.model.course.CourseId;
import com.miiti.firstapp.domain.model.courseitemlist.CourseItemListId;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "course_item")
public class CourseItem extends AbstractBaseEntity {

    private static final long serialVersionUID = -7789177855101967490L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "course_id")
    private long courseId;

    @Column(name = "course_item_list_id")
    private long courseItemListId;

    @Column(name = "type")
    private int type;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "start_time")
    private int startTime;

    @Column(name = "end_time")
    private int endTime;

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
     * Create new board
     */
    public static CourseItem create(int type, int courseId, String content, int playNum, int recordNum, int writeNum) {
        CourseItem courseItem = new CourseItem();
        courseItem.type = type;
        courseItem.courseId = courseId;
        courseItem.playNum = playNum;
        courseItem.content = content;
        courseItem.recordNum = recordNum;
        courseItem.writeNum = writeNum;
        courseItem.createdDate = new Date();
        return courseItem;
    }

    public CourseItemId getId() {
        return new CourseItemId(id);
    }

    public CourseId getCourseId() {
        return new CourseId(id);
    }

    public CourseItemListId getCourseItemListId() {
        return new CourseItemListId(courseItemListId);
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

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
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
        if (!(o instanceof CourseItem)) return false;
        CourseItem courseItem = (CourseItem) o;
        return type == courseItem.type &&
                Objects.equals(courseId, courseItem.courseId) &&
                Objects.equals(content, courseItem.content);
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

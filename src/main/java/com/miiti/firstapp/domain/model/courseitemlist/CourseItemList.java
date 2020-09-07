package com.miiti.firstapp.domain.model.courseitemlist;

import com.miiti.firstapp.domain.common.model.AbstractBaseEntity;
import com.miiti.firstapp.domain.model.course.CourseId;
import com.miiti.firstapp.domain.model.courseitem.CourseItem;
import com.miiti.firstapp.domain.model.courseitem.CourseItemId;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "course_item_list")
public class CourseItemList extends AbstractBaseEntity {

    private static final long serialVersionUID = -7789177855101967490L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "course_id")
    private long courseId;

    @Column(name = "type")
    private int type;

    @Column(name = "name")
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    /**
     * Create new board
     */
    public static CourseItemList create(int type, int courseId, String name) {
        CourseItemList courseItemList = new CourseItemList();
        courseItemList.type = type;
        courseItemList.courseId = courseId;
        courseItemList.name = name;
        courseItemList.createdDate = new Date();
        return courseItemList;
    }

    public CourseItemId getId() {
        return new CourseItemId(id);
    }

    public CourseId getCourseId() {
        return new CourseId(id);
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourseItemList)) return false;
        CourseItemList courseItemList = (CourseItemList) o;
        return type == courseItemList.type &&
                Objects.equals(courseId, courseItemList.courseId) &&
                Objects.equals(name, courseItemList.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, courseId, name);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", name=" + name +
                ", createdDate=" + createdDate +
                '}';
    }
}

package com.miiti.firstapp.web.results;

import com.miiti.firstapp.domain.common.file.FileUrlCreator;
import com.miiti.firstapp.domain.model.card.Card;
import com.miiti.firstapp.domain.model.cardlist.CardList;
import com.miiti.firstapp.domain.model.cardlist.CardListId;
import com.miiti.firstapp.domain.model.course.Course;
import com.miiti.firstapp.domain.model.courseitem.CourseItem;
import com.miiti.firstapp.domain.model.courseitemlist.CourseItemList;
import com.miiti.firstapp.domain.model.courseitemlist.CourseItemListId;
import com.miiti.firstapp.domain.model.user.User;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseResult {
    public static ResponseEntity<ApiResult> build(Course course, List<CourseItemList> courseItemLists, List<CourseItem> courseItems,
                                                  FileUrlCreator fileUrlCreator) {

        Map<String, Object> courseData = new HashMap<>();
        courseData.put("id", course.getId().value());
        courseData.put("title", course.getTitle());
        courseData.put("content", course.getContent());




        List<CourseResult.CourseItemListData> courseItemListData = new ArrayList<>();
        Map<CourseItemListId, List<CourseItem>> courseItemsByList = new HashMap<>();
        for (CourseItem courseItem: courseItems) {
            System.out.println(courseItem.getCourseItemListId());
            courseItemsByList.computeIfAbsent(courseItem.getCourseItemListId(), k -> new ArrayList<>()).add(courseItem);
        }



        System.out.println("dd cc gg");
        System.out.println(courseItemsByList);

        for (CourseItemList courseItemList: courseItemLists) {
            System.out.println(courseItemsByList.get(courseItemList.getId()));
            System.out.println("dd cc gg2");
            courseItemListData.add(new CourseResult.CourseItemListData(courseItemList, courseItemsByList.get(courseItemList.getId()), fileUrlCreator));
        }

        ApiResult result = ApiResult.blank()
                .add("course", courseData)
                .add("courseLists", courseItemListData);

        return Result.ok(result);
    }

    private static class CourseItemListData {
        private long id;
        private String name;
        private int position;
        private List<CourseResult.CourseItemData> courseItems = new ArrayList<>();

        CourseItemListData(CourseItemList courseItemList, List<CourseItem> courseItems, FileUrlCreator fileUrlCreator) {
            this.id = courseItemList.getId().value();
            this.name = courseItemList.getName();


            if (courseItems != null) {
                for (CourseItem courseItem: courseItems) {
                    this.courseItems.add(new CourseResult.CourseItemData(courseItem, fileUrlCreator));
                }
            }
        }

        public long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getPosition() {
            return position;
        }

        public List<CourseResult.CourseItemData> getCourseItems() {
            return courseItems;
        }
    }

    private static class CourseItemData {
        private long id;
        private int type;
        private String content;
        private int startTime;
        private int endTime;

        CourseItemData(CourseItem courseItem, FileUrlCreator fileUrlCreator) {
            this.id = courseItem.getId().value();
            this.type = courseItem.getType();
            this.content = courseItem.getContent();
            this.startTime = courseItem.getStartTime();
            this.endTime = courseItem.getEndTime();
        }

        public long getId() {
            return id;
        }

        public int getType() {
            return type;
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
    }
}

package com.miiti.firstapp.web.results;

import com.miiti.firstapp.domain.model.courseitemuser.CourseItemUser;
import com.miiti.firstapp.domain.model.courseuser.CourseUser;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class CourseUserResult {
    public static ResponseEntity<ApiResult> build(CourseUser courseUser) {

        List<CourseUserResult.CourseItemUserData> courseItemUserData = new ArrayList<>();
        for (CourseItemUser courseItemUser: courseUser.getCourseItemUser()) {
            courseItemUserData.add(new CourseUserResult.CourseItemUserData(courseItemUser));
        }

        ApiResult apiResult = ApiResult.blank()
                .add("id", courseUser.getId().value())
                .add("title", courseUser.getTitle())
                .add("content", courseUser.getContent())
                .add("title", courseUser.getTitle())
                .add("sentence", courseUser.getSentence())
                .add("items", courseItemUserData)
                .add("word", courseUser.getWord());

        return Result.ok(apiResult);
    }

    private static class CourseItemUserData {
        private int type;
        private String content;
        private int playNum;
        private int recordNum;
        private int writeNum;
        private long id;

        CourseItemUserData(CourseItemUser courseItemUser) {
            this.type = courseItemUser.getType();
            this.content = courseItemUser.getContent();
            this.playNum = courseItemUser.getPlayNum();
            this.recordNum = courseItemUser.getRecordNum();
            this.writeNum = courseItemUser.getWriteNum();
            this.id = courseItemUser.getId().value();
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

        public long getId() {
            return id;
        }
    }
}

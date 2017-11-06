package com.ums;

import com.ums.ws.*;
import spark.Spark;

/**
 * Created by W7 on 06.11.2017.
 */
public class Main {
    public static void main(String[] args) {
        Spark.port(8080);

        Spark.post("/user/student/add", new UserStudentAdd());
        Spark.post("/user/teacher/add", new UserTeacherAdd());
        Spark.post("/course/add", new CourseAdd());
        Spark.post("/semester/add", new SemesterAdd());
        Spark.post("/group/add", new GroupAdd());
        Spark.post("/teacher/course/add", new TeacherCourseAdd());
        Spark.post("/teacher/course/remove", new TeacherCourseRemove());
        Spark.post("/group/teacher/add", new GroupTeacherAdd());
        Spark.post("/group/teacher/remove", new GroupTeacherRemove());
        Spark.post("/group/student/add", new GroupStudentAdd());
        Spark.post("/group/student/remove", new GroupStudentRemove());
    }
}

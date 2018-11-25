package clicky.com.clickyapp.models;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class Course implements Serializable {
    private static final long serialVersionUID = 42L;

    private UUID mId;
    private Date mDateCreated;
    private String mCourseTitle;
    private String mCourseCode;
    private String mCourseInstructor;

    public Course() {
        init();
    }

    private void init() {
        mId = UUID.randomUUID();
        mDateCreated = new Date();
        mCourseTitle = "";
        mCourseCode = "";
    }

    public UUID getId() {
        return mId;
    }

    public Date getDateCreated() {
        return mDateCreated;
    }

    public String getCourseTitle() {
        return mCourseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        mCourseTitle = courseTitle;
    }

    public String getCourseCode() {
        return mCourseCode;
    }

    public void setCourseCode(String courseCode) {
        mCourseCode = courseCode;
    }

    public String getCourseInstructor() {
        return mCourseInstructor;
    }

    public void setCourseInstructor(String courseInstructor) {
        mCourseInstructor = courseInstructor;
    }
}

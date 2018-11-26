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
    private transient User mCourseInstructor;

    public Course() {
        init();
    }

    public Course(UUID id, Date dateCreated, String courseTitle, String courseCode, User courseInstructor) {
        mId = id;
        mDateCreated = dateCreated;
        mCourseTitle = courseTitle;
        mCourseCode = courseCode;
        mCourseInstructor = courseInstructor;
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

    public User getCourseInstructor() {
        return mCourseInstructor;
    }

    public void setCourseInstructor(User courseInstructor) {
        mCourseInstructor = courseInstructor;
    }
}

package clicky.com.clickyapp.models;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class CourseStore {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void getCourses(OnCourseAddedListener onCourseAddedListener){

    }

    public interface OnCourseAddedListener {
        void onCourseAdded(List<Course> courses);
    }
}

package clicky.com.clickyapp.models;

import android.app.Activity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CourseStore {

    private static CourseStore sInstance;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Activity mActivity;

    public static CourseStore get(Activity activity) {
        if (sInstance == null) {
            sInstance = new CourseStore(activity);
        }
        return sInstance;
    }

    private CourseStore(Activity activity) {
        mActivity = activity;
    }

    public void listenForCourseUpdates(final OnCoursesUpdatedListener onCoursesUpdatedListener) {
        db.collection("users").document(User.get().getId()).collection("courses").addSnapshotListener(mActivity, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                List<Course> courses = new ArrayList<>();
                for (DocumentChange documentChange : documentSnapshots.getDocumentChanges()) {
                    courses.add(documentChange.getDocument().toObject(Course.class));
                }
                if (onCoursesUpdatedListener != null) {
                    onCoursesUpdatedListener.onCourseUpdated(courses);
                }
            }
        });
    }

    public void getCourses(OnCoursesReturnedListener listener){

    }

    public void addCourse(Course course) {
        db.collection("courses").document(course.getId().toString()).set(course);
        db.collection("users").document(User.get().getId()).collection("courses").document(course.getId().toString()).set(course);
    }

    public interface OnCoursesUpdatedListener {
        void onCourseUpdated(List<Course> courses);
    }

    public interface OnCoursesReturnedListener {
        void onCoursesReturned(List<Course> courses);
    }
}

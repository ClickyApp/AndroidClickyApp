package clicky.com.clickyapp.fragments.instructor;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import clicky.com.clickyapp.models.Course;

public class ChatFragment extends Fragment {

    private static final String ARGS_COURSE = "ARGS_COURSE";

    public static Fragment newInstance(Course course) {
        Bundle args = new Bundle();
        args.putSerializable(ARGS_COURSE, course);
        Fragment fragment = new Fragment();
        fragment.setArguments(args);
        return fragment;
    }
}

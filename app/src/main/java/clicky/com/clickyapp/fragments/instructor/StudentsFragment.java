package clicky.com.clickyapp.fragments.instructor;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import clicky.com.clickyapp.models.Course;

public class StudentsFragment extends Fragment {

    private static final String ARGS_COURSE = "ARGS_COURSE";

    public static Fragment newInstance(Course course){
        Bundle args = new Bundle();
        Fragment fragment = new StudentsFragment();
        args.putSerializable(ARGS_COURSE, course);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText("Students fragment");
        return textView;
    }
}

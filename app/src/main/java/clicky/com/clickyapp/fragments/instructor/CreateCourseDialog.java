package clicky.com.clickyapp.fragments.instructor;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import clicky.com.clickyapp.activities.R;
import clicky.com.clickyapp.models.Course;
import clicky.com.clickyapp.models.User;

import static android.app.Activity.RESULT_OK;

public class CreateCourseDialog extends DialogFragment {

    private EditText mEditTextCourseTitle;
    private EditText mEditTextCourseCode;
    private Button mButtonCreateCourse;
    private Button mButtonCancel;

    public static final String EXTRA_CREATED_COURSE = "clicky.com.clickyapp.fragments.instructor.created_course";

    public static DialogFragment newInstance() {
        Bundle args = new Bundle();
        DialogFragment fragment = new CreateCourseDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_course, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        initViews();
    }

    private void initViews() {
        View view = getView();
        mEditTextCourseTitle = view.findViewById(R.id.edit_text_course_title);
        mEditTextCourseCode = view.findViewById(R.id.edit_text_course_code);
        mButtonCreateCourse = view.findViewById(R.id.button_create_course);
        mButtonCancel = view.findViewById(R.id.button_cancel_create_course);
        mButtonCreateCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Course course = new Course();
                course.setCourseTitle(mEditTextCourseTitle.getText().toString());
                course.setCourseCode(mEditTextCourseCode.getText().toString());
                course.setCourseInstructor(User.get());
                Intent intent = new Intent();
                intent.putExtra(EXTRA_CREATED_COURSE, course);
                getTargetFragment().onActivityResult(getTargetRequestCode(), RESULT_OK, intent);
                dismiss();
            }
        });
        mButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}

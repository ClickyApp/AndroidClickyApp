package clicky.com.clickyapp.fragments.instructor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import clicky.com.clickyapp.activities.R;
import clicky.com.clickyapp.fragments.CoursesFragment;
import clicky.com.clickyapp.models.Course;
import clicky.com.clickyapp.models.CourseStore;

import static android.app.Activity.RESULT_OK;
import static clicky.com.clickyapp.fragments.instructor.CreateCourseDialog.EXTRA_CREATED_COURSE;

public class InstructorCoursesFragment extends CoursesFragment {

    private static final String DIALOG_CREATE_COURSE = "DIALOG_CREATE_COURSE";
    private static final int CREATE_COURSE_REQ_CODE = 1;

    public static Fragment newInstance(OnCourseSelectedListener onCourseSelectedListener) {
        Bundle args = new Bundle();
        Fragment fragment = new InstructorCoursesFragment();
        args.putSerializable(ARGS_COURSE_SELECTED_LISTENER, onCourseSelectedListener);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getMenuId() {
        return R.menu.instructor_fragment_courses;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_course:
                // This will show the course creation screen
                DialogFragment dialog = CreateCourseDialog.newInstance();
                dialog.setTargetFragment(this, CREATE_COURSE_REQ_CODE);
                dialog.show(getFragmentManager(), DIALOG_CREATE_COURSE);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CREATE_COURSE_REQ_CODE && resultCode == RESULT_OK && data.hasExtra(EXTRA_CREATED_COURSE)) {
            Course course = (Course) data.getSerializableExtra(EXTRA_CREATED_COURSE);
            if (course != null) {
                CourseStore.get(getActivity()).addCourse(course);
            }
        }
    }
}

package clicky.com.clickyapp.activities.instructor;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;

import clicky.com.clickyapp.activities.SingleFragmentActivity;
import clicky.com.clickyapp.fragments.CoursesFragment;
import clicky.com.clickyapp.fragments.instructor.InstructorCoursesFragment;
import clicky.com.clickyapp.models.Course;

public class InstructorCoursesActivity extends SingleFragmentActivity implements CoursesFragment.OnCourseSelectedListener {

    private static final String LOG_TAG = InstructorCoursesActivity.class.getSimpleName();

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, InstructorCoursesActivity.class);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        return InstructorCoursesFragment.newInstance(this);
    }

    @Override
    public void onCourseSelected(Course course) {
        Log.d(LOG_TAG, "On Course started");
        startActivity(InstructorActivity.newIntent(this, course));
        finish();
    }
}

package clicky.com.clickyapp.activities.instructor;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import clicky.com.clickyapp.activities.SingleFragmentActivity;
import clicky.com.clickyapp.fragments.instructor.CoursesFragment;

public class CoursesActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, CoursesActivity.class);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        return CoursesFragment.newInstance();
    }
}

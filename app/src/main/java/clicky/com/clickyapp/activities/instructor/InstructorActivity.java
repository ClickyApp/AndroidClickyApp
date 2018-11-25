package clicky.com.clickyapp.activities.instructor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

import clicky.com.clickyapp.R;
import clicky.com.clickyapp.fragments.instructor.ChatFragment;
import clicky.com.clickyapp.fragments.instructor.CoursesFragment;
import clicky.com.clickyapp.fragments.instructor.StudentsFragment;
import clicky.com.clickyapp.fragments.instructor.QuizzesFragment;
import clicky.com.clickyapp.models.Course;

public class InstructorActivity extends AppCompatActivity {
    private static final String EXTRA_COURSE = "clicky.com.clickyapp.activities.instructor.course";
    private ViewPager mViewPagerMyCourses;
    private Course mCourse;

    public static Intent newIntent(Context packageContext, Course course) {
        Intent intent = new Intent(packageContext, InstructorActivity.class);
        intent.putExtra(EXTRA_COURSE, course);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCourse = (Course)getIntent().getSerializableExtra(EXTRA_COURSE);
        initViews();
    }

    private void initViews(){
        mViewPagerMyCourses = findViewById(R.id.view_pager_my_courses);
        List<Fragment> fragments = Arrays.asList(StudentsFragment.newInstance(mCourse), ChatFragment.newInstance(mCourse), new QuizzesFragment());
        mViewPagerMyCourses.setAdapter(new MyCoursesPagerAdapter(getSupportFragmentManager(), fragments));
        mViewPagerMyCourses.setCurrentItem(1);
    }

    private class MyCoursesPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> mFragments;

        public MyCoursesPagerAdapter(FragmentManager fragmentManager, List<Fragment> fragments) {
            super(fragmentManager);
            mFragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }
}

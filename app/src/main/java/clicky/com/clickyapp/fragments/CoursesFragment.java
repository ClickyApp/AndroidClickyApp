package clicky.com.clickyapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.MenuRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import clicky.com.clickyapp.activities.R;
import clicky.com.clickyapp.models.Course;
import clicky.com.clickyapp.models.CourseStore;

public abstract class CoursesFragment extends Fragment implements CourseStore.OnCoursesUpdatedListener {

    private static final String LOG_TAG = CoursesFragment.class.getSimpleName();
    protected static final String ARGS_COURSE_SELECTED_LISTENER = "ARGS_COURSE_SELECTED_LISTENER";
    private OnCourseSelectedListener mOnCourseSelectedListener;
    private RecyclerView mRecyclerViewCourses;
    private CoursesAdapter mCoursesAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        CourseStore.get(getActivity()).listenForCourseUpdates(this);
        if(getArguments().containsKey(ARGS_COURSE_SELECTED_LISTENER)) {
            mOnCourseSelectedListener = (OnCourseSelectedListener)getArguments().getSerializable(ARGS_COURSE_SELECTED_LISTENER);
        }
        initViews();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_courses, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(getMenuId(), menu);
    }

    @MenuRes
    public abstract int getMenuId();

    public void initViews() {
        View view = getView();
        mRecyclerViewCourses = view.findViewById(R.id.recycler_view_courses);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerViewCourses.setLayoutManager(layoutManager);
    }

    @Override
    public void onCourseUpdated(List<Course> courses) {
        updateList(courses);
    }

    private void updateList(List<Course> courses){
        if (mCoursesAdapter == null) {
            mCoursesAdapter = new CoursesAdapter(courses);
        }
        mCoursesAdapter.setCourses(courses);
        mRecyclerViewCourses.setAdapter(mCoursesAdapter);
    }

    private class CoursesAdapter extends RecyclerView.Adapter<CourseViewHolder> {

        private List<Course> mCourses;

        public CoursesAdapter(List<Course> courses) {
            mCourses = courses;
        }

        @NonNull
        @Override
        public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.list_item_course, parent, false);
            return new CourseViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
            holder.bind(mCourses.get(position));
        }

        @Override
        public int getItemCount() {
            return mCourses.size();
        }

        private void setCourses(List<Course> courses) {
            mCourses = courses;
        }
    }

    private class CourseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTextViewCourseTitle;
        private TextView mTextViewCourseCode;
        private TextView mTextViewCourseInstructor;
        private Course mCourse;

        public CourseViewHolder(View itemView) {
            super(itemView);
            mTextViewCourseTitle = itemView.findViewById(R.id.text_view_course_title);
            mTextViewCourseCode = itemView.findViewById(R.id.text_view_course_code);
            mTextViewCourseInstructor = itemView.findViewById(R.id.text_view_instructor_name);
            itemView.setOnClickListener(this);
        }

        public void bind(Course course) {
            mCourse = course;
            mTextViewCourseTitle.setText(mCourse.getCourseTitle());
            mTextViewCourseCode.setText(mCourse.getCourseCode());
            mTextViewCourseInstructor.setText(mCourse.getCourseInstructor().getEmail());
        }

        @Override
        public void onClick(View v) {
            if(mOnCourseSelectedListener != null) {
                mOnCourseSelectedListener.onCourseSelected(mCourse);
            }
            else {
                Log.e(LOG_TAG, "OnCoursesSelectedListener is null");
            }
        }
    }

    public interface OnCourseSelectedListener extends Serializable {
        void onCourseSelected(Course course);
    }
}

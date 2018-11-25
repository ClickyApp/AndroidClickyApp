package clicky.com.clickyapp.fragments.instructor;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import clicky.com.clickyapp.R;
import clicky.com.clickyapp.models.Course;

public class CoursesFragment extends Fragment {

    private RecyclerView mRecyclerViewCourses;
    private CoursesAdapter mCoursesAdapter;

    public static Fragment newInstance() {
        Bundle args = new Bundle();
        Fragment fragment = new CoursesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_instructor_courses, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        initViews();
    }

    public void initViews() {
        View view = getView();
        mRecyclerViewCourses = view.findViewById(R.id.recycler_view_courses);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerViewCourses.setLayoutManager(layoutManager);
    }

    private void updateUi() {
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
    private class CourseViewHolder extends RecyclerView.ViewHolder {

        public CourseViewHolder(View itemView) {
            super(itemView);
        }

        public void bind(Course course) {

        }
    }
}

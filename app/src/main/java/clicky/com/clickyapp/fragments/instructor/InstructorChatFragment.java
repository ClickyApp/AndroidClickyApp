package clicky.com.clickyapp.fragments.instructor;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import clicky.com.clickyapp.activities.R;
import clicky.com.clickyapp.models.Course;

public class InstructorChatFragment extends Fragment {

    private static final String ARGS_COURSE = "ARGS_COURSE";
    private RecyclerView mRecyclerViewMessages;
    private EditText mEditTextMessages;
    private Button mButtonSendMessage;

    public static Fragment newInstance(Course course) {
        Bundle args = new Bundle();
        args.putSerializable(ARGS_COURSE, course);
        Fragment fragment = new InstructorChatFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        initViews();
    }

    private void initViews() {
        View view = getView();
        mRecyclerViewMessages = view.findViewById(R.id.recycler_view_messages);
        mEditTextMessages = view.findViewById(R.id.edit_text_message_input);
        mButtonSendMessage = view.findViewById(R.id.img_message_send);

        mButtonSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }
}

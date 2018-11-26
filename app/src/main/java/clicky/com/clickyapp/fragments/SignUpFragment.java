package clicky.com.clickyapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;

import clicky.com.clickyapp.activities.R;
import clicky.com.clickyapp.models.AuthUtil;

public class SignUpFragment extends Fragment {
    private static final String ARGS_ON_SIGN_UP_COMPLETE_LISTENER = "ARGS_ON_SIGN_UP_COMPLETE_LISTENER";
    private static final String ARGS_ON_AlREADY_HAVE_ACCOUNT_COMPLETE_LISTENER = "ARGS_ON_AlREADY_HAVE_ACCOUNT_COMPLETE_LISTENER";
    private EditText mEditTextFirstName;
    private EditText mEditTextLastName;
    private EditText mEditTextEmail;
    private EditText mEditTextPassword;
    private Button mButtonAlreadyHaveAccount;
    private Button mButtonSignUp;

    private AuthUtil.OnSignUpCompleteListener mOnSignUpCompleteListener;
    private OnAlreadyHaveAccountListener mOnAlreadyHaveAccountListener;

    public static Fragment newInstance(AuthUtil.OnSignUpCompleteListener onSignUpCompleteListener, OnAlreadyHaveAccountListener onAlreadyHaveAccountListener) {
        Bundle args = new Bundle();
        Fragment fragment = new SignUpFragment();

        if (onSignUpCompleteListener != null) {
            args.putSerializable(ARGS_ON_SIGN_UP_COMPLETE_LISTENER, onSignUpCompleteListener);
        }

        if (onAlreadyHaveAccountListener != null) {
            args.putSerializable(ARGS_ON_AlREADY_HAVE_ACCOUNT_COMPLETE_LISTENER, onAlreadyHaveAccountListener);
        }

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initViews();
        AuthUtil.OnSignUpCompleteListener onSignUpCompleteListener = (AuthUtil.OnSignUpCompleteListener) getArguments().getSerializable(ARGS_ON_SIGN_UP_COMPLETE_LISTENER);
        if (onSignUpCompleteListener != null) {
            mOnSignUpCompleteListener = onSignUpCompleteListener;
        }

        OnAlreadyHaveAccountListener onAlreadyHaveAccountListener = (OnAlreadyHaveAccountListener) getArguments().getSerializable(ARGS_ON_AlREADY_HAVE_ACCOUNT_COMPLETE_LISTENER);
        if (onAlreadyHaveAccountListener != null) {
            mOnAlreadyHaveAccountListener = onAlreadyHaveAccountListener;
        }
    }

    private void initViews() {
        View view = getView();
        mEditTextFirstName = view.findViewById(R.id.edit_text_first_name);
        mEditTextLastName = view.findViewById(R.id.edit_text_last_name);
        mEditTextEmail = view.findViewById(R.id.edit_text_email);
        mEditTextPassword = view.findViewById(R.id.edit_text_password);
        mButtonAlreadyHaveAccount = view.findViewById(R.id.button_already_have_account);
        mButtonSignUp = view.findViewById(R.id.button_sign_up);

        mButtonAlreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnAlreadyHaveAccountListener != null) {
                    mOnAlreadyHaveAccountListener.onAlreadyHaveAccount();
                }
            }
        });

        mButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = mEditTextFirstName.getText().toString();
                String lastName = mEditTextLastName.getText().toString();
                String email = mEditTextEmail.getText().toString();
                String password = mEditTextPassword.getText().toString();
                AuthUtil.get().signUpWithEmail(email, password, firstName, lastName, mOnSignUpCompleteListener);
            }
        });
    }

    public interface OnAlreadyHaveAccountListener extends Serializable {
        void onAlreadyHaveAccount();
    }
}

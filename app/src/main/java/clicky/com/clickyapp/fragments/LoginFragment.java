package clicky.com.clickyapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;

import clicky.com.clickyapp.R;
import clicky.com.clickyapp.models.AuthUtil;
import clicky.com.clickyapp.models.User;

public class LoginFragment extends Fragment {
    private static final String LOG_TAG = LoginFragment.class.getSimpleName();
    private static final String ARGS_ON_LOG_IN_COMPLETE_LISTENER = "ARGS_ON_LOG_IN_COMPLETE_LISTENER";
    private static final String ARGS_ON_MAKE_ACCOUNT_LISTENER = "ARGS_ON_MAKE_ACCOUNT_LISTENER";
    private EditText mEditTextEmail;
    private EditText mEditTextPassword;
    private Button mButtonMakeAccount;
    private Button mButtonForgotPassword;
    private Button mButtonLogin;

    private AuthUtil.OnLoginCompleteListener mOnLoginCompleteListener;
    private OnMakeAccountListener mOnMakeAccountListener;

    public static Fragment newInstance(AuthUtil.OnLoginCompleteListener onLoginCompleteListener, OnMakeAccountListener onMakeAccountListener) {
        Bundle args = new Bundle();
        if (onLoginCompleteListener != null) {
            args.putSerializable(ARGS_ON_LOG_IN_COMPLETE_LISTENER, onLoginCompleteListener);
        }
        if (onMakeAccountListener != null) {
            args.putSerializable(ARGS_ON_MAKE_ACCOUNT_LISTENER, onMakeAccountListener);
        }

        Fragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        initViews();
        AuthUtil.OnLoginCompleteListener onLoginCompleteListener = (AuthUtil.OnLoginCompleteListener) getArguments().getSerializable(ARGS_ON_LOG_IN_COMPLETE_LISTENER);
        if (onLoginCompleteListener != null) {
            mOnLoginCompleteListener = onLoginCompleteListener;
        }
        OnMakeAccountListener onMakeAccountListener = (OnMakeAccountListener) getArguments().getSerializable(ARGS_ON_MAKE_ACCOUNT_LISTENER);
        if (onMakeAccountListener != null) {
            mOnMakeAccountListener = onMakeAccountListener;
        }
    }

    private void initViews() {
        View view = getView();
        mEditTextEmail = view.findViewById(R.id.edit_text_email);
        mEditTextPassword = view.findViewById(R.id.edit_text_password);
        mButtonMakeAccount = view.findViewById(R.id.button_make_account);
        mButtonForgotPassword = view.findViewById(R.id.button_forgot_password);
        mButtonLogin = view.findViewById(R.id.button_login);

        mButtonMakeAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnMakeAccountListener != null) {
                    mOnMakeAccountListener.onMakeAccount();
                } else {
                    Log.e(LOG_TAG, "OnMakeAccountListener is null");
                }
            }
        });

        mButtonForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a dialog fragment which asks which email to reset the password for
                // TODO: Implement this

            }
        });

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = mEditTextEmail.getText().toString();
                String password = mEditTextPassword.getText().toString();
                AuthUtil.get().loginWithEmail(email, password, mOnLoginCompleteListener);
            }
        });
    }

    public interface OnMakeAccountListener extends Serializable {
        void onMakeAccount();
    }
}

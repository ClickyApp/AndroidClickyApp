package clicky.com.clickyapp.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import clicky.com.clickyapp.activities.instructor.CoursesActivity;
import clicky.com.clickyapp.fragments.LoginFragment;
import clicky.com.clickyapp.fragments.SignUpFragment;
import clicky.com.clickyapp.models.AuthUtil;
import clicky.com.clickyapp.models.User;

public class AuthActivity extends SingleFragmentActivity implements AuthUtil.OnLoginCompleteListener, AuthUtil.OnSignUpCompleteListener, SignUpFragment.OnAlreadyHaveAccountListener, AuthUtil.OnVerificationEmailSentListener, LoginFragment.OnMakeAccountListener {
    private static final long serialVersionUID = 42L;

    @Override
    public Fragment createFragment() {

        return SignUpFragment.newInstance(this, this);
    }

    private void showCourses() {
        if (User.get().isTeacher()) {
            Intent myCoursesIntent = CoursesActivity.newIntent(this);
            startActivity(myCoursesIntent);
            finish();
        } else {

        }

        finish();
    }

    @Override
    public void onLoginComplete(boolean isSuccess, Exception exception) {
        if (isSuccess && User.get().isEmailVerified()) {
            showCourses();
        } else {
            // Show the login screen
            Toast.makeText(this, "Please verify your email", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onSignUpComplete(boolean isSuccess, Exception exception) {
        if (isSuccess) {
            // Send the Verification Email
            AuthUtil.get().sendEmailVerification(User.get().getEmail(), this);
        } else {
            // Show the sign up screen
        }
    }

    @Override
    public void onAlreadyHaveAccount() {
        setCurrentFragment(LoginFragment.newInstance(this, this));
    }

    @Override
    public void onVerificationEmailSent(boolean isSuccess, Exception exception) {
        if (isSuccess) {
            setCurrentFragment(LoginFragment.newInstance(this, this));
            Toast.makeText(this, "Email sent, please verify your email", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Unable to send verification email please try again", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onMakeAccount() {
        setCurrentFragment(SignUpFragment.newInstance(this, this));
    }
}

package clicky.com.clickyapp.models;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.io.Serializable;

public class AuthUtil {
    private static final String LOG_TAG = AuthUtil.class.getSimpleName();
    private static AuthUtil sInstance;
    private FirebaseAuth mAuth;

    public static AuthUtil get() {
        if (sInstance == null) {
            sInstance = new AuthUtil();
        }
        return sInstance;
    }

    private AuthUtil() {
        mAuth = FirebaseAuth.getInstance();
    }

    public void sendEmailVerification(final String email, final OnVerificationEmailSentListener onVerificationEmailSentListener) {
        mAuth.getCurrentUser().sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (onVerificationEmailSentListener != null) {
                            onVerificationEmailSentListener.onVerificationEmailSent(task.isSuccessful(), task.getException());
                        } else {
                            Log.e(LOG_TAG, "OnVerificationEmailSentListener null");
                        }
                    }
                });
    }

    public void signUpWithEmail(String email, String password, final String firstName, final String lastName, final OnSignUpCompleteListener onSignUpCompleteListener) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User.get().setDisplayName(firstName, lastName);
                        }
                        if (onSignUpCompleteListener != null) {
                            onSignUpCompleteListener.onSignUpComplete(task.isSuccessful(), task.getException());
                        } else {
                            Log.e(LOG_TAG, "OnSignUpCompleteListener null");
                        }
                    }
                });
    }

    public void loginWithEmail(String email, String password, final OnLoginCompleteListener onLoginCompleteListener) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (onLoginCompleteListener != null) {
                            onLoginCompleteListener.onLoginComplete(task.isSuccessful(), task.getException());
                        } else {
                            Log.e(LOG_TAG, "OnLoginCompleteListener null");
                        }
                    }
                });
    }

    public void resetPasswordForEmail(String email, final OnPasswordResetListener onPasswordResetListener) {
        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (onPasswordResetListener != null) {
                            onPasswordResetListener.onPasswordReset(task.isSuccessful(), task.getException());
                        }
                    }
                });
    }

    public interface OnLoginCompleteListener extends Serializable {
        void onLoginComplete(boolean isSuccess, Exception exception);
    }

    public interface OnSignUpCompleteListener extends Serializable {
        void onSignUpComplete(boolean isSuccess, Exception exception);
    }

    public interface OnVerificationEmailSentListener extends Serializable {
        void onVerificationEmailSent(boolean isSuccess, Exception exception);
    }

    public interface OnPasswordResetListener extends Serializable {
        void onPasswordReset(boolean isSuccess, Exception exception);
    }
}

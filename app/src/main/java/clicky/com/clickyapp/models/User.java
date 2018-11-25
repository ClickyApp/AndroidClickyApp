package clicky.com.clickyapp.models;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;

public class User {
    private static User sInstance;
    private static final String LOG_TAG = User.class.getSimpleName();

    private FirebaseAuth mAuth;

    private String mFirstName;
    private String mLastName;
    private String mEmail;
    private boolean isLoggedIn;

    private User() {
        mAuth = FirebaseAuth.getInstance();
    }

    public static User get() {
        if (sInstance == null) {
            sInstance = new User();
        }
        return sInstance;
    }

    public String getFirstName() {
        String[] splitName = mAuth.getCurrentUser().getDisplayName().split("\\s+");
        if (isLoggedIn() && splitName.length >= 2) {
            return splitName[0];
        }
        return null;
    }

    public String getLastName() {
        String[] splitName = mAuth.getCurrentUser().getDisplayName().split("\\s+");
        if (isLoggedIn() && splitName.length >= 2) {
            return splitName[splitName.length - 1];
        }
        return null;
    }

    public String getEmail() {
        if (isLoggedIn()) {
            return mAuth.getCurrentUser().getEmail();
        }
        return null;
    }

    public void setDisplayName(String firstName, String lastName) {
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(firstName + " " + lastName)
                .build();
        mAuth.getCurrentUser().updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (!task.isSuccessful()) {
                            Log.e(LOG_TAG, "Could not update display name: " + task.getException().toString());
                        }
                    }
                });
    }

    public boolean isLoggedIn() {
        return mAuth.getCurrentUser() != null && mAuth.getCurrentUser().isEmailVerified();
    }

    public boolean isTeacher() {
        // Get all teachers and see if my email is in that list :)
        return true;
    }

    public boolean isEmailVerified() {
        return mAuth.getCurrentUser().isEmailVerified();
    }
}

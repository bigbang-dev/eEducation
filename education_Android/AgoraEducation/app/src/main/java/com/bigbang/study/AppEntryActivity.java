package com.bigbang.study;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.bigbang.MainActivity;
import com.bigbang.study.util.MessageUtil;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.bigbang.R;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class AppEntryActivity extends AppCompatActivity implements OnCompleteListener<AuthResult>, OnFailureListener {


    private static final int RC_SIGN_IN = 123;
    public static final String AUTH = "AUTH";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_entry);
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        // this is temporary code for testing. Signout code should be removed in production
//        if(mAuth !=  null) {
//            mAuth.signOut();
//        }
        if (mAuth.getCurrentUser() == null) {
            try {
                createSignInIntent();
            } catch (Exception e) {
                MessageUtil.showToastMessage("Not able to start the application. Please try later",
                        getApplicationContext());
                return;
            }
        } else {
            stopProgress();
            onSuccessfulInitialisation();
        }
    }

    public void createSignInIntent() {
        // [START auth_fui_create_intent]
        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build()
//                , new AuthUI.IdpConfig.PhoneBuilder().build()
//                , new AuthUI.IdpConfig.GoogleBuilder().build()
//                , new AuthUI.IdpConfig.FacebookBuilder().build()
//                , new AuthUI.IdpConfig.TwitterBuilder().build()
        );

        // Create and launch sign-in intent
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN);
        // [END auth_fui_create_intent]
    }

    // [START auth_fui_result]
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                onSuccessfulInitialisation();

                // ...
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }
    }
// [END auth_fui_result]

    private void onSuccessfulInitialisation() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final Context context = this;
        user.getIdToken(true)
                .addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
                    public void onComplete(@NonNull Task<GetTokenResult> task) {
                        if (task.isSuccessful()) {

                            GetTokenResult result = task.getResult();
                            String idToken = result.getToken();
                            // Send token to your backend via HTTPS


                            SharedPreferences auth = getSharedPreferences(AUTH, 0);
                            SharedPreferences.Editor editor = auth.edit();
                            editor.putString("bearer_token", idToken);
                            editor.commit();
                            stopProgress();

                            Intent intent = new Intent(context, MainActivity.class);
                            startActivity(intent);

//                            SharedPreferences token = getSharedPreferences(MyFirebaseMessagingService
//                                    .ANDROID_TOKEN, 0);
//                            SharedPreferences.Editor tokenEditor = auth.edit();
//
//                            String androidToken = token.getString("android_token", "");
//                            if(!androidToken.isEmpty()) {
////                                sendTokenToServerAndContinue(androidToken);
//                            }
                            // ...
                        } else {
                            // Handle error -> task.getException();
                            FirebaseAuth.getInstance().signOut();
                        }
                    }
                });

    }


    @Override
    public void onResume() {
        super.onResume();
    }


    private void signInAnonymously() throws InterruptedException, ExecutionException, TimeoutException {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null)
            return;
        Task<AuthResult> task = mAuth.signInAnonymously();
        task.addOnCompleteListener(this).addOnFailureListener(this);


    }

    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if (task.isSuccessful()) {
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            Log.e("TAG", "signInAnonymously:SUCCESS" + mAuth.getCurrentUser() + "");


            stopProgress();

            onSuccessfulInitialisation();
        } else {
            MessageUtil.showToastMessage("Not able to start the application. Please try later",
                    this);
        }
    }

    private void stopProgress() {
        ProgressBar progressBar = findViewById(R.id.startupprogress);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onFailure(@NonNull Exception e) {
        MessageUtil.showToastMessage("Not able to start the application. Please try later",
                this);
    }

}

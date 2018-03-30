package com.ksapps.shelfshare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private Button btnProfile,btnSearch,btnAddBook,btnChatRoom;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        if (!isUserLogin()) {
            signOut();
        }
        setContentView(R.layout.activity_main);
        setTitle(getString(R.string.app_names));
        btnProfile = (Button) findViewById(R.id.btnProfile);
        btnSearch = (Button)findViewById(R.id.btnSearch);
        btnAddBook = (Button)findViewById(R.id.btnAddBook);
        btnChatRoom = (Button)findViewById(R.id.btnChatRoom);

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser mUser = auth.getCurrentUser();
                Intent i = new Intent(MainActivity.this, ProfileActivity.class);
                i.putExtra("email", mUser.getEmail());
                i.putExtra("name", mUser.getDisplayName());
                startActivity(i);
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SearchBooksActivity.class);
                startActivity(i);
            }
        });

        btnAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AddBooksActivity.class);
                startActivity(i);
            }
        });

        btnChatRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ChatListActivity.class);
                startActivity(i);
            }
        });
//        Button deleteUserButton = (Button)findViewById(R.id.delete_user);
//        deleteUserButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                auth.getCurrentUser().delete().addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if(task.isSuccessful()){
//                            signOut();
//                        }else{
//                            displayMessage(getString(R.string.user_deletion_error));
//                        }
//                    }
//                });
//            }
//        });
    }

    private boolean isUserLogin() {
        if (auth.getCurrentUser() != null) {
            return true;
        }
        return false;
    }

    private void signOut() {
        Intent signOutIntent = new Intent(this, LoginActivity.class);
        startActivity(signOutIntent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.m1, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.LogOut) {
            AuthUI.getInstance()
                    .signOut(MainActivity.this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                signOut();
                            }
                        }
                    });
        }
        if (item.getItemId() == R.id.About) {
            Snackbar.make(findViewById(android.R.id.content), "App developed by KSapps", Snackbar.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}

package com.ksapps.shelfshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchBooksActivity extends AppCompatActivity {

    private ListView lvBooksList;
    private DatabaseReference mDataRef;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    ArrayList<String> ads = new ArrayList<>();
    ArrayList<String> users = new ArrayList<>();
    ArrayList<String> loc = new ArrayList<>();
    String requiredString,username,location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_books);

        lvBooksList = (ListView)findViewById(R.id.lvBooksList);

        final ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,ads);
        lvBooksList.setAdapter(arrayAdapter);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mDataRef = FirebaseDatabase.getInstance().getReference().child("ad_info");

        mDataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child : dataSnapshot.getChildren()){
                    HashMap<String, String> map = (HashMap<String, String>)child.getValue();
                    if(!map.get("username").equals(mUser.getDisplayName())) {
                        requiredString = map.get("ad_title");
                        username = map.get("username");
                        location = map.get("landmark");
                        ads.add(requiredString);
                        users.add(username);
                        loc.add(location);
                        arrayAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        lvBooksList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(SearchBooksActivity.this, AdActivity.class);
                intent.putExtra("ad_name",ads.get(i));
                intent.putExtra("username",users.get(i));
                intent.putExtra("location",loc.get(i));
                startActivity(intent);
                finish();
            }
        });
    }

}

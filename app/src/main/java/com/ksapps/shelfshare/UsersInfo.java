package com.ksapps.shelfshare;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Kshitij on 12-03-2018.
 */

public class UsersInfo {
    String user_name;
    String email_id;
    String phone_no;
    String address;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    DatabaseReference mDataRef;

    public UsersInfo() {
    }

    public String getUser_name() {
        return user_name;
    }

    public String getEmail() {
        return email_id;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public String getAddress() {
        return address;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setEmail(String email_id) {
        this.email_id = email_id;
    }

    public void setPhone_no(String phone_no) {this.phone_no = phone_no; }

    public void setAddress(String address) {
        this.address = address;
    }

//    public void setValuePhone(final String val){
//        mAuth = FirebaseAuth.getInstance();
//        mUser = mAuth.getCurrentUser();
//        mDataRef = FirebaseDatabase.getInstance().getReference();
//        mDataRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if(dataSnapshot.child(val).getValue(UsersInfo.class).getPhone_no()==null){
//                    mDataRef.child(val).child("phone_no").setValue("");
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }

    public void setValueAddress(final String val){
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mDataRef = FirebaseDatabase.getInstance().getReference();
        mDataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(val).getValue(UsersInfo.class).getAddress()==null){
                    mDataRef.child(val).child("address").setValue("");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

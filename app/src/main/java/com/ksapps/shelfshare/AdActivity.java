package com.ksapps.shelfshare;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.w3c.dom.Text;

import java.util.HashMap;

public class AdActivity extends AppCompatActivity {

    ImageView ivAdImage;
    ImageButton userAdPhoto;
    RatingBar rbRating;
    TextView tvDescription,tvDays,tvFreeDonate,tvAdName,tvUser;
    Button btnLocation,btnRequest;
    DatabaseReference mRef;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    StorageReference mStorage,mRef2;
    String description,username,days,freedonate,adName,location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);

        ivAdImage = (ImageView)findViewById(R.id.ivAdImage);
        userAdPhoto =(ImageButton)findViewById(R.id.userAdPhoto);
        rbRating = (RatingBar)findViewById(R.id.rbRating);
        tvDescription = (TextView)findViewById(R.id.tvDescription);
        tvDays = (TextView)findViewById(R.id.tvDays);
        tvAdName = (TextView)findViewById(R.id.tvAdName);
        tvFreeDonate = (TextView)findViewById(R.id.tvFreeDonate);
        btnLocation = (Button)findViewById(R.id.btnLocation);
        btnRequest = (Button)findViewById(R.id.btnRequest);
        tvUser = (TextView)findViewById(R.id.tvUser);

        Intent i = getIntent();
        adName = i.getStringExtra("ad_name");
        username = i.getStringExtra("username");
        location = i.getStringExtra("location");
        tvUser.setText(username);
        Log.v("Sdd",username);

        mAuth = FirebaseAuth.getInstance();
        mRef = FirebaseDatabase.getInstance().getReference().child("ad_info");
        mRef2 = FirebaseStorage.getInstance().getReference().child("images");
        mUser = mAuth.getCurrentUser();
        mStorage = FirebaseStorage.getInstance().getReference().child("ad_image");

        mStorage.child(adName).getBytes(Long.MAX_VALUE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                ivAdImage.setImageBitmap(bmp);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });
        mRef2.child(username).getBytes(Long.MAX_VALUE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                RoundedBitmapDrawable roundedBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(getResources(), bmp);
                roundedBitmapDrawable.setCircular(true);
                userAdPhoto.setImageDrawable(roundedBitmapDrawable);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });

        mStorage.child(adName).getBytes(Long.MAX_VALUE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                ivAdImage.setImageBitmap(bmp);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    HashMap<String,String> map = (HashMap<String, String>)ds.getValue();
                        if(map.get("ad_title").equals(adName)) {
                            description = map.get("description");
                            days = map.get("days");
                            freedonate = map.get("free_donate");
                            tvDescription.setText("Description:\n" + description);
                            tvDays.setText(days + " days ago");
                            tvFreeDonate.setText(freedonate);
                            tvAdName.setText(adName);
                            break;
                        }
                    }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdActivity.this,ChatRoomActivity.class);
                i.putExtra("messageReceiver",username);
                startActivity(i);
            }
        });

        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("geo:0,0?q="+location));
                startActivity(i);
            }
        });
    }
}

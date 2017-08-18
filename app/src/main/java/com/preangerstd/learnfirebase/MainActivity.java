package com.preangerstd.learnfirebase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference dbRef;
    private Button btnFirebase;

    private int increments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbRef = FirebaseDatabase.getInstance().getReference();
        btnFirebase = (Button) findViewById(R.id.firebaseBtn);

        //store data
        btnFirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 1. create child
                // 2. write data to child
                dbRef.child("UserId" + increments ).setValue(increments);
                increments++;
            }
        });
    }
}

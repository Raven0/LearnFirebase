package com.preangerstd.learnfirebase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference dbRef;
    private Button btnFirebase;
    private EditText tbName, tbEmail;

    private int increments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbRef = FirebaseDatabase.getInstance().getReference();
        btnFirebase = (Button) findViewById(R.id.btnSubmit);

        tbName = (EditText) findViewById(R.id.tbName);
        tbEmail = (EditText) findViewById(R.id.tbEmail);

        //store data
        btnFirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 1. create child
                // 2. write data to child
                String name = tbName.getText().toString().trim();
                String email = tbEmail.getText().toString().trim();

                HashMap<String, String> dataMap = new HashMap<String, String>();
                dataMap.put("Name", name);
                dataMap.put("Email", email);

                dbRef.child("UserId" + increments).setValue(dataMap);
                increments++;
            }
        });
    }
}

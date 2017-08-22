package com.preangerstd.learnfirebase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {

    private DatabaseReference dbRef;
    private ListView dgv;

    private ArrayList<String> dataName = new ArrayList<>();
    private ArrayList<String> dataKey = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        dbRef = FirebaseDatabase.getInstance().getReference();
        dgv = (ListView) findViewById(R.id.lvMain);

        final ArrayAdapter<String> arrayAdapter  = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataName);
        dgv.setAdapter(arrayAdapter);

        dbRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value = dataSnapshot.getValue(String.class);
                dataName.add(value);

                String key = dataSnapshot.getKey();
                dataKey.add(key);

                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                String value = dataSnapshot.getValue(String.class);
                String key = dataSnapshot.getKey();

                int index = dataKey.indexOf(key);

                dataName.set(index, value);

                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}

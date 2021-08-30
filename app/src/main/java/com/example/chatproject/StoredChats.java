package com.example.chatproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import com.example.chatproject.DatabaseLocal.DatabaseL;
import com.example.chatproject.Model.DatabaseHelper;

public class StoredChats extends AppCompatActivity {
    Button deleteBtn,viewAll;
    ListView listView;

    FirebaseUser firebaseUser;
    DatabaseL databaseL;
    List<DatabaseHelper> allmessages;
    ArrayAdapter MessageArrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stored_chats);
        deleteBtn = findViewById(R.id.delete_btn);
        listView = findViewById(R.id.StoredChats);
        viewAll = findViewById(R.id.viewAll);
        databaseL = new DatabaseL(StoredChats.this);
        Showall();

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StoredChats.this, "Delete soon coming", Toast.LENGTH_SHORT).show();
            }
        });
        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseL = new DatabaseL(StoredChats.this);
                Showall();
                // Toast.makeText(Stored_Chats.this, allmessages.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DatabaseHelper clickedmessage = (DatabaseHelper) parent.getItemAtPosition(position);
                databaseL.deleteOne(clickedmessage);
                Showall();

            }
        });

    }

    public void Showall() {
        MessageArrayAdapter = new ArrayAdapter<DatabaseHelper>(StoredChats.this, android.R.layout.simple_expandable_list_item_1, databaseL.getAll());
        listView.setAdapter(MessageArrayAdapter);
    }

    //Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(StoredChats.this,LoginActivity.class));
                finish();
                return true;

            case R.id.mainactivity:
                startActivity(new Intent(StoredChats.this, MainActivity.class));
                finish();
                return true;


        }
        return false;
    }

}

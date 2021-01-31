package com.example.loginsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.loginsignup.Model.Data;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Hirer extends AppCompatActivity {
    private android.widget.Toolbar toolbar;
    private Toolbar supportActionBar;
    int i;

    private EditText task_title;
    private EditText task_description;
    private EditText task_budget;
    private String userName;
    private RadioButton online;
    private RadioButton Physical;
    private AutoCompleteTextView task_category;
    private EditText location;
    private Button task_posting_button ;
    private DatabaseReference mTaskPost;
    private DatabaseReference loginUsers;
    private FirebaseAuth mAuth;
    private Spinner spinner;
    private SharedPreferences sharedPref;


    Data data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hirer);
        mTaskPost = FirebaseDatabase.getInstance().getReference().child("Task Post");

        loginUsers = FirebaseDatabase.getInstance().getReference().child("users");
        mAuth = FirebaseAuth.getInstance();


        loginUsers.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userName = (String) dataSnapshot.child("userName").getValue();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        task_title = findViewById(R.id.task_title);
        task_description = findViewById(R.id.task_description);
        task_budget = findViewById(R.id.task_budget);
        online = findViewById(R.id.online);
        location = findViewById(R.id.location);
        task_posting_button = findViewById(R.id.task_post_btn);
        task_category = findViewById(R.id.task_category);
        Physical = findViewById(R.id.Physical);


        String[] categories = {"Home Cleaner", "Nanny", "Baker", "Cook", "Maid", "Fllor technician", "Pet Sitter", "Plumber"
                , "Waiter", "General Cashier", "Chef", "Housekeeper", "Kitchen Technician", "Carpenter", "Driver", "Brickmason"
                , "Cement Mason", "Block mason", "Concrete Finisher", "Drywall and Ceiling Tile Installer", "Drywall and Ceiling Tile Installer"
                , "Tile and Marble Setter", "Pipe Fitter", "Pipe Fitter", "Maintenance and Repair Worker", "Carpet Installer"
                , "Electric Motor, Power Tool, and Related Repairer", "Electrician", "Electrical Power-Line Installer and Repairer"
                , "Welder", "Refrigeration Mechanic and Installer", "Refrigeration Mechanic and Installer", "Motorcycle Mechanic"
                , "Mechanical Engineering Technician", "Gardener"};

        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(Hirer.this, android.R.layout.simple_list_item_1, categories);


        task_category.setAdapter(adapter);
        task_posting_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = task_title.getText().toString().trim();
                String description = task_description.getText().toString().trim();
                String budget = task_budget.getText().toString().trim();
                String category = task_category.getText().toString().trim();
                String locality = location.getText().toString().trim();
                String online1 = online.getText().toString().trim();
                String physical1 = Physical.getText().toString().trim();

                if (TextUtils.isEmpty(title)) {
                    task_title.setError("Required Field");

                    return;

                }
                if (TextUtils.isEmpty(physical1)) {
                    Physical.setError("Required Field");
                }
                if (TextUtils.isEmpty(description)) {
                    task_description.setError("Required Field");
                    return;
                }
                if (TextUtils.isEmpty((CharSequence) locality)) {
                    location.setError("Required Field");
                    return;
                }
                if (TextUtils.isEmpty(budget)) {
                    task_budget.setError("Required Field");
                    return;
                }
                if (TextUtils.isEmpty(category)) {
                    task_category.setError("Required Field");
                    return;
                }


                Data data = new Data(title, description, budget, category, locality,userName);
                Data data1 = new Data();

                if (online.isChecked()) {
                    data.setWorkType(online1);
                    mTaskPost.child(String.valueOf(i + 1)).setValue(data);
                    data.setUserName(userName);

                    i++;


                    Toast.makeText(Hirer.this, "Task Posted", Toast.LENGTH_SHORT).show();
                } else {
                    data.setWorkType(physical1);
                    mTaskPost.child(String.valueOf(i + 1)).setValue(data);
                    i++;
                    Toast.makeText(Hirer.this, "Task Posted", Toast.LENGTH_SHORT).show();
                }

            }
        });

task_posting_button = findViewById(R.id.task_post_btn);
task_posting_button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        openSearch();
    }
});
    }

    private void openSearch() {
        Intent intent = new Intent(this, Search.class);
        startActivity(intent);
    }
}
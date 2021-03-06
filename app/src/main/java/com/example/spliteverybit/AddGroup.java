package com.example.spliteverybit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddGroup extends AppCompatActivity {
private EditText group_name;
private DatabaseReference d1,ref;
String name1;
FirebaseUser user;
ArrayList<Group_information> x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);
        Button add=(Button)findViewById(R.id.add);

        group_name=(EditText)findViewById(R.id.groupname);
        user= FirebaseAuth.getInstance().getCurrentUser();
        String id=user.getUid();
        ref = FirebaseDatabase.getInstance().getReference().child("Users").child(id);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                name1 = dataSnapshot.child("name").getValue().toString();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1=group_name.getText().toString();
                d1= FirebaseDatabase.getInstance().getReference("Groups");
                Group_information g=new Group_information(name1);
                x=new ArrayList<Group_information>();
                x.add(g);
                d1.child(s1).setValue(x);
                Toast.makeText(AddGroup.this, "Group Added",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
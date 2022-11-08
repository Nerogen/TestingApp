package com.app.testingapp;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.jovanovic.stefan.kanbanBoard.R;

public class AddActivity extends AppCompatActivity {

    EditText title_input, progress_input, members_input;
    ImageButton add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        title_input = findViewById(R.id.title_input);
        progress_input = findViewById(R.id.progress_input);
        members_input = findViewById(R.id.members_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addBook(title_input.getText().toString().trim(),
                        progress_input.getText().toString().trim(),
                        members_input.getText().toString().trim());
            }
        });
    }
}

package com.example.agecalculator;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Context mainContext = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //Get references to all UI elements
        EditText firstnameEditText = (EditText) findViewById(R.id.firstname);
        EditText lastnameEditText = (EditText) findViewById(R.id.lastname);
        EditText birthdayEditText = (EditText) findViewById(R.id.birthday);
        Button calculateButton = (Button) findViewById(R.id.calculate_age_button);
        //formatter for birthday
        SimpleDateFormat birthdayFormat = new SimpleDateFormat("MM-dd-yyyy");

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get the entry values
                String firstName = firstnameEditText.getText().toString();
                String lastName = lastnameEditText.getText().toString();
                String birthdayString = birthdayEditText.getText().toString();
                if(firstName.isEmpty() || lastName.isEmpty() || birthdayString.isEmpty()){
                    Toast toast = Toast.makeText(mainContext, "All fields need to be filled out.",Toast.LENGTH_SHORT);
                    toast.show();
                }
                try {
                    Date birthday = birthdayFormat.parse(birthdayString);
                    Date today = Calendar.getInstance().getTime();
                    long dateDifference = today.getTime() - birthday.getTime();
                    //date.getTime gives the number of milliseconds, so we want to convert this to years
                    long years = dateDifference/(1000L*86400*365);
                    Toast toast = Toast.makeText(mainContext, "You are " + years+ " years old",Toast.LENGTH_SHORT);
                    toast.show();
                } catch (ParseException e) {
                    Toast toast = Toast.makeText(mainContext, "Invalid Date: format needs to be mm-dd-yyyy",Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }
}
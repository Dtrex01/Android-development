package com.example.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;


import com.google.android.material.picker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputLayout;
import java.util.regex.Pattern;

import java.util.Calendar;
import java.util.TimeZone;

import database.AppDatabase;
import entities.Person;
import utility.AppExecutors;

public class MainActivity extends AppCompatActivity {
    private EditText name, email, pincode,city, phoneNumber;
    private AppDatabase appDatabase;

    private Button mDatePickerBtn;
    private TextInputLayout textInputLayout;
    private TextInputLayout textInputLayout2;
    EditText etDate;
    DatePickerDialog.OnDateSetListener setListener;


    private AutoCompleteTextView dropDownText;
    private String tag;
    private final String Log = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etDate = findViewById(R.id.et_date);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        initViews();
        initializeDatabase();







etDate.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                 MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                etDate.setText(date);

            }
        },year,month,day);
        datePickerDialog.show();

    }
});






        textInputLayout = findViewById(R.id.text_input_layout);
        dropDownText = findViewById(R.id.dropdown_text);

        String[] items = new String[] {
                "Kent",
                "London",
                "Oxford",
                "Others"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                MainActivity.this,
                R.layout.support_simple_spinner_dropdown_item,
                items
        );

        dropDownText.setAdapter(adapter);
    }


Thread thread1 = new Thread(){
        @Override
    public void run(){
            {
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();


                }
            }
            }

};


public void initViews(){
        name = findViewById(R.id.edit_name);
        email = findViewById(R.id.edit_email);
        pincode = findViewById(R.id.edit_pincode);
        city = findViewById(R.id.edit_city);
        phoneNumber = findViewById(R.id.edit_number);
        }


public void initializeDatabase(){
        appDatabase = AppDatabase.getInstance(getApplicationContext());
        }


public void savePerson(View view){
final Person person= new Person(
        name.getText().toString(),
        email.getText().toString(),
        pincode.getText().toString(),
        city.getText().toString(),
        phoneNumber.getText().toString()
        );

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
@Override
public void run() {
        appDatabase.personDao().insertPerson(person);
        }
        });
        nextScreenOnDataSuccess();
        }

public void nextScreenOnDataSuccess(){
        Intent intent=new Intent(MainActivity.this, com.example.ui.ListPerson.class);
        startActivity(intent);
        }

    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    public void onRestart() {
        super.onRestart();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    @Override
    protected void onStop() {
        super.onStop();



    }
    private boolean validateEmail() {
        String emailInput = email.getEditableText().toString().trim();

        if (emailInput.isEmpty()) {
            email.setError("please check email");
            return false;

        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            email.setError("please enter a correct email address");
            return false;
        }else{
        } {
            email.setError(null);
            return true;
        }
    }}
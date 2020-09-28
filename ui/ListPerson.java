package com.example.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.ui.R;
import database.AppDatabase;
import entities.Person;
import utility.AppExecutors;

import java.util.ArrayList;
import java.util.List;

import database.AppDatabase;
import utility.AppExecutors;

public class ListPerson extends AppCompatActivity {
    private AppDatabase appDatabase;
    private List<Person> personList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_person);
        appDatabase= AppDatabase.getInstance(getApplicationContext());
        retrievePersonTable();
    }

    public void retrievePersonTable(){
        AppExecutors.getInstance().diskIO().execute(new Runnable() {

            @Override
            public void run() {
                personList = appDatabase.personDao().loadAllPersons();
                for(int i=0; i< personList.size(); i++){
                    Log.i("TABLE","Person Table values" + personList.get(i).getName());
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Listview
                        // recyclerview
                    }
                });

            }
        });
    }
}
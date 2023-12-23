package com.bankthanapat.dtcexaminationjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class DataActivity extends AppCompatActivity {

    DBHelper mHelper;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        listView = (ListView) findViewById(R.id.animalList);

        mHelper = new DBHelper(this);
        List<String> animalList = mHelper.getAnimalList();
        String[] animals = animalList.toArray(new String[animalList.size()]);
        Arrays.sort(animals);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, animals);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                String selectedAnimal = animals[index];
//                Toast.makeText(DataActivity.this, "คุณคลิกที่: " + selectedAnimal, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DataActivity.this, MainActivity.class);
                intent.putExtra("user",selectedAnimal);
                startActivity(intent);
            }
        });
    }
}
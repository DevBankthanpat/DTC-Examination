package com.bankthanapat.dtcexaminationjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    Button btn_submit ;
    EditText input_username, input_password ;
    Switch sw_autologin ;

    String defaultUser = "";
    DBHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_submit = (Button)findViewById(R.id.button_login);
        input_username = (EditText)findViewById(R.id.input_username);
        input_password = (EditText)findViewById(R.id.input_password);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            defaultUser = bundle.getString("user","");
            input_username.setText(defaultUser);
        }

        mHelper = new DBHelper(this);
        if (!mHelper.isDataExists()){
            String[] list = {"Ant" , "Dog" , "Cat" , "Bee" , "Pig" , "Wolf" , "Fish" , "Fox" , "Worm" , "Zebra" , "Lion"};
            for (String item : list) {
                mHelper.addAnimal(item);
            }
        }

        btn_submit.setOnClickListener(onButtonSubmitClick);
    }

    private View.OnClickListener onButtonSubmitClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String username = input_username.getText().toString();
            String password = input_password.getText().toString();

            if (input_username.getText().toString().equals("")){
                Toast.makeText(MainActivity.this, "กรุณกรอก ชื่อผู้ใช้", Toast.LENGTH_SHORT).show();
            }else if (input_password.getText().toString().equals("")){
                Toast.makeText(MainActivity.this, "กรุณกรอก รหัสผ่าน", Toast.LENGTH_SHORT).show();
            }else if ("DTCGPS".equals(username) && "test".equals(password)) {
                try {
                    Intent intent = new Intent(MainActivity.this, MapActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    // ล็อกข้อผิดพลาดเมื่อมีข้อผิดพลาดในบล็อก try
                    Log.e("MainActivity", "เกิดข้อผิดพลาดในบล็อก try", e);
                }
            } else {
                Toast.makeText(MainActivity.this, "ชื่อผู้ใช้ หรือ รหัสผ่าน ผิด", Toast.LENGTH_SHORT).show();
            }
        }
    };
}
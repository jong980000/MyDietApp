package com.course.test3;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button imageButton = (Button) findViewById(R.id.button3);
        Button imageButton1 = (Button) findViewById(R.id.button2);
        Button imageButton2 = (Button) findViewById(R.id.button);

        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), google.class);
                startActivity(intent);
            }
        });

        imageButton1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PrintFood.class);
                startActivity(intent);
            }
        });
         // 송종혁 추가부분 식사 분석 페이지 넘기기 위함
        imageButton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PrintCalorie.class);
                startActivity(intent);
            }
        });

    }
}




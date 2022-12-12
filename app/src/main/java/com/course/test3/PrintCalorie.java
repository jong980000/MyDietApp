package com.course.test3;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class PrintCalorie extends AppCompatActivity {

    ImageView imageView;
    Uri uri;
    int sum_cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_calorie);
        TextView textView = findViewById(R.id.TView12);

        ListView lv_print = (ListView)findViewById(R.id.print1);

        List<String> list = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();

        UserDatabase userDatabase = UserDatabase.getAppDatabase(this);


        List<String> calories_list = userDatabase.userDao().getCalorieAll();


        String[] calories_array = calories_list.toArray(new String[calories_list.size()]);

        for(int i=0; i<calories_array.length; i++){
            list.add(calories_array[i]);
            list1.add(Integer.parseInt(calories_array[i]));
        }
        for(int num: list1) {
            sum_cal += num;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        lv_print.setAdapter(adapter);

        textView.setText(String.format("총 칼로리 양은 %d kcal 입니다",sum_cal));



    }
}
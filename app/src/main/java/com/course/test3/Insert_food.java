package com.course.test3;


import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import org.jetbrains.annotations.Nullable;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Insert_food extends AppCompatActivity {
//송종혁 추가부분
    ImageView imageView;
    Button button;
    String TAG = "Insert_food";
    HashMap<String, String> dietDataToInsert;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_food);
        dietDataToInsert = new HashMap<String, String>();

//송종혁 추가부분
        imageView = findViewById(R.id.imageView2);
        button = findViewById(R.id.image_btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });

        EditText et_age = (EditText) findViewById(R.id.age);
        EditText et_name = (EditText) findViewById(R.id.name);
        EditText et_phone = (EditText) findViewById(R.id.phone);
        EditText et_place = (EditText) findViewById(R.id.place);
        EditText et_calorie = (EditText) findViewById(R.id.calories);
        EditText et_date = (EditText) findViewById(R.id.date1);


        Button btn_1 = (Button) findViewById(R.id.btn);
        ListView lv_print = (ListView) findViewById(R.id.lv_songs);
        Button image = (Button) findViewById(R.id.image_btn);

        List<String> list = new ArrayList<>();

        UserDatabase userDatabase = UserDatabase.getAppDatabase(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        lv_print.setAdapter(adapter);

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et_name.getText().toString();
                String age = et_age.getText().toString();
                String phone = et_phone.getText().toString();
                String place = et_place.getText().toString();
                String calorie = et_calorie.getText().toString();
                String date = et_date.getText().toString();

                if (name.equals(""))
                    Toast.makeText(getApplicationContext(), "이름을 입력하세요", Toast.LENGTH_SHORT);
                if (age.equals(""))
                    Toast.makeText(getApplicationContext(), "소감을 입력하세요", Toast.LENGTH_SHORT);
                if (phone.equals(""))
                    Toast.makeText(getApplicationContext(), "식사시간을 입력하세요", Toast.LENGTH_SHORT);
                if (place.equals(""))
                    Toast.makeText(getApplicationContext(), "장소를 입력하세요", Toast.LENGTH_SHORT);
                if (calorie.equals(""))
                    Toast.makeText(getApplicationContext(), "칼로리를 입력하세요", Toast.LENGTH_SHORT);
                if (date.equals(""))
                    Toast.makeText(getApplicationContext(), "날짜를 입력하세요", Toast.LENGTH_SHORT);


                //데이터 추가
                if (!name.equals("") && !age.equals("") && !phone.equals("") && !place.equals("") && !calorie.equals("")
                        && !date.equals("")) {
                    User new_user = new User();

                    new_user.name = name;
                    new_user.age = age;
                    new_user.phoneNumber = phone;
                    new_user.place = place;
                    new_user.calorie = calorie;
                    new_user.date = date;

                    userDatabase.userDao().insertAll(new_user);

                    adapter.notifyDataSetChanged();
                }
            }
        });

    }

    public String getPathFromURI(Uri contentUri) {
        Cursor mediaCursor = null;
        try {
            String[] dataPath = { MediaStore.Images.Media.DATA };
            mediaCursor = getContentResolver().query(contentUri,  dataPath, null, null, null);
            int column_index = mediaCursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            mediaCursor.moveToFirst();
            return mediaCursor.getString(column_index);
        } finally {
            if (mediaCursor != null) {
                mediaCursor.close();
            }
        }
    }

    public String getRealPathFromURI(Uri contentUri)
    {
        String [] project = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(contentUri,project,null,null,null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    //송종혁 추가부분
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    imageView.setImageURI(uri);
                }
                break;
        }
    }
}

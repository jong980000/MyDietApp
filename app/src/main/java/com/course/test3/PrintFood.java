package com.course.test3;


import android.Manifest;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import org.jetbrains.annotations.Nullable;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PrintFood extends AppCompatActivity
{
    ImageView imageView;
    Button button;

    /*protected void setImageURI(Uri uri) {
        try {
            InputStream in = getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(in);
            imageView.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getRealPathFromURI(Uri contentUri)
    {
        String [] project = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(contentUri,project,null,null,null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        button = findViewById(R.id.image_btn12);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });

        imageView = (ImageView) findViewById(R.id.image_View);
        ListView lv_print = (ListView) findViewById(R.id.print);
        Intent intent = getIntent();
        List<String> list = new ArrayList<>();

        UserDatabase userDatabase = UserDatabase.getAppDatabase(this);

        List<String> name_list = userDatabase.userDao().getNameAll();
        List<String> age_list = userDatabase.userDao().getAgeAll();
        List<String> phone_list = userDatabase.userDao().getPhoneAll();
        List<String> place_list = userDatabase.userDao().getPlaceAll();
        List<String> calories_list = userDatabase.userDao().getCalorieAll();
        List<String> date_list = userDatabase.userDao().getDateAll();


        String[] name_array = name_list.toArray(new String[name_list.size()]);
        String[] age_array = age_list.toArray(new String[age_list.size()]);
        String[] phone_array = phone_list.toArray(new String[phone_list.size()]);
        String[] place_array = place_list.toArray(new String[place_list.size()]);
        String[] calories_array = calories_list.toArray(new String[calories_list.size()]);
        String[] date_array = date_list.toArray(new String[calories_list.size()]);
        String image = intent.getStringExtra("image");

        for (int i = 0; i < name_array.length; i++) {
            list.add(date_array[i] + " - " + name_array[i] + " - " + age_array[i] + " - " + phone_array[i] + " - " + place_array[i] + " - " + calories_array[i]);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        lv_print.setAdapter(adapter);

        //setImageURI(Uri.parse(image));
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



package com.wudiandy.contentproviderdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 取得我们在后面要用到的前端对象
        EditText nameEditText = (EditText)findViewById(R.id.et_name);
        EditText ageEditText = (EditText)findViewById(R.id.et_age);
        EditText schoolNameEditText = (EditText)findViewById(R.id.et_school_name);
        Button saveButton = (Button)findViewById(R.id.bt_save);
    }
}

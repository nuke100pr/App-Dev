package com.example.buddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button button2;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button2 = findViewById(R.id.button2);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewActiviy2();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              openNewActiviy();
            }
        });
    }
    public void openNewActiviy2()
    {
        Intent intent = new Intent(this , tabs.class);
        startActivity(intent);
    }
    public void openNewActiviy()
    {
        Intent intent = new Intent(this , signup.class);
        startActivity(intent);
    }
}
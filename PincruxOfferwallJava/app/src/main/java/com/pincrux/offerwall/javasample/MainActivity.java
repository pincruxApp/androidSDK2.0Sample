package com.pincrux.offerwall.javasample;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

public class MainActivity extends AppCompatActivity {

    private AppCompatEditText edit;
    private AppCompatButton next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findId();
        clickAction();
    }

    private void findId() {
        edit = findViewById(R.id.edit);
        next = findViewById(R.id.next);

        edit.setText(Preference.getInstance().getPubKey(this));
    }

    private void clickAction() {
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(edit.getText())) {
                    Preference.getInstance().setPubKey(MainActivity.this, edit.getText().toString());

                    Intent intent = new Intent(MainActivity.this, OptionActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this,R.string.pub_toast,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
package com.augmentis.ayp.keepwalking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class KeepWalkingActivity extends AppCompatActivity {

    public Button saveBtn;
    public EditText title;

    protected static final String TAG = "KEEP_WALKING_ADD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_keep_walking);

        title = (EditText) findViewById(R.id.title);

        saveBtn = (Button) findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                KeepWalkingLab kpl =  KeepWalkingLab.getInstance(getParent());
                kpl.addKeepWalking(title.getText().toString());

                Log.d(TAG,"add already : " + kpl.getKeepWalking().get(0).toString());

                Intent intent = new Intent(KeepWalkingActivity.this,KeepWalkingListActivity.class);
                startActivity(intent);
            }
        });

    }
}

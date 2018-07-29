package com.example.schedulermobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

public class TaskInfo extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taskinfo_layout);

        TextView task = (TextView) findViewById(R.id.textView7);
        TextView description = (TextView) findViewById(R.id.textView11);
        TextView date = (TextView) findViewById(R.id.textView12);
        TextView time = (TextView) findViewById(R.id.textView13);

        Intent intent = getIntent();
        String[] data = intent.getStringExtra("date").split("/");

        task.setText(intent.getStringExtra("task"));
        description.setText(getIntent().getStringExtra("description"));
        date.setText(data[0]);
        time.setText(data[1]);
    }
}

package com.example.schedulermobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

public class ListItem extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listitem_layout);
    }

    public void goToTask(View view) {
        Intent intent = new Intent(this, TaskInfo.class);

        TextView task = (TextView) findViewById(R.id.textView3);
        TextView description = (TextView) findViewById(R.id.textView4);
        TextView date = (TextView) findViewById(R.id.textView5);
        TextView time = (TextView) findViewById(R.id.textView6);

        intent.putExtra("task", task.getText().toString());
        intent.putExtra("description", description.getText().toString());
        intent.putExtra("date", date.getText().toString());
        intent.putExtra("time", time.getText().toString());

        startActivity(intent);
    }


}

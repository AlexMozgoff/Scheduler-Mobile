package com.example.schedulermobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

public class StartActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_layout);
    }

    public void goToActiveTasks(View view) {
        Intent intent = new Intent(this, ActiveTasksActivity.class);
        startActivity(intent);
    }

    public void goToFinishedTasks(View view) {
        Intent intent = new Intent(this, FinishedTasksActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        System.exit(0);
    }

}

package com.example.schedulermobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.main_layout);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public void goToActiveTasks(View view) {
        Intent intent = new Intent(this, ActiveTasksActivity.class);
        startActivity(intent);
    }

    public void goToFinishedTasks(View view) {
        Intent intent = new Intent(this, FinishedTasksActivity.class);
        startActivity(intent);
    }
}

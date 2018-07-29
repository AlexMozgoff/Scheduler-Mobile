package com.example.schedulermobile;

import BLL.Task;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;

import java.io.*;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewTaskActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newactivity_layout);
    }

    public void createTask(View view) throws IOException {
        EditText edit = findViewById(R.id.editText);
        EditText edit1 = findViewById(R.id.editText2);

        String task = ((TextInputLayout) findViewById(R.id.textInputLayout)).getEditText().getText().toString();//получение значений с активити
        String des = ((TextInputLayout) findViewById(R.id.textInputLayout2)).getEditText().getText().toString();
        String date = getDate(edit, edit1);

        Task newTask = new Task(task, des, date);
        saveTask(newTask);                                                                                      //запись в файл
        System.out.println(newTask.getTask() + ", " + newTask.getDescription() + ", " + newTask.getDeadline() + ";");
        goToActiveTasks();                                                                                      //переход к активным задачам
    }

    private String getDate(EditText date1, EditText time) {
        Date data;
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy/hh:mm");
        String date = date1.getText().toString() + "/";
        date += time.getText().toString();
        try {
            data = format.parse(date);
            return format.format(data);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void saveTask(Task task) throws IOException {
        FileOutputStream fos = openFileOutput("tasks3.txt", MODE_APPEND);
        OutputStreamWriter os = new OutputStreamWriter(fos);
        os.write(task.getTask() + ", " + task.getDescription() + ", " + task.getDeadline() + ";");
        os.flush();
        os.close();
        fos.close();
    }

    private void goToActiveTasks() {
        Intent intent = new Intent(this, ActiveTasksActivity.class);
        startActivity(intent);
    }

   /* private Time getTime(EditText editText) {
        Time time;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
        String time1 = editText.getText().toString();
        try {
            //time = simpleDateFormat.parse(time1);
        }
        catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }*/
}

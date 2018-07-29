package com.example.schedulermobile;

import BLL.AdapterTask;
import BLL.Task;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import org.w3c.dom.Text;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ActiveTasksActivity extends Activity {

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activetasks_layout);

        final Context context = getApplicationContext();
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Task task = (Task) adapterView.getAdapter().getItem(i);
                goToTask(view, task);
            }
        });
        List<Task> tasks = initData();
        if (tasks != null) {
            AdapterTask adapterTask = new AdapterTask(this, tasks);
            listView.setAdapter(adapterTask);
        }
    }

    public void goToTask(View view, Task task) {
        Intent intent = new Intent(getApplicationContext(), TaskInfo.class);

        intent.putExtra("task", task.getTask());
        intent.putExtra("description", task.getDescription());
        intent.putExtra("date", task.getDeadline());

        startActivity(intent);
    }

    public void createNewTask(View view) {
        Intent intent = new Intent(this, NewTaskActivity.class);
        startActivity(intent);
    }

    private List<Task> initData() {
        File file = getApplicationContext().getFileStreamPath("tasks3.txt");
        List<Task> tasksList = new ArrayList<>();
        char[] temporary = new char[1024];
        String tempString;
        String tasks[] = null;
        if (file.exists()) {
            try {
                FileInputStream fileInputStream = openFileInput("tasks3.txt");
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                try {
                    inputStreamReader.read(temporary);
                    tempString = String.valueOf(temporary);
                    tempString = tempString.replace("\u0000", "");
                    tasks = tempString.split(";");
                    inputStreamReader.close();
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < tasks.length; i++) {
                String[] taskString;
                taskString = tasks[i].split(", ");
                Task task = new Task(taskString[0], taskString[1], taskString[2]);
                tasksList.add(task);
                System.out.println(task.getTask() + "===" + task.getDescription() + "===" + task.getDeadline());
            }
            sortByYear(tasksList);
            sortByMonth(tasksList);
            sortByDay(tasksList);
            sortByHour(tasksList);
            sortByMinutes(tasksList);
            return tasksList;
        } else {
            System.out.println("Task File was not found!");
            return null;
        }
    }

    private List<Task> sortByYear(List<Task> tasks) {
        String[] date1 = new String[2];
        String[] date2 = new String[2];
        String[] dmy1 = new String[3];
        String[] dmy2 = new String[3];
        for (int i = 0; i < tasks.size(); i++) {
            for (int j = 0; j < tasks.size(); j++) {
                date1 = tasks.get(i).getDeadline().split("/");
                date2 = tasks.get(j).getDeadline().split("/");

                dmy1 = date1[0].split("\\.");
                dmy2 = date2[0].split("\\.");

                if (Integer.valueOf(dmy1[2]) < Integer.valueOf(dmy2[2])) {
                    Task temp = tasks.get(i);
                    tasks.set(i, tasks.get(j));
                    tasks.set(j, temp);
                    System.out.println("Changed!");
                }
            }
        }
        return tasks;
    }

    private List<Task> sortByMonth(List<Task> tasks) {
        String[] date1 = new String[2];
        String[] date2 = new String[2];
        String[] dmy1 = new String[3];
        String[] dmy2 = new String[3];
        for (int i = 0; i < tasks.size(); i++) {
            for (int j = 0; j < tasks.size(); j++) {
                date1 = tasks.get(i).getDeadline().split("/");
                date2 = tasks.get(j).getDeadline().split("/");

                dmy1 = date1[0].split("\\.");
                dmy2 = date2[0].split("\\.");

                if (Integer.valueOf(dmy1[2]).equals(Integer.valueOf(dmy2[2]))) {
                    if (Integer.valueOf(dmy1[1]) < Integer.valueOf(dmy2[1])) {
                        Task temp = tasks.get(i);
                        tasks.set(i, tasks.get(j));
                        tasks.set(j, temp);
                        System.out.println("Changed by Month!");
                    }
                }
            }
        }
        return tasks;
    }

    private List<Task> sortByDay(List<Task> tasks) {
        String[] date1 = new String[2];
        String[] date2 = new String[2];
        String[] dmy1 = new String[3];
        String[] dmy2 = new String[3];
        for (int i = 0; i < tasks.size(); i++) {
            for (int j = 0; j < tasks.size(); j++) {
                date1 = tasks.get(i).getDeadline().split("/");
                date2 = tasks.get(j).getDeadline().split("/");

                dmy1 = date1[0].split("\\.");
                dmy2 = date2[0].split("\\.");

                if (Integer.valueOf(dmy1[2]).equals(Integer.valueOf(dmy2[2]))) {
                    if (Integer.valueOf(dmy1[1]).equals(Integer.valueOf(dmy2[1]))) {
                        if (Integer.valueOf(dmy1[0]) < Integer.valueOf(dmy2[0])) {
                            Task temp = tasks.get(i);
                            tasks.set(i, tasks.get(j));
                            tasks.set(j, temp);
                            System.out.println("Changed by day!");
                        }
                    }
                }
            }
        }
        return tasks;
    }

    private List<Task> sortByHour(List<Task> tasks) {
        String[] date1 = new String[2];
        String[] date2 = new String[2];
        String[] dmy1 = new String[3];
        String[] dmy2 = new String[3];
        String[] time1 = new String[2];
        String[] time2 = new String[2];
        for (int i = 0; i < tasks.size(); i++) {
            for (int j = 0; j < tasks.size(); j++) {
                date1 = tasks.get(i).getDeadline().split("/");
                date2 = tasks.get(j).getDeadline().split("/");

                dmy1 = date1[0].split("\\.");
                dmy2 = date2[0].split("\\.");

                time1 = date1[1].split(":");
                time2 = date2[1].split(":");

                if (Integer.valueOf(dmy1[2]).equals(Integer.valueOf(dmy2[2]))) {
                    if (Integer.valueOf(dmy1[1]).equals(Integer.valueOf(dmy2[1]))) {
                        if (Integer.valueOf(dmy1[0]).equals(Integer.valueOf(dmy2[0]))) {
                            if (Integer.valueOf(time1[0]) < Integer.valueOf(time2[0])) {
                                Task temp = tasks.get(i);
                                tasks.set(i, tasks.get(j));
                                tasks.set(j, temp);
                                System.out.println("Changed by day!");
                            }
                        }
                    }
                }
            }
        }
        return tasks;
    }

    private List<Task> sortByMinutes(List<Task> tasks) {
        String[] date1 = new String[2];
        String[] date2 = new String[2];
        String[] dmy1 = new String[3];
        String[] dmy2 = new String[3];
        String[] time1 = new String[2];
        String[] time2 = new String[2];
        for (int i = 0; i < tasks.size(); i++) {
            for (int j = 0; j < tasks.size(); j++) {
                date1 = tasks.get(i).getDeadline().split("/");
                date2 = tasks.get(j).getDeadline().split("/");

                dmy1 = date1[0].split("\\.");
                dmy2 = date2[0].split("\\.");

                time1 = date1[1].split(":");
                time2 = date2[1].split(":");

                if (Integer.valueOf(dmy1[2]).equals(Integer.valueOf(dmy2[2]))) {
                    if (Integer.valueOf(dmy1[1]).equals(Integer.valueOf(dmy2[1]))) {
                        if (Integer.valueOf(dmy1[0]).equals(Integer.valueOf(dmy2[0]))) {
                            if (Integer.valueOf(time1[0]).equals(Integer.valueOf(time2[0]))) {
                                if (Integer.valueOf(time1[1]) < Integer.valueOf(time2[1])) {
                                    Task temp = tasks.get(i);
                                    tasks.set(i, tasks.get(j));
                                    tasks.set(j, temp);
                                    System.out.println("Changed by day!");
                                }
                            }
                        }
                    }
                }
            }
        }
        return tasks;
    }
}



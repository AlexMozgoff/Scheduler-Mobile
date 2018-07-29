package BLL;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

public class Task {

    private String task;
    private String description;
    private String deadline;

    public Task(String task, String description, String deadline) {
        this.task = task;
        this.description = description;
        this.deadline = deadline;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getFullPath() {
        return this.getClass().getClassLoader().getResource("").getPath();
    }
}

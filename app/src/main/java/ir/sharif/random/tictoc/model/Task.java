package ir.sharif.random.tictoc.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Mojtaba on 8/13/2016.
 */
public class Task {
    private long id;
    private String title;
    private Category category;
    private String date;
    private String startTime;
    private String endTime;
    private ArrayList<SubTask> subTasks;
    private int repeatPeriod = 0; // in days

    public Task(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Task setTitle(String title) {
        this.title = title;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public Task setCategory(Category category) {
        this.category = category;
        return this;
    }

    public String getDate() {
        return date;
    }

    public Task setDate(String date) {
        this.date = date;
        return this;
    }

    public String getStartTime() {
        return startTime;
    }

    public Task setStartTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    public String getEndTime() {
        return endTime;
    }

    public Task setEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    public ArrayList<SubTask> getSubTasks() {
        return subTasks;
    }

    public Task setSubTasks(ArrayList<SubTask> subTasks) {
        this.subTasks = subTasks;
        return this;
    }

    public int getRepeatPeriod() {
        return repeatPeriod;
    }

    public Task setRepeatPeriod(int repeatPeriod) {
        this.repeatPeriod = repeatPeriod;
        return this;
    }
}

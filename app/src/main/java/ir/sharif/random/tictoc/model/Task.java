package ir.sharif.random.tictoc.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Mojtaba on 8/13/2016.
 */
public class Task {
    private String title;
    private Category category;
    private Date date;
    private long startTime;
    private long endTime;
    private ArrayList<SubTask> subTasks;
    private long repeatPeriod=0;

    public Task(String title, Date date) {
        this.title = title;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public ArrayList<SubTask> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(ArrayList<SubTask> subTasks) {
        this.subTasks = subTasks;
    }

    public long getRepeatPeriod() {
        return repeatPeriod;
    }

    public void setRepeatPeriod(long repeatPeriod) {
        this.repeatPeriod = repeatPeriod;
    }
}

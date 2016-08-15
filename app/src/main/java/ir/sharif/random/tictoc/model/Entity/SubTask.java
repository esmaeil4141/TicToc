package ir.sharif.random.tictoc.model.entity;

/**
 * Created by Mojtaba on 8/13/2016.
 */
public class SubTask {
    private boolean subTaskDone;
    private String subTaskName;

    public SubTask(String subTaskName) {
        this.subTaskName = subTaskName;
    }

    public boolean isSubTaskDone() {
        return subTaskDone;
    }

    public void setSubTaskDone(boolean subTaskDone) {
        this.subTaskDone = subTaskDone;
    }

    public String getSubTaskName() {
        return subTaskName;
    }

    public void setSubTaskName(String subTaskName) {
        this.subTaskName = subTaskName;
    }
}

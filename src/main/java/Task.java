public class Task {
    protected String listItem;
    protected boolean taskDone;

    public Task(String listItem) {
        this.listItem = listItem;
        this.taskDone = false;
    }

    public boolean isTaskDone() {
        return taskDone;
    }

    public void setTaskDone(boolean taskDone) {
        this.taskDone = taskDone;
    }

    public String getListItem() {
        return listItem;
    }

    public void setListItem(String listItem) {
        this.listItem = listItem;
    }
}

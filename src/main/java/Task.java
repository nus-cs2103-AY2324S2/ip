public class Task {
    protected String listItem;
    protected String inputItem;

    protected String completed;
    protected boolean taskDone;

    public Task(String listItem, String inputItem) {
        this.listItem = listItem;
        this.inputItem = inputItem;
        this.setTaskDone(false);
    }

    public boolean isTaskDone() {
        return taskDone;
    }

    public void setTaskDone(boolean taskDone) {
        this.taskDone = taskDone;
        this.completed = !this.taskDone ? " incomplete" : " complete";
    }

    public String getListItem() {
        return listItem;
    }

    public void setListItem(String listItem) {
        this.listItem = listItem;
    }
}

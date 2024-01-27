package kaiyap;

public class Task {
    protected String listItem;
    protected String inputItem;
    protected String completed;
    protected boolean completedTask;

    public Task(String listItem, String inputItem) {
        this.listItem = listItem;
        this.inputItem = inputItem;
        this.setTaskDone(false);
    }

    public boolean isTaskDone() {
        return completedTask;
    }

    public void setTaskDone(boolean taskDone) {
        this.completedTask = taskDone;
        this.completed = !this.completedTask ? " incomplete" : " complete";
    }

    public String getListItem() {
        return listItem;
    }

    public void setListItem(String listItem) {
        this.listItem = listItem;
    }

    public String getInputItem() {
        return inputItem;
    }

    public void setInputItem(String inputItem) {
        this.inputItem = inputItem;
    }
}

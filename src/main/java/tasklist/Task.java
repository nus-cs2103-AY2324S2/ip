package tasklist;
public class Task {
    private String description;
    private boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public Task(String description ,boolean isDone ) {
        this.description = description;
        this.isDone = isDone;
    }
    public boolean match(String matchString) {
        if (description.contains(matchString)) {
            return true;
        } else {
            return false;
        }
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Toggles whether the task has been done
     * @return String for UI
     */
    public String toggleIsDone() {
        isDone = !isDone;
        return getStatusIcon();
    }

    /**
     * Saves the task in the storage as a String
     * @return string format of task to store
     */
    public String saveStorage() {
        String storageIsDone = "0";
        if (isDone) {
            storageIsDone = "1";
        }
        String details = storageIsDone + "|" + description;
        return details;
    }
    @Override
    public String toString(){
        return "["+getStatusIcon()+"]"+ description;
    }
}

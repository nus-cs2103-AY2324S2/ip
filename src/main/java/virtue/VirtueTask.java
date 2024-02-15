package virtue;

abstract public class VirtueTask {
    private String description;
    private boolean isDone;

    public VirtueTask(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Checks if the task's description contains a keyword or not.
     *
     * @param keyword The keyword to be searched in the description.
     * @return True if the description contains the keyword, and false if not.
     */
    public boolean hasKeyword(String keyword) {
        return description.toLowerCase().contains(keyword.toLowerCase());
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String fileFormat() {
        if (isDone) {
            return "1 | " + description;
        } else {
            return "0 | " + description;
        }
    }
}

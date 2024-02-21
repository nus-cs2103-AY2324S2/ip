package task;

import java.util.ArrayList;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected ArrayList<String> tags;

    /**
     * Constructor for a Task.
     *
     * @param description Description of the task.
     * */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tags = new ArrayList<>();
    }

    /**
     * Constructor for a Task.
     *
     * @param description Description of the task.
     * @param isDone Boolean value to state if the task is done or not.
     * */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.tags = new ArrayList<>();
    }

    /**
     * Gets the status icon of a task.
     *
     * @return A checked string if the task is done else returns an empty [].
     * */
    public String getStatusIcon() {
        return isDone ? "[✔]" : "[ ]";
    }

    abstract public String taskPrinter();

    abstract public String taskPrinter(int index);

    /**
     * Sets the isDone value of task to be true.
     * */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Sets the isDone value of task to be false.
     * */
    public void markAsUndone() {
        this.isDone = false;
    }

    abstract public String storagePrinter();


    /**
     * Checks if the keyword exists in the description.
     *
     * @param keyword The keyword that needs to be searched in the description.
     * @return Boolean value if such a keyword exists in the description.
     * */
    public boolean hasKeyword(String keyword) {

        String lowercaseDescription = description.toLowerCase();
        String lowercaseKeyword = keyword.toLowerCase();

        return lowercaseDescription.contains(lowercaseKeyword);
    }

    /**
     * Checks if a Task is equal to the current instance of Task.
     *
     * @param obj The object that is to be checked for equality with current Task object.
     * @return True if the obj is equal else return False.
     * */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Task task = (Task) obj;

        return isDone == task.isDone && description.equals(task.description);
    }

    /**
     * Adds a tag to the current instance of the Task
     * */
    public void addTag(String tag) {
        if (!tag.isEmpty()) {
            this.tags.add(tag);
        }
    }

    public String printTags() {
        String result = "( ";
        for (String t : this.tags) {
            result += "#" + t + ", ";
        }

        result += " )";
        return result;
    }

    public void untag(int tagIndex) {
        if (tagIndex < this.tags.size()) {
            this.tags.remove(tagIndex);
        }
    }

}

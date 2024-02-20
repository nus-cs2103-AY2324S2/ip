package duke.task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents the Task specified by users.
 */

public abstract class Task {
    private String task;
    private boolean isDone = false;

    public Task(String task) {
        this.task = task;
    }

    /**
     * Marks current task as done by changing the status of isDone to true.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Unmarks current task by changing the status of isDone to false.
     */
    public void setNotDone() {
        isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * Checks if current task has been marked.
     *
     * @return String representation of task marked or unmarked.
     */
    public String marked() {
        if (isDone) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    /**
     * Getter that returns the description of current task.
     *
     * @return String representation of task description.
     */
    public String getTask() {
        return task;
    }

    /**
     * Finds current category of task.
     *
     * @return String representation of current task category.
     */
        public String getCat() {
            if (this instanceof Todo) {
                return "[T]";
            } else if (this instanceof Deadline) {
                return "[D]";
            } else {
                return "[E]";
            }
        }

    /**
     * Returns deadlines/timing of Deadlines and Events.
     * Returns nothing if task is Todo.
     *
     * @return Deadlines/timings of Deadline and Event tasks.
     */
    public String getDetails() {
        if (this instanceof Deadline) {
            return ((Deadline) this).getDeadline();
        } else if (this instanceof Event) {
            return ((Event) this).getEvent();
        }
        return "";
    }

    /**
     * Writes added tasks to File.
     *
     * @param filePath Filepath to File to be written to.
     */
    public void writeToFile(File filePath) throws IOException {
        try {
            FileWriter fw = new FileWriter(filePath.getPath(), true);
            fw.write(this.add() + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("file not found! try again bb");
        }
    }

    /**
     * Returns tasks to be added.
     *
     * @return String representation of added tasks.
     */
    public String add() {
        if (this instanceof Todo) {
            return ((Todo) this).add();
        } else if (this instanceof Deadline) {
            return ((Deadline) this).add();
        } else if (this instanceof Event) {
            return ((Event) this).add();
        }
        return "";
    }

        public boolean isFound(String str) {
            return task.contains(str);
        }


}

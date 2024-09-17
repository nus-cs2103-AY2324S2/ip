package snomtask;

import snomexceptions.InvalidCommandTaskDoneException;
import snomexceptions.InvalidCommandTaskNotDoneException;

/**
 * Task implments all the task that a user
 * can enter.
 */
public abstract class Task {

    private String name;

    private boolean isDone;

    /**
     * Creates an instance of Task.
     * @param name is the name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[X]" + this.name;
        } else {
            return "[ ]" + this.name;
        }

    }

    /**
     * Changes the status of a task from not done to done
     */
    public void doTask() throws InvalidCommandTaskDoneException {
        if (!isDone) {
            this.isDone = true;
        } else {
            throw new InvalidCommandTaskDoneException();
        }
    }

    /**
     * Changes the satus of a task from done to not done
     */
    public void undoTask() throws InvalidCommandTaskNotDoneException {
        if (isDone) {
            this.isDone = false;
        } else {
            throw new InvalidCommandTaskNotDoneException();
        }
    }

    /**
     * Checks whether the cmd matches the task descripion.
     * @param cmd is the command entered by the user.
     * @return a boolean value to see if the task description
     *         matches the user input.
     */
    public boolean isMatch(String cmd) {
        return this.name.contains(cmd);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Task) {
            Task t = (Task) o;
            return this.name.toLowerCase().equals(t.name);
        } else {
            return false;
        }
    }

    /**
     * Converts the String representation of task back into an instance of Task.
     * @param desc is the String body of the task.
     * @return a Task that is of type Todo, Event, or Deadline.
     */
    public static Task convertFromStringToTask(String desc) {
        String taskType = desc.substring(1, 2);
        Task t;
        String name;
        String processedName;
        switch(taskType) {
        case "T":
            t = new Todo(desc.substring(6));
            break;
        case "D":
            name = desc.split("by:")[0].trim();
            processedName = name.substring(6);
            String deadline = desc.split("by:")[1];
            t = new Deadline(processedName, deadline);
            break;
        case "E":
            name = desc.split("from")[0].trim();
            processedName = name.substring(6);
            String dates = desc.split("from")[1].trim();
            System.out.println(dates);
            String start = dates.split("to")[0].trim();
            String end = dates.split("to")[1].trim();
            t = new Event(processedName, start, end);

            //t = new Todo("");
            break;
        default:
            t = new Todo("");
        }
        return t;
    }


}

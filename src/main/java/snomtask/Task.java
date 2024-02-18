package snomtask;

/**
 * Task implments all the task that a user
 * can enter.
 */
public abstract class Task {

    private String name;

    private boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    @Override
    public String toString() {
        if (done) {
            return "[X]" + this.name;
        } else {
            return "[ ]" + this.name;
        }

    }

    /**
     * Changes the status of a task from not done to done
     */
    public void doTask() {
        if (!done) {
            this.done = true;
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(this.toString());
        } else {
            System.out.println("This task is already done");
        }

    }

    /**
     * Changes the satus of a task from done to not done
     */
    public void undoTask() {
        if (done) {
            this.done = false;
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(this.toString());
        } else {
            System.out.println("This task has not been done");

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

    public static Task convertFromStringToTask(String desc) {
        String taskType = desc.substring(1,2);
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

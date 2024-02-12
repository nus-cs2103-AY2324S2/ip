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
     * Changes the satus of a task from not done to done
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
    public boolean match(String cmd) {
        return this.name.contains(cmd);
    }


}

package hirwan;

public class Todo extends Task {
    String input;
    Tasklist tasks;

    /**
     * the todo constructor
     * @param input
     * @param tasks
     */
    public Todo(String input, Tasklist tasks) {
        this.input = input;
        this.tasks = tasks;
    }

    /**
     * the getMessage method that returns the output of the command to be printed to the user
     * @return the output to be printed to the usr
     */
    @Override
    public String getMessage() {
        try {
            String output = "";
            output = "Got it. I've added this task: \n  " + "[T][ ] "
                    + this.input.substring(5) + "\n";
            output = output + "Now you have " + this.tasks.size() + " tasks in the list.";
            return output;
        } catch (StringIndexOutOfBoundsException e) {
            return "Error: Please enter a description for your todo command";
        }
    }

    /**
     * updateData method that updates the data in the external text file when a new todo task is created
     */
    @Override
    public void updateData() {
        this.tasks.add(". " + "[T][ ] " + this.input.substring(5));
        Storage.writeTask(this.tasks.getList());
    }
}

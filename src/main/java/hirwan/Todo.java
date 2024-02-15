package hirwan;

public class Todo extends Task {
    String input;
    Tasklist tasks;
    public Todo(String input, Tasklist tasks) {
        this.input = input;
        this.tasks = tasks;
    }
    @Override
    public String getMessage() {
        String output = "";
        output = "Got it. I've added this task: \n  " + "[T][ ] "
                + this.input.substring(5) + "\n";
        output = output + "Now you have " + this.tasks.size() + " tasks in the list.";
        return output;
    }

    @Override
    public void updateData() {
        this.tasks.add(". " + "[T][ ] " + this.input.substring(5));
        Storage.writeTask(this.tasks.getList());
    }
}

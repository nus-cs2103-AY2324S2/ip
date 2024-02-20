package hirwan;

public class Todo extends Task {
    String input;
    Tasklist tasks;
//    Tasklist previousTasks;

    public Todo(String input, Tasklist tasks) {
        this.input = input;
        this.tasks = tasks;
//        this.previousTasks = previousTasks;
    }

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

    @Override
    public void updateData() {
//        this.previousTasks.deleteList();
//        this.previousTasks = this.tasks.copyList();
        this.tasks.add(". " + "[T][ ] " + this.input.substring(5));
        Storage.writeTask(this.tasks.getList());
    }
}

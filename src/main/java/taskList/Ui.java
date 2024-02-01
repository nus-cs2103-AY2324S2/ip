package taskList;

public class Ui {
    protected boolean isRunning = true;

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public void showWelcomeMessage() {
        System.out.println("Hello! I'm MichelleBot! What can I do for you? (helpg for guide)" );
        System.out.println("____________________________________________________________");

    }

    public void showAddTaskMessage(Task addedTask, int size) {
        if (addedTask != null) {
            System.out.println("Roger that! I've added in this task:\n " + addedTask);
            System.out.println("Now you have "+ size + " tasks in the list.");
        }
    }

    public void showDeletedTaskMessage(Task deletedTask, int size) {
        if (deletedTask != null) {
            System.out.println("Roger that! I've removed this task:\n " + deletedTask  +"\nNow you have "+ size + " tasks in the list.");
        }
    }

    public void showMarkMessage(Task markedTask) {
        System.out.println("I've marked this task as done: \n" + markedTask);
    }

    public void showUnmarkMessage(Task unmarkedTask) {
        System.out.println("I've marked this task as not done yet: \n" + unmarkedTask);
    }
    
    public void showErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void showHelpMessage() {
        System.out.println("Type in text to add in a task to your list\n"+
                        "Other commands:\n" +
                        "add [task] - adds a task to the task list\n" +
                        "mark [input number] - mark a task as done\n" +
                        "unmark [input number] - unmark a task as undone\n" +
                        "todo [task] - add a TODO task to your list\n" +
                        "deadline [task] /by [deadline] - add a DEADLINE to your list\n" + 
                        "event [task] /from [date] /to [date] - add an EVENT to your list\n" + 
                        "delete [input number] - delete a task from task list\n" +
                        "list - list out the current tasks you have\n" +
                        "bye - exit the program\n" + 
                        "(NOTE: deadline should be in dd-mm-yyyy hhmm format)");
    }

    public void showEndMessage() {
        System.out.println("Bye. Hope to see you again soon! \\(^-^)/ ");
    }
}

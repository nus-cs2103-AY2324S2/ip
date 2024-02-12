package duchess.util;

public class Ui {
    /**
     * Create new Ui object.
     */
    public Ui() {}

    /**
     * Print message that there are no previous tasks recorded.
     */
    public String showLoadingError() {
        return "\n No previous tasks.";
    }

    /**
     * Print message that there are no previous tasks recorded.
     */
    public String showInputError() {
        return "\n No previous tasks.";
    }

    /**
     * Print exit message.
     */
    public String printExit() {
        String response = "";
        response += "\n------------------------------------------";
        response += "\nBye. Hope to see you again soon!";
        response += "\n------------------------------------------";
        return response;
    }

    /**
     * Print menu of commands that can be used.
     */
    public String printMenu() {
        String menu = "Hello! I'm Duchess" +
                "\n--------------How to Use Me--------------" +
                "\n1. To view list of tasks: list" +
                "\n2. To create a 'To Do': todo <description>" +
                "\n3. To create a 'Deadline': deadline <description> /by <by>" +
                "\n4. To create a 'Event': event <description> /from <from> /to <to>" +
                "\n5. To mark task as done: mark <item number in list>" +
                "\n6. To mark task as undone: unmark <item number in list>" +
                "\n7. To delete task: delete <item number in list>" +
                "\n8. To find task: find <keyword>" +
                "\n7. To exit: bye";
        return menu;
    }
}

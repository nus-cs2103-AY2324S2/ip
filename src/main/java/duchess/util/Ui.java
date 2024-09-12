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
        String menu = "--------------How to Use Me--------------" +
                "\n1. To create tasks: tasks" +
                "\n2. To mark tasks: mark/unmark <item number in list>" +
                "\n3. To view tasks: list" +
                "\n4. To find task: find <keyword>" +
                "\n5. To exit: bye";

        return menu;
    }

    /**
     * Print menu of tasks that can be created.
     */
    public String printTasksMenu() {
        String tasksMenu = "--------------Tasks--------------" +
                "\n1. To Do: todo <description>" +
                "\n2. Deadline: deadline <description> /by <by>" +
                "\n3. Event: event <description> /from <from> /to <to>";

        return tasksMenu;
    }
}

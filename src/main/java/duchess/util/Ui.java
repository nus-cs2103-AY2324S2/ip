package duchess.util;

public class Ui {
    /**
     * Create new Ui object.
     */
    public Ui() {}

    /**
     * Print message that there are no previous tasks recorded.
     */
    public void showLoadingError() {
        System.out.println("No previous tasks.");
    }

    /**
     * Print exit message.
     */
    public void printExit() {
        System.out.println("\t\t------------------------------------------");
        System.out.println("\t\tBye. Hope to see you again soon!");
        System.out.println("\t\t------------------------------------------");
    }

    /**
     * Print menu of commands that can be used.
     */
    public void printMenu() {
        String menu = "\t\tHello! I'm Duchess" +
                "\n\t\t--------------How to Use Me--------------" +
                "\n\t\t1. To view list of tasks: list" +
                "\n\t\t2. To create a 'To Do': todo <description>" +
                "\n\t\t3. To create a 'Deadline': deadline <description> /by <by>" +
                "\n\t\t4. To create a 'Event': event <description> /from <from> /to <to>" +
                "\n\t\t5. To mark task as done: mark <item number in list>" +
                "\n\t\t6. To mark task as undone: unmark <item number in list>" +
                "\n\t\t7. To delete task: delete <item number in list>" +
                "\n\t\t8. To find task: find <keyword>" +
                "\n\t\t7. To exit: bye";
        System.out.println(menu);
    }
}

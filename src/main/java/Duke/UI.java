package Duke;

public class UI {
    /**
     * Contains methods to display output for User Interface
     */
    public static void showLogo() {
        String logo = "__________________________________\n" +
                "Hello! I'm Tim \n" +
                "What can i do for you? \n" +
                "__________________________________\n";
        System.out.println(logo);
    }

    public static void showExitMsg() {
        String exit = "Bye. Hope to see you again soon!\n" +
                "__________________________________";
        System.out.println(exit);
    }

    public static void showAvailCommands() {
        String commands = """
                __________________________________
                1. list
                2. bye
                3. deadline {task} /by {date}
                4. todo {task}
                5. event {task} /from {date} /to {date}
                6. check dates
                7. help
                __________________________________
                """;
        System.out.println(commands);
    }

    public static void printAddMsg(Task task, int taskNum){
        String output = "__________________________________\n" +
                "Ok, I have added this task:\n" +
                "   " + task.toString() +
                String.format("\nNow you have %d tasks in the list\n", taskNum) +
                "__________________________________\n";
        System.out.println(output);
    }

    public static void listMsg(String contents) {
        System.out.println("__________________________________\n" +
                "Here are the tasks in your list:\n" +
                contents +
                "__________________________________\n");
    }

    public static void scheduledTaskMsg() {
        System.out.println("__________________________________\n" +
                "This are the tasks within the period you stated:");
    }

    public static void checkDatesMsg() {
        System.out.println("__________________________________\n" +
                "Input dates in the form dd/mm/yyyy:\n" +
                "Start: ");
    }

    public static void markedMsg(Task task) {
        String output = "OK, I've marked this task as done:\n" +
                "   " + task.toString();
        System.out.println(output);
    }

    public static void unMarkedMsg(Task task) {
        String output = "OK, I've marked this task as not done:\n" +
                "   " + task.toString();
        System.out.println(output);
    }

    public static void deleteMsg(Task task, TaskList storage) {
        String output = "OK, I've removed this task:\n" +
                "   " + task.toString() +
                "\nNow you have " + storage.size() + " tasks in the list.";
        System.out.println(output);
    }

    public static String errorMsg(String s) {
        String output = "";
        if (s.equals("delete")) {
            output = "__________________________________\n" +
                    "What tasks do you want to delete?\n" +
                    "__________________________________\n";
        } else if (s.equals("mark")) {
            output = "__________________________________\n" +
                    "What tasks do you want to mark?\n" +
                    "__________________________________\n";
        } else if (s.equals("unmark")) {
            output = "__________________________________\n" +
                    "What tasks do you want to unmark?\n" +
                    "__________________________________\n";
        } else if (s.equals("todo")) {
            output = "__________________________________\n" +
                    "Where is the ToDo task??\n" +
                    "__________________________________\n";
        } else if (s.equals("deadline")) {
            output = "__________________________________\n" +
                    "Where is the Deadline task??\n" +
                    "__________________________________\n";
        } else if (s.equals("event")) {
            output = "__________________________________\n" +
                    "Where is the Event task??\n" +
                    "__________________________________\n";
        }
        return output;
    }

}

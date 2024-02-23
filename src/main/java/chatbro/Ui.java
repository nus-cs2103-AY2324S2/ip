package chatbro;

/**
 * Represents the user interface of ChatBro.
 */
public class Ui {
    public static String welcomeMessage() {
        return " __  __       __\n"
                + " \\ \\/ /__    / /\n"
                + "  \\  / _ \\  /_/ \n"
                + "  /_/\\___/ (_)\n\n"
                + "I'm ChatBro! What can I do for you bro?\n"
                + "Type 'help' to see the list of available commands!";
    }
    public static String helpMessage() {
        return "Here are the available commands bro:\n"
                + "1. list (view your list of tasks)\n"
                + "2. bye (saves your task list and ends the program)\n"
                + "(Tasks *will not be saved* if you exit the program without 'bye')\n"
                + "3. mark <task number> (marks a task as done)\n"
                + "4. unmark <task number> (marks a task as undone)\n"
                + "5. delete <task number> (delete task from your list)\n\n"
                + "You may add tasks to your list using:\n"
                + "6. todo <description>\n"
                + "7. deadline <description> /by <deadline time>\n"
                + "8. event <description> /from <start time> /to <end time>\n"
                + "9. interval <description> /from <starting time of interval deadline> /to "
                + "<ending time of interval deadline>\n\n"
                + "Deadline time and start/end times must follow the format:\n"
                + "'DD-MM-YYYY HHmm' (HHmm is *optional* and in 24hr format)\n\n"
                + "Find tasks using:\n"
                + "10. find <keyword> (finds tasks containing keyword in the description)";
    }
    public static String byeMessage() {
        return "I have saved your tasks.\nHasta la vista bro!";
    }

    public static String formatErrorMessage(String format) {
        return "Hey bro, make sure to follow the format:\n" + format;
    }
    public static String addMessage(Task task) {
        return "Got it bro. I've added this task:\n" + task.toString()
                + "\nNow you have " + TaskManager.getTaskCount() + " tasks in the list.";
    }
    public static String deleteMessage(Task task) {
        return "Noted bro. I've removed this task:\n" + task.toString()
                + "\nNow you have " + TaskManager.getTaskCount() + " tasks in the list.";
    }
    public static String doesNotExistMessage() {
        return "Sorry bro, this task does not exist in your list.";
    }
    public static String markMessage(Task task) {
        return "Got it bro! I've marked this task as done:\n" + task.toString();
    }
    public static String unmarkMessage(Task task) {
        return "Got it bro! I've marked this task as undone:\n" + task.toString();
    }
    /**
     * Returns a message containing the list of tasks.
     * @return Message containing the list of tasks.
     */
    public static String listMessage() {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list bro:\n");
        for (int i = 1; i <= 100; i++) {
            if (TaskManager.getList().get(i) == null) {
                break;
            }
            sb.append(i + "." + TaskManager.getList().get(i).toString() + "\n\n");
        }
        return sb.toString();
    }
    public static String taskDescEmptyMessage() {
        return "Hey bro, task description cannot be empty.";
    }
    public static String startTimeEmptyMessage() {
        return "Hey bro, start time cannot be empty.";
    }
    public static String endTimeEmptyMessage() {
        return "Hey bro, end time cannot be empty.";
    }
    public static String dueByInterval() { // returns String "(due by interval: " in bold and italics
        return " (\uD835\uDC85\uD835\uDC96\uD835\uDC86 \uD835\uDC83\uD835\uDC9A"
            + " \uD835\uDC8A\uD835\uDC8F\uD835\uDC95\uD835\uDC86\uD835\uDC93\uD835\uDC97\uD835\uDC82\uD835\uDC8D: ";
    }
    public static String to() { // Returns String " to: " in bold and italics
        return " \uD835\uDC95\uD835\uDC90: ";
    }
    public static String from() { // Returns String " (from: " in bold and italics
        return " (\uD835\uDC87\uD835\uDC93\uD835\uDC90\uD835\uDC8E: ";
    }
    public static String by() { // Returns String " (by: " in bold and italics
        return " (\uD835\uDC83\uD835\uDC9A: ";
    }
}

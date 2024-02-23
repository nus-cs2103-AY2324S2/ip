package friendlytool.process;

import friendlytool.task.Task;

/**
 * UI for the program. Provides instructions to users.
 */
public class UI {

    private static StringBuilder sb = new StringBuilder();

    /**
     * Provides a response to user's input
     * @return Provides a cumulated response,
     */
    public static String getResponse() {
        String response = sb.toString();
        sb = new StringBuilder();
        return response;
    }
    /**
     * Provides a welcome message.
     * @return welcome message.
     */
    public static String getInitMsg() {
        sb.append("    ____________________________________________________________\n")
                .append("    Hello! I'm FriendlyTool\n")
                .append("    What can I do for you?\n")
                .append("    ____________________________________________________________");
        return UI.getResponse();
    }

    /**
     * Provides a bye message.
     * @return bye message.
     */
    public static String getByeMsg() {
        sb.append("    ____________________________________________________________\n")
                .append("    Bye. Hope to see you again soon!\n")
                .append("    ____________________________________________________________");
        return UI.getResponse();
    }

    /**
     * Prints task updated message.
     *
     * @param task task updated.
     * @param size number of elements in the list.
     * @return string that the task is updated.
     */
    public static String getUpdateTaskMsg(Task task, int size) {
        sb.append("    ____________________________________________________________\n")
                .append("    Completed. I've added this task: \n    ")
                .append(task)
                .append("\n    Now you have " + size + " tasks in the list.\n")
                .append("    ____________________________________________________________\n");
        return UI.getResponse();
    }

    /**
     * Provides a string that a task is marked as done.
     *
     * @param task task given.
     * @return string that task is marked.
     */
    public static String getMarkMsg(Task task) {
        sb.append("    ____________________________________________________________\n")
                .append("    Nice! I've marked this task as done:\n")
                .append("      " + task.toString())
                .append("\n    ____________________________________________________________\n");
        return UI.getResponse();
    }


    /**
     * Provides a string that a task is unmarked.
     *
     * @param task task given.
     * @return string that task is unmarked
     */
    public static String getUnmarkMsg(Task task) {
        sb.append("    ____________________________________________________________\n")
                .append("    OK, I've marked this task as not done yet:\n")
                .append("      " + task.toString())
                .append("\n    ____________________________________________________________\n");
        return UI.getResponse();
    }

    /**
     * Provides a string that the task is deleted.
     *
     * @param task task deleted.
     * @param size number of elements in the list.
     * @return string that the task is deleted.
     */
    public static String getDeleteMsg(String task, int size) {
        sb.append("    ____________________________________________________________\n")
                .append("    Great!, You have completed the task:\n")
                .append("      " + task)
                .append("\n    Now you have " + size + " tasks in the list.\n")
                .append("    ____________________________________________________________\n");
        return UI.getResponse();
    }

    /**
     * Provides a string whether the save data is created or not.
     *
     * @param isSuccessful save data creation is successful or not
     */
    public static void getCreateSaveMsg(Boolean isSuccessful) {
        System.out.println("    There was no save data.");
        System.out.println(isSuccessful ? "    New save data file created." : "    Failed to create a new save data");
    }

    /**
     * Prints that save data is loaded.
     */
    public static void getLoadSaveMsg() {
        System.out.println("    Successfully loaded the save data. ");
    }


    /**
     * Prints the whole list with index.
     *
     * @param list list to be printed.
     * @return string of list
     */
    public static String getListMsg(TaskList list) {
        sb.append("    ____________________________________________________________\n")
                .append("    Here are the tasks in your list:\n");
        for (int i = 1; i < list.size() + 1; i++) {
            Task task = list.get(i - 1);
            sb.append("    ").append(i).append(".").append(task).append("\n");
        }
        sb.append("    ____________________________________________________________\n");
        return UI.getResponse();
    }

    /**
     * prepares string that there are matching tasks in the string builder.
     */
    public static void prepareMatchingMsg() {
        sb.append("____________________________________________________________\n")
                .append("These are the matching tasks in your list:\n");
    }

    /**
     * Prepares the found task in the string builder.
     *
     * @param i    current index
     * @param task task found
     */
    public static void prepareFindTaskMsg(int i, Task task) {
        sb.append("    ").append(i).append(".").append(task).append("\n");
    }

    /**
     * Prints out a dotted line.
     *
     * @return dotted line
     */
    public static String getFound() {
        sb.append("____________________________________________________________\n");
        return UI.getResponse();
    }

    /**
     * Provides Error message.
     *
     * @param e error given
     * @return error message
     */
    public static String getErrorMsg(FtException e) {
        assert e != null : "Error should not be null";
        sb.append("____________________________________________________________\n")
                .append(e.getMessage()).append("\n")
                .append("____________________________________________________________\n");
        return UI.getResponse();
    }

    /**
     * Provides a message that task is sorted.
     *
     * @return a message that task is sorted
     */
    public static String getSortTaskMsg() {
        sb.append("____________________________________________________________\n")
                .append("    OK, I've sorted your tasks!\n")
                .append("____________________________________________________________\n");
        return UI.getResponse();
    }
}

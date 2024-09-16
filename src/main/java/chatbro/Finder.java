package chatbro;

/**
 * Finder class that contains method to find tasks in the task list.
 */
public class Finder {
    /**
     * Performs a partial match searching, for the keyword, of tasks in the task list.
     *
     * @param keyword Keyword to search for in the task list.
     * @return String containing the tasks that contain the keyword.
     */
    public static String findTask(String keyword) {
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= TaskManager.getTaskCount(); i++) {
            if (TaskManager.getTask(i).getDescription().contains(keyword)) { // substring match / partial match
                if (count == 0) {
                    sb.append("Here are the matching tasks in your list bro:" + "\n");
                }
                count++;
                sb.append(count + ". " + TaskManager.getTask(i).toString() + "\n\n");
            }
        }
        if (count == 0) {
            sb.append("Oops, no matching tasks found in your list bro.");
        }
        return sb.toString();
    }
}

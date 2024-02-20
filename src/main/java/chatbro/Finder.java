package chatbro;

/**
 * Finder class that contains method to find tasks in the task list.
 */
public class Finder {
    public static String findTask(String keyword) {
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= TaskManager.getTaskCount(); i++) {
            if (TaskManager.getTask(i).getDescription().contains(keyword)) {
                if (count == 0) {
                    sb.append("Here are the matching tasks in your list bro:");
                }
                count++;
                sb.append(count + ". " + TaskManager.getTask(i).toString());
            }
        }
        if (count == 0) {
            sb.append("Oops, no matching tasks found in your list bro.");
        }
        return sb.toString();
    }
}

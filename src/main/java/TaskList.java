import java.util.ArrayList;

public class TaskList {
    private static ArrayList<String> taskList = new ArrayList<String>();
    String indent = "    ";

    public void addTask(String task) {
        taskList.add(task);
    }

    public String showNewest() {
        int length = taskList.size();
        return taskList.get(length - 1);
    }

    public String showList() {
        String task;
        String finalOutput = new String("");
        for (int i = 0; i < taskList.size(); i++) {
            task = taskList.get(i);
            finalOutput = finalOutput + indent + Integer.toString(i + 1) + "." + task + "\n";
        }
        return finalOutput;
    }
}

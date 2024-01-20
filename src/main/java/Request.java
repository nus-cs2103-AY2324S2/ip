import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Request {
    private String name;
    private int taskType;

    public Request(String name) {
        Pattern todoPattern = Pattern.compile("^todo (.+)$");
        Pattern deadlinePattern = Pattern.compile("^deadline (.+) /by (.+)$");
        Pattern eventPattern = Pattern.compile("^event (.+) /from (.+) /to (.+)$");

        Matcher todoMatcher = todoPattern.matcher(name);
        Matcher deadlineMatcher = deadlinePattern.matcher(name);
        Matcher eventMatcher = eventPattern.matcher(name);

        if (todoMatcher.matches()) {
            this.name = todoMatcher.group(1);
            this.taskType = 0;
        } else if (deadlineMatcher.matches()) {
            this.name = deadlineMatcher.group(1) + " (by: " + deadlineMatcher.group(2) + ")";
            this.taskType = 1;
        } else if (eventMatcher.matches()) {
            this.name = eventMatcher.group(1) + " (from: " + eventMatcher.group(2) + " to: " + eventMatcher.group(3) + ")";
            this.taskType = 2;
        } else {
            this.name = name;
        }
    }

    public void handleRequest() {
        if (this.name.contains("unmark")) {
            int taskNumber = Integer.parseInt(this.name.substring(7));
            Nicole.taskList.get(taskNumber - 1).markUndone();
        } else if (this.name.contains("mark")) {
            int taskNumber = Integer.parseInt(this.name.substring(5));
            Nicole.taskList.get(taskNumber - 1).markDone();
        } else if (!this.name.equals("list")) {
            Task newTask = Task.taskFactory(this.name, this.taskType);
            Nicole.taskList.add(newTask);
            System.out.println(Nicole.botName +
                               ": Oki I added " +
                               "\"" + newTask.toString().substring(6) + "\". " +
                               "There are now " + Nicole.taskList.size() + " item(s) total.");
        } else {
            System.out.println(Nicole.botName + ": Your tasks are, ");
            for (int i = 0; i < Nicole.taskList.size(); i++) {
                System.out.println((i + 1) + ": " + Nicole.taskList.get(i));
            }
        }
    }
}

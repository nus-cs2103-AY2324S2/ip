import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Request {
    private String name;
    private Task newTask;


    public Request(String name) throws NicoleException {
        Pattern todoPattern = Pattern.compile("^todo(?: (.*?))?$");
        Pattern deadlinePattern = Pattern.compile("^deadline(?: (.*?))?(?: /by (.*?))?$");
        Pattern eventPattern = Pattern.compile("^event(?: (.*?))?(?: /from (.*?) /to (.*?))?$");

        Matcher todoMatcher = todoPattern.matcher(name);
        Matcher deadlineMatcher = deadlinePattern.matcher(name);
        Matcher eventMatcher = eventPattern.matcher(name);

        if (name.equals("list")     ||
            name.contains("mark")   ||
            name.contains("unmark") ||
            name.contains("help")   ||
            name.contains("delete")) {
            this.name = name;
        } else {
            if (todoMatcher.matches()) {
                this.newTask = Task.taskFactory(todoMatcher.group(1), 0);
            } else if (deadlineMatcher.matches()) {
                this.newTask = Task.taskFactory(
                                            deadlineMatcher.group(1) + " (by: " + deadlineMatcher.group(2) + ")",
                                            1);
            } else if (eventMatcher.matches()){
                this.newTask = Task.taskFactory(eventMatcher.group(1)
                                                + " (from: "
                                                + eventMatcher.group(2)
                                                + " to: "
                                                + eventMatcher.group(3)
                                                + ")",
                                                2);
            } else {
                throw new NicoleException("What does this mean? I only know todo, deadline, event, list and bye!");
            }
            this.name = name;
        }
    }

    public void handleRequest() throws NicoleException {
        if (this.name.contains("unmark")) {
            int taskNumber = Integer.parseInt(this.name.substring(7));
            this.crudChecker(taskNumber);
            Nicole.taskList.get(taskNumber - 1).markUndone();
        } else if (this.name.contains("mark")) {
            int taskNumber = Integer.parseInt(this.name.substring(5));
            this.crudChecker(taskNumber);
            Nicole.taskList.get(taskNumber - 1).markDone();
        } else if (this.name.contains("delete")) {
            int taskNumber = Integer.parseInt(this.name.substring(7));
            this.crudChecker(taskNumber);
            Nicole.taskList.remove(taskNumber - 1);
            System.out.println(Nicole.botName + ": Phew...deleted  :>");
        } else if (this.name.equals("help")) {
            System.out.println(Nicole.botName + ": " +
                    "I'm your task/deadline/event manager! I'm down with these requests,\n" +
                    "1. todo [task]\n2. deadline [task] /by [datetime]\n3. event [name] /from [starting] /to [ending]\n" +
                    "4. list\n5. bye\n6. help"
            );
        } else if (!this.name.equals("list")) {
            Nicole.taskList.add(this.newTask);
            System.out.println(Nicole.botName +
                    ": Oki I added " +
                    "\"" + newTask.toString().substring(6) + "\". " +
                    "There are now " + Nicole.taskList.size() + " item(s) total.");
        } else {
            this.listTasks();
        }
    }

    private void listTasks() {
        if (Nicole.taskList.size() == 0) {
            System.out.println(Nicole.botName + ": No items. Relax <3");
        } else {
            System.out.println(Nicole.botName + ": Your tasks are,");
            for (int i = 0; i < Nicole.taskList.size(); i++) {
                System.out.println((i + 1) + ": " + Nicole.taskList.get(i));
            }
        }
    }

    private void crudChecker(int taskNumber) throws NicoleException {
        if (taskNumber <= 0 || taskNumber > Nicole.taskList.size()) {
            throw new NicoleException("Huh? That's not a valid item number :')");
        }
    }
}

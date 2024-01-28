import java.io.IOException;
import java.io.FileWriter;

import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Request {
    private String name;
    private Task newTask;
    public static boolean priorityTasking = false;

    public Request(String name) throws NicoleException {
        this.parseRequest(name);
    }

    private void parseRequest(String name) throws NicoleException {
        Pattern todoPattern = Pattern.compile("^todo(?: (.*?))?$");
        Pattern deadlinePattern = Pattern.compile("^deadline(?: (.*?))?(?: by (\\d{4}-\\d{2}-\\d{2}))?$");
        Pattern eventPattern = Pattern.compile("^event(?: (.*?))?(?: " +
                                                        "from (\\d{4}-\\d{2}-\\d{2}) at (\\d{2}:\\d{2}:\\d{2}) " +
                                                        "to (\\d{4}-\\d{2}-\\d{2}) at (\\d{2}:\\d{2}:\\d{2}))?$");

        Matcher todoMatcher = todoPattern.matcher(name);
        Matcher deadlineMatcher = deadlinePattern.matcher(name);
        Matcher eventMatcher = eventPattern.matcher(name);
        // event meeting with kay from 2024-01-29 at 18:30:00 to 2024-01-29 at 21:00:00
        // deadline return book by 2024-01-28
        if (name.equals("list")         ||
                name.contains("mark")   ||
                name.contains("unmark") ||
                name.contains("help")   ||
                name.contains("delete") ||
                name.contains("priority")) {
            this.name = name;
        } else {
            if (todoMatcher.matches()) {
                this.newTask = Task.taskFactory(todoMatcher.group(1), 'T');
            } else if (deadlineMatcher.matches()) {
                this.newTask = Task.taskFactory(
                        deadlineMatcher.group(1) + " by " + deadlineMatcher.group(2),
                        'D');
            } else if (eventMatcher.matches()){
                this.newTask = Task.taskFactory(eventMatcher.group(1)
                                + " from "
                                + eventMatcher.group(2) + " at " + eventMatcher.group(3)
                                + " to "
                                + eventMatcher.group(4) + " at " + eventMatcher.group(5), 'E');
            } else {
                throw new NicoleException("What does this mean? Send 'help' if you want to know what commands I can help you with");
            }
            this.name = name;
        }
    }

    public void handleRequest() throws NicoleException, IOException {
        if (this.name.contains("unmark")) {
            int taskNumber = Integer.parseInt(this.name.substring(7));
            this.crudChecker(taskNumber);
            System.out.println(Nicole.taskList.get(taskNumber - 1).markUndone());
            FileWriter taskFileWriter = new FileWriter("./data/tasks.txt");
            this.saveTasksToFile(taskFileWriter);
        } else if (this.name.contains("mark")) {
            int taskNumber = Integer.parseInt(this.name.substring(5));
            this.crudChecker(taskNumber);
            System.out.println(Nicole.taskList.get(taskNumber - 1).markDone());
            FileWriter taskFileWriter = new FileWriter("./data/tasks.txt");
            this.saveTasksToFile(taskFileWriter);
        } else if (this.name.contains("delete")) {
            int taskNumber = Integer.parseInt(this.name.substring(7));
            this.crudChecker(taskNumber);
            Nicole.taskList.remove(taskNumber - 1);
            System.out.println(Nicole.botName + ": Phew...deleted  :>");
            FileWriter taskFileWriter = new FileWriter("./data/tasks.txt");
            this.saveTasksToFile(taskFileWriter);
        } else if (this.name.equals("help")) {
            System.out.println(Nicole.botName + ": " +
                    "I'm your task/deadline/event manager! I'm down with these requests,\n" +
                    "1. todo [task]\n" +
                    "2. deadline [task] by YYYY-MM-DD\n" +
                    "3. event [name] from YYYY-MM-DD at HH-MM-SS to YYY-MM-DD at HH-MM-SS\n" +
                    "4. list\n" +
                    "5. priority\n" +
                    "6. bye\n" +
                    "7. help"
            );
        } else if (this.name.equals("priority")) {
            Request.priorityTasking = true;
        } else if (!this.name.equals("list")) {
            Nicole.taskList.add(this.newTask);
            try {
                FileWriter taskFileWriter = new FileWriter("./data/tasks.txt", true);
                taskFileWriter.write(this.newTask.toString() + "\n");
                taskFileWriter.close();
            } catch (IOException e) {
                throw new NicoleException("I couldn't save the task >< try again plss");
            }
            System.out.println(Nicole.botName +
                    ": Oki I added " + "\"" + newTask.toString().substring(7) + "\"");
        } else {
            this.listTasks();
        }
    }

    private void saveTasksToFile(FileWriter taskFileWriter) throws NicoleException {
        try {
            for (int i = 0; i < Nicole.taskList.size(); i++) {
                taskFileWriter.write(Nicole.taskList.get(i) + "\n");
            }
            taskFileWriter.close();
        } catch (IOException e) {
            throw new NicoleException("I couldn't save the task >< try again plss");
        }
    }

    private void listTasks() {
        if (Nicole.taskList.size() == 0) {
            System.out.println(Nicole.botName + ": No tasks yet. Let's make some moves BD");
            return;
        }
        System.out.println(Nicole.botName + ": Here's the tasks I saved so far,");
        if (Request.priorityTasking) {
            Comparator<Task> sorter = (task1, task2) -> {
                if (task1.getDate().isBefore(task2.getDate())) {
                    return -1;
                } else if (task1.getDate().isEqual(task2.getDate())) {
                    return 0;
                } else {
                    return 1;
                }
            };
            Nicole.taskList.sort(sorter);
        }
        for (int i = 0; i < Nicole.taskList.size(); i++) {
            System.out.println((i + 1) + ". " + Nicole.taskList.get(i));
        }
    }

    private void crudChecker(int taskNumber) throws NicoleException {
        if (taskNumber <= 0 || taskNumber > Nicole.taskList.size()) {
            throw new NicoleException("Huh? That's not a valid item number :')");
        }
    }
}

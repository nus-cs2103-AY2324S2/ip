import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Request {
    private String name;
    private Task newTask;


    public Request(String name) throws NicoleException {
        this.parseRequest(name);
    }

    private void parseRequest(String name) throws NicoleException {
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

    public void handleRequest() throws NicoleException, IOException {
        if (this.name.contains("unmark")) {
            int taskNumber = Integer.parseInt(this.name.substring(7));
            this.crudChecker(taskNumber);
            Nicole.taskList.get(taskNumber - 1).markUndone();
            FileWriter taskFileWriter = new FileWriter("./data/tasks.txt");
            this.saveTasksToFile(taskFileWriter);
        } else if (this.name.contains("mark")) {
            int taskNumber = Integer.parseInt(this.name.substring(5));
            this.crudChecker(taskNumber);
            Nicole.taskList.get(taskNumber - 1).markDone();
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
                    "1. todo [task]\n2. deadline [task] /by [datetime]\n3. event [name] /from [starting] /to [ending]\n" +
                    "4. list\n5. bye\n6. help"
            );
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
                    ": Oki I added " +
                    "\"" + newTask.toString().substring(6) + "\". " +
                    "There are now " + Nicole.taskList.size() + " item(s) total.");
        } else {
            this.loadTasksFromFile();
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

    private void loadTasksFromFile() {
        File tasksFile = new File("./data/tasks.txt");
        try {
            Scanner userTaskFileReader = new Scanner(tasksFile);
            if (!userTaskFileReader.hasNextLine()) {
                System.out.println(Nicole.botName + ": No tasks saved yet. Let's make some moves BD");
            } else {
                System.out.println(Nicole.botName + ": Here's the tasks I saved so far,");
                int i = 1;
                while (userTaskFileReader.hasNextLine()) {
                    System.out.println(i + ". " + userTaskFileReader.nextLine());
                    i++;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("I have no past data with you, let's start something ;)");
        }
    }

    private void crudChecker(int taskNumber) throws NicoleException {
        if (taskNumber <= 0 || taskNumber > Nicole.taskList.size()) {
            throw new NicoleException("Huh? That's not a valid item number :')");
        }
    }
}

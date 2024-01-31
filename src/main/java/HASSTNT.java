import java.io.*;
import java.util.regex.Pattern;
import java.util.Scanner;
public class HASSTNT {
    private final Scanner scanner;
    private final ToDoList l;
    private final String path = "./src/list_log";
    private static final String AvailableCommand = "Now there are 3 ways to add a Task to list (Replace your <task> with your task name:\n\t1.todo <task> \n\t1.todo <task>\n\t2.deadline <task> by <MM-dd hh:mm am/pm or MM-dd> (where mm is minute and MM is month)\n\t 3.event <task> from \n\t3.1<MM/dd> to <MM/dd> or \n\t3.2<MM/dd mm:hh am/pm> to <MM/dd mm:hh am/pm> or \n\t3.3<MM/dd> <mm:hh> to <mm:hh>";
    private static final String AvailableListOperation = "Here is what you can do with the tasks once added to the list:\n\t 1.mark (to make a mark on the task) <the number of task on the list>\n\t 2.unmark (to unmark a task on the list) <the number of task on the list>\n\t 3.remove <the number of task on the list>(remove a task from the list)\n\t 4.list (to show the current list)";

    private static final String welcome_message = "HASSNT:\n" +
            "Hello! I'm HASSTNT " + "What can I do for you?\n"+"to see what I can do type -command\n" + "-- our date format starts with month  instead of day (ie. mm-dd) --";
    private static final String goodbye_message = "HASSNT:\n" +
            "Bye. Hope to see you again soon!";

    public HASSTNT() {
        // initialize a scanner
        scanner = new Scanner(System.in);
        l = new ToDoList();
    }

    public void startConversation() {
        reloadsession();
        // Method containing the conversation logic
        System.out.println(welcome_message);
        String input;
        while (true) {
            input = scanner.nextLine().toLowerCase();
            try {
                switch (input) {
                    case "bye":
                        saveTasksToFile();
                        handleByeCommand();
                        return;
                    case "-command":
                        showcommand();
                    case "list":
                        l.showLists();
                        break;
                    default:
                        if (input.startsWith("mark")) {
                            handleMarkCommand(input);
                        } else if (input.startsWith("remove")) {
                            handleRemoveCommand(input);
                        } else if (input.startsWith("unmark")) {
                            handleUnmarkCommand(input);

                        } else {
                            l.addToList(input);
                        }
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void handleByeCommand() {
        System.out.println(goodbye_message);
    }

    private void handleMarkCommand(String input) throws IllegalArgumentException {
        String numberString = input.substring("mark".length()).trim();
        if (!numberString.matches("\\d+")) {
            throw new IllegalArgumentException("Invalid input. Please enter mark followed by an integer.");
        }
        int index = Integer.parseInt(numberString);
        l.showMark(index);
    }

    private void handleUnmarkCommand(String input) throws IllegalArgumentException {
        {
            String numberString = input.substring("unmark".length()).trim();
            if (!numberString.matches("\\d+")) {
                throw new IllegalArgumentException("Invalid input. Please enter unmark followed by an integer.");
            }
            int index = Integer.parseInt(numberString);
            l.showUnmark(index);
        }
    }

    private void handleRemoveCommand(String input) throws IllegalArgumentException {
        String numberString = input.substring("remove".length()).trim();
        if (!numberString.matches("\\d+")) {
            throw new IllegalArgumentException("Invalid input. Please enter remove followed by an integer.");
        }
        int index = Integer.parseInt(numberString.trim());
        l.removeTask(index);

    }
    private void reloadsession(){
        try {
            Scanner scanner = new Scanner(new File(path)); // Assuming input.txt is the file you want to read from
            while (scanner.hasNextLine()) {
                String x = scanner.nextLine();
                String[] c = x.split(Pattern.quote("|"));
                String type = c[0];
                String mark = c[1];
                String TaskName = c[2];
                Task  t;
                switch (type){
                    case "T":
                        t = new ToDoTask(TaskName);
                        l.addToList(t);
                        break;
                    case "D":
                        t = new DeadLineTask(c[3],TaskName);
                        l.addToList(t);
                        break;
                    default:
                        t =  new EventTask(c[3],c[4],TaskName);
                        l.addToList(t);
                }
                if (mark.equals("Y")){
                    t.markAsDone();
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }


    private void saveTasksToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (Task task : l) {
                // a string from each task function that returns the desired string
                writer.write(task.logString());
                writer.newLine();
            }

        } catch (IOException e) {
            // Handle file writing error
            e.printStackTrace();
        }

    }
    private static void showcommand(){
        System.out.println(AvailableCommand);
        System.out.println(AvailableListOperation);
    }
}
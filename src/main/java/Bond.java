import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;

public final class Bond {

    private static final String LINE = "____________________________________________________________";

    private static final String LOGO = "Bond";

    private static final ArrayList<String> COMMANDS = new ArrayList<>() {
        {
            add("todo");
            add("deadline");
            add("event");
            add("list");
            add("mark");
            add("unmark");
            add("bye");
            add("delete");
        }
    };

    private static Boolean isValidCommand(String input) {
        return COMMANDS.contains(input.toLowerCase());
    }

    private static Boolean isNumber(String input) {
        char[] digits = input.toCharArray();
        Boolean isNumber = true;

        for (char c : digits) {
            if (!Character.isDigit(c)) {
                isNumber = false;
                break;
            }
        }

        return isNumber;
    }

    private static void addTask(String taskName, ArrayList<Task> taskList, Boolean marked, Boolean tellUser,
            String filePath, Boolean isWrittenToFile) {
        try {
            Task newTask = Task.makeTask(taskName);
            taskList.add(newTask);

            if (isWrittenToFile) {
                FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
                fw.write(newTask.toString());
                fw.write(System.lineSeparator());
                fw.close();
            }

            if (marked) {
                markTask(taskList.size() - 1, taskList, tellUser, filePath);
            }

            if (tellUser) {
                System.out.println(String.format(
                        "%s\n\n    Got it. I've added this task:\n      %s \n    Now you have %d tasks in the list.\n%s\n",
                        LINE, newTask.toString(), taskList.size(), LINE));
            }
        } catch (IOException e) {
            System.out.println(String.format("%s\n\n    %s\n%s\n", LINE, e.getMessage(), LINE));
            System.exit(0);
        }
    }

    private static void parseAndAddTask(String task, ArrayList<Task> taskList, String filePath) throws IOException {
        // System.out.println(task);
        String remainder = task.substring(4);
        String taskName = "";
        Boolean isMarked = false;

        if (remainder.startsWith("[X]")) {
            isMarked = true;
        }

        remainder = remainder.substring(4);

        // Determine task name
        if (remainder.indexOf("(") == -1) {
            taskName = remainder;
            remainder = "";
        } else {
            int openIndex = remainder.indexOf("(");
            taskName = remainder.substring(0, openIndex - 1);
            remainder = remainder.substring(openIndex + 1);
        }

        // Add task to taskList
        if (task.startsWith("[T]")) {
            addTask("todo " + taskName, taskList, isMarked, false, filePath, false);
        } else if (task.startsWith("[D]")) {
            int spaceIndex = remainder.indexOf(" ");
            int closeIndex = remainder.indexOf(")");
            String deadline = remainder.substring(spaceIndex + 1, closeIndex);

            addTask("deadline " + taskName + " /by " + deadline, taskList, isMarked, false, filePath, false);
        } else if (task.startsWith("[E]")) {
            String start = "";
            String end = "";
            int closeIndex = remainder.indexOf(")");
            remainder = remainder.substring(0, closeIndex);
            String[] components = remainder.split(" ");

            for (int i = 0; i < components.length; i++) {
                if (components[i].equals("from:")) {

                    for (int j = i + 1; j < components.length; j++) {
                        if (components[j].equals("to:")) {
                            break;
                        }
                        start += components[j] + " ";
                    }

                } else if (components[i].equals("to:")) {
                    end = components[i + 1];
                }
            }

            addTask("event " + taskName + " /from " + start + " /to " + end, taskList, isMarked, false, filePath,
                    false);
        }
    }

    private static void loadTasksFromFile(String filePath, ArrayList<Task> taskList) {
        try {
            File f = new File(filePath); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source

            while (s.hasNextLine()) {
                // System.out.println("I have reached Loop in " + "loadTasksFromFIle");
                String currTask = s.nextLine();
                // System.out.println("Current task read is: " + currTask);
                parseAndAddTask(currTask, taskList, filePath);
            }

            s.close();
        } catch (FileNotFoundException e) {
            System.out.println(String.format("%s\n\n    %s\n%s\n", LINE, e.getMessage(), LINE));
            System.exit(0);
        } catch (IOException e) {
            System.out.println(String.format("%s\n\n    %s\n%s\n", LINE, e.getMessage(), LINE));
            System.exit(0);
        }
    }

    private static void updateFile(String filePath, ArrayList<Task> taskList) {
        try {
            FileWriter fw = new FileWriter(filePath, false); // create a FileWriter in overwrite mode
            ListIterator<Task> toWrite = taskList.listIterator();

            while (toWrite.hasNext()) {
                // System.out.println("I have reached Loop in " + "updateFile");
                fw.write(toWrite.next().toString());
                fw.write(System.lineSeparator());
            }

            fw.close();
        } catch (IOException e) {
            System.out.println(String.format("%s\n\n    %s\n%s\n", LINE, e.getMessage(), LINE));
            System.exit(0);
        }
    }

    private static void deleteTask(int taskIndex, ArrayList<Task> taskList, Boolean tellUser, String filePath) {
        Task deletedTask = taskList.get(taskIndex);
        taskList.remove(taskIndex);

        updateFile(filePath, taskList);

        if (tellUser) {
            System.out.println(String.format(
                    "%s\n\n    Got it. I've removed this task:\n      %s \n    Now you have %d tasks in the list.\n%s\n",
                    LINE, deletedTask.toString(), taskList.size(), LINE));
        }
    }

    private static void markTask(int taskIndex, ArrayList<Task> taskList, Boolean tellUser, String filePath) {
        taskList.get(taskIndex).markAsComplete();
        updateFile(filePath, taskList);

        if (tellUser) {
            System.out
                    .println(String.format(
                            "%s\n\n    Nice! I've marked this task as done:\n      %s \n%s\n",
                            LINE, taskList.get(taskIndex).toString(), LINE));
        }
    }

    private static void unmarkTask(int taskIndex, ArrayList<Task> taskList, Boolean tellUser, String filePath) {
        taskList.get(taskIndex).markAsIncomplete();
        updateFile(filePath, taskList);

        if (tellUser) {
            System.out
                    .println(String.format(
                            "%s\n\n    OK, I've marked this task as not done yet:\n      %s \n%s\n",
                            LINE, taskList.get(taskIndex).toString(), LINE));
        }
    }

    public static void main(String[] args) {

        System.out.println(String.format("Hello! I'm %s. \nWhat can I do for you? \n%s\n", LOGO, LINE));

        ArrayList<Task> taskList = new ArrayList<Task>();

        try {
            // Check for directory / file existence
            String home = System.getProperty("user.home");
            java.nio.file.Path directoryPath = java.nio.file.Paths.get(home, "data");
            boolean directoryExists = java.nio.file.Files.exists(directoryPath);

            if (!directoryExists) {
                java.nio.file.Files.createDirectory(directoryPath);
            }

            java.nio.file.Path filePath = java.nio.file.Paths.get(home, "data", "Bond.txt");
            boolean fileExists = java.nio.file.Files.exists(filePath);

            if (!fileExists) {
                java.nio.file.Files.createFile(filePath);
            }
        } catch (java.io.IOException e) {
            System.out.println(String.format("%s\n\n    %s\n%s\n", LINE, e.getMessage(), LINE));
            System.exit(0);
        }

        String filePath = System.getProperty("user.home") + "/data/Bond.txt";

        // Read from file: Bond.txt

        loadTasksFromFile(filePath, taskList);

        Scanner scNext = new Scanner(System.in);

        String userInput = "";

        while (scNext.hasNextLine()) {
            // System.out.println("I have reached Loop " + "in MAIN function");

            userInput = scNext.nextLine();

            String[] components = userInput.split(" ");

            try {

                // Invalid Command syntax
                if (!Bond.isValidCommand(components[0])) {
                    BondException.raiseException("NA",
                            "INVALID_COMMAND_TYPE");
                }

                if (components[0].equalsIgnoreCase("todo")) {

                    // No valid task name specified for a todo task
                    if (components.length == 1) {
                        BondException.raiseException("todo", "EMPTY_DESCRIPTION");
                    }

                    Bond.addTask(userInput, taskList, false, true, filePath, true);

                } else if (components[0].equalsIgnoreCase("deadline")) {

                    if (components.length == 1) {
                        BondException.raiseException("deadline", "EMPTY_DESCRIPTION");
                    }

                    Bond.addTask(userInput, taskList, false, true, filePath, true);

                } else if (components[0].equalsIgnoreCase("event")) {

                    if (components.length == 1) {
                        BondException.raiseException("event", "EMPTY_DESCRIPTION");
                    }

                    Bond.addTask(userInput, taskList, false, true, filePath, true);

                } else if (components[0].equalsIgnoreCase("list")) {

                    if (components.length != 1) {
                        BondException.raiseException("list", "EXTRA_DETAILS");
                    }

                    ListIterator<Task> toprintln = taskList.listIterator();

                    System.out.println(String.format("%s\n\n    Here are the tasks in your list:", LINE));

                    while (toprintln.hasNext()) {
                        // System.out.println("I have reached Loop in " + "list function");
                        System.out.println(String.format("    %d. %s",
                                toprintln.nextIndex() + 1, toprintln.next().toString()));
                    }

                    System.out.println(LINE + "\n");

                } else if (components[0].equalsIgnoreCase("mark")) {

                    if (components.length == 1) {
                        BondException.raiseException("mark", "MISSING_INDEX");
                    } else if (!isNumber(components[1])) {
                        BondException.raiseException("mark", "INVALID_INDEX");
                    } else if (Integer.parseInt(components[1]) - 1 >= taskList.size()) {
                        BondException.raiseException("mark", "INVALID_INDEX");
                    }

                    int index = Integer.parseInt(components[1]) - 1;

                    markTask(index, taskList, true, filePath);

                } else if (components[0].equalsIgnoreCase("unmark")) {

                    if (components.length == 1) {
                        BondException.raiseException("unmark", "MISSING_INDEX");
                    } else if (!isNumber(components[1])) {
                        BondException.raiseException("unmark", "INVALID_INDEX");
                    } else if (Integer.parseInt(components[1]) - 1 >= taskList.size()) {
                        BondException.raiseException("unmark", "INVALID_INDEX");
                    }

                    int index = Integer.parseInt(components[1]) - 1;

                    unmarkTask(index, taskList, true, filePath);

                } else if (components[0].equalsIgnoreCase("bye")) {

                    if (components.length != 1) {
                        BondException.raiseException("bye", "EXTRA_DETAILS");
                    }

                    System.out.println(String.format("%s\n\nBye. Hope to see you again soon! \n%s", LINE, LINE));
                    break;
                } else if (components[0].equalsIgnoreCase("delete")) {

                    if (taskList.isEmpty()) {
                        BondException.raiseException("delete", "EMPTY_LIST");
                    } else if (components.length == 1) {
                        BondException.raiseException("delete", "MISSING_INDEX");
                    } else if (!isNumber(components[1])) {
                        BondException.raiseException("delete", "INVALID_INDEX");
                    } else if (Integer.parseInt(components[1]) - 1 >= taskList.size()) {
                        BondException.raiseException("delete", "INVALID_INDEX");
                    }

                    int index = Integer.parseInt(components[1]) - 1;

                    Bond.deleteTask(index, taskList, true, filePath);
                }

            } catch (BondException e) {
                System.out.println(String.format("%s\n\n    %s\n%s\n", LINE, e.getMessage(), LINE));
                continue;
            }
        }

        scNext.close();
        System.exit(0);
    }
}

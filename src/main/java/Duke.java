import Exceptions.DukeException;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.ToDo;
import Tasks.Task.TaskType;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Duke {

    final static String INTRODUCTION = "Hello from Kewgy!\nWhat can I do for you?\nType \"bye\" to exit!";
    final static String HORIZONTAL_LINE = "____________________________________________________________";
    final static String ADD_TASK = "Got it. I've added this task to your list.";
    final static String SAVE_FILE = "src/main/java/Kewqgy.txt";

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        List<Task> userTaskList = loadTasks();

        printSingleLine(INTRODUCTION);

        String userMsg = reader.nextLine();
        while (!Objects.equals(userMsg, "bye")) {

            if (Objects.equals(userMsg, "list")) {
                System.out.println(HORIZONTAL_LINE);
                System.out.println("Here are the tasks in your list: ");
                for (int i = 1; i < userTaskList.size() + 1; i++) {
                    System.out.println(i + ": " + userTaskList.get(i - 1));
                }
                System.out.println(HORIZONTAL_LINE);

                userMsg = reader.nextLine();
                continue;
            }

            String[] userMsgParsed = userMsg.split(" ", 2);

            switch (userMsgParsed[0]) {
                case "mark":
                    if (checkValidMarkCommand(userMsgParsed, userTaskList)) {
                        System.out.println(HORIZONTAL_LINE);
                        int taskInt = Integer.parseInt(userMsgParsed[1]) - 1;
                        System.out.println("Nice! I've marked this task as done:");
                        userTaskList.get(taskInt).setDone(true);
                        updateTask(taskInt, true);
                        System.out.println(userTaskList.get(taskInt).toString());
                        System.out.println(HORIZONTAL_LINE);
                    }

                    userMsg = reader.nextLine();
                    break;
                case "unmark":
                    if (checkValidMarkCommand(userMsgParsed, userTaskList)) {
                        System.out.println(HORIZONTAL_LINE);
                        int taskInt = Integer.parseInt(userMsgParsed[1]) - 1;
                        System.out.println("OK, I've marked this task as not done yet:");
                        userTaskList.get(taskInt).setDone(false);
                        updateTask(taskInt, false);
                        System.out.println(userTaskList.get(taskInt).toString());
                        System.out.println(HORIZONTAL_LINE);
                    }

                    userMsg = reader.nextLine();
                    break;
                case "todo":
                    System.out.println(HORIZONTAL_LINE);

                    try {
                        ToDo newTodo = new ToDo(userMsg);
                        userTaskList.add(newTodo);
                        saveTask(userMsg, TaskType.T);

                        System.out.println(ADD_TASK);
                        System.out.println(newTodo);
                        System.out.println("Now you have " + userTaskList.size() + " tasks in your list.");
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }

                    System.out.println(HORIZONTAL_LINE);
                    userMsg = reader.nextLine();
                    continue;
                case "deadline":
                    System.out.println(HORIZONTAL_LINE);

                    try {
                        Deadline newDeadline = new Deadline(userMsg);
                        userTaskList.add(newDeadline);
                        saveTask(userMsg, TaskType.T);

                        System.out.println(ADD_TASK);
                        System.out.println(newDeadline);
                        System.out.println("Now you have " + userTaskList.size() + " tasks in your list.");
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }

                    System.out.println(HORIZONTAL_LINE);

                    userMsg = reader.nextLine();
                    continue;
                case "event":
                    System.out.println(HORIZONTAL_LINE);

                    try {
                        Event newEvent = new Event(userMsg);
                        userTaskList.add(newEvent);
                        saveTask(userMsg, TaskType.T);

                        System.out.println(ADD_TASK);
                        System.out.println(newEvent);
                        System.out.println("Now you have " + userTaskList.size() + " tasks in your list.");
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }

                    System.out.println(HORIZONTAL_LINE);

                    userMsg = reader.nextLine();
                    continue;
                case "delete":
                    if (checkValidMarkCommand(userMsgParsed, userTaskList)) {
                        int taskInt = Integer.parseInt(userMsgParsed[1]) - 1;

                        System.out.println(HORIZONTAL_LINE);
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(userTaskList.get(taskInt));
                        userTaskList.remove(taskInt);
                        System.out.println("Now you have " + userTaskList.size() + " tasks in your list.");
                        System.out.println(HORIZONTAL_LINE);

                        deleteTask(taskInt);
                    }

                    userMsg = reader.nextLine();
                    break;
                default:
                    printSingleLine("Unknown command!");

                    userMsg = reader.nextLine();
            }
        }

        printSingleLine("Bye! Hope to see you again soon!");

        reader.close();
    }

    public static boolean checkValidMarkCommand(String[] userMsgParsed, List<Task> userTaskList) {
        return userMsgParsed.length > 1 &&
                userMsgParsed[1].chars().allMatch(Character::isDigit) &&
                Integer.parseInt(userMsgParsed[1]) <= userTaskList.size() &&
                Integer.parseInt(userMsgParsed[1]) > 0;
    }

    public static void printSingleLine(String msg) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(msg);
        System.out.println(HORIZONTAL_LINE);
    }
    
    public static void updateTask(int taskInt, Boolean isDone) {
        try {
            File saveFile = new File(SAVE_FILE);

            // Check if file exists, if not, create new file
            if(!saveFile.exists() || saveFile.isDirectory()) {  
                saveFile.createNewFile();

                return;
            }

            List<String> tasks = new ArrayList<>();
            Scanner fileReader = new Scanner(saveFile);

            while (fileReader.hasNextLine()) {
                tasks.add(fileReader.nextLine());
            }
            
            String taskUpdate = tasks.get(taskInt);
            String[] taskUpdateParsed = taskUpdate.split(" ", 3);
            taskUpdateParsed[1] = isDone.toString();
            tasks.set(taskInt, String.join(" ", taskUpdateParsed));

            fileReader.close();

            FileWriter saveFileWriter = new FileWriter(saveFile, false);
            BufferedWriter saveFileBufferedWriter = new BufferedWriter(saveFileWriter);

            for (String taskString: tasks) {
                saveFileBufferedWriter.write(taskString + "\n");
            }

            saveFileBufferedWriter.close();
        } catch (IOException e) {
            printSingleLine("An error occurred.");
            e.printStackTrace();
        } 
    }

    public static void deleteTask(int taskInt) {
        try {
            File saveFile = new File(SAVE_FILE);

            // Check if file exists, if not, create new file
            if(!saveFile.exists() || saveFile.isDirectory()) { 
                saveFile.createNewFile();

                return;
            }

            List<String> tasks = new ArrayList<>();
            Scanner fileReader = new Scanner(saveFile);

            while (fileReader.hasNextLine()) {
                tasks.add(fileReader.nextLine());
            }
            
            tasks.remove(taskInt);
            fileReader.close();

            FileWriter saveFileWriter = new FileWriter(saveFile, false);
            BufferedWriter saveFileBufferedWriter = new BufferedWriter(saveFileWriter);

            for (String taskString: tasks) {
                saveFileBufferedWriter.write(taskString + "\n");
            }

            saveFileBufferedWriter.close();
        } catch (IOException e) {
            printSingleLine("An error occurred.");
            e.printStackTrace();
        } 
    }

    public static void saveTask(String userMsg, TaskType taskType) {
        try {
            File saveFile = new File(SAVE_FILE);

            // Check if file exists, if not, create new file
            if(!saveFile.exists() || saveFile.isDirectory()) { 
                saveFile.createNewFile();
            }

            FileWriter saveFileWriter = new FileWriter(saveFile, true);
            BufferedWriter saveFileBufferedWriter = new BufferedWriter(saveFileWriter);

            switch (taskType) {
                case T:
                    saveFileBufferedWriter.write(TaskType.T.toString() + " false " + userMsg + "\n");
                    break;
                case D:
                    saveFileBufferedWriter.write(TaskType.T.toString() + " false " + userMsg + "\n");
                    break;
                case E:
                    saveFileBufferedWriter.write(TaskType.T.toString() + " false " + userMsg + "\n");
                    break;
                default:
                    printSingleLine("Unkown Task Type: " + taskType);
                    break;
            }
            
            saveFileBufferedWriter.close();
        } catch (IOException e) {
            printSingleLine("An error occurred.");
            e.printStackTrace();
        }
    }

    public static List<Task> loadTasks() {
        try {
            File saveFile = new File(SAVE_FILE);
            
            // Check if file exists, if not, create new file
            if(!saveFile.exists() || saveFile.isDirectory()) { 
                saveFile.createNewFile();

                return new ArrayList<>();
            }

            Scanner fileReader = new Scanner(saveFile);
            List<Task> savedTasks = new ArrayList<>();

            while (fileReader.hasNextLine()) {
                Task savedTask;
                String data = fileReader.nextLine();
                // Type, isDone, Description
                String[] dataParsed = data.split(" ", 3);

                switch (TaskType.getType(dataParsed[0])) {
                    case T:
                        savedTask = new ToDo(dataParsed[2]);
                        break;
                    case D:
                        savedTask = new Deadline(dataParsed[2]);
                        break;
                    case E:
                        savedTask = new Event(dataParsed[2]);
                        break;
                    default:
                        System.out.println("Unknown Task Type: " + dataParsed[0]);
                        continue;
                }

                savedTask.setDone(Boolean.parseBoolean(dataParsed[1]));
                savedTasks.add(savedTask);
            }

            fileReader.close();

            return savedTasks;
        } catch (IOException e) {
            printSingleLine("An error occurred.");
            e.printStackTrace();
        } catch (DukeException e) { 
            printSingleLine("DukeException occurred: " + e.toString());
        }

        return new ArrayList<>();
    }
}

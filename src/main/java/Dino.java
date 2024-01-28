import java.util.ArrayList;
import java.util.Scanner;

import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Dino {
    private ArrayList<Task> taskList;

    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    TaskType taskType;
    Task task;
    private static final String FILE_PATH = "./data/duke.txt";

    public Dino() {
        this.taskList = new ArrayList<>();
        loadTasksFromFile();
    }

    public static void welcome() {
        System.out.println("Hola! I'm Dino.\nWhat are you doing here?");
    }

    public void goodbye() {
        System.out.println("Goodbye! It was nice meeting you.");
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public void listTask() {
        try {
            if (taskList.isEmpty()) {
                throw new DinoException("The list is empty.");
            }
            System.out.println("Here are the tasks that you wanted to do:");
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                int index = i + 1;
                System.out.println(index + "." + task);
            }
        } catch (DinoException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteTask(int taskNum) throws DinoException {
        if (taskNum < 1 || taskNum > taskList.size()) {
            throw new DinoException("Invalid task number. Please provide a valid task number to delete.");
        }

        Task removedTask = taskList.remove(taskNum - 1);

        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + removedTask);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    private Task createTaskFromInput(TaskType taskType, String taskDetails) throws DinoException {
        switch (taskType) {
            case TODO:
                return new ToDo(taskDetails);

            case DEADLINE:
                String[] deadlineParts = taskDetails.split("/by");
                if (deadlineParts.length != 2) {
                    throw new DinoException("Invalid input format for deadline. Please use: deadline <deadline name> /by <time>");
                }
                String deadlineName = deadlineParts[0].trim();
                String deadlineTime = deadlineParts[1].trim();
                if (deadlineName.isEmpty() || deadlineTime.isEmpty()) {
                    throw new DinoException("Deadline name and time cannot be empty.");
                }
                return new Deadline(deadlineName, deadlineTime);

            case EVENT:
                String[] eventParts = taskDetails.split("/from|/to");
                if (eventParts.length != 3) {
                    throw new DinoException("Invalid input format for event. Please use: event <event name> /from <time> /to <time>");
                }
                String eventName = eventParts[0].trim();
                String startTime = eventParts[1].trim();
                String endTime = eventParts[2].trim();
                if (eventName.isEmpty() || startTime.isEmpty() || endTime.isEmpty()) {
                    throw new DinoException("Event name, start time, and end time cannot be empty.");
                }
                return new Event(eventName, startTime, endTime);

            default:
                throw new DinoException("Unknown task type: " + taskType);
        }
    }

    private void saveTasksToFile() {
        try {
            File directory = new File("data");
            if (!directory.exists()) {
                directory.mkdirs();
            }

            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.createNewFile();
            }

            try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
                for (Task task : taskList) {
                    writer.println(task.toString());
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }


    private void loadTasksFromFile() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                System.out.println("Data file does not exist. Creating a new file.");
                return;
            }

            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String taskData = fileScanner.nextLine();
                String[] parts = taskData.split("\\|");

                if (parts.length > 0) {
                    String taskTypeString = parts[0].trim();
                    switch (taskTypeString) {
                        case "T":
                            task = createTaskFromInput(TaskType.TODO, parts[2].trim());
                            break;
                        case "D":
                            task = createTaskFromInput(TaskType.DEADLINE, parts[2].trim() + " /by " + parts[3].trim());
                            break;
                        case "E":
                            task = createTaskFromInput(TaskType.EVENT, parts[2].trim() + " /from " + parts[3].trim() + " /to " + parts[4].trim());
                            break;
                        default:
                            System.out.println("Unknown task type in file: " + taskTypeString);
                    }
                }

                if (task != null) {
                    taskList.add(task);
                } else {
                    System.out.println("Error loading task from file. Skipping invalid task.");
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        } catch (DinoException e) {
            throw new RuntimeException(e);
        }
    }


    private void handleTaskCreation(Scanner sc, TaskType taskType) {
        try {
            String taskDetails = sc.nextLine().trim();
            if (taskDetails.isEmpty()) {
                throw new DinoException("Description cannot be empty.");
            }

            addTask(createTaskFromInput(taskType, taskDetails));

            System.out.println("Okay.");
            System.out.println("  " + taskList.get(taskList.size() - 1));
            System.out.println("Now you have " + taskList.size() + " in the list.");
        } catch (DinoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void chatHere() {

        Scanner sc = new Scanner(System.in);
        String command = sc.next();

        while (true) {
            switch (command) {
                case "list":
                    listTask();
                    break;

                case "bye":
                    saveTasksToFile();
                    goodbye();
                    sc.close();
                    return;

                case "delete":
                    try {
                        int taskNum = sc.nextInt();
                        deleteTask(taskNum);
                    } catch (DinoException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case "todo":
                    taskType = TaskType.TODO;
                    handleTaskCreation(sc, taskType);
                    break;

                case "deadline":
                    taskType = TaskType.DEADLINE;
                    handleTaskCreation(sc, taskType);
                    break;

                case "event":
                    taskType = TaskType.EVENT;
                    handleTaskCreation(sc, taskType);
                    break;

                case "mark":
                    int taskNum = sc.nextInt();
                    if (taskNum > taskList.size()) {
                        System.out.println("Uh oh, we do not have a task assigned to that number.");
                    } else {
                        System.out.println("Good job on completing the task! I have checked it off the list.");
                        Task completed = taskList.get(taskNum - 1);
                        completed.markAsDone();
                    }
                    break;

                case "unmark":
                    int taskNumber = sc.nextInt();
                    if (taskNumber > taskList.size()) {
                        System.out.println("Uh oh, we do not have a task assigned to that number.");
                    } else {
                        System.out.println("Ah, I will mark it as undone. Remember to do it asap!");
                        Task missing = taskList.get(taskNumber - 1);
                        missing.markAsUndone();
                    }
                    break;

                default:
                    try {
                        throw new DinoException("I don't understand ;;");
                    } catch (DinoException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
            }
            command = sc.next();
        }
    }


    public static void main(String[] args) {
        Dino mrDino = new Dino();
        welcome();
        mrDino.chatHere();
    }
}
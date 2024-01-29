import java.util.Scanner;
import java.util.ArrayList;

import java.nio.file.Paths;
import java.nio.file.Files;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
enum TaskType {
    T, D, E
}

public class GPT {
    private static final String FILE_PATH = "./data/GPT.txt";


    public static void main(String[] args) {
        try {
            File dataFolder = new File("./data/");
            if (!dataFolder.exists()) {
                dataFolder.mkdirs();
            }
            File dataFile = new File(FILE_PATH);
            if (!dataFile.exists()) {
                dataFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error creating data folder and file: " + e.getMessage());
        }

        ArrayList<Task> tl = loadTasksFromFile();
        Scanner scn = new Scanner(System.in);
        String name = " GPT";
        String secLine = "What can I do for you?\n\n";

        System.out.println("Hello! I'm" + name);
        System.out.println(secLine);
        String s = scn.nextLine();
        //ArrayList<Task> tl = new ArrayList<>();

        while (!s.equals("bye")) {

            try {
                processCommand(s, tl);
            } catch (GPTException e) {
                System.out.println(e.getMessage());
            }

            s = scn.nextLine();
        }
            System.out.println("ByeBye. Hope to see you soon");
        }
    private static void processCommand(String command, ArrayList<Task> tl) throws GPTException {
        if (command.equals("list")) {
            for (int i = 1; i <= tl.size(); i++) {
                System.out.println(i +  ". " + tl.get(i - 1).toString());
            }
        } else if (command.startsWith("todo")) {
            processTodoCommand(command, tl);
        }else if (command.startsWith("deadline")) {
            processDeadlineCommand(command, tl);
        } else if (command.startsWith("event")) {
            processEventCommand(command, tl);
        } else if (command.startsWith("delete")) {
            processDeleteCommand(command, tl); }
        else if (command.startsWith("unmark")) {
            String[] splitInput = command.split("\\s+");
            if (splitInput[0].equals("unmark") && Integer.valueOf(splitInput[1]) <= tl.size()) {
                tl.get(Integer.valueOf(splitInput[1]) - 1).unmark();
            }
        } else if (command.startsWith("mark")) {
            String[] splitInput = command.split("\\s+");
            if (splitInput[0].equals("mark") && Integer.valueOf(splitInput[1]) <= tl.size()) {
                tl.get(Integer.valueOf(splitInput[1]) - 1).mark();
            }
        } else if (command.equals("save")) {
            saveTasksToFile(tl);
        }else {
            throw new GPTException("HEY YOU mESsEd UP!!! Your input don't make sense to me :-(");
        }
    }
    private static void processDeleteCommand(String command, ArrayList<Task> tl) throws GPTException {
        String[] splitInput = command.split("\\s+");
        if (splitInput.length < 2) {
            throw new GPTException("OIII!!! Please specify the task number to delete.");
        }
        int taskNumber = Integer.parseInt(splitInput[1]);
        if (taskNumber <= 0 || taskNumber > tl.size()) {
            throw new GPTException("OOPS!!! Task number is out of range.");
        }

        Task deletedTask = tl.remove(taskNumber - 1);
        System.out.println("Noted. I've removed this task:\n" + deletedTask);
        System.out.println("Now you have " + tl.size() + " tasks in the list.");
    }
    private static void processTodoCommand(String command, ArrayList<Task> tl) throws GPTException {
        if (command.length() < 5) {
            throw new GPTException("HEY YOU mESsEd UP!!! The description of a todo cannot be empty.");
        }
        String todoDescription = command.substring(5).trim();

        System.out.println("Got it. I've added this task:");
        Task todoTask = new Task(todoDescription, TaskType.T, false);
        tl.add(todoTask);
        System.out.println("  " + todoTask.toString());
        System.out.println("Now you have " + tl.size() + " tasks in the list.");
    }
    private static void processDeadlineCommand(String command, ArrayList<Task> tl) throws GPTException  {
        if (command.length() < 9) {
            throw new GPTException("Name or deadline date missing for deadline task");
        }
        if (!command.contains("/by")) {
            throw new GPTException("Format is wrong, please use /by to indicate deadline");
        }
        String[] splitInput = command.split("/by");
        String deadlineName = splitInput[0].substring(9).trim();
        String deadlineDate = splitInput[1].trim();
        if (deadlineDate.isEmpty() || deadlineName.isEmpty()) {
            throw new GPTException("Name or deadline date missing for deadline task");
        }
        System.out.println("Got it. I've added this task:");
        Task deadlineTask = new Task(deadlineName, TaskType.D, false, deadlineDate); //TODO
        tl.add(deadlineTask);
        System.out.println("  " + deadlineTask.toString());
        System.out.println("Now you have " + tl.size() + " tasks in the list.");
    }
    private static void processEventCommand(String command, ArrayList<Task> tl) throws GPTException {
        if (!command.contains("/from") || !command.contains("/to")|| command.length() < 6) {
            throw new GPTException("name, start date or end date missing for deadline task");
        }
        String[] splitInput = command.split("/from|/to");
        String eventName = splitInput[0].substring(6).trim();
        String eventStartDate = splitInput[1].trim();
        String eventEndDate = splitInput[2].trim();
        System.out.println("Got it. I've added this task:");
        Task eventTask = new Task(eventName, TaskType.E, false, eventStartDate, eventEndDate);
        tl.add(eventTask);
        System.out.println("  " + eventTask.toString());
        System.out.println("Now you have " + tl.size() + " tasks in the list.");
    }
    private static ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> loadedTasks = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] taskData = line.split(" \\| ");
                if (taskData.length >= 4) {
                    TaskType type = TaskType.valueOf(taskData[0]);
                    String name = taskData[1];
                    Boolean done = Integer.parseInt(taskData[2]) == 1;
                    String description = taskData[3];
                    String dateTime = taskData.length > 4 ? taskData[4] : "";
                    loadedTasks.add(new Task(name, type, done, description, dateTime));
                } else {
                    System.out.println("Warning: Ignored corrupted line in the file: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }

        return loadedTasks;
    }
    private static void saveTasksToFile(ArrayList<Task> taskList) {
        try (FileWriter fw = new FileWriter(FILE_PATH)) {
            for (Task task : taskList) {
                fw.write(task.getTaskType().name() + " | " +  task.getName()  + " | " + (task.isDone() ? 1 : 0) + " | " + task.startDate + " | " + task.endDate + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

            }


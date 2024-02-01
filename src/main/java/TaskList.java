import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class TaskList {
    static List<Task> taskList = new ArrayList<>();

    public TaskList(File saveData) {
        try {
            Scanner saveReader = new Scanner(saveData);
            while (saveReader.hasNextLine()) {
                String data = saveReader.nextLine();
                loadFromFile(data);
            }
        } catch (FileNotFoundException | CroException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateSave() {
        try {
            FileWriter saveWriter = new FileWriter("saveFile.txt");
            for (int i = 0; i < taskList.size(); i++) {
                saveWriter.write(taskList.get(i).getSaveLine());
            }
            saveWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void loadFromFile(String inputData) throws CroException {
        List<String> splitStr = new LinkedList<>(Arrays.asList(inputData.trim().split("\\s+")));
        String type = splitStr.get(0);
        int isDone = Integer.parseInt(splitStr.remove(1));
        switch (type) {
            case "T":
                addToDo(splitStr, isDone);
                break;
            case "D":
                addDeadline(splitStr, isDone);
                break;
            case "E":
                addEvent(splitStr, isDone);
                break;
        }
    }
    public static void addToTaskList(Task newTask) {
        taskList.add(newTask);
        System.out.println("-----------------------------------");
        System.out.println("added: " + newTask);
        System.out.println("-----------------------------------");
    }
    public static void addToDo(List<String> splitStr, int isDone) throws CroException {
        String description = String.join(" ", splitStr.subList(1, splitStr.size()));
        if (description.equals("")) {
            throw new CroException("description of todo cannot be empty!");
        }
        ToDo newToDo = new ToDo(description);
        if (isDone == 1) {
            newToDo.markAsDone();
        }
        addToTaskList(newToDo);
    }

    public static void addDeadline(List<String> splitStr, int isDone) throws CroException {
        int byIndex = splitStr.indexOf("/by");
        if (byIndex < 0) {
            throw new CroException("deadline not found, please include with '/by' as an indicator.");
        } else {
            String description = String.join(" ", splitStr.subList(1, byIndex));
            String deadline = String.join(" ", splitStr.subList(byIndex + 1, splitStr.size()));
            if (description.equals("") || deadline.equals("")) {
                throw new CroException("description or deadline cannot be empty!");
            }
            Deadline newDeadline = new Deadline(description, deadline);
            if (isDone == 1) {
                newDeadline.markAsDone();
            }
            addToTaskList(newDeadline);
        }
    }

    public static void addEvent(List<String> splitStr, int isDone) throws CroException {
        int fromIndex = splitStr.indexOf("/from");
        int toIndex = splitStr.indexOf("/to");
        if (fromIndex < 0 || toIndex < 0) {
            throw new CroException("event timings not found, please use /from and /to to indicate.");
        } else {
            String description = String.join(" ", splitStr.subList(1, fromIndex));
            String start = String.join(" ", splitStr.subList(fromIndex + 1, toIndex));
            String end = String.join(" ", splitStr.subList(toIndex + 1, splitStr.size()));
            if (description.equals("") || start.equals("") || end.equals("")) {
                throw new CroException("description, start or end cannot be empty!");
            }
            Event newEvent = new Event(description, start, end);
            if (isDone == 1) {
                newEvent.markAsDone();
            }
            addToTaskList(newEvent);
        }
    }
    public static void displayTasks() {
        for (int i = 0; i < taskList.size(); i++) {
            String output = String.format("%d. %s", i+1, taskList.get(i));
            System.out.println(output);
        }
        System.out.println(String.format("Now you have %d tasks in the list", taskList.size()));
    }

    public static void markTaskAsDone(List<String> splitStr) throws CroException {
        int taskNo;
        try {
            taskNo = Integer.parseInt(splitStr.get(1));
        } catch (Exception e) {
            throw new CroException("index must be a number");
        }
        if (taskNo > taskList.size()) {
            throw new CroException("Task not found!");
        } else {
            taskList.get(taskNo - 1).markAsDone();
            System.out.println("-----------------------------------");
            System.out.println("Nice! I've marked this task as done!");
            System.out.println(taskList.get(taskNo - 1));
            System.out.println("-----------------------------------");
        }
    }

    public static void markTaskAsUndone(List<String> splitStr) throws CroException {
        int taskNo;
        try {
            taskNo = Integer.parseInt(splitStr.get(1));
        } catch (Exception e) {
            throw new CroException("index must be a number");
        }
        if (taskNo > taskList.size()) {
            throw new CroException("Task not found!");
        } else {
            taskList.get(taskNo - 1).markAsUndone();
            System.out.println("-----------------------------------");
            System.out.println("OK, I've marked this task as not done yet.");
            System.out.println(taskList.get(taskNo - 1));
            System.out.println("-----------------------------------");
        }
    }

    public static void deleteEvent(List<String> splitStr) throws CroException {
        int taskNo;
        try {
            taskNo = Integer.parseInt(splitStr.get(1));
        } catch (Exception e) {
            throw new CroException("index must be a number");
        }
        if (taskNo > taskList.size()) {
            throw new CroException("Task not found!");
        } else {
            Task removedTask = taskList.remove(taskNo - 1);
            System.out.println("-----------------------------------");
            System.out.println("OK, I've removed this task.");
            System.out.println(removedTask);
            System.out.println(String.format("Now you have %d tasks in the list", taskList.size()));
            System.out.println("-----------------------------------");
        }
    }
}

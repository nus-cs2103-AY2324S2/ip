import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

public class TaskList {
    static List<Task> taskList = new ArrayList<>();
    static boolean isLoading = false;
    public TaskList(File saveData) {
        try {
            isLoading = true;
            Scanner saveReader = new Scanner(saveData);
            while (saveReader.hasNextLine()) {
                String data = saveReader.nextLine();
                loadFromFile(data);
            }
            isLoading = false;
        } catch (FileNotFoundException | CroException e) {
            System.out.println(e.getMessage());
        }
    }

    public static LocalDateTime generateDateTime(List<String> splitStr) {
        List<Integer> splitInts = splitStr.stream().map(Integer::valueOf).collect(Collectors.toList());
        return LocalDateTime.of(splitInts.get(0), splitInts.get(1), splitInts.get(2), splitInts.get(3), splitInts.get(4));
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
                addToDo(splitStr, isDone, true);
                break;
            case "D":
                addDeadline(splitStr, isDone, true);
                break;
            case "E":
                addEvent(splitStr, isDone, true);
                break;
        }
    }
    public static void addToTaskList(Task newTask) {
        taskList.add(newTask);
        if (!isLoading) {
            System.out.println("-----------------------------------");
            System.out.println("added: " + newTask);
            System.out.println("-----------------------------------");
        }
    }
    public static void addToDo(List<String> splitStr, int isDone, boolean fromSave) throws CroException {
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

    public static void addDeadline(List<String> splitStr, int isDone, boolean fromSave) throws CroException {
        int byIndex = splitStr.indexOf("/by");
        LocalDateTime deadline;
        if (byIndex < 0) {
            throw new CroException("deadline not found, please include with '/by' as an indicator.");
        } else {
            String description = String.join(" ", splitStr.subList(1, byIndex));
            try {
                if (!fromSave) {
                    deadline = generateDateTime(splitStr.subList(byIndex + 1, splitStr.size()));
                } else {
                    deadline = LocalDateTime.parse(splitStr.get(byIndex + 1));
                }
            } catch (Exception e) {
                throw new CroException("deadline must be in the format YYYY MM DD HH MM");
            }
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

    public static void addEvent(List<String> splitStr, int isDone, boolean fromSave) throws CroException {
        int fromIndex = splitStr.indexOf("/from");
        int toIndex = splitStr.indexOf("/to");
        LocalDateTime fromTime;
        LocalDateTime toTime;
        if (fromIndex < 0 || toIndex < 0) {
            throw new CroException("event timings not found, please use /from and /to to indicate.");
        } else {
            String description = String.join(" ", splitStr.subList(1, fromIndex));
            try {
                if (!fromSave) {
                    fromTime = generateDateTime(splitStr.subList(fromIndex + 1, toIndex));
                    toTime = generateDateTime(splitStr.subList(toIndex + 1, splitStr.size()));
                } else {
                    fromTime = LocalDateTime.parse(splitStr.get(fromIndex + 1));
                    toTime = LocalDateTime.parse(splitStr.get(toIndex + 1));
                }
            } catch (Exception e) {
                throw new CroException("start or end time must be in the format YYYY MM DD HH MM");
            }
            if (description.equals("")) {
                throw new CroException("description, start or end cannot be empty!");
            }
            Event newEvent = new Event(description, fromTime, toTime);
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

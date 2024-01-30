import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Luke {

    static String[] validCommands = {"bye", "list", "unmark", "mark", "todo", "event", "deadline", "delete"};

    static String directoryPath = "./data";

    static String fileName = "list.txt";

    static File file = new File (directoryPath, fileName);

    static boolean fileIsEmpty = true;

    private static boolean isCommandValid(String command) {
        for (String validCommand: validCommands) {
            if (command.equals(validCommand)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        String name = "Luke";

        ArrayList<Task> taskList = new ArrayList<Task>();

        ArrayList<String> saveTaskList = new ArrayList<String>();

        int noTasks = 0;

        try {
            File directory = file.getParentFile();
            if (!directory.exists()) {
                directory.mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Failed to save file: " + e.getMessage());
        }

        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data != "") {
                    saveTaskList.add(data);
                    noTasks++;
                }
            }
            myReader.close();
            fileIsEmpty = saveTaskList.isEmpty();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        saveTaskList.forEach(taskString -> {
           String taskType = taskString.substring(1, 2);
           switch (taskType) {
               case "T":
                   Todo todo = new Todo(taskString.substring(7).trim());
                   taskList.add(todo);
                   break;
               case "D":
                   String[] deadlineSplit = taskString.split("by: ");
                   String deadlineDescription = deadlineSplit[0].substring(7, deadlineSplit[0].length() - 1);
                   String by = deadlineSplit[1].substring(0, deadlineSplit[1].length() - 1).trim();
                   Deadline deadline = new Deadline(deadlineDescription, by);
                   taskList.add(deadline);
                   break;
               case "E":
                   String[] eventFirstSplit = taskString.split("from: ");
                   String[] eventSecondSplit = eventFirstSplit[1].split(" to: ");
                   String eventDescription = eventFirstSplit[0].substring(7, eventFirstSplit[0].length() - 1);
                   String from = eventSecondSplit[0].trim();
                   String to = eventSecondSplit[1].substring(0, eventSecondSplit[1].length() - 1).trim();
                   Event event = new Event(eventDescription, from, to);
                   taskList.add(event);
                   break;
           }
        });

        System.out.println("Hello! I'm " + name + "\nWhat can I do for you?");

        Scanner scanner = new Scanner (System.in);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            String[] splited = input.split(" ");
            String command = splited[0];
            try {
                if (!isCommandValid(command)) {
                    throw new LukeException("Invalid command/task type.");
                }

            } catch (LukeException e) {
                System.out.println(e);
            }
            switch (command) {
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < noTasks; i++) {
                        System.out.println((i + 1) + ". " + taskList.get(i).toString());
                    }
                    break;
                case "mark":
                    try {
                        int markIndex = Integer.valueOf(splited[1]);
                        if (markIndex <= 0 || markIndex > noTasks) {
                            throw new LukeException("Task does not exist. Please give a valid task number.");
                        }
                        Task markTask = taskList.get(markIndex - 1);
                        markTask.setToDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(markTask.toString());
                    } catch (LukeException e) {
                        System.out.println(e);
                    }
                    break;
                case "unmark":
                    try {
                        int unmarkIndex = Integer.valueOf(splited[1]);
                        if (unmarkIndex <= 0 || unmarkIndex > noTasks) {
                            throw new LukeException("Task does not exist. Please give a valid task number.");
                        }
                        Task unmarkTask = taskList.get(unmarkIndex - 1);
                        unmarkTask.setToNotDone();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(unmarkTask.toString());
                    } catch (LukeException e) {
                        System.out.println(e);
                    }
                    break;
                case "todo":
                    try {
                        if (input.substring(4).trim().isEmpty()) {
                            throw new LukeException("Invalid command. The description cannot be empty.");
                        }
                        Todo todo = new Todo(input.substring(5));
                        taskList.add(todo);

                        System.out.println("Got it. I've added this task:");
                        System.out.println(todo.toString());
                        noTasks++;
                        System.out.println("Now you have " + noTasks + " tasks in the list.");
                    } catch (LukeException e) {
                        System.out.println(e);
                    }
                    break;
                case "deadline":
                    try {
                        String[] deadlineSplit = input.split("/");
                        if (deadlineSplit.length < 2
                                || !deadlineSplit[1].substring(0,2).equals("by")) {
                            throw new LukeException("Invalid command. Please follow the format for deadline tasks.");
                        }
                        if (deadlineSplit[0].substring(9).trim().isEmpty()) {
                            throw new LukeException("Invalid command. The description cannot be empty.");
                        }
                        if (deadlineSplit[1].trim().length() <= 2) {
                            throw new LukeException("Invalid command. The deadline cannot be empty.");
                        }
                        String deadlineDescription = deadlineSplit[0].substring(9);
                        String by = deadlineSplit[1].substring(3);

                        Deadline deadline = new Deadline(deadlineDescription, by);
                        taskList.add(deadline);

                        System.out.println("Got it. I've added this task:");
                        System.out.println(deadline.toString());
                        noTasks++;
                        System.out.println("Now you have " + noTasks + " tasks in the list.");
                    } catch (LukeException e) {
                        System.out.println(e);
                    }
                    break;
                case "event":
                    try {
                        String[] eventSplit = input.split("/");
                        if (eventSplit.length < 3
                                || !eventSplit[1].substring(0,4).equals("from")
                                || !eventSplit[2].substring(0,2).equals("to")) {
                            throw new LukeException("Invalid command. Please follow the format for event tasks.");
                        }
                        if (eventSplit[0].substring(6).trim().isEmpty()) {
                            throw new LukeException("Invalid command. The description cannot be empty.");
                        }
                        if (eventSplit[1].trim().length() <= 4 ) {
                            throw new LukeException("Invalid command. The from section cannot be empty.");
                        }
                        if (eventSplit[2].trim().length() <= 2 ) {
                            throw new LukeException("Invalid command. The to section cannot be empty.");
                        }
                        String eventDescription = eventSplit[0].substring(6);
                        String from = eventSplit[1].substring(5);
                        String to = eventSplit[2].substring(3);
                        Event event = new Event(eventDescription, from, to);
                        taskList.add(event);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(event.toString());
                        noTasks++;
                        System.out.println("Now you have " + noTasks + " tasks in the list.");
                    } catch (LukeException e) {
                        System.out.println(e);
                    }
                    break;
                case "delete":
                    try {
                        int deleteIndex = Integer.valueOf(splited[1]);
                        if (deleteIndex <= 0 || deleteIndex > noTasks) {
                            throw new LukeException("Task does not exist. Please give a valid task number.");
                        }
                        Task deleteTask = taskList.get(deleteIndex - 1);
                        taskList.remove(deleteIndex - 1);
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(deleteTask.toString());
                        noTasks--;
                        System.out.println("Now you have " + noTasks + " tasks in the list.");
                    } catch (LukeException e) {
                        System.out.println(e);
                    }
            }
            input = scanner.nextLine();
        }
        try {
            FileWriter writer = new FileWriter(directoryPath + "/" + fileName);
            for (Task task : taskList) {
                writer.write(task.toString());
                writer.write("\n");
            }
            writer.close();
            System.out.println("Successfully saved file to local.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving.");
            e.printStackTrace();
        }
        System.out.println("Bye. Hope to see you again soon!");

    }
}

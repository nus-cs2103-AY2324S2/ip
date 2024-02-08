import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;

public class Youdon {

    // function to detect when task desc is missing
    public static void detectMissingDesc(String input) throws YoudonException.EmptyDescException {
        if ((input.equals("todo")) || (input.equals("deadline")) || (input.equals("event"))) {
            throw new YoudonException.EmptyDescException("Hey! The task description is empty!");
        }
    }

    // function to detect when command is invalid
    public static void detectInvalidCommand(String input) throws YoudonException.InvalidCommandException {
        for (validCommands command: validCommands.values()) {
            if (input.contains(command.getCommand())) {
                return;
            }
        }
        throw new YoudonException.InvalidCommandException("Sorry, I do not recognise that command.");
    }

    // save data
    public static void saveData(ArrayList<Task> taskList, String filepath) throws IOException {
        try (FileWriter writer = new FileWriter(filepath)) {
            for (Task task : taskList) {
                writer.write(task.toString() + "\n");
            }
            System.out.println("Tasklist saved!");
        } catch (IOException e) {
            System.err.println("Error! " + e.getMessage());
        }
    }

    // load data
    public static void loadData(String filepath, ArrayList<Task> taskList) throws FileNotFoundException {
        try {
            File file = new File(filepath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                // identify task type
                String taskType = data.substring(1, 2);

                if (taskType.equals("T")) {
                    // identify task description
                    int startIndex = data.indexOf("[") + 7;
                    String description = data.substring(startIndex);
                    taskList.add(new Todo(description));
                }

                if (taskType.equals("D")) {
                    // identify task description
                    int startIndex = data.indexOf("[") + 7;
                    int endIndex = data.indexOf(" (by:");
                    String description = data.substring(startIndex, endIndex).trim();
                    // identify task deadline
                    startIndex = data.indexOf("(by: ") + 5;
                    endIndex = data.indexOf(")", startIndex);
                    String deadline = data.substring(startIndex, endIndex);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
                    LocalDateTime deadlineDateTime = LocalDateTime.parse(deadline, formatter);
                    taskList.add(new Deadline(description, deadlineDateTime));
                }

                if (taskType.equals("E")) {
                    // identify task description
                    int startIndex = data.indexOf("[") + 7;
                    int endIndex = data.indexOf(" (from:");
                    String description = data.substring(startIndex, endIndex);

                    // identify task start time
                    startIndex = data.indexOf("from: ") + 6;
                    endIndex = data.indexOf(" to:", startIndex);
                    String start = data.substring(startIndex, endIndex);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
                    LocalDateTime startDateTime = LocalDateTime.parse(start, formatter);

                    // identify task end time
                    startIndex = data.indexOf(" to:") + 4;
                    endIndex = data.indexOf(")", startIndex);
                    String end = data.substring(startIndex, endIndex);
                    LocalDateTime endDateTime = LocalDateTime.parse(end, formatter);

                    taskList.add(new Event(description, startDateTime, endDateTime));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }


    public static void main(String[] args) {
        // initialise array and index
        ArrayList<Task> taskList = new ArrayList<>();
        int index = 0;

        // create save.txt if it does not exist
        String filepath = "./src/main/data/save.txt";
        File file = new File(filepath);

        try {
            // if file does not exist, create it
            if (!file.exists()) {
                file.getParentFile().mkdir();
                file.createNewFile();
                System.out.println("Save File created successfully!");
            } else {
                System.out.println("Save File already exists!");
            }
        } catch (IOException e) {
            System.out.println("Error!" + e.getMessage());
        }

        // if there is text in save.txt, load the data
        try {
            if (file.length() > 0) {
                loadData(filepath, taskList);
                index = taskList.size();
                System.out.println("Save File loaded!");
            } else {
                System.out.println("Save File is empty :(");
            }
        } catch (IOException e) {
            System.out.println("Error!" + e.getMessage());
        }

        // string constants
        String line = "----------------------------------------------------------------";
        String welcomeMsg = "Hello! I'm Youdon!\nWhat can I do for you?\n";
        String byeMsg = "Bye. Hope to see you again soon!";

        // chatbot welcome message
        System.out.println(line);
        System.out.println(welcomeMsg);
        System.out.println(line);

        // scan input
        Scanner input = new Scanner(System.in);
        String data = input.nextLine();

        // when there is input present
        while (!(data.isEmpty())) {
            // try-catch block for exceptions
            try {
                detectMissingDesc(data);
                detectInvalidCommand(data);
            } catch (YoudonException.EmptyDescException | YoudonException.InvalidCommandException e) {
                // print out error message
                System.out.println(line);
                System.out.println(e.getMessage());
                System.out.println(line);
                // update data to next input
                if (input.hasNextLine()) {
                    data = input.nextLine();
                } else {
                    data = "";
                }
            }

            // if input == "bye", print chatbot bye message
            if (data.equals("bye")) {
                System.out.println(line);
                System.out.println(byeMsg);
                System.out.println(line);
                break;
            }

            // if input == "list", return tasklist
            if (data.equals("list")) {
                System.out.println(line);
                System.out.println("Here are your tasks:");
                for (int i = 0; i < index; i++) {
                    System.out.println((i + 1) + ". " + taskList.get(i).toString());
                }
                System.out.println(line);
                // wait for next input
                if (input.hasNextLine()) {
                    data = input.nextLine();
                } else {
                    data = "";
                }
                continue;
            }

            if (data.contains(" ")) {
                // if input data has 2 parts, split into command & task number
                String[] parts = data.split(" ", 2);
                String command = parts[0];
                String task = parts[1];

                // if input == "mark", mark the specified task as done
                if (command.equals("mark")) {
                    // mark task as done in array
                    int taskNumber = Integer.parseInt(task);
                    taskList.get(taskNumber - 1).isDone = true;

                    // print out changes
                    System.out.println(line);
                    System.out.println("Nicely done! The task has been marked as done:");
                    System.out.println("  " + taskList.get(taskNumber - 1).toString());
                    System.out.println(line);
                    // save and wait for next input
                    try {
                        saveData(taskList, filepath);
                    } catch (IOException e) {
                        System.out.println("Error!" + e.getMessage());
                    }

                    if (input.hasNextLine()) {
                        data = input.nextLine();
                    } else {
                        data = "";
                    }
                    continue;
                }

                // if input == "unmark", mark the specified task as undone
                if (command.equals("unmark")) {
                    // mark task as undone in array
                    int taskNumber = Integer.parseInt(task);
                    taskList.get(taskNumber - 1).isDone = false;

                    // print out changes
                    System.out.println(line);
                    System.out.println("Okies! The task has been marked as undone:");
                    System.out.println("  " + taskList.get(taskNumber - 1).toString());
                    System.out.println(line);
                    // save and wait for next input
                    try {
                        saveData(taskList, filepath);
                    } catch (IOException e) {
                        System.out.println("Error!" + e.getMessage());
                    }

                    if (input.hasNextLine()) {
                        data = input.nextLine();
                    } else {
                        data = "";
                    }
                    continue;
                }

                // if input == "delete", delete the specified task
                if (command.equals("delete")) {
                    int taskNumber = Integer.parseInt(task);
                    // print out changes
                    System.out.println(line);
                    System.out.println("Alright! The task has been deleted:");
                    System.out.println("  " + taskList.get(taskNumber - 1).toString());
                    System.out.println(line);
                    // delete task and fix indexing
                    taskList.remove(taskNumber - 1);
                    index--;
                    // save and wait for next input
                    try {
                        saveData(taskList, filepath);
                    } catch (IOException e) {
                        System.out.println("Error!" + e.getMessage());
                    }

                    if (input.hasNextLine()) {
                        data = input.nextLine();
                    } else {
                        data = "";
                    }
                    continue;
                }

                // differentiate between type of tasks and add to tasklist
                if (command.equals("todo")) {
                    // add to tasklist
                    taskList.add(new Todo(task));
                    // print out task added
                    System.out.println(line);
                    System.out.println("Alright! Task added:\n  " + taskList.get(index).toString());
                    System.out.println("You now have " + (index + 1) + " task(s) in the list.");
                    System.out.println(line);
                    index++;
                }

                // if task has "by", split into task and deadline
                if (task.contains("/by ")) {
                    String[] chunks = task.split("/by ");
                    if (command.equals("deadline")) {
                        // add to tasklist
                        String taskDesc = chunks[0];
                        String deadline = chunks[1];
                        try {
                            // attempt to parse the string into a LocalDateTime object
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                            LocalDateTime dateTimeDeadline = LocalDateTime.parse(deadline, formatter);
                            taskList.add(new Deadline(taskDesc, dateTimeDeadline));
                            // print out task added
                            System.out.println(line);
                            System.out.println("Alright! Task added:\n  " + taskList.get(index).toString());
                            System.out.println("You now have " + (index + 1) + " task(s) in the list.");
                            System.out.println(line);
                            index++;
                        } catch (Exception e) {
                            // handle the exception if the date or time is not the correct format
                            System.out.println("Oh no! That's not a correct date or time format!");
                        }
                    }
                }

                if (task.contains("/from ")) {
                    String[] sections = task.split("/from | /to ");
                    if (command.equals("event")) {
                        // add to tasklist
                        String taskDesc = sections[0];
                        String start = sections[1];
                        String end = sections[2];
                        try {
                            // attempt to parse the strings into a LocalDateTime object
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                            LocalDateTime dateTimeStart = LocalDateTime.parse(start, formatter);
                            LocalDateTime dateTimeEnd = LocalDateTime.parse(end, formatter);
                            taskList.add(new Event(taskDesc, dateTimeStart, dateTimeEnd));
                            // print out task added
                            System.out.println(line);
                            System.out.println("Alright! Task added:\n  " + taskList.get(index).toString());
                            System.out.println("You now have " + (index + 1) + " task(s) in the list.");
                            System.out.println(line);
                            index++;
                        } catch (Exception e) {
                            // handle the exception if the date or time is not the correct format
                            System.out.println("Oh no! That's not a correct date or time format!");
                        }
                    }
                }
                // save and wait for next input
                try {
                    saveData(taskList, filepath);
                } catch (IOException e) {
                    System.out.println("Error!" + e.getMessage());
                }

                if (input.hasNextLine()) {
                    data = input.nextLine();
                } else {
                    data = "";
                }
            }
        }
    }
}
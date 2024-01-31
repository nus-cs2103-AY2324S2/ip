import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class Duke {
    // print list of stored tasks
    //Chatbot stores user commands in a fixed array
    public static ArrayList<Task> listStore = new ArrayList<>();
    public static int listCount = 0;
    public static String DIRECTORY = "data";
    public static String FILE_NAME = "duke.txt";
    public static File listFile = new File(Paths.get(DIRECTORY, FILE_NAME).toString());

    public static void printTasks(ArrayList<Task> listStore, int listCount) {
        for (int i = 0; i < listCount; i++) {
            int taskNum = i + 1;
            System.out.println(taskNum + ". " + listStore.get(i).toString());
        }
        System.out.println();
    }

    // function to mark task as done and print it
    public static void markTaskAsDone(ArrayList<Task> listStore, int taskNum) {
        listStore.get(taskNum - 1).markAsDone();
        System.out.println("Nice! I've marked this task as done:\n" + listStore.get(taskNum - 1).toString() + "\n");
    }

    //function to mark task as not done and print it
    public static void markTaskAsNotDone(ArrayList<Task> listStore, int taskNum) {
        listStore.get(taskNum - 1).markAsNotdone();
        System.out.println("OK, I've marked this task as not done yet:\n"
                + listStore.get(taskNum - 1).toString() + "\n");
    }

    //function to delete task and move remaining tasks up in the list
    public static void deleteTask(ArrayList<Task> listStore, int taskNum) {
        System.out.println("Noted. I've removed this task:\n" + listStore.get(taskNum - 1).toString());
        for (int i = taskNum - 1; i < listCount - 1; i++) {
            listStore.set(i, listStore.get(i + 1));
        }
        listCount--;
        System.out.println("Now you have " + listCount + " tasks in the list.\n");
    }

    public static void loadTasks() throws IOException {
        try {
            if (listFile.exists() && listFile.length() > 0) {
                Scanner sc = new Scanner(listFile);
                while (sc.hasNextLine()) {
                    String input = sc.nextLine();
                    String[] inputArr = input.split(" \\| ");
                    String taskType = inputArr[0];
                    String taskStatus = inputArr[1];
                    String taskDescription = inputArr[2];
                    Task newTask = null;
                    if (taskType.equals("T")) {
                        newTask = new ToDo(taskDescription);
                    } else if (taskType.equals("D")) {
                        String by = inputArr[3];
                        newTask = new Deadline(taskDescription, by);
                    } else if (taskType.equals("E")) {
                        String from = inputArr[3];
                        String to = inputArr[4];
                        newTask = new Event(taskDescription, from, to);
                    }
                    if (taskStatus.equals("1")) {
                        newTask.markAsDone();
                    }
                    listStore.add(newTask);
                    listCount++;
                }
                sc.close();
            }
        } catch (IOException e) {
            System.out.println("ChatterOOHNOO! Chatteroo can't create a new file!");
        }
    }

    public static void saveTasks() throws IOException{
        FileWriter fw = new FileWriter(Paths.get(DIRECTORY, FILE_NAME).toString());
        for (int i = 0; i < listCount; i++) {
            Task currTask = listStore.get(i);
            String taskType = currTask.getTaskType();
            String taskStatus = "";
            String taskDescription = currTask.getDescription();
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            if (currTask.isDone) {
                taskStatus = "1";
            } else {
                taskStatus = "0";
            }
            if (currTask instanceof ToDo) {
                fw.write("T | " + taskStatus + " | " + taskDescription + "\n");
            } else if (currTask instanceof Deadline) {
                taskType = "D";
                String taskBy = ((Deadline) currTask).getBy().format(dateFormat);
                fw.write(taskType + " | " + taskStatus + " | " + taskDescription + " | " + taskBy + "\n");
            } else if (currTask instanceof Event) {
                taskType = "E";
                String taskFrom = ((Event) currTask).getFrom().format(dateFormat);
                String taskTo = ((Event) currTask).getTo().format(dateFormat);
                fw.write(taskType + " | " + taskStatus + " | " + taskDescription + " | "
                        + taskFrom + " | " + taskTo + "\n");
            }
        }
        fw.close();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Hello! I'm Chatteroo\n" + "What can I do for you?\n");
        Path directoryPath = Paths.get(DIRECTORY);
        if (!Files.exists(directoryPath)) {
            Files.createDirectory(directoryPath);
        }
        if (!listFile.exists()) {
            listFile.createNewFile();
        }
        try {
            loadTasks();
        } catch (IOException e) {
            System.out.println("ChatterOOHNOO! Tasks can't be loaded from the file!");
        }
        //Chatbot echos user commands
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            try {
                // print list of stored tasks if user inputs "list"
                if (input.equals("list")) {
                    printTasks(listStore, listCount);
                } else if (input.startsWith("mark")) { // mark task as done
                    String[] inputArr = input.split(" ");
                    int taskNum = Integer.parseInt(inputArr[1]); //retrieve task number from input array
                    markTaskAsDone(listStore, taskNum);
                } else if (input.startsWith("unmark")) { // mark task as not done
                    String[] inputArr = input.split(" ");
                    int taskNum = Integer.parseInt(inputArr[1]); //retrieve task number from input array
                    markTaskAsNotDone(listStore, taskNum);
                } else if (input.startsWith("delete")) { //delete specified task
                    String[] inputArr = input.split(" ");
                    int taskNum = Integer.parseInt(inputArr[1]);
                    deleteTask(listStore, taskNum);
                } else {
                    // add user inputs to list based on what task it is accordingly
                    Task newTask = null;
                    if (input.startsWith("todo")) {
                        if (input.length() < 5) {
                            throw new Exception("ChatterOOHNOO! A todOO's description cannot be empty!");
                        }
                        input = input.substring(5);
                        newTask = new ToDo(input);
                    } else if (input.startsWith("deadline ")) {
                        if (input.length() < 10) {
                            throw new Exception("ChatterOOHNOO! A deadline's description cannot be empty!");
                        }
                        String[] inputArr = input.substring(9).split("/by");
                        input = inputArr[0];
                        String by = inputArr[1];
                        newTask = new Deadline(input, by);
                    } else if (input.startsWith("event ")) {
                        if (input.length() < 7) {
                            throw new Exception("ChatterOOHNOO! An event's description cannot be empty!");
                        }
                        input = input.substring(6);
                        String[] inputArr = input.split("/from");
                        input = inputArr[0];
                        String[] timeArr = inputArr[1].split("/to");
                        String from = timeArr[0];
                        String to = timeArr[1];
                        newTask = new Event(input, from, to);
                    } else {
                        throw new Exception("ChatterOOHNOO! Chatteroo doesn't understand what yoo mean!");
                    }
                    if (newTask != null) {
                        listStore.add(newTask);
                        listCount++;
                        System.out.println("Got it. I've added this task:\n" + newTask.toString());
                        if (listCount == 1) {
                            System.out.println("Now you have " + listCount + " task in the list.\n");
                        } else
                            System.out.println("Now you have " + listCount + " tasks in the list.\n");
                    }
                }
                input = sc.nextLine();
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("ChatterOOHNOO! Chatteroo doesn't understand what yoo mean!");
            }

        }
        saveTasks();
        //Chatbot exits
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}

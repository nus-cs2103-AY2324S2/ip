import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Duke {
    static final String name = "Dave";
    static final String horizontalLine = "____________________________________________________________\n";

    public static void main(String[] args) throws ChatbotException {

        // greeting
        System.out.println(String.format("%s\n%s: Nice to meet you, I'm the ever-helpful %s!\nHow may I be of service today?\n%s", horizontalLine, name, name, horizontalLine));

        ArrayList<Task> taskList = new ArrayList<Task>();

        // load tasks if any
        try {
            taskList = loadTasks();
        } catch (IOException exc) {
            exc.getMessage();
        }

        // read input
        Scanner sc = new Scanner(System.in);

        // user input
        while (sc.hasNext()) {
            try {
                String input = sc.nextLine();
                String[] inputArr = input.split(" ");
                
                if (input.isEmpty()) {
                    throw new InvalidInputException();
                }
                if (inputArr.length == 1 && (inputArr[0].equals("todo") || inputArr[0].equals("deadline") || inputArr[0].equals("event"))) {
                    throw new EmptyTaskException(inputArr[0]);
                } else if (inputArr[0].equals("bye")) {
                    System.out.println(String.format("%s\nDave: Thank you for your patronage, goodbye and have a nice day!\n%s",
                    horizontalLine, horizontalLine));
                    break;
                } else if (inputArr[0].equals("list")) {
                    System.out.println(horizontalLine + "\nHere are the tasks in your list:\n");
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println(String.format("%d. %s", i+1, taskList.get(i).toString()));
                    }
                    System.out.println(horizontalLine);
                } else if (inputArr[0].equals("mark") || inputArr[0].equals("unmark") || inputArr[0].equals("delete")) {
                    int taskNumber = Integer.parseInt(inputArr[1]) - 1;

                    if (inputArr[0].equals("mark")) {
                        taskList.get(taskNumber).isCompleted();
                        System.out.println(String.format("%s\nGood job! Dave will mark this task as done:\n  %s\n%s",
                        horizontalLine, taskList.get(taskNumber).toString(), horizontalLine));    
                    }
                    if (inputArr[0].equals("unmark")) {
                        taskList.get(taskNumber).isNotCompleted();
                        System.out.println(String.format("%s\nAlright, Dave believes you'll get this done eventually:\n  %s\n%s",
                        horizontalLine, taskList.get(taskNumber).toString(), horizontalLine));
                    }
                    if (inputArr[0].equals("delete")) {
                        Task toDelete = taskList.get(taskNumber);
                        taskList.remove(taskNumber);
                        // delete the task in output file
                        deleteInSaved(taskList);
                        System.out.println(String.format("%s\nDave has removed the task:\n  %s", horizontalLine, toDelete.toString()));
                        System.out.println(String.format("\nYou now have %d task(s).\n%s", taskList.size(), horizontalLine));
                    }
                } else if (inputArr[0].equals("todo") || inputArr[0].equals("deadline") || inputArr[0].equals("event")) {
                    Task newTask = new Task(input);
                    if (inputArr[0].equals("todo")) {
                        String taskName = input.substring(5);
                        newTask = new Todo(taskName);
                    }
                    if (inputArr[0].equals("deadline")) {
                        int idxDeadline = input.indexOf("/by");
                        String taskName = input.substring(9, idxDeadline - 1);
                        String deadline = input.substring(idxDeadline + "/by ".length());
                        newTask = new Deadline(taskName, deadline);
                    }
                    if (inputArr[0].equals("event")) {
                        int idxFrom = input.indexOf("/from");
                        int idxTo = input.indexOf("/to");
                        String taskName = input.substring(6, idxFrom - 1);
                        String from = input.substring(idxFrom + "/from ".length(), idxTo - 1);
                        String to = input.substring(idxTo + "/to ".length());
                        newTask = new Event(taskName, from, to);
                    }
                    taskList.add(newTask);

                    // save tasks to hard disk
                    try {
                        saveTask(newTask, taskList.size());
                    } catch (IOException exc) {
                        System.out.println("Could not save tasks.");
                    }

                    System.out.println(String.format("%s\nDave added the task:\n  %s\nYou now have %d task(s).\n%s",
                    horizontalLine, newTask.toString(), taskList.size(), horizontalLine));
                } else {
                    throw new InvalidInputException();
                }
            } catch (InvalidInputException exc) {
                System.out.println(String.format("%s\n%s\n%s", horizontalLine, exc.getMessage(), horizontalLine));
            } catch (EmptyTaskException exc) {
                System.out.println(String.format("%s\n%s\n%s", horizontalLine, exc.getMessage(), horizontalLine));
            }
        }

        sc.close();
    }

    public static ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> taskList = new ArrayList<Task>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("data/dave.txt"));
            int noOfLines = 0;
            String line = br.readLine();
            while (line != null) {
                String[] taskDescription = line.split(" \\| ");
                String taskType = taskDescription[0].split(". ")[1];
                if (taskType.equals("TODO")) {
                    taskList.add(new Todo(taskDescription[2]));
                }
                if (taskType.equals("DEADLINE")) {
                    String deadline = taskDescription[3].substring("BY ".length());
                    taskList.add(new Deadline(taskDescription[2], deadline));
                }
                if (taskType.equals("EVENT")) {
                    int idxTo = taskDescription[3].indexOf("TO");
                    String from = taskDescription[3].substring("FROM ".length(), idxTo - 1);
                    String to = taskDescription[3].substring(idxTo + "TO ".length());
                    taskList.add(new Event(taskDescription[2], from, to));
                }
                if (taskDescription[1].equals("COMPLETED")) {
                    taskList.get(taskList.size() - 1).isCompleted();
                }
                noOfLines++;
                line = br.readLine();
            }
            System.out.println(String.format("Dave has found %s existing tasks and loaded them.\n%s", noOfLines, horizontalLine));
            br.close();
            return taskList;
        } catch (IOException exc) {
            exc.getMessage();
            System.out.println(String.format("Dave did not find any existing tasks to load.\n%s", horizontalLine));
            return taskList;
        }
    }
    
    public static void saveTask(Task newTask, int listSize) throws IOException {
        File fileToWrite = new File("data/dave.txt");
        if (!fileToWrite.exists()) {
            fileToWrite.getParentFile().mkdir();
            fileToWrite.createNewFile();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter("data/dave.txt", true));
        writer.append(listSize + ". " + newTask.fileString());
        writer.newLine();
        writer.close();
    }

    // delete a task in output (if exists)
    // using a loop on saveTask method
    public static void deleteInSaved(ArrayList<Task> taskList) {
        File fileToDelete = new File("data/dave.txt");
        try {
            fileToDelete.delete();
            for (int i = 0; i < taskList.size(); i++) {
                saveTask(taskList.get(i), i++);
            }
        } catch (IOException exc) {
            System.out.println(String.format("Dave cannot find any tasks to delete."));
        }
    }
}

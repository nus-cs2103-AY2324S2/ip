import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

public class Martin {
    private static String NAME = "Martin";
    protected static ArrayList<Task> todoList = new ArrayList<>();
    private static String FILEPATH = "./data/martin.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Martin() {
        this.storage = new Storage(FILEPATH); // fixed file path for now 
        this.tasks = new TaskList(todoList);
    }

    public static void main(String[] args) {

        File martinFile;
        try {
            System.out.println("Initializing Martin...");
            martinFile = new File(FILEPATH);
            if (martinFile.exists()) {
                startUpSequence(martinFile);
            } else {
                System.out.println("File does not exist. Creating a new file.");
                if (martinFile.createNewFile()) {
                    System.out.println("File created: " + martinFile.getName());
                }
                FileWriter fw = new FileWriter(martinFile);
                fw.write("T | 1 | dummy offset\n");
                fw.close();
                startUpSequence(martinFile);
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // UI
        sayGreeting();

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String line = sc.nextLine().strip();
            String[] words = line.split(" ");
            String firstWord = words[0].toUpperCase();
            String remainingWords = line.substring(firstWord.length()).strip();
            try {
                ChatbotKeyword command = ChatbotKeyword.valueOf(firstWord);
                handleCommand(command, remainingWords);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            } catch (IOException e) {
                System.out.println("Error writing to file");
            }
        }
        sc.close();
    }

    public static void startUpSequence(File martinTxt) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(martinTxt));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("line: " + line);
                String[] lineArray = line.split(" \\| ", 3);
                System.out.println(Arrays.toString(lineArray));
                String taskType = lineArray[0];
                boolean isDone = lineArray[1].equals("1");
                String taskDescription = lineArray[2];
                switch (taskType) {
                    case "T":
                        Todo todo = new Todo(taskDescription);
                        if (isDone) {
                            todo.markAsDone();
                        }
                        todoList.add(todo);
                        break;
                    case "E":
                        String[] eventArray = taskDescription.split(" \\| ");
                        String eventDescription = eventArray[0];
                        String[] eventTime = eventArray[1].split("-");
                        String startTime = eventTime[0];
                        String endTime = eventTime[1];
                        Event event = new Event(eventDescription, startTime, endTime);
                        if (isDone) {
                            event.markAsDone();
                        }
                        todoList.add(event);
                        break;
                    case "D":
                        String[] deadlineArray = taskDescription.split(" \\| ");
                        String deadlineDescription = deadlineArray[0];
                        LocalDate deadlineTime = LocalDate.parse(deadlineArray[1]);
                        Deadline deadline = new Deadline(deadlineDescription, deadlineTime);
                        if (isDone) {
                            deadline.markAsDone();
                        }
                        todoList.add(deadline);
                        break;
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error reading file");
        }
    }

    public static void handleCommand(ChatbotKeyword command, String inputs) throws IllegalArgumentException, IOException {
        String[] inputsArray = inputs.split(" "); // second param as -1 might be a soln to bug
        switch (command) {
            case BYE:
                sayBye();
                break;
            case LIST:
                printList();
                break;
            case MARK:
                if (inputsArray.length < 1) {
                    System.out.println("Please specify a task number to mark");
                    break;
                }
                int taskToMark = Integer.parseInt(inputsArray[0]);
                todoList.get(taskToMark).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(todoList.get(taskToMark));
                rewriteFile();
                break;
            case UNMARK:
                if (inputsArray.length < 1) {
                    System.out.println("Please specify a task number to unmark");
                    break;
                }
                int taskToUnmark = Integer.parseInt(inputsArray[0]);
                todoList.get(taskToUnmark).unmarkAsDone();
                System.out.println("Okay, I've unmarked it");
                System.out.println(todoList.get(taskToUnmark));
                rewriteFile();
                break;
            case TODO:
                if (inputsArray.length < 1) {
                    throw new IllegalArgumentException("Please specify a todo description");
                }
                String todoDescription = String.join(" ", inputs);
                Todo todo = new Todo(todoDescription);
                todoList.add(todo);
                System.out.println("Got it. I've added this todo: " + todoDescription);
                appendToFile(todo.toFileString() + "\n");
                break;
            case EVENT:
                if (inputsArray.length < 3) {
                    throw new IllegalArgumentException("Please specify a event description, from, and to");
                }
                String eventDescription = String.join(" ", inputs);
                String from = inputsArray[1];
                String to = inputsArray[2];
                Event event = new Event(eventDescription, from, to);
                todoList.add(event);
                System.out.println("Got it. I've added this event: " + eventDescription);
                appendToFile(event.toFileString() + "\n");
                break;
            case DEADLINE:
                if (inputsArray.length < 2) {
                    System.out.println("Please specify a deadline description and deadline");
                    break;
                }
                String[] deadlineSplit = inputs.split(" ", 2);
                String deadlineDescription = deadlineSplit[0];
                LocalDate deadline = LocalDate.parse(deadlineSplit[1]);
                Deadline deadlineTask = new Deadline(deadlineDescription, deadline);
                todoList.add(deadlineTask);
                System.out.println("Got it. I've added this deadline: " + deadlineDescription);
                appendToFile(deadlineTask.toFileString() + "\n");
                break;
            case DELETE:
                if (inputsArray.length < 1) {
                    throw new IllegalArgumentException("Please specify a task number to delete");
                }
                int taskToDelete = Integer.parseInt(inputsArray[0]);
                Task deletedTask = todoList.remove(taskToDelete);
                System.out.println("Noted. I've removed this task:" + deletedTask);
                rewriteFile();
                break;
        }

    }

    private static void rewriteFile() throws IOException{
        try {
            FileWriter fw = new FileWriter("./data/martin.txt");
            fw.write("T | 1 | dummy offset\n");
            for (int i = 0; i < todoList.size(); i++) {
                todoList.get(i).toFileString();
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void appendToFile(String line) {
        try {
            FileWriter fw = new FileWriter("./data/martin.txt", true);
            fw.write(line);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void printList() {
        // 1-indexed todolist
        for (int i = 1; i < todoList.size(); i++) {
            System.out.println(i + "." + todoList.get(i));
        }
    }

    public static void sayGreeting() {
        System.out.println("Hello from " + NAME);
        System.out.println("What can I do for you?");
    }

    public static void sayBye() {
        System.out.println("Bye from " + NAME);
    }
}

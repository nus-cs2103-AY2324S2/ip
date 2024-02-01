import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;


public class Cortana {

    // We will build the project around the location of this class

    private static Path getBaseDir(Class<?> clazz) {
        String classFilePath = clazz.getName().replace(".", "/") + ".class";
        String classLocation = clazz.getClassLoader().getResource(classFilePath).getPath();

        Path basePath = Paths.get(classLocation).getParent();
        // go up one level
        basePath = basePath.getParent();
        return basePath.toAbsolutePath();
    }

    public static class Response {

        public static String greet(String name) {
            StringBuilder sb = new StringBuilder();
            sb.append("What's up chief? " + name + " here!\n");
            sb.append("What can I do for you?");
            return sb.toString();
        }
    
        public static String bye() {
            return "Bye. Hope to see you again soon!";
        }

        public static String addTaskSuccess(Task task, int numTasks) {
            StringBuilder sb = new StringBuilder();
            sb.append("Got it. I've added this task:\n");
            sb.append("  " + task.toString() + "\n");
            sb.append("Now you have " + numTasks + " tasks in the list.");
            return sb.toString();
        }

        public static String markTask(Task task) {
            StringBuilder sb = new StringBuilder();
            sb.append("Nice! I've marked this task as done:\n");
            sb.append("  " + task.toString());
            return sb.toString();
        }

        public static String unmarkTask(Task task) {
            StringBuilder sb = new StringBuilder();
            sb.append("OK, I've marked this task as not done yet:\n");
            sb.append("  " + task.toString());
            return sb.toString();
        }

        public static String listTasks(ArrayList<Task> tasks, int numTasks) {
            StringBuilder sb = new StringBuilder();
            sb.append("Here are the tasks in your list:\n");
            for (int i = 0; i < numTasks; i++) {
                sb.append((i + 1) + ". " + tasks.get(i).toString() + "\n");
            }
            return sb.toString();
        }
        
        public static String deleteTask(Task task, int numTasks) {
            StringBuilder sb = new StringBuilder();
            sb.append("Noted. I've removed this task:\n");
            sb.append("  " + task.toString() + "\n");
            sb.append("Now you have " + numTasks + " tasks in the list.");
            return sb.toString();
        }

    }

    enum Command {
        TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, LIST, BYE, INVALID;
    }

    private String name = "Cortana";
    private Memory memory;
    // get current file dir
    private final static String BASE_DIR = Cortana.getBaseDir(Main.class).toString();
    private final static String DATA_FOLDER = "data";
    private final static String SAVE_DIR_PATH = java.nio.file.Paths.get(BASE_DIR, DATA_FOLDER).toString();
    private final static String SAVE_FILENAME = "tasks.csv";  

    Cortana() {
        this.memory = new Memory();
    }

    public void run() {
        try {
            this.loadTaskList();
        } catch (IOException e) {
            output(e.getMessage());
        }
        output(Response.greet(this.name));
        echo();
        output(Response.bye());
    }

    public static void indent() {
        System.out.print("    ");
    }

    public static void drawLine() {
        indent();
        System.out.println("____________________________________________________________________________");
    }

    public static void output(String sentences) {
        drawLine();
        String[] arr = sentences.split("\n");
        for (String sentence : arr) {
            indent();
            System.out.println(sentence);
        }
        drawLine();
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches(); 
    }

    public static Command parseCommand(String input) {
        if (input.startsWith("todo")) {
            return Command.TODO;
        } else if (input.startsWith("deadline")) {
            return Command.DEADLINE;
        } else if (input.startsWith("event")) {
            return Command.EVENT;
        } else if (input.startsWith("mark")) {
            return Command.MARK;
        } else if (input.startsWith("unmark")) {
            return Command.UNMARK;
        } else if (input.startsWith("delete")) {
            return Command.DELETE;
        } else if (input.equals("list")) {
            return Command.LIST;
        } else if (input.equals("bye")) {
            return Command.BYE;
        } else {
            return Command.INVALID;
        }
    }

    public void validateInput(Command command, String input) throws InvalidInputException {
        if (command == Command.TODO) {
            if (input.length() <= 4) {
                throw new InvalidInputException("Please enter a valid todo! Tip: todo <description> \nMissing description");
            }
        } else if (command == Command.DEADLINE) {
            if (input.length() > 8) {
                String[] arr = input.substring(9).split("/by");
                if (arr.length != 2) {
                    throw new InvalidInputException("Please enter a valid deadline! Tip: deadline <description> /by <deadline> \nMissing /by");
                } else {
                    try {
                        LocalDateTime.parse(convertTimeString(arr[1]));
                    } catch (Exception e) {
                        throw new InvalidInputException("Please enter a valid deadline! Tip: deadline <description> /by <deadline> \nInvalid deadline");
                    }
                }
            } else {
                throw new InvalidInputException("Please enter a valid deadline! Tip: deadline <description> /by <deadline> \nMissing description");
            }
        } else if (command == Command.EVENT) {
            if (input.length() > 5) {
                String[] arr = input.substring(6).split("/from");
                if (arr.length == 2) {
                    String[] arr2 = arr[1].split("/to");
                    if (arr2.length != 2) {
                        throw new InvalidInputException("Please enter a valid event! Tip: event <description> /from <start> /to <end> \nMissing /to");
                    } else {
                        try {
                            LocalDateTime.parse(convertTimeString(arr2[0].trim()));
                            LocalDateTime.parse(convertTimeString(arr2[1].trim()));
                        } catch (Exception e) {
                            throw new InvalidInputException("Please enter a valid event! Tip: event <description> /from <start> /to <end> \nInvalid start or end time");
                        }
                    } 
                } else {
                    throw new InvalidInputException("Please enter a valid event! Tip: event <description> /from <start> /to <end> \nMissing /from");
                }
            } else {
                throw new InvalidInputException("Please enter a valid event! Tip: event <description> /from <start> /to <end> \nMissing description");
            }
        } else if (command == Command.MARK) {
            if (input.length() > 4) {
                String suffix = input.substring(5);
                if (isNumeric(suffix)) {
                    int index = Integer.parseInt(suffix) - 1;
                    if (this.memory.getNumTasks() <= index) {
                        throw new InvalidInputException("Please enter a valid number! Tip: mark <number> \nNumber out of range");
                    }
                } else {
                    throw new InvalidInputException("Please enter a valid number! Tip: mark <number> \nMissing number");
                }
            } else {
                throw new InvalidInputException("Please enter a valid number! Tip: mark <number> \nMissing number");
            }
        } else if (command == Command.UNMARK) {
            if (input.length() > 6) {
                String suffix = input.substring(7);
                if (isNumeric(suffix)) {
                    int index = Integer.parseInt(suffix) - 1;
                    if (this.memory.getNumTasks() <= index) {
                        throw new InvalidInputException("Please enter a valid number! Tip: unmark <number> \nNumber out of range");
                    }
                } else {
                    throw new InvalidInputException("Please enter a valid number! Tip: unmark <number> \nMissing number");
                }
            } else {
                throw new InvalidInputException("Please enter a valid number! Tip: unmark <number> \nMissing number");
            }
        } else if (command == Command.DELETE) {
            if (input.length() > 6) {
                String suffix = input.substring(7);
                if (isNumeric(suffix)) {
                    int index = Integer.parseInt(suffix) - 1;
                    if (this.memory.getNumTasks() <= index) {
                        throw new InvalidInputException("Please enter a valid number! Tip: delete <number> \nNumber out of range");
                    }
                } else {
                    throw new InvalidInputException("Please enter a valid number! Tip: delete <number> \nMissing number");
                }
            } else {
                throw new InvalidInputException("Please enter a valid number! Tip: delete <number> \nMissing number");
            }
        }
    }

    private static String convertTimeString(String timeString) {
        timeString = timeString.trim();
        timeString = timeString.replace("/", "-");
        String[] arr = timeString.split(" ");
        String time = arr[1].trim();
        String hour = time.substring(0, 2);
        String minute = time.substring(2, 4); 
        return arr[0] + "T" + hour + ":" + minute + ":" + "00";
    }

    public void echo() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        Command command = parseCommand(input);
        ArrayList<Task> tasks; 
        int numTasks;
        Task curr_task;
        String response;
        while (command != Command.BYE) {
            try {
                validateInput(command, input);
                switch (command) {
                case TODO:
                    curr_task = new TodoTask(input.substring(5));
                    this.memory.add(curr_task);
                    response = Response.addTaskSuccess(curr_task, this.memory.getNumTasks());
                    this.saveTaskList();
                    break;
                case DEADLINE:
                    String[] arr = input.substring(9).split("/by");
                    LocalDateTime dateTime = LocalDateTime.parse(convertTimeString(arr[1]));
                    curr_task = new DeadlineTask(arr[0].trim(), dateTime);
                    this.memory.add(curr_task);
                    response = Response.addTaskSuccess(curr_task, this.memory.getNumTasks());
                    this.saveTaskList();
                    break;
                case EVENT:
                    String[] arr2 = input.substring(6).split("/from");
                    String[] arr3 = arr2[1].split("/to");
                    LocalDateTime startDateTime = LocalDateTime.parse(convertTimeString(arr3[0].trim()));
                    LocalDateTime endDateTime = LocalDateTime.parse(convertTimeString(arr3[1].trim()));
                    curr_task = new EventTask(arr2[0].trim(), startDateTime, endDateTime);
                    this.memory.add(curr_task);
                    response = Response.addTaskSuccess(curr_task, this.memory.getNumTasks());
                    this.saveTaskList();
                    break;
                case MARK:
                    int index = Integer.parseInt(input.substring(5)) - 1;
                    curr_task = this.memory.markTask(index);
                    response = Response.markTask(curr_task);
                    this.saveTaskList();
                    break;
                case UNMARK:
                    int index2 = Integer.parseInt(input.substring(7)) - 1;
                    curr_task = this.memory.unmarkTask(index2);
                    response = Response.unmarkTask(curr_task);
                    this.saveTaskList();
                    break;
                case DELETE:
                    int index3 = Integer.parseInt(input.substring(7)) - 1;
                    curr_task = this.memory.deleteTask(index3);
                    response = Response.deleteTask(curr_task, this.memory.getNumTasks());
                    this.saveTaskList();
                    break;
                case LIST:
                    tasks = this.memory.getTasks();
                    numTasks = this.memory.getNumTasks();
                    response = Response.listTasks(tasks, numTasks);
                    break;
                default:
                    response = "I'm sorry, but I don't know what that means :-(";
                }
            } catch (IOException e) {
                response = e.getMessage();
            }
            output(response);
            input = sc.nextLine();
            command = parseCommand(input);
        }
        sc.close();
    }

    public void saveTaskList() throws IOException {
        // create directory if not exists
        File dir = new File(Cortana.SAVE_DIR_PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // create file if not exists
        File file = new File(Cortana.SAVE_DIR_PATH, Cortana.SAVE_FILENAME);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw e;
            }
        }
        ArrayList<Task> tasks = this.memory.getTasks();
        try {
            FileWriter fw = new FileWriter(file);
            for (Task task : tasks) {
                fw.write(task.exportToSave() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw e;
        }

    }

    public void loadTaskList() throws IOException {
        // Load a csv
        File file = new File(Cortana.SAVE_DIR_PATH, Cortana.SAVE_FILENAME);
        if (!file.exists()) {
            return ;
        }
        Scanner sc = new Scanner(file);
        try {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] arr = line.split(",");
                String type = arr[0];
                boolean isDone = arr[1].equals("1");
                String description = arr[2];
                Task task;
                if (type.equals("T")) {
                    task = new TodoTask(description, isDone);
                } else if (type.equals("D")) {
                    String by = arr[3];
                    LocalDateTime byDateTime = LocalDateTime.parse(by);
                    task = new DeadlineTask(description, byDateTime, isDone);
                } else {
                    String from = arr[3];
                    String to = arr[4];
                    LocalDateTime fromDateTime = LocalDateTime.parse(from);
                    LocalDateTime toDateTime = LocalDateTime.parse(to);
                    task = new EventTask(description, fromDateTime, toDateTime, isDone);
                }
                this.memory.add(task);
            }
        } catch (Exception e) {
            throw new IOException("Error reading file");
        } finally {
            sc.close();
        }
    }

}

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.*;


public class Duke {
    private static final String hRULER = "____________________________________________________________\n";
    private static boolean isNumeric(String s) {
        if (s == null) {
            return false;
        }
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        }
        return true;
    }
    private static Commands parseCommand(String s) throws IllegalArgumentException {
        String[] words = s.split(" ", 2);
        Commands res = null;
        switch(words[0]) {
        case "bye":
            res = new ByeCommand();
            break;
        case "list":
            res = new ListCommand();
            break;
        case "mark":
            res = new MarkCommand(words);
            break;
        case "unmark":
            res = new UnMarkCommand(words);
            break;
        case "delete":
            res = new DeleteCommand(words);
            break;
        case "todo":
            res = new ToDoCommand(words);
            break;
        case "deadline":
            res = new DeadlineCommand(words);
            break;
        case "event":
            res = new EventCommand(words);
            break;
        }
        if (res == null) {
            throw new IllegalArgumentException();
        }
        return res;
    }
    private Storage storage;
    private UI ui;
    private TaskList tasks;
    public Duke (String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new UI();
        this.tasks = new TaskList(storage.readFile());
    }
    private void run() {
        ui.displayIntro();
        while (true) {
            try {
                String echoInput = this.ui.receiveNextLine();
                Commands token = parseCommand(echoInput);
                token.execute(tasks, ui, storage);
            } catch (IllegalArgumentException e){
                this.ui.displayExceptionMsg(new UnknownInputException());
            } catch (DukeException e){
                this.ui.displayExceptionMsg(e);
            }
        }
    }
    public static void main(String[] args) {
        try {
            Path path = Paths.get("./data");
            if (!Files.exists(path)) {
                Files.createDirectory(path);
            }
            Path filePath = Paths.get("./data/duke.txt");
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
            Duke duke = new Duke(filePath.toString());
            duke.run();
        } catch (IOException e) {
            System.out.println("An error occur while reading file");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

//    private static void run() {
//        ArrayList<Task> storage = new ArrayList<>();
//        int currentIdx = 0;
//        Scanner sc = new Scanner(System.in);
//
//        while (true) {
//            try {
//                String echoInput = sc.nextLine();
//                Task newTask;
//
//                if (echoInput.equals("bye")) {
//                    return;
//                }
//                if (echoInput.equals("list")) {
//                    System.out.printf("%s Here are the tasks in your list:\n", hRULER);
//                    for (int i = 0; i < currentIdx; i++) {
//                        System.out.printf(" %d.%s\n", i + 1, storage.get(i).toString());
//                    }
//                    System.out.println(hRULER);
//                    continue;
//                }
//                if (echoInput.substring(0, 4).equals("mark")
//                        && isNumeric(echoInput.substring(5))) {
//                    int taskIdx = Integer.parseInt(echoInput.substring(5));
//                    if (taskIdx >= currentIdx) {
//                        throw new InvalidTaskIndexException(currentIdx);
//                    }
//                    storage.get(taskIdx - 1).markDone();
//                    continue;
//                }
//                else if (echoInput.substring(0, 4).equals("todo")) {
//                    if (echoInput.length() == 4) {
//                        throw new EmptyDescriptionException("todo");
//                    }
//                    newTask = new ToDo(echoInput.substring(5));
//                }
//                else if (echoInput.substring(0, 5).equals("event")) {
//                    if (echoInput.length() == 5) {
//                        throw new EmptyDescriptionException("event");
//                    }
//                    int startIdx = 0;
//                    int numOfSlash = 0;
//                    while (startIdx < echoInput.length()) {
//                        if (echoInput.charAt(startIdx) != '/') {
//                            startIdx++;
//                        } else {
//                            numOfSlash++;
//                            break;
//                        }
//                    }
//                    int endIdx = startIdx + 1;
//                    while (endIdx < echoInput.length()) {
//                        if (echoInput.charAt(endIdx) != '/') {
//                            endIdx++;
//                        } else {
//                            numOfSlash++;
//                            break;
//                        }
//                    }
//                    if (numOfSlash < 2) {
//                        throw new InvalidEventException();
//                    }
//                    newTask = new Event(echoInput.substring(6, startIdx),
//                            echoInput.substring(startIdx + 6, endIdx),
//                            echoInput.substring(endIdx + 4));
//                }
//                else if (echoInput.substring(0, 6).equals("unmark") &&
//                        isNumeric(echoInput.substring(7))) {
//                    int taskIdx = Integer.parseInt(echoInput.substring(7));
//                    if (taskIdx >= currentIdx) {
//                        throw new InvalidTaskIndexException(currentIdx);
//                    }
//                    storage.get(taskIdx - 1).unMarkDone();
//                    continue;
//                }
//                else if (echoInput.substring(0, 6).equals("delete") &&
//                        isNumeric(echoInput.substring(7))) {
//                    int taskIdx = Integer.parseInt(echoInput.substring(7));
//                    if (taskIdx >= currentIdx) {
//                        throw new InvalidTaskIndexException(currentIdx);
//                    }
//                    Task removed = storage.remove(taskIdx - 1);
//                    currentIdx--;
//                    System.out.printf("%s Noted. I've removed this task:\n   %s\n Now you have %d tasks in the list.\n%s",
//                            hRULER, removed, currentIdx, hRULER);
//                    continue;
//                }
//                else if (echoInput.substring(0, 8).equals("deadline")) {
//                    if (echoInput.length() == 8) {
//                        throw new EmptyDescriptionException("deadline");
//                    }
//                    int deadlineStartIdx = 0;
//                    boolean foundTime = false;
//                    while (deadlineStartIdx < echoInput.length()) {
//                        if (echoInput.charAt(deadlineStartIdx) != '/') {
//                            deadlineStartIdx++;
//                        } else {
//                            foundTime = true;
//                            break;
//                        }
//                    }
//                    if (!foundTime) {
//                        throw new InvalidDeadlineException();
//                    }
//                    newTask = new Deadline(echoInput.substring(9, deadlineStartIdx),
//                            echoInput.substring(deadlineStartIdx + 4));
//                }
//                else {
//                    throw new UnknownInputException();
//                }
//                storage.add(newTask);
//                currentIdx++;
//                System.out.printf("%s Got it. I've added this task:\n  " +
//                        " %s\n Now you have %d tasks in the list.\n%s", hRULER, newTask, currentIdx, hRULER);
//            } catch (StringIndexOutOfBoundsException e) {
//                System.out.printf("%s%s%s", hRULER, new UnknownInputException(), hRULER);
//            } catch (DukeException e) {
//                System.out.printf("%s%s%s", hRULER, e, hRULER);
//            }
//        }
//    }

//private static ArrayList<Task> readFile(File dukeFile) {
//        ArrayList<Task> res = new ArrayList<>();
//        try {
//            Scanner token = new Scanner(dukeFile);
//            while (token.hasNextLine()) {
//                String line = token.nextLine();
//                String[] splits = line.split(" [|] ");
//                Commands type = Commands.valueOf(splits[0]);
//                Task readTask = null;
//                switch (type) {
//                    case todo:
//                        readTask = new ToDo(splits[1], splits[2]);
//                        break;
//                    case deadline:
//                        readTask = new Deadline(splits[1], splits[2], splits[3]);
//                        break;
//                    case event:
//                        readTask = new Event(splits[1], splits[2], splits[3], splits[4]);
//                        break;
//                }
//                res.add(readTask);
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println("Cannot locate file please try again");
//        }
//        return res;
//    }
//private static void addToWriteFile (File dukeFile, Task task) {
//    try {
//        BufferedWriter writer = new BufferedWriter(new FileWriter(dukeFile.toString(), true));
//        writer.append(task.writeObject());
//        writer.close();
//    } catch (IOException e) {
//        System.out.println("An error has occur while writing to file");
//    }
//}

//    private static void rewriteFile (File dukeFile, ArrayList<Task> current) {
//        try {
//            BufferedWriter writer = new BufferedWriter(new FileWriter(dukeFile.toString()));
//            for (int i = 0; i < current.size(); i++) {
//                writer.write(current.get(i).writeObject());
//            }
//            writer.close();
//        } catch (IOException e) {
//            System.out.println("An error has occur while writing to file");
//        }
//    }
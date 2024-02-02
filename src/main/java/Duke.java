import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
public class Duke {
    private Parser parser;
    public Duke() {
        this.parser = new Parser();
    }
    private class Ui {
        private Scanner scanner = new Scanner(System.in);
        public String receive() {
            return scanner.nextLine();
        }
        public void display(Object n) {
            System.out.println(n.toString());
        }

    }
    private class Storage {
        private File folder = new File("data");
        private File file = new File(folder, "data.txt");
        public void check() {
            if (!folder.exists()) {
                folder.mkdir();
                file = new File(folder, "data.txt");
            }
            if (!file.exists()) {
                file = new File(folder, "data.txt");
            }
        }
        public void clear() {
            if (!folder.exists()) {
                folder.mkdir();
                file = new File(folder, "data.txt");
            }
            if (file.exists()) {
                try (PrintWriter writer = new PrintWriter(file)) {
                    writer.print(""); // This clears all lines in the file
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

        }
        public void add(String n) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.write(n);
                writer.newLine();
            } catch (IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());
            }
        }
        public void delete(int number) {
            // Read the content of the file
            ArrayList<String> lines = new ArrayList<>();
            String currentLine;
            int currLine = 0;
            while ((currentLine = read(currLine)) != null) {
                // Check if the line should be deleted
                if (currLine != number) {
                    lines.add(currentLine);
                }
                currLine = currLine + 1;
            }
            clear();
            for (String Line : lines) {
                add(Line);
            }
        }
        public String read(int n) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String currentLine = null;
                int currLine = 0;
                while (currLine <= n) {
                    currentLine = reader.readLine();
                    currLine = currLine + 1;
                }
                return currentLine;
            } catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());
                return null;
            }
        }
        public void edit(int number, String L) {
            // Read the content of the file
            ArrayList<String> lines = new ArrayList<>();
            String currentLine;
            int currLine = 0;
            while ((currentLine = read(currLine)) != null) {
                // Check if the line should be deleted
                if (currLine != number) {
                    lines.add(currentLine);
                } else {
                    lines.add(L);
                }
                currLine = currLine + 1;
            }
            clear();
            for (String Line : lines) {
                add(Line);
            }

        }
    }
    private class Parser {
        private TaskList taskList = new TaskList();
        private Ui screen = new Ui();
        private Storage storage = new Storage();
        public Parser() {
            storage.check();
            String currentLine;
            int currLine = 0;
            while ((currentLine = storage.read(currLine)) != null) {
                Matcher mTodo2 = pTodo2.matcher(currentLine);
                Matcher mEvent2 = pEvent2.matcher(currentLine);
                Matcher mDeadline2 = pDeadline2.matcher(currentLine);
                Matcher mUnmarked = pUnmarked.matcher(currentLine);
                Matcher mMarked = pMarked.matcher(currentLine);
                if (mTodo2.find()) {
                    if (mMarked.find()) {
                        Todo n = new Todo(currentLine.substring(6), true);
                        n.mark();
                        taskList.add(n);
                    } else if (mUnmarked.find()) {
                        Todo n = new Todo(currentLine.substring(6), false);
                        n.unmark();
                        taskList.add(n);
                    }
                } else if (mDeadline2.find()) {
                    int finalIndex = currentLine.indexOf(by) + by.length();
                    String dL = currentLine.substring(finalIndex);
                    // Define the format of the input string
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    LocalDateTime ldt = null;
                    try {
                        ldt = LocalDateTime.parse(dL, formatter);
                    } catch (DateTimeParseException e) {
                        screen.display("error in deadline");
                    }
                    String newInput = currentLine.substring(currentLine.indexOf(deadline) + deadline.length() - 1, currentLine.indexOf(by));

                    if (mMarked.find()) {
                        Deadline n = new Deadline(newInput, true, ldt);
                        n.mark();
                        taskList.add(n);
                    } else if (mUnmarked.find()) {
                        Deadline n = new Deadline(newInput, false, ldt);
                        n.unmark();
                        taskList.add(n);
                    }
                } else if (mEvent2.find()) {
                    int startIndex = currentLine.indexOf(from);
                    int startIndexTo = currentLine.indexOf(to);
                    String subFrom = currentLine.substring(startIndex + from.length(), startIndexTo);

                    String subTo = currentLine.substring(startIndexTo + to.length());

                    String newInput = currentLine.substring(currentLine.indexOf(event) + event.length() + 2, startIndex);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    LocalDateTime ldt = null;
                    LocalDateTime ldt2 = null;
                    try {
                        ldt = LocalDateTime.parse(subFrom, formatter);
                        ldt2 = LocalDateTime.parse(subTo, formatter);
                    } catch (DateTimeParseException e) {
                        screen.display("error in event");
                    }
                    if (mMarked.find()) {
                        Event n = new Event(newInput, true, ldt, ldt2);
                        n.mark();
                        taskList.add(n);
                    } else if (mUnmarked.find()) {
                        Event n = new Event(newInput, false, ldt, ldt2);
                        n.unmark();
                        taskList.add(n);
                    }

                }
                currLine = currLine + 1;
            }
        }
        private String mark = "mark (\\d+)";
        private String unmark = "unmark (\\d+)";
        private String delete = "delete (\\d+)";
        private String todo = "todo";
        private String deadline = "deadline";
        private String event =  "event";
        private String by = "/by";
        private String from = "/from";
        private String to = "/to";
        private String todo2 = "[T]";
        private String deadline2 = "[D]";
        private String event2 = "[E]";
        private String unmarked = "[ ]";
        private String marked = "[X]";
        private Pattern pMark = Pattern.compile(mark);
        private Pattern pUnmark = Pattern.compile(unmark);
        private Pattern pTodo = Pattern.compile(todo);
        private Pattern pDeadline = Pattern.compile(deadline);
        private Pattern pEvent = Pattern.compile(event);
        private Pattern pBy = Pattern.compile(by);
        private Pattern pFrom = Pattern.compile(from);
        private Pattern pTo = Pattern.compile(to);
        private Pattern pDelete = Pattern.compile(delete);
        private Pattern pTodo2 = Pattern.compile(todo2);
        private Pattern pDeadline2 = Pattern.compile(deadline2);
        private Pattern pEvent2 = Pattern.compile(event2);
        private Pattern pUnmarked = Pattern.compile(unmarked);
        private Pattern pMarked = Pattern.compile(marked);
        private boolean ended = false;
        public boolean isEnded() {
            return ended;
        }
        public void interpret() {
            String input = screen.receive();
            Matcher mMark = pMark.matcher(input);
            Matcher mUnmark = pUnmark.matcher(input);
            Matcher mTodo = pTodo.matcher(input);
            Matcher mDeadline = pDeadline.matcher(input);
            Matcher mEvent = pEvent.matcher(input);
            Matcher mBy = pBy.matcher(input);
            Matcher mFrom = pFrom.matcher(input);
            Matcher mTo = pTo.matcher(input);
            Matcher mDelete = pDelete.matcher(input);
            if (input.equals("reset")) {
                taskList.clear();
                storage.clear();
                screen.display("List cleared!");
            } else if (input.equals("bye")) {
                screen.display("Bye. Hope to see you again soon!");
                ended = true;
            } else if (input.equals("list")) {
                if (taskList.length() == 0) {
                    screen.display("You have no tasks in your list!");
                } else {
                    screen.display("Here are your tasks in your list:");
                    for (int x = 0; x < taskList.length(); x++) {
                        Task item = taskList.get(x);
                        int numeric = x + 1;
                        System.out.println(numeric + "." + item.toString());
                    }

                }
            } else if (mDelete.find()) {
                String captured = mDelete.group(1);
                int number = Integer.parseInt(captured);
                if (number > 0 && number <= taskList.length()) {
                    Task t = taskList.delete(number-1);
                    screen.display("OK! I have deleted this task:");
                    screen.display(t);
                    storage.delete(number-1);
                } else {
                    screen.display("Please input a valid number.");
                }

            } else if (mUnmark.find()) {
                String captured = mUnmark.group(1);
                int number = Integer.parseInt(captured);
                Task t;
                if (number > 0 && number <= taskList.length()) {
                    t = taskList.get(number - 1);
                    t.unmark();
                    screen.display("Oh no! I have marked this as not done:");
                    screen.display(t);
                    storage.edit(number-1, t.export());
                } else {
                    screen.display("Please input a valid number.");
                }
            } else if (mMark.find()) {
                String captured = mMark.group(1);
                int number = Integer.parseInt(captured);
                Task t;
                if (number > 0 && number <= taskList.length()) {
                    t = taskList.get(number -1);
                    t.mark();
                    screen.display("Nice! I have marked this as done:");
                    screen.display(t);
                    storage.edit(number-1, t.export());
                } else {
                    screen.display("Please input a valid number.");
                }
            } else if (mTodo.find()) {
                String newInput = input.replace(todo, "");
                Todo n = new Todo(newInput, false);
                if (newInput.trim().equals("")) {
                    screen.display("Task cannot be empty!");
                } else {
                    taskList.add(n);
                    screen.display("OK, I have added this task :");
                    screen.display(n);
                    screen.display("You now have " + taskList.length() + " items in the list.");
                    storage.add(n.export());
                }
            } else if (mEvent.find()) {
                if (mFrom.find() && mTo.find()) {
                    int startIndex = input.indexOf(from);
                    int startIndexTo = input.indexOf(to);

                    String subFrom = input.substring(startIndex + from.length(), startIndexTo).trim();
                    String subTo = input.substring(startIndexTo + to.length()).trim();
                    String newInput = input.substring(input.indexOf(event) + event.length(), startIndex);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    LocalDateTime ldt;
                    LocalDateTime ldt2;
                    try {
                        // Parse the string into a LocalDate object
                        ldt = LocalDateTime.parse(subFrom, formatter);
                        ldt2 = LocalDateTime.parse(subTo, formatter);
                    } catch (DateTimeParseException e) {
                        // Handle parsing exceptions
                        screen.display("Please enter a valid date/time");
                        return;
                    }
                    if (newInput.trim().equals("")) {
                        screen.display("Task cannot be empty!");
                    } else {
                        Event n = new Event(newInput, false, ldt, ldt2);
                        taskList.add(n);
                        screen.display("OK, I have added this task :");
                        screen.display(n);
                        screen.display("You now have " + taskList.length() + " items in the list.");
                        storage.add(n.export());
                    }
                } else {
                    screen.display("pls input your start and end of the event.");
                }
            } else if (mDeadline.find()) {
                if (mBy.find()) {
                    int finalIndex = input.indexOf(by) + by.length();
                    String dL = input.substring(finalIndex);
                    String newInput = input.substring(input.indexOf(deadline) + deadline.length(), input.indexOf(by));
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" yyyy-MM-dd HH:mm");
                    LocalDateTime ldt;
                    try {
                        // Parse the string into a LocalDate object
                        ldt = LocalDateTime.parse(dL, formatter);
                    } catch (DateTimeParseException e) {
                        screen.display("Please enter a valid date/time");
                        return;
                    }
                    if (newInput.trim().equals("")) {
                        screen.display("Task cannot be empty!");
                    } else {
                        Deadline n = new Deadline(newInput, false, ldt);
                        taskList.add(n);
                        screen.display("OK, I have added this task :");
                        screen.display(n);
                        screen.display("You now have " + taskList.length() + " items in the list.");
                        storage.add(n.export());
                    }
                } else {
                    screen.display("please include a deadline");
                }

            } else {
                screen.display("Sorry, no idea what u talking about lulz");
            }

        }

    }
    private class TaskList {
        private ArrayList<Task> taskList = new ArrayList<>();
        private int length = 0;
        public TaskList() {
        }
        public void clear() {
            taskList.clear();
            length = 0;
        }
        public void add(Task t) {
            taskList.add(t);
            length = length + 1;
        }
        public Task delete(int numberToDelete) {
            if (numberToDelete < length && numberToDelete >= 0) {
                Task t = taskList.remove(numberToDelete);
                length = length - 1;
                return t;
            }
            return null;
        }
        public Task get(int numberToGet) {
            if (numberToGet < length && numberToGet >= 0) {
                return taskList.get(numberToGet);

            }
            return null;
        }
        public int length() {
            return length;
        }

    }
    private static class Task {
        private String action;
        private Boolean isDone;



        public Task(String action, Boolean isDone) {
            this.action = action;
            this.isDone = isDone;
        }
        @Override
        public String toString() {
            if (isDone) {
                return "[X]" + action;
            } else {
                return "[ ]" + action;
            }
        }
        public void mark() {
            isDone = true;
        }
        public void unmark() {
            isDone = false;
        }

        public String export() {
            return toString();
        }
    }
    private static class Event extends Task {
        private LocalDateTime from;
        private LocalDateTime to;
        public Event(String input, boolean isDone, LocalDateTime from, LocalDateTime to) {
            super(input, isDone);
            this.from = from;
            this.to = to;
        }
        @Override
        public String toString() {
            String s = super.toString();
            return "[E]" + s + "(from " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:ss")).replace("T", " ") + " to " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:ss")).replace("T", " ") + ")";
        }
        @Override
        public String export() {
            String s = super.toString();
            return "[E]" + s + "/from" + from.toString().replace("T", " ") + "/to" + to.toString().replace("T" , " ") ;
        }
    }
    private static class Todo extends Task {
        public Todo(String input, boolean isDone) {
            super(input, isDone);
        }
        @Override
        public String toString() {
            String s = super.toString();
            return "[T]" + s;
        }
        @Override
        public String export() {
            return toString();
        }
    }
    private static class Deadline extends Task {
        private LocalDateTime by;
        public Deadline(String input, boolean isDone, LocalDateTime by) {
            super(input, isDone);
            this.by = by;
        }
        @Override
        public String toString() {
            String s = super.toString();
            return "[D]" + s + "(by:" + by.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:ss")).replace("T", " ") +")";
        }

        @Override
        public String export() {
            String s = super.toString();
            return "[D]" + s + "/by" + by.toString().replace("T", " ") ;
        }

    }
    public void run() {
        parser.screen.display("Hello! I'm Dukey.");
        parser.screen.display("What can I do for you?");
        while (true && !parser.isEnded()) {
            parser.interpret();
        }
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}

import com.sun.source.util.TaskEvent;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Harvard {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Harvard(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        Parser parser = new Parser(storage, tasks, ui);
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String echoInput = scanner.nextLine();

            if (echoInput.equals("bye")) {
                break;
            }

            parser.parse(echoInput);
        }
        ui.showGoodbye();
    }

    public static void main(String[] args) {
        new Harvard(System.getProperty("user.dir") + "/data/harvard.txt").run();
    }

    public class Ui {
        public Ui() {
        }

        public void showWelcome() {
            String initial = "____________________________________________________________\n"
                + "Hello! I'm Harvard\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";
            System.out.println(initial);
        }

        public void showGoodbye() {
            String exit = "____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n";
            System.out.println(exit);
        }

        public void printAddTask(Task task, int tasksLeft) {
            System.out.println("____________________________________________________________");
            System.out.println("Got it. I've added this task:");
            System.out.println(task.toString());
            System.out.println("Now you have " + tasksLeft + " tasks in the list.");
            System.out.println("____________________________________________________________\n");
        }

        public void printDeleteTask(Task task, int tasksLeft) {
            System.out.println("____________________________________________________________");
            System.out.println("Noted. I've removed this task:");
            System.out.println(task.toString());
            System.out.println("Now you have " + tasksLeft + " tasks in the list.");
            System.out.println("____________________________________________________________\n");
        }

        public void printMark(Task task) {
            System.out.println("____________________________________________________________");
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(task.toString());
            System.out.println("____________________________________________________________\n");
        }

        public void printUnmark(Task task) {
            System.out.println("____________________________________________________________");
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(task.toString());
            System.out.println("____________________________________________________________\n");
        }

        public void printUnrecognisedCommand() {
            System.out.println("____________________________________________________________");
            System.out.println("Bro... Idk what that is man.");
            System.out.println("____________________________________________________________");
        }

        public void printTasks(TaskList tasks) {
            System.out.println("____________________________________________________________");
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.getSize(); i++) {
                System.out.println(i + 1 + ". " + tasks.printString(i));
            }
            System.out.println("____________________________________________________________\n");
        }

    }

    public class Storage {
        String filePath;
        public Storage(String fP) {
            this.filePath = fP;
        }

        public BufferedReader load() {
            try {
                BufferedReader buffReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/data/harvard.txt"));
                return buffReader;
            } catch (FileNotFoundException ex) {
                CreateTextFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return null;
        }


        public void CreateTextFile() {
            try {
                File file = new File(System.getProperty("user.dir") + "/data/harvard.txt");
                if (file.getParentFile().mkdir()) {
                    file.createNewFile();
                } else {
                    throw new IOException("Failed to create directory " + file.getParent());
                }
            } catch (IOException e) {

            }
        }

        public void store(TaskList tasks) {
            String textToSave = "";
            for (int i = 0; i < tasks.getSize(); i++) {
                textToSave += tasks.getTask(i).saveString() + "\n";
            }
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + "/data/harvard.txt"));
                writer.write(textToSave);
                writer.close();
            } catch (Exception e) {

            }
        }
    }

    public class TaskList {
        List<Task> taskList = new ArrayList<>();

        public TaskList(BufferedReader br) {
            populateTaskList(br);
        }

        public TaskList() {
        }

        public String printString(int index) {
            return taskList.get(index).toString();
        }

        public int getSize() {
            return taskList.size();
        }

        public Task getTask(int index) {
            return this.taskList.get(index);
        }

        public void delete(int index) {
            taskList.remove(index);
        }

        public void add(Task task) {
            taskList.add(task);
        }

        public void mark(int index) {
            this.taskList.get(index).mark();
        }

        public void unmark(int index) {
            this.taskList.get(index).unmark();
        }
        public void populateTaskList(BufferedReader buffReader) {
            try {
                String line;
                while (( line = buffReader.readLine()) != null) {
                    String taskType = line.split(",")[0];
                    Boolean isDone =  line.split(",")[1].equals("0") ? false : true;
                    if (taskType.equals("T") ) {
                        taskList.add(new Todo(line.split(",")[2], isDone));
                    } else if (taskType.equals("D")) {
                        taskList.add(new Deadline(line.split(",")[2], LocalDate.parse(line.split(",")[3]), isDone));
                    } else {
                        taskList.add(new Event(line.split(",")[2], LocalDate.parse(line.split(",")[3]), LocalDate.parse(line.split(",")[4]), isDone));
                    }
                }
            } catch (IOException e) {

            }

        }

    }

    public static class Parser {
        private Storage storage;
        private TaskList tasks;
        private Ui ui;
        public Parser(Storage s, TaskList tL, Ui ui) {
            this.storage = s;
            this.tasks = tL;
            this.ui = ui;
        }

        public void parse(String commandLine) {
            String command = commandLine.split(" ")[0];
            try {
                if (!command.equals("list") && !command.equals("todo") && !command.equals("deadline") &&
                        !command.equals("event") && !command.equals("mark") && !command.equals("unmark") &&
                            !command.equals("delete")) {
                    throw new HarvardException("Bro... Idk what that is man.");
                }
            } catch (HarvardException e) {
                this.ui.printUnrecognisedCommand();
            }

            if (command.equals("list")) {
                this.ui.printTasks(tasks);
            }

            if (command.equals("delete")) {
                int index = Integer.parseInt(commandLine.split(" ")[1]);

                this.ui.printDeleteTask(tasks.getTask(index - 1), tasks.getSize() - 1);
                this.tasks.delete(index - 1);
            }

            if (command.equals("todo")) {
                try {
                    if (commandLine.split(" ").length == 1) {
                        throw new HarvardException("Wow that's awkward... Please enter a description for todo!");
                    }
                    Todo todoTask = new Todo(commandLine.substring(commandLine.indexOf(' ') + 1));
                    this.tasks.add(todoTask);
                    this.ui.printAddTask(todoTask, tasks.getSize());
                } catch (HarvardException e) {
                    System.out.println("____________________________________________________________");
                    System.out.println(e.getMessage());
                    System.out.println("____________________________________________________________");
                }
            }

            if (command.equals("deadline")) {
                String[] commandItems = commandLine.split(" /by ");
                String desc = commandItems[0].substring(commandItems[0].indexOf(' ')+1);
                LocalDate by = LocalDate.parse(commandItems[1]);

                Deadline deadlineTask = new Deadline(desc, by);
                this.tasks.add(deadlineTask);
                this.ui.printAddTask(deadlineTask, tasks.getSize());
            }

            if (command.equals("event")) {
                String[] commandItems = commandLine.split(" /from ");
                String desc = commandItems[0].substring(commandItems[0].indexOf(' ')+1);
                String[] commandItems2 = commandItems[1].split(" /to ");
                LocalDate from = LocalDate.parse(commandItems2[0]);
                LocalDate to = LocalDate.parse(commandItems2[1]);

                Event eventTask = new Event(desc, from, to);
                this.tasks.add(eventTask);
                this.ui.printAddTask(eventTask, tasks.getSize());
            }

            if (command.equals("mark") || command.equals("unmark")) {
                int index = Integer.parseInt(commandLine.split(" ")[1]) - 1;

                if (command.equals("mark")) {
                    this.tasks.mark(index);
                    this.ui.printMark(this.tasks.getTask(index));
                } else {
                    this.tasks.unmark(index);
                    this.ui.printUnmark(this.tasks.getTask(index ));
                }

            }

            this.storage.store(this.tasks);
        }
    }

    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        @Override
        public String toString() {
            return this.getStatusIcon() + " " + this.getDescription();
        }

        public String getStatusIcon() {
            return (isDone ? "[X]" : "[ ]"); // mark done task with X
        }

        public void mark() {
            this.isDone = true;
        }

        public void unmark() {
            this.isDone = false;
        }

        public String getDescription() {
            return this.description;
        }

        public String saveString() {
            return null;
        }
    }

    public static class Todo extends Task {

        public Todo(String description) {
            super(description);
        }

        public Todo(String description, boolean isDone) {
            super(description);
            if (isDone) super.mark();

        }
        @Override
        public String saveString() {
            return "T," + (this.isDone ? "1," : "0,") + super.getDescription();
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    public static class Deadline extends Task {

        protected LocalDate by;

        public Deadline(String description, LocalDate by) {
            super(description);
            this.by = by;
        }

        public Deadline(String description, LocalDate by, Boolean isDone) {
            super(description);
            this.by = by;
            if (isDone) super.mark();
        }

        @Override
        public String saveString() {
            return "D," + (super.isDone ? "1," : "0,") + super.getDescription() + "," +
                    this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
    }

    public static class Event extends Task {

        protected LocalDate from;
        protected LocalDate to;

        public Event(String description, LocalDate from, LocalDate to) {
            super(description);
            this.from = from;
            this.to = to;
        }

        public Event(String description, LocalDate from, LocalDate to, Boolean isDone) {
            super(description);
            this.from = from;
            this.to = to;
            if (isDone) super.mark();
        }

        @Override
        public String saveString() {
            return "E," + (super.isDone ? "1," : "0,") + super.getDescription() + "," +
                    this.from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "," +
                    this.to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        @Override
        public String toString() {
            return "[E]" + super.toString() + " (from: " +
                    this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                    " to: " +
                    this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
    }

    public static class HarvardException extends Throwable {
        public HarvardException(String errorMessage) {
            super(errorMessage);
        }
    }


}


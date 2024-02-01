import com.sun.source.util.TaskEvent;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Harvard {
    public static void main(String[] args) throws HarvardException {
        String initial = "____________________________________________________________\n"
                + "Hello! I'm Harvard\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(initial);

        List<Task> tasks = new ArrayList<Task>();

        // Read from the text file
        tasks = GetTasks();




            while (true) {
                WriteTasks(tasks);
            Scanner scanner = new Scanner(System.in);
            String echoInput = scanner.nextLine();

            if (echoInput.equals("bye")) {
                break;
            }

            String command = echoInput.split(" ")[0];

            try {
                if (!command.equals("list") && !command.equals("todo") && !command.equals("deadline") &&
                        !command.equals("event") && !command.equals("mark") && !command.equals("unmark") &&
                            !command.equals("delete")) {
                    throw new HarvardException("Bro... Idk what that is man.");
                }
            } catch (HarvardException e) {
                System.out.println("____________________________________________________________");
                System.out.println(e.getMessage());
                System.out.println("____________________________________________________________");
            }

            if (command.equals("delete")) {
                int index = Integer.parseInt(echoInput.split(" ")[1]);
                Task targetTask = tasks.get(index - 1);

                System.out.println("____________________________________________________________");
                System.out.println("Noted. I've removed this task:");
                System.out.println(targetTask.toString());
                tasks.remove(index - 1);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("____________________________________________________________\n");
                continue;
            }


            if (command.equals("todo")) {
                try {
                    if (echoInput.split(" ").length == 1) {
                        throw new HarvardException("Wow that's awkward... Please enter a description for todo!");
                    }
                    Todo todoTask = new Todo(echoInput.substring(echoInput.indexOf(' ') + 1));
                    tasks.add(todoTask);
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(todoTask.toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________\n");
                } catch (HarvardException e) {
                    System.out.println("____________________________________________________________");
                    System.out.println(e.getMessage());
                    System.out.println("____________________________________________________________");
                }

            }

            if (command.equals("deadline")) {
                String[] commandItems = echoInput.split(" /by ");
                String desc = commandItems[0].substring(commandItems[0].indexOf(' ')+1);
                LocalDate by = LocalDate.parse(commandItems[1]);

                Deadline deadlineTask = new Deadline(desc, by);
                tasks.add(deadlineTask);
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println(deadlineTask.toString());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("____________________________________________________________\n");
            }

            if (command.equals("event")) {
                String[] commandItems = echoInput.split(" /from ");
                String desc = commandItems[0].substring(commandItems[0].indexOf(' ')+1);
                String[] commandItems2 = commandItems[1].split(" /to ");
                LocalDate from = LocalDate.parse(commandItems2[0]);
                LocalDate to = LocalDate.parse(commandItems2[1]);


                Event eventTask = new Event(desc, from, to);
                tasks.add(eventTask);
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println(eventTask.toString());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("____________________________________________________________\n");
            }

            if (command.equals("mark") || command.equals("unmark")) {
                int index = Integer.parseInt(echoInput.split(" ")[1]);
                Task targetTask = tasks.get(index - 1);

                System.out.println("____________________________________________________________");
                if (command.equals("mark")) {
                    targetTask.mark();
                    System.out.println("Nice! I've marked this task as done:");
                } else {
                    targetTask.unmark();
                    System.out.println("OK, I've marked this task as not done yet:");
                }

                System.out.println(targetTask.toString());
                System.out.println("____________________________________________________________\n");
                continue;
            }

            if (command.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i + 1 + "." + tasks.get(i).toString());
                }
                System.out.println("____________________________________________________________\n");
            }

        }

        String exit = "____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n";
        System.out.println(exit);
    }
    public static List<Task> GetTasks() {
        try {
            BufferedReader buffReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/data/harvard.txt"));
            List<Task> tasks = new ArrayList<>();
            String line;
            while (( line = buffReader.readLine()) != null) {

                String taskType = line.split(",")[0];
                Boolean isDone =  line.split(",")[1].equals("0") ? false : true;
                if (taskType.equals("T") ) {
                    tasks.add(new Todo(line.split(",")[2], isDone));
                } else if (taskType.equals("D")) {
                    tasks.add(new Deadline(line.split(",")[2], LocalDate.parse(line.split(",")[3]), isDone));
                } else {
                    tasks.add(new Event(line.split(",")[2], LocalDate.parse(line.split(",")[3]), LocalDate.parse(line.split(",")[4]), isDone));
                }

            }
            return tasks;
        } catch (FileNotFoundException ex) {
            CreateTextFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static void CreateTextFile() {
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

    public static void WriteTasks(List<Task> tasks) {
        String textToSave = "";
        for (int i = 0; i < tasks.size(); i++) {
            textToSave += tasks.get(i).saveString() + "\n";
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + "/data/harvard.txt"));
            writer.write(textToSave);
            writer.close();
        } catch (Exception e) {

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

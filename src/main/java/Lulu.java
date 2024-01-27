import exceptions.InvalidCommandException;
import exceptions.InvalidDateException;
import exceptions.InvalidSlashParameterException;
import exceptions.InvalidStatusUpdateException;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Lulu {
    private enum Commands {
        LIST,
        MARK,
        UNMARK,
        DELETE,
        TODO,
        DEADLINE,
        EVENT,
        FIND;
    }
    private List<Task> items;
    private String fileName = "src/main/java/data/lulu.txt";
    private File file = new File(this.fileName);
    private Path path = Path.of(this.fileName);

    public Lulu() {
        this.items = new ArrayList<>();
    }

    public void start() {
        try {
            Scanner scanner = new Scanner(this.file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split(",");
                if (words[0].equals("todo")) {
                    Todo todo = new Todo(words[1]);
                    if (words[2].equals("true")) {
                        todo.updateStatus(true);
                    }
                    this.items.add(todo);
                } else if (words[0].equals("deadline")) {
                    Deadline deadline = new Deadline(words[1], LocalDate.parse(words[2]));
                    if (words[3].equals("true")) {
                        deadline.updateStatus(true);
                    }
                    this.items.add(deadline);
                } else if (words[0].equals("event")) {
                    Event event = new Event(words[1], LocalDate.parse(words[2]), LocalDate.parse(words[3]));
                    if (words[4].equals("true")) {
                        event.updateStatus(true);
                    }
                    this.items.add(event);
                }
            }
        } catch (FileNotFoundException e) {
            print("Invalid file path provided, session will not be saved.");
        } catch (InvalidStatusUpdateException e) {
            print (e.getMessage());
        }
        print("Hello! I'm Lulu \n\tWhat can I do for you?");
    }

    public void exit() {
        print("Bye. Hope to see you again soon!");
    }

    public void respond() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine().strip();
            String firstWord = input.split(" ")[0];
            try {
                if (input.toLowerCase().equals("bye")) {
                    break;
                } else if (input.toLowerCase().equals("list")) {
                    manageTasks(Commands.LIST, input);
                } else if (firstWord.equals("mark")) {
                    manageTasks(Commands.MARK, input);
                } else if (firstWord.equals("unmark")) {
                    manageTasks(Commands.UNMARK, input);
                } else if (firstWord.equals("delete")) {
                    manageTasks(Commands.DELETE, input);
                } else if (firstWord.equals("todo")) {
                    addTasks(Commands.TODO, input);
                } else if (firstWord.equals("deadline")) {
                    addTasks(Commands.DEADLINE, input);
                } else if (firstWord.equals("event")) {
                    addTasks(Commands.EVENT, input);
                } else if (firstWord.equals("find")) {
                    manageTasks(Commands.FIND, input);
                } else {
                    throw new InvalidCommandException();
                }
            } catch (InvalidCommandException e) {
                print("Sorry, I dont think I quite understood what you meant...");
            }
        }
    }

    public void manageTasks(Commands command, String input) throws InvalidCommandException {
        switch (command) {
            case LIST:
                list();
                break;
            case MARK:
                mark(input);
                break;
            case UNMARK:
                unmark(input);
                break;
            case DELETE:
                delete(input);
                break;
            case FIND:
                find(input);
                break;
            default:
                throw new InvalidCommandException();
        }
    }

    public void addTasks(Commands command, String input) throws InvalidCommandException {
        switch (command) {
            case TODO:
                todo(input);
                break;
            case DEADLINE:
                deadline(input);
                break;
            case EVENT:
                event(input);
                break;
            default:
                throw new InvalidCommandException();
        }
    }

    public void list() {
        print("Here are the tasks in your list:");
        for (int i = 0; i < this.items.size(); i++) {
            String output = (i + 1) + "." + this.items.get(i);
            print(output);
        }
    }

    public void mark(String input) {
        String[] words = input.split(" ");
        if (words.length <= 1) {
            print("I didn't quite understand, could you complete your command with appropriate arguments?");
            return;
        }
        int index = Integer.valueOf(words[1]) - 1;
        if (index >= this.items.size() || index < 0) {
            print("Oops! You did not give a valid index.");
            return;
        }
        Task task = this.items.get(index);
        try {
            task.updateStatus(true);
            print("Nice! I've marked this task as done:");
            print(task);

            // save
            String line = readLine(index);
            String newLine = line.substring(0, line.length() - 5) + "true";
            updateLine(index, newLine);
        } catch (InvalidStatusUpdateException e) {
            print("This task was already marked!");
            print(task);
        }
    }

    public void unmark(String input) {
        String[] words = input.split(" ");
        if (words.length <= 1) {
            print("I didn't quite understand, could you complete your command with appropriate arguments?");
            return;
        }
        int index = Integer.valueOf(words[1]) - 1;
        if (index >= this.items.size() || index < 0) {
            print("Oops! You did not give a valid index.");
            return;
        }
        Task task = this.items.get(index);
        try {
            task.updateStatus(false);
            print("OK, I've marked this task as not done yet:");
            print(task);

            // save
            String line = readLine(index);
            String newLine = line.substring(0, line.length() - 4) + "false";
            updateLine(index, newLine);
        } catch (InvalidStatusUpdateException e) {
            print("This task was already unmarked!");
            print(task);
        }
    }

    public void delete(String input) {
        String[] words = input.split(" ");
        if (words.length <= 1) {
            print("I didn't quite understand, could you complete your command with appropriate arguments?");
            return;
        }
        int index = Integer.valueOf(words[1]) - 1;
        if (index >= this.items.size() || index < 0) {
            print("Oops! You did not give a valid index.");
            return;
        }
        Task task = this.items.remove(index);

        // save
        deleteLine(index);

        print("Noted. I've removed this task:");
        print("\t" + task);
        print(String.format("Now you have %d tasks in the list.", this.items.size()));
    }

    public void find(String input) {
        String[] words = input.split(" ");
        if (words.length <= 2) {
            print("I didn't quite understand, could you complete your command with appropriate arguments?");
            return;
        }

        String taskType = words[1].toLowerCase();
        LocalDate date = LocalDate.parse(words[2]);
        String formattedDate = date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));

        if (taskType.equals("deadline")) {
            print("Below are deadlines that are due on " + formattedDate);
            for (Task task : this.items) {
                if (task instanceof Deadline && task.queryByDate(date)) {
                    print(task);
                }
            }
        } else if (taskType.equals("event")) {
            print("Below are events that are operating on " + formattedDate);
            for (Task task : this.items) {
                if (task instanceof Event && task.queryByDate(date)) {
                    print(task);
                }
            }
        } else {
            print("Could not query give task type");
        }
    }

    public void todo(String input) {
        if (input.split(" ").length <= 1) {
            print("I didn't quite understand, could you complete your command with appropriate arguments?");
            return;
        }
        String name = input.substring(5).strip();
        Todo todo = new Todo(name);
        this.items.add(todo);
        print("Got it. I've added this task:");
        print("\t" + todo);
        print(String.format("Now you have %d tasks in the list.", this.items.size()));

        // save to file
        String data = String.format("todo,%s,%b", name, todo.status);
        writeLine(data);
    }

    public void deadline(String input) {
        if (input.split(" ").length <= 1) {
            print("I didn't quite understand, could you complete your command with appropriate arguments?");
            return;
        }
        int indexBy = input.indexOf('/');
        try {
            if (!input.substring(indexBy + 1).split(" ")[0].equals("by")) {
                throw new InvalidSlashParameterException();
            }
            String name = input.substring(9, indexBy).strip();
            String by = input.substring(indexBy + 3).strip();
            Deadline deadline = new Deadline(name, LocalDate.parse(by));
            this.items.add(deadline);
            print("Got it. I've added this task:");
            print("\t" + deadline);
            print(String.format("Now you have %d tasks in the list.", this.items.size()));

            // save to file
            String data = String.format("deadline,%s,%s,%b", name, by, deadline.status);
            writeLine(data);
        } catch (InvalidSlashParameterException e) {
            print("Sorry, when would you like the deadline to be until?");
        }
    }

    public void event(String input) {
        if (input.split(" ").length <= 1) {
            print("I didn't quite understand, could you complete your command with appropriate arguments?");
            return;
        }
        int indexFrom = input.indexOf('/');
        int indexTo = input.indexOf('/', indexFrom + 1);
        try {
            if (!input.substring(indexFrom + 1).split(" ")[0].equals("from")) {
                throw new InvalidSlashParameterException();
            }
            if (!input.substring(indexTo + 1).split(" ")[0].equals("to")) {
                throw new InvalidSlashParameterException();
            }
            String name = input.substring(6, indexFrom).strip();
            String from = input.substring(indexFrom + 5, indexTo).strip();
            String to = input.substring(indexTo + 3).strip();
            if (LocalDate.parse(to).isBefore(LocalDate.parse(from))) {
                throw new InvalidDateException();
            }
            Event event = new Event(name, LocalDate.parse(from), LocalDate.parse(to));
            this.items.add(event);
            print("Got it. I've added this task:");
            print("\t" + event);
            print(String.format("Now you have %d tasks in the list.", this.items.size()));

            // save to file
            String data = String.format("event,%s,%s,%s,%b", name, from, to, event.status);
            writeLine(data);
        } catch (InvalidSlashParameterException e) {
            print("Sorry, when would you like the event to begin and end?");
        } catch (InvalidDateException e) {
            print("Please ensure that you are inputting valid start and end dates.");
        }
    }

    public void writeLine(String data) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.file, true));
            writer.write(data);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            print(e.getMessage());
        }
    }

    public String readLine(int index) {
        try {
            Scanner scanner = new Scanner(this.file);
            int i = 0;
            String line = "";
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (i == index) {
                    return line;
                }
                i++;
            }
        } catch (FileNotFoundException e) {
            print(e.getMessage());
        }
        return null;
    }

    public void updateLine(int index, String data) {
        try {
            List<String> lines = Files.readAllLines(this.path);
            lines.set(index, data);
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.file, false));
            writer.write("");
            writer = new BufferedWriter(new FileWriter(this.file, true));
            for (String str : lines) {
                writer.write(str);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            print(e.getMessage());
        }
    }

    public void deleteLine(int index) {
        try {
            List<String> lines = Files.readAllLines(this.path);
            lines.remove(index);
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.file, false));
            writer.write("");
            writer = new BufferedWriter(new FileWriter(this.file, true));
            for (String str : lines) {
                writer.write(str);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            print(e.getMessage());
        }
    }


    public void print(Object text) {
        System.out.println("\t" + text.toString());
    }

    public static void main(String[] args) {
        Lulu chatbot = new Lulu();
        chatbot.start();
        try {
            chatbot.respond();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        chatbot.exit();
    }
}
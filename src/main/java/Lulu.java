import exceptions.InvalidCommandException;
import exceptions.InvalidSlashParameterException;
import exceptions.InvalidStatusUpdateException;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
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
        EVENT;
    }
    private List<Task> items;
    private File file;
    private Path path;

    public Lulu() {
        this.items = new ArrayList<>();
    }

    public void start() {
        try {
            String fileName = "src/main/java/data/lulu.txt";
            this.file = new File(fileName);
            this.path = Path.of(fileName);
            Scanner scanner = new Scanner(file);
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
                    Deadline deadline = new Deadline(words[1], words[2]);
                    if (words[3].equals("true")) {
                        deadline.updateStatus(true);
                    }
                    this.items.add(deadline);
                } else if (words[0].equals("event")) {
                    Event event = new Event(words[1], words[2], words[3]);
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
        if (input.length() <= 4) {
            print("I didn't quite understand, could you complete your command with appropriate arguments?");
            return;
        }
        int index = Integer.valueOf(input.substring(5).strip()) - 1;
        if (index >= this.items.size() || index < 0) {
            print("Oops! You did not give a valid index.");
            return;
        }
        Task task = this.items.get(index);
        try {
            task.updateStatus(true);
            print("Nice! I've marked this task as done:");
            print(task);
        } catch (InvalidStatusUpdateException e) {
            print("This task was already marked!");
            print(task);
        }
    }

    public void unmark(String input) {
        if (input.length() <= 6) {
            print("I didn't quite understand, could you complete your command with appropriate arguments?");
            return;
        }
        int index = Integer.valueOf(input.substring(7).strip()) - 1;
        if (index >= this.items.size() || index < 0) {
            print("Oops! You did not give a valid index.");
            return;
        }
        Task task = this.items.get(index);
        try {
            task.updateStatus(false);
            print("OK, I've marked this task as not done yet:");
            print(task);
        } catch (InvalidStatusUpdateException e) {
            print("This task was already unmarked!");
            print(task);
        }
    }

    public void delete(String input) {
        if (input.length() <= 6) {
            print("I didn't quite understand, could you complete your command with appropriate arguments?");
            return;
        }
        int index = Integer.valueOf(input.substring(6).strip()) - 1;
        if (index >= this.items.size() || index < 0) {
            print("Oops! You did not give a valid index.");
            return;
        }
        Task task = this.items.remove(index);
        print("Noted. I've removed this task:");
        print("\t" + task);
        print(String.format("Now you have %d tasks in the list.", this.items.size()));
    }

    public void todo(String input) {
        if (input.length() <= 4) {
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
        writeLine(this.file, data);
    }

    public void deadline(String input) {
        if (input.length() <= 8) {
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
            Deadline deadline = new Deadline(name, by);
            this.items.add(deadline);
            print("Got it. I've added this task:");
            print("\t" + deadline);
            print(String.format("Now you have %d tasks in the list.", this.items.size()));

            // save to file
            String data = String.format("deadline,%s,%s,%b", name, by, deadline.status);
            writeLine(this.file, data);
        } catch (InvalidSlashParameterException e) {
            print("Sorry, when would you like the deadline to be until?");
        }
    }

    public void event(String input) {
        if (input.length() <= 5) {
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
            Event event = new Event(name, from, to);
            this.items.add(event);
            print("Got it. I've added this task:");
            print("\t" + event);
            print(String.format("Now you have %d tasks in the list.", this.items.size()));

            // save to file
            String data = String.format("event,%s,%s,%s,%b", name, from, to, event.status);
            writeLine(this.file, data);
        } catch (InvalidSlashParameterException e) {
            print("Sorry, when would you like the event to begin and end?");
        }
    }

    public void writeLine(File file, String data) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(data);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            print(e.getMessage());
        }
    }

    private void updateLine(Path path, int index, String data) {
        try {
            List<String> lines = Files.readAllLines(path);
            lines.set(index, data);

            Files.write(path, lines, StandardOpenOption.WRITE);
        } catch (IOException e) {
            print(e.getMessage());
        }
    }

    private void deleteLine(Path path, int index) {
        try {
            List<String> lines = Files.readAllLines(path);
            lines.remove(index);

            Files.write(path, lines, StandardOpenOption.WRITE);
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
            System.out.println("Error detected");
        }
        chatbot.exit();
    }
}
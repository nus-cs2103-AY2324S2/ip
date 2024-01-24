import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;

public class Tony {
    public static void main(String[] args) {
        ToDoList lst = new ToDoList();
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        greeting();
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            String[] words = input.split(" ");
            String firstWord = words[0];
            try {
                if (firstWord.equals("list")) {
                    lst.print();
                } else if (firstWord.equals("mark")) {
                    String secondWord = words[1];
                    lst.mark(secondWord);
                } else if (firstWord.equals("unmark")) {
                    String secondWord = words[1];
                    lst.unmark(secondWord);
                } else if (firstWord.equals("todo")) {
                    String result = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
                    Task toDo = new Todo(result);
                    lst.add(toDo);
                } else if (firstWord.equals("deadline")) {
                    String result = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
                    String[] parts = result.split("/");
                    Task deadline = new Deadline(parts);
                    lst.add(deadline);
                } else if (firstWord.equals("event")) {
                    String result = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
                    String[] parts = result.split("/");
                    Task event = new Event(parts);
                    lst.add(event);
                } else if (firstWord.equals("delete")) {
                    String secondWord = words[1];
                    lst.delete(secondWord);
                } else {
                    throw new IllegalArgumentException("Invalid command: " + firstWord);
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
            input = scanner.nextLine();
        }
        goodbye();
    }

    private static void greeting() {
        String greeting = "_______________________\n"
                + "what is up dawg! I'm Tony!\n"
                + "What can I do for you mate?\n"
                + "_________________________\n";
        System.out.println(greeting);
    }
    private static void goodbye() {
        String goodbye = "See ya later dawg!\n";
        line();
        System.out.println(goodbye);
        line();
        System.exit(0);
    }
    private static void line() {
        System.out.println("_______________________\n");
    }

    static class ToDoList {
        List<Task> list = new ArrayList<>();

        public void add(Task item) {
            list.add(item);
            int numberOfTasks = list.size();
            line();
            System.out.println("Got it dawg. I've added this task: \n");
            System.out.println(item.toString() + "\n");
            System.out.println("Now you got "+ numberOfTasks + " tasks fam \n");
            line();
        }

        public void mark(String input) {
            try {
                int index = Integer.parseInt(input);
                list.get(index - 1).markAsDone();
                line();
                System.out.println("Marked item " + index + " as done dawg.");
                line();
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                line();
                System.out.println("Invalid input for 'mark' command dawg.");
                line();
            }
        }

        public void unmark(String input) {
            try {
                int index = Integer.parseInt(input);
                list.get(index - 1).markAsUndone();
                line();
                System.out.println("Unmarked item " + index + " as done.");
                line();
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                line();
                System.out.println("Invalid input for 'unmark' command.");
                line();
            }
        }

        public void delete(String input) {
            try {
                int index = Integer.parseInt(input);
                String task = list.get(index - 1).toString();
                list.remove(index - 1);
                line();
                System.out.println("Deleted item: \n" + task + "\n");
                System.out.println("Now you have " + list.size() + " tasks left in the list.");
                line();
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                line();
                System.out.println("Invalid input for 'delete' command.");
                line();
            }
        }

        public void print() {
            line();
            System.out.println("Here are the tasks in your list: \n");
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i).toString() + "\n");
            }
            line();
        }
    }
}

class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }
}

class Todo extends Task {
    public Todo(String description) {
        super(description);
        if (description.equals("")) {
            throw new IllegalArgumentException("Should have more description dawg");
        }

    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class Deadline extends Task {
    private String dueDate;

    public Deadline(String[] parts) {
        super(parts[0]);
        if (parts.length < 2) {
            throw new IllegalArgumentException("Invalid input for deadline task. Expected: <description> /by <dueDate>");
        }
        this.dueDate = parts[1];
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (" + dueDate + ")";
    }
}

class Event extends Task {
    private String from;
    private String to;

    public Event(String[] parts) {
        super(parts[0]);
        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid input for event task. Expected: <description> /at <from> <to>");
        }
        this.from = parts[1];
        this.to = parts[2];
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (" + from + " " + to + ")";
    }
}
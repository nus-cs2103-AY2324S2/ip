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
                Task deadline = new Deadline(parts[0], parts[1]);
                lst.add(deadline);

            } else if (firstWord.equals("event")) {
                String result = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
                String[] parts = result.split("/");
                Task event = new Event(parts[0], parts[1], parts[2]);
                lst.add(event);
            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            input = scanner.nextLine();
        }
        goodbye();
    }

    private static void greeting() {
        String greeting = "_______________________\n"
                + "Hello! I'm Tony!\n"
                + "What can I do for you?\n"
                + "_________________________\n";
        System.out.println(greeting);
    }
    private static void goodbye() {
        String goodbye = "Bye. Hope to see you again soon!\n";
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
            System.out.println("Got it. I've added this task: \n");
            item.toString();
            System.out.println("Now you have "+ numberOfTasks + " tasks in the list \n");
            line();
        }

        public void mark(String input) {
            try {
                int index = Integer.parseInt(input);
                list.get(index - 1).markAsDone();
                line();
                System.out.println("Marked item " + index + " as done.");
                line();
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                line();
                System.out.println("Invalid input for 'unmark' command.");
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
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class Deadline extends Task {
    private String dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (" + dueDate + ")";
    }
}

class Event extends Task {
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (" + from + " " + to + ")";
    }
}
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String logo = " ____        _        \n"
                                + "|  _ \\ _   _| | _____ \n"
                                + "| | | | | | | |/ / _ \\\n"
                                + "| |_| | |_| |   <  __/\n"
                                + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String LINE = "\t____________________________________________________________";

    private Scanner sc = new Scanner(System.in);
    private ArrayList<Task> list = new ArrayList<>();


    private void sayGreetings() {
        System.out.println(LINE);
        System.out.println("\tHello! I'm SKY");
        System.out.println("\tWhat can I do for you?");
        System.out.println(LINE);
    }

    private void sayBye() {
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    private void echo(String input) {
        System.out.println(input);
    }

    private void add(Task task) {
        this.list.add(task);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + task);
        System.out.println("\tNow you have " + this.list.size() + " tasks in the list.");
    }

    private void list() {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + this.list.get(i));
        }
    }

    private void mark(String[] input) throws IncompleteCommandException, InvalidArgumentException {
        if (input.length == 1 || input[1].equals("")) {
            throw new IncompleteCommandException(input[0]);
        } 
        try {
            int index = Integer.parseInt(input[1]) - 1;
            this.list.get(index).markAsDone();
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidArgumentException();
        }
    }

    private void unmark(String[] input) throws IncompleteCommandException, InvalidArgumentException {
        if (input.length == 1 || input[1].equals("")) {
            throw new IncompleteCommandException(input[0]);
        } 
        try {
            int index = Integer.parseInt(input[1]) - 1;
            this.list.get(index).markNotDone();
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidArgumentException();
        }
    }

    private void addTodo(String[] input) throws IncompleteCommandException {
        if (input.length == 1 || input[1].equals("")) {
            throw new IncompleteCommandException(input[0]);
        } 
        String description = input[1];
        this.add(new Todo(description));
    }

    private void addDeadline(String[] input) throws IncompleteCommandException, NoDeadlineException {
        if (input.length == 1 || input[1].equals("")) {
            throw new IncompleteCommandException(input[0]);
        } 
        input = input[1].split(" /by ", 2);
        if (input.length == 1) {
            throw new NoDeadlineException();
        }
        String description = input[0];
        String by = input[1];
        this.add(new Deadline(description, by));
    }

    private void addEvent(String[] input) throws IncompleteCommandException, NoPeriodException {
        if (input.length == 1 || input[1].equals("")) {
            throw new IncompleteCommandException(input[0]);
        } 
        input = input[1].split(" /from ", 2);
        if (input.length == 1) {
            throw new NoPeriodException();
        }
        String description = input[0];
        String[] arr = input[1].split(" /to ", 2);
        if (arr.length == 1) {
            throw new NoPeriodException();
        }
        this.add(new Event(description, arr[0], arr[1]));
    }

    private void delete(String[] input) throws IncompleteCommandException, InvalidArgumentException {
        if (input.length == 1 || input[1].equals("")) {
            throw new IncompleteCommandException(input[0]);
        } 
        try {
            int index = Integer.parseInt(input[1]) - 1;
            Task task = this.list.get(index);
            this.list.remove(index);
            System.out.println("\tNoted. I've removed this task:");
            System.out.println("\t  " + task);
            System.out.println("\tNow you have " + this.list.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidArgumentException();
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.sayGreetings();

        while (true) {
            try {
                String userInput = duke.sc.nextLine();
                String[] input = userInput.split(" ", 2);
                String action = input[0];
                System.out.println(LINE);
                if (action.equals("bye")) {
                    break;
                } else if (action.equals("list")) {
                    duke.list();
                } else if (action.equals("mark")) {
                    duke.mark(input);
                } else if (action.equals("unmark")) {
                    duke.unmark(input);
                } else if (action.equals("todo")) {
                    duke.addTodo(input);
                } else if (action.equals("deadline")) {
                    duke.addDeadline(input);
                } else if (action.equals("event")) {
                    duke.addEvent(input);
                } else if (action.equals("delete")) {
                    duke.delete(input);
                } else {
                    throw new UnknownCommandException();
                }
            } catch (Exception e) {
                System.out.println("\t" + e);
            }
            System.out.println(LINE);
        }
        duke.sayBye();
    }
}

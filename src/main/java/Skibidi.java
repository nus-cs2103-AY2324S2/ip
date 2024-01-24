import java.util.ArrayList;
import java.util.Scanner;

public class Skibidi {
    public static String logo = " ____  _  _____ ____ ___ ____ ___ \n"+
            "/ ___|| |/ /_ _| __ )_ _|  _ \\_ _|\n"+
            "\\___ \\| ' / | ||  _ \\| || | | | | \n" +
            " ___) | . \\ | || |_) | || |_| | | \n" +
            "|____/|_|\\_\\___|____/___|____/___|";

    private ArrayList<Task> list = new ArrayList<>();

    public void printLine() {
        System.out.println("\n-------------------------------------------------------------------\n");
    }

    public void greet() {
        System.out.println(Skibidi.logo);
        this.printLine();
        System.out.println("Hello! I'm Skibidi!\nWhat can I do for you?");
        this.printLine();
    }

    public void bye() {
        System.out.println("Bye! Hope to see you again soon!");
        this.printLine();
    }

    public void chat() {
        Scanner sc = new Scanner(System.in);
        String in = null;
        while (true) {
            in = sc.nextLine();
            if (in.equals("bye")) break;

            inputComprehension(in);

            this.printLine();
        }
    }

    public void printList() {
        System.out.println("Here are the tasks in your list:");
        int number = 1;
        for (Task t : this.list) {
            System.out.printf("%d. %s", number, t);
            number++;
        }
    }

    private void inputComprehension(String in) {
        if (in.equals("list")) {
            printList();

        // Marking a task as done
        } else if (in.startsWith("mark")) {
            int i;
            try {
                i = Integer.parseInt(in.substring(5));
                Task t = this.list.get(i-1);
                t.markAsDone();
                System.out.print("Nice! I've marked this task as done:\n  ");
                System.out.print(t);
                this.list.set(i-1, t);

            } catch (NumberFormatException e) {
                System.out.println("Not a valid number!");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Sorry, index out of range!");
            }

        // Marking a task as not done
        } else if (in.startsWith("unmark")) {
            try {
                int i = Integer.parseInt(in.substring(7));
                Task t = this.list.get(i-1);
                t.markAsNotDone();
                System.out.print("OK, I've marked this task as not done yet:\n  ");
                System.out.print(t);
                this.list.set(i-1, t);

            } catch (NumberFormatException e) {
                System.out.println("Not a valid number!");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Sorry, index out of range!");
            }

        } else if (in.startsWith("delete")){
            try {
                int i = Integer.parseInt(in.substring(7));
                Task t = this.list.remove(i-1);
                System.out.print("Noted. I've removed this task::\n  ");
                System.out.println(t);
                System.out.printf("Now you have %d tasks in the list.\n", this.list.size());

            } catch (NumberFormatException e) {
                System.out.println("Not a valid number! Or perhaps add a ' '");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Sorry, index out of range!");
            }

        } else {
            try {
                addTask(in);
            } catch (DukeInvalidInputException e) {
                System.out.println("This is not a valid input!!!");
            } catch (DukeEmptyArgumentException e) {
                System.out.println("There is an argument that is empty!!!");
            } catch (DukeErroneousArgumentException e) {
                System.out.println("There is an argument that is wrong!!!");
            }
        }
    }

    public void addTask(String s) {
        // Todo_
        if (s.startsWith("todo")) {
            // Get name and if it is empty, throw exception
            String n = s.substring(5);
            if (n.isEmpty()) {
                throw new DukeEmptyArgumentException();
            }

            this.list.add(new Todo(n));

        // Deadline
        } else if (s.startsWith("deadline")) {
            // Try to get the index of the first '/', if it does not exist, the statement is invalid.
            // Also, it should adhere to "/by"
            int first = s.indexOf('/');
            if (first == -1) {
                throw new DukeErroneousArgumentException();
            } else if (!s.startsWith("/by", first)) {
                throw new DukeErroneousArgumentException();
            }

            // Get name. If empty, throw exception
            String n = s.substring(9, first);
            String t = s.substring(first + 4);
            if (n.isEmpty() || t.isEmpty()) {
                throw new DukeEmptyArgumentException();
            }

            this.list.add(new Deadline(n, t));

        // Event
        } else if (s.startsWith("event")) {
            // Try to get the index of the first  and second '/', if it does not exist, the statement is invalid.
            // Also, the format should adhere to "/from" and "/to"
            int first = s.indexOf('/');
            int second = s.indexOf('/', first + 1);
            if (first == -1 || second == -1) {
                throw new DukeErroneousArgumentException();
            } else if (!s.startsWith("/from", first)
                    || !s.startsWith("/to", second)) {
                throw new DukeErroneousArgumentException();
            }

            String n = s.substring(6, first - 1);
            String f = s.substring(first + 6,  second);
            String t = s.substring(second + 4);
            if (n.isEmpty() || f.isEmpty() || t.isEmpty()) {
                throw new DukeEmptyArgumentException();
            }

            this.list.add(new Event(n, f, t));

        } else {
            throw new DukeInvalidInputException();
        }

        System.out.print("Got it added this task:\n  " + this.list.get(this.list.size() - 1));
        System.out.printf("Now you have %d tasks in the list.", this.list.size());
    }
}

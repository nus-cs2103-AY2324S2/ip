import java.io.IOException;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.stream.Collectors;

public class Skibidi {
    public static String logo = " ____  _  _____ ____ ___ ____ ___ \n"+
            "/ ___|| |/ /_ _| __ )_ _|  _ \\_ _|\n"+
            "\\___ \\| ' / | ||  _ \\| || | | | | \n" +
            " ___) | . \\ | || |_) | || |_| | | \n" +
            "|____/|_|\\_\\___|____/___|____/___|";

    private List<Task> list = new ArrayList<>();

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
        if (in.equals("list")) printList();

        else if (in.equals("save")) {
            try {
                save();
            } catch (IOException e) {
                System.out.println("I/O Exception");
            }
        }

        else if (in.startsWith("mark ")) mark(in);

        else if (in.startsWith("unmark ")) unmark(in);

        else if (in.startsWith("delete ")) delete(in);

        else {
            try {
                addTask(in);
            } catch (DukeInvalidInputException e) {
                System.out.println("This is not a valid input!!!");
            } catch (DukeEmptyArgumentException e) {
                System.out.println("There is an argument that is empty!!!");
            } catch (DukeErroneousArgumentException e) {
                System.out.println("There is an argument in the wrong format!!!");
            } catch (DateTimeException e) {
                System.out.println("The format of your date is wrong! Make sure it is of the form 'yyyy-MM-dd'.");
                System.out.println("More specifically: \n" + e.getMessage());
            } catch (DukeWrongDateOrderException e) {
                System.out.println("The end date should be after the start date");
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
            if (first == -1 || s.length() < first + 14) {
                throw new DukeErroneousArgumentException();
            } else if (!s.startsWith("/by", first)) {
                throw new DukeErroneousArgumentException();
            }

            // Get name and time. If empty, throw exception
            String n = s.substring(9, first);
            String t = s.substring(first + 4, first + 14);
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
            if (first == -1 || second != first + 17 || s.length() < second + 14) {
                throw new DukeErroneousArgumentException();
            } else if (!s.startsWith("/from", first)
                    || !s.startsWith("/to", second)) {
                throw new DukeErroneousArgumentException();
            }

            String n = s.substring(6, first - 1);
            String f = s.substring(first + 6,  second - 1);
            String t = s.substring(second + 4, second + 14);
            if (n.isEmpty() || f.isEmpty() || t.isEmpty()) {
                throw new DukeEmptyArgumentException();
            }
            Event e = new Event(n, f, t);
            if (!e.isCorrectOrder()) {
                throw new DukeWrongDateOrderException();
            }
            this.list.add(e);

        } else {
            throw new DukeInvalidInputException();
        }

        System.out.print("Got it added this task:\n  " + this.list.get(this.list.size() - 1));
        System.out.printf("Now you have %d tasks in the list.", this.list.size());
    }

    public void mark(String in) {
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
    }

    public void unmark(String in) {
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
    }

    public void delete(String in) {
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
    }

    public void save() throws IOException, IOException {
        // Check if the directory exists
        String userDir = System.getProperty("user.dir");
        Path pathDir = Paths.get(userDir, "data");
        if (!Files.exists(pathDir)) {
            Files.createDirectories(pathDir);
        }
        // Check if the save file exists
        Path pathFile = Paths.get(userDir, "data", "duke.txt");
        if (Files.exists(pathFile)) {
            Files.delete(pathFile);
        }
        Files.createFile(pathFile);
        // Writing to the file
        writeToFile(pathFile);
        System.out.println("Your list has been saved to /data/duke.txt");
    }

    private void writeToFile(Path f) throws IOException {
        List<String> lines = list.stream()
                .map(Task::toSavedString)
                .collect(Collectors.toList());
        Files.write(f, lines);
    }

    public void load() {
        String userDir = System.getProperty("user.dir");
        Path pathFile = Paths.get(userDir, "data", "duke.txt");
        try {
            List<String> read = Files.readAllLines(pathFile);
            this.list = read.stream()
                    .map(this::stringToTask)
                    .collect(Collectors.toList());
            System.out.println("Your current list:");
            printList();
            printLine();
        } catch (IOException e) {
            System.out.println("You do not have a saved list.");
            printLine();
        }
    }

    private Task stringToTask(String s) {
        List<String> taskLst = Arrays.asList(s.split(","));
        Task t = null;
        switch (taskLst.get(0)) {
            case "T":
                t = new Todo(taskLst.get(1).equals("1"), taskLst.get(2));
                break;
            case "D":
                t = new Deadline(taskLst.get(1).equals("1"), taskLst.get(2), taskLst.get(3));
                break;
            case "E":
                t = new Event(taskLst.get(1).equals("1"), taskLst.get(2), taskLst.get(3), taskLst.get(4));
                break;
        }

        return t;
    }
}

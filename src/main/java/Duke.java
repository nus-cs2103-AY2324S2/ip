import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final int TASKS_MAX = 100;
    private static final ArrayList<Task> tasks = new ArrayList<>(TASKS_MAX);
    private static int taskCount = 0;
    public static void main(String[] args) {
        String logo = " _  _   __    ____  ____ \n" +
                "( \\/ ) /__\\  (  _ \\(  _ \\\n" +
                " \\  / /(__)\\  )   / )   /\n" +
                " (__)(__)(__)(_)\\_)(_)\\_)\n";

        System.out.println(logo);
        printDivider();
        System.out.println("Ahoy! I be Yarr \nWhat be yer command, me heartie?");
        printDivider();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                handleInput(scanner);
            } catch (IllegalArgumentException e) {
                printDivider();
                System.out.println(e.getMessage());
                printDivider();
            } catch (ExitProgramException e) {
                printDivider();
                System.out.println(e.getMessage());
                printDivider();
                break;
            }
        }

    }
    private static void printDivider(){
        int length = 90;
        char divider = 0x2500 ;
        for (int i = 0; i < length; i++) {
            System.out.print(divider);
        }
        System.out.println();
    }

    // Solution below adapted from https://www.geeksforgeeks.org/ways-to-read-input-from-console-in-java/
    private static void handleInput(Scanner scanner) {
        String input = scanner.nextLine();
        String[] wordArray = input.split(" ", 0);
        // Handle inputs
        if (input.equals("bye")) {
            throw new ExitProgramException("Fair winds to ye, me hearty! May the tide carry ye safely until our paths cross again.");
        } else if (input.equals("list")) {
            printDivider();
            System.out.println("Behold, yer roster of endeavors!");
            for (int i = 0; i < taskCount; i++) {
                String tempNum = Integer.toString(i + 1);
                System.out.println(tempNum + "." + tasks.get(i).toString());
            }
            printDivider();
        } else if (wordArray[0].equals("mark") || wordArray[0].equals("unmark") || wordArray[0].equals("delete")) {
            if (wordArray.length != 2) {
                throw new IllegalArgumentException("Blunder! Declare a task by number, matey!");
            } else {
                try {
                    int tempIndex = Integer.parseInt(wordArray[1]);
                    if (tempIndex > taskCount || tempIndex < 1) {
                        throw new IllegalArgumentException("Blunder! Ye only be havin' " + taskCount + " tasks on the chart, matey!");
                    } else {
                        printDivider();
                        if (wordArray[0].equals("mark")) {
                            tasks.get(tempIndex - 1).markAsDone();
                            System.out.println("X marks the spot. I've crossed this task of yer list, me heartie!");
                            System.out.println(tasks.get(tempIndex - 1).toString());
                        } else if (wordArray[0].equals("unmark")) {
                            tasks.get(tempIndex - 1).markAsNotDone();
                            System.out.println("The winds be shiftin', and I be lettin' this task sail with the breeze unmarked.");
                            System.out.println(tasks.get(tempIndex - 1).toString());
                        } else {
                            System.out.println("As ye command, this one has walked the plank:");
                            System.out.println("\t" + tasks.get(tempIndex - 1).toString());
                            tasks.remove(tempIndex - 1);
                            taskCount -= 1;
                            System.out.println("Only " + taskCount + " tasks remain, captain!");
                        }
                        printDivider();
                    }
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Blunder! I be searchin' the seas but couldn't spy the task ye named, me heartie!");
                }
            }
        }
        else if (wordArray[0].equals("todo")){
            if (wordArray.length < 2) {
                throw new IllegalArgumentException("Blunder! Declare yer to-do as such: 'todo *', ye scurvy dog!");
            }
            String tempString = input.substring(5).trim();
            printDivider();
            tasks.add(new Todo(tempString));
            taskCount += 1;
            System.out.println("I've appended this to yer list: " + tasks.get(taskCount - 1).toString());
            printDivider();
        } else if (wordArray[0].equals("deadline")) {
            String tempString = input.substring(9).trim();
            String[] tempArray = tempString.split("/by", 0);
            if (tempArray.length == 1) {
                throw new IllegalArgumentException("Blunder! Declare yer deadline as such: 'deadline * /by *', ye scurvy dog!");
            }
            String description = tempArray[0].trim();
            String by = tempArray[1].trim();
            printDivider();
            tasks.add(new Deadline(description, by));
            taskCount += 1;
            System.out.println("I've appended this to yer list: " + tasks.get(taskCount - 1).toString());
            printDivider();
        } else if (wordArray[0].equals("event")) {
            String tempString = input.substring(6).trim();
            String[] tempArray = tempString.split("/from", 0);
            if (tempArray.length == 1) {
                throw new IllegalArgumentException("Blunder! Declare yer event as such: 'deadline * /from * /to *', ye scurvy dog!");
            }
            String description = tempArray[0].trim();
            tempString = tempArray[1].trim();
            tempArray = tempString.split("/to", 0);
            if (tempArray.length == 1) {
                throw new IllegalArgumentException("Blunder! Declare yer event as such: 'deadline * /from * /to *', ye scurvy dog!");
            }
            String from = tempArray[0].trim();
            String to = tempArray[1].trim();
            printDivider();
            tasks.add(new Event(description, from, to));
            taskCount += 1;
            System.out.println("I've appended this to yer list: " + tasks.get(taskCount - 1).toString());
            printDivider();
        } else {
            throw new IllegalArgumentException("Arrr, me apologies! I cannot fathom that.");
        }
    }
}

// Custom Exit Loop Exception class
class ExitProgramException extends RuntimeException {
    public ExitProgramException(String message) {
        super(message);
    }
}
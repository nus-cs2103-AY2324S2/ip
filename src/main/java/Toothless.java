import Exceptions.InvalidInstructionException;
import Exceptions.MissingToDoNameException;
import Exceptions.MissingTaskToMarkException;
import Parsers.DateTimeParser;
import Parsers.FileParser;
import Tasks.*;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.Arrays;
import java.util.List;

public class Toothless {

    public static void main(String[] args) {
        String filePath = "data/toothless.txt";
        Ui ui = new Ui();
        Parser parser = new Parser();
        File f = new File(filePath);
        try {
            boolean fileCreated = f.createNewFile();

        } catch (IOException e) {
            System.err.println("Error creating the file: " + e.getMessage());
            e.printStackTrace();
        }
        FileParser fileParser = new FileParser(f);
        try {
            fileParser.parseFile(f);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        TaskList currTaskList = fileParser.getTaskList();
        currTaskList = ui.run(parser, currTaskList);
        Storage storage = new Storage(currTaskList);
        try {
            storage.store();
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void run() {

    }
    /*
    static void greet() {
        Toothless.printLines();
        System.out.println("Hello! I'm Toothless!\nWhat can I do for you?");
        Toothless.printLines();
    }

    static void bye() {
        Toothless.printLines();
        System.out.println("Bye. Hope to see you again soon!");
        Toothless.printLines();
    }

    static TaskList taskListModify(TaskList tasksList) throws InvalidInstructionException {
        Scanner scanner = new Scanner(System.in);
        String input = "";

        while (!input.toLowerCase().equals("bye")) {
            input = scanner.nextLine();

            if (!input.toLowerCase().equals("bye")) {
                if (input.equals("list")) {
                    System.out.println(tasksList.toString());

                } else if (input.toLowerCase().startsWith("todo")) {
                    try {
                        if (input.split(" ").length == 1) {
                            throw new MissingToDoNameException("Please provide the description of the todo task :) Eg. 'Todo Chores'");
                        } else {
                            String name = input.substring(5);
                            String response = tasksList.add(new ToDo(name, false, "T"));
                            Toothless.printLines();
                            System.out.println(response);
                            Toothless.printLines();
                        }
                    } catch (MissingToDoNameException err) {
                        Toothless.printLines();
                        System.out.println(err.getMessage());
                        Toothless.printLines();
                    }


                } else if (input.toLowerCase().startsWith("deadline")) {
                    int endChar = input.indexOf("/");
                    int startChar = 9;
                    String name = input.substring(9, endChar);
                    String deadline = input.substring(endChar + 4);
                    LocalDate d = DateTimeParser.stringToDT(deadline);
                    String response = tasksList.add(new Deadline(name, d, false, "D"));
                    Toothless.printLines();
                    System.out.println(response);
                    Toothless.printLines();

                } else if (input.toLowerCase().startsWith("event")) {
                    int endChar = input.indexOf("/");
                    int endChar2 = input.indexOf("/", endChar + 1);
                    int startChar = 6;
                    String name = input.substring(6, endChar);
                    String startTime = input.substring(endChar + 5, endChar2);
                    String endTime = input.substring(endChar2 + 3);
                    LocalDate start = DateTimeParser.stringToDT(startTime);
                    LocalDate end = DateTimeParser.stringToDT(endTime);
                    String response = tasksList.add(new Event(name, start, end, false, "E"));
                    Toothless.printLines();
                    System.out.println(response);
                    Toothless.printLines();

                } else if (input.toLowerCase().startsWith("unmark")) {
                    try {
                        if (input.split(" ").length == 1) {
                            throw new MissingTaskToMarkException("Please provide a task to unmark :)");

                        } else {
                            int index = Integer.parseInt(input.substring(7));
                            System.out.print(index);
                            String response = tasksList.unmark(index);
                            System.out.println(response);
                        }
                    } catch (MissingTaskToMarkException err) {
                        Toothless.printLines();
                        System.out.println(err.getMessage());
                        Toothless.printLines();
                    }

                } else if (input.toLowerCase().startsWith("mark")) {
                    try {
                        if (input.split(" ").length == 1) {
                            throw new MissingTaskToMarkException("Please provide a task to mark :)");
                        } else {
                            int index = Integer.parseInt(input.substring(5));
                            System.out.print(index);
                            String response = tasksList.mark(index);
                            System.out.println(response);
                        }
                    } catch (MissingTaskToMarkException err) {
                        Toothless.printLines();
                        System.out.println(err.getMessage());
                        Toothless.printLines();
                    }


                } else if (input.toLowerCase().startsWith("delete")){
                    int index = Integer.parseInt(input.substring(7));
                    System.out.print(index);
                    String response = tasksList.delete(index);
                    System.out.println(response);
                } else {
                    Toothless.printLines();
                    System.out.println("Try entering a valid instruction! Eg. 'Todo Chores' or 'Mark 2'");
                    Toothless.printLines();
                }

            } else {
                break;
            }

        }
        return tasksList;
    }
    */
    static void printTasks(ArrayList<Task> tasksList) {
        int taskCount = 1;
        for (Task t : tasksList) {
            System.out.println(taskCount + ". " + t.toString());
            taskCount++;
        }
    }

    static void printLines() {
        System.out.println("____________________________________________________________");
    }
}

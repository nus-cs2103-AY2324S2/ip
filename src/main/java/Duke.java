import exception.InvalidInputException;
import exception.EmptyTimeException;
import exception.EmptyInputException;
import exception.InvalidFormatException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    ArrayList<Task> list;
    public Duke() {
        this.list = initTasks();
    }
    private static final String SEPERATOR = "------------------------------------------------";
    private static final String INDENT = "    ";
    private static final String INDENT_SEPERATOR = INDENT + SEPERATOR;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String logo = " ____\n"
                    + "|  _ \\   ___   ___\n"
                    + "| |_| | / _ \\ / _ \\\n"
                    + "| |_| | | __/ | __/\n"
                    + "|____/  \\___| \\___|\n";

        String msg = SEPERATOR
                + "\nHello! I'm Bee!\n"
                + "What can I do for you?\n"
                + SEPERATOR;

        System.out.println(logo + "\n" + msg);

        boolean isOutput = true;
        String input;

        Duke bee = new Duke();

        while (isOutput) {
            input = sc.nextLine();
            try {
                String temp = input.split(" ")[0];
                Actions action = Actions.valueOf(temp.toUpperCase());
                switch(action) {
                case BYE:
                    System.out.println(INDENT_SEPERATOR
                            + "\n"
                            + INDENT
                            + "Bye. Hope to see you again soon!\n"
                            + INDENT_SEPERATOR);
                    isOutput = false;
                    break;
                case LIST:
                    showList(bee.list);
                    break;
                case MARK:
                    mark(input, bee.list);
                    break;
                case UNMARK:
                    unmark(input, bee.list);
                    break;
                case TODO:
                    todo(input, bee.list);
                    break;
                case DEADLINE:
                    deadline(input, bee.list);
                    break;
                case EVENT:
                    event(input, bee.list);
                    break;
                case DELETE:
                    delete(input, bee.list);
                    break;
                default:
                    // not a valid command
                    throw new InvalidInputException(
                            SEPERATOR
                            + "Sorry this is an invalid input :("
                            + SEPERATOR);
                }
            } catch (EmptyInputException e) {
                System.out.println(e.getMessage());
            } catch (EmptyTimeException e) {
                System.out.println(e.getMessage());
            } catch (InvalidFormatException e) {
                System.out.println(e.getMessage());
            } catch (InvalidInputException e) {
                System.out.println("    OOPS!!! ");
            } catch (Exception e) {
                System.out.println(INDENT_SEPERATOR + "\n    OOPS!!! Something went wrong D:\n"
                        + INDENT_SEPERATOR);
            } finally {
                writeTasks(bee.list);
            }
        }
        sc.close();
    }

    private static void writeTasks(ArrayList<Task> list) {
        try {
            FileWriter fw = new FileWriter("./data/bee.txt");
            for (Task t : list) {
                fw.write(t.toString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    private ArrayList<Task> initTasks() {
        ArrayList<Task> list = new ArrayList<>();
        try {
            File directory = new File("./data");
            if (!directory.exists()) {
                directory.mkdir();
            }
            File inputFile = new File("./data/bee.txt");
            if (inputFile.createNewFile()) {
                // new file created
            } else {
                list = readTasks(inputFile);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } finally {
            return list;
        }
    }

    private ArrayList<Task> readTasks(File inputFile) {
        ArrayList<Task> list = new ArrayList<>();
        try {
            Scanner s = new Scanner(inputFile);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String type = line.substring(1, 2);
                String mark = line.substring(5, 6);

                if (type.equals("T")) {
                    String description = line.substring(7);
                    Todo t = new Todo(description);
                    if (mark.equals("X")) {
                        t.markAsDone();
                    }
                    list.add(t);
                } else if (type.equals("D")) {
                    String temp = line.substring(7);
                    String[] result = temp.split("\\(by: ");
                    String description  = result[0];
                    String by = result[1].substring(0, result[1].length() - 1);
                    Deadline t = new Deadline(description, by);
                    if (mark.equals("X")) {
                        t.markAsDone();
                    }
                    list.add(t);
                } else if (type.equals("E")) {
                    String temp = line.substring(7);
                    String[] result = temp.split("\\(from: ");
                    String description  = result[0];
                    result = result[1].split("\\to: ");
                    String from = result[0];
                    String to = result[1];
                    to = to.substring(0, to.length() - 1);
                    Event t = new Event(description, from, to);
                    if (mark.equals("X")) {
                        t.markAsDone();
                    }
                    list.add(t);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } finally {
            return list;
        }
    }

    // print the entire list
    private static void showList(ArrayList<Task> list) {
        System.out.println(INDENT_SEPERATOR);
        System.out.println(INDENT + "Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            Task t = list.get(i);
            System.out.println(INDENT + (i + 1) + "." + t.toString());
        }
        System.out.println(INDENT_SEPERATOR);
    }

    // mark an item in list
    private static void mark(String input, ArrayList<Task> list) {
        String indexStr = input.split(" ")[1];
        int position = Integer.parseInt(indexStr) -1;

        // check for error
        if (position + 1 > list.size()) {
            System.out.println(INDENT_SEPERATOR);
            System.out.println(INDENT + "Task does not exist");
        } else if (position < 0) {
            System.out.println(INDENT_SEPERATOR);
            System.out.println(INDENT + "Task does not exist");

        } else {
            System.out.println(INDENT_SEPERATOR);
            Task t = list.get(position);
            if (t.getStatusIcon().equals(" ")) {
                System.out.println(INDENT + "Nice! I've marked this task as done:");
            }
            t.markAsDone();
            System.out.println(INDENT + "  " + t.toString());
        }
        System.out.println(INDENT_SEPERATOR);


    }

    // unmark an item in list
    private static void unmark(String input, ArrayList<Task> list) {
        String indexStr = input.split(" ")[1];
        int position = Integer.parseInt(indexStr) -1;

        // check for error
        if (position + 1 > list.size()) {
            System.out.println(INDENT_SEPERATOR);
            System.out.println(INDENT + "Task does not exist");
        } else if (position < 0) {
            System.out.println(INDENT_SEPERATOR);
            System.out.println(INDENT + "Task does not exist");

        } else {
            System.out.println(INDENT_SEPERATOR);
            Task t = list.get(position);
            if (t.getStatusIcon().equals("X")) {
                System.out.println(INDENT + "OK, I've marked this task as not done yet:");
            }
            t.markAsUndone();
            System.out.println(INDENT + "  " + t.toString());
        }
        System.out.println(INDENT_SEPERATOR);
    }
    private static void todo(String input, ArrayList<Task> list) throws EmptyInputException {
        System.out.println(INDENT_SEPERATOR);
        if (input.split(" ").length > 1) {
            String des = input.substring(4).trim();
            Todo t = new Todo(des);
            list.add(t);
            System.out.println(INDENT + "Got it. I've added this task:");
            System.out.println(INDENT + "  " + t.toString());
            System.out.println(INDENT + "Now you have " + list.size() + " tasks in the list.");
        } else {
            throw new EmptyInputException("todo");
        }
        System.out.println(INDENT_SEPERATOR);
    }

    private static void deadline(String input, ArrayList<Task> list)
            throws EmptyInputException, EmptyTimeException, InvalidFormatException {
        System.out.println(INDENT_SEPERATOR);
        if (input.split(" ").length == 1) {
            throw new EmptyInputException("deadline");
        }  else if (!input.contains("/by")) {
            throw new InvalidFormatException("deadline", "/by");
        } else {
            input = input.substring(8);
            String[] arrOfStr = input.split("/by");
            if (arrOfStr.length < 2) {
                throw new EmptyTimeException("deadline", "end");
            } else {
                String des = arrOfStr[0].trim();
                String by = arrOfStr[1].trim();
                Deadline t = new Deadline(des, by);
                list.add(t);
                System.out.println(INDENT + "Got it. I've added this task:");
                System.out.println(INDENT + "  " + t.toString());
                System.out.println(INDENT + "Now you have " + list.size() + " tasks in the list.");
                System.out.println(INDENT_SEPERATOR);
            }
        }
    }
    private static void event(String input, ArrayList<Task> list)
            throws EmptyInputException, EmptyTimeException, InvalidFormatException {
        System.out.println(INDENT_SEPERATOR);
        if (input.split(" ").length == 1) {
            throw new EmptyInputException("event");
        }  else if (!input.contains("/from")) {
            throw new InvalidFormatException("event", "/from");
        }  else if (!input.contains("/to")) {
            throw new InvalidFormatException("event", "/to");
        } else {
            String temp = input.substring(5);
            String[] arrOfStr = temp.split("/");
            if (arrOfStr.length < 2) {
                throw new EmptyTimeException("event", "start");
            } else if (arrOfStr.length < 3) {
                throw new EmptyTimeException("event", "end");
            } else {
                String des = arrOfStr[0].trim();
                String start = arrOfStr[1].substring(4).trim();
                String end = arrOfStr[2].substring(2).trim();
                Event t = new Event(des, start, end);
                list.add(t);
                System.out.println(INDENT + "Got it. I've added this task:");
                System.out.println(INDENT + "  " + t.toString());
                System.out.println(INDENT + "Now you have " + list.size() + " tasks in the list.");
                System.out.println(INDENT_SEPERATOR);
            }
        }
    }
    private static void delete(String input, ArrayList<Task> list) {
        String indexStr = input.split(" ")[1];
        int position = Integer.parseInt(indexStr) -1;
        System.out.println(INDENT_SEPERATOR);

        // check for error
        if (position + 1 > list.size()) {
            System.out.println(INDENT + "Task does not exist");
        } else if (position < 0) {
            System.out.println(INDENT + "Task does not exist");

        } else {
            Task t = list.get(position);
            list.remove(t);
            System.out.println(INDENT + "Noted. I've removed this task:");
            System.out.println(INDENT + "  " + t.toString());
            System.out.println(INDENT + "Now you have " + list.size() + " tasks in the list.");
        }
        System.out.println(INDENT_SEPERATOR);
    }

}

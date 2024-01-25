import java.io.*;
import java.util.*;

public class Duke {
    enum Request {
        BYE, LIST, MARK, TODO, DEADLINE, EVENT, DELETE, INVALID
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Hao Wen\n" + "What can I do for you?");
        //System.out.println("Bye. Hope to see you again soon!");

        MyList myList = new MyList();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            while (true) {
                String userInput = br.readLine();
                Request request = getRequest(userInput);

                switch (request) {
                    case BYE:
                        System.out.println("Bye. Hope to see you again soon!");
                        return;
                    case LIST:
                        System.out.println(myList.getItems());
                        break;
                    case MARK:
                        try {
                            int index = Integer.parseInt(userInput.substring("mark".length()).trim());
                            System.out.println(myList.markTask(index));
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a number after mark.");
                        }
                        break;
                    case TODO:
                        try {
                            String s = userInput.substring("todo".length()).trim();
                            if (s.isEmpty()) {
                                throw new DukeException("Task description cannot be empty.");
                            }
                            Task task = new Todo(s);
                            System.out.println(myList.addItem(task));
                        } catch (DukeException e) {
                            System.out.println("Error: " + e.getMsg());
                        }
                        break;
                    case DEADLINE:
                        try {
                            String s = userInput.substring("deadline".length()).trim();
                            String[] s1 = s.split("/by");
                            if (s1.length > 2) {
                                throw new DukeException("Please enter format deadline (task) /by (input)");
                            } else if (s1[1].trim().isEmpty()) {
                                throw new DukeException("Empty timing. Please enter format deadline (task) /by (input)");
                            }
                            Task task = new Deadline(s1[0].trim(), s1[1].trim());
                            System.out.println(myList.addItem(task));
                        } catch (DukeException e) {
                            System.out.println("Error: " + e.getMsg());
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Please enter format deadline (task) /by (input)");
                        }
                        break;
                    case EVENT:
                        try {
                            String s = userInput.substring("event".length()).trim();
                            String[] s1 = s.split("/from");
                            if (s1.length > 2) {
                                throw new DukeException("Please enter format event (task) /from (input) /to (input)");
                            } else if (s1[1].trim().isEmpty()) {
                                throw new DukeException("Empty timing. Please enter format event (task) /from (input) /to (input)");
                            }
                            String[] s2 = s1[1].split("/to");
                            if (s2.length > 2) {
                                throw new DukeException("Please enter format event (task) /from (input) /to (input)");
                            } else if (s2[1].trim().isEmpty()) {
                                throw new DukeException("Empty timing. Please enter format event (task) /from (input) /to (input)");
                            }
                            Task task = new Event(s1[0].trim(), s2[0].trim(), s2[1].trim());
                            System.out.println(myList.addItem(task));
                        } catch (DukeException e) {
                            System.out.println("Error: " + e.getMsg());
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Please enter format event (task) /from (input) /to (input)");
                        }
                        break;
                    case DELETE:
                        try {
                            int index = Integer.parseInt(userInput.substring("delete".length()).trim());
                            System.out.println(myList.delete(index));
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a number after delete.");
                        }
                        break;
                    case INVALID:
                        System.out.println("OOPS! That was an invalid input");
                        break;
                }
            }

        } catch (IOException e) {
            System.err.println("Error");
        }
    }

    private static Request getRequest(String userInput) {
        String inputUpper = userInput.toUpperCase();

        for (Request request : Request.values()) {
            if (inputUpper.startsWith(request.name())) {
                return request;
            }
        }

        return Request.INVALID;
    }
}

import java.io.*;
import java.text.ParseException;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDateTime;
import java.util.regex.*;

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
        File file = new File("src/main/java/data/duke.txt");

        try {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                String s, s1, s2, date1 = "", date2= "";
                String[] d_t, d_t1;
                LocalDate d, d1;
                SimpleDateFormat inputFormat = new SimpleDateFormat("HHmm");
                SimpleDateFormat outputFormat = new SimpleDateFormat("h:mm a");
                Date date_1, date_2;

                while ((line = reader.readLine()) != null) {
                    String[] loadInput = line.split("\\|");
                    String type = loadInput[0].trim();
                    String done = loadInput[1].trim();
                    String taskString, byString, dateTimePattern, fromString, toString;
                    Task task;

                    switch (type) {
                        case "T":
                            taskString = loadInput[2].trim();
                            task = new Todo(taskString);

                            if (done.equals("1")) {
                                task.markAsDone();
                            }

                            myList.addItem(task);
                            break;
                        case "D":
                            taskString = loadInput[2].trim();
                            byString = loadInput[3].trim();
                            dateTimePattern = "\\d{4}-\\d{2}-\\d{2} \\d{4}";

                            if (Pattern.matches(dateTimePattern, byString)) {
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                                LocalDateTime dateTime = LocalDateTime.parse(byString, formatter);
                                task = new Deadline(taskString, dateTime);
                                myList.addItem(task);
                            } else {
                                throw new DukeException("Please enter date in the format (yyyy-mm-dd hhmm)");
                            }

                            if (done.equals("1")) {
                                task.markAsDone();
                            }
                            break;
                        case "E":
                            taskString = loadInput[2].trim();
                            fromString = loadInput[3].trim();
                            toString = loadInput[4].trim();
                            dateTimePattern = "\\d{4}-\\d{2}-\\d{2} \\d{4}";

                            if (Pattern.matches(dateTimePattern, fromString) && Pattern.matches(dateTimePattern, toString)) {
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                                LocalDateTime dateTimeFrom = LocalDateTime.parse(fromString, formatter);
                                LocalDateTime dateTimeTo = LocalDateTime.parse(toString, formatter);
                                task = new Event(taskString, dateTimeFrom, dateTimeTo);
                                myList.addItem(task);
                            } else {
                                throw new DukeException("Please enter date in the format (yyyy-mm-dd hhmm)");
                            }

                            if (done.equals("1")) {
                                task.markAsDone();
                            }
                            break;
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Error: File not Found");
            } catch (DukeException e) {
                System.out.println("Error: " + e.getMsg());
            }

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
                                throw new DukeException("Please enter format deadline (task) /by (yyyy-mm-dd hhmm)");
                            } else if (s1[1].trim().isEmpty()) {
                                throw new DukeException("Empty timing. Please enter format deadline (task) /by (yyyy-mm-dd hhmm)");
                            }

                            String taskString = s1[0].trim();
                            String byString = s1[1].trim();
                            String dateTimePattern = "\\d{4}-\\d{2}-\\d{2} \\d{4}";

                            if (Pattern.matches(dateTimePattern, byString)) {
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                                LocalDateTime dateTime = LocalDateTime.parse(byString, formatter);
                                Task task = new Deadline(taskString, dateTime);
                                System.out.println(myList.addItem(task));
                            } else {
                                throw new DukeException("Please enter date in the format (yyyy-mm-dd hhmm)");
                            }
                        } catch (DukeException e) {
                            System.out.println("Error: " + e.getMsg());
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Please enter format deadline (task) /by (yyyy-mm-dd hhmm)");
                        }
                        break;
                    case EVENT:
                        try {
                            String s = userInput.substring("event".length()).trim();
                            String[] s1 = s.split("/from");

                            if (s1.length > 2) {
                                throw new DukeException("Please enter format event (task) /from (yyyy-mm-dd hhmm) /to (yyyy-mm-dd hhmm)");
                            } else if (s1[1].trim().isEmpty()) {
                                throw new DukeException("Empty timing. Please enter format event (task) /from (yyyy-mm-dd hhmm) /to (yyyy-mm-dd hhmm)");
                            }

                            String[] s2 = s1[1].split("/to");

                            if (s2.length > 2) {
                                throw new DukeException("Please enter format event (task) /from (yyyy-mm-dd hhmm) /to (yyyy-mm-dd hhmm)");
                            } else if (s2[1].trim().isEmpty()) {
                                throw new DukeException("Empty timing. Please enter format event (task) /from (yyyy-mm-dd hhmm) /to (yyyy-mm-dd hhmm)");
                            }

                            String taskString = s1[0].trim();
                            String fromString = s2[0].trim();
                            String toString = s2[1].trim();
                            String dateTimePattern = "\\d{4}-\\d{2}-\\d{2} \\d{4}";

                            if (Pattern.matches(dateTimePattern, fromString) && Pattern.matches(dateTimePattern, toString)) {
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                                LocalDateTime dateTimeFrom = LocalDateTime.parse(fromString, formatter);
                                LocalDateTime dateTimeTo = LocalDateTime.parse(toString, formatter);
                                Task task = new Event(taskString, dateTimeFrom, dateTimeTo);
                                System.out.println(myList.addItem(task));
                            } else {
                                throw new DukeException("Please enter date in the format (yyyy-mm-dd hhmm)");
                            }
                        } catch (DukeException e) {
                            System.out.println("Error: " + e.getMsg());
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Please enter format event (task) /from (yyyy-mm-dd hhmm) /to (yyyy-mm-dd hhmm)");
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

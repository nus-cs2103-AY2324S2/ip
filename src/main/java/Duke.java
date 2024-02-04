import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Objects;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;



public class Duke {
    public static String formatDateTime(String inputStr) {
        String[] dateAndTimeParts = inputStr.split("\\s+");
        // Split the input into parts
        String[] dateParts = new String[0];
        if (inputStr.contains("-")) {
            dateParts = dateAndTimeParts[0].split("-");
        } else if (inputStr.contains("/")) {
            dateParts = dateAndTimeParts[0].split("/");
        }

        // Parse the date part
        int day = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int year = Integer.parseInt(dateParts[2]);

        // Parse the time part
        int hour = 0; // Default hour value
        int minute = 0; // Default minute value
        if (dateAndTimeParts.length > 1) {
            String timePart = dateAndTimeParts[1];
            hour = Integer.parseInt(timePart.substring(0, 2));
            minute = Integer.parseInt(timePart.substring(2));
        }

        // Create a LocalDateTime with the parsed parts
        LocalDateTime localDateTime = LocalDateTime.of(year, month, day, hour, minute);

        // Format the LocalDateTime as desired
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d 'of' MMMM yyyy, h:mma", Locale.ENGLISH);
        return localDateTime.format(outputFormatter);
    }
    public static void main(String[] args) {
        System.out.println("Hello! I'm PeWPeWPeW");

        ArrayList<Task> task_arr = new ArrayList<>();
        int index = 0;

        try {
            File file = new File("data/duke.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split("\\|");
                String type = line[0];
                boolean isDone = line[1].equals("1");
                String description = line[2];
                if (Objects.equals(type, "T")) {
                    task_arr.add(new ToDo(index, description));
                    if (isDone) {
                        task_arr.get(index).mark();
                    }
                    index++;
                } else if (Objects.equals(type, "D")) {
                    String deadline = line[3];
                    task_arr.add(new Deadline(index, description, deadline));
                    if (isDone) {
                        task_arr.get(index).mark();
                    }
                    index++;
                } else if (Objects.equals(type, "E")) {
                    String start = line[3];
                    String end = line[4];
                    task_arr.add(new Event(index, description, start, end));
                    if (isDone) {
                        task_arr.get(index).mark();
                    }
                    index++;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e);
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("What can I do for you?");
        String userInput = scanner.nextLine();

        while (!Objects.equals(userInput.toLowerCase(), "bye")) {
            try {
                DukeException.validateInstn(userInput);
                if (Objects.equals(userInput.toLowerCase(), "pewpewpew")) {
                    task_arr.add(new Task(index, userInput));
                    System.out.println("PeWPeWPeWPeWPeWPeWPeWPeWPeWPeWPeWPeW");
                    index++;
                } else if (Objects.equals(userInput.toLowerCase(), "list")) {
                    for (Task task : task_arr) {
                        System.out.println(task.getTask());
                    }
                } else if (userInput.toLowerCase().contains("unmark")) {
                    int markedIndex = Integer.parseInt(userInput.replaceAll("[^0-9]", "")) - 1;
                    try {
                        DukeException.validateArrIndex(markedIndex, task_arr);
                        task_arr.get(markedIndex).unmark();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(task_arr.get(markedIndex).getTask());
                    } catch (DukeException d) {
                        System.out.println("ERROR: " + d);
                    }
                } else if (userInput.toLowerCase().contains("mark")) {
                    int markedIndex = Integer.parseInt(userInput.replaceAll("[^0-9]", "")) - 1;
                    try {
                        DukeException.validateArrIndex(markedIndex, task_arr);
                        task_arr.get(markedIndex).mark();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(task_arr.get(markedIndex).getTask());
                    } catch (DukeException d) {
                        System.out.println("ERROR: " + d);
                    }
                } else if (userInput.toLowerCase().contains("delete")) {
                    int markedIndex = Integer.parseInt(userInput.replaceAll("[^0-9]", "")) - 1;
                    try {
                        DukeException.validateArrIndex(markedIndex, task_arr);

                        System.out.println("Noted. I've removed this task:");
                        System.out.println(task_arr.get(markedIndex).getTask());

                        task_arr.remove(markedIndex);

                        System.out.println("Now you have " + task_arr.size() + " tasks in the list");
                        
                        index--;
                    } catch (DukeException d) {
                        System.out.println("ERROR: " + d);
                    }
                } else if (userInput.toLowerCase().contains("todo")) {
                    try {
                        DukeException.validateToDo(userInput);
                        task_arr.add(new ToDo(index, userInput.substring(5)));
                        System.out.println("Got it. I've added this task:");
                        System.out.println(task_arr.get(index).getTask());
                        System.out.println("Now you have " + task_arr.size() + " tasks in the list");
                        index++;
                    } catch (DukeException d) {
                        System.out.println("ERROR: " + d);
                    }
                } else if (userInput.toLowerCase().contains("deadline")) {
                    try {
                        String[] str = userInput.split("/by ");
                        DukeException.validateDateTime(str[1]);
                        System.out.println(str[1]);
                        String deadline = "by " + formatDateTime(str[1]);
                        task_arr.add(new Deadline(index, str[0], deadline));
                        System.out.println("Got it. I've added this task:");
                        System.out.println(task_arr.get(index).getTask());
                        System.out.println("Now you have " + task_arr.size() + " tasks in the list");
                        index++;
                    } catch (DukeException d) {
                        System.out.println("ERROR: " + d);
                    }
                } else if (userInput.toLowerCase().contains("event")) {
                    try {
                        String[] front = userInput.split("/from ");
                        String[] back = front[1].split("/to ");
                        String start = back[0].trim();
                        String end = back[1];
                        DukeException.validateDateTime(start);
                        System.out.println("HALF");
                        DukeException.validateDateTime(end);
                        task_arr.add(new Event(index, front[0], formatDateTime(start), formatDateTime(end)));
                        System.out.println("Got it. I've added this task:");
                        System.out.println(task_arr.get(index).getTask());
                        System.out.println("Now you have " + task_arr.size() + " tasks in the list");
                        index++;
                    } catch (DukeException d) {
                        System.out.println("ERROR: " + d);
                    }
                }
                try {
                    FileWriter writer = new FileWriter("data/duke.txt");
                    for (Task task : task_arr) {
                        writer.write(task.save() + "\n");
                    }
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (DukeException d) {
                System.out.println("ERROR: " + d);
            }
            System.out.println("What else can I do for you? (try typing my name 3 times with no space in between)" );
            userInput = scanner.nextLine();

        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
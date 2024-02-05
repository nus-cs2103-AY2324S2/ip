package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class Parser {
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
    public static void parseUserInput(String userInput, ArrayList<Task> task_arr) throws DukeException {
        // Your existing parsing logic from the main method
        // ... (user input parsing logic)
        DukeException.validateInstn(userInput);
        int index = task_arr.size();
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
        // Add other parsing methods as needed
    }

    // Add other parsing methods as needed
}

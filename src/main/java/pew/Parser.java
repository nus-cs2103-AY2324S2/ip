package pew;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class Parser {
    /**
     * Converts the input string to LocalDateTime
     *
     * @param inputStr the input date and time string
     * @return the LocalDateTime
     */
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

    /**
     * Parses the user input and calls the appropriate method
     *
     * @param userInput the user input
     * @param taskList the list of tasks
     * @return the message result of the user input
     */
    public static String parseUserInput(String userInput, TaskList taskList) {
        try {
            PewException.validateInstn(userInput);
            int index = taskList.getTaskArr().size();
            InstructionType instr = InstructionType.valueOf(userInput.split(" ")[0].toUpperCase());

            switch (instr) {
                case PEWPEWPEW: {
                    return handlePewPewPew();
                }
                case FIND: {
                    return handleFind(userInput, taskList);
                }
                case LIST: {
                    return handleList(taskList);
                }
                case UNMARK: {
                    return handleUnmark(userInput, taskList);
                }
                case MARK: {
                    return handleMark(userInput, taskList);
                }
                case DELETE: {
                    return handleDelete(userInput, taskList);
                }
                case TODO: {
                    return handleTodo(userInput, taskList, index);
                }
                case DEADLINE: {
                    return handleDeadline(userInput, taskList, index);
                }
                case EVENT: {
                    return handleEvent(userInput, taskList, index);
                }
                default: {
                    return handleDefault(instr);
                }
            }
        } catch (PewException d) {
            return d.toString();
        }
    }

    private static String handleDefault(InstructionType instr) {
        ArrayList<InstructionType> instrArr = new ArrayList<>();
        Collections.addAll(instrArr, InstructionType.values());
        assert instrArr.contains(instr) : "Invalid instruction";
        return "Sorry, I don't understand. Please try again.";
    }

    private static String handlePewPewPew() {
        return "PeWPeWPeWPeWPeWPeWPeWPeWPeWPeWPeWPeW";
    }

    private static String handleFind(String userInput, TaskList taskList) {
        String keyword = userInput.substring(5);
        ArrayList<Task> results = taskList.findTasks(keyword);
        if (results.isEmpty()) {
            return "Sorry, I couldn't find any matching tasks in your list.";
        } else {
            StringBuilder message = new StringBuilder("Here are the matching tasks in your list: ");
            for (Task task : results) {
                message.append("\n").append(task.getTask());
            }
            return message.toString();
        }
    }

    private static String handleList(TaskList taskList) {
        return taskList.listAllTasks();
    }

    private static String handleUnmark(String userInput, TaskList taskList) {
        try {
            int markedIndex = Integer.parseInt(userInput.replaceAll("[^0-9]", ""));
            PewException.validateArrIndex(markedIndex, taskList.getTaskArr());
            taskList.unmarkTask(markedIndex);
            return "OK, I've marked this task as not done yet:\n" + taskList.printSelectedTask(markedIndex);
        } catch (PewException d) {
            return d.toString();
        }
    }

    private static String handleMark(String userInput, TaskList taskList) {
        try {
            int markedIndex = Integer.parseInt(userInput.replaceAll("[^0-9]", ""));
            PewException.validateArrIndex(markedIndex, taskList.getTaskArr());
            taskList.markTask(markedIndex);
            return "Nice! I've marked this task as done:\n" + taskList.printSelectedTask(markedIndex);
        } catch (PewException d) {
            return d.toString();
        }
    }

    private static String handleDelete(String userInput, TaskList taskList) {
        try {
            int markedIndex = Integer.parseInt(userInput.replaceAll("[^0-9]", ""));
            PewException.validateArrIndex(markedIndex, taskList.getTaskArr());
            String message = "Noted. I've removed this task:\n" + taskList.printSelectedTask(markedIndex) + "\n";
            taskList.deleteTask(markedIndex);
            message += "Now you have " + taskList.size() + " tasks in the list";
            return message;
        } catch (PewException d) {
            return d.toString();
        }
    }

    private static String handleTodo(String userInput, TaskList taskList, int index) {
        try {
            PewException.validateToDo(userInput);
            taskList.addTask(new ToDo(index, userInput.substring(5)));
            String message = "Got it. I've added this task:\n" + taskList.printSelectedTask(index + 1) + "\n";
            message += "Now you have " + taskList.size() + " tasks in the list";
            return message;
        } catch (PewException d) {
            return d.toString();
        }
    }

    private static String handleDeadline(String userInput, TaskList taskList, int index) {
        try {
            PewException.validateDeadline(userInput);
            String[] str = userInput.split("/by ");
            PewException.validateDateTime(str[1]);
            String deadline = "by " + formatDateTime(str[1]);
            taskList.addTask(new Deadline(index, str[0], deadline));
            String message = "Got it. I've added this task:\n" + taskList.printSelectedTask(index + 1) + "\n";
            message += "Now you have " + taskList.size() + " tasks in the list";
            return message;
        } catch (PewException d) {
            return d.toString();
        }
    }

    private static String handleEvent(String userInput, TaskList taskList, int index) {
        try {
            PewException.validateEvent(userInput);
            String[] front = userInput.split("/from ");
            String[] back = front[1].split("/to ");
            String start = back[0].trim();
            String end = back[1];
            PewException.validateDateTime(start);
            PewException.validateDateTime(end);
            taskList.addTask(new Event(index, front[0], formatDateTime(start), formatDateTime(end)));
            String message = "Got it. I've added this task:\n" + taskList.printSelectedTask(index + 1) + "\n";
            message += "Now you have " + taskList.size() + " tasks in the list";
            return message;
        } catch (PewException d) {
            return d.toString();
        }
    }
}

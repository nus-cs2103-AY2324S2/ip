package parser;
import jux.JuxException;
import tasklist.Task;
import tasklist.TaskList;
import ui.Ui;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Parser {
    public static final String TASK_TODO = "TODO";
    public static final String TASK_DEADLINE = "DEADLINE";
    public static final String TASK_EVENT = "EVENT";
    public static final String TASK_INVALID = "INVALID";


    /**
     * Takes input from user and finds the corresponding action
     * @param input input string
     * @param taskList the task list
     * @param ui the ui
     * @throws JuxException
     */
    public static String parsingInput(String input, TaskList taskList, Ui ui) throws JuxException {
        String command = findCommand(input);
        String output = "";
        switch (command) {
        case "list":
            output = getListString(taskList, ui);
            break;
        case "mark":
            output = getmarkString(input, taskList, ui);
            break;
        case "unmark":
            output = getunmarkString(input, taskList, ui);
            break;
        case "delete":
            output = getDeleteString(input, taskList, ui);
            break;
        case "add":
            output = getAddTaskString(input, taskList, ui);
            break;
        case "find":
            output = getFindString(input, taskList, ui);
            break;
        default:
            break;
        }
        return output;
    }

    private static String getListString(TaskList taskList, Ui ui) throws JuxException {
        String output;
        if (taskList.isEmpty()) {
            throw new JuxException(" YOUR LIST IS EMPTY");
        }
        output = taskList.showListWithIndexing(ui);
        return output;
    }

    private static String getmarkString(String input, TaskList taskList, Ui ui) throws JuxException {
        String output;
        if (input.length() > 5) {
            String listStringNumber = input.substring(5);
            // future error detection for non-numerals
            int convertedToNumber = Parser.convertStringIndexToIntZeroIndex(listStringNumber);
            // future error when list is empty
            if (convertedToNumber < 0 || convertedToNumber >= taskList.getSize()) {
                throw new JuxException("NUMBER NOT IN LIST, PLEASE ADD A TASK OR " +
                        "CHOOSE A DIFFERENT NUMBER WITHIN 1 AND"
                        + taskList.getSize());
            }
            if (taskList.isIndexedTaskChecked(convertedToNumber)) {
                throw new JuxException("TASK ALREADY MARKED");
            }
            taskList.toggleIndexedTask(convertedToNumber);
            output = taskList.printTaskMarked(ui, convertedToNumber);
        } else {
            throw new JuxException("PLEASE INSERT NUMBER TO MARK");
        }
        return output;
    }

    private static String getunmarkString(String input, TaskList taskList, Ui ui) throws JuxException {
        String output;
        String listStringNumber = input.substring(7);
        // future error detection for non-numerals
        int convertedToNumber = Parser.convertStringIndexToIntZeroIndex(listStringNumber);
        // future error when list is empty
        if (convertedToNumber < 0 || convertedToNumber >= taskList.getSize()) {
            throw new JuxException("NUMBER NOT IN LIST, PLEASE ADD A TASK OR " +
                    "CHOOSE A DIFFERENT NUMBER WITHIN 1 AND"
                    + taskList.getSize());
        }
        if (!taskList.isIndexedTaskChecked(convertedToNumber)) {
            throw new JuxException("TASK ALREADY UNMARKED");
        }
        taskList.toggleIndexedTask(convertedToNumber);
        output = taskList.printTaskUnMarked(ui, convertedToNumber);
        return output;
    }

    private static String getFindString(String input, TaskList taskList, Ui ui) {
        String output;
        String findTask = Parser.parseFind(input);
        ArrayList<Task> tasksFound = taskList.findTask(findTask);
        if (tasksFound.isEmpty()) {
            output = ui.printNotFound();
        } else {
            output = ui.printList(tasksFound);
        }
        return output;
    }

    private static String getAddTaskString(String input, TaskList taskList, Ui ui) throws JuxException {
        String output;
        String typeOfTask = Parser.typeOfTask(input);
        output = taskList.addTask(ui, typeOfTask, input);
        return output;
    }

    private static String getDeleteString(String input, TaskList taskList, Ui ui) throws JuxException {
        String output;
        String deleteListStringNumber =  input.substring(7);
        // future error detection for non-numerals
        int deleteConvertedToNumber = Parser.convertStringIndexToIntZeroIndex(deleteListStringNumber);
        // future error when list is empty
        if (deleteConvertedToNumber < 0 || deleteConvertedToNumber >= taskList.getSize()) {
            throw new JuxException("NUMBER NOT IN LIST, PLEASE ADD A TASK OR " +
                    "CHOOSE A DIFFERENT NUMBER WITHIN 1 AND"
                    + taskList.getSize());
        }
        output = taskList.deleteTask(ui, deleteConvertedToNumber);
        return output;
    }

    public static  String parseFind(String input) {
        return input.substring(5);
    }

    /**
     * Returns the type of task to add
     * @param input user input
     * @return the type of task
     */
    public static String typeOfTask(String input) {

        if (input.startsWith("todo")) {
            return TASK_TODO;
        } else if (input.startsWith("event")) {
            return TASK_EVENT;
        } else if (input.startsWith("deadline")) {
            return TASK_DEADLINE;
        } else {
            return TASK_INVALID;
        }
    }

    /**
     * Parse input for Todo constructor
     * @param input user input
     * @return todo description
     * @throws JuxException when description is empty
     */
    public static String parseTodo(String input) throws JuxException {
        if (input.length() > 5) {
            return input.substring(5);
        } else {
            throw new JuxException("PLEASE INSERT DESCRIPTION FOR YOUR TODO");
        }
    }

    /**
     * Parse input for Deadline constructor
     * Checks if input format is valid
     * @param input user input
     * @return string array containing the deadline constructor values
     * @throws JuxException if invalid time or missing description
     */
    public static String[] parseDeadline(String input) throws JuxException {
        if (input.length() >10) {
            if (!input.contains("/")) {
                throw new JuxException("insert time after deadline such as deadline /monday");
            }
            String desc = input.substring(9, input.indexOf("/"));
            String date = input.substring(input.indexOf("/") + 1);
            String[] strings = {desc, date};
            return strings;
        } else {
            throw new JuxException("PLEASE INSERT DESCRIPTION FOR YOUR DEADLINE");
        }
    }

    /**
     * Parse input for Event constructor
     * Checks if input format is valid
     * @param input user input
     * @return string array containing the event constructor values
     * @throws JuxException if invalid time or missing description
     */
    public static String[] parseEvent(String input) throws JuxException {
        if (input.length() >7 ) {
            String regex = ".*" + '/'+ ".*" + '/' + ".*";
            if (!input.matches(regex)) {
                throw new JuxException("insert time for event such as event /monday /sunday");
            }
            String desc = input.substring(6, input.indexOf("/"));
            String firstDate = input.substring(input.indexOf("/") + 1, input.lastIndexOf("/"));
            String endDate = input.substring(input.lastIndexOf("/") + 1);
            String [] strings ={desc, firstDate, endDate};
            return strings;
        } else {
            throw new JuxException("PLEASE INSERT DESCRIPTION FOR YOUR EVENT");
        }
    }
    /**
     * Finds the action that the user wants to execute
     * @param input user input
     * @return string containing the action
     * @throws JuxException if invalid action
     */
    public static String findCommand(String input) throws JuxException {
        if (input.startsWith("mark")) {
            return "mark";
        } else if (input.startsWith("unmark") || input.startsWith("unMark")) {
            return "unmark";
        } else if (input.startsWith("list")) {
            return "list";
        } else if (input.startsWith("delete")) {
            return "delete";
        } else if (input.startsWith("todo") ||input.startsWith("deadline")||input.startsWith("event")) {
            return "add";
        } else if (input.startsWith("find")) {
            return "find";
        } else {
                throw new JuxException("Enter a valid input");
        }
    }

    /**
     * Converts the input index provided by the user to an int
     * @param listStringNumber string index
     * @return int indedx
     * @throws JuxException if invalid string index
     */
    public static int convertStringIndexToIntZeroIndex(String listStringNumber) throws JuxException {
        try {
            return Integer.parseInt(listStringNumber) - 1;
        } catch (NumberFormatException e) {
            throw new JuxException(e.getMessage()); // might customise error message
        }
    }

    /**
     * Returns a boolean value of whether
     * the user wants to exit the program
     * @param input user input
     * @return boolean value depending if input is equal to bye
     */
    public static boolean isExit(String input) {
        return input.equals("bye") ? true : false;
    }

    /**
     * Convert the localDateTime variable in the task to
     * a presentable format
     * @param localDateTime date time stored in the task
     * @return formatted string to be printed
     */
    public static String convertDateTimeToStringUI(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        if(localDateTime.toLocalTime().equals(LocalTime.MIDNIGHT)) {
            formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        }

        return localDateTime.format(formatter);
    }

    /**
     * Converts the localDateTime date to string to be stored in the datafile
     * @param localDateTime date time stored in the task
     * @return formatted string to be stored
     */
    public static String convertDateTimeToStringStorage(LocalDateTime localDateTime) {
        String formattedDateTime = localDateTime.format(DateTimeFormatter.ISO_DATE_TIME);
        return formattedDateTime;
    }

    /**
     * Converts the String dateTime in the storage to LocalDateTime
     * @param date date string stored in storage
     * @return the local date time to be stored in the task
     */
    public static LocalDateTime storageConvertToDateTime(String date) {
        return LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME);
    }

    /**
     * Converts the String input to LocalDateTime
     * @param date String date input
     * @return local date time format
     * @throws DateTimeException if invalid format
     */
    public static LocalDateTime inputConvertToDateTime(String date) throws DateTimeException{
        try {
            String timeFormat = date;
            if (date.contains(":")) {
                int timeIndex = Util.findNthDividerIndex(date, ' ', 1);
                timeFormat = date.substring(0, timeIndex) + "T" + date.substring(timeIndex + 1);
            } else {
                timeFormat = timeFormat + "T00:00";
            }

            //System.out.println(timeFormat);
            LocalDateTime localDateTime = LocalDateTime.parse(timeFormat,DateTimeFormatter.ISO_DATE_TIME);
            return localDateTime;
        } catch (DateTimeException e) {
            throw new DateTimeException("enter using the format:/2019-10-15 or /2019-10-15 18:30" );
        }
    }
}


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
    public static final String TODO = "TODO";
    public static final String DEADLINE = "DEADLINE";
    public static final String EVENT = "EVENT";
    public static final String INVALID = "INVALID";

    public static void parsingInput(String input, TaskList taskList, Ui ui) throws JuxException {
        String command = findCommand(input);
        switch (command) {
            case "list":
                if (taskList.isEmpty()) {
                    throw new JuxException(" YOUR LIST IS EMPTY");
                }
                taskList.showList(ui);
                break;
            case "mark":
                if (input.length() > 5 ) {
                    String listStringNumber =  input.substring(5);
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
                        taskList.printTaskMarked(ui, convertedToNumber);
                } else {
                    throw new JuxException("PLEASE INSERT NUMBER TO MARK");
                }
                break;
            case "unmark":
                String listStringNumber =  input.substring(7);
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
                taskList.printTaskUnMarked(ui, convertedToNumber);
                break;
            case "delete":
                String deleteListStringNumber =  input.substring(7);
                // future error detection for non-numerals
                int deleteConvertedToNumber = Parser.convertStringIndexToIntZeroIndex(deleteListStringNumber);
                // future error when list is empty
                if (deleteConvertedToNumber < 0 || deleteConvertedToNumber >= taskList.getSize()) {
                    throw new JuxException("NUMBER NOT IN LIST, PLEASE ADD A TASK OR " +
                            "CHOOSE A DIFFERENT NUMBER WITHIN 1 AND"
                            + taskList.getSize());
                }
                taskList.deleteTask(ui,deleteConvertedToNumber);
                break;
            case "add":
                String typeOfTask = Parser.typeOfTask(input);
                taskList.addTask(ui, typeOfTask, input);
                break;
        case "find":
            String findTask = Parser.parseFind(input);
            ArrayList<Task> tasksFound = taskList.findTask(findTask);
            if (tasksFound.isEmpty()) {
                ui.printNotFound();
            } else {
                ui.printList(tasksFound);
            }
            break;
                default:
                break;
        }
    }
    public static  String parseFind(String input) {
        return input.substring(5);
    }
    public static String typeOfTask(String input) {

        if (input.startsWith("todo")) {
            return TODO;
        } else if (input.startsWith("event")) {
            return EVENT;
        } else if (input.startsWith("deadline")) {
            return DEADLINE;
        } else {
            return INVALID;
        }
    }
    public static String parseTodo(String input) throws JuxException {
        if (input.length() > 5) {
            return input.substring(5);
        } else {
            throw new JuxException("PLEASE INSERT DESCRIPTION FOR YOUR TODO");
        }
    }
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
    public static String formattedInputOfTask(String typeOfTask, String input) throws JuxException {
        try {
            String formattedInput = "";
            switch (typeOfTask) {
                case TODO:
                    formattedInput = input.substring(5);
                    break;
                case EVENT:
                    formattedInput = input.substring(6);
                    break;
                case DEADLINE:
                    formattedInput = input.substring(9);
                    break;
                default:
                    formattedInput = "invalid";
            }
            return formattedInput;
        } catch(IndexOutOfBoundsException e) {
            throw new JuxException("Enter the ask u want to add in the format of todo/event/deadline, whitespace" +
                    "and the task u want");
        }

    }
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
    public static int convertStringIndexToIntZeroIndex(String listStringNumber) throws JuxException {
        try {
            return Integer.parseInt(listStringNumber) - 1;
        } catch (NumberFormatException e) {
            throw new JuxException(e.getMessage()); // might customise error message
        }
    }
    public static boolean isExit(String input) {
        return input.equals("bye") ? true : false;
    }

    public static boolean isDateTime(String date) {
        String time = date.substring(date.length()-4);
        if (time.contains(":")) {
            return true;
        } else {
            return false;
        }
    }

    public static String convertDateTimeToStringUI(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        if(localDateTime.toLocalTime().equals(LocalTime.MIDNIGHT)) {
            formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        }

        return localDateTime.format(formatter);
    }
    public static String convertDateTimeToStringStorage(LocalDateTime localDateTime) {
        String formattedDateTime = localDateTime.format(DateTimeFormatter.ISO_DATE_TIME);
        return formattedDateTime;
    }
    public static LocalDateTime storageConvertToDateTime(String date) {
        return LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME);
    }


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


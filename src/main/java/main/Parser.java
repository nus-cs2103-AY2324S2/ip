package main;

import Objects.Deadline;
import Objects.Event;
import Objects.Task;
import Objects.Todo;
import javafx.util.Pair;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * A parse class which makes sense of the input of user
 */
public class Parser {
    private TaskList taskList;

    public Parser(TaskList taskList){
        this.taskList = taskList;
    }

    /**
     * Processes the output after the input is processed
     * Writes to file if list is changed
     * Outputs the response of the command
     *
     * @param pair pair of a boolean if list is changed and response
     * @return response of the specific command
     */
    public String output(Pair<Boolean,String> pair) {
        if(pair.getKey()) {
            try {
                Storage.writeToFile(taskList);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return pair.getValue();
    }

    /**
     * Processes the string into commands and response
     *
     * @param str input string to be processed
     * @return output of response
     */
    public String processInput(String str) {
        try {
            String[] splitStr = str.split("\\s+", 2);
            assert splitStr != null : "Input string is null";
            switch (splitStr[0].toLowerCase()) {
            case "bye":
                System.exit(0);
                return Ui.bye();
            case "list":
                return taskList.printList();
                case "help":
                return Ui.help();
            case "mark":
                return output(taskList.mark(Integer.parseInt(splitStr[1])-1));
            case "unmark":
                return output(taskList.unmark(Integer.parseInt(splitStr[1])-1));
            case "delete":
                return output(taskList.remove(Integer.parseInt(splitStr[1])-1));
            case "todo":
                return output(addTodo(splitStr));
            case "deadline":
                return output(addDeadline(splitStr));
            case "event":
                return output(addEvent(splitStr));
            case "find":
                return find(splitStr);
            default:
                return "huh? what did you say?";
            }
        } catch (ArrayIndexOutOfBoundsException E1) {
            return "No input found after action";
        }
    }

    /**
     * Adds to do task according to the split string
     *
     * @param splitStr String of input split into command and name
     * @return Pair of boolean (if list is changed) and response
     */
    public Pair<Boolean, String> addTodo(String[] splitStr) {
        assert splitStr[1] != null :"No input after action";
        if (splitStr.length == 2) {
            Task todo1 = new Todo(splitStr[1],false);
            taskList.addTask(todo1);

            return new Pair<>(true,Ui.success(todo1));
        } else {
            return new Pair<>(false,Ui.error());
        }
    }

    /**
     * Adds deadline task according to the split string
     *
     * @param splitStr String of input split into command,name and deadline
     * @return Pair of boolean (if list is changed) and response
     */
    public Pair<Boolean, String> addDeadline(String[] splitStr) {
        try {
            if (splitStr.length != 2) {
                return new Pair<>(false, Ui.error());
            }

            String[] splitStrDeadline = splitStr[1].split("/by", 2);

            if (splitStrDeadline.length != 2) {
                return new Pair<>(false, Ui.deadlineError());
            }

            Task deadline1 = new Deadline(splitStrDeadline[0], false, LocalDate.parse(splitStrDeadline[1].trim()));
            taskList.addTask(deadline1);

            return new Pair<>(true, Ui.success(deadline1));
        } catch (DateTimeException e){
            return new Pair<>(false, "Wrong date format. Use : yyyy-mm-dd");
        }
    }

    /**
     * Adds event task according to the split string
     *
     * @param splitStr String of input split into command, name, startDate and endDate
     * @return Pair of boolean (if list is changed) and response
     */
    public Pair<Boolean, String> addEvent(String[] splitStr) {
        try {
            if (splitStr.length != 2) {
                return new Pair<>(false, Ui.error());
            }

            String[] splitStrEvent = splitStr[1].split("/from|/to");

            if (splitStrEvent.length != 3) {
                return new Pair<>(false, Ui.eventError());

            }
            Task event1 = new Event(splitStrEvent[0], false, LocalDate.parse(splitStrEvent[1].trim()), LocalDate.parse(splitStrEvent[2].trim()));
            taskList.getList().add(event1);

            return new Pair<>(true, Ui.success(event1));
        } catch (DateTimeException e) {
            return new Pair<>(false, "Wrong date format. Use : yyyy-mm-dd");
        }
    }

    /**
     * Finds task according to the input of split string
     *
     * @param splitStr String of input split into command, name
     * @return String of list if name is found, error if name is not found
     */
    public String find(String[] splitStr) {
        if (splitStr.length == 2) {
            assert splitStr[1] != null : "no input after find";
            return taskList.printList(taskList.findList(splitStr[1]));
        } else {
            return Ui.error();
        }
    }
}

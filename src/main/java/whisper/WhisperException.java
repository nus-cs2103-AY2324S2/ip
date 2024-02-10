package whisper;
import java.util.*;

public class WhisperException extends Exception {
    public WhisperException(String err) {
        super(err);
    }

    // Invalid command
    public static WhisperException unknownCommand() {
        return new WhisperException("\nInvalid Command, please try again.\nWe only support commands such as:" +
                "\n- todo [description]\n- event [description] /from [start] /to [end]" +
                "\n- deadline [description] /by[time]\n- mark [taskID]\n- unmark [taskID]\n- delete [taskID]\n");
    }

    // tasks has more than 100 task
    public static WhisperException taskFull() {
        return new WhisperException("\nSorry, list is full. Can't add anymore.\n");
    }

    // Invalid Event format
    public static WhisperException invalidEvent() {
        return new WhisperException("\nInvalid format. Please enter again (event [description] /from [start] /to [end]).\n");
    }

    // Invalid Todo format
    public static WhisperException invalidTodo() {
        return new WhisperException("\nInvalid format. Please enter again (todo [description]).\n");
    }

    // Invalid Deadline format
    public static WhisperException invalidDeadline() {
        return new WhisperException("\nInvalid format. Please enter again (deadline [description] /by[time]).\n");
    }

    // Invalid Task ID
    public static WhisperException invalidTaskID() {
        return new WhisperException("\nInvalid task number. Please try again!");
    }

    // handles corrupted file content
    public static WhisperException invalidFileFormat() {
        return new WhisperException("\nError parsing task from file.");
    }

}
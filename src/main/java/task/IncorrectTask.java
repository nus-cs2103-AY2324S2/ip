package task;

import parser.ParseExecutionable;

/**
 * Represents an incorrect task when parsing input.
 * This object is used to handle situations where the input is not valid or recognized.
 */
public class IncorrectTask implements ParseExecutionable {

    private String message;

    /**
     * Creates a ActionTask object.
     * Will save the error message in it to print when executed.
     *
     * @param message The error message to print.
     */
    public IncorrectTask(String message) {
        this.message = message;
    }

    /**
     * Executes the necessary action by printing out the error message.
     * @param taskStorage The storage space where the action will take place.
     */
    @Override
    public String execute(TaskStorage taskStorage) {
        return this.message;
    }
}

package handler;

import action.*;
import exception.InvalidCommandException;

public class InputHandler {
    public static final InputHandler instance = new InputHandler();
    private InputHandler() {}
    public Action handleInput(String input) throws InvalidCommandException{
        String[] parsedInput = input.split(" ");
        switch (parsedInput[0]) {
            case "list":
                return new ListAction();
            case "mark":
                if (parsedInput.length != 2) {
                    throw new InvalidCommandException("Incorrect number of arguments");
                }
                return new MarkAction(parsedInput[1]);
            case "unmark":
                if (parsedInput.length != 2) {
                    throw new InvalidCommandException("Incorrect number of arguments");
                }
                return new UnmarkAction(parsedInput[1]);
            default:
                return new EchoAction(input);
        }
    }
}

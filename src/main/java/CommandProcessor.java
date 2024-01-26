import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class CommandProcessor {
    ArrayList<Task> list = new ArrayList<>();
    Storage storage;

    public CommandProcessor(Storage storage) {
        this.storage = storage;
    }

    public void processData(Command command, String input) throws InputException, ProcessingException {

        switch (command) {
            case DELETE:
                storage.delete(processDelete(input));
                break;

            case LIST:
                displayList();
                break;

            case MARK:
                storage.mark(processMark(input, Command.MARK));
                break;

            case UNMARK:
                storage.unmark(processMark(input, Command.UNMARK));
                break;

            case DEADLINE:
                storage.add(processDeadline(input));
                break;
            
            case EVENT:
                storage.add(processEvent(input));
                break;

            case TODO:
                storage.add(processTodo(input));
                break;

            default:
                break;
        }
        
        storage.update();
        return;
    }

    public Command processCommand(String input) throws InputException {
        try {
            String commandString = input.split(" ")[0];
            Command command = Command.valueOf(commandString.toUpperCase());
            return command;
        } catch (IndexOutOfBoundsException e) {
            String message = "Something went wrong when processing your command: \n"
            + "Check your input again: " + input;
            throw  new InputException(message, e);
        } catch (IllegalArgumentException e) { 
            String message = "You inputted an unrecognizable command";
            throw new InputException(message);
        }
    }

    public Integer processDelete(String input) throws InputException {
        try {
            int i = Integer.parseInt(input.split(" ")[1]) - 1;
            return i;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw InputException.exceptionCommandProcessing(Command.DELETE, input, e);
        }
    }
    public Task processDeadline(String input) throws InputException {
        try {
            String restOfInput = input.substring(8);
            String[] splitInput = restOfInput.split(" /by ");

            String taskName = splitInput[0];
            LocalDateTime by = TimeProcessor.fromString(splitInput[1]);

            return new Deadline(taskName, by);

        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            throw InputException.exceptionCommandProcessing(Command.DEADLINE, input, e);
        }
    }

    public Task processTodo(String input) throws InputException {
        try {
            String taskName = input.substring(4);
            return new Todo(taskName);
        } catch (IndexOutOfBoundsException e) {
            throw InputException.exceptionCommandProcessing(Command.TODO, input, e);
        }
    }

    public Task processEvent(String input) throws InputException {
        try {
            String restOfInput = input.substring(5);
            String[] splitFrom = restOfInput.split(" /from ");
            String[] fromTo = splitFrom[1].split(" /to ");
    
            String taskName = splitFrom[0];
            LocalDateTime from = TimeProcessor.fromString(fromTo[0]);
            LocalDateTime to = TimeProcessor.fromString(fromTo[1]);
            
            return new Event(taskName, from, to);

        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            throw InputException.exceptionCommandProcessing(Command.EVENT, input, e);
        }
    }

    public Integer processMark(String input, Command cmd) throws InputException {
        try {
            int i = Integer.parseInt(input.split(" ")[1]) - 1;
            return i;

        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw InputException.exceptionCommandProcessing(cmd, input, e);
        }
    }
    
    public void displayList() {
        storage.displayList();
    }
}
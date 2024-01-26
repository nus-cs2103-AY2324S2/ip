import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class CommandProcessor {
    ArrayList<Task> list = new ArrayList<>();
    Storage storage;
    String dateFormat = "dd-MM-yyyy";
    String timeFormat = "HH:mm";

    public CommandProcessor(Storage storage) {
        this.storage = storage;
    }

    public void processData(String input) {
        try {
            String command = input.split(" ")[0];
            switch (command) {
                case "delete":
                    storage.delete(processDelete(input));
                    break;

                case "list":
                    displayList();
                    break;

                case "mark":
                    storage.mark(processMark(input));
                    break;

                case "unmark":
                    storage.unmark(processMark(input));
                    break;

                case "deadline":
                    storage.add(processDeadline(input));
                    break;
                
                case "event":
                    storage.add(processEvent(input));
                    break;

                case "todo":
                    storage.add(processTodo(input));
                    break;

                default:
                    String message = String.format("I'm sorry I didn't quite get \"%s\"", input);
                    throw new InputException(message);
            }
            
            storage.update();

        } catch (ProcessingException | InputException e) {
            System.out.println(e.getMessage());
        }
        

        return;
    }


    public Integer processDelete(String input) throws InputException {
        try {
            int i = Integer.parseInt(input.split(" ")[1]) - 1;
            return i;

        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            String message = "Something went wrong when processing your delete command: \n"
            + "Check your input again: " + input;
            throw new InputException(message, e);
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
            String message = "Something went wrong when processing your deadline command: \n"
                                + "Check your input again: " + input;
            throw new InputException(message, e);
        }
    }

    public Task processTodo(String input) throws InputException {
        try {
            String taskName = input.substring(4);
            return new Todo(taskName);
        } catch (IndexOutOfBoundsException e) {
            String message = "Something went wrong when processing your todo command: \n"
                                + "Check your input again: " + input;
            throw new InputException(message, e);
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
            String message = "Something went wrong when processing your event command: \n"
                                + "Check your input again: " + input;
            throw new InputException(message, e);
        }
    }

    public Integer processMark(String input) throws InputException {
        try {
            int i = Integer.parseInt(input.split(" ")[1]) - 1;
            return i;

        } catch (Exception e) {
            String message = "Something went wrong when processing your mark command: \n"
                                + "Check your input again: " + input;
            throw new InputException(message, e);
        }
    }
    
    public void displayList() {
        storage.displayList();
    }
}
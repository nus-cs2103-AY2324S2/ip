import java.util.ArrayList;
public class Parser {

    public Parser() {
    }


    public String parseCommand(String instruction) {
        String[] sentence = instruction.split(" ", 2);
        String command = sentence[0];
        return command;
    }

    public String parseDesription(String instruction) {
        String[] sentence = instruction.split(" ", 2);
        String description = sentence[1];
        return description;
    }

    public String parseModify(String instruction) {
        String[] sentence = instruction.split(" ", 2);
        if (sentence.length == 1) {
            throw new IllegalArgumentException("Please enter an index.");
        } else {
            
            return sentence[1];
        }
    }

    public String parseTodo(String instruction) {
        String[] sentence = instruction.split(" ", 2);
        if (sentence.length == 1) {
            throw new IllegalArgumentException("Your input is incomplete. Please add more details for " + instruction);
        } else {
            return sentence[1];
        }
    }

    public String[] parseDeadline(String instruction) {
        String[] sentence = instruction.split(" ", 2);
        if (sentence.length == 1) {
            throw new IllegalArgumentException("Your input is incomplete. Please add more details for " + instruction);
        } else {
            
            String[] deadline = sentence[1].split(" /by ");
            return deadline;
        }
    }

    public String[] parseEvent(String instruction) {
        String[] sentence = instruction.split(" ", 2);
        if (sentence.length == 1) {
            throw new IllegalArgumentException("Your input is incomplete. Please add more details for " + instruction);
        } else {
            
            String[] arr = sentence[1].split(" /from "); // split task into description and deadline
            String[] arr1 = arr[1].split(" /to "); // split deadline into from and to
            String[] description = { arr[0],arr1[0], arr1[1] };
            return description;
        }
    }

}

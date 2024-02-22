package area;

/**
 * Parser checks the instruction given and divides it into smaller chunks.
 */
public class Parser {

    public Parser() {
    }

    /**
     * takes in raw input and extracts type of instruction to be performed.
     * @param instruction
     * @return String
     */
    public String parseCommand(String instruction) {
        String[] sentence = instruction.split(" ", 2);
        String command = sentence[0];
        return command;
    }

    /**
     * takes in raw input and extracts description of instruction
     * @param instruction
     * @return
     */
    public String parseDesription(String instruction) {
        String[] sentence = instruction.split(" ", 2);
        String description = sentence[1];
        return description;
    }

    /**
     * takes in raw input and extracts changes to be made by instruction
     * @param instruction
     * @return String
     */
    public String parseModify(String instruction) {
        String[] sentence = instruction.split(" ", 2);
        if (sentence.length == 1) {
            throw new IllegalArgumentException("Please enter an index.");
        } else {

            return sentence[1];
        }
    }

    /**
     * extracts description of task of ToDo type
     * @param instruction
     * @return String
     */
    public String parseTodo(String instruction) {
        String[] sentence = instruction.split(" ", 2);
        if (sentence.length == 1) {
            throw new IllegalArgumentException("Your input is incomplete. Please add more details for " + instruction);
        } else {
            return sentence[1];
        }
    }

    /**
     * extracts description of task of Deadline type
     * @param instruction
     * @return String[]
     */
    public String[] parseDeadline(String instruction) {
        String[] sentence = instruction.split(" ", 2);
        if (sentence.length == 1) {
            throw new IllegalArgumentException("Your input is incomplete. Please add more details for " + instruction);
        } else {

            String[] deadline = sentence[1].split(" /by ");
            return deadline;
        }
    }

    /**
     * extracts description of task of Event type
     * @param instruction
     * @return String[]
     */
    public String[] parseEvent(String instruction) {
        String[] sentence = instruction.split(" ", 2);
        if (sentence.length == 1) {
            throw new IllegalArgumentException("Your input is incomplete. Please add more details for " + instruction);
        } else {

            String[] arr = sentence[1].split(" /from "); // split task into description and deadline
            String[] arr1 = arr[1].split(" /to "); // split deadline into from and to
            String[] description = { arr[0], arr1[0], arr1[1] };
            return description;
        }
    }

    /**
     * takes in an instruction and checks if the keyword exists.
     * @param instruction
     * @return String
     */
    public String parseKeyword(String instruction) {
        assert instruction != null : "instruction cannot be null";
        String[] sentence = instruction.split(" ", 2);
        if (sentence.length == 1) {
            throw new IllegalArgumentException("Your input is incomplete. I need a keyword to start my search.");
        } else {
            return sentence[1];
        }
    }

}

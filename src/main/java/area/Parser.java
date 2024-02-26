package area;

/**
 * Parser checks the instruction given and divides it into smaller chunks.
 */
public class Parser {

    public Parser() {
    }

    /**
     * Takes in raw input and extracts type of instruction to be performed.
     * 
     * @param instruction User input string.
     * @return Command by user.
     */
    public String parseCommand(String instruction) {
        String[] sentence = instruction.split(" ", 2);
        String command = sentence[0];
        return command;
    }

    /**
     * Takes in raw input and extracts description of instruction.
     * 
     * @param instruction User input string.
     * @return Description of instruction.
     */
    public String parseDesription(String instruction) {
        String[] sentence = instruction.split(" ", 2);
        String description = sentence[1];
        return description;
    }

    /**
     * Takes in raw input and extracts changes to be made by instruction.
     * 
     * @param instruction User input.
     * @return String representation of index of task in list to be modified.
     * @throws IllegalArgumentException If no change information provided or
     *                                  incorrect format.
     */
    public String parseModify(String instruction) {
        String[] sentence = instruction.split(" ");
        if (sentence.length == 1) {
            throw new IllegalArgumentException("Please enter an index.");
        } else {

            return sentence[1];
        }
    }

    /**
     * Extracts description of task of ToDo type
     * 
     * @param instruction User input.
     * @return Descripition of Todo task.
     * @throws IllegalArgumentException If no proper description by user.
     */
    public String parseTodo(String instruction) {
        String[] sentence = instruction.split(" ", 2);
        if (sentence.length == 1) {
            throw new IllegalArgumentException("Your input is incomplete. Please add more details for "
                    + instruction);
        } else {
            return sentence[1];
        }
    }

    /**
     * Extracts description of task of Deadline type.
     * 
     * @param instruction User input.
     * @return Array of description and deadline.
     * @IllegalArgumentException If no valid deadlines are provided by user.
     */
    public String[] parseDeadline(String instruction) {
        String[] sentence = instruction.split(" ", 2);
        if (sentence.length == 1) {
            throw new IllegalArgumentException("Your input is incomplete. Please add more details for "
                    + instruction);
        } else {
            if (sentence[1].contains(" /by ") == false) {
                throw new IllegalArgumentException("Your input is incomplete. Please add more details for "
                        + instruction);
            }
            String[] deadline = sentence[1].split(" /by ");
            return deadline;
        }
    }

    /**
     * Extracts description of task of Event type.
     * 
     * @param instruction User input
     * @return String Array of description and start and end times/dates of event.
     * @throws IllegalArgumentException If not vaid Event details are provided by
     *                                  user.
     */
    public String[] parseEvent(String instruction) {
        String[] sentence = instruction.split(" ", 2);
        if (sentence.length == 1) {
            throw new IllegalArgumentException("Your input is incomplete.Please add more details for "
                    + instruction);
        } else {
            if (sentence[1].contains(" /from ") == false) {
                throw new IllegalArgumentException("Your input is incomplete.Please add more details for "
                        + instruction);
            }
            String[] arr = sentence[1].split(" /from "); // split task into description and deadline
            String[] arr1 = arr[1].split(" /to "); // split deadline into from and to
            String[] description = { arr[0], arr1[0], arr1[1] };
            return description;
        }

    }

    /**
     * Takes in an instruction and checks if the keyword exists.
     * 
     * @param instruction User input
     * @return Keyword to be searched in list.
     * @throws IllegalArgumentException If no valid keyword is provided by user.
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

    /**
     * Returns the String representation of the Priority Level to be changed for a
     * particular task.
     * 
     * @param instruction User instructions to determine priority of task.
     * @return String representation of new priority level.
     * @throws IllegalArgumentException If no valid priority level or index is
     *                                  provided by user.
     */
    public String parsePriority(String instruction) {
        String[] sentence = instruction.split(" ", 3);
        if (sentence.length != 3) {
            throw new IllegalArgumentException("Your input is incomplete. I need a priority for this task.");
        } else {
            return sentence[2];
        }
    }

}

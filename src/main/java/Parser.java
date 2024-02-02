public class Parser {
    /**
     * Parses the user input into an array of strings.
     *
     * @param userInput The user input.
     * @return An array of strings containing the instruction and details.
     */
    public String[] parseUserInput(String userInput) {
        String[] inputArray = userInput.split(" ", 2);
        String instruction;
        String details = "";
        if (inputArray.length > 1) {
            instruction = inputArray[0];
            details = inputArray[1];
        } else {
            instruction = inputArray[0];
            details = "";
        }

        return new String[]{instruction, details};
    }

    /**
     * Parses the deadline details into an array of strings.
     *
     * @param details The details of the deadline task.
     * @return An array of strings containing the deadline name and deadline.
     * @throws JimmyException If the deadline details are invalid.
     */
    public String[] parseDeadlineDetails(String details) throws JimmyException {
        if (!details.contains(" /by ")) {
            throw new JimmyException("Sorry! " +
                    "Please use the correct format when creating a new event.");
        }
        return details.split(" /by ", 2);
    }

    /**
     * Parses the event details into an array of strings.
     *
     * @param details The details of the event task.
     * @return An array of strings containing the event name, start time and end time.
     * @throws JimmyException If the event details are invalid.
     */
    public String[] parseEventDetails(String details) throws JimmyException {
        if (!details.contains(" /from ") || !details.contains(" /to ")) {
            throw new JimmyException("Sorry! " +
                    "Please use the correct format when creating a new event.");
        }
        return details.split(" /from | /to ");
    }
}

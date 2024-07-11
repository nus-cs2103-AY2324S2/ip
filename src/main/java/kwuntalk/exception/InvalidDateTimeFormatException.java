package kwuntalk.exception;


/**
 * Represents an exception when an invalid format of the DateTime is used.
 */
public class InvalidDateTimeFormatException extends KwunTalkException {
    private String command;


    /**
     * Constructor for InvalidDateTimeFormatException.
     *
     * @param command Command that threw the exception.
     */
    public InvalidDateTimeFormatException(String command) {
        this.command = command;
    }


    /**
     * Return the string representation of the exception.
     *
     * @return String representation of the exception.
     */
    @Override
    public String toString() {
        if (command.equals("DEADLINE")) {
            return String.format("%s The format of the Date/Time is invalid! ;(\n"
                            + "Please follow this format: [/by dd/MM/yyyy HHmm]\n",
                    super.toString());
        } else { // "EVENT"
            return String.format("%s The format of the Date/Time is invalid! ;(\n"
                            + "Please follow this format: [/from dd/MM/yyyy HHmm /to dd/MM/yyyy HHmm]\n",
                    super.toString());
        }
    }
}

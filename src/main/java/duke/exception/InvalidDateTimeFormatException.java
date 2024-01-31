package duke.exception;

public class InvalidDateTimeFormatException extends DukeException {
    private String command;

    public InvalidDateTimeFormatException(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        if (command.equals("DEADLINE")) {
            return String.format("%s The format of the Date/Time is invalid! ;(\n" +
                            "Please follow this format: [/by dd/MM/yyyy HHmm]\n",
                    super.toString());
        } else { // "EVENT"
            return String.format("%s The format of the Date/Time is invalid! ;(\n" +
                            "Please follow this format: [/from dd/MM/yyyy HHmm /to dd/MM/yyyy HHmm]\n",
                    super.toString());
        }
    }
}

package DukeException;

public class MissingArgumentsException extends Exception{
    public MissingArgumentsException(String string) {
        super(string);
    }
    @Override
    public String getMessage() {
        return "    MissingArgumentException\n    You have missing arguments:\n" +
                "    Try " + super.getMessage();
    }
}

package duke.dukeException;

public class WrongTimeFormatException extends Exception{
    public WrongTimeFormatException(String string) {
       super(string);
    }
    @Override
    public String getMessage() {
        return "    " + super.getMessage() + "\n    Try again using the format {day month year} using " +
                "\n    space, dash or slash as delimiters";
    }
}

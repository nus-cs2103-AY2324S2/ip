package DukeException;

public class ListOutofBoundsException extends Exception{
    public ListOutofBoundsException(String string) {
        super(string);
    }

    @Override
    public String getMessage() {
        return "    ListOutofBoundsException\n    You have entered an invalid list index:\n" +
                "    List size:" + super.getMessage();
    }
}

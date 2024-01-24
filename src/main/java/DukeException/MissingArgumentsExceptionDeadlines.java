package DukeException;

public class MissingArgumentsExceptionDeadlines extends MissingArgumentsExceptionTodo{
    public MissingArgumentsExceptionDeadlines(String string) {
        super(string);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " /by {your deadline}";
    }

}

package DukeException;

public class MissingArgumentsExceptionEvents extends MissingArgumentsExceptionTodo{
    public MissingArgumentsExceptionEvents(String string) {
        super(string);
    }
    @Override
    public String getMessage() {
        return super.getMessage() + " /from {your starting time} /to {your ending time}";
    }
}

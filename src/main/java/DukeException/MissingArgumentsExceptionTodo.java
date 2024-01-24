package DukeException;

public class MissingArgumentsExceptionTodo extends MissingArgumentsException {
    public MissingArgumentsExceptionTodo(String string) {
       super(string);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " {your item}";
    }

}

package DukeException;

public class MissingArgumentsExceptionMarking extends MissingArgumentsException{
    public MissingArgumentsExceptionMarking(String string) {
        super(string);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " {index of item in list}";
    }
}

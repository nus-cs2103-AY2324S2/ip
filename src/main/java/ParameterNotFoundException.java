public class ParameterNotFoundException extends BobException {
    public ParameterNotFoundException(String parameter) {
        super(Replies.PARAMETER_NOT_FOUND + parameter);
    }
}

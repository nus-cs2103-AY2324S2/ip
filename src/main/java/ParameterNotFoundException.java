public class ParameterNotFoundException extends BobException {
    public ParameterNotFoundException(String parameter) {
        super(String.format(Replies.PARAMETER_NOT_FOUND, parameter));
    }
}

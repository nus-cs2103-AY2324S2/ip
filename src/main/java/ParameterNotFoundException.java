public class ParameterNotFoundException extends BobException {
    public ParameterNotFoundException(String parameter) {
        super(String.format(Ui.PARAMETER_NOT_FOUND, parameter));
    }
}

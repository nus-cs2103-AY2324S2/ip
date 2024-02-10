package bob;

public class ParameterNotFoundException extends BobException {
    public ParameterNotFoundException(String parameter) {
        super(String.format(Ui.PARAMETER_NOT_FOUND, parameter));
    }

    public ParameterNotFoundException(String[] parameters) {
        super(String.format(Ui.PARAMETER_NOT_FOUND, String.join(" or ", parameters)));
    }
}

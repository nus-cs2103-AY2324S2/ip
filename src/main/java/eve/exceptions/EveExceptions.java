package eve.exceptions;
public class EveExceptions extends RuntimeException {
    public EveExceptions(String error) {
        super(error);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
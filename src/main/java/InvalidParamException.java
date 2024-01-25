public class InvalidParamException extends RuntimeException {
    public InvalidParamException(String _errorMsg, Throwable _exception) {
        super(_errorMsg, _exception);
    }
}
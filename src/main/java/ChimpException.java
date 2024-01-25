public abstract class ChimpException extends Exception{
    ChimpException(String message) {
        super(message);
    }
    @Override
    public abstract String toString();
}

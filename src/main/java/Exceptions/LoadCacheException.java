package exceptions;

public class LoadCacheException extends Exception {
    public LoadCacheException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}

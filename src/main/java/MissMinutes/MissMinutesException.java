package MissMinutes;

public class MissMinutesException extends Exception {
    public MissMinutesException(String errorMsg) {
        super(errorMsg);
    }

    public MissMinutesException(String errorMsg, Throwable err) {
        super(errorMsg, err);
    }
}
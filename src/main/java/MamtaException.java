public class MamtaException {
    private final String exceptionMessage;

    MamtaException(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
    public static MamtaException invalidTaskType() {
        return new MamtaException("Na manega! You entered an invalid task type. Sorry!");
    }

    public static MamtaException incompleteTaskDescription() {
        return new MamtaException("Abe, I don't know what to do! You did not give me instructions!");
    }

    public static MamtaException invalidDates() {
        return new MamtaException("Oye, dates enter properly man!");
    }

    public String toString() {
        return String.format("You ran into this error: %s", this.exceptionMessage);
    }


}

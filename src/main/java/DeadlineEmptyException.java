public class DeadlineEmptyException extends EmptyFieldException {
    public DeadlineEmptyException (String userInput) {
        super(String.format("by - What time does %s end by?", userInput));
    }
}

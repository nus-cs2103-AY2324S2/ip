public class RizException extends Exception {
    String message;

    public RizException(String message) {
        super("EUGH AUWEH!!!!!!!!");
        this.message = message;
    }

    public String getMessage() {
        return super.getMessage() + " " + this.message;
    }
}

/*
class ToDoException extends RizException {
    String message;

    public ToDoException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}

class YapaneseException extends RizException {
    String message;

    public YapaneseException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + this.message;
    }
}
 */
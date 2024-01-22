public class GeneralException extends Exception {

    private final String msg;

    public GeneralException(String msg) {
        this.msg = msg;
    }

    public String toString() {
        return "Hey, watch it! " + this.msg;
    }
}

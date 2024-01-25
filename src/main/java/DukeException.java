public class DukeException extends Exception {
    private String msg;

    public DukeException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}

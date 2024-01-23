public class Reply {
    private String message;

    public Reply(String message) {
        this.message = message;
    }

    public void displayMessage() {
        String horizLine = "_________________________";
        System.out.println(horizLine.concat("\n").concat(this.message)
                        .concat("\n").concat(horizLine));
    }
}

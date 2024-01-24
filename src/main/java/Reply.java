public class Reply {
    private String message;

    public Reply(String message) {
        this.message = message;
    }

    public void displayMessage() {
        String emptySpace = "      ";
        String horizLine = "    _____________________________________";
        System.out.println(horizLine.concat("\n").concat(emptySpace).concat(this.message)
                        .concat("\n").concat(horizLine));
    }
}

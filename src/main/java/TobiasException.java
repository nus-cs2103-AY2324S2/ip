public class TobiasException extends Exception {
    public TobiasException(String message) {
        super(message);
    }

    public void printMessage() {
        String divider = "  =======================================================================================";
        System.out.println(divider);
        System.out.println(getMessage());
        System.out.println(divider);
    }
}

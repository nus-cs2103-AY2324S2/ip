public class TyroneCmdException extends Exception {
    public TyroneCmdException(String message) {
        super("Hey homie, we've got a hiccup...\n\t\t" + message);
    }
}

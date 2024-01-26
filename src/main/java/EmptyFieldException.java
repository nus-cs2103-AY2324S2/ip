public class EmptyFieldException extends Exception{
    public EmptyFieldException  (String err) {
        super("\nApologies Sir/Mdm, it appears as though you left the following fields empty:\n" + err);
    }

}

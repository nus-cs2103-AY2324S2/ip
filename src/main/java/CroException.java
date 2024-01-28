public class CroException extends Exception{
    public CroException(String errorMessage) {
        super("-----------------------------------\n" + errorMessage + "\n" + "-----------------------------------");
    }
}

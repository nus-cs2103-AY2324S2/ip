public class BadTaskOrderException extends Exception {

    public BadTaskOrderException(String correctOrder) {
        super("The correct order for your parameters should be: " + correctOrder);
    }    

}

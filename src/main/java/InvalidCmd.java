public class InvalidCmd extends Exception{
    public InvalidCmd (String userInput) {
        super(String.format("My apologies Sir/Mdm, %s is simply beyond my comprehension.", userInput));
    }
}

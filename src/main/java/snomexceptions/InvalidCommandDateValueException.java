package snomexceptions;



public class InvalidCommandDateValueException extends InvalidCommandException{


    public InvalidCommandDateValueException() {
        super("Please make sure that your end date is after the start date");
    }
}

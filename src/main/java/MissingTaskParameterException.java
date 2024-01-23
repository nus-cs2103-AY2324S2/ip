public class MissingTaskParameterException extends Exception {

    public MissingTaskParameterException(String missingParams) {
        super("You are missing the following parameters: " + missingParams);
    }    

}

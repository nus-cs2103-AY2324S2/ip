public class MissingTaskInformationException extends Exception {

    public MissingTaskInformationException(String missingInfo) {
        super("You are missing the following infomation: " + missingInfo);
    }    

}

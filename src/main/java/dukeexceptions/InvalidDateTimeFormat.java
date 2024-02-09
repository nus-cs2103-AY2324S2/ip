package dukeexceptions;

public class InvalidDateTimeFormat extends DukeException{
    public InvalidDateTimeFormat(String err) {
        super("My apologies Sir/Mdm, your entry has a invalid date time format. Please follow the format of dd-MM-yyyy HH:mm");
    }
}

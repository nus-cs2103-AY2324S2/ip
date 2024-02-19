package lemona.exceptions;
public class DuplicateInstructionException extends Exception{
    public String toString(String string) {
        String str;
        switch (string) {
        case "mark":
            str = "I think you haven't had enough vitamin D."
                    + "\nYour task is already marked!";
            break;
        case "unmark":
            str = "I think you haven't had enough vitamin D."
                    + "\nYour task is already unmarked!";
            break;
        default:
            str = "I think you haven't had enough vitamin D."
                    + "\nYour task is already existing in the list!";
            break;
        }
        str = str + "\nI suggest you take some LEMONA";
        return str;
    }
}

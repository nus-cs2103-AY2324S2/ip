package exceptions;
public class DuplicateInstructionException extends Exception{
    public String toString(String string) {
        String str;
        switch (string) {
        case "mark":
            str = "\t I think you haven't had enough vitamin D."
                    + "\n\t Your task is already marked!";
            break;
        case "unmark":
            str = "\t I think you haven't had enough vitamin D."
                    + "\n\t Your task is already unmarked!";
            break;
        default:
            str ="\t I think you haven't had enough vitamin D."
                    + "\n\t Your task is already existing in the list!";
            break;
        }
        str = str + "\n\t I suggest you take some LEMONA";
        return str;
    }
}

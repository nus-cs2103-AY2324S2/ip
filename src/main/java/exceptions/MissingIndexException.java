package exceptions;
public class MissingIndexException extends Exception{
    public String toString(String string) {
        String str = "\t I think you haven't had enough vitamin C." +
                "\n\t You need to tell me which task to " + string +
                "!\n\t I suggest you take some LEMONA.";
        return str;
    }
}

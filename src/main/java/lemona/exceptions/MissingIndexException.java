package lemona.exceptions;
public class MissingIndexException extends Exception{
    public String toString(String string) {
        String str = "I think you haven't had enough vitamin C." +
                "\nYou need to tell me which task to " + string +
                "!\nI suggest you take some LEMONA.";
        return str;
    }
}

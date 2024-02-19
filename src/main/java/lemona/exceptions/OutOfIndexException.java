package lemona.exceptions;
public class OutOfIndexException extends Exception {
    public String toString(Integer index) {
        String message;
        if (index == 1) {
            message = "\nYou only have 1 task!";
        } else if (index == 0) {
            message = "\nYou do not have any task!";
        } else {
            message = "\nYou only have " + index + " tasks!";
        }
        String str = "I think you haven't had enough vitamin A." +
                message + "\nI suggest you take some LEMONA.";
        return str;
    }
}

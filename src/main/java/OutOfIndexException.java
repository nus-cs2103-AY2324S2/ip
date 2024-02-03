public class OutOfIndexException extends Exception {
    public String toString(Integer index) {
        String message;
        if (index == 1) {
            message = "\n\t You only have 1 task!";
        } else if (index == 0) {
            message = "\n\t You do not have any task!";
        } else {
            message = "\n\t You only have " + index + " tasks!";
        }
        String str = "\t I think you haven't had enough vitamin A." +
                message + "\n\t I suggest you take some LEMONA.";
        return str;
    }
}

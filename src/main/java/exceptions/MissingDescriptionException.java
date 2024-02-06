package exceptions;
public class MissingDescriptionException  extends Exception{
    public String toString(String string) {
        String str = "\t I think you haven't had enough vitamin K." +
                "\n\t Your input should be in format of:";
        if (string.equalsIgnoreCase("todo")) {
            str = str + "\n\t\t { todo (Task) }";
        } else if (string.equalsIgnoreCase("deadline")) {
            str = str + "\n\t\t { deadline (Task) (/by DueDate) }";
        } else if (string.equalsIgnoreCase("event")) {
            str = str + "\n\t\t { event (Task) (/from StartDate) (/to EndDate) }";
        }
        str = str + "\n\t I suggest you take some LEMONA.";
        return str;
    }
}

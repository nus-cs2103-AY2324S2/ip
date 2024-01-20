//General helper class to help parse and print messages. Not sure how useful it can be... for now might remove.
public class MessagePrinter {
    public String message;
    public MessagePrinter(String message) {
        this.message = message;
    }

    public void print() {
        System.out.println(this.message);
    }
    public static void print(String message) {
        System.out.println(message);
    }
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }






}

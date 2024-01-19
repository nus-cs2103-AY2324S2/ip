public class Duke {
    public static void hello() {
        String lineBreak = "---------------------------------------------\n";
        String message = "Hello! I am Squid. How can I help you today?\n\n";
        System.out.println(lineBreak + message + lineBreak);
    }

    public static void bye() {
        String message = "\nBye! I am going to look for pets and food.\n";
    }

    public static void main(String[] args) {
        hello();
        bye();
    }
}

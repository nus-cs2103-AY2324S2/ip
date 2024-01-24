public class Duke {
    static String line = "____________________________________________________________";
    public static void main(String[] args) {

        System.out.println(line);
        System.out.println("Hello! I'm Brian\nWhat can I do for you?");
        System.out.println(line);
        String input = "";
        while (!input.equals("bye")) {
            input = new java.util.Scanner(System.in).nextLine();
            System.out.println(line);
            System.out.println(input);
            System.out.println(line);
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}

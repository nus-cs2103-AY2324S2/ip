import java.io.IOException;
import java.util.Scanner;
import ui.Output;
import storage.Storage;
import parser.Parser;



public class Duke {
    public static void main(String[] args) {
        Storage storage = new Storage();
        Parser parser = new Parser(storage);
        Output output = new Output(parser, storage);
        System.out.println(output.welcome());
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                System.out.println(output.execute(input));
                break;
            } else {
                System.out.println(output.execute(input));
            }
            //writing into the file
            try {
                storage.writeToFile(storage.load());
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
        sc.close();
    }   
}

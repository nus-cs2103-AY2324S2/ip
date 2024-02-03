import java.io.*;
import java.util.LinkedList;

public class Duke {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        String[] tokens;

        Bot bot = new Bot();
        bot.greet();
        while (!(line = br.readLine()).equals("bye")) {
            bot.consume(line);
        }
        bot.exit();
    }
}
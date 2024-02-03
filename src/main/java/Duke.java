import Exceptions.TerminateException;
import Managers.taskManager;

// main class for the project
public class Duke {

    public static void main(String[] args) { 
        // Initialize
        Response response = new Response(new taskManager());

        Response.printGreeting();

        while (true) {
            try {
                response.parseInput(); // read input once
            } catch (TerminateException e) { // only thrown if command is "bye"
                return;
            }
        }
    }
}

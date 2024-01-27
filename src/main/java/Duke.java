import Exceptions.InvalidInputException;

import java.io.IOException;

public class Duke {
    public static void main(String[] args) throws IOException, InvalidInputException {
        Iris iris = new Iris();
        iris.start();;
        iris.taskRecorder();
        iris.exit();
    }
}

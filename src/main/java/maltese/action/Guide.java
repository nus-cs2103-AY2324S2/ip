package maltese.action;

import maltese.Maltese;
import maltese.Storage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Guide implements Action {
    /**
     * Constructs a Help action.
     * This action does not require any parameters.
     */
    public Guide() {
    }

    public static ChangeFilePath parse(Storage storage) throws IOException {
        return new ChangeFilePath("./data/sample.txt", storage, true);
    }

    /**
     * Retrieves the help message.
     *
     * @return A string representing the help message.
     */
    @Override
    public String getResponse() {
        return "I loaded some sample data! Type 'list' to see the sample data";
    }

}

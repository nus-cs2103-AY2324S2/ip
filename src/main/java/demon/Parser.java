package demon;

import java.util.ArrayList;
import java.util.Arrays;

import static demon.Command.splitInput;

public class Parser {
    ArrayList<String> outPut;
    public Parser() {
        this.outPut = new ArrayList<>();
    }

    public ArrayList<String> parseInput(String input) throws EmptyDescriptionException, NoTimingException {

        if (input.startsWith("todo")) {
            outPut.add(splitInput(input));
        } else if (input.startsWith("deadline")) {
            String[] parts = parseDeadlineInput(input);
            outPut.addAll(Arrays.asList(parts));
        } else if (input.startsWith("event")) {
            String[] parts = parseEventInput(input);
            outPut.addAll(Arrays.asList(parts));
        }
        return outPut;
    }
    private String[] parseEventInput(String input) throws NoTimingException, EmptyDescriptionException {
        String description_date = splitInput(input);
        String[] descriptionAndDate = Command.splitDescriptionDate(description_date);
        String description = descriptionAndDate[0];
        String[] fromAndTo = Command.splitByTo(descriptionAndDate[1]);
        String from = fromAndTo[0];
        String to = fromAndTo[1];
        return new String[] {description, from, to};

    }

    private String[] parseDeadlineInput(String input) throws NoTimingException, EmptyDescriptionException {
        String description_date = splitInput(input);
        return Command.splitBy(description_date);
    }
}

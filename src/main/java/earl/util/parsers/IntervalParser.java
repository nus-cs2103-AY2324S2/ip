package earl.util.parsers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import earl.exceptions.ParserException;

/**
 * Class responsible for interpreting complex ranges input by the user.
 */
public class IntervalParser implements Parser<Stream<Integer>> {

    /**
     * Returns a stream of unique indices in reverse sorted order.
     *
     * @param input             the user input ranges
     * @return                  a {@code Stream} of indices
     * @throws ParserException  if the user input is incomprehensible
     */
    public static Stream<Integer> parse(String input) throws ParserException {
        try {
            // The reverse order was chosen due to the possibility of mass
            // operations mutating the list, so reducing the change of bugs
            String[] args = input.split("\\s+");
            List<Integer> result = new ArrayList<>();
            for (String token : args) {
                if (!token.contains("-")) { // single index e.g. <operation> 1
                    int idx = Integer.parseInt(token) - 1;
                    result.add(idx);
                    continue;
                }
                // range of indices e.g. <operation> 3-6
                String[] range = token.split("-");
                int first = Integer.parseInt(range[0]);
                int second = Integer.parseInt(range[1]);
                int start = Math.min(first, second);
                int end = Math.max(first, second);
                for (int idx = start - 1; idx < end; ++idx) {
                    result.add(idx);
                }
            }
            return result.stream()
                    .distinct()
                    .sorted(Collections.reverseOrder());
        } catch (Exception e) {
            throw new ParserException();
        }
    }
}

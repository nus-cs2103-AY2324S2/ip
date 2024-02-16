package earl.util.parsers;

import java.util.Collections;
import java.util.stream.IntStream;
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
            String[] args = input.split("\\s+");
            Stream<Integer> result = Stream.empty();
            for (String token : args) {
                if (!token.contains("-")) { // single index e.g. <operation> 1
                    int idx = Integer.parseInt(token) - 1;
                    result = Stream.concat(result, Stream.of(idx));
                    continue;
                }
                // range of indices e.g. <operation> 3-6
                String[] range = token.split("-");
                int start = Integer.parseInt(range[0]) - 1;
                int end = Integer.parseInt(range[1]);
                result = Stream.concat(result,
                        IntStream.range(start, end).boxed());
            }
            return result.distinct().sorted(Collections.reverseOrder());
        } catch (Exception e) {
            throw new ParserException();
        }
    }
}

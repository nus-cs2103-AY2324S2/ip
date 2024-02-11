package earl.util.parsers;

import java.util.Collections;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import earl.exceptions.EarlException;

/**
 * Class responsible for interpreting complex ranges input by the user.
 */
public class IntervalParser implements Parser<Stream<Integer>> {

    /**
     * Returns a stream of unique indices in reverse sorted order.
     *
     * @param input           the user input ranges
     * @return                a {@code Stream} of indices
     * @throws EarlException  if the user input is incomprehensible
     */
    public static Stream<Integer> parse(String input) throws EarlException {
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
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new EarlException(
                    "Error, indices format invalid.\n"
                            + "\tExample format: 1 4-7 9-10");
        } catch (Exception e) {
            throw new EarlException("Error, unknown argument format:"
                    + e.getMessage());
        }
    }
}

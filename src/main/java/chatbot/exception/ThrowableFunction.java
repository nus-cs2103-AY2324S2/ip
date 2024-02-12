package chatbot.exception;

/**
 * This handles functional programming with exception handling for {@link ChatBotException}s
 * as Java doesn't provide a default implementation which use functional programming with exception handling,
 * without using external libraries.
 * <p>
 * Referenced from {@code https://stackoverflow.com/questions/18198176/java-8-lambda-function-that-throws-exception}
 *
 * @param <T> The type of input to the function.
 * @param <R> The type of output of the function.
 * @param <E> The type of exception thrown by the function.
 */
@FunctionalInterface
public interface ThrowableFunction<T, R, E extends ChatBotException> {
    /**
     * Performs the operation on the given argument of type T,
     * returning an object of type R.
     *
     * @throws E If an exception E is thrown.
     */
    R apply(T t) throws E;
}

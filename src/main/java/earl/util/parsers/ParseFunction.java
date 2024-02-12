package earl.util.parsers;

import earl.exceptions.EarlException;

/**
 * Custom functional interface for parse functions.
 *
 * @param <T>  the type that the parser returns
 */
@FunctionalInterface
public interface ParseFunction<T> {
    T apply(String str) throws EarlException;
}

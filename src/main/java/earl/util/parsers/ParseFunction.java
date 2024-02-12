package earl.util.parsers;

import earl.exceptions.EarlException;

@FunctionalInterface
public interface ParseFunction<T> {
    T apply(String str) throws EarlException;
}

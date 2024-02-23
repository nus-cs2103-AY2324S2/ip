package duke;

/**
 * Class that represents a Taro-specific exception
 */
public class TaroException extends Exception {

    //@@author wongkj12
    // Reused from https://github.com/nus-cs2103-AY2324S2/ip/pull/304/files#diff-1bd16f2a23a6a7bfa300d7ea2497d2e434cbbd58d209333ca10f7357b616c39d
    // with minor modifications
    public TaroException() {
        super();
    }
    public TaroException(String message) {
        super(message);
    }
    //@@author
}

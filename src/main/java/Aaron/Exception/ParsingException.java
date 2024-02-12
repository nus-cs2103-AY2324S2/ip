package Aaron.Exception;
/**
 * Class that represents error during parsing user message
 */
public class ParsingException extends AaronBotException {
    public ParsingException(String e) {
        super(e);
    }
}

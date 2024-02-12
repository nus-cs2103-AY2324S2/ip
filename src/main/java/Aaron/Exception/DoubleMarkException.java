package Aaron.Exception;
/**
 * Class that represents error when marking a marked task or unmarking an unmarked one
 */
public class DoubleMarkException extends AaronBotException{
    public DoubleMarkException(String e) {
        super(e);
    }
}

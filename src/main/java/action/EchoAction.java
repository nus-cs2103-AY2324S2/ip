package action;
import handler.PrintHandler;
import handler.DataHandler;
public class EchoAction implements Action{
    private String text;
    public EchoAction(String text) {
        this.text = text;
    }

    @Override
    public void execute() {
        DataHandler.instance.handleData(text);
        PrintHandler.instance.printWithDivider("added: " + text);
    }
}

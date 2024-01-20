import java.util.ArrayList;

public class Storage {
    public ArrayList<String> storageList;
    public Storage() {
        this.storageList = new ArrayList<>();
    }

    public void add(String command) {
        this.storageList.add(command);
        MessagePrinter.commandPrint(command);
    }

    public void printList() {
        MessagePrinter.commandListPrint(this.storageList);
    }
}

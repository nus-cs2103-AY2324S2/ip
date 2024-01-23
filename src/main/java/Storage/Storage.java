package Storage;

import java.util.ArrayList;

public class Storage {
    private static final String lines = "    ____________________________________________________________";
    private ArrayList<String> storage;

    public Storage() {
        this.storage = new ArrayList<>();
    }
    public void add(String input) {
        this.storage.add(input);
    }
    @Override
    public String toString() {
        String result = lines + "\n";
        for(int i=1;i<=storage.size();i++) {
           result += String.format("    %d.",i) + String.format(" %s\n", this.storage.get(i-1));
        }
        result += lines;
        return result;
    }


}

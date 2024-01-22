import java.util.ArrayList;

public class Memory {
    
    private ArrayList<String> memory;

    Memory() {
        this.memory = new ArrayList<>();
    }

    public boolean add(String sentence) {
        this.memory.add(sentence);
        return true;
    }

    public ArrayList<String> getList() {
        ArrayList<String> list = new ArrayList<>();
        for (String sentence : this.memory) {
            list.add(sentence);
        }
        return list;
    }

}

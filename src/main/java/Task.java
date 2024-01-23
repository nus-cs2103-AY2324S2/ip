public class Task {
    private String name;
    private boolean isMark = false;

    Task(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String isMarkToString(){
        return isMark ? "X": " ";
    }
}

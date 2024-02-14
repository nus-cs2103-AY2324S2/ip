package duke;
// import java.time.LocalDateTime;
// import java.time.format.DateTimeFormatter;

public class Test {
    enum Size {
        SMALL, MEDIUM, LARGE, EXTRALARGE
    }
    public static void main(String[] args) {
        String abc ="medium";
        String sbd = "medium afgasf";
        //Size abc = Size.valueOf("smalll".toUpperCase());
        System.out.println(abc.compareTo(sbd));
    }
}

package duke;
// import java.time.LocalDateTime;
// import java.time.format.DateTimeFormatter;

public class Test {
    enum Size {
        SMALL, MEDIUM, LARGE, EXTRALARGE
    }
    public static void main(String[] args) {
        String abc ="medium";
        //Size abc = Size.valueOf("smalll".toUpperCase());
        System.out.println(Size.valueOf(abc.toUpperCase()));
    }
}

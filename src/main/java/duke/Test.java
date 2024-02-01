package duke;
// import java.time.LocalDateTime;
// import java.time.format.DateTimeFormatter;

public class Test {
    enum Size {
        SMALL, MEDIUM, LARGE, EXTRALARGE}
    public static void main(String[] args) {
        // Size abc = Size.valueOf("small".toUpperCase());
        System.out.println(Size.valueOf("small".toUpperCase()));
        System.out.println();
    }
}

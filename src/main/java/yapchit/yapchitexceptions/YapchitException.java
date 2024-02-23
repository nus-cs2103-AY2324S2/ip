package yapchit.yapchitexceptions;

/**
 * Special exception class for Yapchit.
 */
public class YapchitException extends Exception {

    /**
     * Constucts a special yapchit excption instance.
     * Calls constructor of overall exception class.
     *
     * @param errorMsg error message of the exception.
     */
    public YapchitException(String errorMsg) {
        super(errorMsg);
    }


}
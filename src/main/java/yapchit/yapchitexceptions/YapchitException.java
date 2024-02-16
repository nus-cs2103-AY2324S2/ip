package yapchit.yapchitexceptions;

/**
 * Special exception class for Yapchit.
 */
public class YapchitException extends Exception{

    /**
     * Constuctor of special yapchit excptions. Calls constructor of overall exception class
     * @param errorMsg
     */
    public YapchitException(String errorMsg){
        super(errorMsg);
    }


}
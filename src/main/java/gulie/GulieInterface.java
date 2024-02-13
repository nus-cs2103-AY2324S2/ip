package gulie;

/**
 * An interface for Gulie.
 */
public interface GulieInterface {
    /**
     * Returns the next input String to Gulie.
     * @return
     */
    public String getString();

    /**
     * Outputs a String from Gulie.
     * @param str
     */
    public void print(String str);

    /**
     * Closes the interface.
     */
    public void close();

    /**
     * Confirms the input is still open.
     * @return The status of the interface.
     */
    public boolean isOpen();
}
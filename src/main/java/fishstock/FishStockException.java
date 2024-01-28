package fishstock;

/**
 * Encapsulates a FishStockException.
 */
class FishStockException extends Exception {
    /**
     * Initialize a FishStockException.
     * @param message The error message.
     */
    protected FishStockException(String message) {
        super(message);
    }
}

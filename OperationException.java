public class OperationException extends Exception {
    // Custom exception class to handle operation-related errors


    // Constructor that takes a message as a parameter
    // Calls the superclass (Exception) constructor with the message
    public OperationException(String message) {
        super(message);
    }



    // Constructor that takes both a message and a cause (another throwable) as parameters
    // Calls the superclass (Exception) constructor with the message and cause
    public OperationException(String message, Throwable cause) {
        super(message, cause);
    }
}

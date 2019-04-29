package webd4201.nazirm;
/**
 * webd4201.nazirm.InvalidUserDataException - this file contains extends the generic Exception class so that
 * 			            we have a custom Exception, this one will be used to flag an attempt
 * 			            at entering invalid data
 * @author Darren Puffer
 * @version 1.0 (13 March 2015)
 * @since 1.0
 */
@SuppressWarnings("serial")
/**
 * Exception class that inherits from base exception class.
 * Used when an error is made setting user attributes to invalid values
 */
class InvalidUserDataException extends Exception{

    /**
     * Default constructor calls super constructor
     */
    public InvalidUserDataException() {
    }

    /**
     * Parametrized constructor that takes a custom message and sends that to the super constructor with the
     * same parameters
     * @param message message as a string
     */
    public InvalidUserDataException(String message) {
        super(message);
    }

    /**
     * Shared variable for the serial version id
     */
    private static final long serialVersionUID = 1L;

}

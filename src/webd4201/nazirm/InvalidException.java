package webd4201.nazirm;
/**
 * This file contains extends the generic Exception class so that
 * we have a custom Exception, this one will be used to flag an attempt
 * at a invalid object being created
 */
@SuppressWarnings("serial")
/**
 * Exception class that inherits from base exception class.
 * Used when an error is made setting user attributes to invalid values
 */
class InvalidException extends Exception {

    /**
     * Default constructor calls super constructor
     */
    public InvalidException() {
    }

    /**
     * Parametrized constructor that takes a custom message and sends that to the super constructor with the
     * same parameters
     * @param message message as a string
     */
    public InvalidException(String message) {
        super(message);
    }

    /**
     * Shared variable for the serial version id
     */
    private static final long serialVersionUID = 1L;

}

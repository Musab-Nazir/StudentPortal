package webd4201.nazirm;

/**
 * webd4201.nazirm.DuplicateException - this file contains extends the generic Exception class so that
 * 			            we have a custom Exception, this one will be used to flag an attempt
 * 			            at a duplicate record in our database
 * @author Darren Puffer
 * @version 1.0 (13 March 2015)
 * @since 1.0
 */ 

@SuppressWarnings("serial")
/**
 * Exception class that inherits from base exception class.
 * Used when an existing record is found in the database with the same id
 */
class DuplicateException extends Exception
{
    /**
     * Default constructor calls super constructor
     */
    public DuplicateException()
    { super();}

    /**
     * Parametrized constructor that takes a custom message and sends that to the super constructor with the
     * same parameters
     * @param message message as a string
     */
    public DuplicateException(String message)
    { super(message);}
}
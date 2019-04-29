package webd4201.nazirm;

/**
 * webd4201.nazirm.NotFoundException - this file contains extends the generic Exception class so that
 *                 we have a custom Exception, this one will be used to flag when 
 *                 no record was found in the database (and therefore nothing can be
 *                 done to it)
 * @author Darren Puffer
 * @version 1.0 (13 March 2015)
 * @since 1.0
 */

@SuppressWarnings("serial")
/**
 * Exception class that inherits from base exception class.
 * Used when a specified record is not found in the database
 */
class NotFoundException extends Exception
{
	/**
	 * Default constructor calls super constructor
	 */
	public NotFoundException()
	{ super();}

	/**
	 * Parametrized constructor that takes a custom message and sends that to the super constructor with the
	 * same parameters
	 * @param message message as a string
	 */
	public NotFoundException(String message)
	{ super(message);}
}
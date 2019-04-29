package webd4201.nazirm;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Data Access Class for user objects
 */
public class UserDA {

    /**
     * shared student object for the class
     */
    private static Student aStudent;

    // declare static variables for all webd4201.nazirm.Student instance attribute values
    /**
     * shared variable to store user ID
     */
    private static long id;
    /**
     * shared variable to store user password as a string
     */
    private static String password;
    /**
     * shared variable to store user first name
     */
    private static String first_name;
    /**
     * shared variable to store user last name
     */
    private static String last_name;
    /**
     * shared variable to store user email
     */
    private static String email;
    /**
     * shared variable to store user type
     */
    private static char type;
    /**
     * shared variable to store whether account is enabled
     */
    private static boolean enabled;
    /**
     * shared variable to store user last access date
     */
    private static Date last_access;
    /**
     * shared variable to store user enrol date
     */
    private static Date enrol_date;

    /**
     * Fetches a user from the database as a student object
     * @param key long int for the user ID
     * @return student object with student specific fields null or zero
     * @throws NotFoundException is no user with specified ID is found
     */
    public static Student retrieve(long key) throws NotFoundException { // retrieve webd4201.nazirm Student data
        // check if a user with that ID exists in the system
        String sqlUserQuery = "SELECT * FROM users WHERE user_id = ?";

        try{
            Connection con = DatabaseConnect.initialize();
            PreparedStatement psSelect = con.prepareStatement(sqlUserQuery);
            psSelect.setLong(1,key);
            ResultSet rs = psSelect.executeQuery();
            // next method sets cursor & returns true if there is data
            if (rs.next())  // user exists in the system
            {    // extract the data
                id = rs.getLong("user_id");
                password = rs.getString("password");
                first_name = rs.getString("first_name");
                last_name = rs.getString("last_name");
                email = rs.getString("email");
                type = rs.getString("type").charAt(0);
                enabled = rs.getBoolean("enabled");
                last_access = rs.getDate("last_access");
                enrol_date = rs.getDate("enrol_date");

                // create the student object
                try{
                    aStudent = new Student(id,password,first_name,last_name,email,type,enabled,last_access,enrol_date,
                            null,null,0);
                } catch (InvalidNameException e) {
                    System.out.println("Invalid Name found");
                } catch (InvalidException e) {
                    System.out.println("Some data fields are invalid");;
                }
            }
            else    // nothing was retrieved
            {
                throw (new NotFoundException("Problem retrieving User record, id " +
                        key + " does not exist in the system."));
            }
        }
        catch (SQLException e) {
            System.out.println(e);
        }

        return aStudent;
    }


    /**
     * creates a new user record in the database
     *
     * @param aStudent student object that is instantiated
     * @param con a connection object used for database connection transaction rollback
     * @return true if record was created else return's false
     * @throws DuplicateException if a user with the same ID already exists in the database
     */
    public static boolean create(Student aStudent, Connection con) throws DuplicateException {
        boolean insertedUser = false; //insertion success flag

        String sqlUserInsert = "INSERT INTO users" +
                "(user_id, password, first_name, last_name, email, type, enabled, last_access, enrol_date) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try{
            // retrieve the student attribute values
            id = aStudent.getId();
            Student.retrieve(id);
            throw new DuplicateException();
        } catch (NotFoundException e) {
            first_name = aStudent.getFirstName();
            last_name = aStudent.getLastName();
            password = UserDA.HashPassword(aStudent.getPassword());
            email = aStudent.getEmailAddress();
            type = aStudent.getType();
            last_access = aStudent.getLastAccess();
            enrol_date = aStudent.getEnrolDate();
            enabled = aStudent.isEnabled();
            try{
                PreparedStatement psInsertUser = con.prepareStatement(sqlUserInsert);

                psInsertUser.setLong(1, id);
                psInsertUser.setString(2, password);
                psInsertUser.setString(3, first_name);
                psInsertUser.setString(4, last_name);
                psInsertUser.setString(5, email);
                psInsertUser.setString(6, String.valueOf(type));
                psInsertUser.setBoolean(7, enabled);
                psInsertUser.setDate(8, new java.sql.Date(last_access.getTime()));
                psInsertUser.setDate(9, new java.sql.Date(enrol_date.getTime()));

                // execute update queries
                psInsertUser.executeUpdate();
                insertedUser = true;

            } catch (SQLException ee) {
                ee.printStackTrace();
            }
        }

        return insertedUser;
    }

    /**
     * Updates a user record in the database
     *
     * @param aStudent Student object
     * @param con a connection object used for database connection transaction rollback
     * @return number of records affected as an int
     * @throws NotFoundException if no user with that ID could be found in the database
     */
    public static int update(Student aStudent, Connection con) throws NotFoundException {
        int records = 0;  //records updated in method

        // define the SQL query statement
        String sqlUserUpdate = "UPDATE users SET password=?, first_name=?, last_name=?, email=?, type=?, last_access=?, " +
                "enrol_date=? WHERE user_id = ?";

        // connect to db and create prepared statements
        PreparedStatement psUpdateUser = null;
        try {
            User.retrieve(id);
            // retrieve the student argument attribute values
            id = aStudent.getId();
            password = aStudent.getPassword();
            first_name = aStudent.getFirstName();
            last_name = aStudent.getLastName();
            email = aStudent.getEmailAddress();
            type = aStudent.getType();
            last_access = aStudent.getLastAccess();
            enrol_date = aStudent.getEnrolDate();
            enabled = aStudent.isEnabled();

            psUpdateUser = con.prepareStatement(sqlUserUpdate);
            psUpdateUser.setString(1, password);
            psUpdateUser.setString(2, first_name);
            psUpdateUser.setString(3, last_name);
            psUpdateUser.setString(4, email);
            psUpdateUser.setString(5, String.valueOf(type));
            psUpdateUser.setDate(6, new java.sql.Date(last_access.getTime()));
            psUpdateUser.setDate(7, new java.sql.Date(enrol_date.getTime()));
            psUpdateUser.setLong(8, id);

            records += psUpdateUser.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    /**
     * deletes a user record in the database
     *
     * @param aStudent a student object
     * @return amount of records deleted as an integer
     * @throws NotFoundException if a user with that id cannot be found
     */
    public static int delete(Student aStudent) throws NotFoundException {
        int records = 0;
        id = aStudent.getId();
        UserDA.retrieve(id);
        // create the SQL delete statement
        String sqlUserDelete = "DELETE FROM users WHERE user_id = ?";
        // see if this student already exists in the database
        try {
            Connection con = DatabaseConnect.initialize();
            // make prepared statement object
            PreparedStatement psUserDelete = con.prepareStatement(sqlUserDelete);

            // set attribute for the statement
            psUserDelete.setLong(1, id);

            // execute the SQL update statement
            records += psUserDelete.executeUpdate();
            // close db connection
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return records;     // return number of records effected
    }

    /**
     * converts a decimal number to hexadecimal format
     *
     * @param bytes an array of bytes
     * @return hex string
     */
    private static String decToHex(byte[] bytes) {
        String hex;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(String.format("%02x", bytes[i]));
        }
        hex = sb.toString();
        return hex;
    }

    /**
     * This method takes a string and hashes it using SHA1 algorithm
     *
     * @param passwordInput a password in a string format
     * @return a hashed string
     */
    protected static String HashPassword(String passwordInput) {
        String processedPassword = "";
        try {

            MessageDigest md = MessageDigest.getInstance("SHA1");

            md.update(passwordInput.getBytes());
            byte[] hashedPassword = md.digest();
            processedPassword = decToHex(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Couldn't find the hashing algorithm specified");
        }
        return processedPassword;
    }
}

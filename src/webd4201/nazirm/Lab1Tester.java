package webd4201.nazirm;

import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Main method to test the Student JDBC methods
 *
 * @author Darren Puffer, Musab Nazir
 * @version 1.0 (2018 February 1st)
 * @since 1.0
 */
class Lab1Tester {

	public static void main(String[] args) {
		System.out.println("******************** Lab 1 Output ********************\n");
		Connection c;
		Student mainStudent;  //object for a program created Student
		Student dbStudent;   //object for database retrieved Student
		long possibleId = 100232323L;
		GregorianCalendar cal = new GregorianCalendar();
		Date lastAccess = cal.getTime();
		cal.set(2019, Calendar.JANUARY, 18);
		Date enrol = cal.getTime();
		try{
			System.out.println("\nCreate a Student user to insert/delete later in the program, passing:\n\t" +
					"Student student1 = new Student(" + possibleId + "L, \"password\", \"Robert\", \"McReady\"," +
					" \"bob.mcready@dcmail.ca\", enrol, lastAccess, 's', true, \"CPA\", \"Computer Programmer Analyst\", 3);\n");

			mainStudent = new Student(possibleId,"password", "Robert", "McReady",
                    "bob.mcready@dcmail.ca", 's',true, lastAccess, enrol, "CPA",
                    "Computer Programmer Analyst", 3);
			//mainStudent.dump();
			try{

	            // initialize the database (i.e. create a database connection)
	            c = DatabaseConnect.initialize();
	            Student.initialize(c);

//	            try // attempt to get a Student that does NOT exist, throws Exception
//	            {
//	            	System.out.println("\nAttempt to retrieve a student that does not exist (Id: " + possibleId + ")");
//	            	dbStudent = Student.retrieve(possibleId);
//	            	System.out.println("Student record with id " + possibleId + " retrieved from the database\n");
//	            	dbStudent.dump();
//	            }
//	            catch(NotFoundException e)
//	            {	System.out.println(e.getMessage());}
//
//	            try // attempt to get a Student that does exist
//	            {
//	            	possibleId = 100111111L;
//	            	System.out.println("\nAttempt to retrieve a student that does exist (Id: " + possibleId + ")");
//	            	dbStudent = Student.retrieve(possibleId);
//	            	System.out.println("Student record with id " + possibleId + " retrieved from the database\n");
//	            	dbStudent.dump();
//	            }
//	            catch(NotFoundException e)
//	            {	System.out.println(e.getMessage());}

//	            try
//	            {
//	            	System.out.println("\nAttempt to insert a new student record for "
//	            						+ mainStudent.getFirstName() + " " + mainStudent.getLastName()
//	            						+ "(Id: " + mainStudent.getId()+")");
//	            	mainStudent.setYear(22);
//	            	mainStudent.create();
//	                System.out.println("Student record added to the database.\n");
//	            }
//	            catch(DuplicateException e)
//	            {	System.out.println(e);}

//	            try
//	            {
//	            	System.out.println("\nChange the student object and attempt to update the student record for "
//	            						+ mainStudent.getFirstName() + " " + mainStudent.getLastName()
//	            						+ "(Id: " + mainStudent.getId() +")");
//	            	mainStudent.setPassword("newpassword");
//	            	mainStudent.setProgramCode("RPN");
//	            	mainStudent.setProgramDescription("Registered Practical Nurse");
//	            	mainStudent.setYear(22);
//
//	            	mainStudent.update();
//	                System.out.println("Student record updated in the database.\n");
//	            }
//	            catch(NotFoundException e)
//	            {	System.out.println(e);}

//	            System.out.println("\nStudents are encouraged to comment out the following try...catch block to"
//	            		+ " verify the new record exists in pgAdmin by running the \"SELECT * FROM Students;\" command ");
                try{
                    Student someGuy = Student.retrieve(100111110);
                    System.out.println(someGuy.CalculateGPA());
                }
                catch (Exception e){
                    e.printStackTrace();
                }
//				try // now, attempt to delete the new Student
//	            {
//	            	System.out.println("\nAttempt to delete the new student record for "
//	            	   						+ mainStudent.getFirstName() + " " + mainStudent.getLastName()
//   						+ "(Id: " + mainStudent.getId() + ")");
//	        	   		mainStudent.delete();
//	        	   	System.out.println("Student record with id " + mainStudent.getId() + " successfully removed from the database.\n");
//	            }
//	            catch(NotFoundException e)
//	                    {	System.out.println(e);}
//
//	            try // now, try to find the deleted Student
//	            {
//	            	possibleId = 100232323L;
//	                mainStudent = Student.retrieve(possibleId);
//	                mainStudent.dump();
//	                mainStudent.delete();
//	            }
//	            catch(NotFoundException e)
//	            {
//	            	System.out.println("Did not find student record with id " + possibleId + ".\n");
//	            }
			 }catch(Exception e){   //catch for database initialize/connect try
				  System.out.println(e.toString());
			 }finally{ // close the database resources, if possible
		          try{  Student.terminate(); }catch(Exception e){e.printStackTrace();}
		          try{  DatabaseConnect.terminate(); }catch(Exception e){e.printStackTrace();}
			 }
		}catch(InvalidNameException | InvalidException iude){
			System.out.println(iude.getMessage());
		}
//		try{
//			c = DatabaseConnect.initialize();
//	        Student.initialize(c);
//			Boolean exists = StudentDA.StudentExists(100454543);
//			System.out.println(exists);
//			dbStudent = Student.Authenticate(100454543,"password");
//			dbStudent.dump();
//		} catch (NotFoundException e) {
//			e.printStackTrace();
//		}
	}
}


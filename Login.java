import java.util.Scanner;

public class Login
{
    private Scanner input = new Scanner(System.in);
	private Files file = new Files();
    private Registration rg = new Registration();
    private Administration am = new Administration();
    private Analysis va = new Analysis();

    // Display teacher menu to let teacher login or register their account
    public void teacherLoginPage()
    {
        boolean exit = true;
        while(exit)
        {

            System.out.println("\t\t***************************************************");
            System.out.println("\t\t**           Welcome to Grading System           **");
            System.out.println("\t\t***************************************************");
            System.out.println("\t\t**                Teacher Menu                   **");
            System.out.println("\t\t***************************************************");
            System.out.println("\t\t**      1 - Login                                **");
            System.out.println("\t\t**      2 - Register                             **");
            System.out.println("\t\t**      0 - Exit                                 **");
            System.out.println("\t\t***************************************************"); 

            boolean repeat = true;
            do
            {
                System.out.print("\t\t     Enter an Option: ");
                int opt = Integer.parseInt(input.nextLine());
                
                if(opt == 1) // Go to teacher login
                {
                    repeat = false;
                    teacherLogin();
                }
                else if(opt == 2) // Go to teacher registration
                {
                    repeat =  false;
                    rg.registerTeacher();
                }
                else if(opt == 0)
                {
                    repeat = false;
                    exit = false;
                }
                else
                    System.out.println("\t\t     Invalid Option");
            }while(repeat);
        }
    }

    // Teachers login to their accounts
    public void teacherLogin ()
    {
    	// Read data from files
    	try 
    	{
    		file.readFile();
        } catch (Exception e) 
    	{	
        	System.out.println("Error reading data from files: " + e.getMessage());
        }
    	
    	boolean exit = false;
    	
        do 
        {
            boolean found = false;
            System.out.println("\t\t*******************************************************");
            System.out.println("\t\t**             Teacher Login (Enter 0 to exit)       **");
            System.out.println("\t\t*******************************************************");
            System.out.println();
            // Teacher enters their id
            System.out.print("\t\t     Enter your id: ");
            String id = input.nextLine();
            if (id.equals("0"))
                exit = true;
            else 
            {
            	// Teacher enters password
                System.out.println("\t\t     Enter [PW] if forgot password");
                System.out.print("\t\t     Enter your password: ");
                String password = input.nextLine();
                System.out.println();
                if (password.equals("0"))
                    exit = true;
                else if (password.toUpperCase().equals("PW")) 
                {
                    // Enter PW to forget password
                    System.out.print("\t\t     Enter Your IC number to get the password hint: ");
                    String ic = input.nextLine();
                    found = false;
                    
                    for (int i = 0; i < file.getTeacherList().size(); i++)
                    {
                        // Compare the teacher IC with IC in teacherList
                        Teacher teacher = file.getTeacherList().get(i);
                        if (ic.equals(teacher.getIC()))
                        {
                            // Show password hint
                            System.out.println("\t\t     Here is your password hint:");
                            System.out.print("\t\t     \"");
                            for (int j = 0; j < 4; j++)
                            {
                                System.out.print(teacher.getPassword().charAt(j));
                            }
                            System.out.println("****\"");
                            found = true;
                        }        
                    }

                    if (!found)
                        // If IC not found
                        System.out.println("\t\t     No teacher is found.");
                }
                else 
                {
                	// Validate whether the teacher's id and password are matched in Teacher.txt
                    for (int i = 0; i < file.getTeacherList().size(); i++) 
                    {
                        Teacher teacher = file.getTeacherList().get(i);
                        if (id.equals(teacher.getID()) && password.equals(teacher.getPassword())) 
                        {
                            Teacher tc = new Teacher(teacher.getName(), teacher.getID(), teacher.getIC(), teacher.getPassword());
                            System.out.println("\t\t     Login successful...");
                            found = true;
                            exit = true;
                            
                            boolean exit2 = true;
                            // Display teacher main menu after login
                            while(exit2)
                            {
                                System.out.println("\t\t***************************************************");
                                System.out.println("\t\t**             Welcome to Main page              **");
                                System.out.println("\t\t***************************************************");
                                System.out.println("\t\t**              Teacher Main Menu                **");
                                System.out.println("\t\t***************************************************");
                                System.out.println("\t\t**      1 - Register Student Account             **");
                                System.out.println("\t\t**      2 - Delete Student Account               **");
                                System.out.println("\t\t**      3 - Modify Students' Information         **");
                                System.out.println("\t\t**      4 - Record & Modify Students' Marks      **");
                                System.out.println("\t\t**      5 - Update Teachers' Information         **");
                                System.out.println("\t\t**      6 - View Analysis                        **");
                                System.out.println("\t\t**      0 - Exit                                 **");
                                System.out.println("\t\t***************************************************");

                                boolean repeat = true;
                                do
                                {
                                    System.out.print("\t\t     Enter an Option: ");
                                    int opt = Integer.parseInt(input.nextLine());
                                    
                                    if(opt == 1) // Register new Student in Class Administration
                                    {
                                        repeat = false;
                                        rg.registerStudent();
                                    }
                                    else if(opt == 2) // Delete Student in file in Class Administration
                                    {
                                        repeat = false;
                                        am.deleteStudent();;
                                    }
                                    else if(opt == 3) // Modify Student information in Class Administration
                                    {
                                        repeat = false;
                                        am.modifyStudent();
                                    }
                                    else if(opt == 4) // Record Student marks in Class Administration
                                    {
                                        repeat = false;
                                        am.recordMarks();
                                    }
                                    else if(opt == 5) // Teacher update his/her own information in Class Administration
                                    {
                                        repeat = false;
                                        am.updateTeacherInfo(tc);
                                    }
                                    else if(opt == 6) // View Class and Standard Analysis in Class Analysis
                                    {
                                        repeat = false;
                                        va.viewAnalysis();
                                    }
                                    else if(opt == 0) // Exit
                                    {
                                        exit2 = false;
                                        repeat = false;
                                        break;
                                    }
                                    else
                                        System.out.println("\t\t     Invalid Option");
                                }while(repeat);
                            }
                        }
                    }
                    if (!found)
                        System.out.println("\t\t     Teacher not found...");
                }
            }
        } while (!exit);
    }

    // Students login to their accounts
    public void studentLogin()
    {
    	// Read data from files
    	try {
            file.readFile();
        } catch (Exception e) {
            System.out.println("Error reading data from files: " + e.getMessage());
        }
    	
    	boolean exit =false;
    
    	do
    	{
    		boolean found=false;
            System.out.println("\t\t*******************************************************");
            System.out.println("\t\t**                Student Login                      **");
            System.out.println("\t\t*******************************************************");
            System.out.println();
            // Student enters name
            System.out.print("\t\t     Enter your name: ");
            String name = input.nextLine().toUpperCase();
            if (name.equals("0"))
            	exit=true;
            else
            {
            	// Student enters id
                System.out.print("\t\t     Enter your ID: ");
                String ID = input.nextLine();
                System.out.println();
                if (ID.equals("0"))
                	exit=true;
                else
                {
                	// Validate whether student's name and id are matched in Student.txt
                	for (int i=0; i< file.getStudentList().size();i++)
                	{
                		Student student =file.getStudentList().get(i);
                		if (name.equals(student.getName())&& ID.equals(student.getID()))
                		{
                			System.out.println("\t\t     Login successful...");
                			found=true;
                			exit= true;
                			// if student's name and id are matched, their results are displayed
                            va.viewResults(student);
                		}
                	}if (!found)
                		System.out.println("\t\t     Student not found...");
                }
            } 
    	}while(!exit);
    }
}


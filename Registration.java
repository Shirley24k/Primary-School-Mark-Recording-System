import java.io.IOException;
import java.util.*;

public class Registration
{
    private Scanner input = new Scanner(System.in);
	private Files file = new Files();

    //Register Teacher Information
    public void registerTeacher()
    {
		String name, id, ic, password; 
		boolean exit = false;

		// Read data from files
        try {
            file.readFile();
        } catch (Exception e) {
            System.out.println("Error reading data from files: " + e.getMessage());
        }
        
		System.out.println("\t\t*******************************************************");
		System.out.println("\t\t**             Teacher Registration                  **");
		System.out.println("\t\t*******************************************************");
		System.out.println();
		System.out.println("\t\t     Please enter the details below correctly.");
		System.out.println("\t\t     Press '0' to exit.");
        System.out.println();
		   
		//Enter valid teacher's IC with length of 12 digits.
        do
        {
            System.out.print("\t\t     Enter your ic (without '-'): ");
            ic = input.nextLine();
            System.out.println();
            
            if(ic.equals("0"))
            {
            	exit = true;
            	System.out.println("\t\t     Exiting...");
            }
            else if(ic.length() != 12)
            {
                System.out.println("\t\t     Invalid IC! Please follow standard IC format: YYMMDDBP###G\n");
            }
            else
            {
            	//Validate the teacher has not been registered before
                boolean found = false;
                for(int i = 0; i < file.getTeacherList().size(); i++)
                {
                	if(ic.equals(file.getTeacherList().get(i).getIC().replace("/", "")))
                	{
                		found = true;
                		exit = true;
                		System.out.println("\t\t     IC has been registerd before. Please login as teacher at the main page.\n");
                	}            	
                }
                for(int i = 0; i < file.getStudentList().size(); i++)
                {
                	if(ic.equals(file.getStudentList().get(i).getIC().replace("/", "")))
                	{
                		found = true;
                		exit = true;
                		System.out.println("\t\t     IC has been registerd before. Please login as student at the main page.\n");
                	}            	
                }
                
                if(!found)
                {
                	//Enter full name which should not consist of number.
            		do
            		{
            			System.out.print("\t\t     Enter your full name: ");
            			name = input.nextLine().toUpperCase();
            			System.out.println();
            			
            			if(name.equals("0"))
                        {
            				exit = true;
                        	System.out.println("\t\t     Exiting...");
                        }
            			else
            			{
            				boolean validName = true;
            				//Validate the name does not contain number
            				for(int i = 0; i < name.length(); i++)
            				{
            					char inputChar = name.charAt(i);
            					if (Character.isDigit(inputChar))
            					{
            						validName = false;
            						break;
            					}
            				}

            				if(!validName)
            				{
            					System.out.println("\t\t     Invalid name! Please do not include any numbers.\n");
            				}
            				else
            				{
            					//Enter password which should contain exactly 8 digits.
            					do
            					{
            						System.out.println("\t\t     #Your password should only consist of 8 digits.#");
            						System.out.print("\t\t     Enter your password: ");
            						password = input.nextLine();
            						System.out.println();
            						if(password.equals("0"))
            						{
            							exit = true;
            							System.out.println("\t\t     Exiting...");
            						}
            						else if (password.length() != 8)
            						{
            							System.out.println("\t\t     Invalid password! Please enter 8-digit password.\n");
            						}
            						else
            						{
            							System.out.println("\t\t     Successfully registered... Please login at the main menu.");

            							// Save teacher details to file
            							id = Teacher.teacherID(file.teacherList);
            							file.getTeacherList().add(new Teacher(name, id, ic, password));

            							try {
            								file.saveFile();
            							} catch (IOException e) {
            								// TODO Auto-generated catch block
            								e.printStackTrace();
            							}
										System.out.println("\t\t     Your Teacher ID is " + id);
            							exit = true;
            						}
            					}while(!exit);
            				}
            			}
            		}while(!exit);
               }
            } 
        }while(!exit);
			
	}

    // Teachers register students' information
    public void registerStudent()
    {
    	String name, id, ic, sex, studCls, ans;
	    int standard;
	    boolean exit = false;
	    
	    // Read data from files
        try {
            file.readFile();
        } catch (Exception e) {
            System.out.println("Error reading data from files: " + e.getMessage());
        }
        
	    do
	    {
	    	System.out.println();
	        System.out.println("\t\t*******************************************************");
	        System.out.println("\t\t**           Student Registration                    **");
	        System.out.println("\t\t*******************************************************");
	        System.out.println();
	        System.out.println("\t\t     Please enter the details below correctly.");
	        System.out.println("\t\t     Press '0' to exit.");
	        System.out.println();
	        
	        //Enter valid student's IC with length of 12 digits.
	        do
	        {
	            System.out.print("\t\t     Enter student ic (without '-'): ");
	            ic = input.nextLine();
	            System.out.println();
	            
	            if(ic.equals("0"))
	            {
	            	exit = true;
	            	System.out.println("\t\t     Exiting...");
	            }
	            else if(ic.length() != 12)
	            {
	                System.out.println("\t\t     Invalid IC! Please follow standard IC format: YYMMDDBP###G\n");
	            }
	            else
	            {
	            	//Validate the student has not been registered before
		            boolean found = false;
	                for(int i = 0; i < file.getTeacherList().size(); i++)
	                {
	                	if(ic.equals(file.getTeacherList().get(i).getIC().replace("/", "")))
	                	{
	                		found = true;
	                		exit = true;
	                		System.out.println("\t\t     IC has been registerd before as teacher. \n");
	                	}            	
	                }
	                for(int i = 0; i < file.getStudentList().size(); i++)
	                {
	                	if(ic.equals(file.getStudentList().get(i).getIC().replace("/", "")))
	                	{
	                		found = true;
	                		exit = true;
	                		System.out.println("\t\t     IC has been registerd before as student. \n");
	                	}            	
	                }	
	                
	                if(!found)
	                {
	                	//Register student's name correctly without any number.
	        	        do
	        	        {
	        	            System.out.print("\t\t     Enter student full name: ");
	        	            name = input.nextLine().toUpperCase();
	        	            System.out.println();
	        	            
	        	            if(name.equals("0"))
	                        {
	            				exit = true;
	                        	System.out.println("\t\t     Exiting...");
	                        }
	        	            else
	        	            {
	        	            	boolean validName = true;
	        	            	
	        	            	//Validate the name does not contain number
		        	            for (int i = 0; i < name.length(); i++)
		        	            {
		        	                char inputChar = name.charAt(i);
		        	                if(Character.isDigit(inputChar))
		        	                {
		        	                    validName = false;
		        	                    break;
		        	                }
		        	            }
		        	            
		        	            if(!validName)
		        	            {
		        	            	System.out.println("\t\t     Invalid name! Please do not include any numbers.\n");
		        	            }
		        	            else
		        	            {
		        	            	//Enter student's sex either female or male
		        	            	do
		        	            	{
		        	            		System.out.print("\t\t     Enter student sex (female/male): ");
		        	            		sex = input.nextLine().toUpperCase();
		        	            		System.out.println();

		        	            		if(sex.equals("0"))
		        	            		{
		        	            			exit = true;
		        	            			System.out.println("\t\t     Exiting...");
		        	            		}
		        	            		else if(!sex.equals("FEMALE") && !sex.equals("MALE"))
		        	            		{
		        	            			System.out.println("\t\t     Invalid gender! Please enter either female or male.\n");
		        	            		}
		        	            		else
		        	            		{
		        	            			//Enter student's standard (1/2/3/4/5/6)
		        	            			do
		        	            			{
		        	            				System.out.print("\t\t     Enter standard year (1/2/3/4/5/6): ");
		        	            				standard = Integer.parseInt(input.nextLine());
		        	            				System.out.println();

		        	            				if(standard == 0)
		        	            				{
		        	            					exit = true;
		        	            					System.out.println("\t\t     Exiting...");
		        	            				}
		        	            				else if(standard < 1 || standard > 6)
		        	            				{
		        	            					System.out.println("\t\t     Invalid standard! Primary education in Malaysia starts from Standard 1 and ends with Standard 6.\n");
		        	            				}
		        	            				else
		        	            				{
		        	            					//Enter student's class (A/B/C/D/E)
		        	            					do
		        	            					{
		        	            						String [] clsArray = {"A", "B", "C", "D", "E"};
		        	            						System.out.print("\t\t     Enter student class (A/B/C/D/E): ");
		        	            						studCls = input.nextLine().toUpperCase();
		        	            						System.out.println();

		        	            						if(studCls.equals("0"))
		        	            						{
		        	            							exit = true;
		        	            							System.out.println("\t\t     Exiting...");
		        	            						}
		        	            						else
		        	            						{
		        	            							// Validate class entered is in class array (A/B/C/D/E)
		        	            							boolean clsExist = false;
		        	            							for (String cls : clsArray)
		        	            							{
		        	            								if (studCls.equals(cls))
		        	            								{
		        	            									clsExist = true; 
		        	            									break;
		        	            								}
		        	            							}
		        	            							if (!clsExist)
		        	            							{
		        	            								System.out.println("\t\t     Invalid class! Please choose class among the valid classes A, B, C, D, E.\n");
		        	            							}
		        	            							else
		        	            							{
		        	            								System.out.println("\n\t\t     Successfully registered...\n");
		        	            								System.out.println("\t\t     Students may now log in to their accounts at main menu.\n");

		        	            								// Save student details to file
		        	            								id = Student.studentID(file.studentList);
		        	            								file.getStudentList().add(new Student(name, id, ic, sex, standard, studCls));

		        	            								try {
		        	            									file.saveFile();
		        	            								} catch (IOException e) {
		        	            									// TODO Auto-generated catch block
		        	            									e.printStackTrace();
		        	            								}	
		        	            								exit = true;
		        	            							}
		        	            						}

		        	            					}while(!exit);

		        	            				}
		        	            			}while(!exit);
		        	            		}
		        	            	}while(!exit);
		        	            }
	        	            }

	        	        }while(!exit);
	                }
	            }
				
	        }while(!exit);
			
	        // Allow teacher to register for another students
	        System.out.print("\n\t\t     Anymore student to be registered? (Y/N): ");
			ans = input.nextLine().toUpperCase();
			if(ans.equals("Y"))
			{
				exit = false;
				System.out.println();
			}
			else if(ans.equals("N")||ans.equals("0"))
		    {
		        exit = true;
		    	System.out.println("\n\t\t     Exiting...");	
		    }
		    else
		    {
		        System.out.println("\n\t\t     Invalid input! Please enter Y for Yes, N for No.");		
		    }

	    }while(!exit);
	}
}
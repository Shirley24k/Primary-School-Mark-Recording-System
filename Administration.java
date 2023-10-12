import java.io.IOException;
import java.util.*;

public class Administration
{
	private Scanner input = new Scanner(System.in);
	private Files file = new Files();

    // Remove students who left school permanently from the student list
    public void deleteStudent()
    {
    	boolean exit = false;
		
		// Read data from files
        try {
            file.readFile();
        } catch (Exception e) {
            System.out.println("Error reading data from files: " + e.getMessage());
        }
        
		do
		{
			// Enter valid student's ic to remove student
			int index = 0;
			System.out.println("\n\t\t     Eneter 0 to exit");
			System.out.print("\t\t     Enter student's ic: ");
			String ic = input.nextLine();
			if(ic.equals("0"))
			{
				exit = true;
				System.out.println("\n\t\t     Exiting...");
			}
			else if(ic.length() != 12)
            {
                System.out.println("\n\t\t     Invalid IC! Please follow standard IC format: YYMMDDBP###G\n");
            }
			else
			{
				boolean found = false;
		        
				// Search student from Student.txt based on ic
		        for(int i = 0; i < file.getStudentList().size(); i++)
		        {
		        	if(ic.equals(file.getStudentList().get(i).getIC().replace("/", "")))
		        	{
		        		found = true;
		        		index = i;
		        		System.out.println();
		        		System.out.println("\t\t  *******************************************************");
		        		System.out.println("\t\t     Name: " + file.getStudentList().get(i).getName() + "\t\t     ID: " + file.getStudentList().get(i).getID());
		        		System.out.println("\t\t  *******************************************************");
		        		break;
		        	} 
		        }
		        
		        if (!found)
		        {
		        	System.out.println("\n\t\t     IC not found. Please enter again.");
		        }
		        else
		        {
		        	do
		        	{
		        		// Confirm whether the teacher wants to delete account for this student
		        		System.out.print("\n\t\t     Are you sure you want to delete this student? (Y/N): ");
		        		String ans = input.nextLine().toUpperCase();
		        		if(ans.equals("Y"))
		        		{
		        			file.studentList.remove(index);	
		        			System.out.println("\n\t\t     Deleted successfully...");
		        			try {
								file.saveFile();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}	
		        			exit = true;
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
		}while(!exit);
    }
    
    // Modify students' information
    public void modifyStudent()
    {
    	// Read data from files
		try
		{
			file.readFile();
		} catch (Exception e) {
			System.out.println("Error reading data from files: " + e.getMessage());
		}
		
    	boolean exit = false;
    	while(!exit)
    	{
			System.out.println("\t\t     Enter 0 to quit ");
			// Enter student's full name which should not consist of number.
    		System.out.print("\t\t     Enter student's name: ");
    		String studName = input.nextLine().toUpperCase();
    		System.out.println();
    		 
			if (studName.equals("0"))
			{
				System.out.println("\t\t     Exiting...");
				exit = true;
			}
			else
			{
				int index = -1;
				for (int i = 0; i < file.getStudentList().size(); i++) 
				{
					// Search student's name inside Student.txt to obtain index of student
					if (studName.toUpperCase().equals(file.getStudentList().get(i).getName().replace("/", ""))) 
					{
						index = i; 
						break; 
					}
				}
				if (index == -1) 
				{
					System.out.println("\t\t     Student not found.");
					exit = true;
				} 
				else 
				{
					// Student found, continue with the profile update
					// Display the profile options
					System.out.println("\t\t*******************************************************");
					System.out.println("\t\t**             Student Profile                       **");
					System.out.println("\t\t*******************************************************");
					System.out.println("\t\t**               1 - Name                            **");
					System.out.println("\t\t**               2 - IC                              **");
					System.out.println("\t\t**               3 - Sex                             **");
					System.out.println("\t\t**               4 - Standard                        **");
					System.out.println("\t\t**               5 - Class                           **");
					System.out.println("\t\t**               0 - Exit                            **");
					System.out.println("\t\t*******************************************************");
					System.out.println();
					System.out.print("\t\t     Enter option: ");
					int opt = Integer.parseInt(input.nextLine());
					
					switch (opt) 
					{
						// Update student's name
						case 1:
						{
							boolean quit = false;
							do
							{
								System.out.print("\t\t     Enter new name: ");
								String newName = input.nextLine().toUpperCase();
								//Enter 0 to exit
					        	if (newName.equals("0")) {
					        	    quit = true;
					        	    System.out.println("\n\t\t     Exiting...");
					        	} 
					        	else
					        	{
									boolean validName = true;
									// Validate the name does not contain number
									for (int j = 0; j < newName.length(); j++) 
									{
										char inputChar = newName.charAt(j);
										if (Character.isDigit(inputChar)) 
										{
											validName = false;
											break;
										}
									}
				
									if (!validName) 
									{
										System.out.println("\t\t     Invalid name! Please do not include any numbers.\n");
										System.out.println("\t\t     Failed to update ");
									}
									// Save updated student's name to Student.txt
									else 
									{
										file.getStudentList().get(index).setName(newName);
										try {
											file.saveFile();
										} catch (IOException e) {
											e.printStackTrace();
										}
										quit = true;
										System.out.println("\t\t     Student's name updated successfully!");
									}
					        	}

							} while (!quit);
							break;
						}
						
						// Update student's IC
						case 2:
						{
							boolean quit = false;
							do
							{
								System.out.print("\t\t     Enter new ic without '-': ");
								String newIC = input.nextLine();
								
								//Enter 0 to exit
					            if (newIC.equals("0")) 
					            {
					                quit = true;
					                System.out.println("\n\t\t     Exiting...");
					            } 
					            // Validate the updated ic is in length of 12 digits
					            else if (newIC.length() != 12) 
								{
									System.out.println("\t\t     Invalid IC! Please follow standard IC format: YYMMDDBP###G\n");
									System.out.println("\t\t     Failed to update ");
								} 
								// Save updated student's ic to Student.txt
								else 
								{
									file.getStudentList().get(index).setIC(newIC);
									try {
										file.saveFile();
									} catch (IOException e) {
										e.printStackTrace();
									}
									quit= true;
									System.out.println("\t\t     IC updated successfully!");
								}
							}while (!quit);

							break;
						}
							
						// Update student's sex
						case 3: 
						{
							boolean quit = true;
							do
							{
								System.out.print("\t\t     Enter new sex (female/male): ");
								String newSex = input.nextLine().toUpperCase();
				
								//Enter 0 to exit
					            if (newSex.equals("0")) 
					            {
					                quit = true;
					                System.out.println("\n\t\t     Exiting...");
					            } 
					            else if(!newSex.equals("FEMALE") && !newSex.equals("MALE"))
								{
									System.out.println("\t\t     Invalid gender! Please enter either female or male.\n");
									System.out.println("\t\t     Failed to update ");
								}
								else 
								// Save updated student's sex to Student.txt
								{
									file.getStudentList().get(index).setSex(newSex);
									try {
										file.saveFile();
									} catch (IOException e) {
										e.printStackTrace();
									}
									System.out.println("\t\t     Sex updated successfully!");
									quit=true;
								}
							}while(!quit);

							break;
						}
						
						// Update student's standard
						case 4: 
						{
							boolean quit= true;
							do
							{
								System.out.print("\t\t     Enter new Standard (1/2/3/4/5/6): ");
								int newStandard = Integer.parseInt(input.nextLine());
								
								//Enter 0 to exit
					            if (newStandard == 0) 
					            {
					                quit = true;
					                System.out.println("\n\t\t     Exiting...");
					            } 
					            else if(newStandard < 1 || newStandard > 6)
								{
									System.out.println("\t\t     Invalid standard! Primary education in Malaysia starts from Standard 1 and ends with Standard 6.\n");
									System.out.println("\t\t     Failed to update ");
								}
								// Save updated student's standard to Student.txt
								else 
								{
									file.getStudentList().get(index).setStandard(newStandard);
									try {
										file.saveFile();
									} catch (IOException e) {
										e.printStackTrace();
									}
									System.out.println("\t\t     Standard updated successfully!");
									quit=true;
								}
							}while (!quit);

							break;
						}
						
						// Update student's class
						case 5:
						{
							boolean quit=true;
							String[] classArray = {"A", "B", "C", "D", "E"};
							do
							{
								System.out.print("\t\t     Enter new class (A/B/C/D/E): ");
								char newClass = input.nextLine().toUpperCase().charAt(0);

								//Enter 0 to exit
					            if (newClass == '0') 
					            {
					                quit = true;
					                System.out.println("\n\t\t     Exiting...");
					            } 
					  
								// Validate class entered is in class array (A/B/C/D/E)
								boolean clsExist = false;
								for (String cls : classArray) 
								{
									if (newClass == cls.charAt(0)) 
									{
										clsExist = true;
										// Save updated student's class to Student.txt
										file.getStudentList().get(index).setCls(Character.toString(newClass));
										try {
											file.saveFile();
										} catch (IOException e) {
											e.printStackTrace();
										}
										System.out.println("\t\t     Class updated successfully!");
										quit=true;
										break; 
									}
								}

								if (!clsExist) 
								{
									System.out.println("\t\t     Invalid class! Please choose class among the valid classes A, B, C, D, E.\n");
									System.out.println("\t\t     Failed to update ");
								}
							}while(!quit);

							break;
						}
						case 0: 
						{
							System.out.println("\t\t     Exiting...");
							exit = true;
							break;
						}
						default:
								System.out.println("Invalid input :(");
								break;
					}
				}
			}
    	}
    }
    
    // Record students' marks
    public void recordMarks()
    {
    	boolean exit = false;
		
		// Read data from files
        try {
            file.readFile();
        } catch (Exception e) {
            System.out.println("Error reading data from files: " + e.getMessage());
        }
        
        do
        {
        	int index = 0;
        	int bm, bc, bi, mm, sc;
        	String ans;
        	
        	System.out.println("\t\t*******************************************************");
    		System.out.println("\t\t**             Record Student Marks                  **");
    		System.out.println("\t\t*******************************************************");
			System.out.println();
    		System.out.println("\t\t     Enter 0 to exit");
    		
    		// Enter student's ic to record the student's marks
    		System.out.print("\t\t     Enter student's ic (without '-'): ");
            String ic = input.nextLine();
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
            	//Search the student's ic in Student.txt
                boolean found = false;
                
                for(int i = 0; i < file.getStudentList().size(); i++)
                {
                	if(ic.equals(file.getStudentList().get(i).getIC().replace("/", "")))
                	{
                		found = true;
                		index = i;
                		System.out.println();
		        		System.out.println("\t\t  *******************************************************");
		        		System.out.println("\t\t     Name: " + file.getStudentList().get(i).getName() + "\t\t     ID: " + file.getStudentList().get(i).getID());
		        		System.out.println("\t\t  *******************************************************");
		        		break;
                	}            	
                }
                
                if(!found)
                {
                	System.out.println("\n\t\t     IC not found. Please enter again.");	
                }
                else
                {
                	// Confirm whether the teacher wants to enter marks for this student
                	System.out.print("\n\t\t     Press 'Y' to continue to enter marks: ");
                	ans = input.nextLine().toUpperCase();
                	
                	// Enter student's marks for BM, BC, BI, MM, SC
                	if (ans.equals("Y"))
                	{
                		do
                    	{
                    		System.out.print("\n\t\t     Enter mark for BM: ");
                        	
                        	if(input.hasNextInt())
                        	{
                        		bm = input.nextInt();
                        		if(bm < 0 || bm > 100)
                            	{
                            		System.out.println("\n\t\t     Please enter marks in the range of 0 - 100.");
                            	}
                            	else
                            	{
                            		file.getStudentList().get(index).getMarks().setBM(bm);
                            		break;
                            	}
                        	}
                        	else
                        	{
                        		System.out.println("\n\t\t     Please enter integer for marks.");
                        		input.next();
                        	}
                        	
                    	}while(true);
                    	
                		do
                    	{
                    		System.out.print("\n\t\t     Enter mark for BC: ");
                        	
                        	if(input.hasNextInt())
                        	{
                        		bc = input.nextInt();
                        		if(bc < 0 || bc > 100)
                            	{
                            		System.out.println("\n\t\t     Please enter marks in the range of 0 - 100.");
                            	}
                            	else
                            	{
                            		file.getStudentList().get(index).getMarks().setBC(bc);
                            		break;
                            	}
                        	}
                        	else
                        	{
                        		System.out.println("\n\t\t     Please enter integer for marks.");
                        		input.next();
                        	}
                        	
                    	}while(true);
                    	
                		do
                    	{
                    		System.out.print("\n\t\t     Enter mark for BI: ");
                        	
                        	if(input.hasNextInt())
                        	{
                        		bi = input.nextInt();
                        		if(bi < 0 || bi > 100)
                            	{
                            		System.out.println("\n\t\t     Please enter marks in the range of 0 - 100.");
                            	}
                            	else
                            	{
                            		file.getStudentList().get(index).getMarks().setBI(bi);
                            		break;
                            	}
                        	}
                        	else
                        	{
                        		System.out.println("\n\t\t     Please enter integer for marks.");
                        		input.next();
                        	}
                        	
                    	}while(true);
                    	
                		do
                    	{
                    		System.out.print("\n\t\t     Enter mark for MM: ");
                        	
                        	if(input.hasNextInt())
                        	{
                        		mm = input.nextInt();
                        		if(mm < 0 || mm > 100)
                            	{
                            		System.out.println("\n\t\t     Please enter marks in the range of 0 - 100.");
                            	}
                            	else
                            	{
                            		file.getStudentList().get(index).getMarks().setMM(mm);
                            		break;
                            	}
                        	}
                        	else
                        	{
                        		System.out.println("\n\t\t     Please enter integer for marks.");
                        		input.next();
                        	}
                        	
                    	}while(true);
                    	
                		do
                    	{
                    		System.out.print("\n\t\t     Enter mark for SC: ");
                        	
                        	if(input.hasNextInt())
                        	{
                        		sc = input.nextInt();
                        		if(sc < 0 || sc > 100)
                            	{
                            		System.out.println("\n\t\t     Please enter marks in the range of 0 - 100.");
                            	}
                            	else
                            	{
                            		file.getStudentList().get(index).getMarks().setSC(sc);
                            		break;
                            	}
                        	}
                        	else
                        	{
                        		System.out.println("\n\t\t     Please enter integer for marks.");
                        		input.next();
                        	}
                        	
                    	}while(true);
                    	// Save students' marks
                		input.nextLine();
                    	try {
							file.saveFile();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}	
                    	System.out.println("\n\t\t     Recorded successfully...");
                    	
                    	// Allow teacher to record marks for another student
                    	System.out.print("\n\t\t     Anymore student to record marks? (Y/N): ");
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
                	}
                	else
                	{
                		System.out.println("\n\t\t     Exiting...");
                		exit = true;
                	}
                }
            }
            
        }while(!exit);
    }
    
    // Update teacher's personal details
    public void updateTeacherInfo(Teacher tc)
    {
    	// Read data from files
		try {
			file.readFile();
		} catch (Exception e) {
			System.out.println("Error reading data from files: " + e.getMessage());
		}
		
    	boolean exit = false;
    	while(!exit)
    	{
    		int idx = -1; 
			
			// Search teacher's IC in Teacher.txt
			for (int i = 0; i < file.getTeacherList().size(); i++) 
    		{
				if (tc.getIC().toUpperCase().equals(file.getTeacherList().get(i).getIC().replace("/", ""))) 
				{
					idx = i; 
					break; 
				}
			}
			if (idx == -1) 
			{
				// Teacher not found
				System.out.println("\t\t     Teacher not found.");
				exit=true;
			} 
			else 
			{
				// Teacher found, continue with the profile update
				// Display the profile update options
				System.out.println("\t\t*******************************************************");
				System.out.println("\t\t**             Teacher Profile                       **");
				System.out.println("\t\t*******************************************************");
				System.out.println("\t\t**               1 - Name                            **");
				System.out.println("\t\t**               2 - Password                        **");
				System.out.println("\t\t**               0 - Exit                            **");
				System.out.println("\t\t*******************************************************");
				System.out.println();
				System.out.print("\t\t     Enter option: ");
				int opt = Integer.parseInt(input.nextLine());
					
				switch(opt)
				{
				// Update teacher's new name
				case 1: 
				{

					boolean quit = false;
					do {
						System.out.print("\t\t     Enter new name: ");
						String newName = input.nextLine().toUpperCase();
							
							//Enter 0 to exit
						if (newName.equals("0")) 
						{
							quit = true;
							System.out.println("\n\t\t     Exiting...");
						} 
						else
						{
							boolean validName = true;
							// Validate the name does not contain number
							for (int j = 0; j < newName.length(); j++) 
							{
								char inputChar = newName.charAt(j);
								if (Character.isDigit(inputChar)) 
								{
									validName = false;
									break;
								}
							}

							if (!validName) 
							{
								System.out.println("\t\t     Invalid name! Please do not include any numbers.\n");
								System.out.println("\t\t     Failed to update ");
							} 
							// Save updated teacher's name to Teacher.txt
							else 
							{
								file.getTeacherList().get(idx).setName(newName);
								try {
									file.saveFile();
								} catch (IOException e) {
									e.printStackTrace();
								}
								quit = true;
								System.out.println("\t\t     Name updated successfully!");
							}
						}
					} while (!quit);
					break;
				}
				// Update teacher's password	
				case 2:
				{
					boolean quit=false;
					do
					{
						System.out.print("\t\t     Enter new password (8 digits): ");
						String newPassword = input.nextLine(); 
							
						//Enter 0 to exit
						if (newPassword.equals("0")) 
						{
							quit = true;
							System.out.println("\n\t\t     Exiting...");
						} 
						// Validate the password is in the length of 8 digits
						else if (newPassword.length() != 8)
						{
							System.out.println("\t\t     Invalid password! Please enter 8-digit password.\n");
							System.out.println("\t\t     Failed to update ");
						}
						// Save updated teacher's password to Teacher.txt
						else 
						{
							file.getTeacherList().get(idx).setPassword(newPassword);
							try {
								file.saveFile();
							} catch (IOException e) {
								e.printStackTrace();
							}
							System.out.println("\t\t     Password updated successfully!");
							quit=true;
						}
					}while(!quit);

					break;
				}
				case 0: 
					System.out.println("\n\t\t     Exiting...");
					break;
				default:
					System.out.println("Invalid input:(");
					break;
				}
				exit = true;
    		}
    		
    	}
	}		
}
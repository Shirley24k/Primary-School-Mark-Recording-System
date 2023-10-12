import java.util.Scanner;

public class Analysis
{
    private Scanner input = new Scanner(System.in);
	private Files file = new Files();
    // Display student marks analysis for teacher to review
    public void viewAnalysis()
    {
    	boolean close = false;
    	boolean exit = false;
    	// Read data from files
    	try {
    		file.readFile();
    	} catch (Exception e) {
    		System.out.println("Error reading data from files: " + e.getMessage());
    	}
    	// Display menu to let teacher check marks analysis of students for a specific standard or a specific class
    	System.out.println("\t\t*******************************************************");
		System.out.println("\t\t**               Marks Analysis                      **");
		System.out.println("\t\t*******************************************************");
		System.out.println("\t\t**               1 - Standard                        **");
		System.out.println("\t\t**               2 - Class                           **");
		System.out.println("\t\t**               3 - Exit                            **");
		System.out.println("\t\t*******************************************************");
		System.out.println();

		do
    	{
			System.out.print("\t\t     Enter option: ");
			int opt = input.nextInt();
    		switch(opt)
    		{
    		// Marks analysis of students for a specific standard 
    		case 1: 
    			do
    			{
    				int index = 0;
					int count=0;
					double ttlBM = 0;
					double ttlBC = 0;
					double ttlBI = 0;
					double ttlMM = 0;
					double ttlSC = 0;

					double psBM = 0;
					double psBC = 0;
					double psBI = 0;
					double psMM = 0;
					double psSC = 0;
					int standard;
					// Validate correct standard input
					do
	    			{	
    					
    					System.out.print("\t\t     Enter standard year (1/2/3/4/5/6): ");
    					standard = input.nextInt();
    					if(standard < 1 || standard > 6)
    						System.out.println("\n\t\t     Invalid input :(");
    				}while(standard < 1 || standard > 6);
					for(int i = 0; i < file.getStudentList().size(); i++)
    				{
    					// Read data from file that match the standard input
						if(standard==(file.getStudentList().get(i).getStandard()))
    					{
    						index = i;
    						count +=1;
    						ttlBM += file.getStudentList().get(index).getMarks().getBM();
    						ttlBC += file.getStudentList().get(index).getMarks().getBC();
    						ttlBI += file.getStudentList().get(index).getMarks().getBI();
    						ttlMM += file.getStudentList().get(index).getMarks().getMM();
    						ttlSC += file.getStudentList().get(index).getMarks().getSC();
    						if(file.getStudentList().get(index).getMarks().getBM()>=40)
    						{
    							psBM += 1;
    						}
    						if(file.getStudentList().get(index).getMarks().getBC()>=40)
    						{
    							psBC += 1;
    						}
    						if(file.getStudentList().get(index).getMarks().getBI()>=40)
    						{
    							psBI += 1;
    						}
    						if(file.getStudentList().get(index).getMarks().getMM()>=40)
    						{
    							psMM += 1;
    						}
    						if(file.getStudentList().get(index).getMarks().getSC()>=40)
    						{
    							psSC += 1;
    						}
    					}
    				}
					// Display menu of marks analysis for standard input for teacher to view for average score or passing rate for each subject
    				System.out.println("\t\t*******************************************************");
    				System.out.println("\t\t**     Marks Analysis(Standard)                      **");
    				System.out.println("\t\t*******************************************************");
    				System.out.println("\t\t**     1 - Average Score For Each Subject            **");
    				System.out.println("\t\t**     2 - Passing Rate For Each Subject             **");
    				System.out.println("\t\t**     3 - Exit                                      **");
    				System.out.println("\t\t*******************************************************");
    				System.out.println();
    				System.out.print("\t\t     Enter option: ");
    				int opt2 = input.nextInt();
    				switch(opt2)
    				{
	    				// Display subject average score for each subject 
    					case 1: 
	    					System.out.println("\t\t*******************************************************");
	    					System.out.printf("\t\t**  Subject Average Score for Standard %d             **\n",standard);
	    					System.out.println("\t\t*******************************************************");
	    					System.out.printf("\t\t** 	BM: %6.2f%%                                  **\n",ttlBM/count);
	    					System.out.printf("\t\t** 	BC: %6.2f%%                                  **\n",ttlBC/count);
	    					System.out.printf("\t\t** 	BI: %6.2f%%                                  **\n",ttlBI/count);
	    					System.out.printf("\t\t** 	MM: %6.2f%%                                  **\n",ttlMM/count);
	    					System.out.printf("\t\t** 	SC: %6.2f%%                                  **\n",ttlSC/count);
	    					System.out.println("\t\t*******************************************************");
	    					break;
	    				// Display subject passing rate for each subject 
    					case 2:
    						System.out.println("\t\t*******************************************************");
    						System.out.printf("\t\t**  Subject Passing Rate for Standard %d              **\n",standard);
    						System.out.println("\t\t*******************************************************");
    						System.out.printf("\t\t** 	BM: %6.2f%%                                  **\n",psBM/count*100);
    						System.out.printf("\t\t** 	BC: %6.2f%%                                  **\n",psBC/count*100);
    						System.out.printf("\t\t** 	BI: %6.2f%%                                  **\n",psBI/count*100);
    						System.out.printf("\t\t** 	MM: %6.2f%%                                  **\n",psMM/count*100);
    						System.out.printf("\t\t** 	SC: %6.2f%%                                  **\n",psSC/count*100);
    						System.out.println("\t\t*******************************************************");
    						break;
    					case 3: 
    						exit = true;
    						break;
    					default:
    						System.out.println("\n\t\t     Invalid input :(");
    						break;
					}
    				
    		    	String ans;
    		    	// Allow teacher to view for another standard analysis 
    		    	do
    				{
						System.out.print("\n\t\t     Continue Checking Standard Mark Analysis? (Y/N): ");
	    		    	ans = input.next().toUpperCase();
	    		    	if(!(ans.equals("Y")||ans.equals("N")))
	    		    	{
	    		    		System.out.println("\n\t\t     Invalid input :(");
	    		    	}
    				}while(!(ans.equals("Y")||ans.equals("N")));
					if(ans.equals("Y"))
    		    	{
    		    		close = false;
    		    		System.out.println();
    		    	}
    		    	else if(ans.equals("N"))
    		    	{
    		    		close=true;
    		    		exit = true;
    		    	}
    			}while(!close);
    			break;
    		// Marks analysis of students for a specific standard and class
    		case 2:
    			do
    			{
    				int index = 0;
					int count =0;
					double ttlBM = 0;
					double ttlBC = 0;
					double ttlBI = 0;
					double ttlMM = 0;
					double ttlSC = 0;
					int BMHighest = 0;
					int BCHighest = 0;
					int BIHighest = 0;
					int MMHighest = 0;
					int SCHighest = 0;
					boolean clsExist = false;
    				int standard;
    				String [] clsArray = {"A", "B", "C", "D", "E"};
    				String studCls;
    				// Validate input of standard and class
    				do
    				{
    					System.out.print("\t\t     Enter standard year (1/2/3/4/5/6): ");
        				standard = input.nextInt();
        				input.nextLine();
        				System.out.print("\t\t     Enter student class (A/B/C/D/E): ");
        				studCls = input.nextLine().toUpperCase();
        				for (String cls : clsArray)
        				{
        					if (studCls.equals(cls))
        						clsExist=true;
        				}
        				if(standard < 1 || standard > 6)
        				{
        					System.out.println("\t\t     Invalid standard input! ");
        				}
        				else if(!clsExist)
        				{
        					System.out.println("\t\t     Invalid class input!    ");
        				}
    				}while((standard < 1 || standard > 6)||!clsExist);
    				// Display menu of marks analysis for specific standard and class input for teacher to view for average score or highest achiever for each subject
    				System.out.println("\t\t*******************************************************");
    				System.out.println("\t\t**     Marks Analysis (Class)                        **");
    				System.out.println("\t\t*******************************************************");
    				System.out.println("\t\t**     1 - Average Score For Each Subject            **");
    				System.out.println("\t\t**     2 - Highest Achiever For Each Subject         **");
    				System.out.println("\t\t**     3 - Exit                                      **");
    				System.out.println("\t\t*******************************************************");
    				System.out.println();
	
    				for(int i = 0; i < file.getStudentList().size(); i++)
					{
    					// Read data from file that match the standard and class input
    					if(standard==(file.getStudentList().get(i).getStandard()) && studCls.toUpperCase().equals(file.getStudentList().get(i).getCls()))
						{
							index = i;
							count += 1;
							
							ttlBM += file.getStudentList().get(index).getMarks().getBM();
							ttlBC += file.getStudentList().get(index).getMarks().getBC();
							ttlBI += file.getStudentList().get(index).getMarks().getBI();
							ttlMM += file.getStudentList().get(index).getMarks().getMM();
							ttlSC += file.getStudentList().get(index).getMarks().getSC();
							if(file.getStudentList().get(index).getMarks().getBM()>BMHighest)
							{
								BMHighest = file.getStudentList().get(index).getMarks().getBM();
							}
							if(file.getStudentList().get(index).getMarks().getBC()>BCHighest)
							{
								BCHighest = file.getStudentList().get(index).getMarks().getBC();
							}
							if(file.getStudentList().get(index).getMarks().getBI()>BIHighest)
							{
								BIHighest = file.getStudentList().get(index).getMarks().getBI();
							}
							if(file.getStudentList().get(index).getMarks().getMM()>MMHighest)
							{
								MMHighest = file.getStudentList().get(index).getMarks().getMM();
							}
							if(file.getStudentList().get(index).getMarks().getSC()>SCHighest)
							{
								SCHighest = file.getStudentList().get(index).getMarks().getSC();
							}
						}
					}
					System.out.print("\t\t     Enter option: ");
					int opt2 = input.nextInt();
					switch(opt2)
					{
						// Display subject average score of the specific class and standard input
						case 1: 
							System.out.println("\t\t*******************************************************");
							System.out.printf("\t\t**  Subject Average Score for Standard %d Class %s     **\n",standard,studCls);
							System.out.println("\t\t*******************************************************");
							System.out.printf("\t\t** 	BM: %6.2f%%                                  **\n",ttlBM/count);
							System.out.printf("\t\t** 	BC: %6.2f%%                                  **\n",ttlBC/count);
							System.out.printf("\t\t** 	BI: %6.2f%%                                  **\n",ttlBI/count);
							System.out.printf("\t\t** 	MM: %6.2f%%                                  **\n",ttlMM/count);
							System.out.printf("\t\t** 	SC: %6.2f%%                                  **\n",ttlSC/count);
							System.out.println("\t\t*******************************************************");
							break;
						// Display highest achiever of the specific class and standard input
						case 2:
							System.out.println("\t\t*******************************************************");
							System.out.printf("\t\t**  Highest Achiever for Standard %d Class %s          **\n",standard,studCls);
							System.out.println("\t\t*******************************************************");
							System.out.println("\n\t\t*******************************************************");
							System.out.printf("\t\t** 	BM                                           **\n");
							System.out.println("\t\t*******************************************************");
							int numHBM=0;
							int numHBC=0;
							int numHBI=0;
							int numHMM=0;
							int numHSC=0;
							
							for(int j = 0; j < file.getStudentList().size(); j++)
							{
								
								if ((BMHighest==(file.getStudentList().get(j).getMarks().getBM())&&
										(standard==(file.getStudentList().get(j).getStandard())&&
										studCls.toUpperCase().equals(file.getStudentList().get(j).getCls()))))
								{
									System.out.printf("\t\t** 	%d) %s  Mark:%d                 \n",numHBM+1,file.getStudentList().get(j).getName(),BMHighest);
									numHBM++;
									
								}
							}    						        
							System.out.println("\n\t\t*******************************************************");
							System.out.printf("\t\t** 	BC                                           **\n");
							System.out.println("\t\t*******************************************************");
							
							for(int j = 0; j < file.getStudentList().size(); j++)
							{
								
								if ((BCHighest==(file.getStudentList().get(j).getMarks().getBC())&&
										(standard==(file.getStudentList().get(j).getStandard())&&
										studCls.toUpperCase().equals(file.getStudentList().get(j).getCls()))))
								{
									System.out.printf("\t\t** 	%d) %s  Mark:%d                 \n",numHBC+1,file.getStudentList().get(j).getName(),BCHighest);
									numHBC++;
								}
							}
							System.out.println("\n\t\t*******************************************************");
							System.out.printf("\t\t** 	BI                                           **\n");
							System.out.println("\t\t*******************************************************");
							
							for(int j = 0; j < file.getStudentList().size(); j++)
							{
								
								if ((BIHighest==(file.getStudentList().get(j).getMarks().getBI())&&
										(standard==(file.getStudentList().get(j).getStandard())&&
										studCls.toUpperCase().equals(file.getStudentList().get(j).getCls()))))
								{
									System.out.printf("\t\t** 	%d) %s  Mark:%d                 \n",numHBI+1,file.getStudentList().get(j).getName(),BIHighest);
									numHBI++;
								}
							}
							System.out.println("\n\t\t*******************************************************");
							System.out.printf("\t\t** 	MM                                           **\n");
							System.out.println("\t\t*******************************************************");
							for(int j = 0; j < file.getStudentList().size(); j++)
							{
								
								if ((MMHighest==(file.getStudentList().get(j).getMarks().getMM())&&
										(standard==(file.getStudentList().get(j).getStandard())&&
										studCls.toUpperCase().equals(file.getStudentList().get(j).getCls()))))
								{
									System.out.printf("\t\t** 	%d) %s  Mark:%d                 \n",numHMM+1,file.getStudentList().get(j).getName(),MMHighest);
									numHMM++;
								}
							}
							System.out.println("\n\t\t*******************************************************");
							System.out.printf("\t\t** 	SC                                           **\n");
							System.out.println("\t\t*******************************************************");
							for(int j = 0; j < file.getStudentList().size(); j++)
							{
								
								if ((SCHighest==(file.getStudentList().get(j).getMarks().getSC())&&
										(standard==(file.getStudentList().get(j).getStandard())&&
										studCls.toUpperCase().equals(file.getStudentList().get(j).getCls()))))
								{
									System.out.printf("\t\t** 	%d) %s  Mark:%d                 \n",numHSC+1,file.getStudentList().get(j).getName(),SCHighest);
									numHSC++;
								}
							}
							System.out.println("\t\t*******************************************************");
							break;
						case 3: 
							exit = true;
							break;
						default:
							System.out.println("\n\t\t     Invalid input :(");
							break;
					}
					String ans;
					// Allow teacher to view for another class analysis 
					do
    				{
						System.out.print("\n\t\t     Continue Checking Class Mark Analysis? (Y/N): ");
	    		    	ans = input.next().toUpperCase();
	    		    	if(!(ans.equals("Y")||ans.equals("N")))
	    		    	{
	    		    		System.out.println("\n\t\t     Invalid input :(");
	    		    	}
    				}while(!(ans.equals("Y")||ans.equals("N")));
					if(ans.equals("Y"))
    		    	{
    		    		close = false;
    		    		System.out.println();
    		    	}
    		    	else if(ans.equals("N"))
    		    	{
    		    		close=true;
    		    		exit = true;
    		    	}
    			}while(!close);
    			break;
    		case 3: 
    			exit = true;
    			break;
    		default:
    			System.out.println("\n\t\t     Invalid input :(");
    			break;
    		}
    	}while(!exit);
    	System.out.println("\n\t\t     Exiting...");
    }  
	
    // Display student result 
	public void viewResults(Student student)
	{
		System.out.println("\t\t**********************************************************");
		if (student.getName().length() >= 6)
			System.out.printf("\t\t**  Name: %s\t\t\t\t\t**",student.getName());
		else
    		System.out.printf("\t\t**  Name: %s\t\t\t\t\t\t**",student.getName());
		System.out.printf("\n\t\t**  ID  : %s\t\t\t\t\t\t**",student.getID());
		System.out.printf("\n\t\t**  Class: %d%s\t\t\t\t\t\t**",student.getStandard(), student.getCls());
    	System.out.println("\n\t\t**********************************************************");
		System.out.println("\t\t** 	    Marks\t\tGrade\t\t\t**");
    	System.out.printf("\t\t**      BM: %d\t\t\t%s\t\t\t**",student.getMarks().getBM(), grading(student.getMarks().getBM()));
    	System.out.printf("\n\t\t** 	BC: %d\t\t\t%s\t\t\t**",student.getMarks().getBC(), grading(student.getMarks().getBC()));
    	System.out.printf("\n\t\t** 	BI: %d\t\t\t%s\t\t\t**",student.getMarks().getBI(), grading(student.getMarks().getBI()));
    	System.out.printf("\n\t\t** 	MM: %d\t\t\t%s\t\t\t**",student.getMarks().getMM(), grading(student.getMarks().getMM()));
    	System.out.printf("\n\t\t** 	SC: %d\t\t\t%s\t\t\t**",student.getMarks().getSC(), grading(student.getMarks().getSC()));
    	System.out.println("\n\t\t**********************************************************");
	}
	
	// Providing grade for the marks obtained
	public String grading(int marks)
    {
        String grade;

        if (marks >= 80)
            grade = "A";
        else if (marks >= 65)
            grade = "B";
        else if (marks >= 50)
            grade = "C";
        else if (marks >= 40)
            grade = "D";
        else
            grade = "E";
        
        return grade;
    }
}



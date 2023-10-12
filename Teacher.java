import java.util.*;

public class Teacher extends User
{
	private String password;
	
	// Constructor
	public Teacher(String name, String id, String ic, String password) 
	{
        super(name, id, ic); 
        this.password = password;
    }
	
	// Default Constructor
	public Teacher()
	{

	}

	// Accessor
	public String getPassword()
	{
		return password;
	}
	
	// Mutator
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	// Generate a new teacher ID
    public static String teacherID(ArrayList<Teacher> teacherList)
    {
    	Teacher lastTeacher = teacherList.get(teacherList.size() - 1);
    	String lastTeacherID = lastTeacher.getID();
    	int lastNum = Integer.parseInt(lastTeacherID.substring(1));
    	return "T" + String.format("%04d", lastNum + 1);
    }
}

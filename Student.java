import java.util.ArrayList;

public class Student extends User
{
	private String sex;
	private int standard;
    private String studCls;
	private Marks marks;
	
	// Constructor
	public Student(String  name,String id, String ic, String sex, int standard, String studCls, int BM, int BC, int BI, int MM, int SC)
	{
		super(name, id , ic);
		this.sex = sex;
		this.standard = standard;
        this.studCls = studCls;
		marks = new Marks(BM, BC, BI, MM ,SC);
	}

	// Accessor to get class Marks
	public Marks getMarks()
	{
		return marks;
	}

	// Overloaded Constructor
	public Student(String name,String id, String ic, String sex, int standard, String studCls)
	{
		super(name, id, ic);
		this.sex = sex;
		this.standard = standard;
        this.studCls = studCls;
		marks = new Marks(0, 0, 0, 0 ,0);
	}
	
	// Accessors and Mutators
	public String getSex()
	{
		return sex;
	}
	
	public void setSex(String sex)
	{
		this.sex = sex;
	}
	
	public int getStandard()
	{
		return standard;
	}
	
	public void setStandard(int standard)
	{
		this.standard = standard;
	}
	
	public String getCls()
	{
		return studCls;
	}
	
	public void setCls(String studCls)
	{
		this.studCls = studCls;
	}
    
    // Generate a new student ID
    public static String studentID(ArrayList<Student> studentList)
    {
    	Student lastStudent = studentList.get(studentList.size() - 1);
    	String lastStudentID = lastStudent.getID();
    	int lastNum = Integer.parseInt(lastStudentID.substring(1));
    	return "S" + String.format("%04d", lastNum + 1);
    }
}
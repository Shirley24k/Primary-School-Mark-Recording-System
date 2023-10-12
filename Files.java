import java.util.*;
import java.io.*;

public class Files
{
    public ArrayList<Teacher> teacherList = new ArrayList<Teacher>();
	public ArrayList<Student> studentList = new ArrayList<Student>();
    private File file1 = new File("Teacher.txt");
    private File file2 = new File("Student.txt"); 

    // ArrayLists' Accessors and Mutators
    public ArrayList<Teacher> getTeacherList()
    {
        return teacherList;
    }

    public void setTeacherList(ArrayList<Teacher> t)
    {
        this.teacherList = t;
    }

    public ArrayList<Student> getStudentList()
    {
        return studentList;
    }

    public void setStudentList(ArrayList<Student> s)
    {
        this.studentList = s;
    }

    //To save file
	public void saveFile() throws IOException 
    {
        FileWriter teacherWriter = new FileWriter(file1, false);
        FileWriter studentWriter = new FileWriter(file2, false);
        
        // Save Teacher into String and write into txt file
        for (Teacher t : teacherList)
        {
            String name = t.getName();
            String id = t.getID();
            String ic = t.getIC();
            String password = t.getPassword();
            String string = String.format("%s/%s/%s/%s\n", name, id, ic, password);

            teacherWriter.write(string);
        }

        // Save Student into String and write into txt file
        for (Student s : studentList)
        {
            String name = s.getName();
            String id = s.getID();
            String ic = s.getIC();
            String sex = s.getSex();
            int standard = s.getStandard();
            String studCls = s.getCls();
            int BM = s.getMarks().getBM();
            int BC = s.getMarks().getBC();
            int BI = s.getMarks().getBI();
            int MM = s.getMarks().getMM();
            int SC = s.getMarks().getSC();
            String string = String.format("%s/%s/%s/%s/%d/%s/%d/%d/%d/%d/%d\n", name, id, ic, sex, standard,
                                        studCls, BM, BC, BI, MM, SC);
                
            studentWriter.write(string);
        }

        // Close FileWritter
        teacherWriter.close();
        studentWriter.close();
    }

	//To read file
    public void readFile() throws Exception
    {
        String line;
		BufferedReader teacherReader = new BufferedReader(new FileReader(file1));
		BufferedReader studentReader = new BufferedReader(new FileReader(file2));
		teacherList.clear();
		studentList.clear();
        
        // Read the teacher.txt line by line
        while ((line = teacherReader.readLine()) != null)
        {
        	String[] tDetails = line.split("/"); // Split the line by using "/" as the regex
            String name = tDetails[0];
            String id  = tDetails[1];
            String ic = tDetails[2];
            String password = tDetails[3];
            Teacher t = new Teacher(name, id, ic, password); // Create Teacher
            teacherList.add(t); // Add Teacher to ArrayList
        }

        // Read the student.txt line by line
        while ((line = studentReader.readLine()) != null)
        {
        	String[] sDetails = line.split("/"); // Split the line by using "/" as the regex
            String name = sDetails[0];
            String id = sDetails[1];
            String ic = sDetails[2];
            String sex = sDetails[3];
            // The standard saved in txt file is in String, so need to use Integer.parseInt()
            int standard = Integer.parseInt(sDetails[4]);
            String studCls = sDetails[5];
            int BM = Integer.parseInt(sDetails[6]);
            int BC = Integer.parseInt(sDetails[7]);
            int BI = Integer.parseInt(sDetails[8]);
            int MM = Integer.parseInt(sDetails[9]);
            int SC = Integer.parseInt(sDetails[10]);
            Student s = new Student(name, id, ic, sex, standard, studCls, BM, BC, BI, MM, SC); //Create Student
            studentList.add(s); // Add Student into ArrayList
        }

        // Close BufferedReader
        teacherReader.close();
        studentReader.close();
    }
}

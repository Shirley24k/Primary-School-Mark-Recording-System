import java.util.Scanner;

public class Main
{
    private static int opt;
    private static Scanner input = new Scanner(System.in);
    private static Login lg = new Login();
    public static void main(String[] args)
    {
        boolean exit = true;
        while(exit)
        {
            System.out.println("\t\t***************************************************");
            System.out.println("\t\t**           Welcome to Grading System           **");
            System.out.println("\t\t***************************************************");
            System.out.println("\t\t**                Main Menu                      **");
            System.out.println("\t\t***************************************************");
            System.out.println("\t\t**      1 - Teacher                              **");
            System.out.println("\t\t**      2 - Student                              **");
            System.out.println("\t\t**      0 - Exit                                 **");
            System.out.println("\t\t***************************************************");

            boolean repeat = true;
            do
            {
                System.out.print("\t\t      Enter an Option: ");
                opt = input.nextInt();
            
                if(opt == 1)// Go to teacher login page in Class Login
                {
                    repeat =  false;
                    lg.teacherLoginPage();
                }
                else if(opt == 2)// Go to student login page in Class Login
                {
                    repeat =  false;
                    lg.studentLogin();
                }
                else if(opt == 0)// Exit program
                {
                    repeat = false;
                    exit = false;
                }
                else// Number other than 1, 2 and 0
                    System.out.println("\t\t        Invalid Option");
            }while(repeat);
        }
    }
}


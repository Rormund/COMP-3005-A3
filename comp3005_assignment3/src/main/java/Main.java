import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/University";
        String user = "postgres";
        String password = "admin";

        //create a while loop so that the user can keep adding/updating/etc.
        int flag = 0;
        while (flag==0) {
            Scanner scan = new Scanner(System.in);  // Create a Scanner object
            System.out.println("Choose an option:");
            System.out.println("-------------------");
            System.out.println("List of students: 1");
            System.out.println("Add a student: 2");
            System.out.println("Update email: 3");
            System.out.println("Delete a student: 4");
            System.out.println("Exit: 0");

            String userInput = scan.nextLine();  // Read user input

            //switch statements to check user input
            switch(userInput){
                case "0":
                    flag = 1;
                    break;
                case "1":
                    getAllStudents();
                    break;
                case "2":
                    //get each aspect require to add new student
                    System.out.println("Enter first name: ");
                    String first = scan.nextLine();
                    System.out.println("Enter last name: ");
                    String last = scan.nextLine();
                    System.out.println("Enter email: ");
                    String email = scan.nextLine();
                    System.out.println("Enter date of enrollment (yyyy-mm-dd): ");
                    String date = scan.nextLine();
                    addStudent(first, last, email, date);
                    break;
                case "3":
                    //get new email and student id
                    System.out.println("Enter new email: ");
                    String newEmail = scan.nextLine();
                    System.out.println("Enter student id: ");
                    int id = scan.nextInt();
                    updateStudentEmail(id, newEmail);
                    break;
                case "4":
                    System.out.println("Enter a student id to delete:");
                    int deleteId = scan.nextInt();
                    deleteStudent(deleteId);
                    break;
            }
        }
    }

    //function for returning all students and information in the table
    public static void getAllStudents(){
        String url = "jdbc:postgresql://localhost:5432/Assignment3";
        String user = "postgres";
        String password = "admin";
        //try/catch block mostly taken from week 6 video on JDBC
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            //get all students entries
            statement.executeQuery("SELECT * FROM students\nORDER BY student_id");
            //below information on using metadata and for loop found here
            // 1https://stackoverflow.com/questions/24943894/how-do-you-get-values-from-all-columns-using-resultset-getbinarystream-in-jdbc
            ResultSet resultSet = statement.getResultSet();
            ResultSetMetaData meta = resultSet.getMetaData();
            int columns = meta.getColumnCount();

            while(resultSet.next()){
                for(int i=1;i<=columns;i++){
                    System.out.print(resultSet.getString(i)+" | ");
                }
                System.out.println("\n");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    //function to add a new student with all information to the table
    public static void addStudent(String first_name, String last_name, String email, String enrollment_date){
        String url = "jdbc:postgresql://localhost:5432/Assignment3";
        String user = "postgres";
        String password = "admin";
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            statement.executeQuery("INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES\n" +
                    "('" + first_name + "', '" + last_name + "', '" + email + "', '" + enrollment_date + "');");

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    //function to update a specific student's email
    public static void updateStudentEmail(int student_id, String new_email){
        String url = "jdbc:postgresql://localhost:5432/Assignment3";
        String user = "postgres";
        String password = "admin";
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            statement.executeQuery("UPDATE students \n SET email = '" +
                    new_email + "'\n WHERE student_id = " + student_id + ";");

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    //function to delete a specified student
    public static void deleteStudent(int student_id){
        String url = "jdbc:postgresql://localhost:5432/Assignment3";
        String user = "postgres";
        String password = "admin";
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            statement.executeQuery("DELETE FROM students\nWHERE student_id = " + student_id + ";");

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}

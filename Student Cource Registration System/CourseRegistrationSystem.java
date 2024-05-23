import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

class Course 
{
    String code;
    String title;
    String description;
    int capacity;
    String schedule;
    
    public Course(String code,String title,String description,int capacity,String schedule)
    {
        this.code = code;
        this.title = title;
        this.description =description;
        this.capacity = capacity;
        this.schedule = schedule;
    }
    
    public String toString()
    {
        return "Course Code: " + code + "\nTitle: " + title + "\nDescription: " + description + "\nCapacity: " + capacity+ "\nSchedule: " + schedule;
    }
    
}



class Student 
{


    String studentId;
    String name;
    ArrayList<String> registeredCourses;

    public Student(String studentId,String name)
    {
        this.studentId = studentId;
        this.name = name;
        this.registeredCourses = new ArrayList<>() ;    // the studen can register more than one courses.we should put that courses for student details. so use ArrayList<>(). 

    }
    
    public String toString()
    {
        return "Student ID: " + studentId + "\nName: " + name + "\nRegistered Courses: " + registeredCourses;
    }
    
}

public class CourseRegistrationSystem 
{

    public static void DisplayMenu()
    {
        System.out.println();
        System.out.println("1. \t\t View Course Listing");
        System.out.println("2. \t\t Register for a Course");
        System.out.println("3. \t\t Drop a Course");
        System.out.println("4. \t\t View Student Information");
        System.out.println("5. \t\t Exit");
        System.out.println();

        
    }
    public static void main(String args [])
    {
        
        Scanner scanner = new Scanner(System.in);

        Course c1=new Course("CS001","Introduction to Computer Science","Introduction to basic concept of Computer Science",50,("Tue 8.00am - 10.00am , Fri 1.00pm - 3.00pm"));
        Course c2=new Course("CS002","Coputer Programming I ","Practicing the basic concept of java programme",40,("Mon 8.00am - 11.00am , Wen 1.00pm - 4.00pm , Fri 8.00am - 11.00am"));
        Course c3=new Course("ENG001","English Languge ","Improve the skill of writting,speaking",60,("Thu 8.00am - 9.00am , Wen 8.00am - 9.00am"));

        List<Course> courses = new ArrayList<>();
        courses.add(c1);
        courses.add(c2);
        courses.add(c3);


        Student s1=new Student("S001","Amal");
        Student s2=new Student("S002","Sandaru");
        Student s3=new Student("S003","Bumal");

        List<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        students.add(s3);

        boolean run = true;

        while(run)
        {
            DisplayMenu();
            
            System.out.print("Enter your choice : ");
            int choice = scanner.nextInt();

            scanner.nextLine();
            
            switch(choice)
            {
                case 1 :
                {
                    System.out.println("\nAvailable Courses: ");
                    System.out.println(c1.toString()+ "\n\n" + c2.toString() + "\n\n" + c3.toString());
                    System.out.println();
                    break;
                }
                case 2 :
                { 
                
                    System.out.print("Enter student ID : ");
                    String studID = scanner.nextLine();
                    System.out.print("Enter course code to register : ");
                    String courseCode = scanner.nextLine();
                    registerCourse(students,courses,studID,courseCode);

                    break;
                }
                case 3 : 
                {
                    System.out.print("Enter student ID : ");
                    String studID = scanner.nextLine();
                    System.out.print("Enter course code to drop : ");
                    String courseCode = scanner.nextLine();
                    dropCourse(students,courses,studID,courseCode);

                    break;
                }
                case 4 :
                {
                    System.out.print("Enter student ID : ");
                    String studID = scanner.nextLine();
                    displayStudentInfo(students, studID);

                    break;
                }
                case 5:
                {
                    System.out.println();
                    System.out.println("\t\t<<<<<<  THANK YOU  >>>>>>>\t\t");
                    run = false;
                    break;
                }
                default:
                {
                    System.out.println("Invalid choice!");
                }
                   
            }
           
        }
        
    }

    public static Student findStudentById(List<Student> students, String studID) {
        for (Student student : students) 
        {
            if (student.studentId.equals(studID)) 
            {
                
                return student;
            }
        }
        return null;
    }

    public static Course findCourseByCode(List<Course> courses,String courseCode )
    {
        for(Course course : courses)
        {
            if(course.code.equals(courseCode))
            {
                return course;
            }
        }
        return null;

    }

   

    public static void registerCourse(List<Student> students,List<Course> courses,String studID,String courseCode )
    {
        Student student = findStudentById(students,studID);
        Course course = findCourseByCode(courses,courseCode);

        if(student == null)
        {
            System.out.println("Student not found!");
            return;
        }
        if(course == null)
        {
            System.out.println("Course not found!");
            return;
        }
        if (student.registeredCourses.contains(course.code)) 
        {
            System.out.println("Student already registered for this course!");
            return;
        }

        if (course.capacity <= 0) 
        {
            System.out.println("Course is full!");
            return;
        }
        student.registeredCourses.add(course.code);
        course.capacity--;
        System.out.println("Course successfully registered!");

    }

    public static void dropCourse(List<Student> students,List<Course> courses,String studID,String courseCode )
    {
        Student student = findStudentById(students,studID);
        Course course = findCourseByCode(courses,courseCode);

        if(student == null)
        {
            System.out.println("Student not found!");
            return;
        }
        if(course == null)
        {
            System.out.println("Course not found!");
            return;
        }
        if (!student.registeredCourses.contains(course.code)) 
        {
            System.out.println("Student not registered for this course!");
            return;
        }
        student.registeredCourses.remove(course.code);
        course.capacity++;
        System.out.println("Course successfully drop!");

    }

    public static void displayStudentInfo(List<Student> students,String studID)
    {
        Student student = findStudentById(students, studID);
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }
        System.out.println(student);
    }

    

} 
import java.util.*;
class Course{
    private String code,title,description,schedule;
    private int capacity,enrolledStudents;
    public Course(String code,String title,String description,int capacity,String schedule){
        this.code=code;
        this.title=title;
        this.description=description;
        this.capacity=capacity;
        this.schedule=schedule;
        this.enrolledStudents=enrolledStudents;
    }
    public String getCode(){return code;}
    public String getTitle(){return title;}
    public String getDescription(){return description;}
    public int getCapacity(){return capacity;}
    public int getEnrolledStudents(){return enrolledStudents;}
    public String getSchedule(){return schedule;}
    public boolean isAvailable(){return enrolledStudents<capacity;}
    public boolean enrollStudent(){
        if(isAvailable()){
            enrolledStudents++;
            return true;
        }
        return false;
    }
    public void dropStudent(){
        if(enrolledStudents>0){
            enrolledStudents--;
        }
    }

   
    public String toString(){
        return code +"-"+title+"("+enrolledStudents+"/"+capacity+"enrolled|Schedule:"+schedule;
    }
}

class Student{
    private String id,name;
    private List<Course> registeredCourses;
    public Student(String id, String name){
        this.id=id;
        this.name=name;
        this.registeredCourses=new ArrayList<>();
    }
    public String getId(){return id;}
    public String getName(){return name;}
    public List<Course> getRegisteredCourses(){return registeredCourses;}
    public boolean registerCourse(Course course){
        if(!registeredCourses.contains(course)&& course.enrollStudent()){
            registeredCourses.add(course);
            return true;
        }
        return false;
    }
    public boolean dropCourse(Course course){
        if(registeredCourses.remove(course)){
            course.dropStudent();
            return true;
        }
        return false;
    }
    @Override
    public String toString(){
        return id+"-"+name+"|RegisteredCourses: "+registeredCourses.size();
    }
 }

 public class CourseRegistrationSystem{
    private static List<Course> courses=new ArrayList<>();
    private static List<Student> students=new ArrayList<>();
    private static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {
        initializeData();
        int choice;
        do{
            System.out.println("\n STUDENT COURSE REGISTRATION SYSTEM");
            System.out.println("1.List Courses");
            System.out.println("2.Register Student");
            System.out.println("3.Enroil in a course");
            System.out.println("4.Drop a Course");
            System.out.println("5.view Student Details");
            System.out.println("6.Exit");
            System.out.println("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch(choice){
                case 1 -> listCourses();
                case 2 -> registerStudent();
                case 3 -> enrollStudentInCourse();
                case 4 -> dropCourseForStudent();
                case 5 -> viewStudentDetails();
                case 6 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice! Try again.");

            }
        }while(choice!=6);
    }
    private static void initializeData() {
        courses.add(new Course("CS101", "Intro to CS", "Basic programming concepts", 30, "Mon-Wed 10AM"));
        courses.add(new Course("MATH201", "Calculus I", "Differentiation and Integration", 25, "Tue-Thu 2PM"));
        courses.add(new Course("ENG105", "English Composition", "Writing skills", 20, "Fri 11AM"));
        students.add(new Student("S001", "Alice"));
        students.add(new Student("S002", "Bob"));
    }

    private static void listCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    private static void registerStudent() {
        System.out.print("Enter Student ID: ");
        String id = sc.nextLine();
        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();
        students.add(new Student(id, name));
        System.out.println("Student registered successfully.");
    }

    private static void enrollStudentInCourse() {
        Student student = findStudent();
        if (student == null) return;

        listCourses();
        System.out.print("Enter Course Code to Enroll: ");
        String courseCode = sc.nextLine();
        Course course = findCourse(courseCode);

        if (course != null && student.registerCourse(course)) {
            System.out.println("Successfully enrolled in " + course.getTitle());
        } else {
            System.out.println("Enrollment failed (Course full or already registered).");
        }
    }

    private static void dropCourseForStudent() {
        Student student = findStudent();
        if (student == null) return;

        System.out.println("\nRegistered Courses:");
        for (Course course : student.getRegisteredCourses()) {
            System.out.println(course.getCode() + " - " + course.getTitle());
        }

        System.out.print("Enter Course Code to Drop: ");
        String courseCode = sc.nextLine();
        Course course = findCourse(courseCode);

        if (course != null && student.dropCourse(course)) {
            System.out.println("Successfully dropped " + course.getTitle());
        } else {
            System.out.println("Drop failed (Course not found in registration).");
        }
    }

    private static void viewStudentDetails() {
        Student student = findStudent();
        if (student == null) return;

        System.out.println("\nStudent Details:");
        System.out.println(student);
        System.out.println("Registered Courses:");
        for (Course course : student.getRegisteredCourses()) {
            System.out.println(course);
        }
    }

    private static Student findStudent() {
        System.out.print("Enter Student ID: ");
        String studentId = sc.nextLine();
        for (Student student : students) {
            if (student.getId().equalsIgnoreCase(studentId)) {
                return student;
            }
        }
        System.out.println("Student not found.");
        return null;
    }

    private static Course findCourse(String courseCode) {
        for (Course course : courses) {
            if (course.getCode().equalsIgnoreCase(courseCode)) {
                return course;
            }
        }
        System.out.println("Course not found.");
        return null;
    }
}
 
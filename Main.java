import java.util.*;
import java.io.*;
import java.time.LocalDateTime;

class Student {
    private int id;
    private String name;
    private ArrayList<Integer> marks;

    public Student(int id, String name, ArrayList<Integer> marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getAverage() {
        int sum = 0;
        for (int m : marks) {
            sum += m;
        }
        return (double) sum / marks.size();
    }

    public String getGrade() {
        double avg = getAverage();
        if (avg >= 80) return "A";
        else if (avg >= 60) return "B";
        else if (avg >= 40) return "C";
        else return "D";
    }

    public String toCSV() {
        return id + "," + name + "," + getAverage() + "," + getGrade();
    }
}

public class Main {   

    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);
    private static int idCounter = 1;

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n===== Student Grade Tracker =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Report");
            System.out.println("3. Save Report (TXT)");
            System.out.println("4. Export CSV");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            while (!sc.hasNextInt()) {
                System.out.print("Enter valid number: ");
                sc.next();
            }

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: addStudent(); break;
                case 2: showReport(); break;
                case 3: saveReport(); break;
                case 4: exportCSV(); break;
                case 5: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice!");
            }

        } while (choice != 5);
    }

    private static void addStudent() {
        System.out.print("Enter student name: ");
        String name = sc.nextLine();

        System.out.print("Enter number of subjects: ");
        int subjects = sc.nextInt();

        ArrayList<Integer> marks = new ArrayList<>();

        for (int i = 0; i < subjects; i++) {
            int m;
            while (true) {
                System.out.print("Marks subject " + (i + 1) + ": ");
                m = sc.nextInt();
                if (m >= 0 && m <= 100) break;
                System.out.println("Enter marks between 0–100");
            }
            marks.add(m);
        }

        sc.nextLine();

        students.add(new Student(idCounter++, name, marks));
        System.out.println("Student added successfully!");
    }

    private static void showReport() {

        if (students.isEmpty()) {
            System.out.println("No student data.");
            return;
        }

        Student topper = students.get(0);

        System.out.println("\n---- Summary Report ----");

        for (Student s : students) {
            System.out.println(
                "ID: " + s.getId() +
                " | Name: " + s.getName() +
                " | Avg: " + s.getAverage() +
                " | Grade: " + s.getGrade()
            );

            if (s.getAverage() > topper.getAverage())
                topper = s;
        }

        System.out.println("\nTopper: " + topper.getName() +
                " (" + topper.getAverage() + ")");
    }

    private static void saveReport() {

        try {
            FileWriter writer = new FileWriter("report.txt");

            writer.write("Student Report\n");
            writer.write("Generated: " + LocalDateTime.now() + "\n\n");

            for (Student s : students) {
                writer.write(
                    "ID: " + s.getId() +
                    " Name: " + s.getName() +
                    " Avg: " + s.getAverage() +
                    " Grade: " + s.getGrade() +
                    "\n"
                );
            }

            writer.close();
            System.out.println("Saved to report.txt");

        } catch (IOException e) {
            System.out.println("File error.");
        }
    }

    private static void exportCSV() {

        try {
            FileWriter writer = new FileWriter("report.csv");

            writer.write("ID,Name,Average,Grade\n");

            for (Student s : students) {
                writer.write(s.toCSV() + "\n");
            }

            writer.close();
            System.out.println("CSV exported.");

        } catch (IOException e) {
            System.out.println("CSV error.");
        }
    }
}


package model;

public class Student {

    public static final double EXCELLENT_GPA = 3.5;
    public static final double AT_RISK_GPA = 2.0;

    private int id;
    private String name;
    private int age;
    private double gpa;

    public Student(int id, String name, int age, double gpa) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gpa = gpa;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getGpa() {
        return gpa;
    }

    public boolean isExcellent() {
        return gpa >= EXCELLENT_GPA;
    }

    public boolean isAtRisk() {
        return gpa < AT_RISK_GPA;
    }

    // Used by the Status column (PropertyValueFactory("status"))
    public String getStatus() {
        if (isExcellent()) {
            return "● Excellent";
        } else if (gpa >= 2.5) {
            return "● Good";
        } else if (!isAtRisk()) {
            return "● Pass";
        } else {
            return "● At Risk";
        }
    }
}
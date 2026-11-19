package service;

import model.Student;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class student_service {

    private final String filePath =
            "Data/students.txt";

    public student_service() {
        createFileIfMissing();
    }

    private void createFileIfMissing() {

        try {

            File file = new File(filePath);

            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }

            file.createNewFile();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    // =========================================================
    // LOAD STUDENTS
    // =========================================================

    public List<Student> loadStudents() {

        List<Student> students =
                new ArrayList<>();

        try (BufferedReader reader =
                     new BufferedReader(
                             new FileReader(filePath)
                     )) {

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                try {

                    String[] parts =
                            line.split(",");

                    int id =
                            Integer.parseInt(parts[0].trim());

                    String name =
                            parts[1].trim();

                    int age =
                            Integer.parseInt(parts[2].trim());

                    double gpa =
                            Double.parseDouble(parts[3].trim());

                    students.add(
                            new Student(id, name, age, gpa)
                    );

                } catch (NumberFormatException
                         | ArrayIndexOutOfBoundsException e) {

                    System.out.println(
                            "Skipping invalid line: " + line
                    );
                }
            }

        } catch (IOException e) {

            e.printStackTrace();
        }

        return students;
    }

    // =========================================================
    // ADD STUDENT
    // =========================================================

    public void addStudent(Student student) {

        try (BufferedWriter writer =
                     new BufferedWriter(
                             new FileWriter(filePath, true)
                     )) {

            writer.write(toLine(student));

            writer.newLine();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    // =========================================================
    // UPDATE STUDENT (by id)
    // =========================================================

    public void updateStudent(Student updatedStudent) {

        List<Student> students =
                loadStudents();

        for (int i = 0; i < students.size(); i++) {

            if (students.get(i).getId()
                    == updatedStudent.getId()) {

                students.set(i, updatedStudent);

                break;
            }
        }

        saveStudents(students);
    }

    // =========================================================
    // DELETE STUDENT (by id)
    // =========================================================

    public void deleteStudent(int id) {

        List<Student> students =
                loadStudents();

        students.removeIf(
                student -> student.getId() == id
        );

        saveStudents(students);
    }

    // =========================================================
    // SAVE ALL STUDENTS
    // =========================================================

    private void saveStudents(List<Student> students) {

        try (BufferedWriter writer =
                     new BufferedWriter(
                             new FileWriter(filePath)
                     )) {

            for (Student student : students) {

                writer.write(toLine(student));

                writer.newLine();
            }

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    private String toLine(Student student) {

        return student.getId()
                + ","
                + student.getName()
                + ","
                + student.getAge()
                + ","
                + student.getGpa();
    }
}
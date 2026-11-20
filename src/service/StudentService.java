package service;

import model.Student;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private final String filePath;
    private final String lastIdPath;

    public StudentService() {
        Path dataDirectory = resolveDataDirectory();
        filePath = dataDirectory.resolve("students.txt").toString();
        lastIdPath = dataDirectory.resolve("last_id.txt").toString();

        createFileIfMissing(filePath);
        createFileIfMissing(lastIdPath);
    }

    private static Path resolveDataDirectory() {
        Path current = Paths.get(System.getProperty("user.dir")).toAbsolutePath();

        while (current != null) {
            if (Files.isDirectory(current.resolve("src"))) {
                return current.resolve("Data");
            }
            current = current.getParent();
        }

        return Paths.get(System.getProperty("user.home"), "FCIStudentCare", "Data");
    }

    private void createFileIfMissing(String path) {
        try {
            File file = new File(path);
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Student> loadStudents() {
        List<Student> students = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                try {
                    String[] parts = line.split(",", -1);
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    int age = Integer.parseInt(parts[2].trim());
                    double gpa = Double.parseDouble(parts[3].trim());

                    students.add(new Student(id, name, age, gpa));
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Skipping invalid line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return students;
    }

    public int getNextId() {
        int max = 0;

        for (Student student : loadStudents()) {
            max = Math.max(max, student.getId());
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(lastIdPath))) {
            String line = reader.readLine();
            if (line != null && !line.trim().isEmpty()) {
                max = Math.max(max, Integer.parseInt(line.trim()));
            }
        } catch (IOException | NumberFormatException e) {
            createFileIfMissing(lastIdPath);
        }

        return max + 1;
    }

    public void saveLastId(int id) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(lastIdPath))) {
            writer.write(String.valueOf(id));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addStudent(Student student) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(toLine(student));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(Student updatedStudent) {
        List<Student> students = loadStudents();

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == updatedStudent.getId()) {
                students.set(i, updatedStudent);
                break;
            }
        }

        saveStudents(students);
    }

    public void deleteStudent(int id) {
        List<Student> students = loadStudents();
        students.removeIf(student -> student.getId() == id);
        saveStudents(students);
    }

    private void saveStudents(List<Student> students) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
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

package service;

import model.Student;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentService {

    // Old text file, used only once to import the existing students
    private final String oldTextFile =
            "Data/students.txt";

    public StudentService() {

        new File("Data").mkdirs();

        boolean firstRun =
                !new File("Data/students.db").exists();

        createTable();

        if (firstRun) {
            importFromTextFile();
        }
    }

    // =========================================================
    // CREATE TABLE (if it does not exist)
    // =========================================================

    private void createTable() {

        String sql =
                "CREATE TABLE IF NOT EXISTS students ("
                        + "id INTEGER PRIMARY KEY, "
                        + "name TEXT NOT NULL, "
                        + "age INTEGER NOT NULL, "
                        + "gpa REAL NOT NULL)";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             Statement statement =
                     connection.createStatement()) {

            statement.execute(sql);

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    // =========================================================
    // LOAD STUDENTS
    // =========================================================

    public List<Student> loadStudents() {

        List<Student> students =
                new ArrayList<>();

        String sql =
                "SELECT id, name, age, gpa "
                        + "FROM students ORDER BY id";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             Statement statement =
                     connection.createStatement();
             ResultSet resultSet =
                     statement.executeQuery(sql)) {

            while (resultSet.next()) {

                students.add(
                        new Student(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getInt("age"),
                                resultSet.getDouble("gpa")
                        )
                );
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return students;
    }

    // =========================================================
    // ADD STUDENT
    // =========================================================

    public void addStudent(Student student) {

        String sql =
                "INSERT INTO students (id, name, age, gpa) "
                        + "VALUES (?, ?, ?, ?)";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, student.getId());
            statement.setString(2, student.getName());
            statement.setInt(3, student.getAge());
            statement.setDouble(4, student.getGpa());

            statement.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    // =========================================================
    // UPDATE STUDENT (by id)
    // =========================================================

    public void updateStudent(Student student) {

        String sql =
                "UPDATE students "
                        + "SET name = ?, age = ?, gpa = ? "
                        + "WHERE id = ?";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.setDouble(3, student.getGpa());
            statement.setInt(4, student.getId());

            statement.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    // =========================================================
    // DELETE STUDENT (by id)
    // =========================================================

    public void deleteStudent(int id) {

        String sql =
                "DELETE FROM students WHERE id = ?";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    // =========================================================
    // ONE-TIME IMPORT FROM THE OLD TEXT FILE
    // =========================================================

    private void importFromTextFile() {

        File file =
                new File(oldTextFile);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader =
                     new BufferedReader(
                             new FileReader(file)
                     )) {

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                try {

                    String[] parts =
                            line.split(",");

                    addStudent(
                            new Student(
                                    Integer.parseInt(parts[0].trim()),
                                    parts[1].trim(),
                                    Integer.parseInt(parts[2].trim()),
                                    Double.parseDouble(parts[3].trim())
                            )
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
    }
}

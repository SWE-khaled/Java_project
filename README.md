# FCI Student Care — Student Management System (JavaFX)

A JavaFX desktop application for managing student records, structured into layered packages (`model`, `view`, `controller`, and `service`) with local file-based persistence.

## Features
- **Student CRUD & Search Workflows:**
  - Add new students (`AddStudentView`, `AddStudentController`, `StudentService.addNewStudent`) with automatic sequential ID generation (`getNextId`) tracked in `Data/last_id.txt`.
  - View and edit existing student records (`StudentView`, `EditStudentView`, `ViewStudentController`, `EditStudentController`, `StudentService.updateStudent`).
  - Delete student records (`DeleteStudentController`, `StudentService.deleteStudent`).
  - Search student records (`SearchController`).
- **Validation, Alerts & UI Coordination:**
  - Input validation (`StudentValidator`), user notifications (`AlertHelper`), input event handling (`InputEventsController`), screen navigation (`NavigationController`), shared state (`StudentContext`), and main coordination (`StudentController`).
- **Reactive GPA Status Calculation:**
  - Models student attributes (`id`, `name`, `age`, `gpa`) using JavaFX Properties.
  - Dynamically binds `status` to `gpa` changes using JavaFX `Bindings`:
    - **Excellent:** `GPA >= 3.5`
    - **Good:** `2.5 <= GPA < 3.5`
    - **Pass:** `2.0 <= GPA < 2.5`
    - **At Risk:** `GPA < 2.0`
- **File-Based Persistence & Resilience:**
  - Stores student entries as comma-separated records (`id,name,age,gpa`) in `Data/students.txt` and tracks the latest ID in `Data/last_id.txt`.
  - Automatically resolves `Data` relative to the project root (containing `src`), falling back to `~/FCIStudentCare/Data` if needed, and creates missing files/directories automatically.
  - Uses `synchronized` service methods and skips malformed lines safely during file parsing.

## Project Structure
- `src/model`: Domain models with JavaFX properties and bindings (`Student`).
- `src/service`: Business logic and file persistence (`StudentService`).
- `src/controller`: Feature controllers and UI helpers (`AddStudentController`, `EditStudentController`, `DeleteStudentController`, `ViewStudentController`, `SearchController`, `StudentController`, `StudentContext`, `StudentValidator`, `NavigationController`, `InputEventsController`, `AlertHelper`).
- `src/view`: JavaFX UI views (`AddStudentView`, `EditStudentView`, `StudentView`).
- `src/Main.java`: Application entry point.
- `Data`: Local storage for `students.txt` and `last_id.txt`.

## How to Run
1. Open the project in a Java/JavaFX-capable IDE (such as IntelliJ IDEA).
2. Ensure Java and JavaFX SDK/dependencies are configured for the project.
3. Run `src/Main.java`.
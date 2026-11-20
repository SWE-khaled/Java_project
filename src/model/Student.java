package model;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public final class Student {

    public static final double EXCELLENT_GPA = 3.5;
    public static final double AT_RISK_GPA = 2.0;

    private final IntegerProperty id;
    private final StringProperty name;
    private final IntegerProperty age;
    private final DoubleProperty gpa;
    private final ReadOnlyStringWrapper status;

    public Student(int id, String name, int age, double gpa) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.age = new SimpleIntegerProperty(age);
        this.gpa = new SimpleDoubleProperty(gpa);
        this.status = new ReadOnlyStringWrapper();
        this.status.bind(Bindings.createStringBinding(this::getStatus, this.gpa));
    }

    public int getId() {
        return id.get();
    }

    public void setId(int value) {
        id.set(value);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String value) {
        name.set(value);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public int getAge() {
        return age.get();
    }

    public void setAge(int value) {
        age.set(value);
    }

    public IntegerProperty ageProperty() {
        return age;
    }

    public double getGpa() {
        return gpa.get();
    }

    public void setGpa(double value) {
        gpa.set(value);
    }

    public DoubleProperty gpaProperty() {
        return gpa;
    }

    public boolean isExcellent() {
        return gpa.get() >= EXCELLENT_GPA;
    }

    public boolean isAtRisk() {
        return gpa.get() < AT_RISK_GPA;
    }

    public String getStatus() {
        if (isExcellent()) {
            return "● Excellent";
        } else if (gpa.get() >= 2.5) {
            return "● Good";
        } else if (!isAtRisk()) {
            return "● Pass";
        } else {
            return "● At Risk";
        }
    }

    public ReadOnlyStringProperty statusProperty() {
        return status.getReadOnlyProperty();
    }
}

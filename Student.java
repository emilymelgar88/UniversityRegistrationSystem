import java.util.ArrayList;
import java.util.List;

public class Student {
    private String id;
    private String name;
    private List<Course> enrolledCourses = new ArrayList<>();

    public Student(String id, String name) {
        this.id = id.trim();
        this.name = name.trim();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public List<Course> getEnrolledCourses() { return enrolledCourses; }

    public void enroll(Course c) {
        enrolledCourses.add(c);
    }

    @Override
    public String toString() {
        return id + ", " + name;
    }
}

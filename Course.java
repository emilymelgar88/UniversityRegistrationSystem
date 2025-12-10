import java.util.ArrayList;
import java.util.List;


public class Course {
private String id;
private String title;
private int maxCapacity;
private List<Student> enrolled = new ArrayList<>();
private List<Student> notEnrolled = new ArrayList<>();


public Course(String id, String title, int maxCapacity) {
this.id = id.trim();
this.title = title.trim();
this.maxCapacity = maxCapacity;
}


public String getId() { return id; }
public String getTitle() { return title; }
public int getMaxCapacity() { return maxCapacity; }


public List<Student> getEnrolled() { return enrolled; }
public List<Student> getNotEnrolled() { return notEnrolled; }


public boolean hasSpace() {
return enrolled.size() < maxCapacity;
}


public void addEnrolled(Student s) {
enrolled.add(s);
}


public void addNotEnrolled(Student s) {
notEnrolled.add(s);
}


@Override
public String toString() {
return id + ", " + title + ", " + maxCapacity;
}
}

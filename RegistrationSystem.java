import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class RegistrationSystem {
    public static void main(String[] args) {
        Path studentsFile = Paths.get("students.txt");
        Path coursesFile = Paths.get("courses.txt");
        Path regsFile = Paths.get("registrations.txt");

        try {
            Map<String, Student> students = DataLoader.loadStudents(studentsFile);
            Map<String, Course> courses = DataLoader.loadCourses(coursesFile);

            // Process registrations
            try (BufferedReader br = Files.newBufferedReader(regsFile, StandardCharsets.UTF_8)) {
                String line;
                while ((line = br.readLine()) != null) {
                    line = line.trim();
                    if (line.isEmpty()) continue;
                    String[] parts = line.split(",", 2);
                    if (parts.length < 2) continue;
                    String studentId = parts[0].trim();
                    String courseId = parts[1].trim();

                    Student s = students.get(studentId);
                    Course c = courses.get(courseId);
                    if (s == null || c == null) continue;

                    if (c.hasSpace()) {
                        c.addEnrolled(s);
                        s.enroll(c);
                    } else {
                        c.addNotEnrolled(s);
                    }
                }
            }

            // registrationByStudents.txt
            try (BufferedWriter bw = Files.newBufferedWriter(Paths.get("registrationByStudents.txt"), StandardCharsets.UTF_8)) {
                for (Student s : students.values()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(s.getId()).append(", ").append(s.getName()).append(":");
                    if (!s.getEnrolledCourses().isEmpty()) {
                        sb.append(" ");
                        for (int i = 0; i < s.getEnrolledCourses().size(); i++) {
                            if (i > 0) sb.append(", ");
                            sb.append(s.getEnrolledCourses().get(i).getId());
                        }
                    }
                    bw.write(sb.toString());
                    bw.newLine();
                }
            }

            // registrationByCourses.txt
            try (BufferedWriter bw = Files.newBufferedWriter(Paths.get("registrationByCourses.txt"), StandardCharsets.UTF_8)) {
                for (Course c : courses.values()) {
                    bw.write("Enrolled students for " + c.getId() + ":");
                    bw.newLine();
                    for (Student s : c.getEnrolled()) {
                        bw.write(s.toString());
                        bw.newLine();
                    }
                    bw.write("Not enrolled students for " + c.getId() + ":");
                    bw.newLine();
                    for (Student s : c.getNotEnrolled()) {
                        bw.write(s.toString());
                        bw.newLine();
                    }
                }
            }

            System.out.println("Registration processing completed. Output files generated.");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

public class DataLoader {

    public static Map<String, Student> loadStudents(Path path) throws IOException {
        Map<String, Student> map = new LinkedHashMap<>();
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split(",", 2);
                if (parts.length < 2) continue;
                String id = parts[0].trim();
                String name = parts[1].trim();
                map.put(id, new Student(id, name));
            }
        }
        return map;
    }

    public static Map<String, Course> loadCourses(Path path) throws IOException {
        Map<String, Course> map = new LinkedHashMap<>();
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split(",", 3);
                if (parts.length < 3) continue;
                String id = parts[0].trim();
                String title = parts[1].trim();
                int cap = Integer.parseInt(parts[2].trim());
                map.put(id, new Course(id, title, cap));
            }
        }
        return map;
    }
}

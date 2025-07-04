import java.sql.*;
import java.util.Scanner;

public class Task7 {
    static final String URL = "jdbc:mysql://localhost:3306/taskdb";
    static final String USER = "root";
    static final String PASS = "123";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            boolean running = true;

            while (running) {
                System.out.println("\n--- Task Manager ---");
                System.out.println("1. Add Task");
                System.out.println("2. View Tasks");
                System.out.println("3. Update Task");
                System.out.println("4. Delete Task");
                System.out.println("5. Exit");
                System.out.print("Choose an option (1–5): ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter description: ");
                        String desc = scanner.nextLine();
                        String insertSQL = "INSERT INTO tasks (title, description) VALUES (?, ?)";
                        try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                            pstmt.setString(1, title);
                            pstmt.setString(2, desc);
                            pstmt.executeUpdate();
                            System.out.println("Task added.");
                        }
                        break;

                    case 2:
                        String selectSQL = "SELECT * FROM tasks";
                        try (PreparedStatement pstmt = conn.prepareStatement(selectSQL);
                             ResultSet rs = pstmt.executeQuery()) {
                            System.out.println("\n--- All Tasks ---");
                            while (rs.next()) {
                                int id = rs.getInt("id");
                                String t = rs.getString("title");
                                String d = rs.getString("description");
                                System.out.println("ID: " + id + ", Title: " + t + ", Description: " + d);
                            }
                        }
                        break;

                    case 3:
                        System.out.print("Enter Task ID to update: ");
                        int updateId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter new title: ");
                        String newTitle = scanner.nextLine();
                        System.out.print("Enter new description: ");
                        String newDesc = scanner.nextLine();
                        String updateSQL = "UPDATE tasks SET title = ?, description = ? WHERE id = ?";
                        try (PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
                            pstmt.setString(1, newTitle);
                            pstmt.setString(2, newDesc);
                            pstmt.setInt(3, updateId);
                            int rows = pstmt.executeUpdate();
                            if (rows > 0) {
                                System.out.println("Task updated.");
                            } else {
                                System.out.println("Task not found.");
                            }
                        }
                        break;

                    case 4:
                        System.out.print("Enter Task ID to delete: ");
                        int deleteId = scanner.nextInt();
                        String deleteSQL = "DELETE FROM tasks WHERE id = ?";
                        try (PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {
                            pstmt.setInt(1, deleteId);
                            int rows = pstmt.executeUpdate();
                            if (rows > 0) {
                                System.out.println("Task deleted.");
                            } else {
                                System.out.println("Task not found.");
                            }
                        }
                        break;

                    case 5:
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }

        scanner.close();
    }
}

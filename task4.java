import java.io.*;
import java.util.*;

public class task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filename = "notes.txt"; // File to store notes

        while (true) {
            System.out.println("\n--- Notes App ---");
            System.out.println("1. Write a note");
            System.out.println("2. Read all notes");
            System.out.println("3. Exit");
            System.out.print("Choose an option (1–3): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter your note: ");
                    String note = scanner.nextLine();
                    writeNote(filename, note);
                    break;

                case 2:
                    readNotes(filename);
                    break;

                case 3:
                    System.out.println("Exiting Notes App.");
                    return;

                default:
                    System.out.println("Invalid choice. Please choose 1–3.");
            }
        }
    }

    // Method to write a note to file using FileWriter
    public static void writeNote(String filename, String note) {
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write(note + "\n");
            System.out.println("Note saved successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Method to read notes using BufferedReader
    public static void readNotes(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            System.out.println("\n--- All Notes ---");
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("- " + line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No notes found. Start writing some first.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}

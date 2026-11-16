package messageSystem;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Message> messages = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        
        try {
            messages.add(new BoardMessage("Jeff", "Important update regarding tomorrow's lecture", "B101", "Main Building"));
            messages.add(new BoardMessage("Admin", "Fire drill at 10:00 AM", new java.util.Date(), "B102", PriorityType.URGENT, "All Campus"));

            EmailMessage email1 = new EmailMessage("Alfred", "Please find attached the assignment.", "E201", "Project Submission");
            email1.addAttachment(new File("TR1", "pdf"));
            messages.add(email1);

            EmailMessage email2 = new EmailMessage("Dan", "Meeting rescheduled to Sunday.", "E202", "Team Sync");
            messages.add(email2);

            messages.add(new ChatMessage("Sam", "Hey, are you coming to the lab?", "C301", "WhatsApp"));
            messages.add(new ChatMessage("John", "Don't forget to push the code to GitHub!", "C302", "Telegram"));
        } catch (Exception e) {
            System.out.println("Initialization error: " + e.getMessage());
        }

        int choice = 0;
        while (choice != 7) {
            System.out.println("\n--- Messaging System Menu ---");
            System.out.println("1. Add a new message");
            System.out.println("2. Delete a message");
            System.out.println("3. Print all messages");
            System.out.println("4. Count messages containing specific words");
            System.out.println("5. Print digital messages only");
            System.out.println("6. Print previews of all messages (Bonus Option)");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        addMessageMenu(scanner, messages);
                        break;
                    case 2:
                        System.out.print("Enter Message ID to delete: ");
                        String idToRemove = scanner.nextLine();
                        boolean removed = messages.removeIf(m -> m.getMessageId().equalsIgnoreCase(idToRemove));
                        System.out.println(removed ? "Message deleted successfully." : "Message ID not found.");
                        break;
                    case 3:
                        System.out.println("\n--- All Messages ---");
                        for (Message m : messages) {
                            System.out.println(m);
                        }
                        break;
                    case 4:
                        System.out.print("Enter words separated by space to search: ");
                        String line = scanner.nextLine();
                        ArrayList<String> words = new ArrayList<>(Arrays.asList(line.split("\\s+")));
                        int count = 0;
                        for (Message m : messages) {
                            if (m.find(words)) {
                                count++;
                            }
                        }
                        System.out.println("Number of messages containing at least one word: " + count);
                        break;
                    case 5:
                        System.out.println("\n--- Digital Messages Only ---");
                        for (Message m : messages) {
                            if (m instanceof IDigital) {
                                System.out.println(m + " -> [" + ((IDigital) m).printCommunicationMethod() + "]");
                            }
                        }
                        break;
                    case 6:
                        System.out.println("\n--- Message Previews ---");
                        for (Message m : messages) {
                            System.out.println(m.generatePreview());
                        }
                        break;
                    case 7:
                        System.out.println("Exiting system. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Please choose 1-7.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private static void addMessageMenu(Scanner scanner, ArrayList<Message> messages) throws Exception {
        System.out.println("Select type: 1. BoardMessage | 2. EmailMessage | 3. ChatMessage");
        int type = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Message ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Sender: ");
        String sender = scanner.nextLine();
        System.out.print("Enter Content: ");
        String content = scanner.nextLine();

        if (type == 1) {
            System.out.print("Enter Location: ");
            String location = scanner.nextLine();
            System.out.print("Enter Priority (REGULAR, URGENT): ");
            PriorityType priority = PriorityType.valueOf(scanner.nextLine().toUpperCase());
            messages.add(new BoardMessage(sender, content, new java.util.Date(), id, priority, location));
        } else if (type == 2) {
            System.out.print("Enter Subject: ");
            String subject = scanner.nextLine();
            EmailMessage email = new EmailMessage(sender, content, id, subject);
            System.out.print("Add attachment? (y/n): ");
            if (scanner.nextLine().equalsIgnoreCase("y")) {
                System.out.print("File name: ");
                String fName = scanner.nextLine();
                System.out.print("File type: ");
                String fType = scanner.nextLine();
                email.addAttachment(new File(fName, fType));
            }
            messages.add(email);
        } else if (type == 3) {
            System.out.print("Enter Platform (e.g., WhatsApp): ");
            String platform = scanner.nextLine();
            messages.add(new ChatMessage(sender, content, id, platform));
        } else {
            System.out.println("Unknown message type.");
            return;
        }
        System.out.println("Message added successfully!");
    }
}

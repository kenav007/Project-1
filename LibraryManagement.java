import java.util.Scanner;

abstract class LibraryItem {
    private int id;
    private String title;
    private boolean available;

    LibraryItem(int id, String title) {
        this.id = id;
        this.title = title;
        available = true;
    }

    int getId() {
        return id;
    }

    String getTitle() {
        return title;
    }

    boolean isAvailable() {
        return available;
    }

    void issue() {
        available = false;
    }

    void returnItem() {
        available = true;
    }

    abstract void display();
}

class Book extends LibraryItem {
    private String author;

    Book(int id, String title, String author) {
        super(id, title);
        this.author = author;
    }

    void display() {
        System.out.println(getId() + " | " + getTitle() + " | "
                + author + " | "
                + (isAvailable() ? "Available" : "Issued"));
    }
}

class Magazine extends LibraryItem {
    private int issueNumber;

    Magazine(int id, String title, int issueNumber) {
        super(id, title);
        this.issueNumber = issueNumber;
    }

    void display() {
        System.out.println(getId() + " | " + getTitle() + " | Issue "
                + issueNumber + " | "
                + (isAvailable() ? "Available" : "Issued"));
    }
}

public class LibraryManagement{

    static Scanner sc = new Scanner(System.in);
    static LibraryItem[] items = new LibraryItem[100];
    static int count = 0;

    static void addBook() {
        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Book Title: ");
        String title = sc.nextLine();

        System.out.print("Enter Author: ");
        String author = sc.nextLine();

        items[count++] = new Book(id, title, author);
        System.out.println("Book added successfully!");
    }

    static void addMagazine() {
        System.out.print("Enter Magazine ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Magazine Title: ");
        String title = sc.nextLine();

        System.out.print("Enter Issue Number: ");
        int issue = sc.nextInt();

        items[count++] = new Magazine(id, title, issue);
        System.out.println("Magazine added successfully!");
    }

    static void displayItems() {
        if (count == 0) {
            System.out.println("No items available.");
            return;
        }

        System.out.println("\nID | Title | Details | Status");

        for (int i = 0; i < count; i++)
            items[i].display();
    }

    static void searchItem() {
        System.out.print("Enter Item ID to search: ");
        int id = sc.nextInt();

        for (int i = 0; i < count; i++) {
            if (items[i].getId() == id) {
                items[i].display();
                return;
            }
        }

        System.out.println("Item not found.");
    }

    static void issueItem() {
        System.out.print("Enter Item ID: ");
        int id = sc.nextInt();

        for (int i = 0; i < count; i++) {
            if (items[i].getId() == id) {
                if (items[i].isAvailable()) {
                    items[i].issue();
                    System.out.println("Item issued successfully.");
                } else {
                    System.out.println("Item is already issued.");
                }
                return;
            }
        }

        System.out.println("Item not found.");
    }

    static void returnItem() {
        System.out.print("Enter Item ID: ");
        int id = sc.nextInt();

        for (int i = 0; i < count; i++) {
            if (items[i].getId() == id) {
                if (!items[i].isAvailable()) {
                    items[i].returnItem();
                    System.out.println("Item returned successfully.");
                } else {
                    System.out.println("Item was not issued.");
                }
                return;
            }
        }

        System.out.println("Item not found.");
    }

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Book");
            System.out.println("2. Add Magazine");
            System.out.println("3. Display Items");
            System.out.println("4. Search Item");
            System.out.println("5. Issue Item");
            System.out.println("6. Return Item");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1: addBook(); break;
                case 2: addMagazine(); break;
                case 3: displayItems(); break;
                case 4: searchItem(); break;
                case 5: issueItem(); break;
                case 6: returnItem(); break;
                case 7: System.out.println("Thank you!"); break;
                default: System.out.println("Invalid choice.");
            }

        } while (choice != 7);

        sc.close();
    }
}

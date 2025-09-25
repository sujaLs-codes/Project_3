import java.util.ArrayList;
import java.util.Scanner;

// Book Class
class Book {
    int id;
    String title;
    String author;
    boolean isIssued;

    Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false; // default available
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Title: " + title + ", Author: " + author +
                ", Status: " + (isIssued ? "Issued" : "Available");
    }
}

// User Class
class User {
    int id;
    String name;
    ArrayList<Book> issuedBooks;

    User(int id, String name) {
        this.id = id;
        this.name = name;
        this.issuedBooks = new ArrayList<>();
    }

    void issueBook(Book b) {
        issuedBooks.add(b);
    }

    void returnBook(Book b) {
        issuedBooks.remove(b);
    }

    void viewIssuedBooks() {
        if (issuedBooks.isEmpty()) {
            System.out.println(name + " has no issued books.");
        } else {
            System.out.println(name + "'s issued books:");
            for (Book b : issuedBooks) {
                System.out.println(b);
            }
        }
    }
}

// Library Class
class Library {
    ArrayList<Book> books = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();

    void addBook(Book b) {
        books.add(b);
        System.out.println("✅ Book added successfully!");
    }

    void addUser(User u) {
        users.add(u);
        System.out.println("✅ User added successfully!");
    }

    void issueBook(int bookId, int userId) {
        Book book = findBook(bookId);
        User user = findUser(userId);

        if (book == null) {
            System.out.println("❌ Book not found!");
            return;
        }
        if (user == null) {
            System.out.println("❌ User not found!");
            return;
        }
        if (book.isIssued) {
            System.out.println("⚠️ Book already issued!");
            return;
        }

        book.isIssued = true;
        user.issueBook(book);
        System.out.println("✅ Book issued to " + user.name);
    }

    void returnBook(int bookId, int userId) {
        Book book = findBook(bookId);
        User user = findUser(userId);

        if (book == null || user == null) {
            System.out.println("❌ Book or User not found!");
            return;
        }
        if (!book.isIssued) {
            System.out.println("⚠️ This book is not issued.");
            return;
        }

        book.isIssued = false;
        user.returnBook(book);
        System.out.println("✅ Book returned by " + user.name);
    }

    void viewAllBooks() {
        if (books.isEmpty()) {
            System.out.println("⚠️ No books available in library!");
        } else {
            System.out.println("\n--- Library Books ---");
            for (Book b : books) {
                System.out.println(b);
            }
        }
    }

    private Book findBook(int id) {
        for (Book b : books) {
            if (b.id == id) return b;
        }
        return null;
    }

    private User findUser(int id) {
        for (User u : users) {
            if (u.id == id) return u;
        }
        return null;
    }
}

// Main Class
public class Library_Management_System {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();
        boolean exit = false;

        while (!exit) {
            System.out.println("\n===== Library Management System =====");
            System.out.println("1. Add Book");
            System.out.println("2. Add User");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. View All Books");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    int bookId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();
                    library.addBook(new Book(bookId, title, author));
                    break;

                case 2:
                    System.out.print("Enter User ID: ");
                    int userId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter User Name: ");
                    String name = sc.nextLine();
                    library.addUser(new User(userId, name));
                    break;

                case 3:
                    System.out.print("Enter Book ID to issue: ");
                    int issueBookId = sc.nextInt();
                    System.out.print("Enter User ID: ");
                    int issueUserId = sc.nextInt();
                    library.issueBook(issueBookId, issueUserId);
                    break;

                case 4:
                    System.out.print("Enter Book ID to return: ");
                    int returnBookId = sc.nextInt();
                    System.out.print("Enter User ID: ");
                    int returnUserId = sc.nextInt();
                    library.returnBook(returnBookId, returnUserId);
                    break;

                case 5:
                    library.viewAllBooks();
                    break;

                case 6:
                    exit = true;
                    System.out.println("👋 Exiting Library System...");
                    break;

                default:
                    System.out.println("❌ Invalid choice! Try again.");
            }
        }
        sc.close();
    }
}

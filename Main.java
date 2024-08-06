import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private boolean isCheckedOut;


    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isCheckedOut = false;
    }


    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isCheckedOut() {
        return isCheckedOut;
    }

    public void checkOut() {
        this.isCheckedOut = true;
    }

    public void returnBook() {
        this.isCheckedOut = false;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", Status: " + (isCheckedOut ? "Checked Out" : "Available");
    }
}

class LibraryCatalog {
    private ArrayList<Book> books;

    // Constructor
    public LibraryCatalog() {
        this.books = new ArrayList<>();
    }

    // Method to add a book
    public void addBook(Book book) {
        books.add(book);
    }

    // Method to search books by title
    public ArrayList<Book> searchByTitle(String title) {
        ArrayList<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                result.add(book);
            }
        }
        return result;
    }

    // Method to search books by author
    public ArrayList<Book> searchByAuthor(String author) {
        ArrayList<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                result.add(book);
            }
        }
        return result;
    }

    // Method to check out a book
    public boolean checkOutBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && !book.isCheckedOut()) {
                book.checkOut();
                return true;
            }
        }
        return false;
    }

    // Method to return a book
    public boolean returnBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && book.isCheckedOut()) {
                book.returnBook();
                return true;
            }
        }
        return false;
    }

    // Method to display all books in the catalog
    public void displayCatalog() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public static void main(String[] args) {
        LibraryCatalog catalog = new LibraryCatalog();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nLibrary Catalog Menu:");
            System.out.println("1. Add a book");
            System.out.println("2. Search books by title");
            System.out.println("3. Search books by author");
            System.out.println("4. Check out a book");
            System.out.println("5. Return a book");
            System.out.println("6. Display all books");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();
                    catalog.addBook(new Book(title, author));
                    System.out.println("Book added successfully.");
                    break;
                case 2:
                    System.out.print("Enter title to search: ");
                    String searchTitle = scanner.nextLine();
                    ArrayList<Book> searchResultsByTitle = catalog.searchByTitle(searchTitle);
                    if (searchResultsByTitle.isEmpty()) {
                        System.out.println("No books found with that title.");
                    } else {
                        for (Book book : searchResultsByTitle) {
                            System.out.println(book);
                        }
                    }
                    break;
                case 3:
                    System.out.print("Enter author to search: ");
                    String searchAuthor = scanner.nextLine();
                    ArrayList<Book> searchResultsByAuthor = catalog.searchByAuthor(searchAuthor);
                    if (searchResultsByAuthor.isEmpty()) {
                        System.out.println("No books found by that author.");
                    } else {
                        for (Book book : searchResultsByAuthor) {
                            System.out.println(book);
                        }
                    }
                    break;
                case 4:
                    System.out.print("Enter title to check out: ");
                    String checkOutTitle = scanner.nextLine();
                    if (catalog.checkOutBook(checkOutTitle)) {
                        System.out.println("Book checked out successfully.");
                    } else {
                        System.out.println("Book is not available or does not exist.");
                    }
                    break;
                case 5:
                    System.out.print("Enter title to return: ");
                    String returnTitle = scanner.nextLine();
                    if (catalog.returnBook(returnTitle)) {
                        System.out.println("Book returned successfully.");
                    } else {
                        System.out.println("Book was not checked out or does not exist.");
                    }
                    break;
                case 6:
                    System.out.println("Library Catalog:");
                    catalog.displayCatalog();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

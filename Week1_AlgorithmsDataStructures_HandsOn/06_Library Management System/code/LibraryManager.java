import java.util.Arrays;
import java.util.Comparator;

public class LibraryManager {
    private Book[] books;
    private int count;

    public LibraryManager(int capacity) {
        books = new Book[capacity];
        count = 0;
    }

    public void addBook(Book b) {
        if (count >= books.length) {
            System.out.println("Library is full");
            return;
        }
        books[count++] = b;
    }

    public void displayAll() {
        System.out.println("Library Books:");
        for (int i = 0; i < count; i++) {
            books[i].display();
        }
    }

    public void searchByAuthor(String author) {
        boolean found = false;
        System.out.println("Linear Search by Author: " + author);
        for (int i = 0; i < count; i++) {
            if (books[i].getAuthor().contains(author.toLowerCase())) {
                books[i].display();
                found = true;
            }
        }
        if (!found) System.out.println("No book found with author containing: " + author);
    }

    public void searchByTitleBinary(String title) {
        title = title.toLowerCase();
        Arrays.sort(books, 0, count, Comparator.comparing(Book::getTitle));

        int low = 0, high = count - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = books[mid].getTitle().compareTo(title);
            if (cmp == 0) {
                System.out.println("Binary Search Result:");
                books[mid].display();
                return;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        System.out.println("Book with title '" + title + "' not found.");
    }
}

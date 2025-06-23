public class LibraryApp {
    public static void main(String[] args) {
        LibraryManager lib = new LibraryManager(10);

        lib.addBook(new Book(1, "The Power of Now", "Eckhart Tolle"));
        lib.addBook(new Book(2, "Sapiens", "Yuval Noah Harari"));
        lib.addBook(new Book(3, "The Lean Startup", "Eric Ries"));
        lib.addBook(new Book(4, "Atomic Habits", "James Clear"));
        lib.addBook(new Book(5, "Becoming", "Michelle Obama"));

        lib.displayAll();

        lib.searchByAuthor("clear");
        lib.searchByTitleBinary("sapiens");
        lib.searchByTitleBinary("unknown title");
    }
}

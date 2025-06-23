public class Book {
    private int id;
    private String title;
    private String author;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title.toLowerCase();
        this.author = author.toLowerCase();
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public void display() {
        System.out.println(id + " | " + title + " | " + author);
    }
}

import java.io.Serializable;
import java.util.ArrayList;

public class PackageData implements Serializable {
    private String operationType;
    private ArrayList<Book> books;
    private Book book;


    public PackageData() {
    }

    public PackageData(String oparationType, ArrayList<Book> books) {
        this.operationType = oparationType;
        this.books = books;
    }

    public PackageData(String oparationType, Book book) {
        this.operationType = oparationType;
        this.book=book;
    }

    public String getOparationType() {
        return operationType;
    }

    public void setOparationType(String oparationType) {
        this.operationType = oparationType;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public Book getBook(){
        return book;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }
}
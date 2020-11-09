import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Server {
    public static Connection connection;
    public static void connectToserver(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_db?useUnicode=true&serverTimezone=UTC", "root", "");
            System.out.println("Connected");


        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        connectToserver();
        try{
            ServerSocket serverSocket = new ServerSocket(1989);
            System.out.println("Waiting for a client");

            while (true) {

                Socket socket = serverSocket.accept();
                System.out.println("Client connected");
                ServerThread serverThread = new ServerThread(socket);
                serverThread.start();

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void addBookTODB(Book book){
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO books(id, name, author) values(NULL,?,?)");
            preparedStatement.setString(1,book.getName());
            preparedStatement.setString(2,book.getAuthor());
            preparedStatement.executeUpdate();
            preparedStatement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<Book>listBook(){
        ArrayList<Book>books = new ArrayList<>();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM books");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String author = resultSet.getString("author");
                Book book = new Book(id,name,author);
                books.add(book);

            }

            preparedStatement.close();


        }catch (Exception e){
            e.printStackTrace();
        }
        return books;

    }

}

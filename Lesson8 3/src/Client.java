
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        try{
            Socket socket = new Socket("127.0.0.1", 1989);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            while (true){
                System.out.println("1. Add book");
                System.out.println("2. List books");
                System.out.println("3. Disconnect from server");

                String choise = scan.next();

                if(choise.equals("1")){


                    System.out.println("Insert name: ");
                    String name = scan.next();
                    System.out.println("Insert author: ");
                    String author = scan.next();

                    Book book = new Book(0l, name, author);
                    PackageData pd = new PackageData("add_book", book);
                    outputStream.writeObject(pd);
                }else if(choise.equals("2")){
                    PackageData pd = new PackageData();
                    pd.setOparationType("list_book");
                    outputStream.writeObject(pd);

                    PackageData response = (PackageData) inputStream.readObject();
                    if(response != null){
                        System.out.println(response.getBooks());
                    }
                }else if(choise.equals("3")){
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
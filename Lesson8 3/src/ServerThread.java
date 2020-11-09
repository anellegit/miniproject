import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread {
    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

            PackageData pd=null;

            while ((pd = (PackageData) inputStream.readObject()) != null) {
                if(pd.getOparationType().equals("add_book")){
                    Book book = pd.getBook();
                    Server.addBookTODB(book);
                }else if(pd.getOparationType().equals("list_book")){
                    PackageData response = new PackageData();
                    response.setBooks(Server.listBook());
                    outputStream.writeObject(response);
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
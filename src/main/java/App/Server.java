package App;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    //Server class is responsible for listening to clients who wish to connect
    // And when they do, it will initiate a new thread to handle them
    //ServerSocket listens to new clients and socket object to communicate them
    private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }


    //start method is responsible for keeping our server running
    public void startServer(){
        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                System.out.println("A new client has connected");
                ClientHandler clientHandler = new ClientHandler(socket);

                Thread thread = new Thread(clientHandler);
                thread.start();
            }    }
            catch (IOException e) {
                    e.printStackTrace();
                }
            }


    public void closedServerSocket(){
        try
        {
            if(serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        //our server will be listening to clients that will be making a connection to this port number
        ServerSocket serverSocket1 = new ServerSocket(6739);
        Server server = new Server(serverSocket1);
        server.startServer();
    }

}

package App;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{
    //This class implements runnable interface. This will make it so that the instance will be executed
    // on seperate thread

    // static Arraylist of every instance of this class
    //Arraylist keeps track of all our clients, when clients send message, we can loop through and
    // send a message to each client
    public static ArrayList<ClientHandler> clientHandlerArrayList = new ArrayList<>();

    //Socket:  establish a connection between the client and server
    private Socket socket;

    //BuffereReader: to read messages sent from the client (input stream)
    private BufferedReader bufferedReader;

    //BufferedWriter: to send messages to our clients (output stream)
    private BufferedWriter bufferedWriter;

    //ClientUserName: used to represent each client
    private String clientUserName;

    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.clientUserName = bufferedReader.readLine();
            clientHandlerArrayList.add(this);
            broadcastMessage("SERVER: " + clientUserName + "has entered the chat");
        } catch (IOException e){
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    @Override
    public void run() {
        String messageFromClient;

        while (socket.isConnected()){
            try{
                messageFromClient = bufferedReader.readLine();
                broadcastMessage(messageFromClient);
            } catch (IOException e){
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }

    }

    public void broadcastMessage(String messageToSend){
        for (ClientHandler clientHandler : clientHandlerArrayList){
            try{
                if (!clientHandler.clientUserName.equals(clientHandler)){
                    clientHandler.bufferedWriter.write(messageToSend);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
                }
            } catch (IOException e){
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }

    public void removeClientHandler(){
        clientHandlerArrayList.remove(this);
        broadcastMessage("SERVER" + clientUserName + "has left the chat!");
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter){
        removeClientHandler();
        try {
            if (bufferedReader != null){
                bufferedReader.close();
            }
            if (bufferedWriter != null){
                bufferedWriter.close();
            }
            if (socket != null){
                socket.close();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}

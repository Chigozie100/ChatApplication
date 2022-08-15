package App;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.Socket;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

class ClientHandlerTest {
    static ClientHandler clientHandlerMock;
    static Socket socket;
    static BufferedReader bufferedReader;
    static BufferedWriter bufferedWriter;

    @BeforeEach
    void setUp() throws IOException {
        clientHandlerMock = mock(ClientHandler.class);
        socket = new Socket("localhost", 1234);
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

    }

    @Test
    void broadcastMessage() {
        clientHandlerMock.broadcastMessage("guy");
        doNothing().when(clientHandlerMock).broadcastMessage(isA(String.class));
        verify(clientHandlerMock, times(1)).broadcastMessage("guy");
    }

    @Test
    void removeClientHandler() {
        clientHandlerMock.removeClientHandler();
        doNothing().when(clientHandlerMock).removeClientHandler();
        verify(clientHandlerMock, times(1)).removeClientHandler();
    }

    @Test
    void closeEverything() {
        clientHandlerMock.closeEverything(socket, bufferedReader, bufferedWriter);
        doNothing().when(clientHandlerMock).closeEverything(isA(Socket.class), isA(BufferedReader.class), isA(BufferedWriter.class));
        verify(clientHandlerMock, times(1)).closeEverything(socket, bufferedReader, bufferedWriter);
    }
}
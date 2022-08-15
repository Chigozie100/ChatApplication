package App;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

class ServerTest {
    static Server serverMock;

    @BeforeEach
    void setUp() {
        serverMock = mock(Server.class);
    }

    @Test
    void startServer() {
        serverMock.startServer();
        doNothing().when(serverMock).startServer();
        verify(serverMock, times(1)).startServer();
    }

    @Test
    void closedServerSocket() {
        serverMock.closedServerSocket();
        doNothing().when(serverMock).startServer();
        verify(serverMock, times(1)).closedServerSocket();
    }
}
package App;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientTest {
static Client clientMock;
    @BeforeEach
    void setUp() {
        clientMock = mock(Client.class);
    }

    @Test
    void sendMessage() {
        clientMock.sendMessage();
        doNothing().when(clientMock).sendMessage();
        verify(clientMock, times(1)).sendMessage();
    }
}
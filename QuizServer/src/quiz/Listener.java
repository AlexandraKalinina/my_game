package quiz;

public interface Listener {
    void connect(ClientConnection connection);
    void disconnect(ClientConnection connection);
    void onReceive(ClientConnection connection, String answer);
}

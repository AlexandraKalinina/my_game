package sample.helpers;

import java.io.*;
import java.net.Socket;

public class ClientConnection {
    private Socket socket;
    private Thread thread;
    private BufferedReader in;
    private BufferedWriter out;
    private Listener eventListener;
    private String name;

    public ClientConnection(Listener eventListener, Socket socket) throws IOException {
        name = "Игрок номер " + System.nanoTime();
        System.out.println(name + " " + ClientConnection.this.name);
        this.eventListener = eventListener;
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    eventListener.connect(ClientConnection.this);
                    while (!thread.isInterrupted()) {
                        eventListener.onReceive(ClientConnection.this, in.readLine());
                    }
                } catch (IOException e) {
                    System.out.println("Exception" + e);
                } finally {
                    eventListener.disconnect(ClientConnection.this);
                }
            }
        });
        thread.start();
    }
    public synchronized void sendAnswer(String answer) {
        try {
            out.write(answer + "\r\n");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            disconnect();
        }
    }

    public String getName() {
        return name;
    }

    public synchronized void disconnect() {
        thread.interrupt();
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


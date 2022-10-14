package org.example.server;

import com.google.gson.Gson;
import org.example.shared.Notizia;
import org.example.shared.ServerResponse;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class CatalogoServer {
    public static void main(String[] args) {
        CatalogoServerSocket serverSocket = new CatalogoServerSocket();
        serverSocket.start();
    }

    private static class CatalogoServerSocket {
        private ServerSocket serverSocket;
        private int port = 8080;
        private Socket clientSocket;
        public void start() {
            try {
                serverSocket = new ServerSocket(port);
                while (true) {
                    System.out.println("Server in ascolto sulla porta " + port);
                    System.out.println("In attesa di una connessione...");
                    clientSocket = serverSocket.accept();
                    System.out.println("Connessione accettata");
                    System.out.println("In attesa di un messaggio...");
                    ClientHandler clientHandler = new ClientHandler(clientSocket);
                    clientHandler.start();

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static class ClientHandler extends Thread {
        private Socket clientSocket;
        private DataOutputStream out;
        private DataInputStream in;
        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }
        @Override
        public void run() {
            try {
                out = new DataOutputStream(clientSocket.getOutputStream());
                in = new DataInputStream(clientSocket.getInputStream());
                while (true) {
                    Gson gson = new Gson();
                    String message = in.readUTF();
                    System.out.println("Messaggio ricevuto: " + message);
                    if(message.equalsIgnoreCase("x")){
                        System.out.println("Chiusura del client");
                        clientSocket.close();
                        break;
                    }
                    Notizia notiziaClient = gson.fromJson(message, Notizia.class);
                    System.out.println("Notizia ricevuta dal client: " + notiziaClient);

                    if(NotiziaRepository.getInstance().addNotizia(notiziaClient)){
                        System.out.println("Notizia aggiunta al catalogo");
                    }
                    else {
                        System.out.println("Notizia non aggiunta al catalogo");
                    }
                    List<Notizia> notizie = NotiziaRepository.getInstance().getNotizie(notiziaClient.getTipologia());
                    System.out.println("Notizie trovate: " + Arrays.toString(notizie.toArray()));
                    ServerResponse serverResponse = new ServerResponse(notizie);
                    System.out.println("Invio risposta al client");
                    out.writeUTF(gson.toJson(serverResponse));
                    out.flush();
                    System.out.println("Risposta inviata al client");
                }
            } catch (IOException e) {
                System.err.println("Error: " +e.getMessage());
                System.err.println(Arrays.toString(e.getStackTrace()));
                System.err.println("Chiudo la connessione");
            }
        }
    }
}

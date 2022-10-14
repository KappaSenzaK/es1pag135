package org.example.client;

import com.google.gson.Gson;
import org.example.catalogo.Catalogo;
import org.example.shared.Notizia;
import org.example.shared.ServerResponse;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CatalogoClient {

    public static void main(String[] args) {
        CatalogoSocketClient client = new CatalogoSocketClient();
        client.start();
    }


    private static class CatalogoSocketClient {
        private String host = "localhost";
        private int port = 8080;
        private Socket clientSocket;
        private DataOutputStream out;
        private DataInputStream in;
        public void start() {
            try {
                clientSocket = new Socket(host, port);
                out = new DataOutputStream(clientSocket.getOutputStream());
                in = new DataInputStream(clientSocket.getInputStream());

                while(true){
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Inserisci una tipologia di catalogo (Settore, Argomento, Area Geografica), x per chiudere: ");
                    String catalogoUser = scanner.nextLine();
                    catalogoUser = catalogoUser.toLowerCase();
                    if(catalogoUser.equalsIgnoreCase("x")){
                        System.out.println("Chiusura del client");
                        out.writeUTF("x");
                        out.flush();
                        clientSocket.close();
                        break;
                    }

                    List<String> tipologie = List.of(
                            Arrays.stream(Catalogo.values())
                                    .map(Catalogo::getNome)
                                    .toArray(String[]::new)
                    );

                    if(!tipologie.contains(catalogoUser)){
                        System.out.println("Tipologia non valida");
                        continue;
                    }
                    System.out.println("Inserisci il titolo della notizia: ");
                    String titoloUser = scanner.nextLine();

                    System.out.println("Inserisci il testo della notizia: ");
                    String testo = scanner.nextLine();

                    Catalogo catalogo = switch (catalogoUser) {
                        case "settore" -> Catalogo.SETTORE;
                        case "argomento" -> Catalogo.ARGOMENTO;
                        case "area geografica" -> Catalogo.AREA_GEOGRAFICA;
                        default -> null;
                    };

                    Notizia notizia = new Notizia(titoloUser, testo, catalogo);
                    System.out.println("Notizia: " + notizia);
                    Gson gson = new Gson();
                    System.out.println("Invio notizia al server");
                    out.writeUTF(gson.toJson(notizia));
                    out.flush();
                    System.out.println("Notizia inviata al server");
                    System.out.println("Ricezione risposta dal server");
                    String output = in.readUTF();
                    ServerResponse serverResponse = gson.fromJson(output, ServerResponse.class);
                    System.out.println("Numero di notizie trovate: " + serverResponse.getNumeroNotizie());
                    System.out.println("Risposta del server: ");
                    serverResponse.getNotizie()
                            .forEach(System.out::println);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

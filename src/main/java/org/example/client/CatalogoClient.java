package org.example.client;

import com.google.gson.Gson;
import org.example.shared.Notizia;
import org.example.shared.ServerResponse;
import org.example.shared.Tipologia;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
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
                    System.out.println("Inserisci una tipologiaUtente (Settore, Argomento, Area Geografica), x per chiudere: ");
                    String tipologiaUtente = scanner.nextLine();
                    tipologiaUtente = tipologiaUtente.toLowerCase();
                    if(tipologiaUtente.equalsIgnoreCase("x")){
                        System.out.println("Chiusura del client");
                        break;
                    }
                    List<String> tipologie = List.of("settore", "argomento", "area geografica");
                    if(tipologie.contains(tipologiaUtente)){
                        System.out.println("Inserisci il titolo della notizia: ");
                        String titoloUser = scanner.nextLine();

                        System.out.println("Inserisci il testo della notizia: ");
                        String testo = scanner.nextLine();

                        Tipologia tipologia = switch (tipologiaUtente) {
                            case "settore" -> Tipologia.SETTORE;
                            case "argomento" -> Tipologia.ARGOMENTO;
                            case "area geografica" -> Tipologia.AREA_GEOGRAFICA;
                            default -> null;
                        };

                        Notizia notizia = new Notizia(titoloUser, testo, tipologia);
                        System.out.println("Notizia: " + notizia);
                        Gson gson = new Gson();
                        System.out.println("Invio notizia al server");
                        out.writeUTF(gson.toJson(notizia));
                        out.flush();
                        System.out.println("Notizia inviata al server");
                        System.out.println("Ricezione risposta dal server");
                        String output = in.readUTF();
                        System.out.println("Risposta ricevuta dal server: " + output);
                        ServerResponse serverResponse = gson.fromJson(output, ServerResponse.class);
                        System.out.println("Risposta del server: " + serverResponse);
                        List<Notizia> notizie = serverResponse.getNotizie();
                        for (Notizia notiziaServer : notizie) {
                            System.out.println("------------------");
                            System.out.println(notiziaServer);
                            System.out.println("------------------------------------------------");
                        }

                    } else {
                        System.out.println("Tipologia non valida");
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

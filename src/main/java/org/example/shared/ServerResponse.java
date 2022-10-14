package org.example.shared;

import java.util.List;
import java.util.Optional;

public class ServerResponse {
    private List<Notizia> notizie;

    public ServerResponse(List<Notizia> notizie) {
        this.notizie = Optional.ofNullable(notizie).orElse(List.of());
    }

    public List<Notizia> getNotizie() {
        return notizie;
    }

    public void setNotizie(List<Notizia> notizie) {
        this.notizie = notizie;
    }

    @Override
    public String toString() {
        return "ServerResponse{" +
                "notizie=" + notizie +
                '}';
    }

    public int getNumeroNotizie() {
        int numeroNotizie = 0;
        for(Notizia ignored : notizie){
            numeroNotizie++;
        }
        return numeroNotizie;
    }
}

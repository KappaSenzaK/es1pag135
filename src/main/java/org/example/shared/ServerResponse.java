package org.example.shared;

import java.util.List;

public class ServerResponse {
    private List<Notizia> notizie;

    public ServerResponse(List<Notizia> notizie) {
        this.notizie = notizie;
    }

    public List<Notizia> getNotizie() {
        return notizie;
    }

    public void setNotizie(List<Notizia> notizie) {
        this.notizie = notizie;
    }
}

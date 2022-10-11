package org.example.server;

import org.example.shared.Notizia;
import org.example.shared.Tipologia;

import java.util.HashMap;
import java.util.List;

public class NotiziaRepository {
    private static NotiziaRepository instance;
    public static NotiziaRepository getInstance() {
        if (instance == null) {
            instance = new NotiziaRepository();
        }
        return instance;
    }
    private NotiziaRepository(){
        notizieSmistatePerTipologia = new HashMap<>();
    }


    private final HashMap<Tipologia, List<Notizia>> notizieSmistatePerTipologia;

    public HashMap<Tipologia, List<Notizia>> getNotizieSmistatePerTipologia() {
        return notizieSmistatePerTipologia;
    }

    public boolean addNotizia(Notizia notizia) {
        if (notizieSmistatePerTipologia.containsKey(notizia.getTipologia())) {
            notizieSmistatePerTipologia.get(notizia.getTipologia()).add(notizia);
            return true;
        }
        return false;
    }

    public List<Notizia> getNotizie(Tipologia tipologia) {
        return notizieSmistatePerTipologia.get(tipologia);
    }
}

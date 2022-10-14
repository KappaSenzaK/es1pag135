package org.example.server;

import org.example.shared.Notizia;
import org.example.catalogo.Catalogo;

import java.util.ArrayList;
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
        notizieSmistatePerTipologia.put(Catalogo.AREA_GEOGRAFICA, new ArrayList<>());
        notizieSmistatePerTipologia.put(Catalogo.SETTORE, new ArrayList<>());
        notizieSmistatePerTipologia.put(Catalogo.ARGOMENTO, new ArrayList<>());
    }


    private final HashMap<Catalogo, List<Notizia>> notizieSmistatePerTipologia;

    public HashMap<Catalogo, List<Notizia>> getNotizieSmistatePerTipologia() {
        return notizieSmistatePerTipologia;
    }

    public boolean addNotizia(Notizia notizia) {
        if (notizieSmistatePerTipologia.containsKey(notizia.getTipologia())) {
            notizieSmistatePerTipologia.get(notizia.getTipologia()).add(notizia);
            return true;
        }
        return false;
    }

    public List<Notizia> getNotizie(Catalogo catalogo) {
        return notizieSmistatePerTipologia.get(catalogo);
    }
}

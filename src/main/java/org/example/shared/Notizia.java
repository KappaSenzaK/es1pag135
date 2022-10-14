package org.example.shared;

import org.example.catalogo.Catalogo;

public class Notizia {
    private String titolo;
    private String testo;
    private Catalogo catalogo;

    public Notizia(String titolo, String testo, Catalogo catalogo) {
        this.titolo = titolo;
        this.testo = testo;
        this.catalogo = catalogo;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public Catalogo getTipologia() {
        return catalogo;
    }

    public void setTipologia(Catalogo catalogo) {
        this.catalogo = catalogo;
    }

    @Override
    public String toString() {
        return "Notizia{" +
                "titolo='" + titolo + '\'' +
                ", testo='" + testo + '\'' +
                ", tipologia=" + catalogo +
                '}';
    }
}

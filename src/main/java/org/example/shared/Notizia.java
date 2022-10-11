package org.example.shared;

public class Notizia {
    private String titolo;
    private String testo;
    private Tipologia tipologia;

    public Notizia(String titolo, String testo, Tipologia tipologia) {
        this.titolo = titolo;
        this.testo = testo;
        this.tipologia = tipologia;
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

    public Tipologia getTipologia() {
        return tipologia;
    }

    public void setTipologia(Tipologia tipologia) {
        this.tipologia = tipologia;
    }

    @Override
    public String toString() {
        return "Notizia{" +
                "titolo='" + titolo + '\'' +
                ", testo='" + testo + '\'' +
                ", tipologia=" + tipologia +
                '}';
    }
}

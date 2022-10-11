package org.example.catalogo;

public class CatalogoHelper {
    public static String getNome(Catalogo catalogo) {
        switch (catalogo) {
            case SETTORE:
                return "Settore";
            case ARGOMENTO:
                return "Argomento";
            case AREA_GEOGRAFICA:
                return "Area geografica";
            default:
                return null;
        }
    }
}

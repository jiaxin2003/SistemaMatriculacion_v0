package org.iesalandalus.programacion.matriculacion.dominio;

public enum EspecialidadProfesorado {
    INFORMATICA(" Informática"), FOL(" Formación y Orientación Laboral"), SISTEMAS(" Sistemas y Aplicaciones Informáticas");

    private final String cadenaAMostrar;

    EspecialidadProfesorado(String cadenaAMostrar) {
        this.cadenaAMostrar = cadenaAMostrar;
    }

    public String imprimir() {
        int digito;
        if (this == INFORMATICA) {
            digito = 1;
        } else if (this == FOL) {
            digito = 2;
        } else if(this == SISTEMAS) {
            digito = 3;
        }else {
            digito = 0;
        }
        return digito + ".-" + cadenaAMostrar;
    }

    @Override
    public String toString() {
        return cadenaAMostrar;
    }
}

package org.iesalandalus.programacion.matriculacion.dominio;

public enum Grado {
    GDCFGB(" Grado D Ciclo Formativo de Grado Basico"),
    GDCFGM(" Grado D Ciclo Formativo de Grado Medio"),
    GDCFGS(" Grado D Ciclo Formativo de Grado Superior");

    private final String cadenaAMostrar;

    Grado(String cadenaAMostrar) {
        this.cadenaAMostrar = cadenaAMostrar;
    }


    public String imprimir() {
        int digito;
        if (this == GDCFGB) {
            digito = 1;
        } else if (this == GDCFGM) {
            digito = 2;
        } else if (this == GDCFGS) {
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

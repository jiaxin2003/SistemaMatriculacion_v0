package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.dominio.Matricula;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;

public class Matriculas {
    private static int capacidad;
    private static int tamano;
    private final Matricula[] coleccionMatriculas;

    public Matriculas(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        Matriculas.capacidad = capacidad;
        Matriculas.tamano = 0;
        this.coleccionMatriculas = new Matricula[capacidad];
    }

    public void insertar(Matricula matricula) throws OperationNotSupportedException {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No se puede insertar una matrícula nula.");
        }
        if (tamano >= capacidad) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más matrículas.");
        }
        if (buscarIndice(matricula) != -1) {
            throw new OperationNotSupportedException("ERROR: Ya existe una matrícula con ese identificador.");
        }
        coleccionMatriculas[tamano] = new Matricula(matricula);
        tamano++;
    }

    private int buscarIndice(Matricula matricula) {
        if (matricula == null) {
            throw new NullPointerException("ERROR: La matricula no puede ser nulo.");
        }
        for (int i = 0; i < tamano; i++) {
            if (coleccionMatriculas[i].getIdMatricula() == matricula.getIdMatricula()) {
                return i;
            }
        }
        return -1;
    }

    public Matricula buscar(Matricula matricula) {
        if (matricula == null) {
            throw new NullPointerException("ERROR: La matricula no puede ser nulo.");
        }
        int indice = buscarIndice(matricula);
        if (indice == -1) {
            return null;
        }
        return coleccionMatriculas[indice];

    }

    public void borrar(Matricula matricula) throws OperationNotSupportedException {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No se puede borrar una matrícula nula.");
        }
        int indice = buscarIndice(matricula);
        if (indice == -1) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna matrícula como la indicada.");
        }
        matricula.setFechaAnulacion(LocalDate.now());

    }

    public Matricula[] get() throws OperationNotSupportedException {
        return copiaProfundaMatriculas(coleccionMatriculas);
    }

    private Matricula[] copiaProfundaMatriculas(Matricula[] matriculasOriginales) throws OperationNotSupportedException {
        Matricula[] copia = new Matricula[tamano];
        for (int i = 0; i < tamano; i++) {
            copia[i] = new Matricula(matriculasOriginales[i]);
        }
        return copia;
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        for (int i = indice; i < tamano - 1; i++) {
            coleccionMatriculas[i] = coleccionMatriculas[i + 1];
        }
        tamano--;
    }

    private boolean tamanoSuperado(int i) {
        return i >= getTamano();
    }

    private boolean capacidadSuperada(int i) {
        return i >= capacidad;
    }


    public int getCapacidad() {
        return capacidad;
    }

    public int getTamano() {
        return tamano;
    }


    public Matricula[] get(Alumno alumno) throws OperationNotSupportedException {
        Matricula[] copia = new Matricula[tamano];
        for (int i = 0; i < tamano; i++) {
            if (coleccionMatriculas[i].getAlumno().equals(alumno)) {
                copia[i] = new Matricula(coleccionMatriculas[i]);
            }
        }
        return copia;
    }

    public Matricula[] get(String cursoAcademico) throws OperationNotSupportedException {
        Matricula[] copia = new Matricula[tamano];
        for (int i = 0; i < tamano; i++) {
            if (coleccionMatriculas[i].getCursoAcademico().equals(cursoAcademico)) {
                copia[i] = new Matricula(coleccionMatriculas[i]);
            }
        }
        return copia;
    }

    public Matricula[] get(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        Matricula[] copia = new Matricula[tamano];
        int contador = 0;

        for (int i = 0; i < tamano; i++) {
            Matricula matricula = coleccionMatriculas[i];
            Asignatura[] asignaturas = matricula.getColeccionAsignaturas();

            for (Asignatura asignatura : asignaturas) {
                if (asignatura != null && asignatura.getCicloFormativo().equals(cicloFormativo)) {
                    copia[contador++] = new Matricula(matricula);
                    break;
                }
            }
        }

        Matricula[] resultado = new Matricula[contador];
        for (int i = 0; i < contador; i++) {
            resultado[i] = copia[i];
        }

        return resultado;
    }


}

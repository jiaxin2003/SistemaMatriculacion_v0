package org.iesalandalus.programacion.matriculacion.dominio;

import java.util.Objects;

public class Asignatura {
    public static final int MAX_NUM_HORAS_ANUALES=300;
    public static final int MAX_NUM_HORAS_DESDOBLES=6;
    private String codigo;
    private String nombre;
    private int horasAnuales;
    private int horasDesdoble;
    private Curso curso;
    private EspecialidadProfesorado especialidadProfesorado;
    private CicloFormativo cicloFormativo;


    public Asignatura(String codigo, String nombre, int horas,  Curso curso,int horasDesdobles, EspecialidadProfesorado especialidadProfesorado, CicloFormativo cicloFormativo) {
        setCodigo(codigo);
        setNombre(nombre);
        setHorasAnuales(horas);
        setCurso(curso);
        setHorasDesdoble(horasDesdobles);
        setEspecialidadProfesorado(especialidadProfesorado);
        setCicloFormativo(cicloFormativo);
    }

    public Asignatura(Asignatura asignatura) {
        setCodigo(asignatura.getCodigo());
        setNombre(asignatura.getNombre());
        setHorasAnuales(asignatura.getHorasAnuales());
        setCurso(asignatura.getCurso());
        setHorasDesdoble(asignatura.getHorasDesdoble());
        setEspecialidadProfesorado(asignatura.getEspecialidadProfesorado());
        setCicloFormativo(asignatura.getCicloFormativo());
    }

    public String imprimir() {
        return codigo + " - " + nombre + " - " + horasAnuales + " - " + curso + " - " + horasDesdoble + " - " + especialidadProfesorado + " - " + cicloFormativo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        if (codigo != null && codigo.length() == 4) {
           throw new IllegalArgumentException("ERROR: El código de una asignatura debe ser un número de 4 dígitos.");
        }
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null){
            throw new NullPointerException("ERROR: El nombre de una asignatura no puede ser nulo.");
    } else if (nombre.isBlank()) {
            throw new IllegalArgumentException("ERROR: El nombre de una asignatura no puede estar vacío.");
        }
        this.nombre = nombre;
    }

    public int getHorasAnuales() {
        return horasAnuales;
    }

    public void setHorasAnuales(int horasAnuales) {
        if (horasAnuales <= 0 || horasAnuales > MAX_NUM_HORAS_ANUALES) {
            throw new IllegalArgumentException("ERROR: El número de horas de una asignatura no puede ser menor o igual a 0 ni mayor a " + MAX_NUM_HORAS_ANUALES + ".");
        }
        this.horasAnuales = horasAnuales;
    }

    public int getHorasDesdoble() {
        return horasDesdoble;
    }

    public void setHorasDesdoble(int horasDesdoble) {
        if (horasDesdoble < 0 || horasDesdoble > MAX_NUM_HORAS_DESDOBLES) {
            throw new IllegalArgumentException("ERROR: El número de horas de desdoble de una asignatura no puede ser menor a 0 ni mayor a " + MAX_NUM_HORAS_DESDOBLES + ".");
        }
        this.horasDesdoble = horasDesdoble;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        if (curso == null) {
            throw new NullPointerException("ERROR: El curso de una asignatura no puede ser nulo.");
        }
        this.curso = curso;
    }

    public EspecialidadProfesorado getEspecialidadProfesorado() {
        return especialidadProfesorado;
    }

    public void setEspecialidadProfesorado(EspecialidadProfesorado especialidadProfesorado) {
        if (especialidadProfesorado == null) {
            throw new NullPointerException("ERROR: La especialidad de una asignatura no puede ser nula.");
        }
        this.especialidadProfesorado = especialidadProfesorado;
    }

    public CicloFormativo getCicloFormativo() {
        return cicloFormativo;
    }

    public void setCicloFormativo(CicloFormativo cicloFormativo) {
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: El ciclo formativo de una asignatura no puede ser nulo.");
        }
        this.cicloFormativo = cicloFormativo;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Asignatura that)) return false;
        return horasAnuales == that.horasAnuales && horasDesdoble == that.horasDesdoble && Objects.equals(codigo, that.codigo) && Objects.equals(nombre, that.nombre) && curso == that.curso && especialidadProfesorado == that.especialidadProfesorado && Objects.equals(cicloFormativo, that.cicloFormativo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, nombre, horasAnuales, horasDesdoble, curso, especialidadProfesorado, cicloFormativo);
    }

    @Override
    public String toString() {
        return "Asignatura{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", horas=" + horasAnuales +
                ", horasDesdobles=" + horasDesdoble +
                ", curso=" + curso +
                ", especialidadProfesorado=" + especialidadProfesorado +
                ", cicloFormativo=" + cicloFormativo +
                '}';
    }
}

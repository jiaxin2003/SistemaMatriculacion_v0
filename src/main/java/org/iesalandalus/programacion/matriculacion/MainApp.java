package org.iesalandalus.programacion.matriculacion;

import org.iesalandalus.programacion.matriculacion.dominio.*;
import org.iesalandalus.programacion.matriculacion.negocio.Alumnos;
import org.iesalandalus.programacion.matriculacion.negocio.Asignaturas;
import org.iesalandalus.programacion.matriculacion.negocio.CiclosFormativos;
import org.iesalandalus.programacion.matriculacion.negocio.Matriculas;
import org.iesalandalus.programacion.matriculacion.vista.Consola;
import org.iesalandalus.programacion.matriculacion.vista.Opcion;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.Arrays;


public class MainApp {
    public static final int CAPACIDAD = 3;
    private static Alumnos alumnos;
    private static Asignaturas asignaturas;
    private static CiclosFormativos ciclosFormativos;
    private static Matriculas matriculas;

    public static void main(String[] args) {

        alumnos = new Alumnos(CAPACIDAD);
        asignaturas = new Asignaturas(CAPACIDAD);
        ciclosFormativos = new CiclosFormativos(CAPACIDAD);
        matriculas = new Matriculas(CAPACIDAD);
//        try {
//            Alumno alumno1 = new Alumno("jasdfasdf", "00000000T", "asdasdf@asfasdf.com", "633333222", LocalDate.of(2000, 1, 1));
//            alumnos.insertar(alumno1);
//            CicloFormativo cicloFormativo1 = new CicloFormativo(1333, "Informatica", Grado.GDCFGB, "Informatica", 2000);
//            ciclosFormativos.insertar(cicloFormativo1);
//            Asignatura asignatura1 = new Asignatura("1374", "Informatica", 250, Curso.PRIMERO, 3, EspecialidadProfesorado.INFORMATICA, cicloFormativo1);
//            asignaturas.insertar(asignatura1);
//            Matricula matricula1 = new Matricula(9874, "24-25",LocalDate.now(), alumno1, new Asignatura[]{asignatura1});
//            matriculas.insertar(matricula1);
//        } catch (Exception e) {
//            e.getMessage();
//        }


        Opcion opcion;
        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            ejecutarOpcion(opcion);
        } while (opcion != Opcion.SALIR);

    }

    private static void ejecutarOpcion(Opcion opcion) {
        switch (opcion) {
            case SALIR:
                System.out.println("Hasta pronto.");
                System.exit(0);
                break;
            case INSERTAR_ALUMNO:
                insertarAlumno();
                break;
            case BUSCAR_ALUMNO:
                buscarAlumno();
                break;
            case BORRAR_ALUMNO:
                borrarAlumno();
                break;
            case MOSTRAR_ALUMNOS:
                mostrarAlumnos();
                break;
            case INSERTAR_ASIGNATURA:
                insertarAsignatura();
                break;
            case BUSCAR_ASIGNATURA:
                buscarAsignatura();
                break;
            case BORRAR_ASIGNATURA:
                borrarAsignatura();
                break;
            case MOSTRAR_ASIGNATURAS:
                mostrarAsignaturas();
                break;
            case INSERTAR_CICLO_FORMATIVO:
                insertarCicloFormativo();
                break;
            case BUSCAR_CICLO_FORMATIVO:
                buscarCicloFormativo();
                break;
            case BORRAR_CICLO_FORMATIVO:
                borrarCicloFormativo();
                break;
            case MOSTRAR_CICLOS_FORMATIVOS:
                mostrarCiclosFormativos();
                break;
            case INSERTAR_MATRICULA:
                insertarMatricula();
                break;
            case BUSCAR_MATRICULA:
                buscarMatricula();
                break;
            case ANULAR_MATRICULA:
                anularMatricula();
                break;
            case MOSTRAR_MATRICULAS:
                mostrarMatriculas();
                break;
            case MOSTRAR_MATRICULAS_ALUMNO:
                mostrarMatriculasPorAlumno();
                break;
            case MOSTRAR_MATRICULAS_CICLO_FORMATIVO:
                mostrarMatriculasPorCiclo();
                break;
            case MOSTRAR_MATRICULAS_CURSO_ACADEMICO:
                mostrarMatriculasPorCurso();
                break;

        }
    }

    private static void insertarAlumno() {
        try {
            Alumno alumno = Consola.leerAlumno();
            alumnos.insertar(alumno);
            System.out.println("Alumno insertado correctamente.");
        } catch (IllegalArgumentException | OperationNotSupportedException e) {
            System.out.println("ERROR: No se pudo insertar el alumno.");
            e.getMessage();
        }
    }

    private static void buscarAlumno() {
        try {
            Alumno alumno = alumnos.buscar(Consola.getAlumnoPorDni());
            if (alumno == null) {
                System.out.println("Alumno no encontrado.");
            }
            System.out.println(alumno);
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println("ERROR: No se pudo buscar el alumno.");
            e.getMessage();
        }
    }

    private static void borrarAlumno() {
        try {
            Alumno alumno = Consola.getAlumnoPorDni();
            alumnos.borrar(alumno);
            System.out.println("Alumno borrado correctamente.");
        } catch (IllegalArgumentException | OperationNotSupportedException | NullPointerException e) {
            System.out.println("ERROR: No se pudo borrar el alumno.");
            e.getMessage();
        }
    }

    private static void mostrarAlumnos() {
        try {


            if (alumnos.getTamano() == 0 ) {
                System.out.println("No hay alumnos registrados.");
            } else System.out.println("Alumnos:" + Arrays.toString(alumnos.get()));
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println("ERROR: No se pudo mostrar los alumnos.");
            e.getMessage();
        }
    }


    private static void insertarAsignatura() {
        try {
            CicloFormativo ciclo = Consola.getCicloPorCodigo();
            Asignatura asignatura = Consola.leerAsignatura(ciclo);
            asignaturas.insertar(asignatura);
            System.out.println("Asignatura insertada correctamente.");
        } catch (IllegalArgumentException | OperationNotSupportedException e) {
            System.out.println("ERROR: No se pudo insertar la asignatura.");
            e.getMessage();
        }
    }


    private static void buscarAsignatura() {
        try {
            Asignatura asignatura = asignaturas.buscar(Consola.getAsignaturaPorCodigo());
            if (asignatura == null) {
                System.out.println("Asignatura no encontrada.");
            }
            System.out.println(asignatura);
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println("ERROR: No se pudo buscar la asignatura.");
            e.getMessage();
        }
    }

    private static void borrarAsignatura() {
        try {
            Asignatura asignatura = Consola.getAsignaturaPorCodigo();
            asignaturas.borrar(asignatura);
            System.out.println("Asignatura borrada correctamente.");
        } catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {
            System.out.println("ERROR: No se pudo borrar la asignatura.");
            e.getMessage();
        }
    }


    private static void mostrarAsignaturas() {
        try {
            if (asignaturas.getTamano() == 0) {
                System.out.println("No hay asignaturas registradas.");
            } else {
                System.out.println(Arrays.toString(asignaturas.get()));
            }
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println("ERROR: No se pudo mostrar las asignaturas.");
            e.getMessage();
        }
    }

    private static void insertarCicloFormativo() {
        try {
            CicloFormativo ciclo = Consola.leerCicloFormativo();
            ciclosFormativos.insertar(ciclo);
            System.out.println("Ciclo formativo insertado correctamente.");
        } catch (IllegalArgumentException | OperationNotSupportedException | NullPointerException e) {
            System.out.println("ERROR: No se pudo insertar el ciclo formativo.");
            e.getMessage();
        }
    }

    private static void buscarCicloFormativo() {
        try {
            CicloFormativo ciclo = ciclosFormativos.buscar(Consola.getCicloPorCodigo());
            if (ciclo == null) {
                System.out.println("Ciclo formativo no encontrado.");
            }
            System.out.println(ciclo);
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println("ERROR: No se pudo buscar el ciclo formativo.");
            e.getMessage();
        }
    }

    private static void borrarCicloFormativo() {
        try {
            CicloFormativo ciclo = Consola.getCicloPorCodigo();
            ciclosFormativos.borrar(ciclo);
            System.out.println("Ciclo formativo borrado correctamente.");
        } catch (IllegalArgumentException | OperationNotSupportedException | NullPointerException e) {
            System.out.println("ERROR: No se pudo borrar el ciclo formativo.");
            e.getMessage();
        }
    }

    private static void mostrarCiclosFormativos() {
        try {
            if (ciclosFormativos.getTamano() == 0) {
                System.out.println("No hay ciclos formativos registrados.");
            } else {
                System.out.println("Ciclos Formativos :" + Arrays.toString(ciclosFormativos.get()));
            }
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println("ERROR: No se pudo mostrar los ciclos formativos.");
            e.getMessage();
        }
    }

    private static void insertarMatricula() {
        try {
            Alumno alumno = Consola.getAlumnoPorDni();
            alumno = alumnos.buscar(alumno);
            Matricula matricula = Consola.leerMatricula(alumno, asignaturas);
            matriculas.insertar(matricula);
            System.out.println("Matrícula insertada correctamente.");
        } catch (IllegalArgumentException | OperationNotSupportedException | NullPointerException e) {
            System.out.println("ERROR: No se pudo insertar la matrícula.");
            e.getMessage();
        }
    }

    private static void buscarMatricula() {
        try {
            Matricula matriculaBuscar = Consola.getMatriculaPorIdentificador();
            Matricula matricula = matriculas.buscar(matriculaBuscar);
            if (matricula == null) {
                System.out.println("Matrícula no encontrada.");
            }
            System.out.println(matricula);
        } catch (IllegalArgumentException | OperationNotSupportedException | NullPointerException e) {
            e.getMessage();
        }
    }

    private static void anularMatricula() {
        try {
            Matricula matricula = Consola.getMatriculaPorIdentificador();
            Matricula matriculaEncontrada = matriculas.buscar(matricula);
            if (matriculaEncontrada == null) {
                System.out.println("Matrícula no encontrada.");

            }else if (matriculaEncontrada.getFechaAnulacion() != null) {
                System.out.println("Matrícula ya anulada.");
            } else {
                do {
                    matriculas.borrar(matriculaEncontrada);
                    matricula.setFechaAnulacion(LocalDate.now());
                } while (matricula.getFechaAnulacion() == null);
            }
            System.out.println("Matrícula anulada correctamente.");
        } catch (IllegalArgumentException | OperationNotSupportedException | NullPointerException e) {
            e.getMessage();
            System.out.println("ERROR: No se pudo anular la matrícula.");
        }
    }

    private static void mostrarMatriculas() {
        try {
            if (matriculas.getTamano() == 0) {
                System.out.println("No hay matrículas registradas.");
                return;
            }
            System.out.println(Arrays.toString(matriculas.get()));
        } catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
            System.out.println("No hay Matrículas registradas.");
            e.getMessage();
        }

    }

    private static void mostrarMatriculasPorAlumno() {
        try {
            Alumno alumno = Consola.getAlumnoPorDni();
            alumno = alumnos.buscar(alumno);
            System.out.println(Arrays.toString(matriculas.get(alumno)));
        } catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {
            System.out.println("ERROR: No se pudo mostrar las matrículas por alumno.");
            e.getMessage();
        }
    }

    private static void mostrarMatriculasPorCiclo() {
        try {
            CicloFormativo ciclo = Consola.getCicloPorCodigo();
            CicloFormativo cicloFormativo = ciclosFormativos.buscar(ciclo);
            System.out.println(Arrays.toString(matriculas.get(cicloFormativo)));
        } catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {
            System.out.println("ERROR: No se pudo mostrar las matrículas por ciclo.");
            e.getMessage();
        }
    }

    private static void mostrarMatriculasPorCurso() {
        try {
            System.out.println("Introduce el curso de la matrícula.");
            System.out.println("El formato tiene que ser 24-25");
            String curso = Entrada.cadena();
            System.out.println(Arrays.toString(matriculas.get(curso)));
        } catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {
            System.out.println("ERROR: No se pudo mostrar las matrículas por curso.");
            e.getMessage();
        }
    }


}

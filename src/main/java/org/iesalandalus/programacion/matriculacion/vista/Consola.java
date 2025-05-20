
package org.iesalandalus.programacion.matriculacion.vista;

import org.iesalandalus.programacion.matriculacion.dominio.*;
import org.iesalandalus.programacion.matriculacion.negocio.Alumnos;
import org.iesalandalus.programacion.matriculacion.negocio.Asignaturas;
import org.iesalandalus.programacion.matriculacion.negocio.CiclosFormativos;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;


public class Consola {
    private void consola() {

    }

    public static void mostrarMenu() {
        for (Opcion opcion : Opcion.values()) {
            System.out.println(opcion.toString());
        }
    }

    public static Opcion elegirOpcion() {
        int opcion;
        do {
            System.out.print("Elige una opcion: ");
            opcion = Entrada.entero();
        } while (opcion < 0 || opcion > Opcion.values().length);
        return Opcion.values()[opcion];
    }

    public static Alumno leerAlumno() {


        String dni;
        String nombre;
        LocalDate fechaNacimiento;
        String telefono;
        String email;

        do {
            System.out.println("Introduce los datos del alumno");
            System.out.println("Nombre: ");
            nombre = Entrada.cadena();
            System.out.println("DNI: ");
            dni = Entrada.cadena();
            System.out.println("Telefono: ");
            telefono = Entrada.cadena();
            System.out.println("Email: ");
            email = Entrada.cadena();
            String fechaIntroducida = ("Fecha de nacimiento: ");
            fechaNacimiento = leerFecha(fechaIntroducida);
        } while (dni.isEmpty() || nombre.isEmpty() || telefono.isEmpty() || email.isEmpty());
        return new Alumno(nombre, dni, email, telefono, fechaNacimiento);
    }

    public static LocalDate leerFecha(String fechaIntroducida) {
        LocalDate fecha = null;
        boolean fechaCorrecta = false;

        do {
            try {
                System.out.printf(fechaIntroducida, Alumno.FORMATO_FECHA);
                fecha = LocalDate.parse(Entrada.cadena(), DateTimeFormatter.ofPattern(Alumno.FORMATO_FECHA));
                fechaCorrecta = true;
            } catch (DateTimeParseException e) {
                System.out.println("ERROR: La fecha introducida no es correcta.");
            }
        }
        while (!fechaCorrecta);
        return fecha;
    }


    public static Alumno getAlumnoPorDni() {

        Alumno alumno = null;
        String nombre = "Pepe Perez";
        String telefono = "666333888";
        String correo = "PepePerez@gmail.com";
        String dni = "78945613B";
        LocalDate fechaNacimiento = LocalDate.of(2000, 12, 12);
        ;

        do {
            System.out.println("Introduce el DNI del alumno: ");
            dni = Entrada.cadena();

        } while (dni == null || dni.isEmpty());
        try {
            alumno = new Alumno(nombre, dni, correo, telefono, fechaNacimiento);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return alumno;
    }

    public static Grado leerGrado() {
        int opcion;
        do {
            System.out.println("Elige el grado: ");
            for (Grado grado : Grado.values()) {
                System.out.println(grado.ordinal() + grado.toString());
            }
            opcion = Entrada.entero();
        } while (opcion < 0 || opcion > Grado.values().length);
        return Grado.values()[opcion];
    }

    public static CicloFormativo leerCicloFormativo() {

        int codigo;
        String familiaProfesional;
        Grado grado;
        String nombre;
        int horas;

        System.out.println("Introduce los datos del ciclo formativo");
        do {
            System.out.println("Codigo: ");
            codigo = Entrada.entero();
            System.out.println("Familia profesional: ");
            familiaProfesional = Entrada.cadena();
            System.out.println("Nombre: ");
            nombre = Entrada.cadena();
            System.out.println("Horas: ");
            horas = Entrada.entero();
            grado = leerGrado();
        } while (codigo <= 0 || familiaProfesional.isEmpty() || grado == null || nombre.isEmpty() || horas <= 0);

        return new CicloFormativo(codigo, familiaProfesional, grado, nombre, horas);
    }

    public static void mostrarCiclosFormativos(CiclosFormativos ciclosFormativos) {
        System.out.println(ciclosFormativos.toString());
    }


    public static CicloFormativo getCicloPorCodigo() {
        CicloFormativo cicloFormativo = null;
        int codigo;
        String familiaProfesional = "Informatica";
        Grado grado = Grado.GDCFGB;
        String nombre = "Pepe Perez";
        int horas = 250;

        do {
            System.out.println("Introduce el codigo del ciclo formativo: ");
            codigo = Entrada.entero();
        } while (codigo <= 0);
        try {
            cicloFormativo = new CicloFormativo(codigo, familiaProfesional, grado, nombre, horas);
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println("ERROR: Los datos introducidos no son correctos.");
        }
        return new CicloFormativo(cicloFormativo);
    }

    public static EspecialidadProfesorado leerEspecialidadProfesorado() {
        int opcion;
        do {
            System.out.println("Elige la especialidad del profesorado: ");
            for (EspecialidadProfesorado especialidadProfesorado : EspecialidadProfesorado.values()) {
                System.out.println(especialidadProfesorado.ordinal() + especialidadProfesorado.toString());
            }
            opcion = Entrada.entero();
        } while (opcion < 0 || opcion > EspecialidadProfesorado.values().length);
        return EspecialidadProfesorado.values()[opcion];
    }

    public static Curso leerCurso() {
        int opcion;
        do {
            System.out.println("Elige el curso: ");
            for (Curso curso : Curso.values()) {
                System.out.println(curso.ordinal() + curso.toString());
            }
            opcion = Entrada.entero();
        } while (opcion < 0 || opcion > Curso.values().length);
        return Curso.values()[opcion];
    }

    public static Asignatura leerAsignatura(CicloFormativo ciclosFormativos) {

        String codigo;
        String nombre;
        int horas;
        int horasDesdoble;
        Curso curso;
        EspecialidadProfesorado especialidadProfesorado;


        do {
            System.out.println("Introduce los datos de la asignatura");
            System.out.println("Codigo: ");
            codigo = Entrada.cadena();
            System.out.println("Nombre: ");
            nombre = Entrada.cadena();
            System.out.println("Horas: ");
            horas = Entrada.entero();
            System.out.println("Horas desdoble: ");
            horasDesdoble = Entrada.entero();

            curso = leerCurso();
            especialidadProfesorado = leerEspecialidadProfesorado();


        } while (codigo.isEmpty() || nombre.isEmpty() || horas <= 0 || horasDesdoble <= 0 || curso == null || especialidadProfesorado == null || ciclosFormativos == null);

        return new Asignatura(codigo, nombre, horas, curso, horasDesdoble, especialidadProfesorado, ciclosFormativos);
    }

    public static Asignatura getAsignaturaPorCodigo() {
        String codigo;
        String nombre = "Pepe Perez";
        int horasAnuales = 100;
        int horasDesdoble = 3;
        Curso curso = Curso.PRIMERO;
        EspecialidadProfesorado especialidadProfesorado = EspecialidadProfesorado.INFORMATICA;
        CicloFormativo cicloFormativo = new CicloFormativo(1234, "Informatica", Grado.GDCFGB, "Pepe Perez", 250);

        do {
            System.out.println("Introduce el codigo de la asignatura: ");
            codigo = Entrada.cadena();
        } while (codigo.isEmpty());

        return new Asignatura(codigo, nombre, horasAnuales, curso, horasDesdoble, especialidadProfesorado, cicloFormativo);
    }

    private static void mostrarAsignaturas(Asignaturas asignaturas) {
        System.out.println(Arrays.toString(asignaturas.get()));
    }

    private static boolean asignaturaYaMatriculada(Asignatura[] asignaturasMatricula, Asignatura asignatura) {
        for (Asignatura coleccionAsignatura : asignaturasMatricula) {
            if (coleccionAsignatura.getCodigo().equals(asignatura.getCodigo())) {
                return true;
            }
        }
        return false;
    }

    public static Matricula leerMatricula(Alumno alumnos, Asignaturas asignaturas) throws OperationNotSupportedException {
        int idMatricula;
        String cursoAcademico;
        LocalDate fechaMatriculacion;
        Alumno alumno;
        Matricula matricula;


        do {
            System.out.println("Introduzca los datos de la Matricula:");
            System.out.println("IdMatricula: ");
            idMatricula = Entrada.entero();
            System.out.println("Curso Academico: ");
            cursoAcademico = Entrada.cadena();
            fechaMatriculacion = leerFecha("Fecha Matriculacion: ");


        }
        while (alumnos == null || idMatricula < 0 || cursoAcademico.isEmpty() || fechaMatriculacion == null);
        mostrarAsignaturas(asignaturas);
        System.out.println("Introduzca el codigo de la asignatura: ");
        String codigoAsignatura = Entrada.cadena();
        CicloFormativo cicloFormativo1 = new CicloFormativo(1333, "Informatica", Grado.GDCFGB, "Informatica", 1000);
        Asignatura asignaturaBuscar = new Asignatura(codigoAsignatura, "Informatica", 250, Curso.PRIMERO, 3, EspecialidadProfesorado.INFORMATICA, cicloFormativo1);
        Asignatura asignatura = asignaturas.buscar(asignaturaBuscar);
        Asignatura[] coleccionAsignatura = new Asignatura[10];
        int contadorAsignaturas = 0;
        if (asignatura == null) {
            System.out.println("La asignatura introducida no existe");
        }else {
                coleccionAsignatura[contadorAsignaturas] = asignatura;
                contadorAsignaturas++;
        }
        Asignatura[] asignaturasFinales = new Asignatura[contadorAsignaturas];
        for (int i = 0; i < contadorAsignaturas; i++) {
            asignaturasFinales[i] = coleccionAsignatura[i];
        }
        return new Matricula(idMatricula, cursoAcademico, fechaMatriculacion, alumnos, asignaturasFinales);
    }

    public static Matricula getMatriculaPorIdentificador() throws OperationNotSupportedException {
        Matricula matricula = null;
        int idMatricula = 2009;
        String cursoAcademico = "24-25";
        LocalDate fechaMatriculacion = LocalDate.now();
        Alumno alumno = new Alumno("Pepe Perez", "87654321x","PepePerez@gmail.com","666555444",LocalDate.of(2000, 12, 12));
        Asignatura[] coleccionAsignaturas = new Asignatura[10];


        do {
            System.out.println("Introduzca el ID de la Matricula:");
            idMatricula = Entrada.entero();
        } while (idMatricula < 0);

        matricula = new Matricula(idMatricula, cursoAcademico, fechaMatriculacion, alumno, coleccionAsignaturas);
        return new Matricula(matricula);
    }
}

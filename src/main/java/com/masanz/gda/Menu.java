package com.masanz.gda;

import java.util.Arrays;

public class Menu {

    private final Gestor gestor;

    public static void main(String[] args) {
        String file;
        Gestor gestor = new Gestor();
        if (args.length == 0) {
            file = "data1.csv";
        }else {
            file = args[0];
        }
        Cargador.carga(gestor, file);
        Menu menu = new Menu(gestor);
        menu.run();
    }

    public Menu(Gestor gestor) {
        this.gestor = gestor;
    }

    public void run() {
        Salida.mostrarMenu();
        EMenu opcion = Entrada.leerOpcionMenu("Opción");
        while (opcion != EMenu.SALIR) {
            switch (opcion) {
                case EXISTE_GRUPO -> existeGrupo();
                case ANADIR_GRUPO -> anadirGrupo();
                case LISTAR_GRUPOS -> listarGrupos();
                case BORRAR_GRUPO -> borrarGrupo();
                case EXISTE_ASIGNATURA_GRUPO -> existeAsignatura();
                case ANADIR_ASIGNATURA_GRUPO -> anadirAsignatura();
                case LISTAR_ASIGNATURAS_GRUPO -> listarAsignaturasGrupo();
                case BORRAR_ASIGNATURA_GRUPO -> borrarAsignatura();
                case LISTAR_ASIGNATURAS_TODAS -> listarAsignaturasTodas();
                case LISTAR_ESTUDIANTES_ASIGNATURA_GRUPO -> listarEstudiantesAsignaturaGrupo();
                case EXISTE_ESTUDIANTE_ASIGNATURA_GRUPO -> existeEstudianteAsignaturaGrupo();
                case CONSULTAR_ESTUDIANTE_ASIGNATURA_GRUPO -> consultarEstudianteAsignaturaGrupo();
                case ANADIR_ESTUDIANTE_ASIGNATURA_GRUPO -> anadirEstudianteAsignaturaGrupo();
                case BORRAR_ESTUDIANTE_ASIGNATURA_GRUPO -> borrarEstudianteAsignaturaGrupo();
                case LISTAR_ESTUDIANTES_GRUPO -> listarEstudiantesGrupo();
                case LISTAR_ESTUDIANTES_ASIGNATURA -> listarEstudiantesAsignatura();
                case LISTAR_ESTUDIANTES_NOTA -> listarEstudiantesNota();
                case DISTRIBUCION_NOTAS_ASIGNATURA_GRUPO -> distribucionNotasAsignaturaGrupo();
                case DISTRIBUCION_NOTAS_ASIGNATURA -> distribucionNotasAsignatura();
                case GRUPO_ALUMNO -> grupoAlumno();
                case NOTAS_ALUMNO -> notasAlumno();
                default -> { }
            }
            if (!Entrada.leerConfirmacion("Continuar")) {
                break;
            }
//            Salida.mostrarMenu();
            opcion = Entrada.leerOpcionMenu("Opción");
        }
    }

    //region operaciones grupo 11-14
    private void existeGrupo() {
        String nombre = Entrada.leerString("Nombre del grupo");
        Grupo grupo = new Grupo(nombre);
        if (gestor.existeGrupo(grupo)) {
            Salida.info("El grupo existe");
        }else{
            Salida.info("El grupo no existe");
        }
    }

    private void anadirGrupo() {
        String nombre = Entrada.leerString("Nombre del grupo");
        Grupo grupo = new Grupo(nombre);
        if (gestor.existeGrupo(grupo)){
            Salida.info("El grupo ya existe");
        }else{
            gestor.anadirGrupo(grupo);
            Salida.info("Grupo añadido.");
        }
    }

    private void listarGrupos() {
        Salida.listar(Arrays.stream(gestor.getGrupos().toArray()).map(Object::toString).toArray(String[]::new));
    }

    private void borrarGrupo() {
        String nombre = Entrada.leerString("Nombre del grupo");
        Grupo grupo = new Grupo(nombre);
        if (!gestor.existeGrupo(grupo)) {
            Salida.info("El grupo no existe");
        }else{
            gestor.borrarGrupo(grupo);
            Salida.info("Grupo borrado.");
        }
    }

    //endregion

    //region operaciones asignatura 21-24
    private void existeAsignatura() {
        String nombreGrupo = Entrada.leerString("Nombre del grupo");
        Grupo grupo = new Grupo(nombreGrupo);
        String nombreAsignatura = Entrada.leerString("Nombre de la asignatura");
        Asignatura asignatura = new Asignatura(nombreAsignatura);
        if (gestor.existeAsignaturaGrupo(asignatura, grupo)) {
            Salida.info("La asignatura existe");
        }else{
            Salida.info("La asignatura no existe");
        }
    }

    private void anadirAsignatura() {
        String nombreGrupo = Entrada.leerString("Nombre del grupo");
        Grupo grupo = new Grupo(nombreGrupo);
        String nombreAsignatura = Entrada.leerString("Nombre de la asignatura");
        Asignatura asignatura = new Asignatura(nombreAsignatura);
        if (!gestor.existeGrupo(grupo)){
            Salida.info("El grupo no existe, créalo primero.");
        }else if (gestor.existeAsignaturaGrupo(asignatura, grupo)) {
            Salida.info("La asignatura ya existe");
        }else{
            gestor.anadirAsignaturaGrupo(asignatura, grupo);
            Salida.info("Asignatura añadida.");
        }
    }

    private void listarAsignaturasGrupo() {
        String nombreGrupo = Entrada.leerString("Nombre del grupo");
        Grupo grupo = new Grupo(nombreGrupo);
        if (!gestor.existeGrupo(grupo)) {
            Salida.info("El grupo no existe, créalo primero.");
        }else {
            Salida.listar(Arrays.stream(gestor.getAsignaturas(grupo).toArray()).map(Object::toString).toArray(String[]::new));
        }
    }

    private void borrarAsignatura() {
        String nombreGrupo = Entrada.leerString("Nombre del grupo");
        Grupo grupo = new Grupo(nombreGrupo);
        String nombreAsignatura = Entrada.leerString("Nombre de la asignatura");
        Asignatura asignatura = new Asignatura(nombreAsignatura);
        if (!gestor.existeGrupo(grupo)){
            Salida.info("El grupo no existe.");
        }else if (!gestor.existeAsignaturaGrupo(asignatura, grupo)) {
            Salida.info("No existe esa asignatura en ese grupo");
        }else{
            gestor.borrarAsignaturaGrupo(asignatura, grupo);
            Salida.info("Asignatura borrada.");
        }
    }

    private void listarAsignaturasTodas() {
        Salida.listar(Arrays.stream(gestor.getAsignaturas().toArray()).map(Object::toString).toArray(String[]::new));
    }

    //endregion

    //region operaciones estudiante 31-35

    private void listarEstudiantesAsignaturaGrupo() {
        String nombreGrupo = Entrada.leerString("Nombre del grupo");
        Grupo grupo = new Grupo(nombreGrupo);
        String nombreAsignatura = Entrada.leerString("Nombre de la asignatura");
        Asignatura asignatura = new Asignatura(nombreAsignatura);
        if (!gestor.existeGrupo(grupo)) {
            Salida.info("El grupo no existe");
        }else if (!gestor.existeAsignaturaGrupo(asignatura, grupo)) {
            Salida.info("La asignatura no existe");
        }else {
            Salida.listar(Arrays.stream(gestor.getListaEstudiantesAsignaturaGrupo(asignatura,grupo).toArray()).map(Object::toString).toArray(String[]::new));
        }
    }

    private void existeEstudianteAsignaturaGrupo() {
        String nombreGrupo = Entrada.leerString("Nombre del grupo");
        Grupo grupo = new Grupo(nombreGrupo);
        String nombreAsignatura = Entrada.leerString("Nombre de la asignatura");
        Asignatura asignatura = new Asignatura(nombreAsignatura);
        String nombreEstudiante = Entrada.leerString("Nombre del/a estudiante");
        String apellidosEstudiante = Entrada.leerString("Apellidos del/a estudiante");
        Estudiante estudiante = new Estudiante(nombreEstudiante, apellidosEstudiante);
        if (!gestor.existeGrupo(grupo)) {
            Salida.info("El grupo no existe");
        }else if (!gestor.existeAsignaturaGrupo(asignatura, grupo)) {
            Salida.info("La asignatura no existe");
        }else if (!gestor.existeEstudianteAsignaturaGrupo(estudiante, asignatura, grupo)) {
            Salida.info("La/El estudiante no existe");
        }else {
            Salida.info("La/El estudiante existe");
        }
    }

    private void consultarEstudianteAsignaturaGrupo() {
        String nombreGrupo = Entrada.leerString("Nombre del grupo");
        Grupo grupo = new Grupo(nombreGrupo);
        String nombreAsignatura = Entrada.leerString("Nombre de la asignatura");
        Asignatura asignatura = new Asignatura(nombreAsignatura);
        String nombreEstudiante = Entrada.leerString("Nombre del/a estudiante");
        String apellidosEstudiante = Entrada.leerString("Apellidos del/a estudiante");
        Estudiante estudiante = new Estudiante(nombreEstudiante, apellidosEstudiante);
        if (!gestor.existeGrupo(grupo)) {
            Salida.info("El grupo no existe");
        }else if (!gestor.existeAsignaturaGrupo(asignatura, grupo)) {
            Salida.info("La asignatura no existe");
        }else if (!gestor.existeEstudianteAsignaturaGrupo(estudiante, asignatura, grupo)) {
            Salida.info("La/El estudiante no existe");
        }else {
            var e = gestor.getEstudianteAsignaturaGrupo(estudiante, asignatura, grupo);
            Salida.info(e.toString());
        }
    }

    private void anadirEstudianteAsignaturaGrupo() {
        String nombreGrupo = Entrada.leerString("Nombre del grupo");
        Grupo grupo = new Grupo(nombreGrupo);
        String nombreAsignatura = Entrada.leerString("Nombre de la asignatura");
        Asignatura asignatura = new Asignatura(nombreAsignatura);
        String nombreEstudiante = Entrada.leerString("Nombre del/a estudiante");
        String apellidosEstudiante = Entrada.leerString("Apellidos del/a estudiante");
        Estudiante estudiante = new Estudiante(nombreEstudiante, apellidosEstudiante);
        if (!gestor.existeGrupo(grupo)) {
            Salida.info("El grupo no existe");
        }else if (!gestor.existeAsignaturaGrupo(asignatura, grupo)) {
            Salida.info("La asignatura no existe");
        }else if (gestor.existeEstudianteAsignaturaGrupo(estudiante, asignatura, grupo)) {
            Salida.info("La/El estudiante existe");
        }else {
            gestor.anadirEstudianteAsignaturaGrupo(estudiante, asignatura, grupo);
            Salida.info("Estudiante añadida o añadido.");
        }
    }

    private void borrarEstudianteAsignaturaGrupo() {
        String nombreGrupo = Entrada.leerString("Nombre del grupo");
        Grupo grupo = new Grupo(nombreGrupo);
        String nombreAsignatura = Entrada.leerString("Nombre de la asignatura");
        Asignatura asignatura = new Asignatura(nombreAsignatura);
        String nombreEstudiante = Entrada.leerString("Nombre del/a estudiante");
        String apellidosEstudiante = Entrada.leerString("Apellidos del/a estudiante");
        Estudiante estudiante = new Estudiante(nombreEstudiante, apellidosEstudiante);
        if (!gestor.existeGrupo(grupo)) {
            Salida.info("El grupo no existe");
        }else if (!gestor.existeAsignaturaGrupo(asignatura, grupo)) {
            Salida.info("La asignatura no existe");
        }else if (!gestor.existeEstudianteAsignaturaGrupo(estudiante, asignatura, grupo)) {
            Salida.info("La/El estudiante no existe");
        }else {
            gestor.borrarEstudianteAsignaturaGrupo(estudiante, asignatura, grupo);
            Salida.info("Estudiante borrado o borrada.");
        }
    }

    //endregion

    //region listados estudiantes 41-43

    private void listarEstudiantesGrupo() {
        String nombreGrupo = Entrada.leerString("Nombre del grupo");
        Grupo grupo = new Grupo(nombreGrupo);
        if (!gestor.existeGrupo(grupo)) {
            Salida.info("El grupo no existe");
        }else {
            Salida.listar(gestor.getEstudiantes(grupo).stream().map(e -> e.getApellidosNombre()).toArray(String[]::new));
        }
    }

    private void listarEstudiantesAsignatura() {
        String nombreAsignatura = Entrada.leerString("Asignatura");
        Asignatura asignatura = new Asignatura(nombreAsignatura);
        Salida.listar(gestor.getEstudiantes(asignatura).stream().map(e -> e.toString()).toArray(String[]::new));
    }

    private void listarEstudiantesNota() {
        String nombreAsignatura = Entrada.leerString("Asignatura");
        Asignatura asignatura = new Asignatura(nombreAsignatura);
        double nota = Entrada.leerDoublePositivo("Nota");
        var mapa = gestor.getEstudiantesConNotaMayorIgualQue(asignatura, nota);
        String[] keys = Arrays.stream(mapa.keySet().toArray()).map(Object::toString).toArray(String[]::new);
        String[] values = Arrays.stream(mapa.values().toArray()).map(Object::toString).toArray(String[]::new);
        Salida.listarVgrupo(keys, values);
    }

    //endregion

    //region distribuciones notas 51-52

    private void distribucionNotasAsignaturaGrupo() {
        String nombreGrupo = Entrada.leerString("Nombre del grupo");
        Grupo grupo = new Grupo(nombreGrupo);
        String nombreAsignatura = Entrada.leerString("Nombre de la asignatura");
        Asignatura asignatura = new Asignatura(nombreAsignatura);
        if (!gestor.existeGrupo(grupo)) {
            Salida.info("El grupo no existe");
        }else if (!gestor.existeAsignaturaGrupo(asignatura, grupo)) {
            Salida.info("La asignatura no existe");
        }else {
            var res =gestor.getDistribucionNotasAsignaturaGrupo(asignatura, grupo);
            String[] keys = Arrays.stream(res.keySet().toArray()).map(Object::toString).toArray(String[]::new);
            String[] values = Arrays.stream(res.values().toArray()).map(Object::toString).toArray(String[]::new);
            Salida.listarH(keys, values);
        }
    }

    private void distribucionNotasAsignatura() {
        String nombreAsignatura = Entrada.leerString("Nombre de la asignatura");
        Asignatura asignatura = new Asignatura(nombreAsignatura);
        var res = gestor.getDistribucionNotasAsignatura(asignatura);
        String[] keys = Arrays.stream(res.keySet().toArray()).map(Object::toString).toArray(String[]::new);
        String[] values = Arrays.stream(res.values().toArray()).map(Object::toString).toArray(String[]::new);
        Salida.listarH(keys, values);
    }

    //endregion

    //region info estudiante 61-62

    private void grupoAlumno() {
        String nombreEstudiante = Entrada.leerString("Nombre del/a estudiante");
        String apellidosEstudiante = Entrada.leerString("Apellidos del/a estudiante");
        Grupo grupo = gestor.grupoDelEstudiante(nombreEstudiante, apellidosEstudiante);
        if (grupo==null) {
            Salida.info("La/El estudiante no existe en ningún grupo.");
        }else {
            Salida.info("La/El estudiante pertenece al grupo " + grupo);
        }
    }

    private void notasAlumno() {
        String nombreEstudiante = Entrada.leerString("Nombre del/a estudiante");
        String apellidosEstudiante = Entrada.leerString("Apellidos del/a estudiante");
        if (gestor.grupoDelEstudiante(nombreEstudiante, apellidosEstudiante)==null) {
            Salida.info("La/El estudiante no existe en ningún grupo.");
        }else {
            var res = gestor.notasEstudiante(nombreEstudiante,apellidosEstudiante);
            String[] keys = Arrays.stream(res.keySet().toArray()).map(Object::toString).toArray(String[]::new);
            String[] values = Arrays.stream(res.values().toArray()).map(Object::toString).toArray(String[]::new);
            Salida.listarVnota(keys, values);
        }
    }

    //endregion
}

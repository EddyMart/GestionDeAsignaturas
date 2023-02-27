package com.masanz.gda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class Cargador {

    /**
     * Carga un fichero en el gestor pasado como parámetro.
     * @param gestor
     * @param file
     */
    public static void carga(Gestor gestor, String file) {
        Scanner sc = null;
        try {
            try {
                sc = new Scanner(Cargador.class.getResourceAsStream(file));
            }catch (Exception e){
                sc = new Scanner(file);
            }
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                String[] a = linea.split(";");
                String nombreGrupo = a[0];
                String nombreAsignatura = a[1];
                String nombreAlumno = a[2];
                String apellidosAlumno = a[3];
                double nota = Double.parseDouble(a[4]);
                Grupo grupo = new Grupo(nombreGrupo);
                Asignatura asignatura = new Asignatura(nombreAsignatura);
                Estudiante estudiante = new Estudiante(nombreAlumno, apellidosAlumno, nota);
                // Añadir estudiante asignatura grupo
                TreeMap<Grupo, HashMap<Asignatura, ArrayList<Estudiante>>> registro = gestor.getRegistro();
                if (grupo==null || !registro.containsKey(grupo)) {
                    registro.put(grupo, new HashMap<>());
                }
                var asignaturaArrayListHashMap = registro.get(grupo);
                if (!asignaturaArrayListHashMap.containsKey(asignatura)) {
                    asignaturaArrayListHashMap.put(asignatura, new ArrayList<Estudiante>());
                }
                var listaEstudiantes = asignaturaArrayListHashMap.get(asignatura);
                if (listaEstudiantes.contains(estudiante)) {
                    int i = listaEstudiantes.indexOf(estudiante);
                    listaEstudiantes.remove(i);
                    listaEstudiantes.add(i, estudiante);
                }else {
                    listaEstudiantes.add(estudiante);
                }
            }
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }

}

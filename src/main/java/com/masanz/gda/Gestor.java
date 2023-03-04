package com.masanz.gda;

import java.util.*;

/**
 * @author Eddy Martínez
 */
public class Gestor {

    private TreeMap<Grupo, HashMap<Asignatura, ArrayList<Estudiante>>> registro;

    public Gestor() {
        registro = new TreeMap<>();
    }

    //region get/setRegistro para los tests
    public void setRegistro(TreeMap<Grupo, HashMap<Asignatura, ArrayList<Estudiante>>> registro){
        this.registro = registro;
    }
    public TreeMap<Grupo, HashMap<Asignatura, ArrayList<Estudiante>>> getRegistro() {
        return registro;
    }
    //endregion

    //region operaciones grupo 11-14

    /**
     * Si el grupo existe en el registro.
     * @param grupo Instancia de Grupo, puede ser null.
     * @return Devuelve null si el grupo es nulo o true o false dependiendo si la clave grupo existe en el registro.
     */
    public boolean existeGrupo(Grupo grupo) {
        if (grupo==null) return false;
        return registro.containsKey(grupo);
    }

    /**
     * Añade una entrada con el grupo y un HashMap nuevo asociado como valor
     * reemplazando a una entrada con la misma clave si ya existiese.
     * @param grupo Instancia de un grupo
     */
    public void anadirGrupo(Grupo grupo) {
        if (grupo==null) return;
        registro.put(grupo,new HashMap<>());
    }

    /**
     * Devuelve un conjunto ordenado no repetido de grupos formado con
     * el conjunto de grupos que contiene el registro como claves.
     * @return TreeSet de grupos del registro
     */
    public TreeSet<Grupo> getGrupos() {
        TreeSet<Grupo> grupos = new TreeSet<>();
//        for (Map.Entry<Grupo, HashMap<Asignatura, ArrayList<Estudiante>>> entry : registro.entrySet()) {
//            System.out.println(grupos.add(entry.getKey()));
//        }
        for (Grupo grupo : registro.keySet()) {
            System.out.println(grupos.add(grupo));
        }
        return grupos;
    }

    /**
     * Elimina la entrada del grupo indicado del registro.
     * @param grupo Instancia de Grupo.
     */
    public void borrarGrupo(Grupo grupo) {
//        Iterator<Grupo> it = registro.keySet().iterator();
//        while (it.hasNext()) {
//            if (it.next().equals(grupo)) {
//                it.remove();
//            }
//        }
        registro.remove(grupo);
    }

    //endregion

    //region operaciones asignatura 21-25

    /**
     * Existe la asignatura en el grupo.
     * @param asignatura
     * @param grupo
     * @return Si existe la asignatura asociada al grupo indicado en el registro.
     */

    public boolean existeAsignaturaGrupo(Asignatura asignatura, Grupo grupo) {
        for (Map.Entry<Grupo, HashMap<Asignatura, ArrayList<Estudiante>>> entry : registro.entrySet()) {
            if (entry.getValue().containsKey(asignatura) && entry.getKey().equals(grupo)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Si el grupo no existe en el registro se deberá añadir dentro de este método.
     * Incorpora una lista de estudiantes nueva asociada a una asignatura al grupo indicado.
     * @param asignatura
     * @param grupo
     */
    public void anadirAsignaturaGrupo(Asignatura asignatura, Grupo grupo) {
        if (!existeGrupo(grupo)) {
            anadirGrupo(grupo);
        }
        if (!existeAsignaturaGrupo(asignatura, grupo)){
            registro.get(grupo).put(asignatura, new ArrayList<>());
        }

    }

    /**
     * Devuelve el conjunto de asignaturas asociadas al grupo indicado.
     * @param grupo
     * @return
     */
    //    private TreeMap<Grupo, HashMap<Asignatura, ArrayList<Estudiante>>> registro;
    public HashSet<Asignatura> getAsignaturas(Grupo grupo) {
        HashSet<Asignatura> hasAsignaturas = new HashSet<>();
        if (registro.containsKey(grupo)) {
            HashMap<Asignatura, ArrayList<Estudiante>> asig = registro.get(grupo);

            // aqui el asistente me pide remplazar el .add por addAll pero
            // yo lo entiendo mejor asi,aunq igual es menos eficiente
            for (Asignatura asignatura : asig.keySet()) {
                hasAsignaturas.add(asignatura);
            }
        }
        return hasAsignaturas;
    }

    /**
     * Devuelve un conjunto de asignaturas no repetidas de todos los grupos.
     * @return
     */
    //    private TreeMap<Grupo, HashMap<Asignatura, ArrayList<Estudiante>>> registro;
    public HashSet<Asignatura> getAsignaturas() {
        HashSet<Asignatura> asignaturas = new HashSet<>();
        //aqui se itera por todos los grupos
        for (HashMap<Asignatura, ArrayList<Estudiante>> value : registro.values()) {
                //y este iter es para el hashmap de cada grupo que serian las asignaturas
            for (Asignatura asignatura : value.keySet()) {
                asignaturas.add(asignatura);
            }
        }
        return asignaturas;
    }

    /**
     * Si existe la asignatura asociada al grupo elimina la asignatura y la lista de estudiantes asociada.
     * @param asignatura
     * @param grupo
     */
    public void borrarAsignaturaGrupo(Asignatura asignatura, Grupo grupo) {
        //    private TreeMap<Grupo, HashMap<Asignatura, ArrayList<Estudiante>>> registro;
        if (existeAsignaturaGrupo(asignatura, grupo)) {
            registro.get(grupo).remove(asignatura);
        }
    }

    //endregion

    //region operaciones estudiante 31-35

    /**
     * Devuelve la lista de estudiantes de la asignatura de un grupo si existe, sino devuelve null.
     * @param asignatura
     * @param grupo
     * @return
     */
//    TreeMap<Grupo, HashMap<Asignatura, ArrayList<Estudiante>>> registro;
    public ArrayList<Estudiante> getListaEstudiantesAsignaturaGrupo(Asignatura asignatura, Grupo grupo) {
        // lo inicie como null.....me costo mucho pillar este petodo
        ArrayList<Estudiante> listaEstudiantes = null;
       if (existeAsignaturaGrupo(asignatura,grupo)){
           //aqui se coge los valores de los grupos
            HashMap<Asignatura,ArrayList<Estudiante>> listaDeAsignaturas = registro.get(grupo);
            //aqui los valores de la asignatura
            ArrayList<Estudiante> listaDeEstudiantes = listaDeAsignaturas.get(asignatura);
            if (listaDeEstudiantes !=null){
                listaEstudiantes = new ArrayList<>(listaDeEstudiantes);
            }
       }
        return listaEstudiantes;
    }

    /**
     * Si existe un/a estudiante en la asignatura de un grupo.
     * @param estudiante
     * @param asignatura
     * @param grupo
     * @return
     */
    //    TreeMap<Grupo, HashMap<Asignatura, ArrayList<Estudiante>>> registro;
    public boolean existeEstudianteAsignaturaGrupo(Estudiante estudiante, Asignatura asignatura, Grupo grupo) {
        if (existeAsignaturaGrupo(asignatura,grupo)){
            HashMap<Asignatura,ArrayList<Estudiante>> listaDeAsignaturas = registro.get(grupo);
            ArrayList<Estudiante> listaDeEstudiantes = listaDeAsignaturas.get(asignatura);
            return listaDeEstudiantes.contains(estudiante);
        }
        return false;
    }

    /**
     * Devuelve la instancia del/a estudiante asociado/a a la asignatura indicada de un grupo
     * con la nota que tiene o null si no existe.
     * @param estudiante
     * @param asignatura
     * @param grupo
     * @return
     */
    //    TreeMap<Grupo, HashMap<Asignatura, ArrayList<Estudiante>>> registro;
    public Estudiante getEstudianteAsignaturaGrupo(Estudiante estudiante, Asignatura asignatura, Grupo grupo) {
        if (existeEstudianteAsignaturaGrupo(estudiante, asignatura, grupo)){
            HashMap<Asignatura,ArrayList<Estudiante>> listaDeAsignaturas = registro.get(grupo);
            ArrayList<Estudiante> listaDeEstudiantes = listaDeAsignaturas.get(asignatura);
            for (Estudiante losEstudiante : listaDeEstudiantes) {
                if (losEstudiante.equals(estudiante)){
                        return losEstudiante;
                }
            }
        }
        return null;
    }
    /**
     * Si el grupo no existiese se debería crear en este método.
     * Análogamente, si no existe la asignatura asociada al grupo.
     * Al añadir la/el estudiante a la lista de estudiantes asociado a la asignatura del grupo,
     * si la/el estudiante ya existía se debe actualizar su nota con la que hay en el parámetro del método y
     * sino agregar una referencia a esta instancia.
     * @param estudiante
     * @param asignatura
     * @param grupo
     */
    //    private TreeMap<Grupo, HashMap<Asignatura, ArrayList<Estudiante>>> registro;
    public void anadirEstudianteAsignaturaGrupo(Estudiante estudiante, Asignatura asignatura, Grupo grupo) {
        if (!existeAsignaturaGrupo(asignatura, grupo)){
            anadirAsignaturaGrupo(asignatura, grupo);
        }
        HashMap<Asignatura,ArrayList<Estudiante>> listaDeAsignaturas = registro.get(grupo);
        ArrayList<Estudiante> listaDeEstudiantes = listaDeAsignaturas.get(asignatura);
        if (existeEstudianteAsignaturaGrupo(estudiante, asignatura, grupo)){
            for (Estudiante elEstudiante : listaDeEstudiantes) {
                if (elEstudiante.equals(estudiante)){
                    elEstudiante.setNota(estudiante.getNota());
                }
            }
        }
        listaDeEstudiantes.add(estudiante);
    }

    /**
     * Si existe la o el estudiante asociada/o a la asignatura asociada al grupo elimina a esta/este de la lista de estudiantes.
     * @param estudiante
     * @param asignatura
     * @param grupo
     */
    public void borrarEstudianteAsignaturaGrupo(Estudiante estudiante, Asignatura asignatura, Grupo grupo) {
        if (existeEstudianteAsignaturaGrupo(estudiante, asignatura, grupo)){
            HashMap<Asignatura,ArrayList<Estudiante>> listaDeAsignaturas = registro.get(grupo);
            ArrayList<Estudiante> listaDeEstudiantes = listaDeAsignaturas.get(asignatura);
            Iterator<Estudiante> it = listaDeEstudiantes.iterator();
            while (it.hasNext()){
                if (it.next().equals(estudiante)){
                    it.remove();
                }
            }
        }
    }

    //endregion

    //region listados estudiantes 41-43

    /**
     * Devuelve una lista de estudiantes no repetidos de un grupo.
     * No importa la nota del/a estudiante de qué asignatura sea (no se mostrará).
     * @param grupo
     * @return
     */
    //    private TreeMap<Grupo, HashMap<Asignatura, ArrayList<Estudiante>>> registro;
    public ArrayList<Estudiante> getEstudiantes(Grupo grupo) {
        // este metodo me costo mucho darme cuenta como hacer que devuelva null y no una lista vacia
        if (!existeGrupo(grupo)){
            anadirGrupo(grupo);
        }
        // en un principio mi idea era que devolvira un arraylist pero
        // pero aplicnado los metodos que se ve, no era necesario
//       ArrayList<Estudiante> estudiantes = null;
        for (Asignatura asignatura : registro.get(grupo).keySet()){
            ArrayList<Estudiante> listaDeEstudiantes = getListaEstudiantesAsignaturaGrupo(asignatura, grupo);
            for (Estudiante listaDeEstudiante : listaDeEstudiantes) {
                if (existeEstudianteAsignaturaGrupo(listaDeEstudiante, asignatura, grupo)) {
                    return listaDeEstudiantes;
                }
            }
        }

//        return estudiantes;
        return null;
    }

    /**
     * Devuelve una lista con todas/os las/los estudiantes que cursan una asignatura independientemente
     * del grupo al que pertenezcan. Como un/a estudiante no estará matriculado/a en distintos grupos,
     * no puede estar en la misma asignatura en distintos grupos con distinta nota.
     * La nota de las/los estudiantes será la nota de esa asignatura.
     * @param asignatura
     * @return
     */
    public ArrayList<Estudiante> getEstudiantes(Asignatura asignatura) {
        ArrayList<Estudiante> estudiantes = new ArrayList<>();
        for (Grupo grupo : registro.keySet()) {
            ArrayList<Estudiante> listaDeEstudiantes = getListaEstudiantesAsignaturaGrupo(asignatura,grupo);
            if (listaDeEstudiantes!= null) {
                for (Estudiante estudiante : listaDeEstudiantes) {
                        if (!estudiantes.contains(estudiante)) {
                            estudiantes.add(estudiante);
                        }
                }
            }
        }
        if (estudiantes.isEmpty()) {
            return null;
        }
        return estudiantes;
    }

    /**
     * Devuelve un mapa ordenado por estudiante, asociado al grupo al que pertenecen,
     * en el que aparecen las/los estudiantes que tienen una nota mayor o igual en la asignatura indicada.
     * @param asignatura
     * @param nota
     * @return
     */
    //    private TreeMap<Grupo, HashMap<Asignatura, ArrayList<Estudiante>>> registro;
    public TreeMap<Estudiante,Grupo> getEstudiantesConNotaMayorIgualQue(Asignatura asignatura, double nota) {
        TreeMap<Estudiante,Grupo> estudiantesOrdenados = new TreeMap<>();
        for (Grupo grupo : registro.keySet()) {
            HashMap<Asignatura,ArrayList<Estudiante>> listaDeAsignaturas = registro.get(grupo);
            if (listaDeAsignaturas.containsKey(asignatura)){
                ArrayList<Estudiante> estudiantes = listaDeAsignaturas.get(asignatura);
                for (Estudiante estudiante : estudiantes) {
                    if (estudiante.getNota() >= nota){
                        estudiantesOrdenados.put(estudiante,grupo);
                    }
                }
            }
        }
        if (estudiantesOrdenados.isEmpty()) {
            return null;
        }
        return estudiantesOrdenados;
    }

    //endregion

    //region distribuciones notas 51-52

    /**
     * Devuelve un mapa en el que las claves son valores del 0 al 10 y los valores el número de personas que en esa
     * asignatura en ese grupo han obtenido esa nota. El sumatorio de todos los valores debe ser el número de estudiantes
     * que hay en la lista de estudiantes de la asignatura del grupo. Por ejemplo, si nadie tiene un 0 en PROG en DAW1,
     * el valor asociado a la clave 0 será 0. Si hay 5 personas que han sacado una nota entre 8 y 9 (sin incluir el 9),
     * el valor asociado a la clave 8 será 5.
     *
     *     Opción: 51
     *     Nombre del grupo: DAW1
     *     Nombre de la asignatura: PROG
     *     |  0|  1|  2|  3|  4|  5|  6|  7|  8|  9| 10|
     *     |---|---|---|---|---|---|---|---|---|---|---|
     *     |  0|  2|  2|  3|  1|  1|  1|  1|  5|  3|  0|
     *     |---|---|---|---|---|---|---|---|---|---|---|
     *
     * @param asignatura
     * @param grupo
     * @return
     */
    //    private TreeMap<Grupo, HashMap<Asignatura, ArrayList<Estudiante>>> registro;
    public TreeMap<Integer,Integer> getDistribucionNotasAsignaturaGrupo(Asignatura asignatura, Grupo grupo) {
        TreeMap<Integer,Integer>  distribucionGrupos = new TreeMap<>();
        ArrayList<Estudiante> listaDeEstudiantes = getListaEstudiantesAsignaturaGrupo(asignatura,grupo);
        // iniciar todoo a cero
        for (int i = 0; i <= 10; i++) {
            distribucionGrupos.put(i, 0);
        }
        for (Estudiante listaDeEstudiante : listaDeEstudiantes) {
            int nota = (int) Math.floor(listaDeEstudiante.getNota());
            distribucionGrupos.put(nota, distribucionGrupos.get(nota) + 1);
        }
        //Con este no muestra las claves dont no hay 0
//        for (Estudiante listaEstudiante : listaDeEstudiantes) {
//            int nota = (int) Math.floor(listaEstudiante.getNota());
//            if (!distribucionGrupos.containsKey(nota)){
//                distribucionGrupos.put(nota,1);
//            }else {
//                int cantidadDenotasClave = distribucionGrupos.get(nota);
//                distribucionGrupos.put(nota,cantidadDenotasClave+1);
//            }
//        }
        return distribucionGrupos;
    }

    /**
     * Devuelve un mapa en el que las claves son valores del 0 al 10 y los valores el número de personas que en esa
     * asignatura considerando todos los grupos han obtenido esa nota.
     *
     *     Opción: 52
     *     Nombre de la asignatura: PROG
     *     |  0|  1|  2|  3|  4|  5|  6|  7|  8|  9| 10|
     *     |---|---|---|---|---|---|---|---|---|---|---|
     *     |  5|  8|  5|  4|  4|  7|  1|  4| 13|  7|  1|
     *     |---|---|---|---|---|---|---|---|---|---|---|
     *
     * @param asignatura
     * @return
     */
    public TreeMap<Integer,Integer> getDistribucionNotasAsignatura(Asignatura asignatura) {
        TreeMap<Integer,Integer>  distribucionAsignatura = new TreeMap<>();
        ArrayList<Estudiante> estudiantes = getEstudiantes(asignatura);
        for (int i = 0; i <=10 ; i++) {
            distribucionAsignatura.put(i, 0);
        }
        for (Estudiante estudiante : estudiantes) {
            int nota = (int) Math.floor(estudiante.getNota());
            distribucionAsignatura.put(nota, distribucionAsignatura.get(nota) + 1);
        }
        return distribucionAsignatura;
    }

    private TreeMap<Integer,Integer> acumulaDistribucionesDeNotas(TreeMap<Integer,Integer> map1, TreeMap<Integer,Integer> map2) {
        return null;
    }

    //endregion

    //region info estudiante 61-62

    /**
     * Devuelve el grupo al que pertenece el estudiante o null. Un/a estudiante sólo pertenece a un grupo,
     * si se encuentra una coincidencia se puede devolver esa.
     *
     *     Opción: 61
     *     Nombre del/a estudiante: Samantha
     *     Apellidos del/a estudiante: Oag
     *     La/El estudiante pertenece al grupo DAW1
     *
     * @param nombre
     * @param apellidos
     * @return
     */
//    TreeMap<Grupo, HashMap<Asignatura, ArrayList<Estudiante>>> registro;
    public Grupo grupoDelEstudiante(String nombre, String apellidos) {
        for (Grupo grupo : registro.keySet()) {
            ArrayList<Estudiante> estudiantes = getEstudiantes(grupo);
            for (Estudiante estudiante : estudiantes) {
                if (estudiante.getNombre().equals(nombre) && estudiante.getApellidos().equals(apellidos)) {
                    return grupo;
                }
            }
        }
        return null;
    }

    /**
     * Devuelve un mapa de asignaturas y notas obtenidas de un/a estudiante o null si no tiene.
     *
     *     Opción: 62
     *     Nombre del/a estudiante: Samantha
     *     Apellidos del/a estudiante: Oag
     *     LMGI : 5.89
     *     BADA : 5.45
     *     ING  : 6.74
     *     SIN  : 3.49
     *     ENDE : 8.90
     *     PROG : 3.70
     *
     * @param nombre
     * @param apellidos
     * @return
     */
    //    TreeMap<Grupo, HashMap<Asignatura, ArrayList<Estudiante>>> registro;
    public HashMap<Asignatura, Double> notasEstudiante(String nombre, String apellidos) {
       HashMap<Asignatura, Double> notasDelEstudiante = new HashMap<>();
       Grupo grupo = grupoDelEstudiante(nombre,apellidos);
       //esto me costo pillarlo porq me daba nullException y no caida porque, en todoo caso siento que se puede
//        hacer este codigo con menos lineas utilando los otros metodos pero no pude implementarlos
       if (grupo == null) {
           return null;
       }
       HashMap<Asignatura, ArrayList<Estudiante>> listaDeAsignaturas = registro.get(grupo);
        for (Asignatura asignatura : listaDeAsignaturas.keySet()) {
            ArrayList<Estudiante> estudiantes = listaDeAsignaturas.get(asignatura);
            for (Estudiante estudiante : estudiantes) {
                if (estudiante.getNombre().equals(nombre) && estudiante.getApellidos().equals(apellidos)) {
                    notasDelEstudiante.put(asignatura, estudiante.getNota());
                }
            }
        }
        return notasDelEstudiante;
    }

    //endregion

}


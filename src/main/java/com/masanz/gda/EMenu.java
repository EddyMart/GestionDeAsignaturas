package com.masanz.gda;

public enum EMenu {

    SALIR(0),
    EXISTE_GRUPO(11), ANADIR_GRUPO(12),
    LISTAR_GRUPOS(13), BORRAR_GRUPO(14),
    EXISTE_ASIGNATURA_GRUPO(21), ANADIR_ASIGNATURA_GRUPO(22),
    LISTAR_ASIGNATURAS_GRUPO(23), BORRAR_ASIGNATURA_GRUPO(24),
    LISTAR_ASIGNATURAS_TODAS(25),
    LISTAR_ESTUDIANTES_ASIGNATURA_GRUPO(31), EXISTE_ESTUDIANTE_ASIGNATURA_GRUPO(32),
    CONSULTAR_ESTUDIANTE_ASIGNATURA_GRUPO(33), ANADIR_ESTUDIANTE_ASIGNATURA_GRUPO(34), BORRAR_ESTUDIANTE_ASIGNATURA_GRUPO(35),
    LISTAR_ESTUDIANTES_GRUPO(41), LISTAR_ESTUDIANTES_ASIGNATURA(42), LISTAR_ESTUDIANTES_NOTA(43),
    DISTRIBUCION_NOTAS_ASIGNATURA_GRUPO(51), DISTRIBUCION_NOTAS_ASIGNATURA(52),
    GRUPO_ALUMNO(61), NOTAS_ALUMNO(62);

    private int numero;

    EMenu(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public static EMenu fromNumero(int numero) {
        return switch (numero) {
            case 0 -> SALIR;
            case 11 -> EXISTE_GRUPO;
            case 12 -> ANADIR_GRUPO;
            case 13 -> LISTAR_GRUPOS;
            case 14 -> BORRAR_GRUPO;
            case 21 -> EXISTE_ASIGNATURA_GRUPO;
            case 22 -> ANADIR_ASIGNATURA_GRUPO;
            case 23 -> LISTAR_ASIGNATURAS_GRUPO;
            case 24 -> BORRAR_ASIGNATURA_GRUPO;
            case 25 -> LISTAR_ASIGNATURAS_TODAS;
            case 31 -> LISTAR_ESTUDIANTES_ASIGNATURA_GRUPO;
            case 32 -> EXISTE_ESTUDIANTE_ASIGNATURA_GRUPO;
            case 33 -> CONSULTAR_ESTUDIANTE_ASIGNATURA_GRUPO;
            case 34 -> ANADIR_ESTUDIANTE_ASIGNATURA_GRUPO;
            case 35 -> BORRAR_ESTUDIANTE_ASIGNATURA_GRUPO;
            case 41 -> LISTAR_ESTUDIANTES_GRUPO;
            case 42 -> LISTAR_ESTUDIANTES_ASIGNATURA;
            case 43 -> LISTAR_ESTUDIANTES_NOTA;
            case 51 -> DISTRIBUCION_NOTAS_ASIGNATURA_GRUPO;
            case 52 -> DISTRIBUCION_NOTAS_ASIGNATURA;
            case 61 -> GRUPO_ALUMNO;
            case 62 -> NOTAS_ALUMNO;
            default -> null;
        };
    }

}

package Entidades;

public class Facultad {
    private int idFacultad;
    private String sigla, nombre;

    public Facultad(int idFacultad, String sigla, String nombre) {
        this.idFacultad = idFacultad;
        this.sigla = sigla;
        this.nombre = nombre;
    }

    public int getIdFacultad() {
        return idFacultad;
    }

    public void setIdFacultad(int idFacultad) {
        this.idFacultad = idFacultad;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

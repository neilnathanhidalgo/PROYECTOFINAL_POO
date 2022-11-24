package Entidades;

public class Persona {
    private int idPersona;
    private int idTipo;
    private int idFacultad;
    private int sexo;
    private String fechaNac;

    public Persona(int idPersona, int idTipo, int idFacultad, int sexo, String fechaNac) {
        this.idPersona = idPersona;
        this.idTipo = idTipo;
        this.idFacultad = idFacultad;
        this.sexo = sexo;
        this.fechaNac = fechaNac;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public int getIdFacultad() {
        return idFacultad;
    }

    public void setIdFacultad(int idFacultad) {
        this.idFacultad = idFacultad;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }
}

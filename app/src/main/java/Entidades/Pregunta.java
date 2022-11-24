package Entidades;

public class Pregunta {
    private int idPregunta;
    private String Pregunta;


    public Pregunta(int idPregunta, String pregunta) {

        this.idPregunta = idPregunta;
        Pregunta = pregunta;
    }

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getPregunta() {
        return Pregunta;
    }

    public void setPregunta(String pregunta) {
        Pregunta = pregunta;
    }
}


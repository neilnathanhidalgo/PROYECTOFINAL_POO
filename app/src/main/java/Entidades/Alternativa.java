package Entidades;

public class Alternativa {
    private int idAlternativa;
    private int idPregunta;
    private String Alternativas;

    public Alternativa(int idAlternativa, int idPregunta, String alternativas) {
        this.idAlternativa = idAlternativa;
        this.idPregunta = idPregunta;
        Alternativas = alternativas;
    }

    public int getIdAlternativa() {
        return idAlternativa;
    }

    public void setIdAlternativa(int idAlternativa) {
        this.idAlternativa = idAlternativa;
    }

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getAlternativas() {
        return Alternativas;
    }

    public void setAlternativas(String alternativas) {
        Alternativas = alternativas;
    }
}

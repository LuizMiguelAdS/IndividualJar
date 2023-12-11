package Classes;

import java.util.Date;

public class Player {
    private String Nome;
    private Object Tempo;

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public Object getTempo() {
        return Tempo;
    }

    public void setTempo(Object tempo) {
        Tempo = tempo;
    }

    @Override
    public String toString() {
        return "Player{" +
                "Nome='" + Nome + '\'' +
                ", Tempo=" + Tempo +
                '}';
    }
}

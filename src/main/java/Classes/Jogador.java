package Classes;

public class Jogador {

    private String nickName;

    public Jogador(String nickName) {
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "Jogador{" +
                "nickName='" + nickName + '\'' +
                '}';
    }
}

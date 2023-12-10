import java.util.List;
import java.util.Scanner;

public class Jar {
    static Scanner inLine = new Scanner(System.in);
    static Scanner inInt = new Scanner(System.in);
    static AcessoJDBC acesso = new AcessoJDBC();
    static ScriptInsercao insercao = new ScriptInsercao();

    static Scanner nick = new Scanner(System.in);
    static Scanner escolhaDoAdmin = new Scanner(System.in);


    public static void main(String[] args) {
        System.out.println("// ByteGuard //");
        System.out.println("// Faça login //");
        System.out.println("Insira o código de acesso de sua lanhouse: ");
        Integer idLanhouse = acesso.obterIdLanhousePorCodigo(inLine.nextLine());
        while (idLanhouse.equals(0)) {
            System.out.println("Lanhouse não encontrada, insira o código novamente");
            idLanhouse = acesso.obterIdLanhousePorCodigo(inLine.nextLine());
        }

        System.out.println("Insira o e-mail");
        String email = inLine.nextLine();
        System.out.println("Insira a sua senha");
        String senha = inLine.nextLine();
        while (!acesso.verificarLogin(email, senha)) {
            System.out.println("Usuário não encontrado, tente novamente: ");
            System.out.println("Insira o e-mail");
            email = inLine.nextLine();
            System.out.println("Insira a sua senha");
            senha = inLine.nextLine();
        }
        String nickname;
        String resposta;

        do {
            System.out.println("Digite seu NickName");
            nickname = nick.nextLine();
            System.out.println("Seu nick é esse S/N: " + nickname);
            resposta = nick.nextLine();

        } while (resposta != "S");

        String nomeUsuario = acesso.buscarUsuario(email, senha);
        System.out.println(String.format("""
                =================== Bem vindo(a) " %s " ==================
                ===================================================================
                |                       Escolha uma opção:                        |
                |=================================================================|
                |1- Cadastrar/Localizar componentes e começar a busca de dados    |
                |2- Mudar de conta                                                |
                |3- Sair                                                          |
                |4- Configurações                                                 |
                ===================================================================
                """, nomeUsuario));

        switch (inInt.nextInt()) {
            case 1:
                // '''''''''''''''''''''''''''''''''''''''''''''''''''''''''//
                insercao.cadastrarNicknome(nickname);
                //'''''''''''''''''''''''''''''''''''''''''''''''''''''''''//

                Integer idMaquina = insercao.cadastroMaquina(idLanhouse);

                List<Integer> idsComponentes = insercao.cadastrarComponentes(idMaquina);

                insercao.inserirDados(idsComponentes.get(0), idsComponentes.get(1), idsComponentes.get(2), idsComponentes.get(3), idsComponentes.get(4), idMaquina, idLanhouse);
                break;
            case 2:
                main(null);
                break;
            case 3:
                System.exit(0);
                break;
            case 4:
                Integer escAdmin = 0;

            do {
                System.out.println("""
                        ===================================================================
                        |                       Escolha uma opção:                        |
                        |=================================================================|
                        | 1 - Acessar Historico de Players                                |
                        | 2 - Acessar Historico deste Player                              |
                        | 3 - Cadastrar/Localizar componentes e começar a busca de dados  |
                        | 4 - Mudar de conta                                              |
                        | 5 - Sair                                                        |
                        ===================================================================
                        """);
                escAdmin = escolhaDoAdmin.nextInt();
                if (escAdmin == 1) {
                    acesso.obterJogador();
                } else if (escAdmin == 2) {
                    acesso.obterJogador(nickname);
                } else if (escAdmin == 3) {

                    // '''''''''''''''''''''''''''''''''''''''''''''''''''''''''//
                    insercao.cadastrarNicknome(nickname);
                    //'''''''''''''''''''''''''''''''''''''''''''''''''''''''''//

                    idMaquina = insercao.cadastroMaquina(idLanhouse);

                    idsComponentes = insercao.cadastrarComponentes(idMaquina);

                    insercao.inserirDados(idsComponentes.get(0), idsComponentes.get(1), idsComponentes.get(2), idsComponentes.get(3), idsComponentes.get(4), idMaquina, idLanhouse );

                } else if (escAdmin == 4) {
                    main(null);
                } else if (escAdmin == 5) {
                    System.exit(0);
                }

        }while (escAdmin <6);
                break;
        }
    }
}

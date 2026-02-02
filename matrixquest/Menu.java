package matrixquest;
import java.util.Scanner;

public class Menu {
    private Scanner sc = new Scanner(System.in);
    private JogoLogica jogar = new JogoLogica();
    private int opcao;
    private boolean matrizPreenchida = false;

    public void iniciar() {

        System.out.println("███╗   ███╗ █████╗ ████████╗██████╗ ██╗██╗  ██╗");
        System.out.println("████╗ ████║██╔══██╗╚══██╔══╝██╔══██╗██║╚██╗██╔╝");
        System.out.println("██╔████╔██║███████║   ██║   ██████╔╝██║ ╚███╔╝ ");
        System.out.println("██║╚██╔╝██║██╔══██║   ██║   ██╔══██╗██║ ██╔██╗ ");
        System.out.println("██║ ╚═╝ ██║██║  ██║   ██║   ██║  ██║██║██╔╝ ██╗");
        System.out.println("╚═╝     ╚═╝╚═╝  ╚═╝   ╚═╝   ╚═╝  ╚═╝╚═╝╚═╝  ╚═╝");
        System.out.println();
        System.out.println(" ██████╗ ██╗   ██╗███████╗███████╗████████╗");
        System.out.println("██╔═══██╗██║   ██║██╔════╝██╔════╝╚══██╔══╝");
        System.out.println("██║   ██║██║   ██║█████╗  ███████╗   ██║   ");
        System.out.println("██║▄▄ ██║██║   ██║██╔══╝  ╚════██║   ██║   ");
        System.out.println("╚██████╔╝╚██████╔╝███████╗███████║   ██║   ");
        System.out.println(" ╚══▀▀═╝  ╚═════╝ ╚══════╝╚══════╝   ╚═╝   ");

        System.out.println("==============================================");
        System.out.println("              🎮 PRESS START 🎮              ");
        System.out.println("==============================================");
        System.out.println("PRESSIONE QUALQUER TECLA E ENTER PARA INICIAR");
        System.out.println("==============================================");
        sc.nextLine();

        do {
            System.out.println("========= MENU =========");
            System.out.println("1 - Preencher a matriz");
            System.out.println("2 - Corrigir algum número");
            System.out.println("3 - Conferir resultado");
            System.out.println("4 - Novo Jogo");
            System.out.println("0 - Sair");
            System.out.println("========================");
            System.out.print("Escolha uma opção: ");
            
            try {
                String entrada = sc.next();
                opcao = Integer.parseInt(entrada);

                switch (opcao) {
                    case 1:
                        jogar.colherDados();
                        matrizPreenchida = true;
                        break;
                    case 2:
                        if (matrizPreenchida) jogar.corrigirDigito();
                        else System.out.println("Preencha a matriz primeiro!");
                        break;
                    case 3:
                        if (matrizPreenchida) jogar.conferirResultadoMatriz();
                        else System.out.println("Matriz vazia!");
                        break;
                    case 4:
                        //tirei só a mensagem, tava redundante.
                        //quando começa um novo jogo já limpa a matriz e, claro, começa um novo.
                        jogar.novoJogo();
                        matrizPreenchida = false;
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Digite apenas os números do menu.");
                opcao = -1;
            }// tudo que foi feito aqui foi um tratamento de erro, também em outras partes do código.
            //não altera a lógica.
        } while (opcao != 0);
    }
}
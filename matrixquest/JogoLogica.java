package matrixquest;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.InputMismatchException;

public class JogoLogica {
    
    private int[][] matriz = new int[3][3];
    //alterei um pouco a lógica do sorteio do número, pois não é possível preencher sem ser múltiplo de 3 
    protected int numeroSorteado = ThreadLocalRandom.current().nextInt(5, 34) * 3;
    private Scanner sc = new Scanner(System.in);
    
    private int somaColuna1, somaColuna2, somaColuna3;
    private int somaLinha1, somaLinha2, somaLinha3;
    
    public JogoLogica() {}
    //função pra verificar se tem número repetido
    private boolean isRepetido(int valor) {
        if (valor == 0) return false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matriz[i][j] == valor) return true;
            }
        }
        return false;
    }

    public void mostrarMatriz(int[][] matriz) {
        System.out.println("\n   1 2 3 (Colunas)");
        for(int i=0; i<3; i++){
            System.out.print((i+1) + "  "); 
            for (int j=0; j<3; j++){
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public void colherDados() {
        System.out.println("\nObjetivo: Todas as linhas e colunas devem somar " + numeroSorteado);
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                matriz[i][j] = pedirNumeroValido("Posição ["+(i+1)+"]["+(j+1)+"]: ");
                mostrarMatriz(matriz);
            }
        }
    }

    private int pedirNumeroValido(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                int valor = sc.nextInt();
                if (isRepetido(valor)) {
                    System.out.println("Erro: O número " + valor + " já está na matriz. Tente outro!");
                } else {
                    return valor;
                }
            } catch (InputMismatchException e) {
                System.out.println("Digite apenas números inteiros!");
                sc.next(); 
            }
        }
    }

    public int calcularDeterminante() {
        int principal = (matriz[0][0] * matriz[1][1] * matriz[2][2]) 
                      + (matriz[0][1] * matriz[1][2] * matriz[2][0]) 
                      + (matriz[0][2] * matriz[1][0] * matriz[2][1]);

        int secundaria = (matriz[0][2] * matriz[1][1] * matriz[2][0]) 
                       + (matriz[0][0] * matriz[1][2] * matriz[2][1]) 
                       + (matriz[0][1] * matriz[1][0] * matriz[2][2]);

        return principal - secundaria;
    }

    public void conferirResultadoMatriz() {
        somaLinha1 = matriz[0][0] + matriz[0][1] + matriz[0][2];
        somaLinha2 = matriz[1][0] + matriz[1][1] + matriz[1][2];
        somaLinha3 = matriz[2][0] + matriz[2][1] + matriz[2][2];

        somaColuna1 = matriz[0][0] + matriz[1][0] + matriz[2][0];
        somaColuna2 = matriz[0][1] + matriz[1][1] + matriz[2][1];
        somaColuna3 = matriz[0][2] + matriz[1][2] + matriz[2][2];

        int det = calcularDeterminante();

        boolean somasOk = (somaColuna1 == numeroSorteado && somaColuna2 == numeroSorteado && somaColuna3 == numeroSorteado &&
                           somaLinha1 == numeroSorteado && somaLinha2 == numeroSorteado && somaLinha3 == numeroSorteado);

        if (somasOk) {
            if (det != numeroSorteado) {
                System.out.println("PARABÉNS! Você venceu o Matrix Quest!");
                //System.out.println("Determinante final: " + det);
                //não entendi muito bem como era pra deixar essa parte da determinante, então deixei essas duas linhas aí.
                //se achar melhor a linha anterior com o valor da determinante, só tirar o comentário e apagar esse print.
                System.out.println("O resultado era: " + numeroSorteado);
            } else {
                System.out.println("Quase! Somas perfeitas, mas o determinante foi igual ao número sorteado (" + det + ").");
                System.out.println("Troque a posição de alguns números para ganhar!");
            }
        } else {
            System.out.println("Somas incorretas. O objetivo é " + numeroSorteado);
        }
    }

    public void novoJogo() {
        matriz = new int[3][3];
        //mudei a lógica aqui também, mesma coisa do primeiro número sorteado
        numeroSorteado = ThreadLocalRandom.current().nextInt(5, 34) * 3;
        System.out.println("Jogo Resetado! Novo número: " + numeroSorteado);
    }

    public void corrigirDigito() {
        try {
            System.out.print("Linha (1-3): ");
            int l = sc.nextInt() - 1;
            System.out.print("Coluna (1-3): ");
            int c = sc.nextInt() - 1;
            
            int valorAntigo = matriz[l][c];
            matriz[l][c] = 0;
            
            System.out.print("Novo valor: ");
            int novoValor = sc.nextInt();

            if (isRepetido(novoValor)) {
                System.out.println("Esse número já existe em outra posição!");
                matriz[l][c] = valorAntigo;
            } else {
                matriz[l][c] = novoValor;
                mostrarMatriz(matriz);
            }
        } catch (Exception e) {
            System.out.println("Erro na correção.");
            sc.next();
        }
    }
}
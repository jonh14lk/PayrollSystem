package src.utils;

import java.util.Scanner;

public class Utils {
    public static Scanner scan = new Scanner(System.in);

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void printHelp() {
        System.out.println("[0] - Ajuda");
        System.out.println("[1] - Adicionar empregado");
        System.out.println("[2] - Remoção de um empregado");
        System.out.println("[3] - Lançar um cartão de ponto");
        System.out.println("[4] - Lançar uma resultado venda");
        System.out.println("[5] - Lançar uma taxa de serviço");
        System.out.println("[6] - Alterar detalhes de um empregado");
        System.out.println("[7] - Exibir empregados");
        System.out.println("[8] - Sair da aplicação");
        System.out.println("");
    }
}

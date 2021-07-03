import src.Employees;
import src.Utils;

public class Main {
  public static void main(String[] args) {
    Employees employees = new Employees();
    Utils.clear_screen();

    while (true) {
      System.out.println("Para obter uma lista com os comandos, digite 0");
      System.out.println("Digite um comando:\n");

      int command = Utils.scan.nextInt();
      boolean can_quit = false;

      switch (command) {
        case 0:
          Utils.print_help();
          break;
        case 1:
          employees.create_employee();
          break;
        case 2:
          employees.remove_employee();
          break;
        case 3:
          System.out.println("Lançar cartão de ponto\n");
          break;
        case 4:
          System.out.println("Lançar um resultado venda\n");
          break;
        case 5:
          System.out.println("Lançar taxa de serviço\n");
          break;
        case 6:
          employees.edit_employee();
          break;
        case 7:
          System.out.println("Saindo...\n");
          can_quit = true;
          break;
        case -999:
          employees.print_employees();
          break;
        default:
          System.out.println("Comando não disponivel\n");
          break;
      }

      if (can_quit) {
        break;
      }
    }
  }
}
// TODO: tipo ta sempre saindo = 0
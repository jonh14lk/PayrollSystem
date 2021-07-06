import src.Employees;
import src.Utils;

public class App {
    App() {
        Employees employees = new Employees();
        Utils.clear_screen();

        while (true) {
            System.out.println("Para obter uma lista com os comandos, digite 0");
            System.out.println("Digite um comando:\n");

            int command = Utils.scan.nextInt();
            Utils.scan.nextLine();
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
                    employees.throw_time_card();
                    break;
                case 4:
                    employees.add_sale();
                    break;
                case 5:
                    System.out.println("Lançar taxa de serviço\n");
                    break;
                case 6:
                    employees.edit_employee();
                    break;
                case 7:
                    employees.print_employees();
                    break;
                case 8:
                    System.out.println("Saindo...\n");
                    can_quit = true;
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

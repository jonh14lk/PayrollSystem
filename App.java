import src.Company;
import src.Utils;

public class App {
    App() {
        Company company = new Company();
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
                    company.create_employee();
                    break;
                case 2:
                    company.remove_employee();
                    break;
                case 3:
                    company.throw_time_card();
                    break;
                case 4:
                    company.add_sale();
                    break;
                case 5:
                    company.add_service_charge();
                    break;
                case 6:
                    company.edit_employee();
                    break;
                case 7:
                    company.print_employees();
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

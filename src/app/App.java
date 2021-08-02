package src.app;

import src.controllers.Company;
import src.utils.Utils;

public class App {
    public App() {
        Company company = new Company();
        Utils.clearScreen();

        while (true) {
            System.out.println("Para obter uma lista com os comandos, digite 0");
            System.out.println("Digite um comando:\n");

            int command = Utils.readInt();
            boolean can_quit = false;

            switch (command) {
                case 0:
                    Utils.printHelp();
                    break;
                case 1:
                    company.createEmployee();
                    break;
                case 2:
                    company.removeEmployee();
                    break;
                case 3:
                    company.throwTimeCard();
                    break;
                case 4:
                    company.addSale();
                    break;
                case 5:
                    company.addServiceCharge();
                    break;
                case 6:
                    company.editEmployee();
                    break;
                case 7:
                    company.printEmployees();
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

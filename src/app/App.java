package src.app;

import src.controllers.Company;
import src.utils.Utils;
import java.util.Stack;

public class App {
    public void run() {
        Utils.clearScreen();
        Company company = new Company();
        Stack<String> stack = new Stack<String>();

        while (true) {
            int command = Utils.readCommand();
            boolean can_quit = false;

            if (command != 8 && command <= 10) {
                Utils.addCompany(stack, company);
            }

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
                    company.RunPayroll();
                    break;
                case 8:
                    company = Utils.undo(stack);
                    break;
                case 9:
                    company.changePaymentSchedule();
                    break;
                case 10:
                    company.addPaymentSchedule();
                    break;
                case 11:
                    company.printEmployees();
                    break;
                case 12:
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

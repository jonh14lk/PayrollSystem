package src.app;

import src.controllers.Company;
import src.utils.Utils;
import java.util.Stack;

public class App {
    public void run() {
        Utils.clearScreen();
        Company company = new Company();
        Stack<Company> stack = new Stack<Company>();

        while (true) {
            int command = Utils.readCommand();
            boolean can_quit = false;

            if (command >= 1 && command <= 7) {
                Company previous = new Company(company);
                stack.push(previous);
                System.out.println(stack.peek().current_id);
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
                    this.undoRedo(stack, company); // not working, needs deep copy
                    break;
                case 9:
                    company.printEmployees();
                    break;
                case 10:
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

    public void undoRedo(Stack<Company> stack, Company company) {
        if (!stack.empty()) {
            company = stack.peek();
            stack.pop();
            System.out.println("Undo realizado com sucesso");
        } else {
            System.out.println("Operação não pode ser realizada");
        }
    }
}

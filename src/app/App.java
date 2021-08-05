package src.app;

import java.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
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

            if (command >= 1 && command <= 7) {
                this.addCompany(stack, company);
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
                    company = undo(stack);
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

    public void addCompany(Stack<String> stack, Company company) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(company);
            oos.close();
            baos.close();
            String to_store = Base64.getEncoder().encodeToString(baos.toByteArray());
            stack.push(to_store);
        } catch (Exception exception) {
            System.out.println("Erro ao serializar");
        }
    }

    public Company undo(Stack<String> stack) {
        if (stack.empty()) {
            System.out.println("Operação não pode ser realizada");
            return null;
        }

        String stored = stack.peek();
        stack.pop();

        try {
            byte[] decoded = Base64.getDecoder().decode(stored);
            ByteArrayInputStream bais = new ByteArrayInputStream(decoded);
            ObjectInputStream ois = new ObjectInputStream(bais);
            System.out.println("Undo realizado com sucesso");
            return (Company) ois.readObject();
        } catch (Exception exception) {
            System.out.println("Erro ao deserializar");
            return null;
        }
    }
}

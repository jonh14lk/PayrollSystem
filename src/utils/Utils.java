package src.utils;

import java.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import src.controllers.Company;
import src.models.payment.Schedule;
import java.util.Stack;
import java.util.Calendar;
import java.util.Scanner;

public class Utils {
    public static Scanner scan = new Scanner(System.in);

    public static int readInt() {
        int x = scan.nextInt();
        scan.nextLine();
        return x;
    }

    public static double readDouble() {
        double x = scan.nextDouble();
        scan.nextLine();
        return x;
    }

    public static String readString() {
        String x = scan.nextLine();
        return x;
    }

    public static int readCommand() {
        System.out.println("\nPara obter uma lista com os comandos, digite 0");
        System.out.println("Digite um comando:\n");
        int command = readInt();
        return command;
    }

    public static String readName() {
        System.out.println("Nome do funcionario:");
        String name = readString();
        return name;
    }

    public static String readAddress() {
        System.out.println("Endereço do funcionario:");
        String address = readString();
        return address;
    }

    public static int readEmployeeType() {
        System.out.println("Digite o tipo de funcionario a ser cadastrado:");
        System.out.println("[1] - Horista");
        System.out.println("[2] - Assalariado");
        System.out.println("[3] - Comissionado");
        int type = readInt();
        return type;
    }

    public static int readFromSyndicate() {
        System.out.println("O funcionário pertence ao sindicato?");
        System.out.println("[0] - Não");
        System.out.println("[1] - Sim");
        int from_syndicate = readInt();
        return from_syndicate;
    }

    public static int readId() {
        System.out.println("Id do funcionario:");
        int id = readInt();
        return id;
    }

    public static int readHours() {
        System.out.println("Digite a quantidade de horas trabalhadas:");
        int hours = readInt();
        return hours;
    }

    public static double readSale() {
        System.out.println("Digite o valor da venda:");
        double value = readDouble();
        return value;
    }

    public static double readPercentage() {
        System.out.println("Digite o percentual destinado ao funcionario:");
        double percentage = readDouble();
        return percentage;
    }

    public static double readServiceCharge() {
        System.out.println("Digite o valor da taxa de serviço:");
        double charge = readDouble();
        return charge;
    }

    public static double readSalary() {
        System.out.println("Digite o salario do funcionário:");
        double salary = readDouble();
        return salary;
    }

    public static int readPaymentType() {
        System.out.println("Digite o tipo de pagamento do funcionário:");
        System.out.println("[1] - Cheque pelos correios");
        System.out.println("[2] - Cheque em mãos");
        System.out.println("[3] - Depósito em conta bancária");
        int type = readInt();
        return type;
    }

    public static Calendar readDate() {
        System.out.println("Digite o dia:");
        int day = readInt();
        System.out.println("Digite o mês:");
        int month = readInt();
        month--;
        System.out.println("Digite o ano:");
        int year = readInt();
        Calendar x = Calendar.getInstance();
        x.set(Calendar.YEAR, year);
        x.set(Calendar.MONTH, month);
        x.set(Calendar.DAY_OF_MONTH, day);
        return x;
    }

    public static Schedule readPaymentSchedule() {
        System.out.println("Digite o tipo de agenda de pagamento");
        String payment_schedule = readString();
        return parsePaymentSchedule(payment_schedule);
    }

    public static Schedule parsePaymentSchedule(String str) {
        String[] parsed = str.split(" ");
        Schedule payment_schedule = new Schedule();
        if (parsed.length == 2 && parsed[0].equals("mensal")) {
            payment_schedule.setTimeGap(parsed[0]);
            if (parsed[1].equals("$")) {
                payment_schedule.setDay(50);
            } else {
                payment_schedule.setDay(Integer.parseInt(parsed[1]));
            }
        } else if (parsed.length == 3 && parsed[0].equals("semanal")) {
            payment_schedule.setTimeGap(parsed[0]);
            payment_schedule.setDay(Integer.parseInt(parsed[1]));
            payment_schedule.setWeekDay(parsed[2]);
        }
        return payment_schedule;
    }

    public static void printHelp() {
        System.out.println("[0] - Ajuda");
        System.out.println("[1] - Adicionar empregado");
        System.out.println("[2] - Remoção de um empregado");
        System.out.println("[3] - Lançar um cartão de ponto");
        System.out.println("[4] - Lançar uma resultado venda");
        System.out.println("[5] - Lançar uma taxa de serviço");
        System.out.println("[6] - Alterar detalhes de um empregado");
        System.out.println("[7] - Rodar folha de pagamento");
        System.out.println("[8] - Undo");
        System.out.println("[9] - Mudar agenda de pagamento");
        System.out.println("[10] - Adicionar agenda de pagamento");
        System.out.println("[11] - Exibir empregados");
        System.out.println("[12] - Sair da aplicação\n");
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static boolean compareDates(Calendar a, Calendar b) {
        if (a.get(Calendar.YEAR) != b.get(Calendar.YEAR)) {
            return (a.get(Calendar.YEAR) > b.get(Calendar.YEAR));
        }
        if (a.get(Calendar.MONTH) != b.get(Calendar.MONTH)) {
            return (a.get(Calendar.MONTH) > b.get(Calendar.MONTH));
        }
        if (a.get(Calendar.DAY_OF_MONTH) != b.get(Calendar.DAY_OF_MONTH)) {
            return (a.get(Calendar.DAY_OF_MONTH) > b.get(Calendar.DAY_OF_MONTH));
        }
        return true;
    }

    public static int dateDiff(Calendar a, Calendar b) {
        int diff = 0;
        while (compareDates(a, b) == false) {
            a.add(Calendar.DAY_OF_MONTH, 1);
            diff++;
        }
        a.add(Calendar.DAY_OF_MONTH, -diff);
        return diff;
    }

    public static boolean LastBussinessDay(Calendar a) {
        if (a.get(Calendar.DAY_OF_MONTH) == Calendar.SATURDAY || a.get(Calendar.DAY_OF_MONTH) == Calendar.SUNDAY) {
            return false;
        }
        int added = 1;
        int month = a.get(Calendar.MONTH);
        a.add(Calendar.DAY_OF_MONTH, 1);
        while (a.get(Calendar.DAY_OF_MONTH) == Calendar.SATURDAY || a.get(Calendar.DAY_OF_MONTH) == Calendar.SUNDAY) {
            added++;
            a.add(Calendar.DAY_OF_MONTH, 1);
        }
        int new_month = a.get(Calendar.MONTH);
        a.add(Calendar.DAY_OF_MONTH, -added);
        return (month != new_month);
    }

    public static void addCompany(Stack<String> stack, Company company) {
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

    public static Company undo(Stack<String> stack) {
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

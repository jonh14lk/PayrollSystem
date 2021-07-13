package src;

import java.util.HashMap;
import java.util.Map;

public class Company {
    private HashMap<Integer, Employee> employees;
    Syndicate syndicate;
    private int current_id;

    public Company() {
        this.employees = new HashMap<>();
        this.syndicate = new Syndicate();
        this.current_id = 0;
    }

    public boolean createEmployee() {
        System.out.println("Nome do funcionario:");
        String name = Utils.scan.nextLine();

        System.out.println("Endereço do funcionario:");
        String address = Utils.scan.nextLine();

        System.out.println("Digite o tipo de funcionario a ser cadastrado:");
        System.out.println("Digite 1 para horista");
        System.out.println("Digite 2 para assalariado");
        System.out.println("Digite 3 para comissionado");
        int type = Utils.scan.nextInt();
        Utils.scan.nextLine();

        Employee employee = new Employee();

        if (!employee.setType(type)) {
            System.out.println("O tipo de funcionario digitado não existe");
            System.out.println("O funcionario não pode ser criado!\n");
            return false;
        }

        System.out.println("Caso o funcionario pertença ao sindicato, Digite 1");
        System.out.println("Caso contrário, digite 0");
        int from_syndicate = Utils.scan.nextInt();
        Utils.scan.nextLine();

        if (from_syndicate != 0 && from_syndicate != 1) {
            System.out.println("Entrada Invalida");
            System.out.println("O funcionario não pode ser criado!\n");
            return false;
        }

        employee = new Employee(name, address, ++this.current_id, type, from_syndicate, this.syndicate);
        this.employees.put(employee.id, employee);

        System.out.println("Funcionario criado com sucesso!");
        System.out.println("Id do funcionario criado:" + employee.id);
        if (employee.getSyndicate(this.syndicate)) {
            System.out.println("Id do funcionario criado no sindicato:" + employee.getSyndicateEmployeeId());
        }

        return true;
    }

    public boolean removeEmployee() {
        System.out.println("Id do funcionario:");
        int id = Utils.scan.nextInt();
        Utils.scan.nextLine();

        if (!this.employees.containsKey(id)) {
            System.out.println("O id do funcionario não existe");
            System.out.println("O funcionario não pode ser removido!\n");
            return false;
        }

        Employee employee = this.employees.get(id);

        this.syndicate.removeSyndicateEmployee(employee.getSyndicateEmployeeId());
        this.employees.remove(id);
        System.out.println("Funcionario removido com sucesso!\n");

        return true;
    }

    public boolean editEmployee() {
        System.out.println("Id do funcionario:");
        int id = Utils.scan.nextInt();
        Utils.scan.nextLine();

        if (!this.employees.containsKey(id)) {
            System.out.println("O id do funcionario não existe");
            System.out.println("O funcionario não pode ser editado!\n");
            return false;
        }

        Employee employee = this.employees.get(id);

        System.out.println("Funcionario encontrado!");
        System.out.println("Informações atuais:");
        employee.printEmployee();

        System.out.println("Nome do funcionario:");
        String name = Utils.scan.nextLine();

        System.out.println("Endereço do funcionario:");
        String address = Utils.scan.nextLine();

        System.out.println("Digite o tipo de funcionario a ser cadastrado:");
        System.out.println("Digite 1 para horista");
        System.out.println("Digite 2 para assalariado");
        System.out.println("Digite 3 para comissionado");
        int type = Utils.scan.nextInt();
        Utils.scan.nextLine();

        if (!employee.setType(type)) {
            System.out.println("O tipo de funcionario digitado não existe");
            System.out.println("O funcionario não pode ser editado!\n");
            return false;
        }

        int from_syndicate = 1;

        if (!employee.getSyndicate(syndicate)) {
            System.out.println("Caso queria adicionar o funcionario ao sindicato, Digite 1");
            System.out.println("Caso contrário, digite 0");
            from_syndicate = Utils.scan.nextInt();
            Utils.scan.nextLine();
            if (from_syndicate != 0 && from_syndicate != 1) {
                System.out.println("Entrada Invalida");
                System.out.println("O funcionario não pode ser criado!\n");
                return false;
            }
        }

        employee = new Employee(name, address, id, type, from_syndicate, this.syndicate);
        System.out.println("Funcionario editado com sucesso!\n");

        return true;
    }

    public boolean throwTimeCard() {
        System.out.println("Id do funcionario:");
        int id = Utils.scan.nextInt();
        Utils.scan.nextLine();

        if (!this.employees.containsKey(id)) {
            System.out.println("O id do funcionario não existe");
            System.out.println("O cartão de ponto não pode ser adicionado!\n");
            return false;
        }

        Employee employee = this.employees.get(id);

        if (!employee.getHourly()) {
            System.out.println("O funcionario não é horista");
            System.out.println("O cartão de ponto não pode ser adicionado!\n");
            return false;
        }

        System.out.println("Digite a quantidade de horas trabalhadas:");
        int hours = Utils.scan.nextInt();
        Utils.scan.nextLine();

        if (!employee.addHours(hours)) {
            System.out.println("A quantidade de horas não pode ser negativa");
            System.out.println("O cartão de ponto não pode ser adicionado!\n");
            return false;
        }

        System.out.println("Cartão de ponto adicionado com sucesso!\n");
        return true;
    }

    public boolean addSale() {
        System.out.println("Id do funcionario:");
        int id = Utils.scan.nextInt();
        Utils.scan.nextLine();

        if (!this.employees.containsKey(id)) {
            System.out.println("O id do funcionario não existe");
            System.out.println("A venda não pode ser adicionada!\n");
            return false;
        }

        Employee employee = this.employees.get(id);

        if (!employee.getComissioned()) {
            System.out.println("O funcionario não é comissiondo");
            System.out.println("A venda não pode ser adicionada!\n");
            return false;
        }

        System.out.println("Digite o valor da venda:");
        double value = Utils.scan.nextDouble();
        Utils.scan.nextLine();

        if (value < 0.0) {
            System.out.println("O valor não pode ser negativo");
            System.out.println("A venda não pode ser adicionada!\n");
            return false;
        }

        System.out.println("Digite o percentual destinado ao funcionario:");
        double percentage = Utils.scan.nextDouble();
        Utils.scan.nextLine();

        if (!employee.addComission(value, percentage)) {
            System.out.println("O percentual precisa estar no intervalo [0, 100]");
            System.out.println("A venda não pode ser adicionada!\n");
            return false;
        }

        System.out.println("Venda adicionada com sucesso!\n");
        return true;
    }

    public boolean addServiceCharge() {
        System.out.println("Id do funcionario no sindicato:");
        int id = Utils.scan.nextInt();
        Utils.scan.nextLine();

        if (!this.syndicate.syndicate_employees.containsKey(id)) {
            System.out.println("O id do funcionario não existe");
            System.out.println("A taxa de serviço não pode ser adicionada!\n");
            return false;
        }

        System.out.println("Digite o valor da taxa de serviço:");
        double charge = Utils.scan.nextDouble();
        Utils.scan.nextLine();

        if (charge < 0.0) {
            System.out.println("O valor não pode ser negativo");
            System.out.println("A taxa de serviço não pode ser adicionada!\n");
            return false;
        }

        SyndicateEmployee employee = this.syndicate.syndicate_employees.get(id);
        employee.addServiceCharge(charge);
        System.out.println("Taxa de serviçadicionada com sucesso!\n");

        return true;
    }

    public void printEmployees() {
        for (Map.Entry<Integer, Employee> e : this.employees.entrySet()) {
            Employee employee = e.getValue();
            employee.printEmployee();
        }
        System.out.println("");
    }
}

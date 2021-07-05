package src;

import java.util.HashMap;
import java.util.Map;

public class Employees {
    private HashMap<Integer, Employee> employees;
    private int current_id;

    public Employees() {
        this.employees = new HashMap<>();
        this.current_id = 0;
    }

    public boolean create_employee() {
        Utils.scan.nextLine();
        System.out.println("Nome do funcionario:");
        String name = Utils.scan.nextLine();

        System.out.println("Endereço do funcionario:");
        String address = Utils.scan.nextLine();

        System.out.println("Digite o tipo de funcionario a ser cadastrado:");
        System.out.println("Digite 1 para horista");
        System.out.println("Digite 2 para assalariado");
        System.out.println("Digite 3 para comissionado");
        int type = Utils.scan.nextInt();

        Employee employee = new Employee();

        if (!employee.set_type(type)) {
            System.out.println("O tipo de funcionario digitado não existe");
            System.out.println("O funcionario não pode ser criado!\n");
            return false;
        }

        employee = new Employee(name, address, ++this.current_id, type);
        this.employees.put(employee.id, employee);

        System.out.println("Funcionario criado com sucesso!");
        System.out.println("Id do funcionario criado:" + employee.id);

        return true;
    }

    public boolean remove_employee() {
        System.out.println("Id do funcionario:");
        int id = Utils.scan.nextInt();

        if (!this.employees.containsKey(id)) {
            System.out.println("O id do funcionario não existe");
            System.out.println("O funcionario não pode ser removido!\n");
            return false;
        }

        this.employees.remove(id);
        System.out.println("Funcionario removido com sucesso!\n");

        return true;
    }

    public boolean edit_employee() {
        System.out.println("Id do funcionario:");
        int id = Utils.scan.nextInt();

        if (!this.employees.containsKey(id)) {
            System.out.println("O id do funcionario não existe");
            System.out.println("O funcionario não pode ser editado!\n");
            return false;
        }

        Employee employee = this.employees.get(id);

        System.out.println("Funcionario encontrado!");
        System.out.println("Informações atuais:");
        employee.print_employee();

        Utils.scan.nextLine();
        System.out.println("Nome do funcionario:");
        String name = Utils.scan.nextLine();

        System.out.println("Endereço do funcionario:");
        String address = Utils.scan.nextLine();

        System.out.println("Digite o tipo de funcionario a ser cadastrado:");
        System.out.println("Digite 1 para horista");
        System.out.println("Digite 2 para assalariado");
        System.out.println("Digite 3 para comissionado");
        int type = Utils.scan.nextInt();

        if (!employee.set_type(type)) {
            System.out.println("O tipo de funcionario digitado não existe");
            System.out.println("O funcionario não pode ser editado!\n");
            return false;
        }

        employee = new Employee(name, address, id, type);
        System.out.println("Funcionario editado com sucesso!\n");

        return true;
    }

    public boolean throw_time_card() {
        System.out.println("Id do funcionario:");
        int id = Utils.scan.nextInt();

        if (!this.employees.containsKey(id)) {
            System.out.println("O id do funcionario não existe");
            System.out.println("O cartão de ponto não pode ser adicionado!\n");
            return false;
        }

        Employee employee = this.employees.get(id);

        if (!employee.get_hourly()) {
            System.out.println("O funcionario não é horista");
            System.out.println("O cartão de ponto não pode ser adicionado!\n");
            return false;
        }

        System.out.println("Digite a quantidade de horas trabalhadas:");
        int hours = Utils.scan.nextInt();

        if (!employee.add_hours(hours)) {
            System.out.println("A quantidade de horas não pode ser negativa");
            System.out.println("O cartão de ponto não pode ser adicionado!\n");
            return false;
        }

        System.out.println("Cartão de ponto adicionado com sucesso!\n");
        return true;
    }

    public void print_employees() {
        for (Map.Entry<Integer, Employee> e : this.employees.entrySet()) {
            Employee employee = e.getValue();
            employee.print_employee();
        }
        System.out.println("");
    }
}

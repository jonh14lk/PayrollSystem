package src;

import java.util.HashMap;
import java.util.Map;

public class Employees {
    private HashMap<Integer, Employee> employees;
    private int current_id;

    public Employees() {
        employees = new HashMap<>();
        current_id = 0;
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

        employee = new Employee(name, address, ++current_id);
        employees.put(employee.id, employee);

        System.out.println("Funcionario criado com sucesso!");
        System.out.println("Id do funcionario criado:" + employee.id);
        System.out.println("");

        return true;
    }

    public boolean remove_employee() {
        System.out.println("Id do funcionario:");
        int id = Utils.scan.nextInt();

        if (!employees.containsKey(id)) {
            System.out.println("O id do funcionario não existe");
            System.out.println("O funcionario não pode ser removido!\n");
            return false;
        }

        employees.remove(id);
        System.out.println("Funcionario removido com sucesso!\n");
        System.out.println("");

        return true;
    }

    public boolean edit_employee() {
        System.out.println("Id do funcionario:");
        int id = Utils.scan.nextInt();

        if (!employees.containsKey(id)) {
            System.out.println("O id do funcionario não existe");
            System.out.println("O funcionario não pode ser editado!\n");
            return false;
        }

        Employee employee = employees.get(id);
        Employee new_employee = new Employee();

        System.out.println("Funcionario encontrado!");
        System.out.println("Informações atuais:");
        print_employee(employee);

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

        if (!new_employee.set_type(type)) {
            System.out.println("O tipo de funcionario digitado não existe");
            System.out.println("O funcionario não pode ser editado!\n");
            return false;
        }

        employees.remove(employee.id);
        new_employee = new Employee(name, address, employee.id);
        employees.put(new_employee.id, new_employee);
        System.out.println("Funcionario editado com sucesso!\n");
        System.out.println("");

        return true;
    }

    public void print_employee(Employee employee) {
        System.out.println("Nome: " + employee.name);
        System.out.println("Endereço: " + employee.address);
        System.out.println("Tipo: " + employee.get_type());
        System.out.println("Id: " + employee.id);
        System.out.println("");
    }

    public void print_employees() {
        for (Map.Entry<Integer, Employee> e : employees.entrySet()) {
            print_employee(e.getValue());
        }
        System.out.println("");
    }
}
// TODO: print_employee pode ir pra classe employee

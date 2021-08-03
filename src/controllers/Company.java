package src.controllers;

import src.models.employee.Hourly;
import src.models.employee.Salaried;
import src.models.employee.Comissioned;
import src.models.employee.Employee;
import src.models.syndicate.SyndicateEmployee;
import src.models.syndicate.Syndicate;
import src.utils.Utils;

import java.util.HashMap;
import java.util.Map;

public class Company {
    private HashMap<Integer, Employee> employees;
    private HashMap<Integer, Hourly> hourly;
    private HashMap<Integer, Salaried> salaried;
    private HashMap<Integer, Comissioned> comissioned;
    Syndicate syndicate;
    private int current_id;

    public Company() {
        this.employees = new HashMap<>();
        this.hourly = new HashMap<>();
        this.salaried = new HashMap<>();
        this.comissioned = new HashMap<>();
        this.syndicate = new Syndicate();
        this.current_id = 0;
    }

    public boolean createEmployee() {
        String name = Utils.readName();
        String address = Utils.readAddress();
        int type = Utils.readEmployeeType();
        int from_syndicate = Utils.readFromSyndicate();
        int paymentType = Utils.readPaymentType();

        if (from_syndicate < 0 || from_syndicate > 1 || paymentType < 1 || paymentType > 3) {
            System.out.println("Entrada Invalida");
            return false;
        }

        double salary = 0.0;

        if (type != 1) {
            salary = Utils.readSalary();
            if (salary < 0.0) {
                System.out.println("Salário não pode ser negativo");
                return false;
            }
        }

        Employee employee;

        switch (type) {
            case 1:
                Hourly hourly_employee = new Hourly(name, address, ++this.current_id, type, from_syndicate,
                        this.syndicate, paymentType);
                employee = hourly_employee;
                this.hourly.put(hourly_employee.id, hourly_employee);
                break;
            case 2:
                Salaried salaried_employee = new Salaried(name, address, ++this.current_id, type, from_syndicate,
                        this.syndicate, salary, paymentType);
                employee = salaried_employee;
                this.salaried.put(salaried_employee.id, salaried_employee);
                break;
            case 3:
                Comissioned comissioned_employee = new Comissioned(name, address, ++this.current_id, type,
                        from_syndicate, this.syndicate, salary, paymentType);
                employee = comissioned_employee;
                this.comissioned.put(comissioned_employee.id, comissioned_employee);
                break;
            default:
                System.out.println("O tipo de funcionario não existe");
                return false;
        }

        this.employees.put(employee.id, employee);

        System.out.println("Funcionario criado com sucesso!");
        employee.printEmployee();
        return true;
    }

    public boolean removeEmployee() {
        int id = Utils.readId();

        if (!this.employees.containsKey(id)) {
            System.out.println("O id do funcionario não existe");
            return false;
        }

        Employee employee = this.employees.get(id);

        this.hourly.remove(id);
        this.salaried.remove(id);
        this.comissioned.remove(id);
        this.syndicate.removeSyndicateEmployee(employee.getSyndicateEmployeeId());
        this.employees.remove(id);
        System.out.println("Funcionario removido com sucesso!\n");

        return true;
    }

    public boolean editEmployee() {
        int id = Utils.readId();

        if (!this.employees.containsKey(id)) {
            System.out.println("O id do funcionario não existe");
            return false;
        }

        Employee employee = this.employees.get(id);

        System.out.println("Funcionario encontrado!");
        System.out.println("Informações atuais:");
        employee.printEmployee();

        String name = Utils.readName();
        String address = Utils.readAddress();
        int type = Utils.readEmployeeType();
        int paymentType = Utils.readPaymentType();

        if (paymentType < 1 || paymentType > 3) {
            System.out.println("Entrada Invalida");
            return false;
        }

        int from_syndicate = 1;

        if (!employee.getSyndicate()) {
            from_syndicate = Utils.readFromSyndicate();
            if (from_syndicate < 0 && from_syndicate > 1) {
                System.out.println("Entrada Inválida");
                return false;
            }
        }

        double salary = 0.0;

        if (type != 1) {
            salary = Utils.readSalary();
            if (salary < 0.0) {
                System.out.println("Salário não pode ser negativo");
                return false;
            }
        }

        switch (type) {
            case 1:
                Hourly hourly_employee = this.hourly.get(id);
                hourly_employee = new Hourly(name, address, ++this.current_id, type, from_syndicate, this.syndicate,
                        paymentType);
                employee = hourly_employee;
                break;
            case 2:
                Salaried salaried_employee = this.salaried.get(id);
                salaried_employee = new Salaried(name, address, ++this.current_id, type, from_syndicate, this.syndicate,
                        salary, paymentType);
                employee = salaried_employee;
                break;
            case 3:
                Comissioned comissioned_employee = this.comissioned.get(id);
                comissioned_employee = new Comissioned(name, address, ++this.current_id, type, from_syndicate,
                        this.syndicate, salary, paymentType);
                employee = comissioned_employee;
                break;
            default:
                System.out.println("O tipo de funcionario digitado não existe");
                return false;
        }

        System.out.println("Funcionario editado com sucesso!\n");
        return true;
    }

    public boolean throwTimeCard() {
        int id = Utils.readId();

        if (!this.employees.containsKey(id)) {
            System.out.println("O id do funcionario não existe");
            return false;
        }

        Employee employee = this.employees.get(id);

        if (!employee.getHourly()) {
            return false;
        }

        Hourly hourly_employee = this.hourly.get(id);
        int hours = Utils.readHours();

        if (!hourly_employee.addHours(hours)) {
            return false;
        }

        return true;
    }

    public boolean addSale() {
        int id = Utils.readId();

        if (!this.employees.containsKey(id)) {
            System.out.println("O id do funcionario não existe");
            return false;
        }

        Employee employee = this.employees.get(id);

        if (!employee.getComissioned()) {
            return false;
        }

        double value = Utils.readSale();
        double percentage = Utils.readPercentage();
        Comissioned comissioned_employee = this.comissioned.get(id);

        if (!comissioned_employee.addComission(value, percentage)) {
            return false;
        }

        return true;
    }

    public boolean addServiceCharge() {
        int id = Utils.readId();

        if (!this.syndicate.syndicate_employees.containsKey(id)) {
            System.out.println("O id do funcionario não existe");
            return false;
        }

        double charge = Utils.readServiceCharge();
        SyndicateEmployee employee = this.syndicate.syndicate_employees.get(id);

        if (!employee.addServiceCharge(charge)) {
            return false;
        }

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
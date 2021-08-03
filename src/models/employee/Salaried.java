package src.models.employee;

import src.models.syndicate.Syndicate;

public class Salaried extends Employee {
    private double salary;

    public Salaried(String name, String address, int id, int type, int from_syndicate, Syndicate syndicate,
            double salary, int paymentType) {
        super(name, address, id, type, from_syndicate, syndicate, paymentType);
        this.setSalary(salary);
    }

    public double getSalary() {
        return this.salary;
    }

    public boolean setSalary(double salary) {
        if (salary < 0.0) {
            return false;
        }
        this.salary = salary;
        return true;
    }

    @Override
    public void printEmployee() {
        System.out.println("Nome: " + this.name);
        System.out.println("Endereço: " + this.address);
        System.out.println("Id: " + this.id);
        System.out.println("Tipo: Assalariado");
        System.out.println("Salario: " + this.getSalary());
        this.printPaymentType();
        if (getSyndicate()) {
            System.out.println("Id do funcionario no sindicato: " + getSyndicateEmployeeId());
        }
        System.out.println("");
    }
}

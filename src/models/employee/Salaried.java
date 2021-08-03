package src.models.employee;

import src.models.syndicate.Syndicate;
import src.models.syndicate.SyndicateEmployee;
import java.util.Calendar;

public class Salaried extends Employee {
    private double salary;

    public Salaried(String name, String address, int id, int type, int from_syndicate, Syndicate syndicate,
            double salary, int payment_type) {
        super(name, address, id, type, from_syndicate, syndicate, payment_type);
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
    }

    @Override
    public void payEmployee(Calendar current_date, Syndicate syndicate) {
        System.out.println("Nome: " + this.name);
        System.out.println("Id: " + this.id);
        System.out.println("Tipo: Assalariado");
        printPaymentType();

        double value = this.getSalary();

        if (getSyndicate()) {
            SyndicateEmployee employee = syndicate.syndicate_employees.get(this.getSyndicateEmployeeId());
            double service_charge = employee.getServiceCharge();

            if (service_charge > value) {
                service_charge = value;
            }

            employee.addServiceCharge(-service_charge);
            value -= service_charge;
        }

        System.out.println("Valor: " + value);
        this.setLastPayment(current_date.get(Calendar.DAY_OF_MONTH), current_date.get(Calendar.MONTH),
                current_date.get(Calendar.YEAR));
    }
}

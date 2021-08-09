package src.models.employee;

import src.models.syndicate.Syndicate;
import src.models.syndicate.SyndicateEmployee;
import java.util.Calendar;

public class Hourly extends Employee {
    private int hours;
    private int extra_hours;

    public Hourly(String name, String address, int id, int type, int from_syndicate, Syndicate syndicate, double salary,
            int payment_type) {
        super(name, address, id, type, from_syndicate, syndicate, payment_type, salary, "semanal 1 sexta");
        this.hours = 0;
        this.extra_hours = 0;
    }

    public void editHourly(String name, String address, int id, int type, double salary, int payment_type) {
        editEmployee(name, address, id, type, payment_type, salary, "semanal 1 sexta");
    }

    public boolean addHours(int hours) {
        if (hours < 0) {
            System.out.println("A quantidade de horas não pode ser negativa");
            return false;
        }
        this.hours += Math.min(hours, 8);
        this.extra_hours += Math.max(0, hours - 8);
        System.out.println("Cartão de ponto adicionado com sucesso!\n");
        return true;
    }

    public int getHours() {
        return this.hours;
    }

    public int getExtraHours() {
        return this.extra_hours;
    }

    @Override
    public void printEmployee() {
        System.out.println("Nome: " + this.name);
        System.out.println("Endereço: " + this.address);
        System.out.println("Id: " + this.id);
        System.out.println("Tipo: Horista");
        System.out.println("Horas Normais: " + this.hours);
        System.out.println("Horas Extras: " + this.extra_hours);
        System.out.println("Salário por hora: " + this.getSalary());
        System.out.println("Forma de pagamento: " + this.getPaymentType());
        System.out.println("Agenda de pagamento: " + this.getPaymentSchedule().toString());
        if (getSyndicate()) {
            System.out.println("Id do funcionario no sindicato: " + getSyndicateEmployeeId());
        }
    }

    @Override
    public void payEmployee(Calendar current_date, Syndicate syndicate) {
        System.out.println("Nome: " + this.name);
        System.out.println("Id: " + this.id);
        System.out.println("Tipo: Horista");
        System.out.println("Forma de pagamento: " + this.getPaymentType());

        double value = (this.getSalary() * this.hours) + (1.5 * this.extra_hours * this.getSalary());

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
        this.hours = 0;
        this.extra_hours = 0;
        this.setLastPayment(current_date.get(Calendar.DAY_OF_MONTH), current_date.get(Calendar.MONTH),
                current_date.get(Calendar.YEAR));
    }
}

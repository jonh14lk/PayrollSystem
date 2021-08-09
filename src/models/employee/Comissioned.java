package src.models.employee;

import src.models.syndicate.Syndicate;
import src.models.syndicate.SyndicateEmployee;
import java.util.Calendar;

public class Comissioned extends Salaried {
    private double comission;

    public Comissioned(String name, String address, int id, int type, int from_syndicate, Syndicate syndicate,
            double salary, int payment_type) {
        super(name, address, id, type, from_syndicate, syndicate, salary, payment_type);
        this.setPaymentSchedule("semanal 2 sexta");
        this.comission = 0.0;
    }

    public void editComissioned(String name, String address, int id, int type, double salary, int payment_type) {
        editSalaried(name, address, id, type, salary, payment_type);
        this.setPaymentSchedule("semanal 2 sexta");
    }

    public boolean addComission(double value, double percentage) {
        if (value < 0.0) {
            System.out.println("O valor não pode ser negativo");
            return false;
        } else if (!(percentage >= 0.0 && percentage <= 100.0)) {
            System.out.println("O percentual precisa estar no intervalo [0, 100]");
            return false;
        }
        percentage /= 100;
        value *= percentage;
        this.comission += value;
        System.out.println("Venda adicionada com sucesso!\n");
        return true;
    }

    @Override
    public void printEmployee() {
        System.out.println("Nome: " + this.name);
        System.out.println("Endereço: " + this.address);
        System.out.println("Id: " + this.id);
        System.out.println("Tipo: Comissionado");
        System.out.println("Salário: " + this.getSalary());
        System.out.println("Comissão: " + this.comission);
        System.out.println("Forma de pagamento: " + this.getPaymentType());
        System.out.println("Agenda de pagamento: " + this.getPaymentSchedule().toString());
        if (getSyndicate()) {
            System.out.println("Id do funcionario no sindicato: " + this.getSyndicateEmployeeId());
        }
    }

    @Override
    public void payEmployee(Calendar current_date, Syndicate syndicate) {
        System.out.println("Nome: " + this.name);
        System.out.println("Id: " + this.id);
        System.out.println("Tipo: Comissionado");
        System.out.println("Forma de pagamento: " + this.getPaymentType());

        double value = (this.getSalary() / 2) + this.comission;

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
        this.comission = 0.0;
        this.setLastPayment(current_date.get(Calendar.DAY_OF_MONTH), current_date.get(Calendar.MONTH),
                current_date.get(Calendar.YEAR));
    }
}

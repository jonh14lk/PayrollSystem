package src.models.employee;

import src.models.syndicate.Syndicate;

public class Hourly extends Employee {
    private int hours, extra_hours;

    public Hourly(String name, String address, int id, int type, int from_syndicate, Syndicate syndicate,
            int paymentType) {
        super(name, address, id, type, from_syndicate, syndicate, paymentType);
        this.hours = 0;
        this.extra_hours = 0;
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
        this.printPaymentType();
        if (getSyndicate()) {
            System.out.println("Id do funcionario no sindicato: " + getSyndicateEmployeeId());
        }
        System.out.println("");
    }
}

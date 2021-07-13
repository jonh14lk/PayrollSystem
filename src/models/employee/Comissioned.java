package src.models.employee;

import src.models.syndicate.Syndicate;

public class Comissioned extends Employee {
    private double comission;

    public Comissioned(String name, String address, int id, int type, int from_syndicate, Syndicate syndicate) {
        this.name = name;
        this.address = address;
        this.id = id;
        if (from_syndicate == 1) {
            setSyndicate(syndicate);
        }
        this.setType(type);
        this.comission = 0.0;
    }

    public boolean addComission(double value, double percentage) {
        if (!(percentage >= 0.0 && percentage <= 100.0)) {
            return false;
        }
        percentage /= 100;
        value *= percentage;
        this.comission += value;
        return true;
    }

    @Override
    public void printEmployee() {
        System.out.println("Nome: " + this.name);
        System.out.println("Endereço: " + this.address);
        System.out.println("Id: " + this.id);
        System.out.println("Tipo: Comissionado");
        System.out.println("Comissão: " + this.comission);
        if (getSyndicate()) {
            System.out.println("Id do funcionario no sindicato :" + getSyndicateEmployeeId());
        }
        System.out.println("");
    }
}

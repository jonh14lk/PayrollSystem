package src.models.employee;

import src.models.syndicate.Syndicate;

public class Salaried extends Employee {

    public Salaried(String name, String address, int id, int type, int from_syndicate, Syndicate syndicate) {
        this.name = name;
        this.address = address;
        this.id = id;
        if (from_syndicate == 1) {
            setSyndicate(syndicate);
        }
        this.setType(type);
    }

    @Override
    public void printEmployee() {
        System.out.println("Nome: " + this.name);
        System.out.println("Endereço: " + this.address);
        System.out.println("Id: " + this.id);
        System.out.println("Tipo: Assalariado");
        if (getSyndicate()) {
            System.out.println("Id do funcionario no sindicato :" + getSyndicateEmployeeId());
        }
        System.out.println("");
    }
}

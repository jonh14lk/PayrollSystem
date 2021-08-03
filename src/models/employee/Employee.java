package src.models.employee;

import src.models.syndicate.Syndicate;

public class Employee {
    public String name, address;
    public int id;
    private boolean hourly, salaried, commissioned;
    private boolean from_syndicate;
    private int syndicate_employee_id;
    private int paymentType;

    public Employee(String name, String address, int id, int type, int from_syndicate, Syndicate syndicate,
            int paymentType) {
        this.name = name;
        this.address = address;
        this.id = id;
        this.setType(type);
        this.setPayment(paymentType);
        if (from_syndicate == 1) {
            setSyndicate(syndicate);
        }
    }

    public boolean setPayment(int paymentType) {
        if (paymentType >= 1 && paymentType <= 3) {
            this.paymentType = paymentType;
            return true;
        }
        return false;
    }

    public int getPayment(int paymentType) {
        return this.paymentType;
    }

    public boolean setType(int type) {
        switch (type) {
            case 1:
                this.hourly = true;
                this.salaried = false;
                this.commissioned = false;
                return true;
            case 2:
                this.hourly = false;
                this.salaried = true;
                this.commissioned = false;
                return true;
            case 3:
                this.hourly = false;
                this.salaried = true;
                this.commissioned = true;
                return true;
            default:
                break;
        }
        return false;
    }

    public void setSyndicate(Syndicate syndicate) {
        this.from_syndicate = true;
        this.syndicate_employee_id = syndicate.createSyndicateEmployee();
    }

    public boolean getSyndicate() {
        return this.from_syndicate;
    }

    public int getSyndicateEmployeeId() {
        return this.syndicate_employee_id;
    }

    public boolean getHourly() {
        if (!this.hourly) {
            System.out.println("O funcionario não é horista");
        }
        return this.hourly;
    }

    public boolean getSalaried() {
        return this.salaried;
    }

    public boolean getComissioned() {
        if (this.commissioned == false) {
            System.out.println("O funcionario não é comissiondo");
        }
        return this.commissioned;
    }

    public void printEmployee() {
        System.out.println("Nome: " + this.name);
        System.out.println("Endereço: " + this.address);
        System.out.println("Id: " + this.id);
        this.printPaymentType();
        System.out.println("");
    }

    public void printPaymentType() {
        switch (this.paymentType) {
            case 1:
                System.out.println("Pagamento: Cheque pelos correios");
                break;
            case 2:
                System.out.println("Pagamento: Cheque em mãos");
                break;
            case 3:
                System.out.println("Pagamento: Depósito em conta bancária");
                break;
        }
    }
}

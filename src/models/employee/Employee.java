package src.models.employee;

import src.models.syndicate.Syndicate;
import java.util.Calendar;

public class Employee {
    public String name, address;
    public int id;
    private boolean hourly, salaried, commissioned;
    private boolean from_syndicate;
    private int syndicate_employee_id, payment_type;
    private Calendar last_payment;

    public Employee(String name, String address, int id, int type, int from_syndicate, Syndicate syndicate,
            int payment_type) {
        this.name = name;
        this.address = address;
        this.id = id;
        this.setType(type);
        this.setPaymentType(payment_type);
        this.setLastPayment(1, 1, 2021);
        if (from_syndicate == 1) {
            setSyndicate(syndicate);
        }
    }

    public void setLastPayment(int day, int month, int year) {
        this.last_payment = Calendar.getInstance();
        this.last_payment.set(Calendar.YEAR, year);
        this.last_payment.set(Calendar.MONTH, month);
        this.last_payment.set(Calendar.DAY_OF_MONTH, day);
    }

    public Calendar getLastPayment() {
        return this.last_payment;
    }

    public boolean setPaymentType(int payment_type) {
        if (payment_type >= 1 && payment_type <= 3) {
            this.payment_type = payment_type;
            return true;
        }
        return false;
    }

    public int getPaymentType(int paymentType) {
        return this.payment_type;
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
    }

    public void printPaymentType() {
        switch (this.payment_type) {
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

    public void payEmployee(Calendar current_date, Syndicate syndicate) {
        System.out.println("Nome: " + this.name);
        System.out.println("Id: " + this.id);
        printPaymentType();
        this.setLastPayment(current_date.get(Calendar.DAY_OF_MONTH), current_date.get(Calendar.MONTH),
                current_date.get(Calendar.YEAR));
    }
}

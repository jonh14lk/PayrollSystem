package src.models.employee;

import java.io.Serializable;
import src.models.syndicate.Syndicate;
import java.util.Calendar;

public class Employee implements Serializable {
    public String name;
    public String address;
    public int id;
    private boolean hourly;
    private boolean salaried;
    private boolean commissioned;
    private boolean from_syndicate;
    private int syndicate_employee_id;
    private int payment_type;
    private int payment_schedule;
    private double salary;
    private Calendar last_payment;

    public Employee(String name, String address, int id, int type, int from_syndicate, Syndicate syndicate,
            int payment_type, double salary, int payment_schedule) {
        this.name = name;
        this.address = address;
        this.id = id;
        this.setPaymentSchedule(payment_schedule);
        this.setSalary(salary);
        this.setType(type);
        this.setPaymentType(payment_type);
        this.setLastPayment(1, 1, 2021);
        if (from_syndicate == 1) {
            setSyndicate(syndicate);
        }
    }

    public void editEmployee(String name, String address, int id, int type, int payment_type, double salary,
            int payment_schedule) {
        this.name = name;
        this.address = address;
        this.id = id;
        this.setPaymentSchedule(payment_schedule);
        this.setSalary(salary);
        this.setType(type);
        this.setPaymentType(payment_type);
    }

    public boolean setPaymentSchedule(int payment_schedule) {
        if (payment_schedule >= 1 || payment_schedule <= 3) {
            this.payment_schedule = payment_schedule;
            return true;
        }
        return false;
    }

    public String getPaymentSchedule() {
        switch (this.payment_schedule) {
            case 1:
                return "Semanalmente";
            case 2:
                return "Mensalmente";
            case 3:
                return "Bi-semanalmente";
        }
        return "";
    }

    public boolean setSalary(double salary) {
        if (salary < 0.0) {
            return false;
        }
        this.salary = salary;
        return true;
    }

    public double getSalary() {
        return this.salary;
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

    public String getPaymentType() {
        switch (this.payment_type) {
            case 1:
                return "Cheque pelos correios";
            case 2:
                return "Cheque em mãos";
            case 3:
                return "Depósito em conta bancária";
        }
        return "";
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
        if (!this.salaried) {
            System.out.println("O funcionario não é assalariado");
        }
        return this.salaried;
    }

    public boolean getComissioned() {
        if (!this.commissioned) {
            System.out.println("O funcionario não é comissiondo");
        }
        return this.commissioned;
    }

    public void printEmployee() {
        System.out.println("Nome: " + this.name);
        System.out.println("Endereço: " + this.address);
        System.out.println("Id: " + this.id);
    }

    public void payEmployee(Calendar current_date, Syndicate syndicate) {
        System.out.println("Nome: " + this.name);
        System.out.println("Id: " + this.id);
        this.setLastPayment(current_date.get(Calendar.DAY_OF_MONTH), current_date.get(Calendar.MONTH),
                current_date.get(Calendar.YEAR));
    }
}

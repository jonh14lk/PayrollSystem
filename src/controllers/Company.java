package src.controllers;

import src.models.employee.Hourly;
import src.models.employee.Salaried;
import src.models.employee.Comissioned;
import src.models.employee.Employee;
import src.models.syndicate.SyndicateEmployee;
import src.models.syndicate.Syndicate;
import src.models.payment.Schedule;
import src.utils.Utils;
import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Company implements Serializable {
    private HashMap<Integer, Employee> employees;
    private HashMap<Integer, Hourly> hourly;
    private HashMap<Integer, Salaried> salaried;
    private HashMap<Integer, Comissioned> comissioned;
    private HashSet<String> payment_schedules;
    public Syndicate syndicate;
    public int current_id;

    public Company() {
        this.employees = new HashMap<>();
        this.hourly = new HashMap<>();
        this.salaried = new HashMap<>();
        this.comissioned = new HashMap<>();
        this.payment_schedules = new HashSet<>();
        this.syndicate = new Syndicate();
        this.current_id = 0;
        this.payment_schedules.add("mensal $");
        this.payment_schedules.add("semanal 1 sexta");
        this.payment_schedules.add("semanal 2 sexta");
    }

    public boolean createEmployee() {
        String name = Utils.readName();
        String address = Utils.readAddress();
        int type = Utils.readEmployeeType();
        int from_syndicate = Utils.readFromSyndicate();
        int payment_type = Utils.readPaymentType();
        double salary = Utils.readSalary();

        if (from_syndicate < 0 || from_syndicate > 1 || payment_type < 1 || payment_type > 3) {
            System.out.println("Entrada Invalida");
            return false;
        } else if (salary < 0.0) {
            System.out.println("Salário não pode ser negativo");
            return false;
        }

        Employee employee;

        switch (type) {
            case 1:
                Hourly hourly_employee = new Hourly(name, address, ++this.current_id, type, from_syndicate,
                        this.syndicate, salary, payment_type);
                employee = hourly_employee;
                this.hourly.put(hourly_employee.id, hourly_employee);
                break;
            case 2:
                Salaried salaried_employee = new Salaried(name, address, ++this.current_id, type, from_syndicate,
                        this.syndicate, salary, payment_type);
                employee = salaried_employee;
                this.salaried.put(salaried_employee.id, salaried_employee);
                break;
            case 3:
                Comissioned comissioned_employee = new Comissioned(name, address, ++this.current_id, type,
                        from_syndicate, this.syndicate, salary, payment_type);
                employee = comissioned_employee;
                this.comissioned.put(comissioned_employee.id, comissioned_employee);
                break;
            default:
                System.out.println("O tipo de funcionario não existe");
                return false;
        }

        this.employees.put(employee.id, employee);
        employee.printEmployee();
        return true;
    }

    public boolean removeEmployee() {
        int id = Utils.readId();

        if (!this.employees.containsKey(id)) {
            System.out.println("O id do funcionario não existe");
            return false;
        }

        Employee employee = this.employees.get(id);

        this.hourly.remove(id);
        this.salaried.remove(id);
        this.comissioned.remove(id);
        this.syndicate.removeSyndicateEmployee(employee.getSyndicateEmployeeId());
        this.employees.remove(id);
        return true;
    }

    public boolean editEmployee() {
        int id = Utils.readId();

        if (!this.employees.containsKey(id)) {
            System.out.println("O id do funcionario não existe");
            return false;
        }

        Employee employee = this.employees.get(id);

        System.out.println("Funcionario encontrado!");
        System.out.println("Informações atuais:");
        employee.printEmployee();

        String name = Utils.readName();
        String address = Utils.readAddress();
        int type = Utils.readEmployeeType();
        int from_syndicate = Utils.readFromSyndicate();
        int payment_type = Utils.readPaymentType();
        double salary = Utils.readSalary();

        if (from_syndicate < 0 || from_syndicate > 1 || payment_type < 1 || payment_type > 3) {
            System.out.println("Entrada Invalida");
            return false;
        } else if (salary < 0.0 || type < 1 || type > 3) {
            System.out.println("Entrada Invalida");
            return false;
        }

        Hourly hourly_employee = this.hourly.get(id);
        Salaried salaried_employee = this.salaried.get(id);
        Comissioned comissioned_employee = this.comissioned.get(id);

        this.hourly.remove(id);
        this.salaried.remove(id);
        this.comissioned.remove(id);
        if (from_syndicate == 0) {
            this.syndicate.removeSyndicateEmployee(employee.getSyndicateEmployeeId());
        }

        switch (type) {
            case 1:
                if (hourly_employee == null) {
                    hourly_employee = new Hourly(name, address, id, type, from_syndicate, this.syndicate, salary,
                            payment_type);
                }
                hourly_employee.editHourly(name, address, id, type, salary, payment_type);
                employee = hourly_employee;
                this.hourly.put(id, hourly_employee);
                break;
            case 2:
                if (salaried_employee == null) {
                    salaried_employee = new Salaried(name, address, id, type, from_syndicate, this.syndicate, salary,
                            payment_type);
                }
                salaried_employee.editSalaried(name, address, id, type, salary, payment_type);
                employee = salaried_employee;
                this.salaried.put(id, salaried_employee);
                break;
            case 3:
                if (comissioned_employee == null) {
                    comissioned_employee = new Comissioned(name, address, id, type, from_syndicate, this.syndicate,
                            salary, payment_type);
                }
                comissioned_employee.editComissioned(name, address, id, type, salary, payment_type);
                employee = comissioned_employee;
                this.comissioned.put(id, comissioned_employee);
                break;
        }

        this.employees.put(id, employee);
        return true;
    }

    public boolean throwTimeCard() {
        int id = Utils.readId();

        if (!this.employees.containsKey(id)) {
            System.out.println("O id do funcionario não existe");
            return false;
        }

        Employee employee = this.employees.get(id);

        if (!employee.getHourly()) {
            return false;
        }

        Hourly hourly_employee = this.hourly.get(id);
        int hours = Utils.readHours();

        if (!hourly_employee.addHours(hours)) {
            return false;
        }

        return true;
    }

    public boolean addSale() {
        int id = Utils.readId();

        if (!this.employees.containsKey(id)) {
            System.out.println("O id do funcionario não existe");
            return false;
        }

        Employee employee = this.employees.get(id);

        if (!employee.getComissioned()) {
            return false;
        }

        double value = Utils.readSale();
        double percentage = Utils.readPercentage();
        Comissioned comissioned_employee = this.comissioned.get(id);

        if (!comissioned_employee.addComission(value, percentage)) {
            return false;
        }

        return true;
    }

    public boolean addServiceCharge() {
        int id = Utils.readId();

        if (!this.syndicate.syndicate_employees.containsKey(id)) {
            System.out.println("O id do funcionario não existe");
            return false;
        }

        double charge = Utils.readServiceCharge();
        SyndicateEmployee employee = this.syndicate.syndicate_employees.get(id);

        if (!employee.addServiceCharge(charge)) {
            return false;
        }

        return true;
    }

    public void RunPayroll() {
        Calendar current_date = Utils.readDate();

        for (Map.Entry<Integer, Employee> e : this.employees.entrySet()) {
            Employee employee = e.getValue();
            Schedule payment_schedule = employee.getPaymentSchedule();
            if (payment_schedule.getTimeGap() == 0) {
                int day = payment_schedule.getDay();

                if (day == 50 && Utils.LastBussinessDay(current_date)
                        && Utils.dateDiff(employee.getLastPayment(), current_date) > 0) {
                    employee.payEmployee(current_date, syndicate);
                    System.out.println("");
                } else if (current_date.get(Calendar.DATE) == day
                        && Utils.dateDiff(employee.getLastPayment(), current_date) > 0) {
                    employee.payEmployee(current_date, syndicate);
                    System.out.println("");
                }

            } else if (payment_schedule.getTimeGap() == 1) {
                int need = payment_schedule.getDay() * 7;
                int week_day = payment_schedule.getWeekDay();

                if (current_date.get(Calendar.DAY_OF_WEEK) == week_day
                        && Utils.dateDiff(employee.getLastPayment(), current_date) >= need) {
                    employee.payEmployee(current_date, syndicate);
                    System.out.println("");
                }
            }
        }
    }

    public boolean changePaymentSchedule() {
        int id = Utils.readId();
        Schedule payment_schedule = Utils.readPaymentSchedule();
        if (!this.syndicate.syndicate_employees.containsKey(id)) {
            System.out.println("O id do funcionario não existe");
            return false;
        } else if (!this.payment_schedules.contains(payment_schedule.toString())) {
            System.out.println("Agenda de pagamento não encontrada");
            return false;
        }

        Employee employee = this.employees.get(id);
        employee.setPaymentSchedule(payment_schedule);
        return true;
    }

    public boolean addPaymentSchedule() {
        Schedule payment_schedule = Utils.readPaymentSchedule();

        if (!payment_schedule.isValid()) {
            System.out.println("Entrada inválida");
            return false;
        }

        payment_schedules.add(payment_schedule.toString());
        return true;
    }

    public void printEmployees() {
        if (this.employees.size() == 0) {
            System.out.println("Não há nenhum empregado no sistema");
            return;
        }
        for (Map.Entry<Integer, Employee> e : this.employees.entrySet()) {
            Employee employee = e.getValue();
            employee.printEmployee();
            System.out.println("");
        }
    }
}
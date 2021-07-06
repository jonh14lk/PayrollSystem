package src;

public class Employee {
    public String name, address;
    public int id;
    private int type;
    private boolean hourly, salaried, commissioned;
    private int hours, extra_hours;
    private double comission;

    public Employee() {
        this.name = "";
        this.address = "";
        this.id = 0;
        this.type = 1;
    }

    public Employee(String name, String address, int id, int type) {
        this.set_type(type);
        this.name = name;
        this.address = address;
        this.id = id;
    }

    public boolean set_type(int type) {
        switch (type) {
            case 1:
                this.type = type;
                this.hourly = true;
                this.salaried = false;
                this.commissioned = false;
                return true;
            case 2:
                this.type = type;
                this.hourly = false;
                this.salaried = true;
                this.commissioned = false;
                return true;
            case 3:
                this.type = type;
                this.hourly = false;
                this.salaried = true;
                this.commissioned = true;
                return true;
            default:
                break;
        }
        return false;
    }

    public boolean get_hourly() {
        return this.hourly;
    }

    public boolean get_salaried() {
        return this.salaried;
    }

    public boolean get_comissioned() {
        return this.commissioned;
    }

    public boolean add_hours(int hours) {
        if (hours < 0) {
            return false;
        }
        this.hours += Math.min(hours, 8);
        this.extra_hours += Math.max(0, hours - 8);
        return true;
    }

    public boolean add_comission(double value, double percentage) {
        if (!(percentage >= 0.0 && percentage <= 100.0)) {
            return false;
        }
        percentage /= 100;
        value *= percentage;
        this.comission += value;
        return true;
    }

    public void print_employee() {
        System.out.println("Nome: " + this.name);
        System.out.println("Endereço: " + this.address);
        System.out.println("Id: " + this.id);
        switch (type) {
            case 1:
                System.out.println("Tipo: Horista");
                System.out.println("Horas Normais: " + this.hours);
                System.out.println("Horas Extras: " + this.extra_hours);
                break;
            case 2:
                System.out.println("Tipo: Assalariado");
                break;
            case 3:
                System.out.println("Tipo: Comissionado");
                System.out.println("Comissão: " + this.comission);
                break;
            default:
                break;
        }
        System.out.println("");
    }
}

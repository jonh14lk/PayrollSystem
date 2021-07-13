package src.controllers;

public class SyndicateEmployee {
    public int id;
    private double service_charge;

    public SyndicateEmployee(int id) {
        this.id = id;
        this.service_charge = 0;
    }

    public boolean addServiceCharge(double charge) {
        if (charge < 0) {
            return false;
        }
        this.service_charge += charge;
        return true;
    }
}

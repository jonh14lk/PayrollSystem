package src;

public class SyndicateEmployee {
    public int id;
    private double service_charge;

    SyndicateEmployee(int id) {
        this.id = id;
        this.service_charge = 0;
    }

    public boolean add_service_charge(double charge) {
        if (charge < 0) {
            return false;
        }
        this.service_charge += charge;
        return true;
    }
}

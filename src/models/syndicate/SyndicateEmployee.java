package src.models.syndicate;

public class SyndicateEmployee {
    public int id;
    private double service_charge;

    public SyndicateEmployee(int id) {
        this.id = id;
        this.service_charge = 0;
    }

    public boolean addServiceCharge(double charge) {
        this.service_charge += charge;
        System.out.println("Taxa de serviço adicionada com sucesso!\n");
        return true;
    }

    public double getServiceCharge() {
        return this.service_charge;
    }
}

package src.models.syndicate;

public class SyndicateEmployee {
    public int id;
    private double service_charge;

    public SyndicateEmployee(int id) {
        this.id = id;
        this.service_charge = 0;
    }

    public boolean addServiceCharge(double charge) {
        if (charge < 0) {
            System.out.println("O valor não pode ser negativo");
            return false;
        }
        this.service_charge += charge;
        System.out.println("Taxa de serviço adicionada com sucesso!\n");
        return true;
    }
}

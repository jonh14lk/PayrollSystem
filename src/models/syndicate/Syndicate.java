package src.models.syndicate;

import java.io.Serializable;
import java.util.HashMap;

public class Syndicate implements Serializable {
    public HashMap<Integer, SyndicateEmployee> syndicate_employees;
    private int current_id;

    public Syndicate() {
        this.syndicate_employees = new HashMap<>();
        this.current_id = 0;
    }

    public int createSyndicateEmployee() {
        SyndicateEmployee employee = new SyndicateEmployee(++current_id);
        this.syndicate_employees.put(employee.id, employee);
        return employee.id;
    }

    public void removeSyndicateEmployee(int id) {
        this.syndicate_employees.remove(id);
    }
}

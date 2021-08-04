package src.models.syndicate;

import java.util.HashMap;

public class Syndicate {
    public HashMap<Integer, SyndicateEmployee> syndicate_employees;
    private int current_id;

    public Syndicate() {
        this.syndicate_employees = new HashMap<>();
        this.current_id = 0;
    }

    public Syndicate(Syndicate syndicate) {
        this.syndicate_employees = new HashMap<>(syndicate.syndicate_employees);
        this.current_id = syndicate.current_id;
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

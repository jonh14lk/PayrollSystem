package src;

import java.util.HashMap;

public class Syndicate {
    public HashMap<Integer, SyndicateEmployee> syndicate_employees;
    private int current_id;

    Syndicate() {
        this.syndicate_employees = new HashMap<>();
        this.current_id = 0;
    }

    public int create_syndicate_employee() {
        SyndicateEmployee employee = new SyndicateEmployee(++current_id);
        this.syndicate_employees.put(employee.id, employee);
        return employee.id;
    }

    public void remove_syndicate_employee(int id) {
        this.syndicate_employees.remove(id);
    }
}

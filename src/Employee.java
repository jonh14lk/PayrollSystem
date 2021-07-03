package src;

public class Employee {
    public String name, address;
    public int id;
    private int type;

    public Employee() {
        this.name = "";
        this.address = "";
        this.id = -1;
        this.type = -1;
    }

    public Employee(String name, String address, int id) {
        this.name = name;
        this.address = address;
        this.id = id;
    }

    public int get_type() {
        return this.type;
    }

    public boolean set_type(int type) {
        if (type >= 1 && type <= 3) {
            this.type = type;
            return true;
        }
        return false;
    }
}

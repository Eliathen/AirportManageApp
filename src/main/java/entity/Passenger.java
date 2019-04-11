package entity;

public class Passenger {
    protected Long id;
    protected String name;
    protected String surname;
    protected String pesel;

    public Passenger(Long id, String name, String surname, String pesel){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
    }

    public Passenger(String name, String surname, String pesel) {
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
    }

    public Long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getSurname(){
        return surname;
    }
    public String getPesel(){
        return pesel;
    }

    @Override
    public String toString(){
        return "Person{ " +
                "id = " + id + '\n' +
                "name = " + name + '\n' +
                "surname = " + surname + '\n' +
                "pesel = " + pesel;
    }
}

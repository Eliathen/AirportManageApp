package entity;

public class Passanger{
    protected Long id;
    protected String name;
    protected String surname;
    protected String pesel;

    public Passanger(Long id, String name, String surname, String pesel){
        this.id = id;
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

package entity;

public class Person {
    private Long id;
    private String name;
    private String surname;
    private String pesel;

    public Person(Long id, String name, String surname, String pesel){
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
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", pesel='" + pesel + '\'' +
                '}' ;
    }
}

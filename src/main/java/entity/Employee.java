package entity;

import java.math.BigDecimal;

public class Employee extends Passenger {
    private String occupation;
    private BigDecimal salary;
    private Long airlineId;

    public Employee(Long id, String name, String surname, String pesel, String occupation, BigDecimal salary, Long airlineId){
        super(id,name,surname,pesel);
        this.occupation = occupation;
        this.salary = salary;
        this.airlineId = airlineId;
    }
    public Employee(String name, String surname, String pesel, String occupation, BigDecimal salary, Long airlineId){
        super(name,surname,pesel);
        this.occupation = occupation;
        this.salary = salary;
        this.airlineId = airlineId;
    }
    public String getOccupation(){
        return occupation;
    }
    public BigDecimal getSalary(){
        return salary;
    }
    public Long getAirlineId(){
        return airlineId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "occupation='" + occupation + '\'' +
                ", salary=" + salary +
                ", airlineId=" + airlineId +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", pesel='" + pesel + '\'' +
                '}' ;
    }
}

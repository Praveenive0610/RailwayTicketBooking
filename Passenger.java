public class Passenger{
    int id=1;
    int passengerId;
    String name ;
    int age;
    String berthPreference;
    int seatNumber;
    String allotedSeat;

    public Passenger(String name, int age, String berthPreference){
       this.name = name;
       this.age = age;
       this.berthPreference = berthPreference;
       this.passengerId = id++;
       this.seatNumber=-1;
       this.allotedSeat=" ";
    }
}
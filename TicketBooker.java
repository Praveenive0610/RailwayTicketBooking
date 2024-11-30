import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;


public class TicketBooker {

    static int availableLowerBerth =1;
    static int availableMiddleBerth =1;
    static int availableUpperBerth = 1;
    static int availableRacList = 1;
    static int availableWaitingList =1;

    static Queue<Integer> racList= new LinkedList<>();
    static Queue<Integer> waitingList = new LinkedList<>();
    static List<Integer>bookedTicket = new ArrayList<>();

    static List<Integer> lowerBerthSeats = new ArrayList<>();
    {
    for(int i=1;i<=21;i++){
        lowerBerthSeats.add(i);
    }
}
     
    static List<Integer> middleBerthsPositions = new ArrayList<>(Arrays.asList(1));//normally 1,2,...21
    static List<Integer> upperBerthsPositions = new ArrayList<>(Arrays.asList(1));//normally 1,2,...21
    static List<Integer> racPositions = new ArrayList<>(Arrays.asList(1));//normally 1,2,...18
    static List<Integer> waitingListPositions = new ArrayList<>(Arrays.asList(1));
    
    static Map<Integer,Passenger> mapToPassenger = new HashMap<Integer,Passenger>();

    public void bookTicket(Passenger p,int seatNumber,String alloted)
    {
        p.seatNumber = seatNumber;
        p.allotedSeat= alloted;
        mapToPassenger.put(p.passengerId,p);
        bookedTicket.add(p.passengerId);
        System.out.println("Ticket booked");  
    }

    public void addRac(Passenger p,int seatNumber, String alloted)
    {
        p.seatNumber= seatNumber;
        p.allotedSeat = alloted;
        mapToPassenger.put(p.passengerId, p);
        racList.add(p.passengerId);
        System.out.print("RAC Ticket booked");

    }
    public void addWaitingList(Passenger p, int seatNumber,String alloted)
    {
        p.seatNumber= seatNumber;
        p.allotedSeat = alloted;
        mapToPassenger.put(p.passengerId, p);
        waitingList.add(p.passengerId);
        System.out.print("Your an WaitingLIST");
    }

   
    public void avilableTickets(){
        System.out.println("LowerBerth"+availableLowerBerth);
        System.out.println("MiddleBerth"+availableMiddleBerth);
        System.out.println("UpperBerth"+availableUpperBerth);
        System.out.println("RAC"+availableRacList);
        System.out.println("WaitingList"+availableWaitingList);
    }
    public void
    passengerList(){
        // System.out.print(mapToPassenger.values());
        for(Passenger p :mapToPassenger.values()){
              System.out.println("PassengerID "+ p.passengerId);
              System.out.println("Passenger Name " + p.name);
              System.out.println("Passenger SeatNumber " + p.seatNumber + p.allotedSeat);
        }
    }
    public void cancelTicket(int id){
        Passenger p = mapToPassenger.get(id);
      mapToPassenger.remove(Integer.valueOf(id));
      bookedTicket.remove(Integer.valueOf(id));

    //   int freePostion  =p.seatNumber;
    System.out.println("---------------cancelled Successfully");

      if(p.allotedSeat.equals("L")){
        availableLowerBerth++;
        lowerBerthSeats.add(p.seatNumber);
      }
      else if(p.allotedSeat.equals("M")){
        availableMiddleBerth++;
        middleBerthsPositions.add(p.seatNumber);
      }
      else if(p.allotedSeat.equals("U")){
        availableUpperBerth++;
        upperBerthsPositions.add(p.seatNumber);
      }

     if(racList.size()>0){

        Passenger passengerfromRAC = mapToPassenger.get(racList.poll());
        racPositions.add(passengerfromRAC.passengerId);
        racList.remove(Integer.valueOf(passengerfromRAC.passengerId));
        availableRacList++;
        if(waitingList.size()>0)
        {

            Passenger passengerfromWL = mapToPassenger.get(waitingList.poll());
            waitingListPositions.add(passengerfromWL.passengerId);
            waitingList.remove(Integer.valueOf(passengerfromWL.passengerId));
            
            passengerfromWL.seatNumber = racPositions.get(0);
            passengerfromWL.allotedSeat = "RAC";
            racPositions.remove(0);
            racList.add(passengerfromWL.passengerId);
            availableRacList--;
            availableWaitingList++;
        }
     
    
    Main.bookTicket(passengerfromRAC);
     }
    }
}

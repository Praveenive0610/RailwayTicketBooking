import java.util.Scanner;

public class Main {

    public static void bookTicket(Passenger p){
        TicketBooker ticketBooker = new TicketBooker();
        if(ticketBooker.availableWaitingList==0){
            System.out.println("No tickets available");
            return;
        }
        System.out.print(p.berthPreference.toLowerCase());
        if((p.berthPreference.toLowerCase().equals("l")&& ticketBooker.availableLowerBerth>0) ||
        (p.berthPreference.toLowerCase().equals("m") && ticketBooker.availableMiddleBerth>0)||
        (p.berthPreference.toLowerCase().equals("u") && ticketBooker.availableUpperBerth>0) )
        {
        if(p.berthPreference.toLowerCase().equals("l") && ticketBooker.availableLowerBerth>0){
             System.out.println("Given Ticket Lower berth");
            ticketBooker.bookTicket(p,ticketBooker.lowerBerthSeats.get(0),"L");
            ticketBooker.lowerBerthSeats.remove(0);
            ticketBooker.availableLowerBerth--;
        }
        else if(p.berthPreference.toLowerCase().equals("m") && ticketBooker.availableMiddleBerth>0)
        {
            System.out.println("Middle Berth Given");
            ticketBooker.bookTicket((p), ticketBooker.middleBerthsPositions.get(0), "M");
            ticketBooker.middleBerthsPositions.remove(0);
            ticketBooker.availableMiddleBerth--;
        }
        else if(p.berthPreference.toLowerCase().equals("u") && ticketBooker.availableUpperBerth>0)
        {
            System.out.println("Upper Berth Given");
            ticketBooker.bookTicket((p), ticketBooker.upperBerthsPositions.get(0), "U");
            ticketBooker.upperBerthsPositions.remove(0);
            ticketBooker.availableUpperBerth--;
        }
    }
    else{
        if(ticketBooker.availableRacList==0){
            System.out.println("RAC Also closed Your an WaitingList");
            ticketBooker.addWaitingList(p, ticketBooker.waitingListPositions.get(0), "WL");
            ticketBooker.waitingListPositions.remove(0);
            ticketBooker.availableWaitingList--;

        }
        if(ticketBooker.availableRacList>0){
            System.out.print("RAC Available");
            ticketBooker.addRac(p,ticketBooker.racPositions.get(0),"RAC");
            ticketBooker.racPositions.remove(0);
            ticketBooker.availableRacList--;

        }
    }

        
    }
    public static void cancelTicket(int id){
       TicketBooker tBooker = new TicketBooker();
       if(!tBooker.mapToPassenger.containsKey(id)){
        System.out.println("Passenger Detail wrong");
        return;
       }
       tBooker.cancelTicket(id);
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean loopIteration = true;
        while (loopIteration) {
         System.out.println("1.Book Ticket \n 2.Cancel Ticket \n 3.Available Tickets \n 4.Booked Tickets \n 5.Exit");
         int value = scan.nextInt();
         switch (value){
            case 1:
               {
                System.out.println("Enter Passenger Name and Age && Berth Preferenece(L,M,U)");
                String passengerName = scan.next();
                int passengerAge = scan.nextInt();
                String passengerBerthPreference = scan.next();
                Passenger p = new Passenger(passengerName, passengerAge, passengerBerthPreference);
                bookTicket(p);
                break;
               }
               case 2:{
                  System.out.println("Enter PassengerID");
                  int cancelId = scan.nextInt();
                  cancelTicket(cancelId);
                  break;
               }
            case 3:{
                TicketBooker tBooker = new TicketBooker();
                tBooker.avilableTickets();
                break;
            }
            case 4:{
                TicketBooker tBooker = new TicketBooker();
                tBooker.passengerList();
                break;
            }
             case 5:
             {
                loopIteration =false;
             }
            default:
                break;
        }
    }
}
}

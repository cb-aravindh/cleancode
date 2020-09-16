package chapter_3;

import java.util.*;

public class HotelManagement {

    private static final String F = "Filled";
    private static final String N = "NotFilled";
    public static List<String> rooms = Arrays.asList(F,F,N,N,N,N,N,N,N,N,N);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the no of rooms guest needed");
        int roomCount = sc.nextInt();
        if(checkRoomsAvl(roomCount)){
            System.out.println("Enter the no of days going to stay");
            int days = sc.nextInt();
            System.out.println("Enter the no of guests");
            int guestCount = sc.nextInt();
            double Tprice = getprice(days,guestCount);
            System.out.println("Total Price : "+Tprice);
        }
        else {
            System.out.println("Rooms are full");
        }

    }

    private static double getprice(int days, int guestCount) {
        int oneWeek = 7;
        int twoWeek = 14;
        double priceForOnePerson;
        double taxPercent;
        double totalAmt;
        double GST;

        if(days < oneWeek){
            priceForOnePerson = 1000;
            taxPercent= 25.60;
            totalAmt = (priceForOnePerson * guestCount) * days;
            GST = (totalAmt/100) * taxPercent;
            return totalAmt + GST;
        }
        else if (days > oneWeek && days < twoWeek){
            priceForOnePerson = 950;
            taxPercent= 20.15;
            totalAmt =(priceForOnePerson * guestCount) * days;
            GST = (totalAmt/100) * taxPercent;
            return totalAmt + GST;
        }
        else{
            priceForOnePerson = 800;
            taxPercent= 17.36;
            totalAmt = (priceForOnePerson * guestCount) * days;
            GST = (totalAmt/100) * taxPercent;
            return totalAmt + GST;
        }

    }

    private static boolean checkRoomsAvl(int roomCount) {
        int roomAvailable = Collections.frequency(rooms,N);
        return roomAvailable < roomCount ? false : true;
    }
}

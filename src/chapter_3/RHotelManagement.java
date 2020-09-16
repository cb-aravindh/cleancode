package chapter_3;

import java.util.*;

public class RHotelManagement {

    private static final String F = "Filled";
    private static final String N = "NotFilled";
    public static List<String> rooms = Arrays.asList(F,F,N,N,N,N,N,N,N,N,N);

    public static final int oneWeek = 7;
    public static final int twoWeek = 14;

    public static double priceForOnePerson;
    public static double taxPercent;
    public static double totalAmt;
    public static double GST;

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

    private static boolean checkRoomsAvl(int roomCount) {
        int roomAvailable = Collections.frequency(rooms,N);
        return roomAvailable < roomCount ? false : true;
    }

    private static double getprice(int days, int guestCount) {

        if(days < oneWeek){
            return lessThenOneWeekStay( days, guestCount);
        }
        else if (days > oneWeek && days < twoWeek){
            return moreThenOneWeekStay(days, guestCount);
        }
        else{
            return moreThenTwoWeekStay(days, guestCount);

        }

    }
    private static double lessThenOneWeekStay(int days, int guestCount){
        priceForOnePerson = 1000;
        taxPercent= 25.60;
        totalAmt = (priceForOnePerson * guestCount) * days;
        GST = (totalAmt/100) * taxPercent;
        return totalAmt + GST;
    }

    private static double moreThenOneWeekStay (int days, int guestCount){
        priceForOnePerson = 950;
        taxPercent= 20.15;
        totalAmt =(priceForOnePerson * guestCount) * days;
        GST = (totalAmt/100) * taxPercent;
        return totalAmt + GST;
    }

    private static double moreThenTwoWeekStay (int days, int guestCount){
        priceForOnePerson = 800;
        taxPercent= 17.36;
        totalAmt = (priceForOnePerson * guestCount) * days;
        GST = (totalAmt/100) * taxPercent;
        return totalAmt + GST;
    }

}

package chapter_3;

import java.util.*;

enum  PricePerPerson {
    LESS_THAN_ONE_WEEK(1000,25.60),MORE_THAN_ONE_WEEK(950,20.15),MORE_THAN_TWO_WEEK(800,17.36);

    private  int amount;
    private double taxPercent;

    PricePerPerson( int amount ,double taxPercent){
        this.amount = amount;
        this.taxPercent = taxPercent;
    }

    public int getAmount() {
        return amount;
    }
    public double getTaxPercent() {
        return taxPercent;
    }
}

public class RHotelManagement {

    private static final String F = "Filled";
    private static final String N = "NotFilled";
    public static List<String> rooms = Arrays.asList(F,F,N,N,N,N,N,N,N,N,N);

    public static final int oneWeek = 7;
    public static final int twoWeek = 14;

    private  PricePerPerson priceForOnePerson;
    private  double days;
    private  double guestCount;

    RHotelManagement(PricePerPerson priceForOnePerson,double days ,double guestCount){
        this.priceForOnePerson = priceForOnePerson;
        this.days = days;
        this.guestCount = guestCount;
    }

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
        int roomUnOccupied = Collections.frequency(rooms,N);
        return roomUnOccupied < roomCount ? false : true;
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
        return new RHotelManagement(PricePerPerson.LESS_THAN_ONE_WEEK, days, guestCount).execute();
    }

    private static double moreThenOneWeekStay (int days, int guestCount){
        return new RHotelManagement(PricePerPerson.MORE_THAN_ONE_WEEK, days, guestCount).execute();
    }

    private static double moreThenTwoWeekStay (int days, int guestCount){
        return new RHotelManagement(PricePerPerson.MORE_THAN_TWO_WEEK, days, guestCount).execute();
    }

    private double execute(){
        double totalAmt =(this.priceForOnePerson.getAmount() * this.guestCount) * this.days;
        double GST = (this.priceForOnePerson.getTaxPercent()/100) * totalAmt;
        return totalAmt + GST;
    }


}
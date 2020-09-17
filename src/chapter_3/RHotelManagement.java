package chapter_3;

import java.util.*;

enum  PricePerPerson {
    LESS_THAN_ONE_WEEK(1000,25.60), MORE_THAN_ONE_WEEK(950,20.15), MORE_THAN_TWO_WEEK(800,17.36);

    private int amount;
    private double taxPercent;

    PricePerPerson( int amount ,double taxPercent){
        this.amount = amount;
        this.taxPercent = taxPercent;
    }

    int getAmount() {
        return amount;
    }
    double getTaxPercent() {
        return taxPercent;
    }
}

public class RHotelManagement {

    private final String F = "Filled";
    private final String N = "NotFilled";
    private List<String> rooms = Arrays.asList(F, F, N, N, N, N, N, N, N, N, N);

    private final int oneWeek = 7;
    private final int twoWeek = 14;

    private PricePerPerson priceForOnePerson;
    private double days;
    private double guestCount;
    RHotelManagement(){}
    RHotelManagement(PricePerPerson priceForOnePerson, double days, double guestCount){
        this.priceForOnePerson = priceForOnePerson;
        this.days = days;
        this.guestCount = guestCount;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner( System.in );
        RHotelManagement hotel = new RHotelManagement();

        System.out.println("Enter the no of rooms guest needed");
        int roomCount = sc.nextInt();

        if( hotel.checkRoomsAvl(roomCount) ){
            System.out.println("Enter the no of days going to stay");
            int days = sc.nextInt();

            System.out.println("Enter the no of guests");
            int guestCount = sc.nextInt();

            double Tprice = hotel.getprice(days,guestCount);
            System.out.println("Total Price : "+Tprice);
        }
        else {
            System.out.println("Rooms are full");
        }
    }

    private boolean checkRoomsAvl(int roomCount) {
        int roomUnOccupied = Collections.frequency(rooms,N);
        return roomUnOccupied < roomCount ? false : true;
    }

    private double getprice(int days, int guestCount) {

        if(days < oneWeek){
            return lessThanOneWeekStay( days, guestCount);
        }
        else if (days > oneWeek && days < twoWeek){
            return moreThanOneWeekStay( days, guestCount);
        }
        else{
            return moreThanTwoWeekStay( days, guestCount);
        }

    }
    private double lessThanOneWeekStay(int days, int guestCount){
        return new RHotelManagement(PricePerPerson.LESS_THAN_ONE_WEEK, days, guestCount).execute();
    }

    private double moreThanOneWeekStay (int days, int guestCount){
        return new RHotelManagement(PricePerPerson.MORE_THAN_ONE_WEEK, days, guestCount).execute();
    }

    private double moreThanTwoWeekStay (int days, int guestCount){
        return new RHotelManagement(PricePerPerson.MORE_THAN_TWO_WEEK, days, guestCount).execute();
    }

    private double execute(){
        double totalAmt =( this.priceForOnePerson.getAmount() * this.guestCount ) * this.days;
        double GST = ( this.priceForOnePerson.getTaxPercent() / 100 ) * totalAmt;
        return totalAmt + GST;
    }


}
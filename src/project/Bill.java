package project;

public class Bill {

    public static float calculate(int type, int num, holder h) {

        float roomCharge = 0;
        float foodCharge = 0;

        if(type==1) {
            roomCharge = 4000;
            foodCharge = h.luxuryDouble[num].getFoodBill();
        }

        if(type==2) {
            roomCharge = 3000;
            foodCharge = h.deluxeDouble[num].getFoodBill();
        }

        if(type==3) {
            roomCharge = 2200;
            foodCharge = h.luxurySingle[num].getFoodBill();
        }

        if(type==4) {
            roomCharge = 1200;
            foodCharge = h.deluxeSingle[num].getFoodBill();
        }

        return roomCharge + foodCharge;
    }
}

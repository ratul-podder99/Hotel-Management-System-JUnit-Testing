package project;

import org.junit.jupiter.api.*;
import java.lang.reflect.Field;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Bill Class Tests")
public class BillTest {

    private holder h;

    @BeforeEach
    void setUp() {
        h = new holder();

        // Pre-book one room of each type at index 0
        h.luxuryDouble[0]  = new Doubleroom("Alice", "01711111111", "Female",
                                             "Bob",   "01722222222", "Male");

        h.deluxeDouble[0]  = new Doubleroom("Carol", "01733333333", "Female",
                                             "Dave",  "01744444444", "Male");

        h.luxurySingle[0]  = new Singleroom("Eve",   "01755555555", "Female");
        h.deluxeSingle[0]  = new Singleroom("Frank", "01766666666", "Male");
    }

    // ── Helper: create Food with known price via Reflection ──
    private Food makeFoodWithPrice(float price) throws Exception {
        Food f = (Food) sun.misc.Unsafe.class
                .getDeclaredMethod("allocateInstance", Class.class)
                .invoke(getUnsafe(), Food.class);
        Field priceField = Food.class.getDeclaredField("price");
        priceField.setAccessible(true);
        priceField.set(f, price);
        return f;
    }

    private sun.misc.Unsafe getUnsafe() throws Exception {
        Field f = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        return (sun.misc.Unsafe) f.get(null);
    }
    
    // 1. Type 1 — Luxury Double Room (4000 TK/day)

    @Test
    @DisplayName("TC01 – Type 1 (Luxury Double): bill = 4000 when no food ordered")
    void testLuxuryDoubleNoFood() {
        float bill = Bill.calculate(1, 0, h);
        assertEquals(4000.0f, bill, 0.001f);
    }

    @Test
    @DisplayName("TC02 – Type 1 (Luxury Double): bill = 4000 + food charge")
    void testLuxuryDoubleWithFood() throws Exception {
        h.luxuryDouble[0].food.add(makeFoodWithPrice(40.0f));  // Coffee
        h.luxuryDouble[0].food.add(makeFoodWithPrice(70.0f));  // Noodles
        // Room: 4000 + Food: 110 = 4110
        float bill = Bill.calculate(1, 0, h);
        assertEquals(4110.0f, bill, 0.001f);
    }

    @Test
    @DisplayName("TC03 – Type 1 (Luxury Double): bill at different index (num=5)")
    void testLuxuryDoubleAtIndex5() {
        h.luxuryDouble[5] = new Doubleroom("Greg", "01777777777", "Male",
                                            "Hana", "01788888888", "Female");
        float bill = Bill.calculate(1, 5, h);
        assertEquals(4000.0f, bill, 0.001f);
    }

    // 2. Type 2 — Deluxe Double Room (3000 TK/day)

    @Test
    @DisplayName("TC04 – Type 2 (Deluxe Double): bill = 3000 when no food ordered")
    void testDeluxeDoubleNoFood() {
        float bill = Bill.calculate(2, 0, h);
        assertEquals(3000.0f, bill, 0.001f);
    }

    @Test
    @DisplayName("TC05 – Type 2 (Deluxe Double): bill = 3000 + food charge")
    void testDeluxeDoubleWithFood() throws Exception {
        h.deluxeDouble[0].food.add(makeFoodWithPrice(20.0f));  // Tea
        h.deluxeDouble[0].food.add(makeFoodWithPrice(50.0f));  // Sandwich
        // Room: 3000 + Food: 70 = 3070
        float bill = Bill.calculate(2, 0, h);
        assertEquals(3070.0f, bill, 0.001f);
    }

    @Test
    @DisplayName("TC06 – Type 2 (Deluxe Double): room charge is exactly 3000 (less than type 1)")
    void testDeluxeDoubleRoomChargeLessThanLuxury() {
        float luxuryBill = Bill.calculate(1, 0, h);
        float deluxeBill = Bill.calculate(2, 0, h);
        assertTrue(deluxeBill < luxuryBill,
                "Deluxe double (3000) should cost less than Luxury double (4000)");
    }

    // 3. Type 3 — Luxury Single Room (2200 TK/day)

    @Test
    @DisplayName("TC07 – Type 3 (Luxury Single): bill = 2200 when no food ordered")
    void testLuxurySingleNoFood() {
        float bill = Bill.calculate(3, 0, h);
        assertEquals(2200.0f, bill, 0.001f);
    }

    @Test
    @DisplayName("TC08 – Type 3 (Luxury Single): bill = 2200 + food charge")
    void testLuxurySingleWithFood() throws Exception {
        h.luxurySingle[0].food.add(makeFoodWithPrice(60.0f));  // Pasta
        h.luxurySingle[0].food.add(makeFoodWithPrice(30.0f));  // Coke
        // Room: 2200 + Food: 90 = 2290
        float bill = Bill.calculate(3, 0, h);
        assertEquals(2290.0f, bill, 0.001f);
    }

    @Test
    @DisplayName("TC09 – Type 3 (Luxury Single): bill at different index (num=3)")
    void testLuxurySingleAtIndex3() {
        h.luxurySingle[3] = new Singleroom("Ivy", "01799999999", "Female");
        float bill = Bill.calculate(3, 3, h);
        assertEquals(2200.0f, bill, 0.001f);
    }

    // 4. Type 4 — Deluxe Single Room (1200 TK/day)

    @Test
    @DisplayName("TC10 – Type 4 (Deluxe Single): bill = 1200 when no food ordered")
    void testDeluxeSingleNoFood() {
        float bill = Bill.calculate(4, 0, h);
        assertEquals(1200.0f, bill, 0.001f);
    }

    @Test
    @DisplayName("TC11 – Type 4 (Deluxe Single): bill = 1200 + food charge")
    void testDeluxeSingleWithFood() throws Exception {
        h.deluxeSingle[0].food.add(makeFoodWithPrice(20.0f));  // Tea
        h.deluxeSingle[0].food.add(makeFoodWithPrice(40.0f));  // Coffee
        h.deluxeSingle[0].food.add(makeFoodWithPrice(70.0f));  // Noodles
        // Room: 1200 + Food: 130 = 1330
        float bill = Bill.calculate(4, 0, h);
        assertEquals(1330.0f, bill, 0.001f);
    }

    @Test
    @DisplayName("TC12 – Type 4 (Deluxe Single): cheapest room type (1200)")
    void testDeluxeSingleCheapest() {
        float t1 = Bill.calculate(1, 0, h); // 4000
        float t2 = Bill.calculate(2, 0, h); // 3000
        float t3 = Bill.calculate(3, 0, h); // 2200
        float t4 = Bill.calculate(4, 0, h); // 1200
        assertTrue(t4 < t3 && t3 < t2 && t2 < t1,
                "Room charges must decrease: Luxury Double > Deluxe Double > Luxury Single > Deluxe Single");
    }

    // 5. Room Charge Hierarchy Tests

    @Test
    @DisplayName("TC13 – All four room charges are distinct and in correct order")
    void testAllRoomChargesOrdered() {
        assertEquals(4000.0f, Bill.calculate(1, 0, h), 0.001f);
        assertEquals(3000.0f, Bill.calculate(2, 0, h), 0.001f);
        assertEquals(2200.0f, Bill.calculate(3, 0, h), 0.001f);
        assertEquals(1200.0f, Bill.calculate(4, 0, h), 0.001f);
    }

    @Test
    @DisplayName("TC14 – Total bill equals room charge + food bill (type 1)")
    void testTotalBillEqualsRoomPlusFood() throws Exception {
        h.luxuryDouble[0].food.add(makeFoodWithPrice(100.0f));
        float foodBill   = h.luxuryDouble[0].getFoodBill();  // 100
        float totalBill  = Bill.calculate(1, 0, h);           // 4000 + 100 = 4100
        assertEquals(4000.0f + foodBill, totalBill, 0.001f);
    }

    @Test
    @DisplayName("TC15 – Bill with multiple food items sums correctly (type 4)")
    void testBillMultipleFoodItemsType4() throws Exception {
        h.deluxeSingle[0].food.add(makeFoodWithPrice(20.0f));
        h.deluxeSingle[0].food.add(makeFoodWithPrice(20.0f));
        h.deluxeSingle[0].food.add(makeFoodWithPrice(20.0f));
        // 3 × Tea = 60 TK food + 1200 TK room = 1260
        assertEquals(1260.0f, Bill.calculate(4, 0, h), 0.001f);
    }
}
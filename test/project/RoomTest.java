package project;

import org.junit.jupiter.api.*;
import java.lang.reflect.Field;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Singleroom and Doubleroom Class Tests")
public class RoomTest {

    // ── Helper: create a Food object with a known price using Reflection ──
    private Food makeFoodWithPrice(float price) throws Exception {
        // Allocate Food without calling its Scanner constructor
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

    // PART A — Singleroom Tests

    // ── A1: Constructor Tests ──

    @Test
    @DisplayName("TC01 – Singleroom constructor stores name correctly")
    void testSingleroomStoreName() {
        Singleroom s = new Singleroom("Alice", "01711223344", "Female");
        assertEquals("Alice", s.name);
    }

    @Test
    @DisplayName("TC02 – Singleroom constructor stores contact correctly")
    void testSingleroomStoreContact() {
        Singleroom s = new Singleroom("Alice", "01711223344", "Female");
        assertEquals("01711223344", s.contact);
    }

    @Test
    @DisplayName("TC03 – Singleroom constructor stores gender correctly")
    void testSingleroomStoreGender() {
        Singleroom s = new Singleroom("Alice", "01711223344", "Female");
        assertEquals("Female", s.gender);
    }

    @Test
    @DisplayName("TC04 – Singleroom food list is initialized and empty on creation")
    void testSingleroomFoodListInitiallyEmpty() {
        Singleroom s = new Singleroom("Bob", "01812345678", "Male");
        assertNotNull(s.food);
        assertEquals(0, s.food.size());
    }

    @Test
    @DisplayName("TC05 – Two Singleroom objects are independent (no shared state)")
    void testTwoSingleroomsAreIndependent() {
        Singleroom s1 = new Singleroom("Alice", "01711111111", "Female");
        Singleroom s2 = new Singleroom("Bob",   "01722222222", "Male");
        assertNotEquals(s1.name, s2.name);
        assertNotEquals(s1.contact, s2.contact);
        assertNotEquals(s1.gender, s2.gender);
    }

    // ── A2: getFoodBill() Tests ──

    @Test
    @DisplayName("TC06 – getFoodBill() returns 0.0 when no food ordered")
    void testSingleroomFoodBillNoFood() {
        Singleroom s = new Singleroom("Alice", "01711223344", "Female");
        assertEquals(0.0f, s.getFoodBill(), 0.001f);
    }

    @Test
    @DisplayName("TC07 – getFoodBill() returns correct price for one food item")
    void testSingleroomFoodBillOneItem() throws Exception {
        Singleroom s = new Singleroom("Alice", "01711223344", "Female");
        s.food.add(makeFoodWithPrice(40.0f)); // Coffee
        assertEquals(40.0f, s.getFoodBill(), 0.001f);
    }

    @Test
    @DisplayName("TC08 – getFoodBill() returns correct sum for multiple food items")
    void testSingleroomFoodBillMultipleItems() throws Exception {
        Singleroom s = new Singleroom("Alice", "01711223344", "Female");
        s.food.add(makeFoodWithPrice(20.0f));  // Tea
        s.food.add(makeFoodWithPrice(50.0f));  // Sandwich
        s.food.add(makeFoodWithPrice(70.0f));  // Noodles
        // 20 + 50 + 70 = 140
        assertEquals(140.0f, s.getFoodBill(), 0.001f);
    }

    @Test
    @DisplayName("TC09 – getFoodBill() returns correct value after adding food sequentially")
    void testSingleroomFoodBillSequentialAdd() throws Exception {
        Singleroom s = new Singleroom("Bob", "01812345678", "Male");
        s.food.add(makeFoodWithPrice(30.0f)); // Coke
        assertEquals(30.0f, s.getFoodBill(), 0.001f);

        s.food.add(makeFoodWithPrice(60.0f)); // Pasta
        assertEquals(90.0f, s.getFoodBill(), 0.001f);
    }

    @Test
    @DisplayName("TC10 – getFoodBill() handles large food bill correctly")
    void testSingleroomFoodBillLargeOrder() throws Exception {
        Singleroom s = new Singleroom("Bob", "01812345678", "Male");
        // 5 × 70 TK (noodles) = 350
        for (int i = 0; i < 5; i++) s.food.add(makeFoodWithPrice(70.0f));
        assertEquals(350.0f, s.getFoodBill(), 0.001f);
    }
    
    // PART B — Doubleroom Tests

    @Test
    @DisplayName("TC11 – Doubleroom constructor stores guest1 name correctly")
    void testDoubleroomStoreName1() {
        Doubleroom d = new Doubleroom("Alice", "01711111111", "Female",
                                      "Bob",   "01722222222", "Male");
        assertEquals("Alice", d.name1);
    }

    @Test
    @DisplayName("TC12 – Doubleroom constructor stores guest1 contact correctly")
    void testDoubleroomStoreContact1() {
        Doubleroom d = new Doubleroom("Alice", "01711111111", "Female",
                                      "Bob",   "01722222222", "Male");
        assertEquals("01711111111", d.contact1);
    }

    @Test
    @DisplayName("TC13 – Doubleroom constructor stores guest1 gender correctly")
    void testDoubleroomStoreGender1() {
        Doubleroom d = new Doubleroom("Alice", "01711111111", "Female",
                                      "Bob",   "01722222222", "Male");
        assertEquals("Female", d.gender1);
    }

    @Test
    @DisplayName("TC14 – Doubleroom constructor stores guest2 name correctly")
    void testDoubleroomStoreName2() {
        Doubleroom d = new Doubleroom("Alice", "01711111111", "Female",
                                      "Bob",   "01722222222", "Male");
        assertEquals("Bob", d.name2);
    }

    @Test
    @DisplayName("TC15 – Doubleroom constructor stores guest2 contact correctly")
    void testDoubleroomStoreContact2() {
        Doubleroom d = new Doubleroom("Alice", "01711111111", "Female",
                                      "Bob",   "01722222222", "Male");
        assertEquals("01722222222", d.contact2);
    }

    @Test
    @DisplayName("TC16 – Doubleroom constructor stores guest2 gender correctly")
    void testDoubleroomStoreGender2() {
        Doubleroom d = new Doubleroom("Alice", "01711111111", "Female",
                                      "Bob",   "01722222222", "Male");
        assertEquals("Male", d.gender2);
    }

    @Test
    @DisplayName("TC17 – Doubleroom food list is initialized and empty on creation")
    void testDoubleroomFoodListInitiallyEmpty() {
        Doubleroom d = new Doubleroom("Alice", "01711111111", "Female",
                                      "Bob",   "01722222222", "Male");
        assertNotNull(d.food);
        assertEquals(0, d.food.size());
    }

    @Test
    @DisplayName("TC18 – Two Doubleroom objects are independent")
    void testTwoDoubleroomsAreIndependent() {
        Doubleroom d1 = new Doubleroom("Alice", "01711111111", "Female",
                                        "Bob",  "01722222222", "Male");
        Doubleroom d2 = new Doubleroom("Carol", "01733333333", "Female",
                                        "Dave", "01744444444", "Male");
        assertNotEquals(d1.name1, d2.name1);
        assertNotEquals(d1.name2, d2.name2);
    }

    // ── B2: getFoodBill() Tests ──

    @Test
    @DisplayName("TC19 – Doubleroom getFoodBill() returns 0.0 when no food ordered")
    void testDoubleroomFoodBillNoFood() {
        Doubleroom d = new Doubleroom("Alice", "01711111111", "Female",
                                      "Bob",   "01722222222", "Male");
        assertEquals(0.0f, d.getFoodBill(), 0.001f);
    }

    @Test
    @DisplayName("TC20 – Doubleroom getFoodBill() correct for one food item")
    void testDoubleroomFoodBillOneItem() throws Exception {
        Doubleroom d = new Doubleroom("Alice", "01711111111", "Female",
                                      "Bob",   "01722222222", "Male");
        d.food.add(makeFoodWithPrice(50.0f)); // Sandwich
        assertEquals(50.0f, d.getFoodBill(), 0.001f);
    }

    @Test
    @DisplayName("TC21 – Doubleroom getFoodBill() correct sum for multiple items")
    void testDoubleroomFoodBillMultipleItems() throws Exception {
        Doubleroom d = new Doubleroom("Alice", "01711111111", "Female",
                                      "Bob",   "01722222222", "Male");
        d.food.add(makeFoodWithPrice(20.0f));  // Tea
        d.food.add(makeFoodWithPrice(40.0f));  // Coffee
        d.food.add(makeFoodWithPrice(60.0f));  // Pasta
        // 20 + 40 + 60 = 120
        assertEquals(120.0f, d.getFoodBill(), 0.001f);
    }

    @Test
    @DisplayName("TC22 – Doubleroom getFoodBill() handles decimal food prices correctly")
    void testDoubleroomFoodBillDecimal() throws Exception {
        Doubleroom d = new Doubleroom("Alice", "01711111111", "Female",
                                      "Bob",   "01722222222", "Male");
        d.food.add(makeFoodWithPrice(25.5f));
        d.food.add(makeFoodWithPrice(14.5f));
        assertEquals(40.0f, d.getFoodBill(), 0.001f);
    }
}
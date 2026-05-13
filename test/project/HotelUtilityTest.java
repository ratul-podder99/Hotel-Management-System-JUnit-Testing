package project;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Hotel, holder, NotAvailable, write Tests")
public class HotelUtilityTest {

    // PART A — Hotel.getIndex() Tests

    // ── Type 1: Luxury Double (101–110) ──

    @Test
    @DisplayName("TC01 – Type 1, room 101 → index 0 (first room)")
    void testType1Room101() {
        assertEquals(0, Hotel.getIndex(1, 101));
    }

    @Test
    @DisplayName("TC02 – Type 1, room 105 → index 4 (middle room)")
    void testType1Room105() {
        assertEquals(4, Hotel.getIndex(1, 105));
    }

    @Test
    @DisplayName("TC03 – Type 1, room 110 → index 9 (last room)")
    void testType1Room110() {
        assertEquals(9, Hotel.getIndex(1, 110));
    }

    @Test
    @DisplayName("TC04 – Type 1, room 100 → -1 (below valid range)")
    void testType1Room100Invalid() {
        assertEquals(-1, Hotel.getIndex(1, 100));
    }

    @Test
    @DisplayName("TC05 – Type 1, room 111 → -1 (above valid range)")
    void testType1Room111Invalid() {
        assertEquals(-1, Hotel.getIndex(1, 111));
    }

    // ── Type 2: Deluxe Double (111–130) ──

    @Test
    @DisplayName("TC06 – Type 2, room 111 → index 0 (first room)")
    void testType2Room111() {
        assertEquals(0, Hotel.getIndex(2, 111));
    }

    @Test
    @DisplayName("TC07 – Type 2, room 120 → index 9 (middle room)")
    void testType2Room120() {
        assertEquals(9, Hotel.getIndex(2, 120));
    }

    @Test
    @DisplayName("TC08 – Type 2, room 130 → index 19 (last room)")
    void testType2Room130() {
        assertEquals(19, Hotel.getIndex(2, 130));
    }

    @Test
    @DisplayName("TC09 – Type 2, room 110 → -1 (below valid range)")
    void testType2Room110Invalid() {
        assertEquals(-1, Hotel.getIndex(2, 110));
    }

    @Test
    @DisplayName("TC10 – Type 2, room 131 → -1 (above valid range)")
    void testType2Room131Invalid() {
        assertEquals(-1, Hotel.getIndex(2, 131));
    }

    // ── Type 3: Luxury Single (131–140) ──

    @Test
    @DisplayName("TC11 – Type 3, room 131 → index 0 (first room)")
    void testType3Room131() {
        assertEquals(0, Hotel.getIndex(3, 131));
    }

    @Test
    @DisplayName("TC12 – Type 3, room 135 → index 4 (middle room)")
    void testType3Room135() {
        assertEquals(4, Hotel.getIndex(3, 135));
    }

    @Test
    @DisplayName("TC13 – Type 3, room 140 → index 9 (last room)")
    void testType3Room140() {
        assertEquals(9, Hotel.getIndex(3, 140));
    }

    @Test
    @DisplayName("TC14 – Type 3, room 130 → -1 (below valid range)")
    void testType3Room130Invalid() {
        assertEquals(-1, Hotel.getIndex(3, 130));
    }

    @Test
    @DisplayName("TC15 – Type 3, room 141 → -1 (above valid range)")
    void testType3Room141Invalid() {
        assertEquals(-1, Hotel.getIndex(3, 141));
    }

    // ── Type 4: Deluxe Single (141–150) ──

    @Test
    @DisplayName("TC16 – Type 4, room 141 → index 0 (first room)")
    void testType4Room141() {
        assertEquals(0, Hotel.getIndex(4, 141));
    }

    @Test
    @DisplayName("TC17 – Type 4, room 145 → index 4 (middle room)")
    void testType4Room145() {
        assertEquals(4, Hotel.getIndex(4, 145));
    }

    @Test
    @DisplayName("TC18 – Type 4, room 150 → index 9 (last room)")
    void testType4Room150() {
        assertEquals(9, Hotel.getIndex(4, 150));
    }

    @Test
    @DisplayName("TC19 – Type 4, room 140 → -1 (below valid range)")
    void testType4Room140Invalid() {
        assertEquals(-1, Hotel.getIndex(4, 140));
    }

    @Test
    @DisplayName("TC20 – Type 4, room 151 → -1 (above valid range)")
    void testType4Room151Invalid() {
        assertEquals(-1, Hotel.getIndex(4, 151));
    }

    // ── Invalid type ──

    @Test
    @DisplayName("TC21 – Invalid type 5 with valid room 101 → -1")
    void testInvalidType5() {
        assertEquals(-1, Hotel.getIndex(5, 101));
    }

    @Test
    @DisplayName("TC22 – Invalid type 0 → -1")
    void testInvalidType0() {
        assertEquals(-1, Hotel.getIndex(0, 101));
    }

    @Test
    @DisplayName("TC23 – Negative room number returns -1")
    void testNegativeRoomNumber() {
        assertEquals(-1, Hotel.getIndex(1, -1));
    }

    // PART B — Hotel.countAvailable() Tests

    @Test
    @DisplayName("TC24 – All null array (all rooms free) returns full count")
    void testCountAvailableAllFree() {
        Object[] rooms = new Object[10]; // all null by default
        assertEquals(10, Hotel.countAvailable(rooms));
    }

    @Test
    @DisplayName("TC25 – All filled array (no free rooms) returns 0")
    void testCountAvailableNoneFree() {
        Object[] rooms = new Object[5];
        for (int i = 0; i < 5; i++)
            rooms[i] = new Singleroom("Guest", "01711111111", "Male");
        assertEquals(0, Hotel.countAvailable(rooms));
    }

    @Test
    @DisplayName("TC26 – Mixed array: 3 booked out of 5 → 2 available")
    void testCountAvailableMixed() {
        Object[] rooms = new Object[5];
        rooms[0] = new Singleroom("Alice", "01711111111", "Female");
        rooms[2] = new Singleroom("Bob",   "01722222222", "Male");
        rooms[4] = new Singleroom("Carol", "01733333333", "Female");
        // rooms[1] and rooms[3] are null → 2 available
        assertEquals(2, Hotel.countAvailable(rooms));
    }

    @Test
    @DisplayName("TC27 – Single element array with null → 1 available")
    void testCountAvailableSingleNull() {
        Object[] rooms = new Object[1]; // null by default
        assertEquals(1, Hotel.countAvailable(rooms));
    }

    @Test
    @DisplayName("TC28 – Single element array with value → 0 available")
    void testCountAvailableSingleBooked() {
        Object[] rooms = new Object[1];
        rooms[0] = new Singleroom("Guest", "01711111111", "Male");
        assertEquals(0, Hotel.countAvailable(rooms));
    }

    @Test
    @DisplayName("TC29 – Luxury Double array: all 10 rooms free initially")
    void testHolderLuxuryDoubleAllFree() {
        holder h = new holder();
        assertEquals(10, Hotel.countAvailable(h.luxuryDouble));
    }

    @Test
    @DisplayName("TC30 – Deluxe Double array: all 20 rooms free initially")
    void testHolderDeluxeDoubleAllFree() {
        holder h = new holder();
        assertEquals(20, Hotel.countAvailable(h.deluxeDouble));
    }

    @Test
    @DisplayName("TC31 – Luxury Single array: all 10 rooms free initially")
    void testHolderLuxurySingleAllFree() {
        holder h = new holder();
        assertEquals(10, Hotel.countAvailable(h.luxurySingle));
    }

    @Test
    @DisplayName("TC32 – Deluxe Single array: all 10 rooms free initially")
    void testHolderDeluxeSingleAllFree() {
        holder h = new holder();
        assertEquals(10, Hotel.countAvailable(h.deluxeSingle));
    }

    // ===========================================================
    // PART C — holder Class Tests
    // ===========================================================

    @Test
    @DisplayName("TC33 – holder: luxuryDouble array size is 10")
    void testHolderLuxuryDoubleArraySize() {
        holder h = new holder();
        assertEquals(10, h.luxuryDouble.length);
    }

    @Test
    @DisplayName("TC34 – holder: deluxeDouble array size is 20")
    void testHolderDeluxeDoubleArraySize() {
        holder h = new holder();
        assertEquals(20, h.deluxeDouble.length);
    }

    @Test
    @DisplayName("TC35 – holder: luxurySingle array size is 10")
    void testHolderLuxurySingleArraySize() {
        holder h = new holder();
        assertEquals(10, h.luxurySingle.length);
    }

    @Test
    @DisplayName("TC36 – holder: deluxeSingle array size is 10")
    void testHolderDeluxeSingleArraySize() {
        holder h = new holder();
        assertEquals(10, h.deluxeSingle.length);
    }

    @Test
    @DisplayName("TC37 – holder: all arrays are initialized (not null)")
    void testHolderAllArraysNotNull() {
        holder h = new holder();
        assertNotNull(h.luxuryDouble);
        assertNotNull(h.deluxeDouble);
        assertNotNull(h.luxurySingle);
        assertNotNull(h.deluxeSingle);
    }

    @Test
    @DisplayName("TC38 – holder: can assign a Doubleroom to luxuryDouble slot")
    void testHolderAssignDoubleroom() {
        holder h = new holder();
        h.luxuryDouble[0] = new Doubleroom("Alice", "01711111111", "Female",
                                            "Bob",   "01722222222", "Male");
        assertNotNull(h.luxuryDouble[0]);
    }

    @Test
    @DisplayName("TC39 – holder: can assign a Singleroom to luxurySingle slot")
    void testHolderAssignSingleroom() {
        holder h = new holder();
        h.luxurySingle[0] = new Singleroom("Eve", "01755555555", "Female");
        assertNotNull(h.luxurySingle[0]);
    }

    @Test
    @DisplayName("TC40 – holder: cancelling booking sets slot back to null")
    void testHolderCancelBooking() {
        holder h = new holder();
        h.deluxeSingle[2] = new Singleroom("Frank", "01766666666", "Male");
        assertNotNull(h.deluxeSingle[2]);
        h.deluxeSingle[2] = null;  // simulate cancel
        assertNull(h.deluxeSingle[2]);
    }

    // PART D — NotAvailable Exception Tests

    @Test
    @DisplayName("TC41 – NotAvailable is a subclass of Exception")
    void testNotAvailableIsException() {
        NotAvailable ex = new NotAvailable();
        assertInstanceOf(Exception.class, ex);
    }

    @Test
    @DisplayName("TC42 – NotAvailable.toString() returns 'Room Not Available'")
    void testNotAvailableToString() {
        NotAvailable ex = new NotAvailable();
        assertEquals("Room Not Available", ex.toString());
    }

    @Test
    @DisplayName("TC43 – NotAvailable can be thrown and caught")
    void testNotAvailableCanBeThrown() {
        assertThrows(NotAvailable.class, () -> {
            throw new NotAvailable();
        });
    }

    @Test
    @DisplayName("TC44 – NotAvailable toString is not null or empty")
    void testNotAvailableToStringNotEmpty() {
        NotAvailable ex = new NotAvailable();
        assertNotNull(ex.toString());
        assertFalse(ex.toString().isEmpty());
    }
    
    // PART E — write (Runnable) Tests

    @Test
    @DisplayName("TC45 – write implements Runnable")
    void testWriteImplementsRunnable() {
        write w = new write();
        assertInstanceOf(Runnable.class, w);
    }

    @Test
    @DisplayName("TC46 – write.run() executes without throwing any exception")
    void testWriteRunNoException() {
        write w = new write();
        assertDoesNotThrow(() -> w.run());
    }

    @Test
    @DisplayName("TC47 – write can be used in a Thread and started without error")
    void testWriteRunsInThread() throws InterruptedException {
        write w = new write();
        Thread t = new Thread(w);
        assertDoesNotThrow(t::start);
        t.join(); // wait for thread to complete
    }
}
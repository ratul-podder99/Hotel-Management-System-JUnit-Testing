# 🏨 Hotel Management System

A console-based Java application for managing hotel room bookings, food orders, and guest billing. Built as a semester project and tested using **JUnit 5** as part of a Software Testing course.

---

## 📋 Table of Contents

- [About the Project](#about-the-project)
- [Features](#features)
- [Room Categories](#room-categories)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Running the Application](#running-the-application)
- [Running the Tests](#running-the-tests)
- [Test Coverage Summary](#test-coverage-summary)
- [Technologies Used](#technologies-used)
- [Authors](#authors)

---

## About the Project

The Hotel Management System is a Java console application that allows hotel staff to manage all core hotel operations through a text-based menu. Guest data is validated before storage, and all booking information is persisted to disk using Java object serialization.

The project also includes a complete **JUnit 5 unit test suite** covering all testable business logic — input validation, room data storage, billing calculations, room index mapping, availability tracking, exception handling, and thread execution.

---

## Features

- 📋 **Display Room Details** — View specifications and pricing for any room category
- 🔍 **Check Availability** — See how many rooms are free in each category
- 🛏️ **Book a Room** — Reserve a single or double room with full guest details
- 🍽️ **Order Food** — Add food items to a booked room's bill
- 💳 **Checkout** — Calculate and display the total bill (room + food)
- ❌ **Cancel Booking** — Release a room back to available inventory
- 💾 **Data Persistence** — All bookings saved to `hotel.dat` via Java serialization

---

## Room Categories

| Type | Category | Room Numbers | Capacity | Rate per Day |
|------|----------|-------------|----------|-------------|
| 1 | Luxury Double | 101 – 110 | 2 Guests | 4,000 TK |
| 2 | Deluxe Double | 111 – 130 | 2 Guests | 3,000 TK |
| 3 | Luxury Single | 131 – 140 | 1 Guest  | 2,200 TK |
| 4 | Deluxe Single | 141 – 150 | 1 Guest  | 1,200 TK |

> **Total Bill = Room Charge + Food Bill**

---

## Project Structure

```
HotelManagementSystem/
│
├── src/
│   └── project/
│       ├── Bill.java            # Calculates total bill per room type
│       ├── Doubleroom.java      # Model for double-occupancy rooms
│       ├── FileSystem.java      # Save and load hotel data (serialization)
│       ├── Food.java            # Food ordering with menu and pricing
│       ├── holder.java          # Central data container (all room arrays)
│       ├── Hotel.java           # Core operations: book, order, checkout, cancel
│       ├── NotAvailable.java    # Custom exception for unavailable rooms
│       ├── Project.java         # Main entry point and menu loop
│       ├── Singleroom.java      # Model for single-occupancy rooms
│       ├── Validator.java       # Input validation (name, phone, gender)
│       └── write.java           # Runnable class for backup thread
│
├── test/
│   └── project/
│       ├── ValidatorTest.java       # 35 tests — all 3 validation methods
│       ├── RoomTest.java            # 22 tests — Singleroom + Doubleroom
│       ├── BillTest.java            # 15 tests — Bill.calculate()
│       └── HotelUtilityTest.java    # 47 tests — Hotel utilities, holder, exception, thread
│
├── .gitignore
└── README.md
```

---

## Getting Started

### Prerequisites

- **Java JDK 8 or higher** — [Download here](https://www.oracle.com/java/technologies/downloads/)
- **Eclipse IDE** (recommended) — [Download here](https://www.eclipse.org/downloads/)
- **JUnit 5** — Added via Eclipse's built-in library manager

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/HotelManagementSystem.git
   ```

2. **Open in Eclipse**
   - Go to `File` → `Import` → `General` → `Existing Projects into Workspace`
   - Browse to the cloned folder and click **Finish**

3. **Add JUnit 5 library** (for running tests)
   - Right-click the project → `Build Path` → `Add Libraries`
   - Select **JUnit** → choose **JUnit 5** → click **Finish**

### Running the Application

- Right-click `Project.java` → **Run As** → **Java Application**
- The menu will appear in the console:

```
=========================================
     Welcome to Hotel Management System
=========================================
 1. Display Room Details
 2. Display Available Rooms
 3. Book a Room
 4. Order Food
 5. Checkout
 6. Cancel Booking
 7. Exit
=========================================
Enter your choice:
```

---

## Running the Tests

1. Make sure JUnit 5 is added to the build path (see Installation step 3)
2. Right-click the **project** in the Package Explorer
3. Select **Run As** → **JUnit Test**
4. The JUnit view will show the results — all 119 tests should display a **green bar**

You can also run individual test files by right-clicking any `*Test.java` file → **Run As** → **JUnit Test**

---

## Test Coverage Summary

| Test File | Tests | What is Covered |
|-----------|-------|-----------------|
| `ValidatorTest.java` | 35 | `isValidName()`, `isValidPhone()`, `isValidGender()` — valid inputs, invalid inputs, boundary values, edge cases |
| `RoomTest.java` | 22 | `Singleroom` and `Doubleroom` constructors, `getFoodBill()` — field storage, empty list, single/multiple items, decimal prices |
| `BillTest.java` | 15 | `Bill.calculate()` — all 4 room types, food charge integration, price hierarchy, index independence |
| `HotelUtilityTest.java` | 47 | `Hotel.getIndex()` (boundary value analysis), `countAvailable()`, `holder` arrays, `NotAvailable` exception, `write` Runnable |
| **Total** | **119** | **All 119 tests pass — 0 failures** |

### Notable Testing Techniques Used

- **Boundary Value Analysis** — `getIndex()` tested at lower bound, middle, upper bound, one below, and one above for all 4 room types
- **Java Reflection** — `sun.misc.Unsafe.allocateInstance()` used to create `Food` objects without invoking the `Scanner`-coupled constructor, enabling `getFoodBill()` to be tested
- **`@BeforeEach` setup** — Pre-configured `holder` state for clean test isolation in `BillTest`

---

## Technologies Used

- **Java** — Core application language
- **JUnit 5 (Jupiter)** — Unit testing framework
- **Java Serialization** — Data persistence (`ObjectOutputStream` / `ObjectInputStream`)
- **Java Reflection** — Used in tests to bypass `Scanner`-coupled constructors
- **Eclipse IDE** — Development and test execution environment


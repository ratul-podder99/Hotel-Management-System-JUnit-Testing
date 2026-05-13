package project;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Validator Class Tests")
public class ValidatorTest {

    // 1. isValidName() Tests

    @Test
    @DisplayName("TC01 – Valid single word name returns true")
    void testValidNameSingleWord() {
        assertTrue(Validator.isValidName("Alice"));
    }

    @Test
    @DisplayName("TC02 – Valid full name with space returns true")
    void testValidNameWithSpace() {
        assertTrue(Validator.isValidName("Alice Rahman"));
    }

    @Test
    @DisplayName("TC03 – Valid name with multiple spaces returns true")
    void testValidNameMultipleSpaces() {
        assertTrue(Validator.isValidName("Md Al Amin"));
    }

    @Test
    @DisplayName("TC04 – Lowercase only name returns true")
    void testValidNameAllLowercase() {
        assertTrue(Validator.isValidName("john doe"));
    }

    @Test
    @DisplayName("TC05 – Uppercase only name returns true")
    void testValidNameAllUppercase() {
        assertTrue(Validator.isValidName("JOHN DOE"));
    }

    @Test
    @DisplayName("TC06 – Name with digits returns false")
    void testInvalidNameWithDigits() {
        assertFalse(Validator.isValidName("Alice123"));
    }

    @Test
    @DisplayName("TC07 – Name with special characters returns false")
    void testInvalidNameWithSpecialChars() {
        assertFalse(Validator.isValidName("Alice@Khan"));
    }

    @Test
    @DisplayName("TC08 – Name with hyphen returns false")
    void testInvalidNameWithHyphen() {
        assertFalse(Validator.isValidName("Mary-Jane"));
    }

    @Test
    @DisplayName("TC09 – Empty string name returns false")
    void testInvalidNameEmptyString() {
        assertFalse(Validator.isValidName(""));
    }

    @Test
    @DisplayName("TC10 – Name with dot returns false")
    void testInvalidNameWithDot() {
        assertFalse(Validator.isValidName("Dr. Smith"));
    }

    // 2. isValidPhone() Tests
 
    // Rule: "01[3-9][0-9]{8}" — starts with 01, then digit 3-9, then 8 digits

    @Test
    @DisplayName("TC11 – Valid 017 prefix phone returns true")
    void testValidPhone017() {
        assertTrue(Validator.isValidPhone("01711223344"));
    }

    @Test
    @DisplayName("TC12 – Valid 018 prefix phone returns true")
    void testValidPhone018() {
        assertTrue(Validator.isValidPhone("01812345678"));
    }

    @Test
    @DisplayName("TC13 – Valid 019 prefix phone returns true")
    void testValidPhone019() {
        assertTrue(Validator.isValidPhone("01912345678"));
    }

    @Test
    @DisplayName("TC14 – Valid 013 prefix phone returns true")
    void testValidPhone013() {
        assertTrue(Validator.isValidPhone("01312345678"));
    }

    @Test
    @DisplayName("TC15 – Valid 016 prefix phone returns true")
    void testValidPhone016() {
        assertTrue(Validator.isValidPhone("01612345678"));
    }

    @Test
    @DisplayName("TC16 – Phone with 010 prefix returns false (0 is not in [3-9])")
    void testInvalidPhone010() {
        assertFalse(Validator.isValidPhone("01012345678"));
    }

    @Test
    @DisplayName("TC17 – Phone with 011 prefix returns false (1 is not in [3-9])")
    void testInvalidPhone011() {
        assertFalse(Validator.isValidPhone("01112345678"));
    }

    @Test
    @DisplayName("TC18 – Phone with 012 prefix returns false (2 is not in [3-9])")
    void testInvalidPhone012() {
        assertFalse(Validator.isValidPhone("01212345678"));
    }

    @Test
    @DisplayName("TC19 – Phone too short (10 digits) returns false")
    void testInvalidPhoneTooShort() {
        assertFalse(Validator.isValidPhone("0171122334")); // 10 digits
    }

    @Test
    @DisplayName("TC20 – Phone too long (12 digits) returns false")
    void testInvalidPhoneTooLong() {
        assertFalse(Validator.isValidPhone("017112233445")); // 12 digits
    }

    @Test
    @DisplayName("TC21 – Phone with letters returns false")
    void testInvalidPhoneWithLetters() {
        assertFalse(Validator.isValidPhone("017ABCDEFGH"));
    }

    @Test
    @DisplayName("TC22 – Empty phone string returns false")
    void testInvalidPhoneEmpty() {
        assertFalse(Validator.isValidPhone(""));
    }

    @Test
    @DisplayName("TC23 – Phone not starting with 01 returns false")
    void testInvalidPhoneWrongPrefix() {
        assertFalse(Validator.isValidPhone("02712345678"));
    }

    // 3. isValidGender() Tests
    // Rule: equalsIgnoreCase("male") OR equalsIgnoreCase("female")

    @Test
    @DisplayName("TC24 – 'Male' (title case) returns true")
    void testValidGenderMaleTitleCase() {
        assertTrue(Validator.isValidGender("Male"));
    }

    @Test
    @DisplayName("TC25 – 'Female' (title case) returns true")
    void testValidGenderFemaleTitleCase() {
        assertTrue(Validator.isValidGender("Female"));
    }

    @Test
    @DisplayName("TC26 – 'male' (lowercase) returns true")
    void testValidGenderMaleLowercase() {
        assertTrue(Validator.isValidGender("male"));
    }

    @Test
    @DisplayName("TC27 – 'female' (lowercase) returns true")
    void testValidGenderFemaleLowercase() {
        assertTrue(Validator.isValidGender("female"));
    }

    @Test
    @DisplayName("TC28 – 'MALE' (uppercase) returns true")
    void testValidGenderMaleUppercase() {
        assertTrue(Validator.isValidGender("MALE"));
    }

    @Test
    @DisplayName("TC29 – 'FEMALE' (uppercase) returns true")
    void testValidGenderFemaleUppercase() {
        assertTrue(Validator.isValidGender("FEMALE"));
    }

    @Test
    @DisplayName("TC30 – 'Other' returns false")
    void testInvalidGenderOther() {
        assertFalse(Validator.isValidGender("Other"));
    }

    @Test
    @DisplayName("TC31 – 'M' abbreviation returns false")
    void testInvalidGenderAbbreviationM() {
        assertFalse(Validator.isValidGender("M"));
    }

    @Test
    @DisplayName("TC32 – 'F' abbreviation returns false")
    void testInvalidGenderAbbreviationF() {
        assertFalse(Validator.isValidGender("F"));
    }

    @Test
    @DisplayName("TC33 – Empty string returns false")
    void testInvalidGenderEmpty() {
        assertFalse(Validator.isValidGender(""));
    }

    @Test
    @DisplayName("TC34 – 'mALe' (mixed case) returns true — case insensitive")
    void testValidGenderMixedCase() {
        assertTrue(Validator.isValidGender("mALe"));
    }

    @Test
    @DisplayName("TC35 – 'fEmAlE' (random case) returns true — case insensitive")
    void testValidGenderFemaleRandomCase() {
        assertTrue(Validator.isValidGender("fEmAlE"));
    }
}
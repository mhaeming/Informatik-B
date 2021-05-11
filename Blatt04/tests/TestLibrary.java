package tests;

import library.BluRay;
import library.Book;
import library.Library;
import library.LibraryItem;
import util.List;

public class TestLibrary {
    public static void main(String[] args) {
        System.out.println("Starting Tests...");
        TestLibrary tester = new TestLibrary();
        tester.runTests();
        System.out.println(TestSuite.getTests() + " Tests finished with " + TestSuite.getErrors() + " Errors.");
    }

    public void runTests() {
        testInitilization();
        testSearch();
        testDeleteItem();
        testSideEffects();
    }

    public void testInitilization() {
        Library lib = new Library();
        List result = lib.search("A");
        TestSuite.assertTrue(result.empty(), "Intial library should be empty");
    }

    public void testSearch() {
        Library lib = new Library();
        LibraryItem li1 = new BluRay("The Day After Tomorrow", "Roland Emmerich");
        LibraryItem li2 = new Book("The Lord of the Rings", "J.R.R. Tolkien");
        lib.addItem(li1);
        lib.addItem(li2);

        // Query existing items
        List result = lib.search("The");
        TestSuite.assertEquals("The movie on this BluRay is named 'The Day After Tomorrow' and was directed by Roland Emmerich.", ((LibraryItem) result.elem()).getDescription(), "Unexpected search result");
        result.advance();
        TestSuite.assertEquals("This book named 'The Lord of the Rings' was written by J.R.R. Tolkien.", ((LibraryItem) result.elem()).getDescription(), "Unexpected search result");
        result.advance();
        // Assert correct endposition
        TestSuite.assertTrue(result.endpos(), "Too many enteries in test library");

        // Specific Query
        result =  lib.search("The Lord");
        TestSuite.assertEquals("This book named 'The Lord of the Rings' was written by J.R.R. Tolkien.", ((LibraryItem) result.elem()).getDescription(), "Unexpected search result 2");
        result.advance();
        TestSuite.assertTrue(result.endpos(), "Too many enteries in test library 2");
    }

    public void testDeleteItem() {
        Library lib = new Library();
        LibraryItem li1 = new BluRay("The Day After Tomorrow", "Roland Emmerich");
        LibraryItem li2 = new Book("The Lord of the Rings", "J.R.R. Tolkien");
        li2.setBorrowed(true);
        lib.addItem(li1);
        lib.addItem(li2);

        lib.deleteItem(li1);
        List result = lib.search("The");
        result.reset();
    
        TestSuite.assertEquals("This book named 'The Lord of the Rings' was written by J.R.R. Tolkien.", ((LibraryItem) result.elem()).getDescription(), "Error during deletion");
        result.advance();
        TestSuite.assertTrue(result.endpos(), "Too many enteries in test library after delete test");
    }

    public void testSideEffects() {
        Library lib = new Library();
        LibraryItem li1 = new BluRay("The Day After Tomorrow", "Roland Emmerich");
        LibraryItem li2 = new Book("The Lord of the Rings", "J.R.R. Tolkien");
        li2.setBorrowed(true);
        lib.addItem(li1);
        lib.addItem(li2);

        lib.search("The");
        lib.search("The Lord");
        lib.deleteItem(li1);

        TestSuite.assertTrue(li2.isBorrowed(), "Side effects in Item2");
        TestSuite.assertFalse(li1.isBorrowed(), "Side effects in Item1");
    }
}

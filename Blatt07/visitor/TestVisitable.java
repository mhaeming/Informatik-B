package visitor;

import test.TestSuite;


/**
 * TestVisitable
 */
public class TestVisitable {

    public static void main(String[] args) {
        System.out.println("Starting Tests...");
        TestVisitable tester = new TestVisitable();
        tester.CreateTestList();
        tester.runTests();
        System.out.println(TestSuite.getTests() + " Tests finished with " + TestSuite.getErrors() + " Errors.");
    }
    
    public void runTests() {
        TestVisitAll();
        TestVisitOne();
    }

    public void TestVisitAll() {
        MyList<Integer> list = CreateTestList();
        MyList<Integer> vList = new MyList<Integer>();

        // Create a visitor to visit all elements
        Visitor<Integer> visitAll = new Visitor<Integer>(){
            public boolean visit(Integer o) {
                vList.add(o);
                vList.advance();
                return true;
            }
        };

        list.accept(visitAll);
        TestSuite.assertEquals(vList, list, "Complete visited lists are not equal!");
    }

    /**
     * Only visit the 4th entry
     */
    public void TestVisitOne() {
        MyList<Integer> list = CreateTestList();
        MyList<Integer> vList = new MyList<Integer>();

        // Create a visitor to visit all elements
        Visitor<Integer> visitForth = new Visitor<Integer>(){

            private int index = 0;

            public boolean visit(Integer o) {
                
                if (index == 3) {
                    vList.add(o);
                    return false;
                } else {
                    index++;
                    return true;
                }

            }
        };

        list.accept(visitForth);
        TestSuite.assertEquals(8, (int) vList.elem(), "Error while visiting 4th element!");
    }

    /**
     * Populate a test list with Integers
     */
    public MyList<Integer> CreateTestList() {
        Integer[] ints = {1, 2, 5, 8, -3, 6, 0};
        MyList<Integer> list = new MyList<Integer>();

        for (Integer integer : ints) {
            list.add(integer);
        }

        return list;
    }
}
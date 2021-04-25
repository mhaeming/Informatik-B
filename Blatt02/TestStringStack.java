public class TestStringStack {
    public static void main(String[] args) {

        System.out.println("Starting Tests...");
        TestStringStack tester = new TestStringStack();
        tester.runTests();
        System.out.println("Tests finished with " + TestSuite.getErrors() + " Errors.");

    }

    public void runTests() {
        testEmptyStack();
        testTypicalStack();
        testDeepProperties();
    }

    public void testEmptyStack() {
        StringStack s = new StringStack();
        StringStack t = new StringStack(s);
    }

    public void testTypicalStack() {
        StringStack s = new StringStack();
        s.push("1");
        s.push("2");
        s.push("3");
        StringStack t = new StringStack(s);

        for (int i = 0; i < 3; i++) {
            TestSuite.assertEquals(s.peek(), t.peek(), "");
            s.pop();
            t.pop();
        }   
    }

    public void testDeepProperties() {
        StringStack s = new StringStack();
        s.push("content");
        StringStack t = new StringStack(s);

        if (s == t) {
            System.out.println("ASSERTION ERROR: StringStacks have the same reference");
            TestSuite.countError();
        }
    }
}

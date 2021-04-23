public class TestStringStack {
    public static void main(String[] args) {
        StringStack s = new StringStack();
        s.push("1");
        s.push("2");
        s.push("3");

        StringStack t = new StringStack(s);
        
        /*
         * Display content of first stack
         * If it would be a shallow copy this would throw an error, because s would be an empty stack after,
         * the copy process
         */ 
        for (int i = 0; i < 3; i++) {
            System.out.println(s.peek());
            s.pop();
        }

        // Display content of the second stack
        for (int i = 0; i < 3; i++) {
            System.out.println(t.peek());
            t.pop();
        }
        
    }
}

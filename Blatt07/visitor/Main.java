package visitor;

public class Main {

    public static void main(String[] args) {
        MyList<Integer> myIntList = new MyList<Integer>();

        for(int i = 0; i<=50; i++){
            myIntList.add(i);
            myIntList.advance();
        }

        Visitor<Integer> visitor = new PrintListVisitor<Integer>();
        Visitor<Integer> visitor2 = new PrintListVisitor2<Integer>();

        System.out.println("Visiting all elements:");
        myIntList.accept(visitor);

        System.out.println("\nVisiting list with termination condition:");
        myIntList.accept(visitor2);


    }
}

package visitor;

public class Main {

    public static void main(String[] args) {
        MyList<Integer> myIntList = new MyList<Integer>();

        for(int i = 0; i<=50; i++){
            myIntList.add(i);
            myIntList.advance();
        }

        Visitor<Integer> visitor = new PrintListVisitor<Integer>();

        myIntList.accept(visitor);


    }
}

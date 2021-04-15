/**
 * Empfängt eine Zahl als Argument und gibt die Zahlen von 1 bis zur Zahl in einer
 * neuen Zeile aus.
 */
public class PrintLines{
    
    public static void main(String[] args){
    
        // Wurde genau ein Argument gegeben?
        if(args.length != 1){
            System.out.println("Bitte genau einen Parameter eingeben!");
            return;
        }
        
        // Ist die Zahl positv?
        int stop = Integer.parseInt(args[0]);
    
        if(stop < 1){
            System.out.println("Das Programm empfängt nur postive Werte!");
            return;
        }
    
        // Ausgabe der Zahlen. Jede in einer neuen Zeile
        for(int i = 0; i < stop; i++){
            System.out.println(i+1);
        }
    }
    
}

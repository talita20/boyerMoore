
package boyermoore;

import java.util.Scanner;

public class main {
    
    public static void main(String[] args) {
        
        Scanner leitura = new Scanner(System.in);
        System.out.println("Digite a palavra chave:");
        String key = "";
        key = leitura.nextLine();
        
        Algoritmo a = new Algoritmo();
        a.preProcessamento(key);
        a.leArquivo();
        
        
    }
}

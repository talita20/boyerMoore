/*
    TRABALHO DE ALGORITMOS E ESTRUTURA DE DADOS II
    IMPLEMENTACAO DO ALGORITMO BOYER MOORE

    PALOMA GOMES - 13.2.8564
    TALITA SANTOS -  14.1.8335

 */
package boyermoore;

import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        
        Scanner leitura = new Scanner(System.in);
        System.out.println("Digite a palavra chave da primeira entrada:");
        String key = "";
        key = leitura.nextLine();
        
        System.out.println("Digite a palavra chave da segunda entrada para substituição:");
        String key2 = "";
        key2 = leitura.nextLine();
        
        int opcao;
        System.out.println("Escolha o tipo de entrada:");
        System.out.println("1 - Arquivo menor que a RAM");
        System.out.println("2 - Arquivo maior que a RAM (O TAMANHO DA RAM É DE 1000 BYTES!)");
        opcao = leitura.nextInt();
        
        if(opcao == 1){
            Algoritmo a = new Algoritmo();
            a.preProcessamento(key);
            a.leArquivo(key2);
        }else{
            Diferencial d = new Diferencial();
            d.preProcessamento(key);
            d.abreArquivo(key2);
        }

    }
}

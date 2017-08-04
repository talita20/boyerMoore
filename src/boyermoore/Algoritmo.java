
package boyermoore;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Math.abs;


public class Algoritmo {
    protected char[] alfabeto = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','y','x','z'};
    protected int[] valor = new int[alfabeto.length];
    protected int tam;
    protected char[] chave;
    protected String linha;
    
    public Algoritmo() {
    }
    
    public void preProcessamento(String key){
        tam = key.length();
        
        //inicializa o vetor de valores com o tamanho da palavra chave
        for(int i=0; i<alfabeto.length; i++){
            valor[i] = tam;
        }
        
        System.out.println("Vetor de valores: ");
        for(int i=0; i<alfabeto.length; i++){
            System.out.print(" "+valor[i]);
        }
        
        //insercao da palavra chave em um vetor
        chave = key.toCharArray();
        
        //atualizacao dos valores no vetor
        for(int i=0; i<tam; i++){
            for(int j=0; j<alfabeto.length; j++){
                if(chave[i] == alfabeto[j]){
                        valor[j] = abs(tam - (i+1));
                }
           }
        }
        
        System.out.println("\nVetor de valores após a atualização: ");
        for(int i=0; i<alfabeto.length; i++){
            System.out.print(" "+valor[i]);
        }
    }
    
    public void leArquivo(){
        try {
            FileReader arq = new FileReader("entrada1.txt");
            BufferedReader ent = new BufferedReader(arq);
                while (ent.ready()) {
                    linha = ent.readLine();  
                    buscaPalavra(linha);
                }
                arq.close();
        } catch (IOException e) {
            linha = "NULL";
            System.out.println("Erro ao abrir o arquivo entrada1.txt");
        }
    }
    
    //busca por ocorrencias da chave
    public void buscaPalavra(String linha){
        
    }
    
}

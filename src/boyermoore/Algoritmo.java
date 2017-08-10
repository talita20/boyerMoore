
package boyermoore;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Math.abs;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Algoritmo {
    protected char[] alfabeto = {'a','b','c','d','e','f','g','h','i','j','k','l','m',
                                'n','o','p','q','r','s','t','u','v','w','y','x','z'};
    protected int[] valor = new int[alfabeto.length];//valor e um vetor que representa o peso de cada letra do alfabeto
    protected int tam;//tamanho da palavra chave
    protected char[] chave;//vetor da palavra chave
    protected String linha;//String referente a cada linha do arquivo lido
    
    /*
    Construtor da classe Algoritmo
    */
    public Algoritmo() {
    }
    
    /*
    Função que insere os valores de cada letra do alfabeto de acordo com a palavra chave 
    */
    public void preProcessamento(String key){
        tam = key.length();
        
        //inicializa o vetor de valores com o tamanho da palavra chave
        for(int i=0; i<alfabeto.length; i++){
            valor[i] = tam;
        }
        
        System.out.println("Vetor de valores do alfabeto: ");
        System.out.print(" ( " );
        for(int i=0; i<alfabeto.length; i++){
            System.out.print(valor[i]+",");
        }
        System.out.print(" ) " );
        
        //insercao da palavra chave em um vetor
        chave = key.toCharArray();
        
        //atualizacao dos valores no vetor apos a comparacao com a palavra chave
        for(int i=0; i<tam; i++){
            for(int j=0; j<alfabeto.length; j++){
                if(chave[i] == alfabeto[j]){
                        valor[j] = abs(tam - (i+1));//so aceita valores positivos
                }
           }
        }
        
        System.out.println("\nVetor de valores do alfabeto após a atualização: ");
        System.out.print(" ( " );
        for(int i=0; i<alfabeto.length; i++){
            System.out.print(valor[i]+",");
        }
        System.out.print(" ) " );
    }
    
    /*
    funcao que faz a leitura do arquivo entrada01.txt e faz a chamada da funcao buscaPalavra(String linha) para
    cada linha do arquivo
    */
    public void leArquivo(){
        try {
            FileReader arq = new FileReader("entrada1.txt");
            BufferedReader ent = new BufferedReader(arq);
                while (ent.ready()) {
                    linha = ent.readLine();  
                    buscaPalavra(linha);
                }
                arq.close();
                System.out.println("\nArquivo lido com sucesso!");
        } catch (IOException e) {
            linha = "NULL";
            System.out.println("Erro ao abrir o arquivo entrada1.txt");
        }
    }
    
    /*funcao que busca por ocorrencias da palavra chave em cada linha do arquivo*/
    public void buscaPalavra(String linha){
        saidaArquivo();//chama a funcao de saida a cada linha lida do arquivo
    }
    
    /*
    funcao que cria arquivo de saida contendo a ocorrencia da palavra chave em cada linha do arquivo lido
    e a posicao inicial de cada ocorrencia da palavra
    */
    public void saidaArquivo(){
        try {
            FileWriter saida = new FileWriter("saida1.txt");
            BufferedWriter sai = new BufferedWriter(saida);
            
            sai.write(linha);//escreve cada linha do arquivo de entrada na saida (APENAS PARA TESTE)
            sai.newLine();
            sai.flush();
            
            System.out.println("Arquivo escrito com sucesso!");
        } catch (IOException ex) {
            Logger.getLogger(Algoritmo.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao escrever no arquivo saida1.txt");
        }
    }

    
}

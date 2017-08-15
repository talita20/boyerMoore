package boyermoore;

import java.io.BufferedReader;
//import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
//import java.io.FileReader;
//import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import static java.lang.Math.abs;
//import java.util.logging.Level;
//import java.util.logging.Logger;

public class Algoritmo {

    protected char[] alfabeto = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
        'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'y', 'x', 'z'};
    protected int[] valor = new int[alfabeto.length];//valor e um vetor que representa o peso de cada letra do alfabeto
    protected int tam;//tamanho da palavra chave
    protected char[] chave;//vetor da palavra chave
    protected String linha;//String referente a cada linha do arquivo lido
    protected char[] frase;//vetor da linha

    /* Construtor da classe Algoritmo */
    public Algoritmo() {
    }

    /* função que insere os valores de cada letra do alfabeto de acordo com a palavra chave */
    public void preProcessamento(String key) {
        tam = key.length();

        //inicializa o vetor de valores com o tamanho da palavra chave
        for (int i = 0; i < alfabeto.length; i++) {
            valor[i] = tam;
        }

        /*System.out.println("Vetor de valores do alfabeto: ");
        System.out.print(" ( ");
        for (int i = 0; i < alfabeto.length; i++) {
            System.out.print(valor[i] + ",");
        }
        System.out.print(" ) ");*/
        
        //insercao da palavra chave em um vetor
        chave = key.toCharArray();

        //atualizacao dos valores no vetor apos a comparacao com a palavra chave
        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < alfabeto.length; j++) {
                if (chave[i] == alfabeto[j]) {
                    valor[j] -= i + 1;
                }
                if (valor[j] < 0) {
                    valor[j] = 0;
                }
            }
        }

        /*System.out.println("\nVetor de valores do alfabeto após a atualização: ");
        System.out.print(" ( ");
        for (int i = 0; i < alfabeto.length; i++) {
            System.out.print(valor[i] + ",");
        }
        System.out.print(" ) ");*/
    }

    /* funcao que faz a leitura do arquivo entrada01.txt e faz a chamada da funcao buscaPalavra(String linha) para
    cada linha do arquivo */
    public void leArquivo() {
        try {
            /* nao aceita caracteres especiais
            FileReader arq = new FileReader("entrada1.txt");
            BufferedReader ent = new BufferedReader(arq);
             */

            //aceita caracteres especiais
            BufferedReader ent = new BufferedReader(new InputStreamReader(new FileInputStream("entrada1.txt"), "ISO-8859-1"));

            int num = 0;//linha do arquivo
            while (ent.ready()) {
                num++;
                linha = ent.readLine();
                buscaPalavra(linha, num);
            }
            ent.close();
        } catch (IOException e) {
            linha = "NULL";
            System.out.println("Erro ao abrir o arquivo entrada1.txt");
        }
    }

    /* funcao que busca por ocorrencias da palavra chave em cada linha do arquivo */
    public void buscaPalavra(String linha, int num) {
        frase = linha.toCharArray();//passa a linha do arquivo para um vetor
        /*
        cont -> contador de ocorrencias da chave
        posicao -> possicao inicial de ocorrencia da chave
        salto -> salto da chave
        flag -> igual a 1 quando as letras sao iguais
        j -> contador do while
         */
        int cont = 0, posicao, salto = 0, flag = 0, j;

        for (int i = tam - 1; i >= 0; i--) {//o i vai do ultimo caractere da chave ate o primeiro
            //System.out.print(frase[i]+" == "+ chave[i]+"\n");
            if (frase[i] == chave[i]) {
                posicao = tam - i;//salva a posicao inicial
                j = i;
                do {//varre a palavra ate o caractere inicial
                    j--;
                    if (frase[j] == chave[j]) {
                        flag = 1;
                    } else {
                        flag = 0;
                        /* se os caracteres sao diferentes, salta o vetor */
                        salto += valor[i];
                        int pulo = tam - 1 + salto;
                        i = 0;
                        j = 0;
                        posSalto(pulo, num);//chamada da funcao do salto
                    }
                } while (j > 0);
            } else {
                /* se os caracteres sao diferentes, salta o vetor */
                salto += valor[i];
                int pulo = tam - 1 + salto;
                i = 0;
                posSalto(pulo, num);//chamada da funcao do salto
            }
            /* se o flag = 1, incrementa o contador */
            if (flag == 1) {
                cont++;
                System.out.println("Num= " + num + " Cont= " + cont + " Posição inicial= ");//exibe a linha, o contador e a
                //posicao inicial da chave na frase
                //saidaArquivo(num, cont, posicao);//escreve no arquivo de saida
            }
        }
    }

    /* funcao que busca por ocorrencias da palavra chave em cada linha do arquivo apos o salto */
    public void posSalto(int pulo, int num) {
        int cont = 0, posicao, salto = 0, flag = 0, j;
        for (int k = pulo - 1; k >= tam - 1; k--) {
            //avancar a chave "pulo" posicoes
            
            
            
            /* esse eh o mesmo codigo da funcao anterior. 
                Verificar se ela funciona no posSalto()
            */
//            if(frase[k] == chave[k]){
//                posicao = pulo-1;
//                j = k;
//                 do {//varre a palavra ate o caractere inicial
//                    j--;
//                    if (frase[j] == chave[k]) {
//                        flag = 1;
//                    } else {
//                        flag = 0;
//                        /* se os caracteres sao diferentes, salta o vetor */
//                        salto += valor[k];
//                        pulo = pulo - 1 + salto;
//                        k = 0;
//                        j = 0;
//                        posSalto(pulo, num);//chamada da funcao do salto
//                    }
//                } while (j > 0);
//            }else{
//                 /* se os caracteres sao diferentes, salta o vetor */
//                salto += valor[k];
//                pulo = pulo - 1 + salto;
//                k = 0;
//                posSalto(pulo, num);//chamada da funcao do salto
//            }
//            /* se o flag = 1, incrementa o contador */
//            if (flag == 1) {
//                cont++;
//                System.out.println("Num= " + num + " Cont= " + cont + " Posição inicial= ");//exibe a linha, o contador e a
//                //posicao inicial da chave na frase
//                //saidaArquivo(num, cont, posicao);//escreve no arquivo de saida
//            }
            
            
            
        }
    }

    /* funcao que cria arquivo de saida contendo a ocorrencia da palavra chave em cada linha do arquivo lido
    e a posicao inicial de cada ocorrencia da palavra */
    public void saidaArquivo(int num, int cont, int posicao) {
        try {
            /* não aceita caracteres especiais
            FileWriter saida = new FileWriter("saida1.txt");
            BufferedWriter sai = new BufferedWriter(saida);
            sai.write("Linha "+ num + " - Número de ocorrências da palavra: "+ cont + " Posição de ocorrência: "+ posicao);
            sai.write();
            sai.newLine();
            sai.flush();
             */
            
            //aceita caracteres especiais
            OutputStreamWriter sai = new OutputStreamWriter(new FileOutputStream("saida1.txt"), "UTF-8");
            
            //escreve no arquivo
            sai.write("Linha " + num + " - Número de ocorrências da palavra: " + cont + " Posição de ocorrência: " + posicao);
            sai.close();

        } catch (IOException ex) {
            System.out.println("Erro ao escrever no arquivo saida1.txt");
        }
    }

}

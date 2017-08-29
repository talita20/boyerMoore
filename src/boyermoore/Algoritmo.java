package boyermoore;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Algoritmo {

    protected char[] alfabeto = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
        'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'y', 'x', 'z'};
    protected int[] valor = new int[alfabeto.length];//valor de um vetor que representa o peso de cada letra do alfabeto
    protected int tam;//tamanho da primeira palavra chave
    protected char[] chave;//vetor da palavra chave
    protected char[] chave2;//vetor da segunda palavra chave
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

//        System.out.println("Vetor de valores do alfabeto: ");
//        System.out.print(" [ ");
//        for (int i = 0; i < alfabeto.length; i++) {
//            System.out.print(valor[i] + ",");
//        }
//        System.out.print(" ] ");
        //insercao da palavra chave em um vetor
        chave = key.toCharArray();

        //atualizacao dos valores no vetor apos a comparacao com a palavra chave
        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < alfabeto.length; j++) {
                if (chave[i] == alfabeto[j]) {
                    valor[j] -= i + 1;
                }
                if (valor[j] <= 0) {
                    valor[j] = 1;
                }
            }
        }

//        System.out.println("\nVetor de valores do alfabeto após a atualização: ");
//        System.out.print(" [ ");
//        for (int i = 0; i < alfabeto.length; i++) {
//            System.out.print(valor[i] + ",");
//        }
//        System.out.print(" ] ");
    }

    /* funcao que faz a leitura do arquivo entrada1.txt e faz a chamada da funcao buscaPalavra(String linha, int num, String key2) para
    cada linha do arquivo */
    public void leArquivo(String key2) {
        try {
            FileReader arq = new FileReader("entrada1.txt");
            BufferedReader ent = new BufferedReader(arq);
            linha = ent.readLine();
            
            if (linha == null) {
                System.out.println("O arquivo entrada1.txt está vazio!");
            } else {
                int num = 1;//linha do arquivo
                while (ent.ready()) {
                    linha = ent.readLine();
                    buscaPalavra(linha, num, key2);
                    num++;
                }
                ent.close();
            }

        } catch (IOException e) {
            linha = "NULL";
            System.out.println("Erro ao abrir o arquivo entrada1.txt");
        }
    }

    /* funcao que busca por ocorrencias da palavra chave em cada linha do arquivo */
    public void buscaPalavra(String linha, int num, String key2) {
        frase = linha.toCharArray();//passa a linha do arquivo para um vetor
        List<Integer> posicoes = new ArrayList();//lista das posicoes iniciais do padrao
        /*
        cont -> contador de ocorrencias
        posicao -> posicao inicial do padrao
        flag -> verifica se achou o padrao
        j -> percorre a palavra chave
        i -> percorre a frase
        fim -> guarda o valor inicial de i
         */
        int cont = 0, posicao = -1, flag = 0, j = tam - 1, i = tam - 1, fim = tam - 1;

        while (i < frase.length && j < tam) {
            if (frase[i] == chave[j]) {
                while (frase[i] == chave[j]) {
                    i--;
                    j--;
                    if (j < 0) {//se chegou no inicio da palavra chave
                        flag = 1;//achou o padrao
                        posicao = i + 1;//salva posicao inicial
                        cont += 1;//incrementa o contador do padrao
                        break;
                    }
                }
            } else {
                for (int a = 0; a < alfabeto.length; a++) {
                    if (frase[i] == alfabeto[a]) {
                        i += valor[a];//salto
                        if (i >= frase.length) {
                            break;
                        }
                    }
                }
                flag = 0;//nao achou o padrao
            }
            j = tam - 1;//reposiciona o j no fim da palavra chave
            fim = fim + 1;//incrementa o fim
            i = fim;//reposiciona o i na frase
            if (flag == 1) {//se encontrou o padrao
                posicoes.add(posicao);
            }
        }
        saidaArquivo(cont, (ArrayList) posicoes, num);//escreve no arquivo saida1.txt
        substituiPalavra(frase, key2, cont);//chamada da funcao de substituicao do padrao
    }


    /* funcao que cria arquivo de saida contendo a ocorrencia da palavra chave em cada linha do arquivo lido
    e a posicao inicial de cada ocorrencia da palavra */
    public void saidaArquivo(int cont, ArrayList posicoes, int num) {
        try {
            FileWriter saida = new FileWriter("saida1.txt", true);
            BufferedWriter sai = new BufferedWriter(saida);

            //escreve no arquivo
            sai.write("Total de ocorrências na linha " + num + ": " + cont);
            sai.newLine();
            sai.write("Posições: " + posicoes);
            sai.newLine();
            sai.flush();
            sai.close();

        } catch (IOException ex) {
            System.out.println("Erro ao escrever no arquivo saida1.txt");
        }
    }

    public void substituiPalavra(char[] frase, String key2, int cont) {
        chave2 = key2.toCharArray();//passa a segunda palavra chave para um vetor
        int tamanho = chave2.length;//tamanho da segunda palavra chave
        List<Character> fraseAux = new ArrayList();//lista que guarda os caracteres da frase
        char[] frase2 = null;//vetor para a frase apos a substituicao

        /* definicao do tamanho da frase2 */
        if (tamanho > tam) {
            frase2 = new char[frase.length + (cont * (tamanho - tam))];
        } else {
            frase2 = new char[frase.length];
        }

        /*
            i -> contador da frase2
            j -> contador da primeira palavra chave
            k -> contador da frase
            l -> contador auxiliar da frase
            flag -> se for 0, achou o padrao. Se for 1, "quase" achou o padrao
         */
        int i = 0, j = 0, k = 0, l = 0, flag = 0;
        while (k < frase.length) {
            if (frase[k] == chave[j]) {
                l = k;
                while (frase[l] == chave[j]) {
                    fraseAux.add(frase[l]);//salva a frase em um vetor auxiliar, caso nao ache o padrao
                    flag = 1;
                    j++;
                    l++;
                    if (j == chave.length) {//se achou o padrao, escreve a nova chave na frase2
                        for (int n = 0; n < tamanho; n++) {
                            frase2[i] = chave2[n];
                            i++;
                        }
                        flag = 0;
                        k += tam;
                        break;
                    }

                    if (l >= frase.length) {//se leu toda a frase, escreve a lista auxiliar na frase2
                        for (int m = 0; m < fraseAux.size(); m++) {
                            frase2[i] = fraseAux.get(m);
                            i++;
                        }
                        fraseAux.clear();//limpa a lista
                        flag = 0;
                        k += tam;
                        break;
                    }
                }
                if (flag == 1) {//se "quase" achou o padrao, escreve a lista auxiliar na frase2
                    for (int m = 0; m < fraseAux.size(); m++) {
                        frase2[i] = fraseAux.get(m);
                        i++;
                    }
                    k += fraseAux.size();
                }
            } else {
                frase2[i] = frase[k];
                k++;
                i++;
            }
            j = 0;
            fraseAux.clear();//limpa a lista
        }
        saidaArquivo2(frase2);//escreve no arquivo saida2.txt
    }

    public void saidaArquivo2(char[] frase2) {
        try {
            FileWriter saida = new FileWriter("saida2.txt", true);
            BufferedWriter sai = new BufferedWriter(saida);

            //escreve no arquivo
            sai.write(frase2);
            sai.newLine();
            sai.flush();
            sai.close();

        } catch (IOException ex) {
            System.out.println("Erro ao escrever no arquivo saida1.txt");
        }
    }
}

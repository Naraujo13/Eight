/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Eight;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;


/**
 *
 * @author nicol
 */
public class Tabuleiro {

     int matriz[][];
     
    public Tabuleiro (){
        matriz = new int [3][3];
    }
    
    public void intitialize(){
        //Cira ArrayList com números e os aleatoriza
        List <Integer> numbers = new ArrayList<>();
        for(int i = 0; i < 9 ; i++){
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        
        //Insere na matriz de forma aleatória
        for (int i = 0; i<3 ; i++){
            for (int j = 0; j<3 ; j++){
                matriz[i][j] = numbers.get(3*i + j);
            }
        }
        
    }
    
    

    public boolean isPossible(int matriz[][]){
        //Testa se matriz foi instanciada
        if (matriz == null)
            return false;
        //Inteiro para contar numero de inversões
        int swap = 0;
        int test;
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                test = 0;
                for (int l=j; l<3; l++){
                    if ( matriz[i][l] < matriz[i][j] && matriz[i][l] != 0)
                        test++;
                }
                for (int k=i+1; k<3; k++){
                    for (int l=0; l<3; l++){
                        if (matriz[k][l] < matriz[i][j] && matriz[k][l] != 0)
                            test++;
                    }                        
                }
                swap = swap + test;
            }                    
        }
        System.out.println(swap);
         return swap%2 == 0;
    }
    
    public void profundidade(Node raiz){
        
    }
    
    public void chooseSon(Node n){

    }
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Tabuleiro game = new Tabuleiro ();
        game.intitialize();
   /* //Teste pré-definido
    game.matriz[0][0] = 8;
    game.matriz[0][1] = 7;
    game.matriz[0][2] = 6;
    game.matriz[1][0] = 1;
    game.matriz[1][1] =  2;
    game.matriz[1][2] =  4;
    game.matriz[2][0] =  0;
    game.matriz[2][1] =  3;
    game.matriz[2][2] =  5;
    */
    if (game.isPossible(game.matriz))
            System.out.println("Possível.");
        else
           System.out.println("Impossível."); 
        
      
    }
    
}

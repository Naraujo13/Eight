/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eight;

/**
 *
 * @author nicol
 */
public class Node {
    
    Node Up, Down, Left, Right;
    int estado[][];
    
    public Node(int estado[][]){
       this.estado = estado;
       Up = null;
       Down = null;
       Left = null;
       Right = null;      
    }
}

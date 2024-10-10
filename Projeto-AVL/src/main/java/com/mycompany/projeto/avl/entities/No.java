/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto.avl.entities;

/**
 *
 * @author andre
 */
public class No {

    public int valor, altura;
    public No esquerdo, direito;

    public No(int valor) {
        this.valor = valor;
        this.altura = 1;
    }
}
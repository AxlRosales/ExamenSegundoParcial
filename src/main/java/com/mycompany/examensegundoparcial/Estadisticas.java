/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examensegundoparcial;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Axel Rosales
 */
public class Estadisticas {
    
    
    public double PromedioPartidos (List<Double> valores)
    {
        double resultado = 0;
        
        int Partidos = valores.size();
        double TotalMinutos = 0;
        
        for(Double min : valores)
        {
            TotalMinutos += min;
        }
        
        resultado = TotalMinutos / Partidos;
        
        return resultado;
    }
    
    public double CalcularVarianza (List<Double> lista)
    {
        if(lista.isEmpty())
            return 0;
        
        double Promedio = PromedioPartidos(lista);
        
        double sumaDiferenciaCuadrada = 0;
        
        for(Double min: lista)
        {
            double diferencia = min - Promedio;
            sumaDiferenciaCuadrada += diferencia * diferencia;
        }
        
        return sumaDiferenciaCuadrada / lista.size();
                
        
    }
    
    public double CalcularDesviacionEstandar(List<Double> lista)
    {
        double varianza = CalcularVarianza(lista);
        return Math.sqrt(varianza);
    }
    
    public double calcularMediana(List<Double> lista)
    {
        if(lista.isEmpty())
            return 0;
        
        List<Double> copiaOrdenada = new ArrayList<>(lista);
        Collections.sort(lista);
        
        int tamanoLista = lista.size();
        
        if(tamanoLista % 2 == 1)
        {
            return copiaOrdenada.get(tamanoLista / 2);
        }
        else
        {
            double Centro1 = copiaOrdenada.get((tamanoLista / 2) - 1);
            double Centro2 = copiaOrdenada.get(tamanoLista / 2);
            return (Centro1 + Centro2) / 2;
        }
        
    }
    
    public List<Double> calcularModa (List<Double> lista)
    {
        Map<Double, Integer> frecuencia = new HashMap<>();
        List<Double> modas = new ArrayList<>();
        
        for(Double min : lista)
        {
            frecuencia.put(min, frecuencia.getOrDefault(min, 0) + 1);
        }
        
        
        int maxFrecuencia = 0;
        for(int frec : frecuencia.values())
        {
            if(frec > maxFrecuencia)
                maxFrecuencia = frec;
        }
        
        if(maxFrecuencia == 1)
        {
            return new ArrayList<>();
        }
        else
        {
            for(Map.Entry<Double, Integer> entry : frecuencia.entrySet())
            {
                if(entry.getValue() == maxFrecuencia)
                {
                    modas.add(entry.getKey());
                }
            }
            
            return modas;
        }
        
        
    }
    
    public double GetMaxMinute(List<Double> lista)
    {
        double result = 0;
        
        for(double min: lista)
        {
            if(min > result)
                result = min;
        }
        
        return result;
    }
    
    public double GetMinMinute(List<Double> lista)
    {
        double result = 1000;
        
        for(double min: lista)
        {
            if(min < result)
                result = min;
        }
        
        return result;
    }
    
    public double CalcularRango(List<Double> lista)
    {
        double MinMaximo = GetMaxMinute(lista);
        double MinMinimo = GetMinMinute(lista);
        
        return MinMaximo - MinMinimo;
    }
    
    public  double calcularCuartil(List<Double> ListaOrdenada, double porcentaje)
    {
        int tamanoLista = ListaOrdenada.size();
        double posicion = (porcentaje / 100.0) * (tamanoLista - 1);
        int abajo = (int)Math.floor(posicion);
        int arriba = (int)Math.ceil(posicion);
        
        if(abajo == arriba)
        {
            return ListaOrdenada.get(abajo);
        }
        else
        {
            double valorAbajo = ListaOrdenada.get(abajo);
            double valorArriba = ListaOrdenada.get(arriba);
            return valorAbajo + (valorArriba - valorAbajo) * (posicion - abajo);
        }
    }
    
    public  List<Double> EncontrarOutliner(List<Double> Lista)
    {
        if(Lista.size() < 4)
        {
            return new ArrayList();
        }
        
        List<Double> ListaOrdenada = new ArrayList<>(Lista);
        Collections.sort(ListaOrdenada);
        
        double q1 = calcularCuartil(ListaOrdenada, 25);
        double q3 = calcularCuartil(ListaOrdenada, 75);
        double iqr = q3 - q1;
        
        double limiteInferior = q1 - 1.5 * iqr;
        double limiteSuperior = q3 - 1.5 * iqr;
        
        List<Double> outlier = new ArrayList<>();
        for(double valor : ListaOrdenada)
        {
            if(valor< limiteInferior || valor > limiteSuperior)
            {
                outlier.add(valor);
            }
        }
        
        return outlier;
        
    } 
    
    
    
}

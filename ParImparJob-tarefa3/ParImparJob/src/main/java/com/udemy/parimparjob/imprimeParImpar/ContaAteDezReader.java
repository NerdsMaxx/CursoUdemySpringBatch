package com.udemy.parimparjob.imprimeParImpar;

import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Component
public class ContaAteDezReader extends IteratorItemReader<Integer> {
    
    public ContaAteDezReader() {
        super(getIntegerIterator());
    }
    
    private static Iterator<Integer> getIntegerIterator() {
        List<Integer> numerosDeUmAteDez = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        return numerosDeUmAteDez.iterator();
    }
}
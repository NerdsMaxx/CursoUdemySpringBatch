package com.udemy.parimparjob.imprimeParImpar;

import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class ParOuImparProcessor extends FunctionItemProcessor<Integer, String> {
    public ParOuImparProcessor() {
        super(item -> item % 2 == 0 ? String.format("Item %s é Par", item) : String.format("Item %s é Ímpar", item));
    }
}
package com.udemy.parimparjob.imprimeParImpar;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ImprimeWriter implements ItemWriter<String> {
    
    @Override
    public void write(List<? extends String> list) throws Exception {
        list.forEach(System.out::println);
    }
}
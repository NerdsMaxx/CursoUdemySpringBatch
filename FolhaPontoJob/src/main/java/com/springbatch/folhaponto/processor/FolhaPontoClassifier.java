package com.springbatch.folhaponto.processor;

import com.springbatch.folhaponto.dominio.FolhaPonto;
import com.springbatch.folhaponto.dominio.Funcionario;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.classify.Classifier;

import static java.util.Objects.isNull;

public class FolhaPontoClassifier implements Classifier<Funcionario,ItemProcessor<?,? extends FolhaPonto>> {
    /**
     *
     */
    private static final long serialVersionUID = 7376045285633686254L;
    
    @Override
    public ItemProcessor<Funcionario,FolhaPonto> classify(Funcionario funcionario) {
        if(funcionario.getRegistrosPonto().isEmpty()) {
            return new FolhaPontoInvalidaItemProcessor();
        }
        else {
            return new FolhaPontoItemProcessor();
        }
    }
    
}
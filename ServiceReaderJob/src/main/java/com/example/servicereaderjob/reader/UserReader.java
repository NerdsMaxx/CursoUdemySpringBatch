package com.example.servicereaderjob.reader;

import com.example.servicereaderjob.domain.ResponseUser;
import com.example.servicereaderjob.domain.User;
import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserReader implements ItemReader<User> {
    
    private final RestTemplate restTemplate = new RestTemplate();
    private int page = 1;
    private final List<User> userList = new ArrayList<>();
    private int index = 0;
    private int total = 0;
    
    @Value("${chunkSize}")
    private int chunkSize;
    
    @Value("${pageSize}")
    private int pageSize;
    
    @Value("${limit}")
    private int limit;
    
    @Override
    public User read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return (index < userList.size()) ?
               userList.get(index++) :
               null;
    }
    
    private List<User> fetchUserDataFromAPI() {
        final ResponseEntity<ResponseUser> response =
                restTemplate.getForEntity("https://gorest.co.in/public/v1/users?page=" + page, ResponseUser.class);
        
        return Optional.ofNullable(response.getBody())
                       .map(ResponseUser::getData)
                       .orElse(List.of());
    }
    
    @BeforeChunk
    private void beforeChunk(final ChunkContext context) {
        System.out.println("Inicio do Chunk!");
        for (int i = 0; i < chunkSize; i += pageSize) {
            if (total >= limit) {
                return;
            }
            
            final List<User> userListNow = fetchUserDataFromAPI();
            userList.addAll(userListNow);
            
            total += userListNow.size();
            
            ++ page;
        }
    }
    
    @AfterChunk
    private void afterChunk(final ChunkContext context) {
        System.out.println("Fim do Chunk!");
        index = 0;
        userList.clear();
    }
}
package com.example.BookMyShowSpringBootApplication.service.impl;

import com.example.BookMyShowSpringBootApplication.entity.Movie;
import com.example.BookMyShowSpringBootApplication.helper.SearchHelper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class SearchService {

    @Autowired
    SearchHelper searchHelper;

    public List<Movie> searchMovie(String keyword){
          return searchHelper.searchMovies(keyword);
    }
}

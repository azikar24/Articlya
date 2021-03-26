package com.azikar24.articlya;

import com.azikar24.articlya.Repository.ArticleRepository;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArticleRepositoryTest {
    private ArticleRepository articleRepository = new ArticleRepository();

    @Test
    public void testGetApiUrl() {
        String url = articleRepository.getApiUrl(7);
        assertEquals(url, "https://api.nytimes.com/svc/mostpopular/v2/viewed/7.json?api-key=LreWSkDH18CMup3Frk3imMeLK6mLxeVL");
    }

}

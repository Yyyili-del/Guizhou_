package com.example.guizhou.service;

import com.example.guizhou.entity.Attraction;
import com.example.guizhou.repository.AttractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class RecommendationService {

    @Autowired
    private AttractionRepository attractionRepository;

    public List<Attraction> getHotRecommendations() {
        return attractionRepository.findTop5ByOrderByViewCountDesc();
    }

    public List<Attraction> getRandomRecommendations() {
        return attractionRepository.findRandomAttractions(3);
    }

    public List<Attraction> getMixedRecommendations() {
        List<Attraction> hot = getHotRecommendations();
        List<Attraction> random = getRandomRecommendations();
        Set<Long> ids = new HashSet<>();
        List<Attraction> combined = new ArrayList<>();
        for (Attraction a : hot) {
            if (ids.add(a.getId())) combined.add(a);
        }
        for (Attraction a : random) {
            if (ids.add(a.getId())) combined.add(a);
        }
        return combined.size() > 6 ? combined.subList(0, 6) : combined;
    }
}

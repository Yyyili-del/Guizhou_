package com.example.guizhou.service;

import com.example.guizhou.entity.Attraction;
import com.example.guizhou.repository.AttractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    @Autowired
    private AttractionRepository attractionRepository;

    public List<Attraction> getHotRecommendations() {
        return attractionRepository.findTop10ByOrderByViewCountDesc();
    }

    public List<Attraction> getHighRatingRecommendations() {
        return attractionRepository.findTop10ByOrderByRatingDesc();
    }

    public List<Attraction> getRandomRecommendations() {
        return attractionRepository.findRandomAttractions(5);
    }

    public List<Attraction> getMixedRecommendations() {
        List<Attraction> hot = getHotRecommendations();
        List<Attraction> highRating = getHighRatingRecommendations();
        List<Attraction> random = getRandomRecommendations();
        
        Set<Long> ids = new HashSet<>();
        List<Attraction> combined = new ArrayList<>();
        
        for (Attraction a : hot) {
            if (ids.add(a.getId())) combined.add(a);
        }
        for (Attraction a : highRating) {
            if (ids.add(a.getId())) combined.add(a);
        }
        for (Attraction a : random) {
            if (ids.add(a.getId())) combined.add(a);
        }
        
        return combined.size() > 12 ? combined.subList(0, 12) : combined;
    }

    public List<Attraction> getRecommendationsByCategory(String category, int limit) {
        List<Attraction> byCategory = attractionRepository.findByCategory(category);
        return byCategory.stream()
                .sorted(Comparator.comparing(Attraction::getRating).reversed()
                        .thenComparing(Attraction::getViewCount).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    public List<Attraction> getSmartRecommendations(String preferredCategory, int limit) {
        List<Attraction> all = attractionRepository.findAll();
        
        return all.stream()
                .sorted((a1, a2) -> {
                    double score1 = calculateScore(a1, preferredCategory);
                    double score2 = calculateScore(a2, preferredCategory);
                    return Double.compare(score2, score1);
                })
                .limit(limit)
                .collect(Collectors.toList());
    }

    private double calculateScore(Attraction attraction, String preferredCategory) {
        double score = 0.0;
        
        if (attraction.getRating() != null) {
            score += attraction.getRating() * 20;
        }
        
        if (attraction.getViewCount() != null) {
            score += Math.log(attraction.getViewCount() + 1) * 10;
        }
        
        if (preferredCategory != null && preferredCategory.equals(attraction.getCategory())) {
            score += 50;
        }
        
        return score;
    }
}

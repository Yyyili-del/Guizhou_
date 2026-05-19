package com.example.guizhou.controller;

import com.example.guizhou.entity.Attraction;
import com.example.guizhou.service.AttractionService;
import com.example.guizhou.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/attractions")
public class AttractionController {

    @Autowired
    private AttractionService attractionService;

    @Autowired
    private RecommendationService recommendationService;

    @GetMapping
    public List<Attraction> getAll() {
        return attractionService.getAllAttractions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Attraction> getDetail(@PathVariable Long id) {
        Optional<Attraction> opt = attractionService.getAttractionById(id);
        if (opt.isPresent()) {
            attractionService.increaseViewCount(id);
            return ResponseEntity.ok(opt.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/recommend/hot")
    public List<Attraction> hotRecommend() {
        return recommendationService.getHotRecommendations();
    }

    @GetMapping("/recommend/random")
    public List<Attraction> randomRecommend() {
        return recommendationService.getRandomRecommendations();
    }

    @GetMapping("/recommend/mixed")
    public List<Attraction> mixedRecommend() {
        return recommendationService.getMixedRecommendations();
    }

    @GetMapping("/search")
    public List<Attraction> searchByLocation(@RequestParam String location) {
        return attractionService.getAttractionsByLocation(location);
    }
}
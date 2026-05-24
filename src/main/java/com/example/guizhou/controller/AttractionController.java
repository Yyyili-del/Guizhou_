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

    @GetMapping("/recommend/rating")
    public List<Attraction> highRatingRecommend() {
        return recommendationService.getHighRatingRecommendations();
    }

    @GetMapping("/recommend/random")
    public List<Attraction> randomRecommend() {
        return recommendationService.getRandomRecommendations();
    }

    @GetMapping("/recommend/mixed")
    public List<Attraction> mixedRecommend() {
        return recommendationService.getMixedRecommendations();
    }

    @GetMapping("/recommend/smart")
    public List<Attraction> smartRecommend(@RequestParam(required = false) String category,
                                           @RequestParam(defaultValue = "10") int limit) {
        return recommendationService.getSmartRecommendations(category, limit);
    }

    @GetMapping("/search")
    public List<Attraction> search(@RequestParam String keyword) {
        return attractionService.searchAttractions(keyword);
    }

    @GetMapping("/category/{category}")
    public List<Attraction> getByCategory(@PathVariable String category) {
        return attractionService.getAttractionsByCategory(category);
    }

    @GetMapping("/categories")
    public List<String> getCategories() {
        return attractionService.getAllCategories();
    }

    @PostMapping
    public ResponseEntity<Attraction> create(@RequestBody Attraction attraction) {
        Attraction created = attractionService.createAttraction(attraction);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Attraction> update(@PathVariable Long id, @RequestBody Attraction attraction) {
        Attraction updated = attractionService.updateAttraction(id, attraction);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (attractionService.deleteAttraction(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/high-rating")
    public List<Attraction> getHighRating(@RequestParam(defaultValue = "4.5") Double minRating) {
        return attractionService.getHighRatingAttractions(minRating);
    }
}
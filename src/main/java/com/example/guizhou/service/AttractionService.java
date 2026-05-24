package com.example.guizhou.service;

import com.example.guizhou.entity.Attraction;
import com.example.guizhou.repository.AttractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AttractionService {

    @Autowired
    private AttractionRepository attractionRepository;

    public List<Attraction> getAllAttractions() {
        return attractionRepository.findAll();
    }

    public Optional<Attraction> getAttractionById(Long id) {
        return attractionRepository.findById(id);
    }

    @Transactional
    public Attraction increaseViewCount(Long id) {
        Optional<Attraction> opt = attractionRepository.findById(id);
        if (opt.isPresent()) {
            Attraction a = opt.get();
            a.setViewCount(a.getViewCount() + 1);
            return attractionRepository.save(a);
        }
        return null;
    }

    public List<Attraction> getAttractionsByLocation(String location) {
        return attractionRepository.findByLocationContaining(location);
    }

    public List<Attraction> searchAttractions(String keyword) {
        return attractionRepository.findByNameContaining(keyword);
    }

    public List<Attraction> getAttractionsByCategory(String category) {
        return attractionRepository.findByCategory(category);
    }

    public List<String> getAllCategories() {
        return attractionRepository.findAllCategories();
    }

    @Transactional
    public Attraction createAttraction(Attraction attraction) {
        return attractionRepository.save(attraction);
    }

    @Transactional
    public Attraction updateAttraction(Long id, Attraction attraction) {
        Optional<Attraction> opt = attractionRepository.findById(id);
        if (opt.isPresent()) {
            Attraction existing = opt.get();
            existing.setName(attraction.getName());
            existing.setLocation(attraction.getLocation());
            existing.setCategory(attraction.getCategory());
            existing.setDescription(attraction.getDescription());
            existing.setImageUrl(attraction.getImageUrl());
            existing.setRating(attraction.getRating());
            existing.setTicketPrice(attraction.getTicketPrice());
            existing.setOpeningHours(attraction.getOpeningHours());
            existing.setBestSeason(attraction.getBestSeason());
            return attractionRepository.save(existing);
        }
        return null;
    }

    @Transactional
    public boolean deleteAttraction(Long id) {
        if (attractionRepository.existsById(id)) {
            attractionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Attraction> getHighRatingAttractions(Double minRating) {
        return attractionRepository.findByRatingGreaterThanEqual(minRating);
    }
}
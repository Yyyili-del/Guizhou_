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
}
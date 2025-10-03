package com.iset.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.iset.dao.OffreRepository;
import com.iset.entities.Offre;
import java.util.List;

@RestController
@RequestMapping("/Offres")
public class RestOffres {

    @Autowired
    private OffreRepository offreRepository;

    // Get all
    @GetMapping
    public List<Offre> getAll() {
        return offreRepository.findAll();
    }

    // Get by ID
    @GetMapping("/{id}")
    public Offre getById(@PathVariable Long id) {
        return offreRepository.findById(id).orElse(null);
    }

    // Add new
    @PostMapping
    public Offre saveOffre(@RequestBody Offre newOffre) {
        return offreRepository.save(newOffre);
    }

    // Update
    @PutMapping("/{id}")
    public Offre updateOffre(@PathVariable Long id, @RequestBody Offre updated) {
        return offreRepository.findById(id)
                .map(o -> {
                    o.setIntitule(updated.getIntitule());
                    o.setSpecialite(updated.getSpecialite());
                    o.setSociete(updated.getSociete());
                    o.setNbpostes(updated.getNbpostes());
                    o.setPays(updated.getPays());
                    return offreRepository.save(o);
                })
                .orElseGet(() -> {
                    updated.setCode(id);
                    return offreRepository.save(updated);
                });
    }

    // Delete
    @DeleteMapping("/{id}")
    public void deleteOffre(@PathVariable Long id) {
        offreRepository.deleteById(id);
    }
}

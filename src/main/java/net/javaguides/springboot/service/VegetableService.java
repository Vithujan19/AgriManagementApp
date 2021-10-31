package net.javaguides.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;

import net.javaguides.springboot.model.Vegetable;

public interface VegetableService {
    List<Vegetable> getAllVegetables();
    void saveVegetable(Vegetable vegetable);
    Vegetable getVegetableById(long id);
    void deleteVegetableById(long id);
    Page<Vegetable> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}

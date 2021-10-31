package net.javaguides.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.model.Vegetable;
import net.javaguides.springboot.repository.VegetableRepository;

@Service
public class VegetableServiceImpl implements VegetableService {

    @Autowired
    private VegetableRepository vegetableRepository;

    @Override
    public List<Vegetable> getAllVegetables() {
        return vegetableRepository.findAll();
    }

    @Override
    public void saveVegetable(Vegetable vegetable) {
        this.vegetableRepository.save(vegetable);
    }

    @Override
    public Vegetable getVegetableById(long id) {
        Optional<Vegetable> optional = vegetableRepository.findById(id);
        Vegetable vegetable = null;
        if (optional.isPresent()) {
            vegetable = optional.get();
        } else {
            throw new RuntimeException(" Employee not found for id :: " + id);
        }
        return vegetable;
    }

    @Override
    public void deleteVegetableById(long id) {
        this.vegetableRepository.deleteById(id);
    }

    @Override
    public Page<Vegetable> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.vegetableRepository.findAll(pageable);
    }
}

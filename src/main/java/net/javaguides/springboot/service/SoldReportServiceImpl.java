package net.javaguides.springboot.service;

import java.util.List;
import java.util.Optional;

import net.javaguides.springboot.model.SoldReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.repository.SoldReportRepository;

@Service
public class SoldReportServiceImpl implements SoldReportService {

    @Autowired
    private SoldReportRepository soldReportRepository;

    @Override
    public List<SoldReport> getAllSoldReport() {
        return soldReportRepository.findAll();
    }

    @Override
    public void saveSoldReport(SoldReport soldReport) {
        this.soldReportRepository.save(soldReport);
    }

    @Override
    public SoldReport getSoldReportById(long id) {
        Optional<SoldReport> optional = soldReportRepository.findById(id);
        SoldReport soldReport = null;
        if (optional.isPresent()) {
            soldReport = optional.get();
        } else {
            throw new RuntimeException(" Employee not found for id :: " + id);
        }
        return soldReport;
    }

    @Override
    public void deleteSoldReportById(long id) {
        this.soldReportRepository.deleteById(id);
    }

    @Override
    public Page<SoldReport> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.soldReportRepository.findAll(pageable);
    }
}

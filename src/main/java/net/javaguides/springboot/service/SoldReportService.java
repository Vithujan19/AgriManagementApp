package net.javaguides.springboot.service;

import java.util.List;

import net.javaguides.springboot.model.SoldReport;
import org.springframework.data.domain.Page;

public interface SoldReportService {
    List<SoldReport> getAllSoldReport();
    void saveSoldReport(SoldReport employee);
    SoldReport getSoldReportById(long id);
    void deleteSoldReportById(long id);
    Page<SoldReport> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}

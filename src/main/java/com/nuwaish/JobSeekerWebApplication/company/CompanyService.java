package com.nuwaish.JobSeekerWebApplication.company;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();

    void createCompany(Company company);

    Company getCompanyById(Long id);

    boolean updateCompanyById(Company company, Long id);

    boolean deleteCompanyById(Long id);
}

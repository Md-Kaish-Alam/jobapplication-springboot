package com.nuwaish.JobSeekerWebApplication.review.impl;

import com.nuwaish.JobSeekerWebApplication.company.Company;
import com.nuwaish.JobSeekerWebApplication.company.CompanyService;
import com.nuwaish.JobSeekerWebApplication.review.Review;
import com.nuwaish.JobSeekerWebApplication.review.ReviewRepository;
import com.nuwaish.JobSeekerWebApplication.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReviewByCompanyId(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if (company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReviewById(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream().filter(review -> review.getId().equals(reviewId)).findFirst().orElse(null);
    }

    @Override
    public boolean updateReviewById(Long companyId, Long reviewId, Review updatedReviewData) {
        if (companyService.getCompanyById(companyId) != null) {
            updatedReviewData.setCompany(companyService.getCompanyById(companyId));
            updatedReviewData.setId(reviewId);
            reviewRepository.save(updatedReviewData);
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteReviewById(Long companyId, Long reviewId) {
         if (companyService.getCompanyById(companyId) != null && reviewRepository.existsById(reviewId)) {
             Review review = reviewRepository.findById(reviewId).orElse(null);
             Company company = review.getCompany();
             company.getReviews().remove(review);
             review.setCompany(null);
             companyService.updateCompanyById(company, companyId);
             reviewRepository.deleteById(reviewId);
             return true;
         }
        return false;
    }
}

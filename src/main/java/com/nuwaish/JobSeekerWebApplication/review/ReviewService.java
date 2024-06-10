package com.nuwaish.JobSeekerWebApplication.review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(Long companyId);

    boolean addReviewByCompanyId(Long companyId, Review review);

    Review getReviewById(Long companyId, Long reviewId);

    boolean updateReviewById(Long companyId, Long reviewId, Review updatedReviewData);

    boolean deleteReviewById(Long companyId, Long reviewId);
}

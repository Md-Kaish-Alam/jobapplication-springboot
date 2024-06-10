package com.nuwaish.JobSeekerWebApplication.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviewsByCompanyId(@PathVariable Long companyId) {
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> addReviewByCompanyId(@PathVariable Long companyId, @RequestBody Review review) {
        boolean isReviewSaved = reviewService.addReviewByCompanyId(companyId, review);
        if (isReviewSaved) return new ResponseEntity<>("Review Added Successfully", HttpStatus.OK);

        return new ResponseEntity<>("Review Not Saved!", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long companyId, @PathVariable Long reviewId) {
        return new ResponseEntity<>(reviewService.getReviewById(companyId, reviewId), HttpStatus.OK);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReviewById(@PathVariable Long companyId, @PathVariable Long reviewId, @RequestBody Review updatedReviewData) {
        boolean isReviewUpdated = reviewService.updateReviewById(companyId, reviewId, updatedReviewData);
        if (isReviewUpdated)
            return new ResponseEntity<>("Review Updated Successfully", HttpStatus.OK);

        return new ResponseEntity<>("Review not found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReviewById(@PathVariable Long companyId, @PathVariable Long reviewId) {
        boolean isReviewDeleted = reviewService.deleteReviewById(companyId, reviewId);
        if (isReviewDeleted)
            return new ResponseEntity<>("Review is deleted successfully", HttpStatus.OK);

        return new ResponseEntity<>("Review is not found", HttpStatus.NOT_FOUND);
    }
}

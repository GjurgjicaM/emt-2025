package mk.ukim.finki.emtlabb.dto;

import mk.ukim.finki.emtlabb.model.domain.Review;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayReviewDto(
        String comment,
        Integer rate
) {
    public static DisplayReviewDto from(Review review) {
        return new DisplayReviewDto(review.getComment(),review.getRate());
    }

    public static List<DisplayReviewDto> from(List<Review>reviews){
        return reviews.stream().map(DisplayReviewDto::from).collect(Collectors.toList());
    }
}

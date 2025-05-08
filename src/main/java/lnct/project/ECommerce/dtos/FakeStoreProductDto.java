package lnct.project.ECommerce.dtos;

import lombok.Data;

@Data
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private String price;
    private String description;
    private String category;
    private String image;
    private RatingDto rating;

    @Data
    public static class RatingDto {
        private Double rate;
        private Integer count;
    }
}

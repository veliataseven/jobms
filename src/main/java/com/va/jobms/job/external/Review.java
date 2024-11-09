package com.va.jobms.job.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    private Long id;
    private String title;
    private String description;
    private double rating;
}

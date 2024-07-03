package com.example.demo.client.FakeStoreDTOs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FakeStoreRatingDTO {
    private double rate;
    private long count;
}
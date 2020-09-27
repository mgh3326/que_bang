package com.example.que_bang.modules.common;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import static com.example.que_bang.modules.common.BaseWeightEntity.weightInterval;

@Data
@RequiredArgsConstructor
public class WeightChangeDto {
  private Double largeValue;
  private Double smallValue;

  public Double targetValue() {
    if (largeValue == null && smallValue == null) {
      throw new IllegalStateException("large value and small value are null");
    }
    if (largeValue != null && smallValue != null && largeValue <= smallValue) {
      throw new IllegalStateException("smallValue great than or equal largeValue");
    }
    if (largeValue == null) {
      return smallValue - weightInterval;
    } else if (smallValue == null) {
      return largeValue + weightInterval;
    }
    return (largeValue + smallValue) / 2;
  }
}
package com.example.que_bang.modules.common;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseWeightEntity extends BaseTimeEntity {
  public final static Double defaultWeight = 1.0;
  public final static Double weightInterval = 0.01;
  private Double weight = defaultWeight;

  public static Double targetValue(WeightChangeDto weightChangeDto) {
    return weightChangeDto.targetValue();
  }
}

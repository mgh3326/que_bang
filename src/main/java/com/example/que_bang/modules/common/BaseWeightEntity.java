package com.example.que_bang.modules.common;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.util.Optional;
import java.util.stream.Stream;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseWeightEntity extends BaseTimeEntity {
  public final static Double defaultWeight = 1.0;
  public final static Double weightInterval = 0.01;
  private Double weight = defaultWeight;

  // TODO optinal로 변경 하면 좋을것 같다.
  public static Double middleValue(Double largeValue, Double smallValue) {
    if (largeValue == null && smallValue == null) {
      throw new IllegalStateException("large value and small value are null");
    }
    if (largeValue != null && smallValue != null && largeValue <= smallValue) {
      throw new IllegalStateException("smallValue great than or equal largeValue");
    }
    if (largeValue == null) {
      return smallValue - defaultWeight;
    } else if (smallValue == null) {
      return largeValue + defaultWeight;
    }
    return (largeValue + smallValue) / 2;
  }
}

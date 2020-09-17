package com.example.que_bang.modules.common;

import lombok.*;

import javax.persistence.*;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Weight {
  public final static Double defaultWeight = 1.0;
  public final static Double weightInterval = 0.01;
  @Column(name = "weight")
  private Double value;

  public static Weight createWeight() {
    Weight weight = new Weight();
    weight.setValue(defaultWeight);
    return weight;
  }
}

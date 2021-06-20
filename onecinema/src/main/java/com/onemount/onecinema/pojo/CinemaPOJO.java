package com.onemount.onecinema.pojo;

import lombok.Data;

@Data
public class CinemaPOJO {
  private Long id;
  private String name;
  public CinemaPOJO(Long id, String name) {
    this.id = id;
    this.name = name;
  }
}

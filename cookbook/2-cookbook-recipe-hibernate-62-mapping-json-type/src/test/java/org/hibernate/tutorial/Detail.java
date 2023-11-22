package org.hibernate.tutorial;


import java.io.Serializable;


public class Detail implements Serializable {

  private static final long serialVersionUID = 12131312L;

  public String name;
  public Integer height;

  public Detail() {
  }

  public Detail(String name, Integer height) {
    this.name = name;
    this.height = height;
  }


  public Integer getHeight() {
    return height;
  }

  public void setHeight(Integer height) {
    this.height = height;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}

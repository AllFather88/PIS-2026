package org.example.src.domain.model;

// класс доменной сущности "площадка"
public class Court {
  private final Long id;
  private String name;
  private String location;

  public Court(Long id, String name, String location) {
    this.id = id;
    this.name = name;
    this.location = location;
  }
  public void rename(String newName) {
    this.name = newName;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getLocation() {
    return location;
  }
}

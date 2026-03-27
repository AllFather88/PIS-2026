package org.example.src.domain.model.value_objects;

public record Location(double latitude, double longitude) {

  public Location {
    if (latitude < -90 || latitude > 90)
      throw new IllegalArgumentException("Latitude must be in range [-90, 90]");

    if (longitude < -180 || longitude > 180)
      throw new IllegalArgumentException("Longitude must be in range [-180, 180]");
  }
}


package com.go;

public class ResourceBlock extends Block {
  Resource resourceLayer = Resource.EMPTY_RESOURCE;

  public ResourceBlock (int row, int column) {
    super(row, column);
  }

  public void setResourceLayer (Resource resourceLayer) {
    this.resourceLayer = resourceLayer;
  }

  public Resource getResourceLayer () {
    return resourceLayer;
  }

  public boolean hasLayer (Resource resourceLayer) {
    return this.resourceLayer == resourceLayer;
  }
}

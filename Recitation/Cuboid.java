class Cuboid implements Volume,Mass,Density{
  public double density;
  public double x;
  public double y;
  public double z;
  public Cuboid(double density,double x,double y,double z){
    this.density= density;
    this.x = x;
    this.y = y;
    this.z = z;
  }
  public double getVolume(){
    return this.x*this.y*this.z;
  }
  public double getDensity(){
    return this.density;
  }
  public double getMass(){
    return getDensity()*getVolume();
  }
}

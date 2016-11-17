package Utility;

public class Meals {
  private int calorie;
  private String name;
  private String restaurant;
  private String item; //e=entree, s=side, d=drink
  
  public Meals(String rn, String n, int c, String i) {
    this.restaurant = rn;
    this.name = n;
    this.calorie = c;
    this.item = i;
  }
  
   public int getCalorie() {
    return this.calorie;
  } 
   
  public String getRestaurant(){
    return this.restaurant;
  }
   
  public String getName(){
    return this.name;
  }
  
  public String getItem(){
    return this.item;
  }
  
  @Override
  public String toString(){
    return this.restaurant + "," + this.name + "," + this.calorie + "," + this.item;
  }
}

package Utility;

public class Meals {
  private int calorie;
  private String entreeName;
  private String restaurant;
  private String item; //e=entree, s=side, d=drink
  
  public Meals(String rn, String n, int c, String i) {
    this.restaurant = rn;
    this.entreeName = n;
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
    return this.entreeName;
  }
  
  public String getItem(){
    return this.item;
  }
  
  @Override
  public String toString(){
    return this.restaurant + "," + this.entreeName + "," + this.calorie + "," + this.item;
  }
}

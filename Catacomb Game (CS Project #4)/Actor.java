import java.util.*;
public class Actor {
    private String name;
    private int health;
    private int maxDamage;
    private int row;
    private int col;
    
    public Actor(){
      
    }
    
    public Actor(String name, int health, int maxDamage, int row, int col){
      this.name = name;
      this.health = health;
      this.maxDamage = maxDamage;
      this.row = row;
      this.col = col;
    }
    
    public void setName(String username) {
      name = username;
    }
    
    public String getName() {
      return name;
    }
    
    public void setHealth(int health) {
      this.health = health;
    }
    
    public int getHealth() {
      return health;
    }
    
    public void setMaxDamage(int maxDamage) {
      this.maxDamage = maxDamage;
    }
    
    public int getMaxDamage() {
      return maxDamage;
    }
    
    public void setRow(int row){
      this.row = row;
    }
    
    public int getRow() {
      return row;
    }
    
    public void setCol(int col){
      this.col = col;
    }
    
    public int getCol() {
      return col;
    }
    
    public boolean isAlive(){
      if(health <= 0){
        return false;
      }
      
      return true;
    }
    
    public void damageTaken(int damage) {
      this.health -= damage;
    }
    
    public void attack(Actor actor) {
      Random random = new Random();
      int damage = random.nextInt(maxDamage + 1);
      actor.damageTaken(damage);
    }
    
    @Override
    public String toString(){
      String actorInfo = name + " at " + row + ", " + col + " with " + health + " health";
      return actorInfo;
    }
}
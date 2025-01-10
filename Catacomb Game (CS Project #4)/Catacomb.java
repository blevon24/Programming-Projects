import java.util.*;
public class Catacomb{
  Random random = new Random();
  private int size;
  private String[][] map;
  private ArrayList<Monster> monsters;
  
  public Catacomb(int size){
    this.size = size;
    this.map = new String[size][size];
    this.monsters = new ArrayList<>();
  }
  
  public void setMap(int size){
    for(int i = 0; i < size; i++){
      for(int j = 0; j < size; j++){
        map[i][j] = "[ ]";
      }
    }
    map[0][0] = "[H]";
    
    map[size - 1][size - 1] = "[Exit]";
    
    setMonsters(size);
    
    for(Monster monster: getMonsters()){
      map[monster.getRow()][monster.getCol()] = "[M]";
    }
  }
  
  public void printMap(String choice){
    while(true){
      if(choice.equalsIgnoreCase("yes")){
        for(int i = 0; i < size; i++){
          for(int j = 0; j < size; j++){
              System.out.print(map[i][j] + " ");
          }
          System.out.println();
        }
        break;
      }
      else if(!(choice.equalsIgnoreCase("no"))){
        System.out.println("Invalid Option");
      }
      else{
        break;
      }
    }
  }
  
  public void setMonsters(int size){
    int numMonsters = (size * size) / 6;
    
    for(int i = 0; i < numMonsters; i++){
      int row;
      int col;
      
      // Used ChatGPT
      do{
        row = random.nextInt(size - 1);
        col = random.nextInt(size - 1);
      } while ((row == 0 && col == 0) || ((row == (size - 1)) && (col == (size - 1))));
      
      Monster monster = new Monster("Monster " + (i + 1), 25, 5, row, col);
      monsters.add(monster);
    }
  }
  
  public ArrayList<Monster> getMonsters(){
    return monsters;
  }
  
  // ChatGPT
  public void moveHero(Hero hero, String direction, int size){
    map[hero.getRow()][hero.getCol()] = "[ ]";
    
    hero.move(direction, size);
    
    map[hero.getRow()][hero.getCol()] = "[H]";
    
    hero.setHealth(hero.getHealth() - 2);
  }
  
  public void removeActor(Actor actor){
    // ChatGPT
    if (actor instanceof Monster) {
      monsters.remove(actor);
    }
    
    else{
      actor = null;
    }
  }
  
  public boolean hasEscaped(Hero hero, int size){
    if((hero.getRow() == (size - 1)) && (hero.getCol() == (size - 1))){
      return true;
    }
    
    return false;
  }
}
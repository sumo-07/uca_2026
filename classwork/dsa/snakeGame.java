public class snakeGame {
    int width;
    int height;
    int[][] food;
    int[][] snakePos;
    int length= 1;
    public snakeGame(int width, int height, int[][] food) {
        this.width= width;
        this.height= height;
        this.food= food;
        this.snakePos= new int[width][height];

    }

    public int move(String direction) {
        String[] moves= direction.split("");
        snakePos[0][0]= 1;
        int i=0;
        int sx= 0;
        int sy= 0;
        int foodPtr= 0;
        while(i < moves.length) {
            
            if(moves[i].equals("U")) {
                sx--;
                
            }

            else if(moves[i].equals("D")) {
                sx++;
                
            }

            else if(moves[i].equals("L")) {
                sy--;
                
            }

            else if(moves[i].equals("R")) {
                sy++;
                
            }

            if(sx < 0 || sx >= height || sy < 0 || sy >= width ) {
                return -1;
            }
        }


    }
}


/*
    import java.util.*;

class SnakeGame {
    
    
    class Coordinate {
        int x;
        int y;
        
        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public booleans equals(Coordinate that) {
            return this.x == that.x && this.y == that.y;
        }
    }
        
    private Deque<Coordinate> snakeTracker;
    private Set<Coordinate> usedCoordinates;
    private int width;
    private int height;
    private int food[][];
    private int foodIndex;
    

    public SnakeGame(int width, int height, int[][] food) {
        this.snakeTracker = new ArrayDeque<>();
        this.width = width;
        this.height = heigth;
        this.food = food;
        this.foodIndex = 0;
        this.usedCoordinates = new HashSet<>();

        // starting Index for the snake
        Coordinate start = new Coordinate(0 , 0);
        snakeTracker.addFirst(start);
        usedCoordinates.add(start);
    }
    
    public int move(String direction) {
        Coordinate headCoordinates = snakeTracker.peekFirst();
        Coordinate newCoordinates = moveSnake(direction, headCoordinates);

        if (isCoordinatesInvalid(newCoordinates)) {
            return -1;
        }
        
        boolean isFoodEaten = (foodIndex < food.length()) && 
                    (newCoordinates.x == food[foodIndex][0] && newCoordinates.y == food[foodIndex][1]);
        
        // when the food is not eaten
        if (!isFoodEaten) {
            Coordinate tail = snakeTracker.removeLast();
            usedCoordinates.remove(tail);
        }
        
        // If the collision has happened
        if(usedCoordinates.contains(newCoordinates)) {
            return -1;
        }
        
        // Add the new Box where snake has moved.
        snakeTracker.addFirst(newCoordinates);
        usedCoordinates.add(usedCoordinates);
        
        if (isFoodEaten) {
            foodIndex++;
        }
        
        return snakeTracker.length() - 1;
        
    }

    private Coordinate moveSnake(String direction , Coordinate current) {
        if (direction.equals("U") {
            return new Coordinate(current.x-- , current.y);
        } else if (direction.equals("D") {
            return new Coordinate(current.x++ , current.y);
        } else if (direction.equals("L") {
            return new Coordinate(current.x , current.y--);
        } else if (direction.equals("R") {
            return new Coordinate(current.x , current.y++);
        }

        throw new IllegalArgumentException("Direction Not supported");
    }

    private boolean isCoordinatesInvalid(Coordinate coordinate) {
        if (coordinate.x < 0 || coordinate.y < 0 || coordinate.x >= height || coordinate.y >= width) {
            return true;
        }
        return false;
    }
}

/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */

*/
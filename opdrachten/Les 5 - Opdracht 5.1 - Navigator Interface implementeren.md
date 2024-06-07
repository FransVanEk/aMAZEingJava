## Opdracht Les 5: Implementeer een Interface voor een Navigator

In deze opdracht ga je een eenvoudige navigator implementeren die een speler door een doolhof leidt totdat hij het
doelpunt bereikt. De speler moet stoppen als hij niet meer verder kan of als het niet veilig is, en dan rechtsomdraaien
om een nieuwe route te vinden.

### Stappen:

1. **Maak een Nieuwe Class aan in de Package `navigator`**

   Maak een nieuwe class aan in de `nl.novi.amazeing.navigators` package. Deze class moet de `Navigator` interface
   implementeren. Schrijf de code om de uitgang te vinden en een lijst van instructies af te leveren.

---

#### Toelichting van de Logica van het Algoritme

- **Initialisatie:**
    - De `findPathToTarget`-methode initialiseert het doolhof en de startpositie van de speler. Een
      boolean-array `visited` wordt aangemaakt om bij te houden welke posities al bezocht zijn.


- **Markeer Startpositie als Bezocht:**
    - De startpositie wordt gemarkeerd als bezocht door deze toe te voegen aan de `path`-lijst en de bijbehorende
      positie in de `visited`-array op `true` te zetten.

- **Zoek de Route naar het Doelpunt:**
    - Een while-loop blijft draaien totdat de speler het doelpunt bereikt heeft. Binnen deze loop wordt geprobeerd om
      naar de volgende geldige positie te bewegen.
    - De `moveToNextPosition`-methode controleert de aangrenzende posities en beweegt naar een geldige positie indien
      mogelijk. Als er geen geldige posities zijn, wordt de `backtrack`-methode aangeroepen.

- **Backtracking:**
    - Als de speler niet verder kan, wordt de `backtrack`-methode aangeroepen om terug te keren naar de vorige positie
      en opnieuw te proberen een geldige route te vinden.

- **Instructies Converteren:**
    - Zodra het doelpunt bereikt is, worden de verzamelde posities in de `path`-lijst omgezet in instructies die de
      speler kan volgen.

---

2. **Pas de `main`-methode aan**

   Nadat je je eigen navigator hebt geïmplementeerd, kun je deze als volgt gebruiken in je main-applicatie:

   ```java
   package nl.novi.amazeing;

   import nl.novi.amazeing.factories.ChallengeFactory;
   import nl.novi.amazeing.navigators.SimpleNavigator;

   public class AMAZEingApp {

       public static void main(String[] args) {
           var challenge = ChallengeFactory.constructRandomChallenge(20);
           var player = challenge.player();
           player.showMaze();

           var instructions = new SimpleNavigator().findPathToTarget(challenge.maze(), 0, 0);
           player.followInstructions(instructions);
       }
   }
   ```

   Hiermee laat je de speler de instructies volgen die gegenereerd zijn door je navigator en beweeg je de speler door
   het doolhof totdat hij het doelpunt bereikt.

3. **Test het Project**

    - Compileer en voer de `AMAZEingApp`-klasse uit.
    - Controleer of de speler het doelpunt bereikt en zich correct gedraagt wanneer hij niet verder kan.

## Toelichting van de Logica van het Algoritme

1. **Initialisatie:**
    - De `findPathToTarget`-methode initialiseert het doolhof en de startpositie van de speler. Een
      boolean-array `visited` wordt aangemaakt om bij te houden welke posities al bezocht zijn.

2. **Markeer Startpositie als Bezocht:**
    - De startpositie wordt gemarkeerd als bezocht door deze toe te voegen aan de `path`-lijst en de bijbehorende
      positie in de `visited`-array op `true` te zetten.

3. **Zoek de Route naar het Doelpunt:**
    - Een while-loop blijft draaien totdat de speler het doelpunt bereikt heeft. Binnen deze loop wordt geprobeerd om
      naar de volgende geldige positie te bewegen.
    - De `moveToNextPosition`-methode controleert de aangrenzende posities en beweegt naar een geldige positie indien
      mogelijk. Als er geen geldige posities zijn, wordt de `backtrack`-methode aangeroepen.

4. **Backtracking:**
    - Als de speler niet verder kan, wordt de `backtrack`-methode aangeroepen om terug te keren naar de vorige positie
      en opnieuw te proberen een geldige route te vinden.

5. **Instructies Converteren:**
    - Zodra het doelpunt bereikt is, worden de verzamelde posities in de `path`-lijst omgezet in instructies die de
      speler kan volgen.

### Feedback

Als je tegen problemen aanloopt, vraag dan hulp aan je docent of medestudenten. Veel succes!

## Volledige uitwerking
<details>
<summary>
Klik hier voor de volledige uitwerking.
</summary>  

 ```java
   package nl.novi.amazeing.navigators;

import nl.novi.amazeing.helpers.PositionToInstructionsConverter;
import nl.novi.amazeing.models.Maze;
import nl.novi.amazeing.models.position.MazePosition;
import nl.novi.amazeing.models.position.Orientation;

import java.util.*;

public class SimpleNavigator implements Navigator {
    private Maze maze;
    private boolean[][] visited;
    private final List<MazePosition> path = new ArrayList<>();

    @Override
    public List<Instruction> findPathToTarget(Maze maze, int startX, int startY) {
        this.maze = maze;
        this.visited = new boolean[maze.getSizeX()][maze.getSizeY()];
        MazePosition startPosition = new MazePosition(startX, startY, Orientation.FacingRight);
        markPositionAsVisited(startPosition);

        while (!maze.isTarget(getCurrentPosition())) {
            if (!moveToNextPosition()) {
                backtrack();
            }
        }

        return PositionToInstructionsConverter.convertToInstructions(path);
    }

    private boolean moveToNextPosition() {
        for (MazePosition nextPosition : getAdjacentPositions(getCurrentPosition())) {
            if (isPositionValid(nextPosition)) {
                markPositionAsVisited(nextPosition);
                return true;
            }
        }
        return false;
    }

    private void backtrack() {
        if (path.size() > 1) {
            path.remove(path.size() - 1);
        }
        if (path.isEmpty()) {
            throw new IllegalArgumentException("Maze has no solution");
        }
    }

    private MazePosition getCurrentPosition() {
        return path.get(path.size() - 1);
    }

    private List<MazePosition> getAdjacentPositions(MazePosition position) {
        return Arrays.asList(
                new MazePosition(position.getPositionX() + 1, position.getPositionY(), Orientation.FacingRight),
                new MazePosition(position.getPositionX(), position.getPositionY() + 1, Orientation.FacingRight),
                new MazePosition(position.getPositionX(), position.getPositionY() - 1, Orientation.FacingRight),
                new MazePosition(position.getPositionX() - 1, position.getPositionY(), Orientation.FacingRight)
        );
    }

    private boolean isPositionValid(MazePosition position) {
        return maze.isAccessible(position) && !maze.isDeadly(position) && !isVisited(position);
    }

    private boolean isVisited(MazePosition position) {
        return visited[position.getPositionX()][position.getPositionY()];
    }

    private void markPositionAsVisited(MazePosition position) {
        path.add(position);
        visited[position.getPositionX()][position.getPositionY()] = true;
    }
}
   ```

</details>
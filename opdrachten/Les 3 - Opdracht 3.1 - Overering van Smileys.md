### Opdracht: Refactor en Uitbreiden van Smiley Classes

#### Doel

Je leert hoe je gedeelde functionaliteit kunt refactoren naar een basisclass en vervolgens een nieuwe klasse maakt die
daarvan erft.

#### Instructies

1. **Voorbereiding:**
    - Clone de repository van [aMAZEingJava](https://github.com/FransVanEk/aMAZEingJava) naar je lokale machine.
    - Open het project in je favoriete IDE.

2. **Refactor Smiley Classes:**
    - Ga naar de `SmileyFace` en `SadFace` classes in de repository.
    - Maak een nieuwe abstracte klasse genaamd `BaseSmileyFace` voor de gedeelde functionaliteit. (gezicht en ogen)
    - Zorg ervoor dat zowel `SmileyFace` als `SadFace` erven van `BaseSmileyFace`.
    - Verplaats de tekenlogica van de monden naar hun respectieve klassen.

   **BaseSmileyFace.java**
   ```java
   public abstract class BaseSmileyFace implements Drawable {
       protected static final int HEAD_RADIUS = 40;
       protected static final int EYE_RADIUS = 5;

       @Override
       public void draw(Graphics2D g2d, GraphicsPosition position) {
           var headRadius = (int) (HEAD_RADIUS * position.elementFactor());
           var eyeRadius = (int) (EYE_RADIUS * position.elementFactor());

           int headX = position.x() - headRadius / 2;
           int headY = position.y() - headRadius / 2;

           g2d.setColor(Color.YELLOW);
           g2d.fillOval(headX, headY, headRadius, headRadius);

           int leftEyeX = headX + headRadius / 3;
           int rightEyeX = headX + 2 * headRadius / 3;
           int eyeY = headY + headRadius / 3;

           g2d.setColor(Color.BLACK);
           g2d.fillOval(leftEyeX - eyeRadius / 2, eyeY - eyeRadius / 2, eyeRadius, eyeRadius);
           g2d.fillOval(rightEyeX - eyeRadius / 2, eyeY - eyeRadius / 2, eyeRadius, eyeRadius);

           drawMouth(g2d, headX, headY, headRadius);
       }

       protected abstract void drawMouth(Graphics2D g2d, int headX, int headY, int headRadius);
   }
   ```

   **SmileyFace.java**
   ```java
   public class SmileyFace extends BaseSmileyFace {
       @Override
       protected void drawMouth(Graphics2D g2d, int headX, int headY, int headRadius) {
           g2d.setColor(Color.BLACK);
           g2d.drawArc(headX + headRadius / 4, headY + headRadius / 3, headRadius / 2, headRadius / 4,
                   180, 180);
       }
   }
   ```

   **SadFace.java**
   ```java
   public class SadFace extends BaseSmileyFace {
       @Override
       protected void drawMouth(Graphics2D g2d, int headX, int headY, int headRadius) {
           g2d.setColor(Color.BLACK);
           g2d.drawArc(headX + headRadius / 4, headY + 2 * headRadius / 3, headRadius / 2, headRadius / 4,
                   0, 180);
       }
   }
   ```

3. **Maak je Eigen Smiley Class:**
    - Maak een nieuwe klasse genaamd `CustomFace` die ook erft van `BaseSmileyFace`.
    - Implementeer een unieke tekenlogica voor de mond.

   **CustomFace.java**
   ```java
   public class CustomFace extends BaseSmileyFace {
       @Override
       protected void drawMouth(Graphics2D g2d, int headX, int headY, int headRadius)       g2d.setColor(Color.BLACK)       // Bereken de start- en eindpunten van de rechte lijn voor de mond
            int mouthStartX = headX + headRadius / 4;
            int mouthStartY = headY + 2 * headRadius / 3;
            int mouthEndX = headX + 3 * headRadius / 4;
            int mouthEndY = headY + 2 * headRadius / 3;

            // Teken de rechte lijn voor de mond
            g2d.drawLine(mouthStartX, mouthStartY, mouthEndX, mouthEndY);
       }
   }
   ```

4. **Pas de Main Class aan:**
    - Zorg ervoor dat je de nieuwe klassen kunt instantiëren en gebruiken in een demo-applicatie.

   **Main.java**
   ```java
   public class Main {
       public static void main(String[] args) {
        
          var challenge = ChallengeFactory.constructSpiralMaze(9);
          var player = challenge.player();
          player.changeAppearance(new CustomFace());
          player.showMaze();
            
          // voeg logica toe om de speler te laten lopen
       }
   }
   ```

### Belangrijke Punten:

- Test je implementatie door verschillende smileys te tekenen en te controleren of de monden correct worden weergegeven.

Veel succes!
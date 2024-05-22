# Les 1

## Opdracht Les 1: Variabelen en het Maze-project

Maak een doolhof met de afmetingen 5 horizontaal en 8 verticaal. De opdracht geeft een basis voor de `main`-methode waarin je variabelen kunt toepassen.

### Stappen:
1. **Download de Repository**
    - Clone de repository naar je lokale machine:
   ```bash
   git clone https://github.com/FransVanEk/aMAZEingJava.git
   ```

2. **Open het Project in je IDE**
    - Open het project in je favoriete Java-IDE.

3. **Pas de `main`-methode aan**
    - Voeg in de `main`-methode variabelen toe om het doolhof aan te passen.
    - Voeg variabelen toe om de grootte van het doolhof en het aantal obstakels te definiëren.
    - Gebruik **`int`** als gegevenstype voor de variabelen.
<details>
  <summary>Klik hier om de Uitwerking van Opdracht 1.0 te zien</summary>

  ```java
 public class Main {
   public static void main(String[] args) {
      // Definieer variabelen voor doolhofmaten
      int horizontaalAantal = 5;
      int verticaalAantal = 8;

      // Maak de uitdaging aan met variabelen
      var challenge = ChallengeFactory.Les_1_Opdracht_1_0(horizontaalAantal, verticaalAantal);
      var player = challenge.player();
      player.showMaze();
   }
}
  ```

1. **Declaratie en Initialisatie van Variabelen:**
    - **`int horizontaalAantal = 5;`**
        - Hier declareren we een variabele met de naam `horizontaalAantal` en het gegevenstype `int` (gehele getallen).
        - De waarde wordt direct geïnitialiseerd met `5`.

    - **`int verticaalAantal = 8;`**
        - Op dezelfde manier wordt de variabele `verticaalAantal` gedeclareerd en geïnitialiseerd met `8`.

2. **Gebruik van Variabelen in een Methodeaanroep:**
    - In de aanroep **`ChallengeFactory.Les_1_Opdracht_1_0(horizontaalAantal, verticaalAantal);`** gebruiken we de variabelen om het doolhof aan te maken.

3. **Doolhof weergeven:**
    - Met **`player.showMaze();`** geven we het gegenereerde doolhof weer.
</details>


4. **Test het Project**
    - Compileer en voer de `Main`-klasse uit.
    - Controleer of het doolhof de juiste afmetingen heeft zoals aangegeven.

## Feedback
Als je tegen problemen aanloopt, vraag dan hulp aan je docent of medestudenten. Veel succes!



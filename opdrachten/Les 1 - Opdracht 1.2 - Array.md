## Opdracht Les 1: Array (1.2)

Maak een doolhof van 2 bij 2, geef de speler een naam, en laat de speler een rondje maken waarbij hij terugkeert naar de startpositie.

### Voorwaarden:
**Let op:** Zorg ervoor dat je de repository al hebt gedownload zoals beschreven in Opdracht 1.0.

### Stappen:
1. **Open het Project in je IDE**
    - Open het `aMAZEingJava`-project in je favoriete Java-IDE.

2. **Pas de `main`-methode aan**
    - Voeg variabelen toe om de naam van de speler en de grootte van het doolhof te definiëren.
    - Maak een array van instructies om de speler een rondje te laten maken.
    - Importeer de benodigde `Instruction`-klasse
    - 
    - **`int horizontaalAantal = 2;`**
        - Hier declareren we een variabele met de naam `horizontaalAantal` en het gegevenstype `int`.
        - De waarde wordt direct geïnitialiseerd met `2`.

    - **`int verticaalAantal = 2;`**
        - Op dezelfde manier wordt de variabele `verticaalAantal` gedeclareerd en geïnitialiseerd met `2`.

    - **`String spelerNaam = "JouwNaam";`**
        - Hier declareren we een variabele met de naam `spelerNaam` en het gegevenstype `String`.
        - Vul je eigen naam in in plaats van `"JouwNaam"`.

3. **Gebruik van Variabelen in Methodeaanroepen:**
    - In de aanroep **`ChallengeFactory.les1Opdracht1(horizontaalAantal, verticaalAantal);`** gebruiken we de variabelen om het doolhof aan te maken.
    - Met **`player.setName(spelerNaam);`** stellen we de naam van de speler in.
    - Met **`player.followInstructions(instructies);`** laat je de speler de gegeven instructies volgen.

4. **Gebruik van `Instruction` Enum:**
    - **Importeer `Instruction`:**  
      Voeg deze regel bovenaan je bestand toe:
      ```java
      import nl.novi.amazeing.navigators.Instruction;
      ```
    - **Instructies Opvolgen:**  
      Maak een array van `Instruction`-enums met de volgende waarden:
        - **`Instruction.FORWARD`**
        - **`Instruction.TURNRIGHT`**

<details>
  <summary>Klik hier om de Uitwerking van Opdracht 1.2 te zien</summary>

  ```java
  package nl.novi.amazeing;

  import nl.novi.amazeing.factories.ChallengeFactory;
  import nl.novi.amazeing.navigators.Instruction;

  public class AMAZEingApp {
      public static void main(String[] args) {
          // Definieer de grootte van het doolhof
          int horizontaalAantal = 2;
          int verticaalAantal = 2;

          // Vul hier je eigen naam in
          String spelerNaam = "JouwNaam";

          // Maak de uitdaging aan met variabelen
          var challenge = ChallengeFactory.constructChallenge1(horizontaalAantal, verticaalAantal);
          var player = challenge.player();
          
          // Zet de naam van de speler
          player.setName(spelerNaam);
          
          // Toon het doolhof
          player.showMaze();
          
          // Definieer instructies voor een rondje
          Instruction[] instructies = {
              Instruction.FORWARD,
              Instruction.TURNRIGHT,
              Instruction.FORWARD,
              Instruction.TURNRIGHT,
              Instruction.FORWARD,
              Instruction.TURNRIGHT,
              Instruction.FORWARD
          };
          
          // Laat de speler de instructies volgen
          player.followInstructions(instructies);
      }
  }
  ```



</details>

3. **Test het Project**
    - Compileer en voer de `AMAZEingApp`-klasse uit.
    - Controleer of het doolhof de juiste afmetingen heeft en of de speler een volledig rondje maakt.

### Belangrijke Punten:
- **Import van `Instruction`:** Zorg ervoor dat je de `Instruction`-klasse correct importeert.
- **Aanmaken Array van Enums:** Maak een array van instructies van het type `Instruction`.
- **Aanmaken van de Speler:** Stel de naam van de speler in en roep `player.setName()` aan.

## Feedback
Als je tegen problemen aanloopt, vraag dan hulp aan je docent of medestudenten. Veel succes!
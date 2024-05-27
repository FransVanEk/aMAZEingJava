## Opdracht Les 2: For Loop (2.1)

Maak een doolhof van 4 bij 4, geef de speler een naam, en laat de speler de rand van het doolhof volgen met behulp van een for-loop.

### Voorwaarden:
**Let op:** Zorg ervoor dat je de repository al hebt gedownload zoals beschreven in Opdracht 1.0.

### Stappen:
1. **Open het Project in je IDE**
   - Open het `aMAZEingJava`-project in je favoriete Java-IDE.

2. **Pas de `main`-methode aan**
   - Voeg variabelen toe om de naam van de speler en de grootte van het doolhof te definiëren.
   - Gebruik een for-loop om de speler de rand van het doolhof te laten volgen.
   - Importeer de benodigde `Instruction`-klasse.

   - **`int horizontaalAantal = 4;`**
      - Hier declareren we een variabele met de naam `horizontaalAantal` en het gegevenstype `int`.
      - De waarde wordt direct geïnitialiseerd met `4`.

   - **`int verticaalAantal = 4;`**
      - Op dezelfde manier wordt de variabele `verticaalAantal` gedeclareerd en geïnitialiseerd met `4`.

   - **`String spelerNaam = "JouwNaam";`**
      - Hier declareren we een variabele met de naam `spelerNaam` en het gegevenstype `String`.
      - Vul je eigen naam in in plaats van `"JouwNaam"`.

3. **Gebruik van Variabelen in Methodeaanroepen:**
   - In de aanroep **`ChallengeFactory.Les_1_Opdracht_1(horizontaalAantal, verticaalAantal);`** gebruiken we de variabelen om het doolhof aan te maken.
   - Met **`player.setName(spelerNaam);`** stellen we de naam van de speler in.
   - Gebruik een for-loop om de speler de rand van het doolhof te laten volgen.

4.**Instructies Opvolgen met een For Loop:**  
     Gebruik een for-loop om de speler een rechthoekige route te laten volgen langs de randen van het doolhof.

<details>
  <summary>Klik hier om de Uitwerking van Opdracht 2.1 te zien</summary>

  ```java
  package nl.novi.amazeing;

  import nl.novi.amazeing.factories.ChallengeFactory;
  
  public class AMAZEingApp {

      public static void main(String[] args) {
          int horizontaalAantal = 4;
          int verticaalAantal = 4;

          String spelerNaam = "jouw naam";

          var challenge = ChallengeFactory.Les_1_Opdracht_1(horizontaalAantal, verticaalAantal);
          var player = challenge.player();
          player.setSpeed(10);
          // Zet de naam van de speler
          player.setName(spelerNaam);
          player.showMaze();

          // Gebruik een for-loop om de rand van het doolhof te volgen
          for (int sides = 0; sides < 4; sides++) {
              for (int i = 0; i < 3; i++) {
                  player.moveForward();
              }
              player.turnRight();
          }
      }
  }
  ```

</details>

5. **Test het Project**
   - Compileer en voer de `AMAZEingApp`-klasse uit.
   - Controleer of het doolhof de juiste afmetingen heeft en of de speler de rand van het doolhof volgt.

### Belangrijke Punten:
- **Import van `Instruction`:** Zorg ervoor dat je de `Instruction`-klasse correct importeert.
- **Gebruik van een For Loop:** Maak gebruik van een for-loop om de instructies voor de speler te genereren.
- **Aanmaken van de Speler:** Stel de naam van de speler in en roep `player.setName()` aan.

## Vervolgopdracht Les 2: Variabele Loops (2.2)

Pas de for-loop aan zodat de speler de grootte van het doolhof herkent en correct langs de randen van het doolhof beweegt, ongeacht de grootte.

### Voorwaarden:
**Let op:** Zorg ervoor dat je de repository al hebt gedownload zoals beschreven in Opdracht 1.0.

### Stappen:
1. **Open het Project in je IDE**
   - Open het `aMAZEingJava`-project in je favoriete Java-IDE.

2. **Pas de `main`-methode aan**
   - Voeg variabelen toe om de grootte van het doolhof dynamisch te maken.
   - Pas de for-loop aan om afhankelijk van de grootte van het doolhof te bewegen.

3. **Gebruik van Variabelen in Methodeaanroepen:**
   - Pas de methodeaanroepen aan om de grootte van het doolhof dynamisch te verwerken.

4. **Aanpassen van de For Loop:**
   - Pas de for-loop aan zodat de speler de juiste aantal stappen neemt afhankelijk van de grootte van het doolhof.

<details>
  <summary>Klik hier om de Uitwerking van Vervolgopdracht 2.2 te zien</summary>

  ```java
  package nl.novi.amazeing;

  import nl.novi.amazeing.factories.ChallengeFactory;
  
  public class AMAZEingApp {

      public static void main(String[] args) {
          int horizontaalAantal = 4; // Pas deze waarde aan om de grootte van het doolhof te wijzigen
          int verticaalAantal = 4;

          String spelerNaam = "jouw naam";

          var challenge = ChallengeFactory.Les_1_Opdracht_1(horizontaalAantal, verticaalAantal);
          var player = challenge.player();
          player.setSpeed(10);
          // Zet de naam van de speler
          player.setName(spelerNaam);
          player.showMaze();

          // Gebruik een for-loop om de rand van het doolhof te volgen, aangepast aan de grootte van het doolhof
          for (int sides = 0; sides < 4; sides++) {
              for (int i = 0; i < horizontaalAantal - 1; i++) {
                  player.moveForward();
              }
              player.turnRight();
          }
      }
  }
  ```

</details>

5. **Test het Project**
   - Compileer en voer de `AMAZEingApp`-klasse uit.
   - Pas de waarden van `horizontaalAantal` en `verticaalAantal` aan en controleer of de speler correct langs de randen van het doolhof beweegt.

### Belangrijke Punten:
- **Gebruik van Variabelen:** Zorg ervoor dat de for-loop dynamisch reageert op de grootte van het doolhof.
- **Testen van Verschillende Groottes:** Test de code met verschillende waarden voor `horizontaalAantal` en `verticaalAantal` om te controleren of de speler correct beweegt.

## Feedback
Als je tegen problemen aanloopt, vraag dan hulp aan je docent of medestudenten. Veel succes!
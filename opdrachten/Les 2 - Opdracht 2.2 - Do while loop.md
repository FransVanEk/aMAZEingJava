## Opdracht Les 2: Do-While Loop (2.2)

Maak een doolhof van 4 bij 4, geef de speler een naam, en laat de speler de rand van het doolhof volgen met behulp van een do-while-loop.

### Voorwaarden:
**Let op:** Zorg ervoor dat je de repository al hebt gedownload zoals beschreven in Opdracht 1.0.

### Stappen:
1. **Open het Project in je IDE**
    - Open het `aMAZEingJava`-project in je favoriete Java-IDE.

2. **Pas de `main`-methode aan**
    - Voeg variabelen toe om de naam van de speler en de grootte van het doolhof te definiëren.
    - Gebruik een do-while-loop om de speler de rand van het doolhof te laten volgen.
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
    - In de aanroep **`ChallengeFactory.les1Opdracht1(horizontaalAantal, verticaalAantal);`** gebruiken we de variabelen om het doolhof aan te maken.
    - Met **`player.setName(spelerNaam);`** stellen we de naam van de speler in.
    - Gebruik een do-while-loop om de speler de rand van het doolhof te laten volgen.

4. **Instructies Opvolgen met een Do-While Loop:**  
      Gebruik een do-while-loop om de speler een rechthoekige route te laten volgen langs de randen van het doolhof.

<details>
  <summary>Klik hier om de Uitwerking van Opdracht 2.2 te zien</summary>

  ```java
  package nl.novi.amazeing;

import nl.novi.amazeing.factories.ChallengeFactory;

public class AMAZEingApp {

    public static void main(String[] args) {
        int horizontaalAantal = 4;
        int verticaalAantal = 4;

        String spelerNaam = "jouw naam";

        var challenge = ChallengeFactory.les1Opdracht1(horizontaalAantal, verticaalAantal);
        var player = challenge.player();
        player.setSpeed(10);
        // Zet de naam van de speler
        player.setName(spelerNaam);
        player.showMaze();

        // Gebruik een for-loop om de rand van het doolhof te volgen
        for (int sides = 0; sides < 4; sides++) {
            do {
                player.moveForward();
            } while (player.canMoveForward());
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
- **Gebruik van een Do-While Loop:** Maak gebruik van een do-while-loop om de instructies voor de speler te genereren.
- **Aanmaken van de Speler:** Stel de naam van de speler in en roep `player.setName()` aan.

## Vervolgopdracht Les 2: Variabele Loops (2.2)

Pas de do-while-loop aan zodat de speler de grootte van het doolhof herkent en correct langs de randen van het doolhof beweegt. Gebruik een while-loop in plaats van een do-while-loop. Verander de grootte van het doolhof naar 5 bij 1 en observeer wat er gebeurt.

### Voorwaarden:
**Let op:** Zorg ervoor dat je de repository al hebt gedownload zoals beschreven in Opdracht 1.0.

### Stappen:
1. **Open het Project in je IDE**
   - Open het `aMAZEingJava`-project in je favoriete Java-IDE.

2. **Pas de `main`-methode aan**
   - Voeg variabelen toe om de grootte van het doolhof dynamisch te maken.
   - Pas de loop aan om afhankelijk van de grootte van het doolhof te bewegen.
   - Verander de grootte van het doolhof naar 5 bij 1 en observeer wat er gebeurt.

3. **Gebruik van Variabelen in Methodeaanroepen:**
   - Pas de methodeaanroepen aan om de grootte van het doolhof dynamisch te verwerken.

4. **Aanpassen van de Loop:**
   - Gebruik een while-loop zodat de speler de juiste aantal stappen neemt afhankelijk van de grootte van het doolhof.

<details>
  <summary>Klik hier om de Uitwerking van Vervolgopdracht 3.2 te zien</summary>

  ```java
  package nl.novi.amazeing;

import nl.novi.amazeing.factories.ChallengeFactory;

public class AMAZEingApp {

    public static void main(String[] args) {
        int horizontaalAantal = 5; // Pas deze waarde aan om de grootte van het doolhof te wijzigen
        int verticaalAantal = 1;

        String spelerNaam = "jouw naam";

        var challenge = ChallengeFactory.les1Opdracht1(horizontaalAantal, verticaalAantal);
        var player = challenge.player();
        player.setSpeed(10);
        // Zet de naam van de speler
        player.setName(spelerNaam);
        player.showMaze();

        for (int sides = 0; sides < 4; sides++) {
            while (player.canMoveForward()) {
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
   - Verander de grootte van het doolhof naar 5 bij 1 en observeer wat er gebeurt. Los het probleem op door een while-loop te gebruiken in plaats van een do-while-loop.

### Belangrijke Punten:
- **Gebruik van Variabelen:** Zorg ervoor dat de loop dynamisch reageert op de grootte van het doolhof.
- **Testen van Verschillende Groottes:** Test de code met verschillende waarden voor `horizontaalAantal` en `verticaalAantal` om te controleren of de speler correct beweegt.
- **Gebruik van een While Loop:** Gebruik een while-loop om te voorkomen dat de speler een stap doet wanneer dat niet mogelijk is.

## Feedback
Als je tegen problemen aanloopt, vraag dan hulp aan je docent of medestudenten. Veel succes!
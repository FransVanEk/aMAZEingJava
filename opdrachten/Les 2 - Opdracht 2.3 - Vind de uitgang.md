## Opdracht Les 2: vind de uitgang (2.3)

Laat de speler door het doolhof bewegen totdat hij het doelpunt bereikt. De speler moet stoppen als hij niet meer verder kan of als het niet veilig is, en dan rechtsomdraaien om een nieuwe route te vinden.

### Voorwaarden:
**Let op:** Zorg ervoor dat je de repository al hebt gedownload zoals beschreven in Opdracht 1.0.

### Stappen:
1. **Open het Project in je IDE**
    - Open het `aMAZEingJava`-project in je favoriete Java-IDE.

2. **Verken het Doolhof**
    - Laat de speler het doolhof verkennen en noteer de criteria die bepalen of hij kan bewegen of moet draaien.

3. **Pas de `main`-methode aan**
    - Voeg variabelen toe om de naam van de speler te definiëren.
    - Gebruik een while-loop om de speler door het doolhof te laten bewegen totdat hij het doelpunt bereikt.

    - **`String spelerNaam = "JouwNaam";`**
        - Hier declareren we een variabele met de naam `spelerNaam` en het gegevenstype `String`.
        - Vul je eigen naam in in plaats van `"JouwNaam"`.

4. **Gebruik van Methodeaanroepen:**
    - In de aanroep **`ChallengeFactory.constructChallenge2_1();`** gebruiken we de variabelen om het doolhof aan te maken.
    - Met **`player.setName(spelerNaam);`** stellen we de naam van de speler in.
    - Gebruik een while-loop om de speler door het doolhof te laten bewegen totdat hij het doelpunt bereikt.

5. **Instructies Opvolgen met een While Loop:**  
   Gebruik een while-loop om de speler te laten bewegen totdat hij het doelpunt bereikt.

<details>
  <summary>Klik hier om de Uitwerking van Opdracht 2.3 te zien</summary>

  ```java
  package nl.novi.amazeing;

import nl.novi.amazeing.factories.ChallengeFactory;

public class AMAZEingApp {

    public static void main(String[] args) {
        String spelerNaam = "jouw naam";

        var challenge = ChallengeFactory.constructChallenge2();
        var player = challenge.player();
        player.setSpeed(100);
        // Zet de naam van de speler
        player.setName(spelerNaam);
        player.showMaze();

        // Gebruik een while-loop om de speler naar het doelpunt te bewegen
        while (!player.isOnTarget()) {
            while (player.canMoveForward() && !player.isOnTarget()) {
                player.moveForward();
            }
            player.turnRight();
        }
    }
}
  ```

</details>

6. **Test het Project**
    - Compileer en voer de `AMAZEingApp`-klasse uit.
    - Controleer of de speler het doelpunt bereikt en zich correct gedraagt wanneer hij niet verder kan.

### Belangrijke Punten:
- **Gebruik van Variabelen:** Zorg ervoor dat de loop dynamisch reageert op de grootte van het doolhof.
- **Testen van Verschillende Groottes:** Test de code met verschillende waarden voor `horizontaalAantal` en `verticaalAantal` om te controleren of de speler correct beweegt.
- **Gebruik van een While Loop:** Gebruik een while-loop om de speler te laten bewegen totdat hij het doelpunt bereikt.

## Vervolgopdracht Les 2: Veilig naar het Doel Punt (2.4)

Verander het doolhof zodat er een kruis wordt geplaatst dat dodelijk is. Laat de speler door het doolhof bewegen totdat hij het doelpunt bereikt, waarbij hij controleert of het veilig is om verder te gaan.

### Voorwaarden:
**Let op:** Zorg ervoor dat je de repository al hebt gedownload zoals beschreven in Opdracht 1.0.

### Stappen:
1. **Open het Project in je IDE**
    - Open het `aMAZEingJava`-project in je favoriete Java-IDE.

2. **Pas de `main`-methode aan**
    - Verander de aanroep naar **`ChallengeFactory.constructChallenge2_1();`** om het doolhof met het dodelijke kruis te maken.
    - Voeg variabelen toe om de naam van de speler te definiëren.
    - Gebruik een while-loop om de speler door het doolhof te laten bewegen totdat hij het doelpunt bereikt, waarbij je controleert of het veilig is om verder te gaan met **`player.isSafeToMoveForward()`**.

    - **`String spelerNaam = "JouwNaam";`**
        - Hier declareren we een variabele met de naam `spelerNaam` en het gegevenstype `String`.
        - Vul je eigen naam in in plaats van `"JouwNaam"`.

3. **Gebruik van Methodeaanroepen:**
    - Pas de methodeaanroepen aan om de grootte van het doolhof dynamisch te verwerken.

4. **Aanpassen van de Loop:**
    - Gebruik een while-loop zodat de speler de juiste aantal stappen neemt afhankelijk van de veiligheid van de route.

### Voorbeelden:

<details>
  <summary>Klik hier om de Uitwerking van Vervolgopdracht 2.4 te zien</summary>

  ```java
  package nl.novi.amazeing;

import nl.novi.amazeing.factories.ChallengeFactory;

public class AMAZEingApp {

    public static void main(String[] args) {
        String spelerNaam = "jouw naam";

        var challenge = ChallengeFactory.constructChallenge2_1();
        var player = challenge.player();
        player.setSpeed(100);
        // Zet de naam van de speler
        player.setName(spelerNaam);
        player.showMaze();

        // Gebruik een while-loop om de speler naar het doelpunt te bewegen en controleer op veiligheid
        while (!player.isOnTarget()) {
            while (player.isSafeToMoveForward() && !player.isOnTarget()) {
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
    - Controleer of de speler het doelpunt bereikt en zich correct gedraagt wanneer hij niet verder kan of wanneer het niet veilig is om verder te gaan.

### Belangrijke Punten:
- **Gebruik van Variabelen:** Zorg ervoor dat de loop dynamisch reageert op de grootte van het doolhof en de veiligheid van de speler.
- **Testen van Verschillende Groottes en Veiligheid:** Test de code met verschillende waarden voor `horizontaalAantal` en `verticaalAantal` om te controleren of de speler correct beweegt en veilig blijft.
- **Gebruik van Veiligheidscontrole:** Gebruik de functie `player.isSafeToMoveForward()` om te controleren of het veilig is om verder te gaan.

## Feedback
Als je tegen problemen aanloopt, vraag dan hulp aan je docent of medestudenten. Veel succes!


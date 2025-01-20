## Opdracht Les 1: Variabelen en het Maze-project (1.1)


Maak een doolhof waarbij de breedte altijd twee keer de hoogte is, en voeg een variabele toe voor de naam van de speler. Gebruik je eigen naam als de naam van de speler. De naam van de speler moet zichtbaar zijn bij het tonen van het doolhof.

### Voorwaarden:
**Let op:** Zorg ervoor dat je de repository al hebt gedownload zoals beschreven in Opdracht 1.0.

### Stappen:
1. **Open het Project in je IDE**
   - Open het `aMAZEingJava`-project in je favoriete Java-IDE.

2. **Pas de `main`-methode aan**
   - Voeg in de `main`-methode variabelen toe om de naam van de speler en de grootte van het doolhof te definiëren.
   - Zorg ervoor dat de breedte altijd twee keer de hoogte is.
   - Gebruik **`int`** als gegevenstype voor de breedte en hoogte, en **`String`** voor de naam van de speler.
   - Voeg ook de regel `player.setName(spelerNaam);` toe op de juiste plek om te zorgen dat de player jou naam krijgt.

<details>
  <summary>Klik hier om de Uitwerking van Opdracht 1.1 te zien</summary>

  ```java
  public class Main {
      public static void main(String[] args) {
          // Definieer de hoogte en bereken de breedte
          int verticaalAantal = 4; // De hoogte van het doolhof
          int horizontaalAantal = verticaalAantal * 2; // Breedte is twee keer de hoogte

          // Vul hier je eigen naam in
          String spelerNaam = "JouwNaam";

          // Maak de uitdaging aan met variabelen
          var challenge = ChallengeFactory.constructChallenge1(horizontaalAantal, verticaalAantal);
          var player = challenge.player();
          
          // Zet de naam van de speler
          player.setName(spelerNaam);
          
          // Toon het doolhof op basis van de variabelen
          player.showMaze();
      }
  }
  ```

1. **Declaratie en Initialisatie van Variabelen:**
   - **`int verticaalAantal = 4;`**
      - Hier declareren we een variabele met de naam `verticaalAantal` en het gegevenstype `int`.
      - De waarde wordt direct geïnitialiseerd met `4`.

   - **`int horizontaalAantal = verticaalAantal * 2;`**
      - Hier berekenen we de breedte door de hoogte te vermenigvuldigen met `2`.

   - **`String spelerNaam = "JouwNaam";`**
      - Hier declareren we een variabele met de naam `spelerNaam` en het gegevenstype `String`.
      - Vul je eigen naam in in plaats van `"JouwNaam"`.

2. **Gebruik van Variabelen in Methodeaanroepen:**
   - In de aanroep **`ChallengeFactory.constructChallenge1(horizontaalAantal, verticaalAantal);`** gebruiken we de variabelen om het doolhof aan te maken.
   - Met **`player.setName(spelerNaam);`** stellen we de naam van de speler in.

3. **Doolhof Weergeven:**
   - Met **`player.showMaze();`** geven we het gegenereerde doolhof weer en tonen we de naam van de speler.

**Alternatieve aanpak** 

````java
  int horizontaalAantal = verticaalAantal / 2;  // Aantal cellen horizontaal
  int verticaalAantal = 8;    // Aantal cellen verticaal
````

De reden waarom het niet mogelijk is om de breedte (`horizontaalAantal`) te berekenen uit de hoogte (`verticaalAantal`) door het delen van `verticaalAantal`, terwijl `verticaalAantal` pas na die berekening wordt geïnitialiseerd, is te wijten aan de volgorde van variabele-initialisatie.

- **Volgorde van Initialisatie:**
   - Java leest code van boven naar beneden, wat betekent dat variabelen in volgorde van hun declaratie en initialisatie worden verwerkt.
   - Als een variabele wordt gebruikt in een berekening vóórdat deze is geïnitialiseerd, kan de compiler de waarde niet weten en zal een fout optreden.

**Foutmelding:**  
  Deze code zal resulteren in de foutmelding **"Cannot find symbol"** of **"Variable verticaalAantal might not have been initialized"**.


---
</details>

3. **Test het Project**
   - Compileer en voer de `Main`-klasse uit.
   - Controleer of het doolhof de juiste afmetingen heeft en of jouw naam zichtbaar is in de console-uitvoer.

## Feedback
Als je tegen problemen aanloopt, vraag dan hulp aan je docent of medestudenten. Veel succes!
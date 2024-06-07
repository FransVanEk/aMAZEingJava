# Les 1

## Introductie opdracht

Hoewel je nog niet alle concepten van Java hebt geleerd, kun je al beginnen met programmeren. Soms heb je daarbij code nodig die je misschien nog niet volledig begrijpt. Dat is niet erg; we gaan die concepten later in detail behandelen. Voor nu is het belangrijk om te beginnen met het toepassen van wat je wel hebt geleerd. De code die je nu krijgt, wordt later duidelijk en verder uitgelegd.

Puntnotatie is een manier om toegang te krijgen tot de eigenschappen (attributen) en methoden (functies) van een object in Java. Dit stelt je in staat om de onderdelen van een klasse te gebruiken. In deze opdracht zul je de puntnotatie toepassen om een doolhof te maken en aan te passen.

Hieronder zie je hoe je een klasse aanmaakt en hoe je puntnotatie gebruikt om de eigenschappen en methoden van een object te benaderen:

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

In dit voorbeeld creëren we een object van de klasse `ChallengeFactory`, stellen we de afmetingen van het doolhof in, en roepen we de methode `showMaze()` aan om het doolhof weer te geven.


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



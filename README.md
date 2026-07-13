# Monety Mod (Fabric, Minecraft 1.21.11)

Ten mod dodaje trzy przedmioty (monety) oraz HUD pokazujący ile gotówki gracz ma w ekwipunku:

- **Moneta 1ZŁ** – warta 1 zł
- **Moneta 50ZŁ** – warta 50 zł
- **Moneta 100ZŁ** – warta 100 zł

W prawym górnym rogu ekranu wyświetla się napis `Kasa: X zł`, gdzie X to suma wartości
wszystkich monet w ekwipunku gracza (główny ekwipunek + pasek szybkiego dostępu + druga ręka).

## Dlaczego dostajesz kod źródłowy, a nie gotowy .jar?

Zbudowanie gotowego pliku .jar wymaga narzędzia Gradle + wtyczki Fabric Loom, która podczas
budowania **pobiera z internetu** oficjalne biblioteki Minecrafta, mapowania Yarn oraz Fabric API
(łącznie kilkaset MB danych). Środowisko, w którym pracuję, nie ma dostępu do internetu, więc nie
jestem w stanie sam uruchomić tej kompilacji. Za to przygotowałem kompletny, gotowy do zbudowania
projekt — wystarczy go zbudować u siebie jednym poleceniem.

## Wymagania

- **Java Development Kit (JDK) 21** (np. z adoptium.net / Eclipse Temurin)
- Połączenie z internetem (do pobrania zależności przy pierwszym budowaniu)
- IntelliJ IDEA (zalecane, ma najlepsze wsparcie dla Fabric) LUB tylko wiersz poleceń

## Budowanie z wiersza poleceń

1. Rozpakuj folder `monety-mod` w dowolnym miejscu.
2. Wejdź do folderu w terminalu: `cd monety-mod`
3. Wygeneruj wrapper Gradle (jeśli masz zainstalowany Gradle 8.10+):
   ```
   gradle wrapper --gradle-version 8.11
   ```
4. Zbuduj mod:
   - Linux/Mac: `./gradlew build`
   - Windows: `gradlew.bat build`
5. Gotowy plik .jar pojawi się w: `build/libs/monety-mod-1.0.0.jar`

## Budowanie w IntelliJ IDEA (najprostsza opcja)

1. Zainstaluj wtyczkę **Minecraft Development** (Settings → Plugins).
2. `File → Open` i wskaż folder `monety-mod`.
3. IntelliJ sam wykryje `build.gradle` i pobierze zależności (potrzebny internet).
4. Po zaimportowaniu, uruchom zadanie Gradle `build` (zakładka Gradle po prawej → Tasks → build → build).
5. Gotowy .jar znajdziesz w `build/libs/`.

## Instalacja w grze

1. Zainstaluj **Fabric Loader** dla Minecraft 1.21.11 (fabricmc.net/use).
2. Zainstaluj bibliotekę **Fabric API** (wersja 0.141.4+1.21.11 lub nowsza dla 1.21.11) —
   plik .jar wrzuć do folderu `mods`.
3. Wrzuć zbudowany `monety-mod-1.0.0.jar` do tego samego folderu `mods`.
4. Uruchom Minecraft z profilem Fabric 1.21.11.

## Struktura projektu

```
monety-mod/
├── build.gradle              - konfiguracja budowania
├── gradle.properties         - numery wersji (Minecraft, Yarn, Fabric API, Loader)
├── settings.gradle           - repozytorium wtyczki Fabric Loom
├── src/main/java/pl/monety/
│   ├── MonetyMod.java         - główna klasa moda, rejestruje przedmioty
│   ├── ModItems.java          - definicje 3 monet
│   └── MonetyClient.java      - rysowanie HUD "Kasa: X zł"
└── src/main/resources/
    ├── fabric.mod.json        - metadane moda
    └── assets/monety/
        ├── lang/pl_pl.json     - polskie nazwy przedmiotów
        ├── lang/en_us.json     - angielskie nazwy (fallback)
        ├── models/item/*.json  - modele przedmiotów
        └── textures/item/*.png - proste tekstury monet (możesz je podmienić)

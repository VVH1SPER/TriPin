# TriPin – Project Overview

## Aký problém riešim

Používatelia majú veľké množstvo fotografií, ktoré sa po čase ťažko triedia a vyhľadávajú, pretože bežná galéria neposkytuje dostatočne pohodlnú organizáciu podľa vlastných priečinkov, tagov a miesta vytvorenia fotografie.

## Kto sú moji používatelia

Primárnymi používateľmi sú ľudia, ktorí často fotografujú mobilným telefónom a chcú si fotografie organizovať už v momente ich vytvorenia.

Typickými používateľmi môžu byť:

- cestovatelia a turisti,
- študenti,
- ľudia, ktorí si fotografujú dokumenty, poznámky alebo predmety,
- používatelia, ktorí chcú mať fotografie rozdelené podľa vlastných kategórií,
- používatelia, ktorí chcú neskôr jednoducho nájsť fotografie podľa miesta, tagu alebo priečinka.

## Ako chcem problém vyriešiť – MVP

MVP bude Android aplikácia, ktorá umožní používateľovi vytvoriť fotografiu a okamžite ju zaradiť do virtuálneho priečinka a pridať jej jeden alebo viac tagov.

### Funkcie MVP

- vytvorenie fotografie priamo v aplikácii,
- náhľad fotografie pred uložením,
- možnosť fotografiu opätovne nasnímať,
- uloženie fotografie do interného úložiska aplikácie,
- vytváranie virtuálnych priečinkov,
- vytváranie vnorených priečinkov,
- premenovanie a odstránenie priečinkov,
- presúvanie fotografií medzi virtuálnymi priečinkami,
- vytváranie a premenovanie tagov,
- priradenie viacerých tagov jednej fotografii,
- pridávanie a odoberanie tagov už existujúcim fotografiám,
- vyhľadávanie fotografií podľa jedného alebo viacerých tagov,
- vyhľadávanie v režime AND aj OR,
- zobrazenie fotografií v galérii,
- zobrazenie fotografie na celú obrazovku a zoom,
- triedenie fotografií podľa dátumu,
- zobrazenie základných informácií o fotografii,
- odstránenie fotografie,
- export fotografie alebo priečinka mimo aplikácie.

### Geolokácia v MVP

Ak používateľ povolí prístup ku geolokácii, aplikácia uloží k fotografii aj súradnice miesta, kde bola vytvorená.

Na základe polohy môže aplikácia neskôr navrhovať tagy podľa blízkych miest alebo pamiatok. V základnom MVP však bude prioritou správne uloženie polohy k fotografii a príprava dátového modelu na ďalšie rozšírenie.

## Čo v MVP nebude – Non-goals

Nasledujúce funkcie nebudú súčasťou prvej verzie, pretože by výrazne zvýšili rozsah, technickú náročnosť a čas potrebný na dokončenie základnej funkčnej verzie aplikácie.

### Automatické rozpoznávanie obsahu fotografie pomocou AI

Aplikácia nebude v MVP automaticky analyzovať obsah fotografie a určovať, čo sa na nej nachádza.

**Dôvod:** vyžadovalo by to model strojového učenia alebo externú AI službu, čo nie je potrebné na overenie hlavnej myšlienky aplikácie.

### Automatické sledovanie celej trasy na pozadí

Aplikácia nebude nepretržite sledovať pohyb používateľa pomocou GPS.

**Dôvod:** background location výrazne zvyšuje náročnosť implementácie, spotrebu batérie a požiadavky na Android oprávnenia a ochranu súkromia.

### Automaticky generovaná mapa celej cesty

Prvá verzia nebude vytvárať kompletnú interaktívnu mapu cesty s trasou medzi jednotlivými bodmi.

**Dôvod:** táto funkcia bude nadstavbou nad geolokačnými dátami fotografií a je vhodnejšia pre ďalšiu verziu po dokončení jadra aplikácie.

### Cloudová synchronizácia

Fotografie a metadata sa nebudú synchronizovať medzi zariadeniami.

**Dôvod:** MVP má fungovať lokálne bez potreby používateľského účtu, servera alebo cloudovej infraštruktúry.

### Používateľské účty a prihlasovanie

Aplikácia nebude vyžadovať registráciu ani prihlásenie.

**Dôvod:** pre lokálnu organizáciu fotografií nie je účet potrebný.

### Zdieľanie knižnice medzi používateľmi

Nebude možné vytvárať spoločné albumy alebo zdieľané priečinky.

**Dôvod:** táto funkcia by vyžadovala backend, autentifikáciu a synchronizáciu dát.

### Pokročilý editor fotografií

Aplikácia nebude obsahovať filtre, retušovanie, úpravu farieb ani pokročilé editovanie obrázkov.

**Dôvod:** hlavnou úlohou aplikácie je organizácia fotografií, nie ich úprava.

## Aké technológie potrebujem

### Platforma a jazyk

- **Android** – cieľová platforma aplikácie,
- **Kotlin** – hlavný programovací jazyk.

### Používateľské rozhranie

- **Jetpack Compose** – tvorba moderného používateľského rozhrania,
- **Navigation Compose** – navigácia medzi obrazovkami aplikácie.

### Architektúra

- **MVVM** – oddelenie používateľského rozhrania, aplikačnej logiky a dátovej vrstvy,
- **ViewModel** – správa stavu jednotlivých obrazoviek,
- **Repository pattern** – jednotný prístup k databáze, súborom a externým zdrojom,
- **Kotlin Coroutines / Flow** – asynchrónne spracovanie a reakcia na zmeny dát.

### Kamera

- **CameraX** – fotografovanie a živý náhľad z kamery.

### Lokálne dáta

- **Room** – lokálna SQLite databáza,
- **interné úložisko aplikácie** – uloženie originálnych fotografií,
- **DataStore** – uloženie používateľských nastavení.

### Práca s obrázkami

- **Coil** – načítavanie a zobrazovanie fotografií a náhľadov.

### Geolokácia

- **Android Location APIs / Fused Location Provider** – získanie aktuálnej polohy používateľa pri fotografovaní.

### Mapa a miesta

Pre rozšírenie aplikácie o mapu a návrhy miest bude potrebné zvoliť službu pre:

- zobrazenie mapy,
- získavanie blízkych bodov záujmu,
- prevod GPS súradníc na názov miesta.

Možnými riešeniami sú napríklad Google Maps + Places API alebo OpenStreetMap s vhodnou službou pre POI.

### Export

- **Storage Access Framework** – export fotografií do používateľom zvoleného umiestnenia,
- **FileProvider / Android Share API** – bezpečné zdieľanie fotografií s inými aplikáciami.

## Zhrnutie

MVP aplikácie TriPin sa zameria na základnú hodnotu produktu: používateľ vytvorí fotografiu a hneď ju zaradí do priečinka a označí tagmi. Fotografia sa uloží spolu s metadátami a voliteľnou geolokáciou.

Pokročilé funkcie ako automatické rozpoznávanie miest, interaktívne mapy ciest, AI kategorizácia, cloudová synchronizácia a sledovanie trasy budú riešené až po dokončení a overení základnej verzie aplikácie.

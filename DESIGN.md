# TriPin — Dizajnový dokument

## 1. Účel a princípy návrhu

Tento dokument opisuje navrhovanú štruktúru, používateľské toky, informačnú architektúru a technický návrh aplikácie TriPin. Návrh nadväzuje na požiadavky v [SPEC.md](./SPEC.md) a na produktový backlog.

TriPin má používateľovi umožniť usporiadať fotografiu už pri jej vytvorení. Návrh preto uprednostňuje fotografovanie a rýchle označenie, jasnú navigáciu v priečinkoch a nepretržitú dostupnosť vyhľadávania.

### Princípy

- **Najprv fotografia:** vytvorenie a uloženie snímky má byť dostupné priamo z hlavného zobrazenia.
- **Rýchle zaradenie:** posledný alebo aktuálny priečinok sa ponúkne automaticky; výber značiek nesmie používateľa nútiť opustiť proces ukladania.
- **Priečinky a značky sú odlišné nástroje:** priečinok určuje miesto v hierarchii; značka je nezávislý atribút fotografie.
- **Používateľ rozhoduje:** poloha a návrhy značiek sú voliteľné, mazanie je potvrdené a export nezasahuje do originálov.
- **Lokálne fungovanie:** základná práca s knižnicou nevyžaduje účet ani sieťové pripojenie.
- **Postupné rozšírenie:** model a navigácia majú umožniť doplniť výlety a mapu bez narušenia základnej galérie.

## 2. Cieľové zariadenie a technologický návrh

- Platforma: Android, primárne telefóny.
- Jazyk a UI toolkit: Kotlin a Jetpack Compose.
- Navigácia: Navigation Compose.
- Architektúra: MVVM; obrazovky odoberajú stav z ViewModelov, logiku a zdroje dát sprostredkujú repozitáre.
- Lokálna databáza: Room/SQLite.
- Súbory fotografií: interné úložisko aplikácie.
- Kamera: CameraX.
- Zobrazovanie obrázkov: Coil.
- Nastavenia: DataStore.
- Asynchrónna práca a stav: Kotlin Coroutines a Flow.

Tieto technológie zodpovedajú aktuálnemu smerovaniu projektu. Podľa konfigurácie Android modulu sa momentálne cieli na Android API 29 a vyššie.

## 3. Používateľské roly

V MVP je iba jeden používateľ aplikácie. Nie sú používateľské účty ani roly s odlišnými oprávneniami.

Používateľ môže:

- vytvárať a spravovať fotografie, priečinky a značky;
- vyhľadávať a prezerať svoju lokálnu knižnicu;
- povoliť alebo odmietnuť voliteľný prístup k polohe;
- exportovať vybrané fotografie;
- odstrániť vlastné fotografie alebo priečinky.

## 4. Informačná architektúra

### Hlavná navigácia

Navrhované hlavné sekcie:

1. **Fotografie** — mriežka knižnice a aktuálneho priečinka.
2. **Priečinky** — hierarchia priečinkov a vnorených priečinkov.
3. **Značky / Hľadať** — správa značiek a vyhľadávanie podľa značiek s režimom AND/OR.
4. **Nastavenia** — predvoľby, napríklad triedenie a správanie pri polohe.
5. **Fotografovanie** — výrazná centrálna akcia dostupná z hlavnej navigácie.

Na menšom displeji sa majú zachovať najdôležitejšie akcie bez preplnenia spodnej navigácie. Ak sa všetky sekcie nezmestia, menej časté akcie sa umiestnia do ponuky alebo na príslušné obrazovky.

### Navrhované obrazovky

| Obrazovka | Účel | Hlavné akcie |
|---|---|---|
| Knižnica | Zobrazenie fotografií, rýchly vstup do priečinkov a vyhľadávania | Fotografovať, hľadať, triediť, otvoriť fotografiu |
| Obsah priečinka | Zobrazenie fotografií a vnorených priečinkov na aktuálnej úrovni | Vytvoriť priečinok, vytvoriť podpriečinok, fotografovať do tohto priečinka |
| Vyhľadávanie | Výber značiek a spôsobu zhody | Vybrať značky, prepínať AND/OR, otvoriť výsledok |
| Značky | Zobrazenie a úprava značiek | Vytvoriť a premenovať značku |
| Kamera | Živý náhľad a zachytenie snímky | Odfotiť, zatvoriť kameru |
| Náhľad a zaradenie | Kontrola snímky pred uložením | Odfotiť znova, vybrať priečinok, pridať značky, povoliť polohu, uložiť |
| Detail fotografie | Zobrazenie snímky a dostupných metadát | Priblížiť, upraviť priečinok alebo značky, exportovať, odstrániť |
| Nastavenia | Predvoľby a informácie o lokálnom ukladaní | Nastaviť predvolené triedenie a preferencie polohy |
| Výlet (budúca funkcia) | Zoznam a zhrnutie vybraných fotografií výletu | Vytvoriť výlet, prezerať snímky, otvoriť mapu |
| Mapa výletu (budúca funkcia) | Zobrazenie miest fotografií | Vybrať bod a otvoriť súvisiace fotografie |

## 5. Kľúčové používateľské toky

### 5.1 Vytvorenie a uloženie fotografie

1. Používateľ otvorí kameru z knižnice alebo z konkrétneho priečinka.
2. Aplikácia zobrazí živý náhľad a používateľ vytvorí snímku.
3. Aplikácia zobrazí náhľad s možnosťou fotografiu zahodiť a odfotiť znova alebo pokračovať.
4. Pri pokračovaní sa otvorí krok zaradenia: cieľový priečinok, značky a voliteľné mesto.
5. Ak sa fotografovalo z priečinka, tento priečinok je predvoleným cieľom.
6. Poloha sa načíta iba po udelení súhlasu; pri odmietnutí sa tok ukladania nezastaví.
7. Používateľ potvrdí uloženie. Aplikácia uloží obrazový súbor a jeho metadáta.
8. Po úspešnom uložení sa používateľ vráti do knižnice alebo priečinka, z ktorého fotografovanie začal.

### 5.2 Vyhľadanie fotografií podľa značiek

1. Používateľ otvorí vyhľadávanie.
2. Vyberie jednu alebo viac značiek.
3. Pri viacerých značkách zvolí AND alebo OR.
4. Aplikácia zobrazí zodpovedajúce fotografie v mriežke.
5. Používateľ môže otvoriť detail fotografie alebo upraviť výber značiek.

### 5.3 Odstránenie priečinka

1. Používateľ otvorí ponuku priečinka a zvolí odstránenie.
2. Potvrdzovacie okno jasne uvedie, že sa odstráni priečinok, vnorené priečinky a fotografie.
3. Po potvrdení aplikácia odstráni súbory aj súvisiace záznamy.
4. Pri chybe aplikácia zobrazí neúspešný stav a nesmie oznámiť úspech, ak odstránenie nebolo dokončené.

### 5.4 Export jednej alebo viacerých fotografií

1. Používateľ vyberie jednu alebo viac fotografií.
2. Zvolí uloženie do umiestnenia cez systém Android alebo zdieľanie s inou aplikáciou.
3. Aplikácia poskytne bezpečný prístup ku kópiám fotografií.
4. Zrušenie výberu alebo exportu ponechá originály nezmenené.

## 6. Návrh používateľského rozhrania

### Knižnica

- Fotografie sú hlavnou časťou obrazovky a zobrazujú sa v mriežke s náhľadmi.
- Horná časť obrazovky obsahuje názov aktuálneho umiestnenia alebo priečinka a vstup do vyhľadávania.
- Triedenie je dostupné cez ľahko rozpoznateľnú akciu; predvolené poradie je od najnovších fotografií.
- Fotografovanie je primárna, vizuálne výrazná akcia.
- Pri prázdnej knižnici sa zobrazí vysvetlenie a výzva na vytvorenie prvej fotografie.

### Priečinky

- Hierarchia sa zobrazuje ako navigovateľný zoznam alebo mriežka priečinkov.
- Vnorená úroveň zobrazuje cestu k aktuálnemu priečinku a umožňuje návrat o úroveň vyššie.
- Menu priečinka obsahuje premenovanie, vytvorenie podpriečinka a odstránenie.
- Akcia fotografovania v priečinku zachová aktuálny priečinok ako predvolený cieľ uloženia.

### Náhľad fotografie

- Fotografia zaberá väčšinu dostupnej plochy.
- Akcie „Znova odfotiť“ a „Pokračovať“ sú jasne odlíšené.
- Výber priečinka a značiek je dostupný pred potvrdením uloženia.
- Poloha sa prezentuje ako voliteľná funkcia s vysvetlením, aké údaje sa použijú.
- Potvrdenie uloženia je jednoznačné; používateľ nesmie omylom uložiť neoverený záber.

### Detail fotografie

- Fotografia je dominantná a možno ju otvoriť na celú obrazovku.
- Pod fotografiou sa zobrazia dostupné informácie: dátum, priečinok, značky a mesto.
- Ak údaj chýba, skryje sa alebo sa označí ako nedostupný; nesmie sa nahradiť vymyslenou hodnotou.
- Akcie na úpravu, export a odstránenie sú oddelené; odstránenie vyžaduje potvrdenie.

### Vizuálny štýl a prístupnosť

- Použiť Material 3 a systémové komponenty Jetpack Compose.
- Udržiavať jednotnú typografiu, rozstupy a tvary prvkov naprieč obrazovkami.
- Zachovať kontrast textu a ovládacích prvkov v svetlom aj tmavom režime.
- Dotykové ciele majú byť pohodlné na ovládanie jednou rukou.
- Ikony majú mať prístupné textové popisy; samotná farba nesmie byť jediným nositeľom významu.
- UI má reagovať na zmenu veľkosti textu a orientáciu zariadenia bez straty hlavných akcií.
- Slovenčina je navrhovaný jazyk MVP; texty sa majú spravovať tak, aby ich bolo možné lokalizovať.

## 7. Dátový model

### Photo

- `id` — stabilný identifikátor fotografie.
- `fileName` — názov súboru uloženého v internom úložisku.
- `folderId` — priečinok, do ktorého fotografia patrí.
- `createdAt` — dátum vytvorenia fotografie použitý na triedenie.
- `savedAt` — čas uloženia do TriPin.
- `sizeBytes`, `width`, `height`, `mimeType` — základné informácie o súbore.
- `city` — voliteľné mesto získané so súhlasom používateľa.
- `latitude`, `longitude` — voliteľné súradnice iba v prípade, že sa rozhodne ukladať presnú polohu; táto potreba je otvorenou otázkou pre mapové funkcie.

### Folder

- `id` — identifikátor priečinka.
- `name` — zobrazovaný názov.
- `parentId` — identifikátor nadradeného priečinka alebo prázdna hodnota pre koreň.
- `createdAt` — čas vytvorenia.

### Tag

- `id` — identifikátor značky.
- `name` — zobrazovaný názov značky.
- `normalizedName` — normalizovaný názov na vyhľadávanie a kontrolu duplicít.
- `color` — voliteľná farba značky.
- `createdAt` — čas vytvorenia.

### PhotoTag

Prepojenie fotografie a značky. Jedna fotografia môže mať viac značiek a jedna značka môže byť priradená viacerým fotografiám.

### Budúce entity

- **Trip** — názov a metadáta výletu.
- **TripPhoto** — prepojenie výletu s fotografiami.
- Poloha pre mapu bude vyžadovať rozhodnutie, či sa uchovávajú presné súradnice, mesto alebo iná forma približného miesta.

## 8. Technická architektúra

Navrhované vrstvy:

1. **UI (Compose)** — obrazovky, komponenty a zobrazenie stavov.
2. **ViewModel** — správa stavu obrazovky a koordinácia používateľských akcií.
3. **Domain / aplikačná logika** — pravidlá fotografovania, organizácie, vyhľadávania, exportu a mazania.
4. **Repository** — jednotné rozhranie k databáze, súborom, kamere a polohe.
5. **Data sources**:
   - Room pre fotografie, priečinky, značky a ich väzby;
   - interné úložisko pre obrazové súbory;
   - CameraX pre získanie fotografie;
   - Android Location APIs pre voliteľné získanie polohy;
   - Storage Access Framework a Android Sharesheet pre export;
   - DataStore pre nastavenia.

### Ukladanie a konzistentnosť dát

- Súbor fotografie a záznam v databáze musia zostať vzájomne konzistentné.
- Pri ukladaní sa má najprv bezpečne vytvoriť súbor a následne uložiť metadáta; pri zlyhaní sa používateľovi zobrazí chyba a dočasné súbory sa upracú.
- Pri odstránení fotografie sa odstráni súbor aj databázové väzby.
- Pri odstránení priečinka sa odstráni jeho obsah vrátane vnorených priečinkov; aplikácia má používateľa vopred upozorniť na rozsah operácie.
- Dlhšie operácie, napríklad načítanie náhľadov alebo export viacerých súborov, nesmú blokovať hlavné vlákno.

## 9. Stavové a chybové situácie

Každá obrazovka pracujúca s dátami má definovať:

- **Načítavanie** — zobraziť primeranú indikáciu bez blokovania celej aplikácie.
- **Prázdny stav** — vysvetliť, čo sa zatiaľ nezobrazuje, a ponúknuť relevantnú akciu.
- **Chyba** — zobraziť zrozumiteľnú správu a možnosť opakovať operáciu, ak je to bezpečné.
- **Nedostupné oprávnenie** — vysvetliť, čo nebude fungovať, ale zachovať dostupné funkcie.
- **Nedostupný súbor** — nezlyhať potichu; označiť chýbajúci obrázok a ponúknuť primerané riešenie.
- **Potvrdenie deštruktívnej akcie** — pred odstránením fotografie alebo priečinka vždy vyžiadať potvrdenie.

## 10. Nefunkčné požiadavky

- Základná knižnica, priečinky, značky a vyhľadávanie musia fungovať bez internetového pripojenia.
- Aplikácia nesmie pri odmietnutí voliteľného oprávnenia spadnúť ani znemožniť uloženie fotografie.
- Databázové a súborové operácie musia prebiehať asynchrónne.
- Chyba pri zápise, exporte alebo odstránení musí byť používateľovi oznámená; aplikácia nesmie zobrazovať úspech, ak operácia zlyhala.
- Súbory uložené v internom úložisku nemajú byť sprístupnené iným aplikáciám bez výslovnej akcie exportu alebo zdieľania.
- Rozhranie musí zostať použiteľné na podporovaných veľkostiach telefónov a pri systémovej zmene veľkosti textu.

## 11. Testovacie scenáre návrhu

1. Fotografovať z koreňovej knižnice, potvrdiť snímku, zvoliť priečinok a značky a overiť jej zobrazenie v galérii.
2. Fotografovať z vnoreného priečinka a overiť, že tento priečinok je predvoleným cieľom.
3. Odmietnuť oprávnenie na polohu a overiť, že fotografiu je stále možné uložiť.
4. Priradiť fotografii viacero značiek a overiť výsledky vyhľadávania AND aj OR.
5. Presunúť fotografiu medzi priečinkami a overiť zachovanie značiek a metadát.
6. Odstrániť priečinok s vnoreným obsahom a overiť potvrdenie aj odstránenie súborov a záznamov.
7. Exportovať viac fotografií a overiť, že originály v TriPin zostali nezmenené.
8. Otvoriť fotografiu s chýbajúcim miestom alebo inými metadátami a overiť, že UI nezobrazuje zavádzajúce údaje.

## 12. Stav existujúceho projektu

Aktuálny Android projekt už obsahuje základ aplikácie na Kotlin/Compose s navigáciou medzi domovskou obrazovkou, kamerou, správou značiek a detailom fotografie, ako aj základné dátové modely a lokálnu databázovú vrstvu. Súčasný zdrojový kód je však východiskový scaffold a nepokrýva celý rozsah špecifikácie. Najmä úplný tok náhľadu a uloženia, správa hierarchie priečinkov, priraďovanie a vyhľadávanie značiek, export, geolokácia a úplné stavy používateľského rozhrania vyžadujú ďalšiu implementáciu.


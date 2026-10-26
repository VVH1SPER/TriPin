# TriPin — Špecifikácia požiadaviek

## 1. Problém

Používatelia, ktorí často fotografujú, časom vytvoria veľkú zbierku snímok, v ktorej sa konkrétne fotografie ťažko hľadajú. Bežná galéria zvyčajne ponúka iba základné triedenie podľa času a neumožňuje pohodlne vytvárať vlastnú hierarchiu priečinkov, nezávislé značky ani vyhľadávať pomocou viacerých značiek.

TriPin rieši tento problém tým, že používateľovi umožní fotografiu vytvoriť a hneď ju zaradiť do priečinka, označiť značkami a prípadne uložiť mesto, v ktorom vznikla. Fotografie bude možné neskôr prehliadať, triediť a vyhľadávať. V budúcnosti bude možné vytvárať z fotografií zhrnutia výletov a zobrazovať ich miesta na mape.

## 2. Používatelia a roly

### Používateľ aplikácie

TriPin je osobná aplikácia bez používateľských účtov. V prvej verzii existuje iba jedna rola:

- **Používateľ** — vytvára a spravuje vlastnú lokálnu knižnicu fotografií; môže fotografovať, organizovať snímky v priečinkoch, priraďovať im značky, vyhľadávať, prezerať, exportovať a odstraňovať fotografie a priečinky.

Všetky údaje patria do lokálnej inštalácie aplikácie. Aplikácia neposkytuje administrátorskú rolu ani zdieľanie knižnice.

## 3. Funkčné požiadavky

Číslovanie je zoskupené podľa oblastí. Funkcie označené ako budúce nie sú súčasťou MVP.

### 3.1 Fotografovanie a ukladanie

**3.1.1.** Používateľ môže vytvoriť fotografiu priamo v aplikácii TriPin.

**3.1.2.** Po odfotení môže používateľ zobraziť náhľad fotografie a rozhodnúť sa, či ju potvrdí alebo odfotí znova.

**3.1.3.** Aplikácia uloží fotografiu až po jej potvrdení používateľom.

**3.1.4.** Používateľ môže pri ukladaní vybrať existujúci priečinok, do ktorého sa fotografia zaradí.

**3.1.5.** Ak používateľ začne fotografovať z konkrétneho priečinka, aplikácia tento priečinok predvolene vyberie ako cieľ uloženia.

**3.1.6.** Používateľ môže k fotografii priradiť jednu alebo viac existujúcich značiek.

**3.1.7.** Používateľ môže počas ukladania fotografie vytvoriť novú značku a priradiť ju k snímke.

**3.1.8.** Používateľ môže uložiť fotografiu bez značky.

**3.1.9.** Aplikácia môže po udelení oprávnenia používateľom zistiť mesto, v ktorom fotografia vznikla, a uložiť ho k snímke.

**3.1.10.** Ak používateľ odmietne prístup k polohe alebo sa mesto nepodarí zistiť, aplikácia napriek tomu umožní fotografiu uložiť.

### 3.2 Priečinky

**3.2.1.** Používateľ môže vytvoriť priečinok v koreňovej úrovni knižnice.

**3.2.2.** Používateľ môže vytvoriť vnorený priečinok v existujúcom priečinku.

**3.2.3.** Používateľ môže priečinok premenovať.

**3.2.4.** Používateľ môže presunúť fotografiu do iného priečinka.

**3.2.5.** Používateľ môže odstrániť priečinok; odstránením priečinka sa odstránia aj jeho fotografie a vnorené priečinky.

**3.2.6.** Pred odstránením priečinka aplikácia zobrazí potvrdenie, ktoré upozorní na odstránenie jeho obsahu.

### 3.3 Značky a vyhľadávanie

**3.3.1.** Používateľ môže vytvoriť značku.

**3.3.2.** Používateľ môže premenovať značku.

**3.3.3.** Používateľ môže pridať alebo odobrať jednu či viac značiek na už uloženej fotografii.

**3.3.4.** Používateľ môže vyhľadávať fotografie podľa jednej alebo viacerých značiek.

**3.3.5.** Používateľ môže pri vyhľadávaní viacerých značiek vybrať režim **AND**, v ktorom výsledok obsahuje všetky vybrané značky.

**3.3.6.** Používateľ môže pri vyhľadávaní viacerých značiek vybrať režim **OR**, v ktorom výsledok obsahuje aspoň jednu z vybraných značiek.

**3.3.7.** Značky fungujú nezávisle od priečinkov; presunutie fotografie do iného priečinka nemení jej značky.

### 3.4 Galéria a správa fotografií

**3.4.1.** Používateľ môže prezerať uložené fotografie v galérii aplikácie.

**3.4.2.** Používateľ môže otvoriť fotografiu na celú obrazovku a približovať alebo odďaľovať jej zobrazenie.

**3.4.3.** Používateľ môže triediť fotografie podľa dátumu od najnovších po najstaršie alebo opačne.

**3.4.4.** Používateľ môže zobraziť dostupné informácie o fotografii, napríklad dátum, priečinok, značky a uložené mesto.

**3.4.5.** Používateľ môže odstrániť fotografiu po potvrdení tejto akcie.

**3.4.6.** Odstránenie fotografie z TriPin odstráni jej súbor z interného úložiska aplikácie a súvisiace lokálne záznamy.

### 3.5 Export

**3.5.1.** Používateľ môže vybrať a exportovať jednu alebo viac fotografií.

**3.5.2.** Používateľ môže uložiť exportované kópie do umiestnenia, ktoré vyberie prostredníctvom systému Android, alebo ich odovzdať inej aplikácii.

**3.5.3.** Export nemení ani neodstraňuje originálne fotografie uložené v TriPin.

### 3.6 Budúce funkcie — výlety a miesta

Nasledujúce požiadavky predstavujú plánované rozšírenia a nie sú súčasťou MVP.

**3.6.1.** Používateľ môže vytvoriť výlet a priradiť k nemu vybrané fotografie.

**3.6.2.** Používateľ môže zobraziť zhrnutie výletu s dostupnými fotografiami, dátumami a miestami.

**3.6.3.** Používateľ môže zobraziť dostupné miesta fotografií výletu na mape.

**3.6.4.** Po výbere bodu na mape môže používateľ zobraziť fotografie, ktoré sú s týmto miestom spojené.

**3.6.5.** Aplikácia môže navrhnúť značku podľa dostupného mesta alebo blízkeho bodu záujmu; používateľ musí návrh potvrdiť.

## 4. Obmedzenia

### 4.1 Technické obmedzenia

- Aplikácia je natívna mobilná aplikácia pre platformu Android.
- Projekt používa Kotlin a Jetpack Compose.
- Navigácia je založená na Navigation Compose.
- Architektúra aplikácie má oddeľovať používateľské rozhranie, stav obrazoviek, aplikačnú logiku a prístup k dátam (MVVM a repository pattern).
- Lokálne štruktúrované údaje sa ukladajú pomocou Room/SQLite.
- Originálne fotografie sa ukladajú do interného úložiska aplikácie.
- CameraX sa používa na prístup ku kamere.
- Coil sa používa na načítanie a zobrazovanie fotografií.
- DataStore je určený na používateľské nastavenia.
- Podľa aktuálnej konfigurácie projektu je minimálna podporovaná verzia Androidu API 29 (Android 10), cieľová verzia API 34 a Java/Kotlin bytecode target 17. Verzie SDK sa môžu upraviť pri aktualizácii projektu.

### 4.2 Ochrana súkromia a správanie

- V MVP sa fotografie a ich metadáta spravujú lokálne; cloudová synchronizácia a serverová závislosť nie sú požadované.
- Aplikácia nevyžaduje registráciu ani prihlásenie.
- Prístup k polohe je voliteľný a vyžaduje súhlas používateľa. Odmietnutie nesmie blokovať fotografovanie ani ukladanie.
- Aplikácia nesleduje polohu na pozadí.
- Značky, priečinky a informácie o fotografiách sa pri presune zachovávajú, pokiaľ požiadavka výslovne neurčuje ich odstránenie.
- Zmazanie priečinka je deštruktívna operácia a musí vyžadovať potvrdenie.

### 4.3 Platforma a jazyk

- Cieľovou platformou je Android; aplikácia je navrhnutá primárne pre telefóny.
- Jazyk dokumentácie je slovenčina. Slovenčina je navrhovaný predvolený jazyk používateľského rozhrania; lokalizácia do ďalších jazykov nie je podmienkou MVP.
- Aplikácia musí rešpektovať systémové oprávnenia Androidu pre kameru, súbory a voliteľnú polohu.

## 5. Mimo rozsahu MVP

- Používateľské účty, registrácia a prihlasovanie.
- Cloudové zálohovanie alebo synchronizácia medzi zariadeniami.
- Zdieľaná knižnica, spoločné priečinky a spolupráca viacerých používateľov.
- Automatická AI analýza obsahu fotografie a rozpoznávanie objektov.
- Nepretržité sledovanie GPS alebo zaznamenávanie trasy na pozadí.
- Automaticky vytvorená interaktívna mapa výletu; mapa je budúcou funkciou.
- Automatické návrhy značiek podľa miest a bodov záujmu; návrhy sú budúcou funkciou.
- Pokročilá úprava fotografií, napríklad filtre, retuš alebo úprava farieb.
- Export celého priečinka ako samostatná operácia; MVP zahŕňa export jednej alebo viacerých vybraných fotografií.

## 6. Otvorené otázky

1. Má aplikácia ukladať iba názov mesta, alebo aj presné súradnice? Presné súradnice by boli potrebné na presné body fotografií na budúcej mape; mesto postačuje pre MVP.
2. Ktorá služba sa použije na prevod polohy na mesto a neskôr na zobrazenie mapy a bodov záujmu?
3. Má byť rozhranie v MVP výhradne v slovenčine alebo má byť od začiatku pripravené na ďalšie jazyky?
4. Aké formáty a maximálny počet fotografií má podporovať hromadný export?
5. Má sa pri odstránení značky odstrániť iba značka, alebo sa má používateľovi ponúknuť aj hromadné odstránenie tejto značky zo všetkých fotografií? Navrhované správanie: značka sa odstráni a jej väzby sa zrušia, fotografie zostanú zachované.
6. Má používateľ môcť vytvoriť prázdny výlet a dopĺňať fotografie neskôr, alebo sa výlet vytvára vždy výberom existujúcich fotografií?
7. Ako sa má určiť hranica medzi miestami na mape, ktoré sa majú považovať za rovnaké miesto?
8. Aká je požadovaná hranica veľkosti alebo počtu fotografií v knižnici a aký výkon vyhľadávania sa očakáva pri veľkej zbierke?


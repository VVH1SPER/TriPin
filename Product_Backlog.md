# TriPin – Jira backlog

Štruktúra backlogu: **Initiative → Epics → User Stories → Tasks**. Initiative opisuje merateľný spoločný cieľ, Epics väčšie oblasti funkcionality, User Stories potreby používateľa a Tasks konkrétnu prácu potrebnú na ich dodanie.

## INITIATIVE — TRI-I1: Umožniť používateľom rýchlo organizovať a znovu nájsť svoje fotografie

**Opis:** Vytvoriť Android aplikáciu TriPin, v ktorej si používatelia môžu ukladať fotografie, organizovať ich pomocou priečinkov a značiek, vyhľadávať snímky a prezerať fotografie súvisiace s výletmi a miestami.

**Merateľný cieľ:** Do dokončenia MVP najmenej 4 z 5 účastníkov používateľského testovania (80 %) samostatne uložia novú fotografiu s priečinkom a značkou a následne ju nájdu vyhľadaním podľa značky do 60 sekúnd.

**Spôsob merania:** Moderovaný test s minimálne 5 používateľmi z cieľovej skupiny. Úspešnosť sa počíta ako podiel účastníkov, ktorí dokončia oba scenáre bez pomoci; čas sa meria od začatia úlohy po úspešné dokončenie.

**Hodnota:** Používateľ si fotografie priebežne usporiada a neskôr ich nájde bez zdĺhavého prehľadávania celej galérie.

**Cieľový výsledok:** Lokálna organizácia fotografií funguje bez používateľského účtu a cloudovej synchronizácie. Geolokácia sa používa iba so súhlasom používateľa; v prvej verzii stačí ukladať mesto. Funkcie výletov a mapy sa budú rozvíjať ako budúce možnosti.

**Poznámka:** Cieľ 80 % a limit 60 sekúnd sú navrhované východiskové hodnoty na overenie v MVP; po prvom používateľskom teste sa môžu upraviť podľa výsledkov.

---

# MVP

## EPIC TRI-E1: Fotografovanie a ukladanie fotografií

**Cieľ:** Používateľ môže odfotografovať objekt, skontrolovať snímku a uložiť ju spolu s priečinkom, značkami a prípadne údajmi o mieste.

### User Stories

#### TRI-1 — Odfotografovať a skontrolovať snímku

Ako používateľ chcem odfotografovať snímku a pred uložením si ju pozrieť, aby som sa mohol rozhodnúť, či mi vyhovuje.

**Kritériá akceptácie:**
- Po odfotení sa zobrazí náhľad fotografie.
- Používateľ môže fotografiu odfotiť znova.
- Nová snímka nahradí predchádzajúci náhľad.
- Fotografia sa uloží až po potvrdení používateľom.

**Tasks:**
- Integrovať CameraX a zobraziť živý náhľad kamery.
- Implementovať vytvorenie snímky a dočasné uloženie pred potvrdením.
- Vytvoriť obrazovku náhľadu s akciami „Znova odfotiť“ a „Potvrdiť“.

#### TRI-2 — Uložiť snímku do vybraného priečinka

Ako používateľ chcem pri ukladaní fotografie vybrať priečinok, aby som ju hneď zaradil na správne miesto.

**Kritériá akceptácie:**
- Pri ukladaní je možné vybrať existujúci priečinok.
- Ak sa fotografovanie začne v konkrétnom priečinku, fotografia sa doň predvolene uloží.
- Používateľ môže priečinok pred potvrdením zmeniť.

**Tasks:**
- Pripraviť výber cieľového priečinka v procese ukladania.
- Predvybrať priečinok, z ktorého sa spustilo fotografovanie.
- Uložiť fotografiu do interného úložiska a záznam do lokálnej databázy.

#### TRI-3 — Pridať značky pri ukladaní

Ako používateľ chcem pri ukladaní fotografie vybrať existujúce značky alebo vytvoriť nové, aby som ju mohol neskôr jednoducho vyhľadať.

**Kritériá akceptácie:**
- K fotografii je možné priradiť viacero značiek.
- Používateľ môže vybrať existujúce značky.
- Používateľ môže vytvoriť značku priamo počas ukladania.
- Fotografiu je možné uložiť aj bez značiek.

**Tasks:**
- Pridať do procesu ukladania výber existujúcich značiek.
- Umožniť vytvorenie novej značky počas ukladania.
- Uložiť väzby medzi fotografiou a vybranými značkami.

#### TRI-4 — Uložiť miesto fotografovania so súhlasom používateľa

Ako používateľ chcem povoliť ukladanie mesta, v ktorom bola fotografia vytvorená, aby som neskôr mohol snímky vyhľadávať podľa miesta.

**Kritériá akceptácie:**
- Aplikácia požiada používateľa o povolenie prístupu k polohe.
- Ak používateľ súhlasí a poloha je dostupná, mesto sa uloží k fotografii.
- Ak používateľ nesúhlasí alebo poloha nie je dostupná, fotografia sa napriek tomu uloží.
- Aplikácia nevyžaduje nepretržité sledovanie polohy na pozadí.

**Tasks:**
- Implementovať vyžiadanie oprávnenia na polohu pri použití funkcie.
- Získať aktuálnu polohu a previesť súradnice na názov mesta.
- Uložiť mesto k fotografii a ošetriť odmietnuté oprávnenie alebo nedostupnú polohu.

---

## EPIC TRI-E2: Priečinky a organizácia fotografií

**Cieľ:** Používateľ môže organizovať fotografie pomocou bežných aj vnorených priečinkov.

### User Stories

#### TRI-5 — Vytvoriť priečinok alebo vnorený priečinok

Ako používateľ chcem vytvárať priečinky a priečinky vnútri iných priečinkov, aby som si fotografie usporiadal podľa vlastných kategórií.

**Kritériá akceptácie:**
- Používateľ môže vytvoriť priečinok v koreňovej úrovni alebo v existujúcom priečinku.
- Nový priečinok sa zobrazí na správnom mieste v štruktúre.
- Prázdny názov priečinka nie je povolený.

**Tasks:**
- Doplniť dátový model priečinka s odkazom na nadradený priečinok.
- Implementovať vytvorenie koreňového a vnoreného priečinka.
- Vytvoriť používateľské rozhranie na vytvorenie a zobrazenie priečinkov.

#### TRI-6 — Premenovať priečinok

Ako používateľ chcem premenovať priečinok, aby som si udržal poriadok vo svojej knižnici fotografií.

**Kritériá akceptácie:**
- Používateľ môže zmeniť názov existujúceho priečinka.
- Po premenovaní zostane priečinok aj jeho obsah dostupný.

**Tasks:**
- Implementovať aktualizáciu názvu priečinka v databáze.
- Pridať akciu a formulár na premenovanie priečinka.
- Zabezpečiť zobrazenie aktualizovaného názvu bez zmeny hierarchie a obsahu.

#### TRI-7 — Odstrániť priečinok spolu s fotografiami

Ako používateľ chcem odstrániť priečinok aj fotografie, ktoré obsahuje, aby som odstránil nepotrebné dáta.

**Kritériá akceptácie:**
- Pred odstránením priečinka sa zobrazí potvrdenie.
- Odstránením priečinka sa odstránia fotografie, ktoré obsahuje.
- Odstránením nadradeného priečinka sa odstránia aj vnorené priečinky a ich fotografie.

**Tasks:**
- Pridať potvrdenie pred odstránením priečinka a jeho obsahu.
- Implementovať rekurzívne odstránenie vnorených priečinkov a databázových záznamov fotografií.
- Odstrániť súbory fotografií z interného úložiska a ošetriť zlyhanie odstránenia.

#### TRI-8 — Presunúť fotografiu do iného priečinka

Ako používateľ chcem presúvať fotografie medzi priečinkami, aby som mohol meniť ich usporiadanie bez opätovného fotografovania.

**Kritériá akceptácie:**
- Používateľ môže vybrať fotografiu a cieľový priečinok.
- Po presunutí sa fotografia zobrazuje v novom priečinku.
- Značky a uložené údaje fotografie sa zachovajú.

**Tasks:**
- Pridať výber cieľového priečinka pre existujúcu fotografiu.
- Aktualizovať priradenie fotografie k priečinku v databáze.
- Overiť zachovanie súboru, značiek a metadát pri presune.

---

## EPIC TRI-E3: Značky a vyhľadávanie

**Cieľ:** Používateľ môže priraďovať fotografiám značky nezávislé od priečinkov a vyhľadávať snímky podľa značiek.

### User Stories

#### TRI-9 — Spravovať značky

Ako používateľ chcem vytvárať a premenovávať značky, aby som mohol fotografie klasifikovať podľa vlastných tém.

**Kritériá akceptácie:**
- Používateľ môže vytvoriť značku a zmeniť jej názov.
- Zmena názvu značky neodstráni fotografie, ktoré sú s ňou spojené.

**Tasks:**
- Doplniť operácie na vytvorenie a premenovanie značiek v dátovej vrstve.
- Vytvoriť obrazovku alebo dialóg na správu značiek.
- Zabezpečiť zachovanie existujúcich väzieb fotografií pri premenovaní.

#### TRI-10 — Upraviť značky fotografie

Ako používateľ chcem pridávať a odoberať značky už uloženej fotografii, aby som mohol jej označenie priebežne aktualizovať.

**Kritériá akceptácie:**
- K fotografii je možné pridať viacero značiek.
- Značku je možné odobrať z konkrétnej fotografie.
- Úprava značiek nezmení priečinok fotografie.

**Tasks:**
- Implementovať pridávanie a odoberanie väzieb medzi fotografiou a značkou.
- Pridať používateľské rozhranie na úpravu značiek fotografie.
- Po úprave obnoviť zobrazené značky bez zmeny ostatných údajov.

#### TRI-11 — Vyhľadávať podľa značiek v režime AND alebo OR

Ako používateľ chcem vyhľadávať fotografie podľa viacerých značiek a zvoliť logiku zhody, aby som presnejšie našiel požadované snímky.

**Kritériá akceptácie:**
- Používateľ môže na vyhľadávanie vybrať jednu alebo viac značiek.
- V režime **AND** sa zobrazia fotografie, ktoré majú všetky vybrané značky.
- V režime **OR** sa zobrazia fotografie, ktoré majú aspoň jednu z vybraných značiek.
- Používateľ môže prepínať medzi režimami vyhľadávania.

**Tasks:**
- Implementovať databázové vyhľadávanie fotografií podľa viacerých značiek v režimoch AND a OR.
- Pridať výber značiek a prepínač režimu do obrazovky vyhľadávania.
- Zobraziť výsledky a prázdny stav, ak sa nenašli žiadne fotografie.

---

## EPIC TRI-E4: Galéria a prezeranie fotografií

**Cieľ:** Používateľ môže fotografie v aplikácii prezerať, triediť a odstraňovať.

### User Stories

#### TRI-12 — Prezerať fotografie v galérii

Ako používateľ chcem vidieť uložené fotografie v galérii, aby som si mohol prezerať svoju knižnicu.

**Kritériá akceptácie:**
- Galéria zobrazuje uložené fotografie.
- Používateľ môže otvoriť fotografiu na celú obrazovku.
- Používateľ môže fotografiu približovať a odďaľovať.

**Tasks:**
- Načítať fotografie z úložiska a zobraziť ich v mriežke galérie.
- Vytvoriť zobrazenie fotografie na celú obrazovku.
- Doplniť ovládanie priblíženia a oddialenia.

#### TRI-13 — Triediť fotografie podľa dátumu

Ako používateľ chcem triediť fotografie podľa dátumu, aby som ľahšie našiel novšie alebo staršie snímky.

**Kritériá akceptácie:**
- Používateľ si môže zvoliť triedenie podľa dátumu.
- K dispozícii je triedenie od najnovších po najstaršie aj opačne.

**Tasks:**
- Ukladať dátum vytvorenia fotografie ako triediteľný údaj.
- Implementovať triedenie v oboch smeroch.
- Pridať ovládanie výberu smeru triedenia v galérii.

#### TRI-14 — Zobraziť informácie o fotografii

Ako používateľ chcem vidieť základné informácie o fotografii, aby som vedel, kedy a kde vznikla a ako je usporiadaná.

**Kritériá akceptácie:**
- Pri fotografii sa zobrazia dostupné základné údaje, napríklad dátum, priečinok a značky.
- Mesto sa zobrazí, ak bolo uložené.
- Nedostupné údaje sa nezobrazia ako známe alebo potvrdené.

**Tasks:**
- Pripraviť načítanie dátumu, priečinka, značiek a dostupného mesta fotografie.
- Vytvoriť panel alebo obrazovku s detailmi fotografie.
- Ošetriť chýbajúce metadáta tak, aby sa nezobrazovali zavádzajúce hodnoty.

#### TRI-15 — Odstrániť fotografiu

Ako používateľ chcem odstrániť nepotrebné fotografie, aby som si udržal poriadok a uvoľnil miesto.

**Kritériá akceptácie:**
- Pred odstránením fotografie sa zobrazí potvrdenie.
- Po potvrdení sa fotografia odstráni z aplikácie a prestane sa zobrazovať v galérii.

**Tasks:**
- Pridať potvrdzovací dialóg na odstránenie fotografie.
- Implementovať odstránenie súboru a súvisiacich databázových záznamov.
- Aktualizovať galériu po úspešnom odstránení a zobraziť chybu pri neúspechu.

---

## EPIC TRI-E5: Export fotografií

**Cieľ:** Používateľ môže exportovať kópie vybraných fotografií mimo aplikácie TriPin.

### User Stories

#### TRI-16 — Exportovať jednu alebo viac fotografií

Ako používateľ chcem vybrať a exportovať jednu alebo viac fotografií, aby som si ich mohol uložiť inde alebo odovzdať inej aplikácii.

**Kritériá akceptácie:**
- Používateľ môže exportovať jednu fotografiu alebo viac vybraných fotografií.
- Pri exporte si používateľ vyberie dostupné miesto uloženia alebo cieľovú aplikáciu.
- Export neodstráni ani nepresunie originály z aplikácie TriPin.

**Tasks:**
- Umožniť výber jednej alebo viacerých fotografií na export.
- Implementovať kopírovanie fotografií cez Android Storage Access Framework.
- Implementovať zdieľanie vybraných fotografií cez Android Sharesheet.
- Ošetriť zrušenie exportu a chyby pri kopírovaní bez zásahu do originálov.

---

# Budúce možnosti

## EPIC TRI-E6: Zhrnutie výletu a mapa fotografií

**Cieľ:** Používateľ môže spojiť fotografie z výletu a zobraziť ich miesta na mape.

### User Stories

#### TRI-17 — Vytvoriť výlet z fotografií

Ako používateľ chcem spojiť fotografie do výletu, aby som si mohol súvisiace snímky prezerať spoločne.

**Kritériá akceptácie:**
- Používateľ môže vybrať fotografie, ktoré chce zaradiť do výletu.
- Fotografie výletu sú dostupné v samostatnom zobrazení.
- Fotografie bez uloženého miesta je možné zahrnúť do výletu, ale nezobrazia sa ako bod na mape.

**Tasks:**
- Navrhnúť dátový model výletu a priradenia fotografií k výletu.
- Implementovať vytvorenie výletu a výber fotografií.
- Vytvoriť zoznam a detail výletu.

#### TRI-18 — Zobraziť zhrnutie výletu

Ako používateľ chcem vidieť zhrnutie výletu, aby som rýchlo získal prehľad o súvisiacich fotografiách a miestach.

**Kritériá akceptácie:**
- Zhrnutie zobrazuje fotografie zaradené do výletu.
- Zhrnutie zobrazuje dostupné informácie o miestach a dátumoch.
- Údaje, ktoré nie sú dostupné z fotografií, sa nezobrazujú ako známe.

**Tasks:**
- Agregovať dostupné fotografie, dátumy a mestá v rámci výletu.
- Vytvoriť obrazovku zhrnutia výletu.
- Ošetriť chýbajúce dátumy a miesta bez zobrazovania vymyslených údajov.

#### TRI-19 — Zobraziť miesta fotografií na mape

Ako používateľ chcem vidieť na mape miesta, kde vznikli fotografie z výletu, aby som si pripomenul trasu a snímky.

**Kritériá akceptácie:**
- Na mape sa zobrazia dostupné body fotografií z výletu.
- Po výbere bodu si používateľ môže zobraziť súvisiace fotografie.
- Mapa používa iba uložené údaje o miestach; nevyžaduje nepretržité sledovanie GPS.

**Tasks:**
- Vybrať a integrovať mapovú službu pre Android.
- Zobraziť na mape body z uložených miest fotografií.
- Po výbere bodu zobraziť súvisiace fotografie.

---

## EPIC TRI-E7: Návrhy značiek podľa miesta

**Cieľ:** Zjednodušiť označovanie fotografií návrhmi, ktoré vychádzajú z uloženého mesta alebo miesta.

### User Stories

#### TRI-20 — Získať návrh značky podľa miesta

Ako používateľ chcem dostávať návrhy značiek na základe miesta fotografovania, aby som mohol fotografie rýchlejšie organizovať.

**Kritériá akceptácie:**
- Návrhy sa zobrazia iba vtedy, keď sú dostupné údaje o mieste.
- Používateľ sám rozhodne, či navrhnutú značku pridá.
- Návrhy nenahrádzajú ani neodstraňujú existujúce značky.

**Tasks:**
- Zistiť dostupné miesta v okolí na základe polohy fotografie.
- Vytvoriť návrhy značiek odvodené od dostupných názvov miest alebo bodov záujmu.
- Zobraziť návrhy ako voliteľné a umožniť ich pridať bez zmeny existujúcich značiek.

---

## Predpoklady a otvorené rozhodnutia

- Export je súčasťou MVP a vytvára kópie vybraných fotografií; originály zostávajú v aplikácii TriPin.
- Pri odstránení priečinka sa odstránia aj fotografie a vnorené priečinky, ktoré obsahuje.
- Vyhľadávanie podľa viacerých značiek podporuje režimy AND aj OR, medzi ktorými si používateľ vyberá.
- Geolokácia je voliteľná. Ak používateľ udelí súhlas a miesto je dostupné, k fotografii sa uloží mesto.
- Cloudová synchronizácia, používateľské účty, automatické rozpoznávanie obsahu fotografií a sledovanie GPS na pozadí nie sú súčasťou aktuálneho rozsahu.
- Priority a odhady nie sú doplnené, pretože nebola určená tímová metodika ich stanovenia.

# TriPin — štruktúra kódu

Dokument opisuje zdrojový kód Android aplikácie TriPin. Build výstupy, IDE
cache a lokálne nastavenia (`build/`, `.gradle/`, `.idea/`, `.kotlin/`,
`local.properties`) nie sú súčasťou zdrojovej štruktúry.

## Koreň projektu

```text
Android/
├── App/                                  # Android Studio/Gradle projekt
│   ├── app/                              # Android aplikácia (modul :app)
│   ├── gradle/                           # Gradle wrapper konfigurácia
│   ├── build.gradle.kts                  # Pluginy a ich verzie
│   ├── gradle.properties                 # Nastavenia Gradle
│   ├── gradlew, gradlew.bat              # Gradle wrapper pre Unix/Windows
│   ├── settings.gradle.kts               # Názov projektu a zahrnuté moduly
│   ├── README.md                         # Pokyny k Android aplikácii
│   └── Photo_Organizer_Technical_Specification.txt
├── gradle/                               # Wrapper konfigurácia v koreňovom projekte
├── build.gradle.kts                      # Koreňová Gradle konfigurácia
├── gradle.properties                     # Koreňové nastavenia Gradle
├── gradlew, gradlew.bat                  # Koreňový Gradle wrapper
├── settings.gradle.kts                   # Koreňový Gradle projekt
├── logo.png                              # Originálny zdroj loga
├── CODE_STRUCTURE.md                     # Tento dokument
├── README.md                             # Stručný prehľad projektu
├── project.md                            # Produktový prehľad
└── project_prompt.md                     # Pôvodné zadanie projektu
```

## Android modul `App/app`

```text
app/
├── build.gradle.kts                      # Android konfigurácia a závislosti
└── src/main/
    ├── AndroidManifest.xml               # Aplikácia, launcher activity,
    │                                     # oprávnenia a FileProvider
    ├── java/com/photoorganizer/
    │   ├── MainActivity.kt               # Android vstupný bod; Compose content
    │   ├── model/
    │   │   └── PhotoModels.kt            # Room entity a modely fotografií,
    │   │                                 # priečinkov a tagov
    │   ├── data/
    │   │   ├── database/
    │   │   │   ├── PhotoOrganizerDatabase.kt # Room databáza a singleton
    │   │   │   ├── FolderDao.kt          # Dopyty pre priečinky
    │   │   │   ├── PhotoDao.kt           # Dopyty pre fotografie a tag väzby
    │   │   │   └── TagDao.kt             # Dopyty pre tagy
    │   │   └── repository/
    │   │       └── PhotoRepository.kt    # Prístup k Room a súborom fotografií
    │   └── ui/
    │       ├── navigation/
    │       │   └── AppNavHost.kt         # Navigácia medzi obrazovkami
    │       ├── screens/
    │       │   ├── home/HomeScreen.kt    # Domov, priečinky a galéria
    │       │   ├── camera/CameraScreen.kt # Fotografovanie cez CameraX
    │       │   ├── photo/PhotoDetailScreen.kt # Detail a akcie fotografie
    │       │   └── tags/TagsScreen.kt    # Zoznam a správa tagov
    │       ├── theme/
    │       │   ├── Theme.kt              # Compose Material theme
    │       │   └── Typography.kt         # Typografia
    │       └── viewmodel/
    │           └── PhotoViewModel.kt     # Stav UI a operácie cez repository
    └── res/
        ├── drawable/
        │   ├── ic_launcher_background.xml # Pôvodné pozadie adaptívnej ikony
        │   └── ic_launcher_foreground.xml # Pôvodné vektorové popredie ikony
        ├── drawable-nodpi/
        │   └── tripin_logo.png            # Logo na obrazovke a ikona aplikácie
        ├── mipmap-anydpi-v26/
        │   ├── ic_launcher.xml             # Pôvodná adaptívna ikona
        │   └── ic_launcher_round.xml       # Pôvodná okrúhla adaptívna ikona
        ├── values/
        │   ├── strings.xml                 # Názov aplikácie „TriPin“
        │   └── themes.xml                  # Android window/theme nastavenia
        └── xml/
            └── file_paths.xml              # Zdieľanie súborov cez FileProvider
```

## Zodpovednosti a tok dát

- **UI:** `MainActivity` spúšťa tému a `AppNavHost` prepína medzi domovskou,
  kamerovou, detailovou a tag obrazovkou.
- **Stav:** obrazovky čítajú `StateFlow` a vyvolávajú akcie vo
  `PhotoViewModel`; ViewModel spúšťa databázové operácie v coroutine scope.
- **Dáta:** `PhotoRepository` koordinuje DAO vrstvy a ukladanie obrazových
  súborov do interného úložiska aplikácie.
- **Perzistencia:** `PhotoOrganizerDatabase` poskytuje Room databázu; entity
  a DAO definujú tabuľky a operácie pre priečinky, fotografie, tagy a ich väzby.
- **Branding:** originálne logo je v `Android/logo.png`; Android resource kópia
  je `App/app/src/main/res/drawable-nodpi/tripin_logo.png`. Zobrazovaný názov
  je definovaný v `strings.xml`.
- **Gradle:** `App/settings.gradle.kts` určuje názov projektu a moduly;
  `App/build.gradle.kts` nastavuje verzie pluginov;
  `App/app/build.gradle.kts` nastavuje Android aplikáciu a závislosti.

## Pravidlo aktualizácie

Pri každom pridaní, odstránení alebo premenovaní modulu, zdrojového súboru,
balíka alebo dôležitého adresára aktualizuj príslušnú časť tohto dokumentu
v tej istej zmene. Ak sa zmení zodpovednosť komponentu bez zmeny cesty, oprav
aj jeho opis vyššie. Build výstupy a cache sem nepridávaj.

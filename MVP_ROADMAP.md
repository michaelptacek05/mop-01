# MOP-01 — cesta k prvnímu MVP

Datum založení: 27. 10. 2026. Stav k 30. 10. 2026: konzolový Kotlin/JVM program úspěšně přeložený a spuštěný, ignorování ověřené a základ nahraný na GitHub `michaelptacek05/mop-01`. `main` sleduje `origin/main`; při kontrole obě odpovídají commitu `1c81c76`. Následuje ruční přechod na Gradle. Okno a hudební implementace nezačaly. Kód i konfigurace píše Michael; mentor poskytuje vysvětlení a review podle [AGENTS.md](AGENTS.md). Kontext a rozlišení požadavků od doporučení jsou v [PROJECT_ANALYSIS.md](PROJECT_ANALYSIS.md).

## Cíl a způsob postupu

První MVP je lokální macOS aplikace v Kotlinu s jedním jednoduchým syntezátorem ovládaným počítačovou klávesnicí. Technický základ s otevřeným oknem bude první dílčí výsledek; hratelný a spolehlivě zastavitelný nástroj bude výsledkem MVP.

Dlouhodobá vize upřesněná 28. 10. 2026 zahrnuje časovou osu pro skládání hudebních vrstev podobně jako v Michaelově představě práce s FL Studiem. První syntezátor je základ zvukového nástroje, který později může ovládat i sekvencer. Typy klipů a rozsah časové osy zatím nejsou zvolené; časová osa není novou podmínkou prvního MVP.

Postupujeme po malých funkčních krocích. Před každým krokem určíme očekávaný výsledek, Michael navrhne a napíše řešení a společně jej ověříme. Dokument neurčuje termíny: tempo upřesníme podle prvních zkušeností s Kotlinem a audiem a času, který Michael chce věnovat projektu.

Milníky níže jsou plán, nikoli dokončená práce. Návrhové detaily rozsahu lze změnit dohodou před jejich implementací.

| Milník | Výsledek | Stav |
| --- | --- | --- |
| M0 — zadání a prostředí | Známé podmínky, potvrzená první technická cesta | Prostředí doplněno, technická cesta otevřená |
| M1 — základ aplikace | Ručně založená a pochopená aplikace s oknem | Konzolový program, ignorování, commity a GitHub základ hotové; další krok Gradle, okno neimplementované |
| M2 — první zvuk | Ověřená cesta od vlastního signálu k reproduktoru | Nezahájeno |
| M3 — hratelný synth | Tóny, vstupy, hlasitost a životní cyklus fungují společně | Nezahájeno |
| M4 — ověření MVP | Opakovatelně fungující desktopový nástroj | Nezahájeno |
| W1 — webový protějšek | Základní nástroj v Next.js | Po desktopovém MVP |
| R1 — další hudební funkce | Sampling a sekvencer v samostatných přírůstcích | Mimo první MVP |

## M0 — uzavřít první rozhodnutí

**Cíl:** mít dost informací pro první implementační krok, bez návrhu celé budoucí platformy.

Michael uvedl macOS 27.0, MacBook Pro s Apple M4 Pro, 24 GB RAM a vestavěné reproduktory MacBook Pro Speakers. Prostředí je zaznamenané podle jeho sdělení; kompatibilita a zvuková odezva zatím nejsou ověřené. Dále se seznámí s navrženou cestou Kotlin/JVM a Compose pro desktop a rozhodne, zda ji chce použít. Mentor vysvětlí rozdíl mezi jazykem, JVM, UI frameworkem, build nástrojem a zvukovým API. Přesnou kompatibilní kombinaci verzí ověříme před založením projektu.

**Hotovo, když:** je vybraná desktopová cesta, víme, na jakém zařízení ji ověřujeme, a Michael dokáže říct, co má dodat UI framework a co musí řešit zvuková vrstva.

**Aktuální nejbližší úkol (2. 11. 2026):** Michaelova první konfigurace konzolového Gradle projektu je zkontrolovaná čtením: název projektu, Kotlin/JVM 2.4.21, `application`, Maven Central, JVM toolchain 26 a vstupní třída `MainKt`. Přesun zdroje do `src/main/kotlin/Main.kt` je ověřený, obsah zůstal zachovaný. Michael doplnil ignorování `.gradle`, `.kotlin` a `build`; účinek pravidel je ověřený, zdroje a build skripty ignorované nejsou. Sestavení přes Gradle zatím ověřené není. Další krok je zjistit současnou dostupnost Gradlu a stav propojení s IDE, připravit kompatibilní Gradle/Wrapper a ověřit konzolový běh před přidáváním UI a audia.

## M1 — pochopit a ručně založit základ aplikace

**Cíl:** spustit vlastní jednoduché okno a rozumět tomu, co jej spouští.

Učivo průběžně podle potřeby: základní syntaxe Kotlinu, typy a nullabilita, funkce, stav, události a životní cyklus UI. Není nutné nejprve projít celý jazyk. Deklarativní UI můžeme srovnat s Reactem, ale konkrétní chování Compose budeme ověřovat v jeho dokumentaci.

Michael sám připraví projektové a build soubory. Případný oficiální generátor si zvolí vědomě; automaticky vytvořenou kostru budeme rozlišovat od jím napsané implementace. Pokud chce doslova ručně psát i veškerou kostru, zvolíme ruční založení. AI generátor nespouští a soubory nevytváří.

Praktické pořadí pro současnou složku: spuštění konzolového základu, pravidla ignorování a první commit jsou ověřené; nyní přejít na Gradle s Kotlin DSL a reprodukovatelným sestavením, potom přidat Compose a první okno. To je plán postupných kroků, nikoli seznam úkolů k provedení najednou. Dokumentovaná kompatibilita pro první konzolový Gradle build byla ověřena pro kandidáta Gradle 9.7.0, Kotlin plugin 2.4.21 a JDK 26; praktické sestavení a kompatibilita s budoucím Compose zatím ověřené nejsou. Vyřešené problémy s IDE modulem a nastavení JDK jsou popsané v analýze.

První kroky rozdělíme na spuštění prázdného okna, jednoduchý ovládací prvek a zobrazení změny stavu. Zvuk ještě nemusí být přítomen.

**Hotovo, když:** aplikaci Michael opakovaně spustí a zavře, jedna interakce viditelně mění stav a dokáže vysvětlit odpovědnost vlastních souborů. Toto je základ aplikace, ještě ne hudební MVP.

## M2 — ověřit první zvuk a vhodnost audio cesty

**Cíl:** co nejdříve zjistit, zda zvolená technologie zvládne jednoduchý interaktivní tón.

Učivo: zvukový vzorek, frekvence, amplituda, fáze, vzorkovací frekvence, buffer a latence. Buffer je blok dat připravených k přehrání; jeho velikost je jeden z faktorů ovlivňujících odezvu i riziko výpadků.

Michael nejprve vytvoří jeden tón s nízkou výchozí hlasitostí a omezenou dobou přehrávání nebo jasným zastavením. Experiment má být malý a může zpočátku fungovat odděleně od ovládání nástroje. Mentor pomůže pochopit výpočet průběhu a požadavky vybraného API; nedodá hotový oscilátor ani přehrávací smyčku.

Následuje ověření startu, zastavení, opakovaného spuštění a souběhu s UI. Michael zaznamená použitý výstup, konfiguraci a pozorování. Poslech odhalí hrubé problémy, ale není přesným měřením latence. Pokud provedeme měření, uvedeme metodu a její omezení.

**Hotovo, když:** tón opakovaně zazní a skončí, UI se nezablokuje, zvukové prostředky se uvolní a máme podklad pro volbu audio API. Přetrvávající zpoždění nebo výpadky vyřešíme před přidáním dalších funkcí.

## M3 — spojit zvuk s ovládáním nástroje

**Cíl:** získat malý skutečně hratelný syntezátor.

Před implementací Michael slovně specifikuje:

- mapování vybraných počítačových kláves na tóny;
- chování při držení dvou a více kláves v monofonním nástroji;
- chování při opakovaných událostech dlouze držené klávesy;
- reakci na puštění klávesy, ztrátu fokusu, umlčení a zavření okna.

Pak po malých krocích propojí vstup se zvukem, doplní hlavní hlasitost, viditelný stav aktivního tónu a spolehlivé umlčení. Krátký plynulý náběh a doznění pomohou odstranit lupnutí způsobená náhlou změnou amplitudy; pro MVP nepotřebujeme plný editor obálky ADSR.

**Hotovo, když:** lze zahrát krátkou melodii, vstupy odpovídají dohodnutým pravidlům, hlasitost funguje a zvuk nezůstává viset po přepnutí okna. Michael dokáže vysvětlit rozdíl mezi stavem kláves, stavem tónu a běžícím audio výstupem.

## M4 — ověřit a uzavřít desktopové MVP

**Cíl:** prokázat, že základ funguje opakovaně a jeho limity známe.

Michael provede následující scénáře. Mentor vyhodnotí výsledky a případné chyby; sám implementaci ani testy nezapisuje.

| Scénář | Očekávaný výsledek |
| --- | --- |
| Čerstvé spuštění | Okno se otevře, stav je čitelný, zvuk se nespustí nečekaně |
| Aktivace a běžné hraní | Mapované klávesy hrají očekávané tóny |
| Dlouhé držení klávesy | Tón zůstane stabilní, autorepeat jej nechtěně nespouští znovu |
| Dvě klávesy a jejich postupné uvolnění | Výsledek odpovídá předem zvolenému monofonnímu pravidlu |
| Změna hlasitosti a umlčení | Odezva je srozumitelná, nevzniká přetrvávající tón |
| Přepnutí do jiného okna během tónu | Nástroj podle specifikace umlkne a nezasekne se |
| Opakované zapnutí a vypnutí zvuku | Nenásobí se zvukové zdroje ani se neblokuje ovládání |
| Běžné hraní přibližně pět minut | Bez opakovaných výpadků, zamrznutí či patrného postupného zhoršení |
| Nedostupný audio výstup | Aplikace oznámí problém a zůstane ovladatelná |
| Zavření a nové spuštění | Zvuk skončí a aplikace se znovu spustí bez zablokovaného zařízení |

Pět minut je praktická úvodní kontrola, nikoli důkaz dlouhodobé stability. Odezvu posoudíme v dohodnutých podmínkách z M0/M2; číselný výsledek uvedeme jen při skutečném měření.

Automatické testy přidá Michael tam, kde dávají konkrétní hodnotu: například pravidla přepínání držených tónů nebo převod tónu na frekvenci. Testování samotného slyšitelného výstupu vyžaduje jiný přístup než test stavu; ruční poslech a systémové chování nenahradíme tvrzením, že prošly unit testy.

**Desktopové MVP je hotové, když:** scénáře fungují v dohodnutém prostředí, nejsou známé zásadní chyby bránící hraní a Michael rozumí vlastní implementaci. Do tohoto dokumentu doplníme skutečně ověřenou verzi, prostředí a omezení.

Pro osobní MVP stačí opakovatelně spustitelná lokální aplikace. Instalační balíček, podpis a notarizace jsou další úkol při distribuci ostatním, ne skrytá podmínka prvního tónu. Compose umožňuje balení pro macOS; podrobnosti ověříme, až bude distribuce aktuální. [Dokumentace desktopových distribucí](https://kotlinlang.org/docs/multiplatform/compose-native-distribution.html)

## W1 — převést základní nástroj na web

Tento milník přichází po desktopovém MVP; přesnou prioritu vůči sampleru a sekvenceru Michael později zvolí.

Nejdřív sepíšeme chování, které má web převzít. Michael založí Next.js projekt, určí klientskou část nástroje a ověří první tón přes Web Audio API. Následuje stejné mapování kláves, pravidla monofonie, hlasitost a umlčení. Implementace nevznikne automatickým překladem Kotlinu.

Web navíc potřebuje vědomou aktivaci zvuku, reakci na pozastavení audia a přepínání záložek, uvolňování prostředků a respektování vstupů do textových polí. Před uzavřením zvolíme podporované prohlížeče a znovu projdeme odpovídající scénáře M4.

**Hotovo, když:** první webový nástroj splní dohodnuté chování v konkrétních ověřených prohlížečích. Nevyžadujeme automaticky shodný audio engine, bitově identický výstup ani serverovou databázi.

## R1 — rozšiřovat po jednom hudebním scénáři

| Rozšíření | První malý užitečný výsledek | Další samostatná rozhodnutí |
| --- | --- | --- |
| Sampler | Načíst krátký zvukový soubor a zahrát jej klávesou | Formáty, limity, ladění, ořez, smyčka, mikrofon |
| Sekvencer | Přehrát a zastavit jeden jednoduchý opakovaný pattern | Tempo, počet kroků, noty, časování, více stop |
| Časová osa a vrstvy | Uspořádat několik klipů ve stopách a přehrát je společně | Notové versus audio klipy, posun, délka, překryvy, mixování, ukládání |
| Presety | Uložit a obnovit parametry zvuku | Formát, verze, přenos mezi aplikacemi |
| Více hlasů a efekty | Vybrané zlepšení konkrétního hudebního použití | Zátěž, mixování, řízení hlasů, rozsahy parametrů |
| Windows | Spustit a ověřit desktopový nástroj na Windows | Audio backend, vstupy, balení, distribuce |
| Prezentační web | Vysvětlit produkt a umožnit jej vyzkoušet nebo získat | Obsah, Next.js versus Astro, hosting |

Každé rozšíření dostane vlastní malý rozsah a podmínky dokončení až v době, kdy jej budeme řešit. Pásové nahrávání, celý DAW, cloud a velká knihovna enginů nejsou implicitní součástí žádného z těchto kroků.

## Jak budeme zaznamenávat pokrok

Po doloženém dokončení upravíme stav příslušného milníku a do této části stručně zapíšeme datum, výsledek a případné omezení. Rozpracovaný úkol nesmí být označen jako dokončený jen proto, že existuje návrh řešení.

- 27. 10. 2026: připraveny tři dokumenty; potvrzená priorita macOS/Kotlin, jednoduchý klávesový synth pro MVP, později web/Next.js a rozvoj směrem k samplingu a sekvenceru. Při závěrečné kontrole byla přítomná výchozí konzolová kostra `src/Main.kt` a IDE metadata. AI je nevytvářela ani neupravovala. Spuštění, desktopové UI a audio technologie zatím nejsou ověřené.
- 27. 10. 2026: Michael doplnil prostředí: macOS 27.0, MacBook Pro s M4 Pro, 24 GB RAM, MacBook Pro Speakers. M0 zůstává otevřené do volby technické cesty a vysvětlení základních odpovědností aplikace.
- 28. 10. 2026: Michael upřesnil dlouhodobou představu časové osy se skládáním hudebních vrstev podobně jako ve FL Studiu. Zaznamenáno jako pozdější rozšíření; první MVP syntezátoru zůstává podle dosavadní dohody. Typy klipů a pořadí rozšíření jsou otevřené.
- 28. 10. 2026: provedena kontrola souborů a dostupného JDK. Git a Gradle nejsou založené, systém uvádí OpenJDK 26.0.1 arm64, konfigurace IDE načítá modul bez zdrojového kořene projektu. Nejbližší praktický krok je srovnat načtení zdrojů a ručně spustit existující konzolovou ukázku. Žádný build ani aplikaci AI nespouštěla.
- 30. 10. 2026: doložené neúspěšné spuštění `MainKt` po nastavení zdrojové složky. Uložená konfigurace nyní načítá vnořený modul bez Kotlin knihovny; podrobnosti jsou v analýze. Další krok je získat výsledek ručně spuštěného sestavení. Milník spuštění zůstává nedokončený.
- 30. 10. 2026: konfigurace Kotlin knihovny doložena screenshotem a soubory; chyby `println` zmizely. Přetrvává nesprávný vnořený modul. Další doporučený krok je import existujícího kořenového modulu. Úspěšné spuštění stále není doložené.
- 30. 10. 2026: import kořenového modulu doložený soubory. Sestavení už hlásí konkrétní chybějící výstupní adresář; další krok je nastavení projektového compiler output a ověření spuštění.
- 30. 10. 2026: výstup překladu nastaven přímo modulu. Nový build hlásí nedostupné Java rozhraní `Serializable`. Další ověření míří na přímou vazbu modulu na JDK; příčina dosud není potvrzená úspěšnou opravou.
- 30. 10. 2026: po přímém výběru Module SDK OpenJDK 26 Michael doložil očekávaný výstup a úspěšné ukončení s kódem 0. Soubor `MainKt.class` existuje; dílčí úkol konzolového spuštění je dokončený. Git stále není založený a je nejbližším krokem. M1 jako celek zůstává rozpracované.
- 30. 10. 2026: Git založený na `main`, zatím bez commitů. Existuje první `.gitignore`, který vyžaduje opravu a doplnění. V indexu jsou i build výstupy a osobní workspace; před commitem je Michael vyřadí z indexu. Kontrola ignorování a indexu zůstává aktuálním úkolem.
- 30. 10. 2026: upravený `.gitignore` ověřený pomocí kontroly pravidel pro výstupy, macOS soubor, workspace a `.env`. Zdrojový kód a dokumentace zůstávají zahrnutelné do historie. Vyřazení již připravených ignorovaných souborů a první commit zatím neprovedené.
- 30. 10. 2026: první commit `b0a3939` (`initial commit`) ověřený; v historii nejsou build výstupy ani osobní workspace. Přecházíme k ručně psané konfiguraci Gradlu; první úkol je pojmenování projektu v `settings.gradle.kts`. Gradle není dostupný v kontrolovaném PATH a Wrapper zatím neexistuje.
- 30. 10. 2026: Michael požádal před Gradlem nejprve o nahrání základu na GitHub. Remote chybí, první push zatím neproběhl. Nejbližší krok je založení prázdného online repozitáře a získání URL; jeho viditelnost vybere Michael. AI dále pracuje jako mentor a push neprovádí.
- 30. 10. 2026: Michael dokončil nahrání na GitHub. Lokálně ověřený `origin` na `michaelptacek05/mop-01`, upstream `main` → `origin/main`, commit `1c81c76` (`uprava md`) a čistý pracovní strom před aktualizací dokumentace. Další praktický krok je `settings.gradle.kts`.
- 30. 10. 2026: `settings.gradle.kts` vytvořený a po Michaelově opravě uvozovek správný při čtecím review. Gradle samotný zatím není spuštěný. Další úkol je první část `build.gradle.kts`: Kotlin/JVM plugin v bloku pluginů.
- 31. 10. 2026: Michael doplnil Kotlin/JVM plugin, `application` a Maven Central v `build.gradle.kts`. Zápis těchto bloků je při čtecím review správný; sestavení ještě neproběhlo. Nejbližší krok je explicitní JVM toolchain pro JDK 26.
- 31. 10. 2026: JVM toolchain 26 a vstupní třída `MainKt` doplněné Michaelem a zkontrolované čtením. Byl vysvětlen rozdíl mezi názvem vlastnosti a hodnotou i odvození názvu JVM třídy ze souboru. Zdroj stále leží v `src/Main.kt`; dalším krokem je jeho přesun do standardního umístění Gradlu.
- 31. 10. 2026: přesun do `src/main/kotlin/Main.kt` ověřený čtením souboru i Git statusem, který jej rozpoznává jako přejmenování/přesun. `.gradle` již existuje a není ignorovaná. Další úkol je doplnění `.gitignore` pro lokální data Gradlu/Kotlinu a build výstupy.

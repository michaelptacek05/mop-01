# MOP-01 — prvotní analýza projektu

Datum založení analýzy: 27. 10. 2026. Aktuální stav k 30. 10. 2026: konzolový Kotlin/JVM program je úspěšně přeložený a spuštěný, pravidla ignorování jsou ověřená, první commit je `b0a3939` (`initial commit`). GitHub základ je připojený přes `origin` na `https://github.com/michaelptacek05/mop-01.git`; lokální `main` sleduje `origin/main` a při kontrole odpovídá commitu `1c81c76` (`uprava md`). Následuje ruční konfigurace Gradlu; desktopové okno a audio zatím nejsou implementované. Pracovní název vychází z názvu složky; finální název produktu není rozhodnutý. Pravidla spolupráce jsou v [AGENTS.md](AGENTS.md), postup v [MVP_ROADMAP.md](MVP_ROADMAP.md).

## 1. Vize a potvrzené zadání

Vlastní hudební aplikace inspirovaná přístupem OP-1 field: kompaktní nástroj pro hraní, hledání zvuků a později skládání krátkých hudebních nápadů. Dlouhodobými pilíři jsou syntéza, sampling a sekvencer. Vlastní produkt nemusí přebírat všechny funkce ani přesnou podobu hardwarové předlohy.

Upřesnění z 28. 10. 2026: Michael si představuje také časovou osu podobnou způsobu skládání ve FL Studiu, kde postupně skládá hudební vrstvy. Časová osa s více stopami patří do dlouhodobé vize. Zatím není rozhodnuté, zda první taková verze bude obsahovat notové patterny, nahrané audio klipy, nebo obojí. Toto upřesnění samo nerozšiřuje dříve zvolený první MVP syntezátoru.

OP-1 field spojuje syntezátor, sampler, sekvencery a další nástroje včetně pásového nahrávání. Pro náš projekt je to inspirační mapa, nikoli seznam požadavků na první verzi. [Oficiální přehled OP-1 field](https://teenage.engineering/products/op-1)

| Oblast | Potvrzená dohoda |
| --- | --- |
| Autor implementace | Michael; AI vede, vysvětluje a provádí review |
| První platforma | macOS |
| Jazyk desktopové aplikace | Kotlin |
| První MVP | Jeden jednoduchý syntezátor ovládaný počítačovou klávesnicí |
| Další hudební funkce | Sampling a sekvencer, postupně po MVP |
| Dlouhodobé skládání | Časová osa pro uspořádání a vrstvení hudebních částí; podoba klipů otevřená |
| Druhá aplikace | Webový hudební nástroj v Next.js |
| Windows | Možné rozšíření, bez závazku pro první MVP |
| Prezentační web | Pozdější samostatný úkol; technologie otevřená, možný Next.js nebo Astro |

Dokončení desktopového MVP nebude znamenat dokončení celé vize. Webová aplikace a další hudební funkce mají vlastní pozdější milníky.

### Výchozí prostředí pro ověření

Michael uvedl následující prostředí; údaje nejsou nezávisle ověřené diagnostikou:

| Položka | Hodnota |
| --- | --- |
| Systém | macOS 27.0 |
| Počítač a procesor | MacBook Pro, Apple M4 Pro |
| Paměť | 24 GB RAM |
| Zvukový výstup | Vestavěné reproduktory — MacBook Pro Speakers |

První zvukové experimenty budeme vyhodnocovat na tomto výstupu. Kompatibilita konkrétního JDK, Compose a audio API ani latence zatím nebyly ověřeny.

## 2. Výchozí zkušenosti a způsob učení

Michael se hodnotí jako lehký medior v Reactu/TypeScriptu a začátečník v Kotlinu a digitálním audiu. To je pracovní výchozí bod, nikoli nezávislé hodnocení.

Michael povolil analýzu veřejných repozitářů profilu [michaelptacek05](https://github.com/michaelptacek05). Při přípravě dokumentu se profil ani seznam repozitářů nepodařilo načíst dostupnými webovými nástroji; terminál neměl funkční DNS přístup a nebyl dostupný připojený prohlížeč. Žádný zdrojový kód z profilu nebyl zkontrolován. Z úrovně repozitářů zde proto nevyvozujeme závěry.

Pokud bude veřejný kód později dostupný, smysl má projít několik reprezentativních ručně psaných částí: práci se stavem, rozdělení odpovědností, typy, chybové stavy a testování. Zjištění poslouží k nastavení tempa mentoringu; veřejný kód sám neprokáže autorství ani celkovou profesní úroveň.

Pro začátek není nutný obecný kurz programování ani celý kurz DSP. Potřebujeme postupně propojit tři témata: Kotlin, stav a události desktopového UI, základní tvorbu a přehrávání digitálního zvuku. DSP znamená digitální zpracování signálu.

Při závěrečné kontrole složka obsahovala také IDE metadata a `src/Main.kt` s úvodním konzolovým příkladem. AI tento soubor pouze přečetla. Spuštění nebylo ověřeno; tento základ zatím nedokládá vlastní desktopové UI ani zvukovou implementaci.

### Kontrola výchozího stavu 28. 10. 2026

- Ve složce jsou tři projektové dokumenty, konzolová ukázka `src/Main.kt` a metadata IntelliJ. Žádná implementace okna nebo audia nebyla nalezena.
- Složka není Git repozitář; kontrola stavu skončila hlášením o chybějícím repozitáři.
- Nebyly nalezeny build soubory Gradlu ani Gradle Wrapper. Současná konfigurace je projekt řízený IntelliJ.
- Systémový seznam JDK obsahuje OpenJDK 26.0.1 pro arm64. Nastavení projektu odkazuje na OpenJDK 26. Jde o kontrolu instalace a konfigurace, nikoli úspěšného sestavení.
- `.idea/modules.xml` načítá `.idea/mop-01.iml`. Tento modul má kořen obsahu uvnitř `.idea` a nemá definovaný zdrojový adresář. Samostatný `mop-01.iml` v kořeni naopak zdroje v `src` definuje. Nastavení je nekonzistentní a může bránit rozpoznání a spuštění současné ukázky. Opravu a spuštění provede Michael.

Nejbližší ověření je správné načtení zdrojů v IDE a ruční spuštění konzolové ukázky. Následovat má verzování a sestavení přes Gradle, potom desktopové okno. Gradle je doporučenou cestou i v desktopové šabloně JetBrains. [Desktopová šablona Compose](https://github.com/JetBrains/compose-multiplatform-desktop-template)

Při volbě Gradlu nelze libovolně kombinovat verze: pro běh na Javě 26 uvádí aktuální tabulka podporu od Gradlu 9.4.0. To samo nepotvrzuje kompatibilitu celé kombinace Kotlinu a Compose. Výslednou kombinaci zvolíme před přechodem na Gradle; instalované JDK zatím neměníme. [Kompatibilita Gradlu a Javy](https://docs.gradle.org/current/userguide/compatibility.html)

### Neúspěšné spuštění 30. 10. 2026

Michael doložil screenshot s chybou `ClassNotFoundException: MainKt`, ukončením s kódem 1 a červeně označenými voláními `println`. Modré označení `src` bylo doložené předchozím screenshotem, úspěšné sestavení však nikoli. Aktuální `src/Main.kt` obsahuje pozdrav se jménem Michael a cyklus; příčina chyby zatím není prokázaná výpisem sestavení.

Kontrola uložených nastavení nově ukázala odkaz na vnořený modul `mop-01/.idea/mop-01.iml`, který neobsahuje závislost na Kotlin standardní knihovně a používá zdrojovou cestu relativní k této vnořené složce. Původní kořenový `mop-01.iml` zůstává samostatně. Kandidáty příčiny jsou tedy nesprávné načtení zdrojů a chybějící Kotlin závislost; případnou chybu překladu má nejprve potvrdit ručně spuštěné sestavení. AI žádné nastavení ani zdrojové soubory neměnila.

Následná kontrola 30. 10. 2026: Michael provedl konfiguraci Kotlinu. Existuje `.idea/libraries/KotlinJavaRuntime.xml` a načítaný modul už knihovnu deklaruje; screenshot také neukazuje červené chyby u `println`. Dialog „All modules with Kotlin files are configured“ proto není chybou. Přetrvává konkrétní nesoulad modulu: projekt stále načítá vnořený `mop-01/.idea/mop-01.iml` se zdroji relativními k této složce, zatímco skutečný `src/Main.kt` leží v kořeni projektu. Doporučený další krok je v IDE odpojit vnořený modul a importovat existující kořenový `mop-01.iml`, který už má správný vztah ke zdrojům i odkaz na knihovnu. Úspěch sestavení a spuštění se teprve ověří.

Další ověření 30. 10. 2026: import kořenového modulu je doložený uloženým `.idea/modules.xml`, který již odkazuje na kořenový `mop-01.iml`. Michael nyní doložil výpis sestavení s chybou „Output directory not specified for Module 'mop-01' production“. Modul dědí projektový výstup překladu, ale v uloženém nastavení projektu výstup není definovaný. Dalším krokem je nastavit projektový výstup do složky `out` v kořeni projektu, ověřit dědění v Modules → Paths a znovu sestavit a spustit. Dosavadní problém s načítáním vnořeného modulu je vyřešený; úspěšný překlad dosud doložený není.

Následně Michael nastavil výstup přímo modulu do `out/production/mop-01` a `out/test/mop-01`; tyto cesty jsou uložené v kořenovém `mop-01.iml`. Překlad nyní hlásí nedostupné `Serializable` jako nadtyp `String`. Modul stále dědí projektové JDK, zatímco kořenový `.idea/misc.xml` s projektovým SDK v kontrolovaných souborech chybí. První další ověření je proto vybrat OpenJDK 26 přímo jako Module SDK v Modules → Dependencies a znovu sestavit. Jde o ověření vazby na JDK, nikoli prokázanou nekompatibilitu Javy 26 nebo důvod upravovat uživatelský kód.

### Úspěšné spuštění 30. 10. 2026

Po přímém nastavení Module SDK na OpenJDK 26 Michael doložil pozdrav „Hello, Michael!“, výpisy `i = 1` až `i = 5` a ukončení s kódem 0. Kontrola souborů potvrzuje přímou vazbu modulu na `openjdk-26` a existenci `out/production/mop-01/MainKt.class`. Tím je pro tuto konzolovou ukázku ověřen překlad a spuštění; předchozí chyby modulu, knihovny, výstupu a vazby na JDK už nejsou aktuální překážkou. Není to zatím ověření desktopového UI nebo audio technologie. AI program nesestavovala ani nespouštěla; spuštění provedl Michael.

Další krok je založení lokálního Gitu, ruční příprava pravidel ignorování a následně přechod na Gradle. Kontrola Gitu v době potvrzení spuštění stále hlásila chybějící repozitář. Vygenerovaný obsah `out` patří mezi soubory, které nemají být součástí zdrojové historie.

### Rozpracované verzování 30. 10. 2026

Následná kontrola potvrzuje Git na větvi `main` bez commitů a nově vytvořený `.gitignore`. Jeho první verze obsahuje pravidlo pro `.env` a nepřesně zapsaný název souboru macOS. Je potřeba opravit velikost písmen názvu `.DS_Store` a doplnit ignorování výstupní složky `out` a osobního nastavení `.idea/workspace.xml`. V indexu už jsou připravené i přeložené soubory a soubor workspace; před prvním commitem je Michael vyřadí z indexu se zachováním lokálních souborů. AI `.gitignore` ani Git index neupravuje.

Po Michaelově úpravě jsou pravidla pro `.DS_Store`, `.env`, `out` a `.idea/workspace.xml` ověřená pomocí čtecí kontroly `git check-ignore` bez použití indexu. Zdrojový soubor, pravidla spolupráce ani samotný `.gitignore` tato pravidla nevylučují. Ignorované build výstupy a workspace však zatím stále zůstávají v indexu; workspace má navíc změny od posledního přidání. Dalším krokem je cílené vyřazení těchto položek pouze z indexu, nové přidání aktuálních zdrojů a dokumentace a kontrola připraveného obsahu. AI žádný z těchto zápisů do Gitu neprovádí.

### První commit a přechod na Gradle 30. 10. 2026

Michael vytvořil commit `b0a3939` s názvem `initial commit`. Kontrola obsahu potvrzuje zahrnutí zdroje, `.gitignore`, dokumentace a potřebné dosavadní projektové konfigurace; výstupy `out` a osobní `.idea/workspace.xml` v commitu nejsou. Před následnou aktualizací těchto dokumentů byl pracovní strom čistý. Další změny dokumentace zaznamenávají nový stav a nejsou samy dalším commitem.

V projektu zatím nejsou build soubory ani Gradle Wrapper; příkaz `gradle` se nenašel v aktuálním PATH. To nevylučuje instalaci mimo PATH nebo možnost stažení prostřednictvím IDE.

Pro první ručně psanou Kotlin/JVM konfiguraci je doporučeným kandidátem Kotlin Gradle plugin 2.4.21 s Gradlem 9.7.0 a instalovaným JDK 26. Tabulka Kotlinu uvádí plnou podporu Gradlu do 9.7.0 pro KGP 2.4.21; Gradle uvádí běh na JDK 26 od 9.4.0. Je to ověření dokumentované kompatibility pro konzolový základ, nikoli provedený build nebo schválená finální kombinace s Compose. [Kompatibilita Kotlin pluginu](https://kotlinlang.org/docs/gradle-configure-project.html), [kompatibilita Javy a Gradlu](https://docs.gradle.org/current/userguide/compatibility.html)

Další malý úkol je ručně vytvořit v kořeni `settings.gradle.kts` a pojmenovat kořenový projekt. Následují postupně vysvětlený `build.gradle.kts`, zpřístupnění připnuté verze Gradlu a Wrapperu, standardní umístění zdroje, aktualizace ignorování pro Gradle a ověření sestavení a běhu. Každý krok provede Michael; AI dodává výklad a review. Wrapper je podpůrný výstup oficiálního nástroje, jeho případné vytvoření nástrojem provede Michael vědomě; AI jej negeneruje a nevyužije generátor k vytvoření aplikační implementace.

### Priorita prvního nahrání na GitHub

Michael následně upřesnil, že chce před konfigurací Gradlu uložit aktuální základ také na GitHub. Lokální `main` zatím nemá nastavený remote ani upstream. Existující commit je zkontrolovaný; ignorované build výstupy a workspace v něm nejsou. Dva dokumenty se po commitu průběžně změnily a jejich aktuální verze se odešlou až po dalším Michaelově commitu. GitHub CLI je dostupné v PATH; stav přihlášení ani existence dosud nepřipojeného online repozitáře nebyly ověřené.

Nejbližší uživatelský krok je vytvořit prázdný GitHub repozitář pod zvoleným názvem a viditelností, bez inicializace README, licence a dalšího `.gitignore`, a předat jeho URL. Potom Michael připojí remote, případně uloží aktuální dokumentaci a provede první push větve `main` s nastavením upstreamu. AI pouze navede a ověří výsledek; sama GitHub repozitář nezakládá ani neprovádí push. Po ověření nahrání se vrátíme k naplánované ruční konfiguraci Gradlu.

Dokončení tohoto kroku 30. 10. 2026: Michael potvrdil dokončení a lokální kontrola ukazuje remote `origin` s adresou `https://github.com/michaelptacek05/mop-01.git`, větev `main` s upstreamem `origin/main` bez indikovaného rozdílu a commit `1c81c76` (`uprava md`). Pracovní strom byl před zapsáním tohoto záznamu čistý. Základ na GitHub nahrál Michael; AI pouze zkontrolovala lokální stav. Viditelnost online repozitáře nebyla zjišťována. Záznam o dokončení v těchto dokumentech může být součástí dalšího commitu při práci na Gradlu; není nutné kvůli každému záznamu přerušovat implementaci dalším commitem.

### První soubor konfigurace Gradlu

Michael vytvořil kořenový `settings.gradle.kts` a po review sám opravil jednoduché uvozovky na dvojité. Čtením ověřeno správné nastavení jména kořenového projektu na `mop-01`. Rozdíl mezi znakem a řetězcem byl vysvětlen s návazností na zkušenosti s TypeScriptem. Gradle zatím spuštěný nebyl a `build.gradle.kts` ani Wrapper dosud neexistují.

Další úkol je ručně založit kořenový `build.gradle.kts` a podle dokumentace zapsat blok pluginů s Kotlin/JVM pluginem verze 2.4.21. Mentor vysvětlí, že plugin dodává Gradlu podporu překladu Kotlinu pro JVM. Toto je první část build konfigurace, nikoli hotový spustitelný Gradle projekt. Následující části a ověření přidáme až po review Michaelova zápisu. [Kotlin/JVM Gradle plugin](https://kotlinlang.org/docs/gradle-configure-project.html#targeting-the-jvm)

### Průběžné review build skriptu 31. 10. 2026

Michael vytvořil `build.gradle.kts` a postupně sám doplnil Kotlin/JVM plugin 2.4.21, plugin `application` a Maven Central v samostatném bloku repozitářů. Po opravě původního zápisu Groovy na Kotlin DSL je zápis těchto částí správný při čtecím review. Gradle nebyl spuštěný; nejde o důkaz dokončeného sestavení ani spuštění aplikace.

Další malý úkol je explicitní JVM toolchain pro instalované JDK 26. Nastavení má sjednotit cílovou JVM verzi Kotlinu a Javy a vyjádřit požadované JDK v projektu. Verze JDK a verze Kotlin pluginu jsou samostatné údaje. Podpora Java 26 je dokumentovaná od Kotlinu 2.4.0; praktické použití v tomto projektu zatím ověřené není. Vstupní třída aplikace, dostupnost Gradlu/Wrapperu, standardní umístění zdroje a ignorování nových build výstupů zůstávají k dokončení. [JVM toolchain](https://kotlinlang.org/docs/gradle-configure-project.html#gradle-java-toolchains-support), [podpora Java 26](https://kotlinlang.org/docs/whatsnew24.html#kotlin-jvm)

### První verze konzolového build skriptu 31. 10. 2026

Michael následně doplnil JVM toolchain 26 a opravil ukázkovou vstupní třídu na `MainKt`. Tyto hodnoty jsou zkontrolované čtením. Vysvětlili jsme, že `mainClass` je název konfigurační vlastnosti a `MainKt` její hodnota; Kotlin do této JVM třídy překládá funkce z aktuálního souboru `Main.kt`, který nemá deklaraci balíčku ani vlastní JVM název. Nejde dosud o ověřený Gradle build.

Zdroj zůstává v `src/Main.kt`, zatímco výchozí umístění Kotlin zdrojů pro Gradle je `src/main/kotlin`. Další malý úkol provede Michael: přesun existujícího souboru do `src/main/kotlin/Main.kt`. Samotný přesun bez změny balíčku nebo názvu souboru nemění vstupní třídu `MainKt`. [Umístění zdrojů](https://kotlinlang.org/docs/gradle-configure-project.html#kotlin-and-java-sources), [funkce na úrovni souboru a JVM třídy](https://kotlinlang.org/docs/java-to-kotlin-interop.html#package-level-functions)

## 3. Co má první MVP přinést

První uživatelský scénář: Michael spustí macOS aplikaci, vědomě zapne zvuk, zahraje několik tónů klávesnicí, změní hlasitost a vše spolehlivě umlčí. Dokáže vlastními slovy vysvětlit, jak se stisk klávesy promění ve zvuk.

Následující podrobnosti jsou návrhem rozsahu, nikoli dalšími potvrzenými požadavky:

- Jedna obrazovka s jasným stavem zvuku a čitelným mapováním kláves.
- Jeden jednoduchý zvukový zdroj, pro začátek sinusový průběh.
- Monofonie: nejvýše jeden znějící tón. Více současně držených kláves má předem určené a opakovatelné chování.
- Malý pevný rozsah tónů, například jedna oktáva; konečné mapování vybere Michael.
- Hlavní hlasitost, viditelné umlčení a krátký plynulý náběh/doznění tónu.
- Spolehlivé uvolnění tónu při puštění klávesy, ztrátě fokusu nebo ukončení aplikace.
- Lokální hraní bez účtu, serveru nebo internetového připojení.

Sinus umožní soustředit se na přehrávání a ovládání. Další průběhy přinášejí i otázku aliasingu: falešných frekvenčních složek vznikajících při nevhodné digitální syntéze. To není důvod je vynechat navždy, ale oddělený pozdější krok.

První MVP nepotřebuje sampler, sekvencer, nahrávání, export, efekty, MIDI, více hlasů, ukládání projektů, cloud ani hotovou vizuální kopii OP-1 field. Nepotřebuje ani webovou verzi. Jeho hodnota je zatím úzká: funkční a pochopitelný vlastní nástroj.

## 4. Technický směr desktopové aplikace

**Návrh:** Kotlin/JVM a Compose Multiplatform pro desktopové rozhraní. Kotlin je potvrzený; Compose a JVM jsou zatím doporučenou cestou k ověření. Compose podporuje desktopové aplikace a umožňuje pozdější rozšíření na Windows. To samo nezaručuje přenositelnost vybraného audia. [Kotlin Multiplatform](https://kotlinlang.org/multiplatform/)

Desktopový Compose na JVM není totéž jako Kotlin/Native aplikace přímo nad macOS frameworky. Pro první variantu mluví použití JVM ekosystému a možnost rozšířit desktopový cíl. Pokud se ukáže překážka v audiu, vyhodnotíme ji samostatně před přidáváním nativních integrací.

UI framework není zvukový engine. Pro první průzkum dává smysl porovnat Java Sound dostupný z JVM s případnou specializovanou knihovnou. Java Sound umožňuje průběžně přehrávat generovaná data; dokumentace jej uvádí i pro interaktivně vytvářený zvuk. Z toho ale nelze vyvodit přijatelnou latenci na Michaelově Macu. Tu musí ověřit malý ručně napsaný experiment. [Oracle: přehrávání audia](https://docs.oracle.com/javase/tutorial/sound/playing.html)

Před definitivní volbou audio řešení ověřit:

- dostupnost na používaném Macu, konkrétní verzi macOS a procesoru;
- zpoždění mezi vstupem a slyšitelným tónem;
- výpadky, lupání a stabilitu při souběhu s UI;
- správné zastavení a uvolnění zvukového zařízení;
- složitost API, údržbu, licenci a případné nativní závislosti;
- možnost budoucí podpory Windows, pokud ji Michael bude chtít.

Zpočátku hodnotit vestavěný nebo kabelový zvukový výstup. Bluetooth posuzovat zvlášť, aby nezamaskoval zpoždění aplikace. Před měřením netvrdit, že nástroj splňuje profesionální požadavky.

## 5. Hranice odpovědností

Zpočátku jde o myšlenkové oddělení, nikoli požadavek na mnoho modulů a rozhraní:

| Část | Odpovědnost |
| --- | --- |
| Ovládání a UI | Zobrazuje stav, sbírá vstupy, dává srozumitelnou zpětnou vazbu |
| Stav nástroje | Eviduje držené klávesy, aktivní tón a parametry |
| Zvuková vrstva | Tvoří nebo přehrává signál a zajišťuje jeho plynulý průběh |
| Pozdější sekvencer a časová osa | Určují, kdy se mají noty nebo klipy přehrát a jak jsou uspořádané ve stopách |
| Integrace se systémem | Otevře a zavře audio prostředky, reaguje na životní cyklus okna |

Tvorba zvukových vzorků nesmí záviset na rychlosti překreslování UI. UI událost vyjadřuje záměr zahrát; souvislý průběh zvuku má vlastní časování. Do cesty, která musí pravidelně dodávat zvuk, nepatří čekání na síť, dlouhé operace ani zbytečné blokování.

Pro malou monofonní aplikaci není potřeba obecný pluginový systém nebo framework pro všechny budoucí enginy. Oddělovat budeme to, co už má konkrétní odpovědnost nebo se obtížně ověřuje.

Živé hraní a pozdější přehrávání z časové osy mohou ovládat stejný nástroj. Notový klip uchovává instrukce, například výšku tónu, začátek a délku; audio klip obsahuje už vytvořený nebo nahraný zvuk. Délka noty a doznění zvuku jsou dvě různé věci: konec noty může teprve zahájit doznění nástroje. Tyto principy lze nyní vysvětlit bez implementace časové osy nebo návrhu celého datového formátu.

## 6. Webová hudební aplikace

Next.js je potvrzená technologie pro pozdější webovou aplikaci. Doporučený jazyk je TypeScript. Interaktivní část a prohlížečové audio musí respektovat hranici mezi serverovým a klientským prostředím Next.js; přístup k browser API patří na klienta. [Next.js: serverové a klientské komponenty](https://nextjs.org/docs/app/getting-started/server-and-client-components)

Pro zvuk je přirozeným kandidátem Web Audio API. Prohlížeče omezují automatické spuštění zvuku; aplikace proto musí počítat s vědomou aktivací uživatelem a stavem pozastaveného audia. [MDN: doporučení pro Web Audio API](https://developer.mozilla.org/en-US/docs/Web/API/Web_Audio_API/Best_practices)

Počáteční doporučení je sdílet slovní specifikaci chování a ověřovací scénáře, nikoli vynucovat společnou implementaci s Kotlinem. Stejné vstupy a pravidla nástroje lze implementovat zvlášť nad různými audio API. Stejný parametr přitom nezaručuje bitově shodný zvuk.

O společném formátu presetů nebo projektů rozhodneme až tehdy, kdy skutečně vznikne ukládání a potřeba přenosu dat. Společný engine přes Kotlin/JS, Wasm nebo jinou integraci není součástí prvního MVP.

## 7. Rizika a způsob jejich ověření

| Riziko | Jak se projeví | Nejbližší opatření |
| --- | --- | --- |
| Učení všeho najednou | Kotlin, DSP i UI blokují jeden velký úkol | Oddělit okno, první zvuk a hratelný nástroj |
| Nevhodná audio cesta | Opožděné reakce nebo výpadky | Brzký zvukový experiment před větší prací na UI |
| Lupnutí při změně zvuku | Slyšitelný přechod při startu, stopu či hlasitosti | Pochopit a ověřit plynulé změny amplitudy |
| Zaseknutý tón | Zvuk pokračuje po přepnutí okna | Držené klávesy a ztrátu fokusu zahrnout do specifikace |
| Autorepeat klávesnice | Dlouhý stisk opakovaně spouští tón | Rozlišovat nový stisk od opakovaných událostí |
| Přerůstání zadání | Sampler nebo sekvencer oddálí základ | Uzavřít desktopový synth podle měřitelných kritérií |
| Předčasné sdílení kódu | Integrace platforem je složitější než nástroj | Nejprve jednotné chování, dvě jednoduché implementace |
| AI převezme učení | Michael jen přepisuje cizí řešení | Uplatňovat AGENTS.md a kontrolovat porozumění |

## 8. Pozdější rozvoj

Pořadí za desktopovým MVP je zatím návrh k pozdějšímu rozhodnutí:

1. Webový protějšek základního syntezátoru; srovnání vstupů, zvuku a limitů platforem.
2. Sampler: nejdříve načtení a přehrání krátkého souboru, následně ladění a obálka. Živé nahrávání a editace až jako další rozhodnutí.
3. Jednoduchý krokový sekvencer: nejdříve jedna stopa a pevný počet kroků, poté tempo a hudební rozšíření. Pravidelné časování zvuku řešit odděleně od animace UI.
4. Časová osa pro skládání vrstev: nejdříve malý scénář s několika klipy a jasným začátkem, délkou a stopou. Rozhodnutí mezi notovými a audio klipy, případně jejich kombinací, je otevřené. S tímto krokem znovu vyhodnotíme ukládání a případně export.
5. Další syntéza, efekty, více hlasů, MIDI nebo nahrávání vrstev podle priorit.
6. Windows a prezentační web jako samostatně vyhodnocené cíle.

Sampling a sekvencer mohou dostat přednost před webovým portem, pokud si to Michael později zvolí. Zatím je potvrzená pouze priorita macOS před webem; pořadí všech dalších funkcí uzavřené není.

## 9. Otevřená rozhodnutí

- Přijetí doporučené kombinace Kotlin/JVM a Compose pro desktop.
- Audio API po praktickém experimentu, nikoli pouze podle seznamu funkcí knihovny.
- Rozsah tónů, konkrétní klávesy a pravidlo pro více současně držených kláves.
- Referenční podmínky a přijatelná odezva pro MVP; číslo nezaměňovat s neprovedeným měřením.
- Které vlastnosti desktopového MVP musí později převzít web a ve kterých prohlížečích jej ověříme.
- Priorita webového portu vůči sampleru a sekvenceru po dokončení desktopového základu.
- Rozsah první časové osy, typy klipů a její pořadí vůči ostatním rozšířením.

Tato rozhodnutí není nutné vyřešit současně. Každé uzavřeme před úkolem, který na něm závisí. Verze nástrojů zvolíme při ručním zakládání projektu podle tehdy kompatibilní stabilní kombinace; dokument nyní nepředepisuje neověřené verze.

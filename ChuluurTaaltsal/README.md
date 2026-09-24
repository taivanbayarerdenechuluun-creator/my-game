# Чулуу таалцах тоглоом - Java OOP Бие даалт

## Тоглоомын дүрэм
- 2 тоглогч тус бүр санамсаргүй тооны чулуу авна
- Нийт чулуу = тогтоосон тоо (жишээ: 20)
- Эелж ээлжлэн нийт чулуунуудын нийлбэрийг таана
- **Зov таасан** → нөгөөгийн чулуунуудыг авна → тоглоом дуусна
- **Буруу таасан** → өөр өөрийн чулуугаа буцааж, шинэ чулуу авна
- Нийт чулуу дуусвал хэн их чулуутай тэр ялна

## Классын диаграм (Class Diagram)

```
<<interface>>                    <<interface>>
 Saveable                         Sortable
 + saveToFile()                   + sortScores()
 + loadFromFile()                 + searchPlayer()
 + toFileString()
      ^                                ^
      |                                |
      |                                |
   Togloch                       TogloomDun
   - ner: String                 - dunList: List<DunBichleg>
   - niitChuluu: int             - filename: String
   - eeljToo: int                + dunNem()
   - tuuhUshig: List<int>        + dunHaruulah()
   + Togloch(ner)                + sortScores() [BubbleSort]
   + Togloch(ner, chuluu)        + searchPlayer() [LinearSearch]
   + Togloch(ner, chuluu, eelj)  + saveToFile()
   + chuluuNem()                 + loadFromFile()
   + chuluuButsaa()              +-----------+
   + eeljNem()                   | DunBichleg| (static inner class)
   + toString()                  +-----------+
         ^                       
         |                       
         | (composition)         
         |                       
  TogloomManager  <>--- ChuluuSav
  - togloch1: Togloch    - niitChuluu: int
  - togloch2: Togloch    - ugugdsunChuluu: int
  - chuluuSav: ChuluuSav + chuluuOlgo(): int
  - dunBurtgel: TogloomDun + chuluuHuleen()
  - zovNiilber: int      + chuluuBainaUu()
  + togloomEhluuleh()    + toString()
  + eeljTogloh()
  + etstDunHaruulah()

 TogloomAldaa extends Exception
  - aldaaToo: int
  + TogloomAldaa(msg)
  + TogloomAldaa(msg, code)
  + TogloomAldaa(msg, cause)
  + toString()
```

## OOP Ойлголтуудын хэрэгжүүлэлт

| Шаардлага | Класс / Файл |
|-----------|-------------|
| Файлтай ажиллах I/O | TogloomDun.saveToFile(), loadFromFile(), TogloomManager лог файл |
| Байгуулагч функц (overloaded) | Togloch(3 байгуулагч), TogloomAldaa(3 байгуулагч), ChuluuSav |
| Дахин тодорхойлох (Override) | toString() — Togloch, ChuluuSav, TogloomAldaa |
| Бүрдмэл харьцаа (Composition) | TogloomManager нь ChuluuSav, TogloomDun, Togloch-г агуулна |
| Эрэмбэлэлт (Bubble Sort) | TogloomDun.sortScores() |
| Хайлт (Linear Search) | TogloomDun.searchPlayer() |
| Алдаа боловсруулалт | TogloomAldaa extends Exception, try-catch бүх оролтод |
| Интерфэйс | Saveable, Sortable |

## Хэрхэн ажиллуулах

```bash
# Хавтас дотор
mkdir bin
javac -encoding UTF-8 src/*.java -d bin/
cd bin
java -cp . Main
```

## Файлын бүтэц
```
ChuluurTaaltsal/
├── src/
│   ├── Main.java             # Үндсэн оролт
│   ├── TogloomManager.java   # Тоглоомын удирдлага (Composition)
│   ├── Togloch.java          # Тоглогчийн класс
│   ├── ChuluuSav.java        # Чулуу сав
│   ├── TogloomDun.java       # Дүнгийн бүртгэл + Sort/Search
│   ├── TogloomAldaa.java     # Custom Exception
│   ├── Saveable.java         # Interface 1
│   └── Sortable.java         # Interface 2
└── README.md
```

## Хадгалагддаг файлууд (runtime)
- `togloom_dun.txt` — Тоглоомын дүнгийн бүртгэл (CSV хэлбэр)
- `togloom_log.txt` — Тоглоомын лог файл

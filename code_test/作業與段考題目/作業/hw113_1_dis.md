
# H01 Discussion

## Common problem
* Submission
  * PDF report, No word file
  * Just zip, don't RAR
  * Project name: <YourID-HW01>
  * the input is 'win-loss', not the seed order
* Program
  * Use POM to build your project
  * Comment is important!!
  * don't put logic in `main()`
  * should care "testability"-- modualize the logic; separate it from UI
* Preventive programming
  * Dont just IO exception; think about MLB rules
  * Check the MLB rules
  * Logging
* Test
  * Don't just `print` error message
  * more test cases; not only one collect file
* Others
  * No 簡體中文
  * 不要抄襲！

## Strange logic

```java
    // 排序分區隊伍
    Collections.sort(divisionChampions, new Comparator<Team>() {
        @Override
        public int compare(Team t1, Team t2) {
            // 首先比較勝場數
            if (t1.getRecord().getWin() != t2.getRecord().getWin()) {
                return Integer.compare(t1.getRecord().getWin(), t2.getRecord().getWin());
            }
            // 如果勝場數相同，則比較敗場數
            return Integer.compare(t1.getRecord().getLose(), t2.getRecord().getLose());
        }
    });
```
## No Simplied Chinese words 

新增队伍??

```java
    // 新增队伍
    public void addTeam(String league, Team team) {
        if (league.equalsIgnoreCase("AMERICAN")) {
            ALT.add(team);
        } else if (league.equalsIgnoreCase("NATIONAL")) {
            NLT.add(team);
        }else {
            System.out.println("Invalid league name: " + league);
        }
    }
```


## Exception handle!

* How to do if it is `null`
```java
        List<Team> divisionWinners = new ArrayList<>();
        if (eastChampion != null) divisionWinners.add(eastChampion);
        if (centralChampion != null) divisionWinners.add(centralChampion);
        if (westChampion != null) divisionWinners.add(westChampion);
```

* don't just handle IO exception

```java
        try {
            JsonNode rootNode = mapper.readTree(new File(filepath));
            for (JsonNode teamData : rootNode) {

                int wins = teamData.get("W").asInt();
                int losses = teamData.get("L").asInt();
                double pct = teamData.get("PCT").asDouble();

                if (wins <= 0 || losses <= 0 || pct < 0 || pct > 1) {
                    System.err.printf("Error: Invalid data for team %s: W=%d, L=%d, PCT=%.3f%n",
                            teamData.get("name").asText(), wins, losses, pct);
                    continue;
                }

                teams.add(new Team(
                        teamData.get("name").asText(),
                        wins,
                        losses,
                        pct,
                        teamData.get("league").asText()
                ));
            }
        } catch (IOException e) {
            System.err.println("Error: Failed to read file " + filepath);
            e.printStackTrace();
        }
```



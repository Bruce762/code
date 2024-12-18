package org.example;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    public static void teamsort(List<teamPromotion> obj){
        obj.sort(new Comparator<teamPromotion>() {
            @Override
            public int compare(teamPromotion o1, teamPromotion o2) {
                if(o1.getWin()<o2.getWin()){
                    return 1;
                }else if(o1.getWin()==o2.getWin() && o1.getName().compareTo(o2.getName())>0){
                    return 1;
                }else
                    return -1;
            }
        });//這裡排序以防得到的數據是亂的
    }
    public static Optional<PlayoffTeam> findTeamByName(List<PlayoffTeam> playoffTeams, String teamName) {
        return playoffTeams.stream()
                .filter(team -> team.getTeam().equals(teamName))
                .findFirst();
    }
    public static String whoWin(String left,String right,List<PlayoffTeam> data)throws Exception{
        Optional<PlayoffTeam> optionalTeam = findTeamByName(data, left);
        if (optionalTeam.isPresent()) {
            PlayoffTeam targetTeam = optionalTeam.get();
            for (int i=0;i<targetTeam.getSeries().size();i++){
                if(targetTeam.getSeries().get(i).getOpponent().equals(right)){
                    if(targetTeam.getSeries().get(i).getAdvanced()){
                        return left;
                    }
                    else return right;
                }
            }
            return null;
        }
        return null;
    }
    public static int checkReapeat(String[] obj){
        List<String> list = Arrays.asList(obj);
        for(int i=0;i<obj.length;i++){
            if(obj[i]!=null && Collections.frequency(list, obj[i]) > 1){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        ObjectMapper objectMapper =new ObjectMapper();
        SeasonData Team;
        List<teamPromotion> AlSeed = new ArrayList<>();
        List<teamPromotion> NlSeed = new ArrayList<>();
        List<teamPromotion> AlWildCard = new ArrayList<>();
        List<teamPromotion> NlWildCard = new ArrayList<>();
        String[] Post = new String[32];
        String divisionCmp=" ";
        try{
            File jsonFile=new File("src/main/java/org/example/team2023battle.json");
            if(!jsonFile.exists()){
                throw new IOException("File not found: "+jsonFile.getAbsolutePath());
            }//因為new File不會偵測錯誤 所以要在這邊先檢查檔案存不存在 並且列出絕對路徑方便除錯

            FileHandler fileHandler = new FileHandler("app.log", true); // true 表示追加到文件中
            fileHandler.setFormatter(new SimpleFormatter()); // 設定格式為簡單格式
            logger.addHandler(fileHandler);

            Team=objectMapper.readValue(jsonFile,SeasonData.class);//讀取檔案



            for(int i=0;i<Team.getTeamRecord().size();i++){
                TeamRecord obj =Team.getTeamRecord().get(i);
                if((obj.getWins()+obj.getLosses())!=162){
                    logger.warning(obj.getTeam()+"沒有打滿或超過162場比賽");
                }//沒打滿代表常規賽可能還沒打完 易出錯
                if(obj.getLeague().equals("America League"))obj.setLeague("AL");
                if(obj.getLeague().equals("Natinonal League"))obj.setLeague("NL");
                //轉換America League 成AL
                if(!(obj.getLeague().equals("AL") || obj.getLeague().equals("NL"))){
                    throw new Exception(obj.getLeague()+" is not AL or NL");
                }
            }
            for(int i=0;i<Team.getTeamRecord().size()-3;i++){
                if(!Team.getTeamRecord().get(i).getDivision().equals(divisionCmp)){
                    for (int j=0;j<3;j++){
                        if(!Team.getTeamRecord().get(j).getDivision().equals(Team.getTeamRecord().get(j+1).getDivision()))
                            throw new Exception("資料給不足或是排序錯誤");
                        if(!Team.getTeamRecord().get(j).getLeague().equals(Team.getTeamRecord().get(j+1).getLeague()))
                            throw new Exception("資料給不足或是排序錯誤");
                    }//確保給的資料是完整的沒有缺東缺西
                    if(Team.getTeamRecord().get(i).getLeague().equals("AL")){
                        AlSeed.add(new teamPromotion(Team.getTeamRecord().get(i).getTeam(),Team.getTeamRecord().get(i).getWins()));
                        AlWildCard.add(new teamPromotion(Team.getTeamRecord().get(i+1).getTeam(),Team.getTeamRecord().get(i+1).getWins()));
                        AlWildCard.add(new teamPromotion(Team.getTeamRecord().get(i+2).getTeam(),Team.getTeamRecord().get(i+2).getWins()));
                        AlWildCard.add(new teamPromotion(Team.getTeamRecord().get(i+3).getTeam(),Team.getTeamRecord().get(i+3).getWins()));
                    }else if(Team.getTeamRecord().get(i).getLeague().equals("NL")){
                        NlSeed.add(new teamPromotion(Team.getTeamRecord().get(i).getTeam(),Team.getTeamRecord().get(i).getWins()));
                        NlWildCard.add(new teamPromotion(Team.getTeamRecord().get(i+1).getTeam(),Team.getTeamRecord().get(i+1).getWins()));
                        NlWildCard.add(new teamPromotion(Team.getTeamRecord().get(i+2).getTeam(),Team.getTeamRecord().get(i+2).getWins()));
                        NlWildCard.add(new teamPromotion(Team.getTeamRecord().get(i+3).getTeam(),Team.getTeamRecord().get(i+3).getWins()));
                    }
                }
                divisionCmp=Team.getTeamRecord().get(i).getDivision();
            }

            teamsort(AlSeed);
            teamsort(AlWildCard);
            teamsort(NlSeed);
            teamsort(NlWildCard);
            Post[16]=AlWildCard.get(2).getName();
            Post[17]=AlSeed.get(2).getName();
            Post[9]=AlSeed.get(1).getName();
            Post[20]=AlWildCard.get(1).getName();
            Post[21]=AlWildCard.get(0).getName();//
            Post[11]=AlSeed.get(0).getName();

            Post[24]=NlWildCard.get(2).getName();
            Post[25]=NlSeed.get(2).getName();
            Post[13]=NlSeed.get(1).getName();//
            Post[28]=NlWildCard.get(1).getName();
            Post[29]=NlWildCard.get(0).getName();
            Post[15]=NlSeed.get(0).getName();

            int tmpcheck=checkReapeat(Post);
            if(tmpcheck!=-1){
                throw new Exception(Post[tmpcheck]+" is repeated team data");
            }//檢查如果外卡跟季後賽有任何人重複的話就報錯

            for(int i=30;i>0;i-=2){
                if(Post[i]!=null && Post[i+1]!=null){
                    Post[i/2]=whoWin(Post[i],Post[i+1],Team.getPlayoffTeams());
                    if(Post[i/2]==null)Post[i/2]="?";
                }
            }
            System.out.printf("%-3s ────┐\n        ├───%-3s──┐\n%-3s ────┘        │\n                 %-3s──┐\n      %-3s────────┘    │\n%-3s ────┐             %-3s──┐\n        ├───%-3s──┐    │    │\n%-3s ────┘        │    │    │\n                 %-3s──┘    │\n      %-3s────────┘         │\n%-3s ────┐                  %-3s\n        ├───%-3s──┐         │\n%-3s ────┘        │         │\n                 %-3s──┐    │\n      %-3s────────┘    │    │\n%-3s ────┐             %-3s──┘\n        ├───%-3s──┐    │\n%-3s ────┘        │    │\n                 %-3s──┘\n      %-3s────────┘\n",Post[16],Post[8],Post[17],Post[4],Post[9],Post[20],Post[2],Post[10],Post[21],Post[5],Post[11],Post[24],Post[1],Post[12],Post[25],Post[6],Post[13],Post[28],Post[3],Post[14],Post[29],Post[7],Post[15]);

        }catch(Exception e){
            e.printStackTrace();
        };
    }
}
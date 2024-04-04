import java.util.concurrent.TimeUnit;

public class Game {

    public static final int totalBases = 4;
    public static void main(String[] args) throws InterruptedException{
        Player[][] players = new Player[][]{{new Player("Mullins",0.243), new Player("Rutschman",0.291), 
                                        new Player("Mountcastle",0.213), new Player("Hays", 0.295), 
                                        new Player("Frazier",0.213), new Player("Santander",0.211), 
                                        new Player("Henderson",0.183)}, 
                                        {new Player("Ezra", 0.250), new Player("Elliott", 0.225), 
                                        new Player("Nick", 0.310),new Player("Jack", 0.320), 
                                        new Player("Luke", 0.100), new Player("Jaden", 0.200), 
                                        new Player("Michael", 0.190)}};
        Player[] runners = new Player[totalBases];
        int[] battingOrder = new int[]{0, 0};
        Base[] bases = new Base[]{new Base(0, -10), new Base(10, 0), new Base(0, 10), new Base(-10, 0)};
        StdDraw.setXscale(-20, 20);
        StdDraw.setYscale(-20, 20);
        int[] runs = new int[]{0, 0};
        int inning = 1;
        //inning
        while (inning < 13 || runs[0] == runs[1]){
            int d = inning % 2;
            int outs = 0;
            for (int j = 0;j < totalBases;j++){
                runners[j] = null;
            }

            //each at bat
            while (outs < 3){
                int hit = AtBat(players[d][battingOrder[d]].getAVG());
                System.out.println(players[d][battingOrder[d]].getName()+ " hit " + hit);
                if (hit == 0){
                    outs++;
                }
                else {
                    runs[d] += advanceBases(hit, players[d][battingOrder[d]], runners);
                }
                System.out.println("Outs: " + outs);
                System.out.println("Runs: " + runs[d]);
                battingOrder[d]++;
                if (battingOrder[d] >= players[d].length){
                    battingOrder[d] = 0;
                }

                for (int q = 0; q < runners.length;q++){
                    bases[q].addPlayer(runners[q]);
                    // System.out.print(q + "-" + runners[q] + ", ");
                }
                System.out.println();
    
                if (d == 1){
                    bases[0].erase();
                    bases[0].setScore(runs, outs, inning, players[d][battingOrder[d]].getName());
                    bases[0].fillBase(null);
                    Player lastBatter;
                    if(battingOrder[d] != 0){lastBatter = players[d][battingOrder[d]-1];}
                    else {lastBatter = players[d][players[d].length-1];}
                    bases[0].printHit(hit, lastBatter);
                    for (int t = 1; t < bases.length;t++){
                        bases[t].fillBase(bases[t].getRunner());
                    }
                    TimeUnit.SECONDS.sleep(2);
                }
            }
            if (d == 1){
                bases[0].oBatting();
                TimeUnit.SECONDS.sleep(1);
            }
            else {
                bases[0].nextInning();
                TimeUnit.SECONDS.sleep(1);
            }
            inning++;
        }
        bases[0].gameOver(runs);
    }
    
    public static int AtBat(double avg){
        int hit = 0;
        double random = (Math.random() / 10)*7;
        for (int i = totalBases; i > 0; i--){
            if ((avg/i) > random){
                hit = i;
                break;
            }
        }
        return hit;
    }
    //if hit is 3, puts player p on 3rd and advances runners ahead of him
    public static int advanceBases(int hit, Player p, Player[] b){
        int runs = 0;
        for (int i = totalBases-1; i > 0; i--){
            if (b[i] != null){
                // System.out.println(b[i].getName() + " is on base " + i);
                if (hit+i >= totalBases){
                    runs++;
                }else {
                    b[i+hit] = b[i];
                }
                b[i] = null;
            }
        }
        if (hit < 4 && b[hit] != p){
            b[hit] = p;
        }
        else if (hit == 4){
            runs++;
        }
        System.out.println();
        System.out.println();
        System.out.println("advanceBases");
        for (int r=0;r<b.length;r++){
            System.out.print(r+"-"+b[r]+", ");
        }
        System.out.println();
        System.out.println();
        System.out.println();
        return runs;
    }
}
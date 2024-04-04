public class Base {
    private final int x;
    private final int y;
    private Player runner;
    

    public Base(int x, int y) {
        this.x = x;
        this.y = y;
        runner = null;
        drawBase();
    }

    public void drawBase() {
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.filledSquare(x, y, 2);
            StdDraw.setPenColor(StdDraw.BLUE);
    }

    public void addPlayer(Player p){
        runner = p;
    }

    public void removePlayer(){
        runner = null;
    }

    public void erase(){
        StdDraw.setPenColor(124, 252, 150);
        StdDraw.filledSquare(0, 0, 20);
        StdDraw.setPenColor(StdDraw.BLACK);
    }

    public void oBatting(){
        erase();
        StdDraw.text(0, 0, "Orioles batting... ");
    }

    public void gameOver(int[] runs){
        erase();
        if (runs[0] > runs[1]){
            StdDraw.text(0, 0, "Orioles Win " + runs[0] + "-" + runs[1] + "  :(");
        }else {
            StdDraw.text(0, 0, "Wafflers Win " + runs[1] + "-" + runs[0] + "  :)");
        }
    }

    public void nextInning(){
        erase();
        StdDraw.text(0, 0, "Next Inning...");
    }

    public void setScore(int[] score, int outs, int inning, String batter){
        StdDraw.text(-15, 17, "Wafflers: "+ score[1]);
        StdDraw.text(-15, 15, "Orioles: "+ score[0]);
        StdDraw.text(-15, 13, "Outs: "+ outs);
        StdDraw.text(-15, 11, "Top of "+ inning/2);
        StdDraw.text(13, -15, "Next at Bat: "+ batter);
        StdDraw.rectangle(-15,14,2.5,4);
    }

    public void printHit(int hit, Player lastBatter){
        String str = lastBatter.getName() + " ";
        if (hit == 1){
            str += "Single!";
        }
        else if (hit == 2){
            str += "Double!";
        }
        else if (hit == 3){
            str += "Triple!";
        }
        else if (hit == 4){
            str += "Home Run!";
        }
        else if (hit == 0){
            str += "Strikeout :(";
        }
        StdDraw.text(0, -15, str);
    }

    public void fillBase(Player p){
        if (p == null){
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.filledSquare(x, y, 2);
        }
        else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.filledSquare(x, y, 2);
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.text(x, y+3, p.getName());
        }
    }

    public Player getRunner(){
        return runner;
    }

}

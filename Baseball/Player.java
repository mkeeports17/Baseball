public class Player {
    private String Name;
    private double AVG;

    public Player(String name, double avg){
        Name = name;
        AVG = avg;
    }

    public double getAVG(){
        return AVG;
    }

    public String getName(){
        if (Name != null){
            return Name;
        }else {
            return "null";
        }
    }

    public String toString(){
        return getName();
    }
}
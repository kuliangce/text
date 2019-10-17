package Game;

public class EX{
    private int id = 0;
    private String[] card = new String[3];
    public EX init(hand_card Hd){
        EX ex = new EX();
        ex.id = Hd.id;
        ex.card[0] = Hd.card[0];
        ex.card[1] = Hd.card[1];
        ex.card[2] = Hd.card[2];
        return ex;
    }
}
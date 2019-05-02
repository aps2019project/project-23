public class Item extends Card {

    private String desc;
    private boolean collectable;

    public Item(String name , int cost , int MP , String desc , boolean collectable){
        super(name,cost,MP);
        this.desc = desc;
        this.collectable = collectable;
    }

    public String getDesc() {
        return desc;
    }

    public boolean isCollectable() {
        return collectable;
    }

    public Card copyOfCard () {
        return null;
    }

}

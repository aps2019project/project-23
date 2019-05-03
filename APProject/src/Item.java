import java.util.ArrayList;

public class Item extends Card {

    private String desc;
    private boolean collectable;
    private static ArrayList<Item> collectableItems = new ArrayList<Item>();
    private String effect;

    public static ArrayList<Item> getCollectableItems() {
        return collectableItems;
    }

    public static void addCollectableItem (Item item) {
        collectableItems.add(item);
    }

    public Item(String name , int cost , int MP , String desc , boolean collectable , ArrayList<Buff> buffs , String effect){

        super(name,cost,MP);
        this.desc = desc;
        this.collectable = collectable;
        for (Buff buff:buffs) {
            this.buffs.add(buff);
        }
        this.effect = effect;

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

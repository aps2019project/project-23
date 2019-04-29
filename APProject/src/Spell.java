import java.util.ArrayList;

public class Spell extends Card {

    private String desc;
    private String targetCommunity;
    private int length;
    private int width;
    private ArrayList<Integer[]> cellEffect = new ArrayList<Integer[]>();

    public ArrayList<Integer[]> getCellEffect() {
        return cellEffect;
    }

    public String getDesc() {
        return desc;
    }

    public Spell(String name, String desc, String targetCommunity, int cost, int MP, int length, int width , ArrayList<Buff> buffs) {
        super(name, cost, MP);
        this.desc = desc;
        this.targetCommunity = targetCommunity;
        this.length = length;
        this.width = width;
        for (Buff buff:buffs) {
            this.buffs.add(buff);
        }
    }

    public void setNullCellEffect() {
        for (int i = 0; i < cellEffect.size(); i++) {
            cellEffect.remove(0);
        }
    }

    public boolean setAroundfriendlyMinion(String[] targetCommunitys, int x, int y) {
        boolean correct = false;
        if (!(x < 8 && x > 0 && y < 4 && y > 0))
            return false;
        if (targetCommunitys[1].matches("H") && !(Main.getCardsCell()[x][y] instanceof Hero))
            return false;
        if (targetCommunitys[2].matches("friendly") && Main.getCardsCell()[x][y].numberOfPlayer != this.numberOfPlayer)
            return false;
        if (targetCommunitys[4].matches("enemy")) {
            if (targetCommunitys[3].matches("M")) {
                int counterOfMinion = 0;
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        if (Main.getCardsCell()[x + i][y + j] instanceof Minion && Main.getCardsCell()[x + i][y + j].numberOfPlayer != this.numberOfPlayer)
                            counterOfMinion++;
                    }
                }
                if (counterOfMinion == 0)
                    correct = false;
                else {
                    correct = true;
                    int rand = (int) (Math.random() * counterOfMinion + 1);
                    for1:
                    for (int i = -1; i < 2; i++) {
                        for (int j = -1; j < 2; j++) {
                            if (Main.getCardsCell()[x + i][y + j] instanceof Minion && Main.getCardsCell()[x + i][y + j].numberOfPlayer != this.numberOfPlayer)
                                rand--;
                            if (rand == 0) {
                                Integer[] cell = {x + i, y + j};
                                cellEffect.add(cell);
                                break for1;
                            }
                        }
                    }
                }
            }
        }
        return correct;
    }

    public void setAllCell(int x, int y, String[] targetCommunitys) {
        if (targetCommunitys[2].matches("M")) {
            if (Main.getCardsCell()[x][y] instanceof Minion) {
                Integer[] cell = {x, y};
                cellEffect.add(cell);
            }
        } else if (targetCommunitys[2].matches("MH")) {
            if (Main.getCardsCell()[x][y] instanceof Minion || Main.getCardsCell()[x][y] instanceof Hero) {
                Integer[] cell = {x, y};
                cellEffect.add(cell);
            }
        } else if (targetCommunitys[2].matches("H")) {
            if (Main.getCardsCell()[x][y] instanceof Hero) {
                Integer[] cell = {x, y};
                cellEffect.add(cell);
            }
        }
    }

    public void setGeneral(String[] targetCommunitys) {
        if (targetCommunitys[1].matches("friendly")) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 5; j++) {
                    if (Main.getCardsCell()[i][j] == null)
                        continue;
                    if (Main.getCardsCell()[i][j].numberOfPlayer == this.numberOfPlayer) {
                        setAllCell(i, j, targetCommunitys);
                    }
                }
            }
        } else if (targetCommunitys[1].matches("enemy")) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 5; j++) {
                    if (Main.getCardsCell()[i][j] == null)
                        continue;
                    if (Main.getCardsCell()[i][j].numberOfPlayer != this.numberOfPlayer) {
                        setAllCell(i, j, targetCommunitys);
                    }
                }
            }
        }
    }

    public boolean generalCell(int x, int y, String[] targetCommunitys) {
        int counter = 0;
        if (targetCommunitys[1].matches("enemy")) {
            if (width == 1) {
                for (int i = 0; i < length; i++) {
                    if (Main.getCardsCell()[i][y] == null)
                        continue;
                    if (Main.getCardsCell()[i][y].numberOfPlayer != this.numberOfPlayer) {
                        counter++;
                        setAllCell(i, y, targetCommunitys);
                    }
                }
            } else {
                for (int i = 0; i < width; i++) {
                    if (Main.getCardsCell()[x][i] == null)
                        continue;
                    if (Main.getCardsCell()[x][i].numberOfPlayer != this.numberOfPlayer) {
                        counter++;
                        setAllCell(x, i, targetCommunitys);
                    }
                }
            }
        }
        if (counter == 0)
            return false;
        return true;
    }

    public boolean checkCell(int x, int y, String[] targetCommunitys) {
        if (targetCommunitys[2].matches("M"))
            if (!(Main.getCardsCell()[x][y] instanceof Minion))
                return false;
        if (targetCommunitys[2].matches("H"))
            if (!(Main.getCardsCell()[x][y] instanceof Hero))
                return false;
        if (targetCommunitys[2].matches("MH"))
            if (!(Main.getCardsCell()[x][y] instanceof Hero || Main.getCardsCell()[x][y] instanceof Minion))
                return false;
        return true;
    }

    public boolean oneCell(int x, int y, String[] targetCommunitys) {
        if (!(Main.getCardsCell()[x][y] instanceof Minion || Main.getCardsCell()[x][y] instanceof Hero))
            return false;
        if (!checkCell(x, y, targetCommunitys))
            return false;
        if (targetCommunitys[1].matches("friendly")) {
            if (Main.getCardsCell()[x][y].numberOfPlayer != this.numberOfPlayer)
                return false;
        } else if (targetCommunitys[1].matches("enemy")) {
            if (Main.getCardsCell()[x][y].numberOfPlayer == this.numberOfPlayer) {
                return false;
            }
        }
        Integer[] cell = {x, y};
        cellEffect.add(cell);
        return true;
    }

    public boolean rectangleSet(int x, int y, String[] targetCommunitys) {
        if (x + length > 9 || y + width > 5)
            return false;
        if (width == 1 && length == 1) {
            return oneCell(x, y, targetCommunitys);
        } else {
            if (targetCommunitys[1].matches("allcell")) {
                for (int i = x; i < x + length; i++) {
                    for (int j = y; j < y + width; j++) {
                        Integer[] cell = {i, j};
                        cellEffect.add(cell);
                    }
                }
            } else {
                for (int i = x; i < x + length; i++) {
                    for (int j = y; j < y + width; j++) {
                        if (Main.getCardsCell()[i][j] instanceof Minion || Main.getCardsCell()[i][j] instanceof Hero) {
                            Integer[] cell = {i, j};
                            cellEffect.add(cell);
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean setCellEffect(int x, int y) {

        setNullCellEffect();
        String[] targetCommunitys = targetCommunity.split(" ");
        if (targetCommunitys[0].matches("around"))
            return setAroundfriendlyMinion(targetCommunitys, x, y);
        else if (targetCommunitys[0].matches("allcell"))
            setGeneral(targetCommunitys);
        else if (targetCommunitys[0].matches("generalcell"))
            return generalCell(x, y, targetCommunitys);
        else if (targetCommunitys[0].matches("rectangle")) {
            return rectangleSet(x, y, targetCommunitys);
        }

        return true;
    }

}

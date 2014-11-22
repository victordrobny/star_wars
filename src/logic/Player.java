package logic;

import graphics.ViewFrame;

import java.util.ArrayList;

public class Player {
    private ArrayList<SpaceShip> ships;


    private Universe universe;
    private double gold = Properties.properties.START_GOLD;
    private double iron = Properties.properties.START_IRON;
    private int id;

    public double getGold() {
        return gold;
    }

    public double getIron() {
        return iron;
    }

    public Player(int i, Universe u) {
        ships = new ArrayList<SpaceShip>();
        universe = u;
        id = i;
        if (id == 1) {
            ships.add(new SpaceShip(-0.8, -0.8, this, IdGenerator.getNewId(), u));
            ships.add(new SpaceShip(-0.8, 0.8, this, IdGenerator.getNewId(), u));
        } else if (id == 2) {
            ships.add(new SpaceShip(0.5, 0.8, this, IdGenerator.getNewId(), u));
            ships.add(new SpaceShip(0, 0.8, this, IdGenerator.getNewId(), u));
            ships.add(new SpaceShip(0.5, 0.8, this, IdGenerator.getNewId(), u));
            ships.add(new SpaceShip(0, 0.8, this, IdGenerator.getNewId(), u));
            ships.add(new SpaceShip(0.5, 0.8, this, IdGenerator.getNewId(), u));
            ships.add(new SpaceShip(0, 0.8, this, IdGenerator.getNewId(), u));

        }
    }

    public Player(int i, Universe u, Player p) {
        ships = new ArrayList<SpaceShip>();
        universe = u;
        id = i;
        for (SpaceShip s: p.getShips()) {
            ships.add(new SpaceShip(s.getX(), s.getY(), this, 1, u));
        }
    }

    public Universe getUniverse() {
        return universe;
    }

    public SpaceShip getNearestShip(double x, double y) {
        double dist = 10;
        SpaceShip res = null;
        for (SpaceShip s: ships) {
            double d = Universe.distanse(x, y, s.getX(), s.getY());
            if (d < dist) {
                dist = d;
                res = s;
            }
        }
        return res;
    }

    public SpaceShip getShip(double x, double y) {
        SpaceShip res = getNearestShip(x, y);
        if (res != null && Universe.distanse(x, y, res.getX(), res.getY()) < Properties.properties.SHIP_RADIOUS) {
            return res;
        }
        return null;
    }

    public void addShip(SpaceShip s) {
        ships.add(s);
    }

    public boolean addGold(double g) {
        if (gold + g >= 0) {
            gold += g;
            return true;
        }
        return false;
    }

    public void removeShip(SpaceShip s) {
        ships.remove(s);
    }

    public boolean addIron(double i) {
        if (iron + i >= 0) {
            iron += i;
            return true;
        }
        return false;
    }

    public ArrayList<SpaceShip> getShips() {
        return ships;
    }

}

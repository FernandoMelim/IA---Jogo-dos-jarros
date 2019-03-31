package Operações;

import Jarro.Jarro;

public class Operacoes {

    private static final int EMPTY = 0;

    public static boolean encher(Jarro toFill) {
        if (toFill.setWater(toFill.getMaxCapacity())) {
            return true;
        }

        return false;

    }

    public static void esvaziar(Jarro toEmpty) {
        toEmpty.setWater(EMPTY);

    }

    /**
     *
     * @param source
     * @param target
     */
    public static void mover(Jarro source, Jarro target) {

        if (source.getWater() > 0 && target.getWater() < target.getMaxCapacity()) {
            int targetContent = source.getWater() + target.getWater();

            if (targetContent <= target.getMaxCapacity()) {
                target.setWater(targetContent);
                source.setWater(EMPTY);
            } else {
                target.setWater(target.getMaxCapacity());
                source.setWater(targetContent - target.getMaxCapacity());
            }
        }
    }

}

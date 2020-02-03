package de.fhdw.wip.rpntilecalculator.core.calculation;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import de.fhdw.wip.rpntilecalculator.core.model.operand.ODouble;
import de.fhdw.wip.rpntilecalculator.core.model.operand.Operand;

/*
 * Summary: Defines the arc Cosinus action.
 * Author:  Jannis Luca Keienburg
 * Date:    2020/01/04
 */

public class ArcCosinus extends Action {

    @NotNull
    private static final ArcCosinus ARC_COSINUS = new ArcCosinus();

    /*
     * Singleton for COSINUS
     * @return singleton object
     */
    @Contract(pure = true) @NotNull public static ArcCosinus getInstance() { return ARC_COSINUS; }
    private ArcCosinus() { }

    @NotNull @Override
    public Operand with(@NotNull Operand... operands) throws CalculationException {
        scopedAction = this;
        return super.with(operands);
    }

    // Calculates the arc cosinus with a given angle.
    @Contract(pure = true) @NotNull ODouble on(@NotNull ODouble angle) {
        return new ODouble(Math.acos(Math.toRadians((angle.getDouble()))));
    }
}





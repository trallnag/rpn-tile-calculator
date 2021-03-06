package de.fhdw.wip.rpntilecalculator.model.calculation;

import de.fhdw.wip.rpntilecalculator.model.operands.ODouble;
import de.fhdw.wip.rpntilecalculator.model.operands.Operand;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Contract;

/*
 * Summary: Defines the Modulo click.
 * Author:  Hendrik Falk
 */
public class Modulo extends Action {
    @NotNull private static final Modulo MODULO = new Modulo();

    @Contract(pure = true) @NotNull public static Modulo getInstance() { return MODULO; }
    private Modulo() {
        requiredNumOfOperands = new int[]{2};
    }

    @NotNull @Override
    public Operand with(@NotNull Operand... operands) throws CalculationException {
        scopedAction = this;
        return super.with(operands);
    }

    //region Integer
    //------------------------------------------------------------------------------------
    /*
     * Modulo operations. It should be noted that normally it isn't allow to modulo with floating numbers.
     * However it is possible in Java.
     * @return result of the modulo operations
     */
    @Contract(pure = true) @NotNull ODouble on(@NotNull ODouble dividend, @NotNull ODouble divisor) {
        return new ODouble(dividend.getDouble() % divisor.getDouble());
    }
}

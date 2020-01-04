package de.fhdw.wip.rpntilecalculator.core.calculation;

import de.fhdw.wip.rpntilecalculator.core.model.operand.ODouble;
import de.fhdw.wip.rpntilecalculator.core.model.operand.OFraction;
import de.fhdw.wip.rpntilecalculator.core.model.operand.OMatrix;
import de.fhdw.wip.rpntilecalculator.core.model.operand.OPolynom;
import de.fhdw.wip.rpntilecalculator.core.model.operand.OSet;
import de.fhdw.wip.rpntilecalculator.core.model.operand.OTuple;
import de.fhdw.wip.rpntilecalculator.core.model.operand.Operand;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

/*
 * Summary: Defines the Times action. Lets the user Multiplies operands.
 * Author:  Tim Schwenke
 * Date:    2020/01/04
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class Times extends Action {

    @NotNull private static final Times TIMES = new Times();

    /*
     * Singleton for TIMES
     * @return singleton object
     */
    @Contract(pure = true) @NotNull public static Times getInstance() { return TIMES; }
    private Times() { }

    /*
     * Multiplying ODouble and ODouble
     * @param operands params
     * @return product of operands
     */
    @NotNull @Override
    public Operand with(@NotNull Operand... operands) throws CalculationException {
        positionDoesNotMatter = true;
        scopedAction = this;
        return super.with(operands);
    }

    //region Double
    //------------------------------------------------------------------------------------

    /*
     * Multiplying ODouble and ODouble
     * @param oDouble1 first operand
     * @param oDouble2 second operand
     * @return product of params
     */
    @Contract(pure = true) @NotNull ODouble on(@NotNull ODouble oDouble1, @NotNull ODouble oDouble2) {
        return new ODouble(oDouble1.getDouble() * oDouble2.getDouble());
    }

    /*
     * Multiplying ODouble and oFraction
     * @param oDouble first operand
     * @param oFraction second operand
     * @return product of params
     */
    @Contract(pure = true) @NotNull ODouble on(@NotNull ODouble oDouble, @NotNull OFraction oFraction) {
        return new ODouble(oDouble.getDouble() * oFraction.getDouble());
    }

    /*
     * Multiplying ODouble and oSet
     * @param oDouble first operand
     * @param oSet second operand
     * @return product of params
     */
    @Contract(pure = true) @NotNull OSet on(@NotNull ODouble oDouble, @NotNull OSet oSet) {
        Set<Double> newSet = new HashSet<>();
        for (double d : oSet.getDoubleSet())
            newSet.add(d * oDouble.getDouble());
        return new OSet(newSet);
    }

    /*
     * Multiplying ODouble and oMatrix
     * @param oDouble first operand
     * @param oMatrix second operand
     * @return product of params
     */
    @Contract(pure = true) @NotNull OMatrix on(@NotNull ODouble oDouble, @NotNull OMatrix oMatrix) {
        return new OMatrix(oMatrix.getMatrix().scalarMultiply(oDouble.getDouble()));
    }

    /*
     * Multiplying ODouble and oPolynom
     * @param oDouble first operand
     * @param oPolynom second operand
     * @return product of params
     */
    @Contract(pure = true) @NotNull OPolynom on(@NotNull ODouble oDouble, @NotNull OPolynom oPolynom) {
        double[] d = oPolynom.getPolynom().getCoefficients();
        d[0] *= oDouble.getDouble();
        return new OPolynom(new PolynomialFunction(d));
    }

    /*
     * Multiplying ODouble and oTuple
     * @param oDouble first operand
     * @param oTuple second operand
     * @return product of params
     */
    @Contract(pure = true) @NotNull OTuple on(@NotNull ODouble oDouble, @NotNull OTuple oTuple) {
        double[] oldTuple = oTuple.getTuple();
        double[] newTuple = new double[oldTuple.length];
        for (int i = 0; i < newTuple.length; i++)
            newTuple[i] = oldTuple[i] * oDouble.getDouble();
        return new OTuple(newTuple);
    }

    //endregion

    //region Fraction
    //------------------------------------------------------------------------------------

    /*
     * Multiplying OFraction and OFraction
     * @param oFraction1 first operand
     * @param oFraction2 second operand
     * @return product of params
     */
    @Contract(pure = true) @NotNull OFraction on(@NotNull OFraction oFraction1, @NotNull OFraction oFraction2) {
        return new OFraction(oFraction1.getFraction().multiply(oFraction2.getFraction()));
    }

    /*
     * Multiplying OFraction and OSet
     * @param oFraction first operand
     * @param oSet second operand
     * @return product of params
     */
    @Contract(pure = true) @NotNull OSet on(@NotNull OFraction oFraction, @NotNull OSet oSet) {
        return on(new ODouble(oFraction.getDouble()), oSet);
    }

    /*
     * Multiplying OFraction and OMatrix
     * @param oFraction first operand
     * @param oMatrix second operand
     * @return product of params
     */
    @Contract(pure = true) @NotNull OMatrix on(@NotNull OFraction oFraction, @NotNull OMatrix oMatrix) {
        return new OMatrix(oMatrix.getMatrix().scalarAdd(oFraction.getDouble()));
    }

    /*
     * Multiplying OFraction and oPolynom
     * @param oFraction first operand
     * @param oPolynom second operand
     * @return product of params
     */
    @Contract(pure = true) @NotNull OPolynom on(@NotNull OFraction oFraction, @NotNull OPolynom oPolynom) {
        return on(new ODouble(oFraction.getDouble()), oPolynom);
    }

    /*
     * Multiplying OFraction and oTuple
     * @param oFraction first operand
     * @param oTuple second operand
     * @return product of params
     */
    @Contract(pure = true) @NotNull OTuple on(@NotNull OFraction oFraction, @NotNull OTuple oTuple) {
        return on(new ODouble(oFraction.getDouble()), oTuple);
    }

    //endregion

    /*
     * Multiplying OMatrix and OMatrix
     * @param oMatrix1 first operand
     * @param oMatrix2 second operand
     * @return product of params
     */
    @Contract(pure = true) @NotNull OMatrix on(@NotNull OMatrix oMatrix1, @NotNull OMatrix oMatrix2) {
        return new OMatrix(oMatrix1.getMatrix().multiply(oMatrix2.getMatrix()));
    }

    /*
     * Multiplying OPolynom and OPolynom
     * @param oPolynom1 first operand
     * @param oPolynom2 second operand
     * @return product of params
     */
    @Contract(pure = true) @NotNull OPolynom on(@NotNull OPolynom oPolynom1, @NotNull OPolynom oPolynom2) {
        return new OPolynom(oPolynom1.getPolynom().multiply(oPolynom2.getPolynom()));
    }

    /*
     * Multiplying OTuple and OTuple
     * @param oTuple1 first operand
     * @param oTuple2 second operand
     * @return product of params
     */
    @Contract(pure = true) @NotNull OTuple on(@NotNull OTuple oTuple1, @NotNull OTuple oTuple2) {
        double[] tuple1 = oTuple1.getTuple();
        double[] tuple2 = oTuple2.getTuple();
        double[] tupleSum = new double[tuple2.length];

        if (tuple1.length != tuple2.length)
            throw new IllegalArgumentException("Tuples must have matching size.");

        for (int i = 0; i < tuple1.length; i++)
            tupleSum[i] = tuple1[i] * tuple2[i];

        return new OTuple(tupleSum);
    }

}
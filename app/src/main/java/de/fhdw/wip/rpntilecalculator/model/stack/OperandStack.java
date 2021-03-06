package de.fhdw.wip.rpntilecalculator.model.stack;

import de.fhdw.wip.rpntilecalculator.model.operands.Operand;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/*
 * Summary: The Stack for the operands that is used for calculating
 * Author:  Jannis Luca Keienburg
 * Date:    2020/01/03 & 2020/01/04
 */
public final class OperandStack implements StackInterface<Operand> {

    private final LinkedList<Operand> linkedList = new LinkedList<>();
    private final List<Operand> listView = linkedList;
    private final Deque<Operand> dequeView = linkedList;

    @Override public void push(@NotNull Operand operand) {
        dequeView.push(operand);
    }

    @Override public void push(@NotNull Operand[] operands) {
        for (Operand value : operands) dequeView.push(value);
    }

    @Override public @Nullable Operand pop() {
        try { return dequeView.pop(); }
        catch (RuntimeException e) { return null; }
    }

    @Override public @NotNull List<Operand> pop(int max) {
        List<Operand> list = new ArrayList<>();
        while (!linkedList.isEmpty() && list.size() < max)
            list.add(dequeView.pop());
        return list;
    }

    @Override public @Nullable <G extends Operand> G pop(Class<G> type) {
        for (int i = 0; i < listView.size(); i++) {
            if (type.isInstance(listView.get(i)))
                return type.cast(linkedList.remove(i));
        }
        return null;
    }

    @Override public @NotNull <G extends Operand> List<G> pop(int max, Class<G> type) {
        List<G> list = new ArrayList<>();
        for (int i = 0; i < linkedList.size(); i++) {
            if (list.size() < max && type.isInstance(linkedList.get(i))) {
                list.add(type.cast(linkedList.remove(i--)));
            }
        }
        return list;
    }

    @Override public @Nullable Operand peek() {
        try { return dequeView.peek(); }
        catch (RuntimeException e) { return null; }
    }

    @Override public @NotNull List<Operand> peek(int max) {
        List<Operand> list = new ArrayList<>();
        for (int i = 0; i < listView.size(); i++)
            if (list.size() < max)
                list.add(listView.get(i));
        return list;
    }

    @Override public @Nullable <G extends Operand> G peek(Class<G> type) {
        for (int i = 0; i < listView.size(); i++) {
            Operand operand = listView.get(i);
            if (type.isInstance(operand))
                return type.cast(operand);
        }
        return null;
    }

    @Override public @NotNull <G extends Operand> List<G> peek(int max, Class<G> type) {
        List<G> list = new ArrayList<>();
        for (int i = 0; i < listView.size(); i++) {
            Operand operand = listView.get(i);
            if (list.size() < max && type.isInstance(operand))
                list.add(type.cast(operand));
        }
        return list;
    }

    @Override public boolean contains(Operand object) {
        return linkedList.contains(object);
    }

    @Override public void clear() {
        linkedList.clear();
    }

    @NotNull @Override public Operand[] get() {
        return linkedList.toArray(new Operand[0]);
    }

    @NotNull @Override public <G extends Operand> List<G> get(Class<G> type) {
        List<G> list = new ArrayList<>();
        for (Operand operand : listView)
            if (type.isInstance(operand))
                list.add(type.cast(operand));
        return list;
    }

    @Override public int size() {
        return linkedList.size();
    }

    public void print() {
        for (int i = 0; i < listView.size(); i++)
            System.out.println(i + ": " + listView.get(i));
    }

}

package de.fhdw.wip.rpntilecalculator.view.layout.schemes;

/*
 * Summary: TileScheme contains the information about tiles and is used to create tiles and save them
 * Author:  Tom Bockhorn
 */


import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import de.fhdw.wip.rpntilecalculator.R;
import de.fhdw.wip.rpntilecalculator.model.operands.Operand;
import de.fhdw.wip.rpntilecalculator.view.TileMapping;
import de.fhdw.wip.rpntilecalculator.view.layout.TileLayoutFactory;

public abstract class TileScheme {

    @NotNull private TileMapping tileType;
    @NotNull private String content;

    TileScheme(@NotNull TileMapping tileType, @NotNull String content) {
        this.tileType = tileType;
        this.content = content;
    }

    /**
     * Factory method that creates a TileScheme depending on the type
     * @param tileType exact type of the scheme (determines the subclass)
     * @param content content
     * @return Type of TileScheme that inherits TileScheme.class
     */
    public static TileScheme createTileScheme(@NotNull TileMapping tileType, @Nullable String content) {
        if(tileType.getType().isAction()) { return new ActionTileScheme(tileType, content); }
        else if(tileType.getType().isOperand()) { return new OperandTileScheme(tileType, content); }
        else if(tileType.getType().isStack()) { return new StackTileScheme(content); }
        else if(tileType.getType().isSetting()) {return new SettingTileScheme(tileType, content); }
        else {return null;}
    }

    public static TileScheme createTileScheme(@NotNull TileMapping tileType, @Nullable Operand operand, int rank) {
        if(tileType.getType().isStack()) {
            if(operand != null) return new StackTileScheme(operand, rank);
            else return new StackTileScheme(rank);
        }
        else if(tileType.getType().isOperand()) {
            assert operand != null;
            return new OperandTileScheme(tileType, operand);
        }
        else {return null;}
    }

    // gives back a drawable resource
    public int getStyle() {
        return tileType == TileMapping.X_ERROR ? R.drawable.tile_error : tileType.getType().getStyle();
    }

    @NotNull
    public TileMapping getTileType() {
        return tileType;
    }

    public void setTileType(@NotNull TileMapping tileType) {
        this.tileType = tileType;
    }

    @NotNull
    public String getContent() {
        return content;
    }

    public void setContent(@NotNull String content) {
        this.content = content;
    }

    @NonNull
    @Override
    public String toString() {
        //O_DOUBLE;20
        //A_MINUS;-
        return tileType.toString() + TileLayoutFactory.VALUE_SEPERATOR + content;
    }
}

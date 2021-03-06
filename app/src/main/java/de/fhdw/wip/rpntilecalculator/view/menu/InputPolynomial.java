package de.fhdw.wip.rpntilecalculator.view.menu;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import de.fhdw.wip.rpntilecalculator.view.MainActivity;
import de.fhdw.wip.rpntilecalculator.R;
import de.fhdw.wip.rpntilecalculator.model.operands.OPolynom;
import de.fhdw.wip.rpntilecalculator.view.Tile;
import de.fhdw.wip.rpntilecalculator.view.TileMapping;
import de.fhdw.wip.rpntilecalculator.view.schemes.TileScheme;

/**
 * Summary: Input class for inputting polynomial functions
 * Author:  Dennis Gentges
 * Date:    2020/01/30
 */
public class InputPolynomial extends DialogMenu {

    private Button confirmButton = this.dialog.findViewById(R.id.enterButton2);
    private EditText coefficient_0_Txt = this.dialog.findViewById(R.id.coefficient_0);
    private EditText coefficient_1_Txt = this.dialog.findViewById(R.id.coefficient_1);
    private EditText coefficient_2_Txt = this.dialog.findViewById(R.id.coefficient_2);

    public InputPolynomial(final MainActivity context, Tile displayTile, DialogMenu last)
    {
        super(context, displayTile, last);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    double coefficient_0 = Double.parseDouble(coefficient_0_Txt.getText().toString());
                    double coefficient_1 = Double.parseDouble(coefficient_1_Txt.getText().toString());
                    double coefficient_2 = Double.parseDouble(coefficient_2_Txt.getText().toString());

                    double[] coefficients = new double[]{coefficient_0, coefficient_1, coefficient_2};
                    OPolynom oPolynom = new OPolynom(coefficients);
                    TileScheme newTileScheme = TileScheme.createTileScheme(TileMapping.O_POLYNOM, oPolynom, 0);
                    tile.update(newTileScheme);
                    tile.getTileLayout().removeFromStacks(tile);
                    dismissAll();
                }catch (Exception e)
                {
                    Toast.makeText(context, "Please check your input", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    protected void setContentView() {
        contentView = R.layout.input_polynomial;
    }
}

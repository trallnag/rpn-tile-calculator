package de.fhdw.wip.rpntilecalculator.view.menu;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import de.fhdw.wip.rpntilecalculator.MainActivity;
import de.fhdw.wip.rpntilecalculator.R;
import de.fhdw.wip.rpntilecalculator.model.operands.OFraction;
import de.fhdw.wip.rpntilecalculator.view.Tile;
import de.fhdw.wip.rpntilecalculator.view.TileMapping;
import de.fhdw.wip.rpntilecalculator.view.layout.schemes.TileScheme;

public class InputFraction extends DialogMenu {

    private Button confirmButton = this.dialog.findViewById(R.id.enterButton);
    private EditText numeratorText = this.dialog.findViewById(R.id.numerator);
    private EditText denumeratorText = this.dialog.findViewById(R.id.denumerator);

   public InputFraction(MainActivity context, int windowFeature, Tile tileOutside)
   {
       super(context, windowFeature, tileOutside);
       confirmButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               int nummerator = Integer.parseInt(numeratorText.getText().toString());
               int denumerator = Integer.parseInt(denumeratorText.getText().toString());
               OFraction oFraction = new OFraction(nummerator, denumerator);
               TileScheme newTileScheme = TileScheme.createTileScheme(TileMapping.O_FRACTION, oFraction, 0);
               tile.update(newTileScheme);
               tile.setBackgroundResource(R.drawable.tile_operand_white);
               tile.getTileLayout().removeFromHistoryStack(tile);
               dialog.dismiss();
           }
       });
   }

    @Override
    protected void setContentView() {
        contentView = R.layout.input_fraction;
    }
}

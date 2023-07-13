package com.tbl.problemsolvers;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.github.angads25.toggle.interfaces.OnToggledListener;
import com.github.angads25.toggle.model.ToggleableView;
import com.github.angads25.toggle.widget.LabeledSwitch;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.List;

public class ChipsAndToggle extends AppCompatActivity {

    SwitchCompat switchCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chips_and_toggle);


        ChipGroup chipGroup = findViewById(R.id.chip_group_filter);


        chipGroup.setOnCheckedStateChangeListener(new ChipGroup.OnCheckedStateChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull ChipGroup group, @NonNull List<Integer> checkedIds) {

                Log.e("chip", "ddd");
                List<Integer> ids = group.getCheckedChipIds();

                for (Integer id : ids) {
                    Chip chip = group.findViewById(id);
                    Toast.makeText(ChipsAndToggle.this, chip.getText(), Toast.LENGTH_SHORT).show();
                }

            }
        });


        ChipGroup chipMultiple = findViewById(R.id.chip_group_multiple);

        chipMultiple.setOnCheckedStateChangeListener(new ChipGroup.OnCheckedStateChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull ChipGroup group, @NonNull List<Integer> checkedIds) {

                Log.e("chip", "ddd");
                List<Integer> ids = group.getCheckedChipIds();

                for (Integer id : checkedIds) {
                    Chip chip = group.findViewById(id);
                    chip.setChecked(true);
                    Toast.makeText(ChipsAndToggle.this, chip.getText(), Toast.LENGTH_SHORT).show();
                }

            }
        });



        switchCompat = findViewById(R.id.swOnOff);

        switchCompat.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                if (switchCompat.isChecked()) {
                    Toast.makeText(ChipsAndToggle.this, "Switch to On!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ChipsAndToggle.this, "Switch to Off!", Toast.LENGTH_SHORT).show();


                }

            }

        });


        LabeledSwitch labeledSwitch = findViewById(R.id.sw);
        labeledSwitch.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(ToggleableView toggleableView, boolean isOn) {
                if (isOn) {
                    Toast.makeText(ChipsAndToggle.this, "Switch to On!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ChipsAndToggle.this, "Switch to Off!", Toast.LENGTH_SHORT).show();


                }
            }

        });
    }
}
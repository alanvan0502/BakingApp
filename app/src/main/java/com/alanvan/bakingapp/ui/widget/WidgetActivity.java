package com.alanvan.bakingapp.ui.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatRadioButton;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alanvan.bakingapp.R;
import com.alanvan.bakingapp.databinding.ActivityWidgetBinding;
import com.alanvan.bakingapp.utils.RxUtils;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class WidgetActivity extends AppCompatActivity {

    private int appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
    private WidgetDataProvider widgetDataProvider = new WidgetDataProvider();
    private CompositeDisposable bag = new CompositeDisposable();

    private RadioGroup recipeNamesRG;
    private Button selectButton;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget);

        ActivityWidgetBinding activityWidgetBinding = DataBindingUtil.setContentView(this, R.layout.activity_widget);
        recipeNamesRG = activityWidgetBinding.radioGroup;
        selectButton = activityWidgetBinding.button;
        textView = activityWidgetBinding.textView;

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null) {
            appWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID
            );

            if (appWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
                finish();
            }
        }

        Disposable d = widgetDataProvider.getRecipeIdsAndNames().compose(RxUtils.applyIOSchedulers()).subscribe(result -> {

            for (int i = 0; i < result.size(); i++) {
                Integer recipeId = result.keyAt(i);
                String recipeName = result.get(recipeId);
                widgetDataProvider.saveRecipeIdAndNameToPreferences(appWidgetId, recipeId, recipeName);

                AppCompatRadioButton button = new AppCompatRadioButton(this);
                button.setText(recipeName);
                button.setId(recipeId);
                recipeNamesRG.addView(button);
            }

            setSelectButtonClick();
        });

        bag.add(d);
    }

    private void setSelectButtonClick() {
        selectButton.setOnClickListener(v -> {
            Integer id = recipeNamesRG.getCheckedRadioButtonId();
            String recipeName = ((AppCompatRadioButton) recipeNamesRG.getChildAt(id - 1)).getText().toString();

            Context context = getApplicationContext();
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

            Disposable d = widgetDataProvider.getIngredientList(id)
                    .subscribe(
                            ingredients -> WidgetProvider.updateAppWidget(context, appWidgetManager, appWidgetId, recipeName, ingredients),
                            error -> { });

            bag.add(d);

            Intent resultValue = new Intent();
            resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            setResult(RESULT_OK, resultValue);
            finish();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bag.dispose();
    }
}

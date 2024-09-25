package androidsamples.java.dicegames;

import static android.widget.Toast.*;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import androidx.appcompat.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class WalletActivity extends AppCompatActivity {

  private TextView mTxtBalance;
  private Button mBtnDie;
  private TextView mPrevRollBalance;
  private TextView mSixesRolled;
  private TextView mTotalRolls;
  private TextView mDoubleSixes;
  private TextView mDoubleOthers;
  private static final String TAG = "WalletActivity";
  private WalletViewModel mWalletVM;



  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

//    Log.d(TAG, "onCreate");
    setContentView(R.layout.activity_wallet);
//    mDie = new Die6();

    mTxtBalance = findViewById(R.id.txt_balance);
    mBtnDie = findViewById(R.id.btn_die);
    mPrevRollBalance = findViewById(R.id.prev_roll_value);
    mSixesRolled = findViewById(R.id.sixes_rolled_value);
    mTotalRolls = findViewById(R.id.total_dice_rolls_value);
    mDoubleSixes = findViewById(R.id.double_sixes_rolled_value);
    mDoubleOthers = findViewById(R.id.double_others_rolled_value);
    ImageButton settingsButton = (ImageButton)findViewById(R.id.btn_settings);

    mWalletVM = new ViewModelProvider(this).get(WalletViewModel.class);
    updateUI();


//    if(savedInstanceState != null){
//      mBalance = savedInstanceState.getInt(KEY_BALANCE, 0);
//      int dieValue = savedInstanceState.getInt(KEY_DIE_VALUE, 0);
//      mTxtBalance.setText(Integer.toString(mBalance));
//      mBtnDie.setText(Integer.toString(dieValue));
//      Log.d(TAG, "Restored Balance = " + mBalance + ", die = " + dieValue);
//    }

    mBtnDie.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View view){
        mWalletVM.rollDie();
        if(mWalletVM.isWin()) Toast.makeText(WalletActivity.this, "Congratulations!", LENGTH_SHORT).show();
        updateUI();
      }
    });

    settingsButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // Change background color to yellow
        showSettingsOptionsDialog();
      }
    });
  }

  private void showSettingsOptionsDialog(){
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("Settings");

    String[] settingsOptions = {"Change Background Color"};

    builder.setItems(settingsOptions, new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {
        if(i==0){
          showColorOptionsDialog();
        }
      }
    });

    AlertDialog dialog = builder.create();
    dialog.show();
  }

  private void showColorOptionsDialog(){
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("Choose a background color");

    String[] colors = {"Yellow", "Default (White)"};

    builder.setItems(colors, new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {
        View mainLayout = findViewById(R.id.main_layout);

        if(i==0){
          mainLayout.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
        } else if (i == 1) {
          mainLayout.setBackgroundColor(Color.WHITE);
        }
      }
    });

    AlertDialog dialog = builder.create();
    dialog.show();
  }

  private void updateUI() {
    mTxtBalance.setText(Integer.toString(mWalletVM.balance()));
    mBtnDie.setText(Integer.toString(mWalletVM.dieValue()));
    mPrevRollBalance.setText(Integer.toString(mWalletVM.previousRoll()));
    mSixesRolled.setText(Integer.toString(mWalletVM.singleSixes()));
    mTotalRolls.setText(Integer.toString(mWalletVM.totalRolls()));
    mDoubleSixes.setText(Integer.toString(mWalletVM.doubleSixes()));
    mDoubleOthers.setText(Integer.toString(mWalletVM.doubleOthers()));
  }

//  @Override
//  protected void onSaveInstanceState(@NonNull Bundle outState){
//    super.onSaveInstanceState(outState);
//    Log.d(TAG, "onSaveInstanceState");
//    outState.putInt(KEY_BALANCE, mBalance);
//    outState.putInt(KEY_DIE_VALUE, mDie.value());
//    Log.d(TAG, "Saved: balance = " + mBalance + ", die = " + mDie.value());
//  }


  @Override
  protected void onStart() {
    super.onStart();
//    Log.d(TAG, "onStart");
  }

  @Override
  protected void onResume() {
    super.onResume();
//    Log.d(TAG, "onResume");
  }

  @Override
  protected void onStop() {
    super.onStop();
//    Log.d(TAG, "onStop");
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
//    Log.d(TAG, "onDestroy");
  }

  @Override
  protected void onPause() {
    super.onPause();
//    Log.d(TAG, "onPause");
  }
  
}


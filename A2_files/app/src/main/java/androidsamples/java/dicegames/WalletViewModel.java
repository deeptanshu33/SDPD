package androidsamples.java.dicegames;

import static android.widget.Toast.LENGTH_SHORT;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

public class WalletViewModel extends ViewModel {
  int WIN_VALUE = 6;
  int INCREMENT = 5;
  int INCREMENT_DOUBLE = 10;
  int DECREMENT = 5;
  private int rolls = 0;
  private int prev_roll = 0;
  private int curr_roll = 0;
  private int single_sixes = 0;
  private int double_sixes = 0;
  private int double_others = 0;
  private final String TAG = "WalletViewModel";
  private int mBalance;
  Die mDie;

  public WalletViewModel() {
    mBalance = 0;
    mDie = new Die6();
  }

  /**
   * Reports the current balance.
   *
   */
  public int balance() {
    return mBalance;
  }

  /**
   * Rolls the {@link Die} in the wallet and implements the changes accordingly.
   */
  public void rollDie() {
    //roll
    rolls++;
    mDie.roll();
//    Log.d(TAG, "Die roll = " + mDie.value());
    //add coins if win_value rolled
    prev_roll = curr_roll;
    curr_roll = mDie.value();
    if(mDie.value() == WIN_VALUE){
      if(prev_roll == WIN_VALUE){
//        Log.d(TAG, "Double Sixes!");
        double_sixes++;
        mBalance += INCREMENT_DOUBLE;
//        Log.d(TAG, "New Balance = " + mBalance);
      }
      else{
        single_sixes++;
        mBalance += INCREMENT;
//        Log.d(TAG, "New Balance = " + mBalance);
      }
    }
    else{
      if(prev_roll == curr_roll){
//        Log.d(TAG, "Double others!");
        double_others++;
        mBalance -= DECREMENT;
//        Log.d(TAG, "New Balance = " + mBalance);
      }
    }
  }

  public boolean isWin(){
    return mDie.value() == WIN_VALUE;
  }

  /**
   * Reports the current value of the {@link Die}.
   *
   */
  public int dieValue() {
    return mDie.value();
  }

  /**
   * Reports the number of single (or first) sixes rolled so far.
   *
   */
  public int singleSixes() {
    return single_sixes;
  }

  /**
   * Reports the total number of dice rolls so far.
   *
   */
  public int totalRolls() {
    return rolls;
  }

  /**
   * Reports the number of times two sixes were rolled in a row.
   *
   */
  public int doubleSixes() {
    return double_sixes;
  }

  /**
   * Reports the number of times any other number was rolled twice in a row.
   *
   */
  public int doubleOthers() {
    return double_others;
  }

  /**
   * Reports the value of the die on the previous roll.
   *
   */
  public int previousRoll() {
//    Log.d(TAG, "Previous Die roll = " + prev_roll);
    return prev_roll;
  }

  public void setBalance(int balance){
    mBalance = balance;
  }

  @Override
  protected void onCleared() {
    super.onCleared();
//    Log.d(TAG, "onCleared");
  }
}

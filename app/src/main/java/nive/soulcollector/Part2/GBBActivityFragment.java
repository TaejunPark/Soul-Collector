package nive.soulcollector.Part2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import nive.soulcollector.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class GBBActivityFragment extends Fragment implements View.OnClickListener {
    static final String TAG = "MainActivityFragment";
    static final int NORMAL_TICK_MILLI_SEC = 800;
    static final int LONG_TICK_MILLI_SEC = 1600;


    static final int GBB_NULL = 0;
    static final int GBB_GA = 1;
    static final int GBB_BA = 2;
    static final int GBB_BO = 3;

    static final int STATE_0 = 0;
    static final int STATE_1 = 1;
    static final int STATE_2 = 2;
    static final int STATE_3_1 = 3;
    static final int STATE_3_2 = 4;

    static final int MSG_TICK = 0;

    static final int RESULT_NULL = -1;
    static final int RESULT_DRAW = 0;
    static final int RESULT_WIN = 1;
    static final int RESULT_LOSE = 2;


    int currentGBB = GBB_NULL;
    int enemyGBB = GBB_NULL;
    int gbbCounter = 0;

    int gbbResult = RESULT_NULL;

    View currentView;

    public GBBActivityFragment() {
    }

    void init(View viewInput) {
        (viewInput.findViewById(R.id.ggb_start_button)).setOnClickListener(this);
        (viewInput.findViewById(R.id.ggb_ga)).setOnClickListener(this);
        (viewInput.findViewById(R.id.ggb_ba)).setOnClickListener(this);
        (viewInput.findViewById(R.id.ggb_bo)).setOnClickListener(this);
        (viewInput.findViewById(R.id.ggb_finish1_button)).setOnClickListener(this);
//        (viewInput.findViewById(R.id.ggb_finish2_button)).setOnClickListener(this);

         gbbCounter = 0;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        currentView = inflater.inflate(R.layout.fragment_ggb, container, false);
        init(currentView);
        setGameState(currentView, STATE_0);
        return currentView;
    }

    void setGameState(View view, int stateInput) {
        view.findViewById(R.id.ggb_text_self).setVisibility(View.INVISIBLE);
        view.findViewById(R.id.ggb_ggb_buttons).setVisibility(View.INVISIBLE);
        view.findViewById(R.id.ggb_top_text).setVisibility(View.INVISIBLE);
        view.findViewById(R.id.ggb_start_button).setVisibility(View.INVISIBLE);
        view.findViewById(R.id.ggb_finish_buttons).setVisibility(View.INVISIBLE);

        Log.d(TAG, "state: " + stateInput);

        switch (stateInput) {
            case STATE_0:
                view.findViewById(R.id.ggb_start_button).setVisibility(View.VISIBLE);
                break;
            case STATE_1:
                view.findViewById(R.id.ggb_top_text).setVisibility(View.VISIBLE);
                tickHandler.sendEmptyMessageDelayed(MSG_TICK, NORMAL_TICK_MILLI_SEC);
                break;
            case STATE_2:
                checkGBBResult();
                view.findViewById(R.id.ggb_top_text).setVisibility(View.VISIBLE);
                view.findViewById(R.id.ggb_text_self).setVisibility(View.VISIBLE);
                ((TextView)(view.findViewById(R.id.ggb_text_self))).setText("당신 : " +  getGBB(currentGBB));
                 break;
            case STATE_3_1:
                view.findViewById(R.id.ggb_top_text).setVisibility(View.VISIBLE);
                view.findViewById(R.id.ggb_finish_buttons).setVisibility(View.VISIBLE);
                setResult();
                break;
            case STATE_3_2:
                view.findViewById(R.id.ggb_top_text).setVisibility(View.VISIBLE);
                view.findViewById(R.id.ggb_finish_buttons).setVisibility(View.VISIBLE);
//                view.findViewById(R.id.ggb_start_button).setVisibility(View.VISIBLE);
                setResult();
                break;
        }
    }

    void setResult(){
        if(gbbResult==RESULT_WIN){
            ((TextView)(getView().findViewById(R.id.ggb_top_text))).setText("WIN");
        }else if(gbbResult==RESULT_LOSE){
            ((TextView)(getView().findViewById(R.id.ggb_top_text))).setText("LOSE");
        } else if (gbbResult == RESULT_DRAW){
            ((TextView)(getView().findViewById(R.id.ggb_top_text))).setText("DRAW");
        }
     }

    Handler tickHandler;

    {
        tickHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == MSG_TICK) {
                    switch (gbbCounter) {
                        case 0: //saying 가위
                            currentView.findViewById(R.id.ggb_ggb_buttons).setVisibility(View.VISIBLE);
                            ((TextView) (currentView.findViewById(R.id.ggb_top_text))).setText("가위");
                            sendEmptyMessageDelayed(MSG_TICK, NORMAL_TICK_MILLI_SEC);
                            gbbCounter++;
                            break;
                        case 1: //saying 바위
                            ((TextView) (currentView.findViewById(R.id.ggb_top_text))).setText("바위");
                            sendEmptyMessageDelayed(MSG_TICK, NORMAL_TICK_MILLI_SEC);
                            gbbCounter++;
                            break;
                        case 2: //saying 보
                            ((TextView) (currentView.findViewById(R.id.ggb_top_text))).setText("보");
//                            setGameState(getView(), STATE_2);
                            gbbCounter++;
                            tickHandler.sendEmptyMessageDelayed(MSG_TICK, NORMAL_TICK_MILLI_SEC);
                            break;
                        case 3: //result
                            setGameState(getView(), STATE_2);

                            ((TextView) (currentView.findViewById(R.id.ggb_top_text))).setText(((GBBActivity)getActivity()).name+" :" + getGBB(enemyGBB));
                            gbbCounter++;
                            tickHandler.sendEmptyMessageDelayed(MSG_TICK, LONG_TICK_MILLI_SEC);
                            break;
                        case 4:
                            ((TextView) (currentView.findViewById(R.id.ggb_top_text))).setText(getGBB(enemyGBB));
                            ((TextView) (currentView.findViewById(R.id.ggb_text_self))).setText(getGBB(currentGBB));

                            Log.d(TAG, gbbResult + " " + getGBB(currentGBB )+ " " + getGBB(enemyGBB));
                            if(gbbResult==RESULT_LOSE ){
                                ((Button)(getView().findViewById(R.id.ggb_finish1_button))).setText("실패다...");
                                setGameState(getView(), STATE_3_2);
                            } else if(gbbResult == RESULT_DRAW){
                                ((Button)(getView().findViewById(R.id.ggb_finish1_button))).setText("놓쳤다..");
                                setGameState(getView(), STATE_3_2);
                            } else if(gbbResult==RESULT_WIN){
                                ((Button)(getView().findViewById(R.id.ggb_finish1_button))).setText("잡았다!");
                                setGameState(getView(), STATE_3_1);
                            }
                            gbbCounter =0;
                            break;
                    }
                }
                super.handleMessage(msg);
            }
        };
    }

    String getGBB(int gbbNumber){
        switch (gbbNumber){
            case GBB_GA:
                return "가위";
             case GBB_BA:
                return "바위";
             case GBB_BO:
                return "보";
         }
        return "안냄";
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ggb_start_button:
                setGameState(getView(), STATE_1);
                break;
            case R.id.ggb_ga:
                currentGBB = GBB_GA;
                hideGBBButtons();
                break;
            case R.id.ggb_ba:
                currentGBB = GBB_BA;
                hideGBBButtons();
                break;
            case R.id.ggb_bo:
                currentGBB = GBB_BO;
                hideGBBButtons();
                break;
            case R.id.ggb_finish1_button:
                getActivity().finish();
                Intent intent = new Intent(getActivity(), GhostPrisonActivity.class);
                startActivity(intent);
                break;
//            case R.id.ggb_finish2_button:
//                Intent intent2 = new Intent( getContext(), GhostStatusActivity.class);
//                startActivity(intent2);
//                break;
        }
    }

    void checkGBBResult(){
        int randomNumber = (new Random().nextInt(3) ) + 1;
        switch (randomNumber){
            case GBB_GA:
                enemyGBB = GBB_GA;
                break;
            case GBB_BA:
                enemyGBB = GBB_BA;
                break;
            case GBB_BO:
                enemyGBB = GBB_BO;
                break;
        }
        Log.d(TAG, "enemyGBB: " + enemyGBB + "  " + randomNumber);

        gbbResult = checkGBBMatch(currentGBB, enemyGBB);

    }

    int checkGBBMatch(int user, int enemy){
        switch (user){
            case GBB_GA:
                switch(enemy){
                    case GBB_GA:
                        return RESULT_DRAW;
                    case GBB_BA:
                        return RESULT_LOSE;
                    case GBB_BO:
                        return RESULT_WIN;
                }
                break;
            case GBB_BA:
                switch(enemy){
                    case GBB_GA:
                        return RESULT_WIN;
                    case GBB_BA:
                        return RESULT_DRAW;
                    case GBB_BO:
                        return RESULT_LOSE;
                }
                break;
            case GBB_BO:
                switch(enemy){
                    case GBB_GA:
                        return RESULT_LOSE;
                    case GBB_BA:
                        return RESULT_WIN;
                    case GBB_BO:
                        return RESULT_DRAW;
                }
                break;
        }
        return RESULT_LOSE;
    }

    void hideGBBButtons() {
        currentView.findViewById(R.id.ggb_ggb_buttons).setVisibility(View.INVISIBLE);
     }
}

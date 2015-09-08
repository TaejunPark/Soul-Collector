package nive.soulcollector.Part2;

import nive.soulcollector.R;

/**
 * Created by tj on 15. 8. 29..
 */
public class Ghost {

    static final int GHOST_GIRL_1 = R.drawable.soul_keep_girl1;
    static final int GHOST_GIRL_3 = R.drawable.soul_keep_girl3;
    static final int GHOST_GRIM_1 = R.drawable.soul_keep_grim1;
    static final int GHOST_GRIM_2 = R.drawable.soul_keep_grim2;
    static final int GHOST_SOUL_1 = R.drawable.soul_keep_soul1;
    static final int GHOST_SOUL_2 = R.drawable.soul_keep_soul2;

    static final String NAME_GIRL ="처녀귀신";
    static final String NAME_GRIM ="저승사자";
    static final String NAME_SOUL= "도깨비 불";

    static final String SCRIPT_WIN_GIRL = "여자라서 행복해요~";
    static final String SCRIPT_WIN_GRIM = "저승사자도 성불할수 있어!";
    static final String SCRIPT_WIN_SOUL= "다음생애엔 교수님으로 태어나고 싶다";

    static final String SCRIPT_LOSE_GIRL = "원빈을 데려와!";
    static final String SCRIPT_LOSE_GRIM= "니놈 늙으면 내가 데려갈거다!";
    static final String SCRIPT_LOSE_SOUL= "원통합니다!";


    String name;
    String scriptWin;
    String scriptLose;
    int kind;
    boolean isCaptured;
    int power;

    public Ghost(int kindInput, boolean isCaptured) {
         power = kindInput;
        switch(kindInput){
            case 0:
                kind = GHOST_GIRL_1;
                name = NAME_GIRL;
                scriptWin = SCRIPT_WIN_GIRL;
                scriptLose = SCRIPT_LOSE_GIRL;
                break;
            case 1:
                kind = GHOST_GIRL_3;
                name = NAME_GIRL;
                scriptWin = SCRIPT_WIN_GIRL;
                scriptLose = SCRIPT_LOSE_GIRL;
                break;
            case 2:
                kind = GHOST_GRIM_1;
                name = NAME_GRIM;
                scriptWin = SCRIPT_WIN_GRIM;
                scriptLose = SCRIPT_LOSE_GRIM;
                break;
            case 3:
                kind = GHOST_GRIM_2;
                name = NAME_GRIM;
                scriptWin = SCRIPT_WIN_GRIM;
                scriptLose = SCRIPT_LOSE_GRIM;
                break;
            case 4:
                kind = GHOST_SOUL_1;
                name = NAME_SOUL;
                scriptWin = SCRIPT_WIN_SOUL;
                scriptLose = SCRIPT_LOSE_SOUL;
                break;
            case 5:
                kind = GHOST_SOUL_2;
                name = NAME_SOUL;
                scriptWin = SCRIPT_WIN_SOUL;
                scriptLose = SCRIPT_LOSE_SOUL;
                break;
        }
        this.isCaptured = isCaptured;
     }
}
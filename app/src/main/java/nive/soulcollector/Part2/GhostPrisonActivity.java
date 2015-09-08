package nive.soulcollector.Part2;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import nive.soulcollector.Part1.Rader.NMapViewer;
import nive.soulcollector.R;

public class GhostPrisonActivity extends ListActivity {
    ArrayList<GhostLine> ghostList;
    MHArrayAdapter adapter;
    List<View> views;
    private MenuItem map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghost_prison);
        init();
    }

    void init() {
        views = new ArrayList<>();
        ghostList = new ArrayList<>();

        adapter = new MHArrayAdapter(this, android.R.layout.simple_list_item_1, ghostList);
        setListAdapter(adapter);


        Random rand = new Random();

        for (int i = 0; i < 2; i++) {
            ghostList.add(new GhostLine(new Ghost(rand.nextInt(3), rand.nextBoolean()), new Ghost(rand.nextInt(3), rand.nextBoolean())));
        }
    }

    class MHArrayAdapter extends ArrayAdapter<GhostLine> {
        public MHArrayAdapter(Context context, int textViewResourceId, List<GhostLine> objects) {
            super(context, textViewResourceId, objects);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi;
                vi = LayoutInflater.from(getContext());
                v = vi.inflate(R.layout.ghost_room_half, null);

                GhostLine ghosts = getItem(position);
                Ghost left = ghosts.getLeft();
                Ghost right = ghosts.getRight();

                if (left != null) {
                    ((ImageView) (v.findViewById(R.id.half_left_ghost))).setImageResource(left.kind);
                    ((TextView) (v.findViewById(R.id.half_left_text))).setText(left.name);
                    ProgressBar pb = ((ProgressBar) (v.findViewById(R.id.half_left_health)));
                    pb.setProgress(left.power + 1);
                    View lv = v.findViewById(R.id.half_left);

                    views.add(lv);

                    if (left.isCaptured) {
                        lv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int index = views.indexOf(v) / 2;
                                Intent intent = new Intent(getContext(), GhostStatusActivity.class);
                                intent.putExtra(GhostStatusActivity.GHOST_KIND, getItem(index).getLeft().kind);
                                intent.putExtra(GhostStatusActivity.GHOST_NAME, getItem(index).getLeft().name);
                                intent.putExtra(GhostStatusActivity.GHOST_POWER, getItem(index).getLeft().power);
                                intent.putExtra(GhostStatusActivity.GHOST_SCRIPT_LOSE, getItem(index).getLeft().scriptLose);
                                intent.putExtra(GhostStatusActivity.GHOST_SCRIPT_WIN, getItem(index).getLeft().scriptWin);
                                startActivity(intent);
                            }
                        });
                    } else {
                        v.findViewById(R.id.half_left_prison).setVisibility(View.INVISIBLE);
                        lv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int index = views.indexOf(v) / 2;
                                popSpeech(getItem(index).getLeft().name + ": " + getItem(index).getLeft().scriptWin);
                            }
                        });
                    }
                    if (left.power < 3) {
                        pb.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
                    } else {
                        pb.getProgressDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
                    }
                } else {
                    v.findViewById(R.id.half_left).setVisibility(View.INVISIBLE);
                }
                if (right != null) {
                    ((ImageView) (v.findViewById(R.id.half_right_ghost))).setImageResource(right.kind);
                    ((TextView) (v.findViewById(R.id.half_right_text))).setText(right.name);
                    ProgressBar pb = ((ProgressBar) (v.findViewById(R.id.half_right_health)));
                    pb.setProgress(right.power + 1);
                    View rv = v.findViewById(R.id.half_right);

                    views.add(rv);

                    if (right.isCaptured) {
                        rv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int index = views.indexOf(v) / 2;
                                Intent intent = new Intent(getContext(), GhostStatusActivity.class);
                                intent.putExtra(GhostStatusActivity.GHOST_KIND, getItem(index).getRight().kind);
                                intent.putExtra(GhostStatusActivity.GHOST_NAME, getItem(index).getRight().name);
                                intent.putExtra(GhostStatusActivity.GHOST_POWER, getItem(index).getRight().power);
                                intent.putExtra(GhostStatusActivity.GHOST_SCRIPT_LOSE, getItem(index).getRight().scriptLose);
                                intent.putExtra(GhostStatusActivity.GHOST_SCRIPT_WIN, getItem(index).getRight().scriptWin);
                                startActivity(intent);
                            }
                        });
                    } else {
                        v.findViewById(R.id.half_right_prison).setVisibility(View.INVISIBLE);
                        rv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int index = views.indexOf(v) / 2;
                                popSpeech(getItem(index).getRight().name + ": " + getItem(index).getRight().scriptWin);
                            }
                        });
                    }
                    if (right.power < 3) {
                        pb.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
                    } else {
                        pb.getProgressDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
                    }
                } else {
                    v.findViewById(R.id.half_right).setVisibility(View.INVISIBLE);
                }
            }

            return v;
        }
    }

    void popSpeech(String speech) {
        Toast.makeText(getApplicationContext(), speech,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        map = menu.add("레이더 예시");
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item == map) {
            Intent intent = new Intent(GhostPrisonActivity.this, NMapViewer.class);
            startActivity(intent);
        }
        return true;
    }

}

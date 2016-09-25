package com.example.etasheva.spotifyapp;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecycleViewSelectedElement {

    private ArrayList<String> mData;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private Context mContext;
    private RecyclerViewCuctomDecoration mDecoration;
    private String[] songs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("ROCK THIS");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitleMarginStart(100);

        this.mData = new ArrayList<>();

        fillData();

        this.mRecyclerView = (RecyclerView) findViewById(R.id.recycleView);
        this.mLayoutManager = new LinearLayoutManager(this, 1, false);
        this.mRecyclerView.setLayoutManager(this.mLayoutManager);

        this.mAdapter = new RecycleViewAdapter(this.mData, this);
        this.mRecyclerView.setAdapter(this.mAdapter);

        mIth.attachToRecyclerView(mRecyclerView);

        this.mDecoration = new RecyclerViewCuctomDecoration(this);
        this.mRecyclerView.addItemDecoration(this.mDecoration);
        this.mContext = this;

    }

    ItemTouchHelper mIth = new ItemTouchHelper(
            new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                    ItemTouchHelper.RIGHT) {

                @Override
                public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                    int elementPosition = viewHolder.getAdapterPosition();

                    mData.remove(elementPosition);

                    mAdapter.notifyItemRemoved(elementPosition);
                }


            });

    @Override
    public void onItemSelected(int position) {
        String message = "Playing " + this.songs[position];
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private void fillData() {
        this.songs = new String[]{"The Chainsmokers Featuring Halsey - Closer", "Major Lazer Featuring Justin Bieber &amp; MO - Cold Water", "twenty one pilots - Heathens", "Sia Featuring Sean Paul - Cheap Thrills", "The Chainsmokers Featuring Daya - Don't Let Me Down", "Calvin Harris Featuring Rihanna - This Is What You Came For", "twenty one pilots - Ride", "Shawn Mendes - Treat You Better", "Drake Featuring WizKid &amp; Kyla - One Dance", "Rihanna - Needed Me", "Adele - Send My Love (To Your New Lover)", "Charlie Puth Featuring Selena Gomez - We Don't Talk Anymore", "DJ Snake Featuring Justin Bieber - Let Me Love You", "Justin Timberlake - Can't Stop The Feeling!", "Ariana Grande - Into You", "Drake Featuring Rihanna - Too Good", "Britney Spears Featuring G-Eazy - Make Me...", "D.R.A.M. Featuring Lil Yachty - Broccoli", "Lil Wayne, Wiz Khalifa &amp; Imagine Dragons With Logic &amp; Ty Dolla $ign Feat. X Ambassadors - Sucker For Pain", "Kiiara - Gold", "Tory Lanez - Luv", "DJ Khaled Featuring Drake - For Free", "gnash Featuring Olivia O'Brien - I Hate U I Love U", "Flume Featuring Kai - Never Be Like You", "Desiigner - Panda", "Drake - Controlla", "Coldplay - Hymn For The Weekend", "Daya - Sit Still, Look Pretty", "Fifth Harmony Featuring Ty Dolla $ign - Work From Home", "P!nk - Just Like Fire", "Ariana Grande Featuring Nicki Minaj - Side To Side", "Kungs vs Cookin' On 3 Burners - This Girl", "Usher Featuring Young Thug - No Limit", "Florida Georgia Line - H.O.L.Y.", "Rihanna Featuring Drake - Work", "Lukas Graham - Mama Said", "twenty one pilots - Stressed Out", "Mike Posner - I Took A Pill In Ibiza", "Meghan Trainor - Me Too", "Fifth Harmony Featuring Fetty Wap - All In My Head (Flex)", "Desiigner - Tiimmy Turner", "X Ambassadors - Unsteady", "Kelsea Ballerini - Peter Pan", "Future Featuring The Weeknd - Low Life", "Kent Jones - Don't Mind", "Lukas Graham - 7 Years", "DNCE - Cake By The Ocean", "Dierks Bentley Featuring Elle King - Different For Girls", "Hailee Steinfeld &amp; Grey Featuring Zedd - Starving", "Sam Hunt - Make You Miss Me", "Kenny Chesney Featuring P!nk - Setting The World On Fire", "DJ Khaled Featuring Jay Z &amp; Future - I Got The Keys", "Katy Perry - Rise", "Kehlani - Gangsta", "Jake Owen - American Country Love Song", "Florida Georgia Line Featuring Tim McGraw - May We All", "Martin Garrix &amp; Bebe Rexha - In The Name Of Love", "Lil Uzi Vert - Money Longer", "ScHoolboy Q Featuring Kanye West - THat Part", "Dan + Shay - From The Ground Up", "Skrillex &amp; Rick Ross - Purple Lamborghini", "Beyonce - Sorry", "Marc E. Bassy Featuring G-Eazy - You &amp; Me", "Wale - My PYT", "Rob $tone Featuring J. Davi$ &amp; Spooks - Chill Bill", "YG Featuring Drake &amp; Kamaiyah - Why You Always Hatin?", "Dae Dae - Wat U Mean (Aye, Aye, Aye)", "Miranda Lambert - Vice", "Kanye West - Father Stretch My Hands Pt. 1", "Blake Shelton - She's Got A Way With Words", "Chris Brown - Grass Ain't Greener", "Cole Swindell - Middle Of A Memory", "Young Thug And Travis Scott Featuring Quavo - Pick Up The Phone", "French Montana Featuring Drake - No Shopping", "Grace Featuring G-Eazy - You Don't Own Me", "Billy Currington - It Don't Hurt Like It Used To", "Nicky Jam - With You Tonight / Hasta El Amanecer", "Tucker Beathard - Rock On", "Selena Gomez - Kill Em With Kindness", "Chance The Rapper Featuring Lil Wayne &amp; 2 Chainz - No Problem", "PARTYNEXTDOOR Featuring Drake - Come And See Me", "DJ ESCO Featuring Future &amp; Lil Uzi Vert - Too Much Sauce", "Justin Moore - You Look Like I Need A Drink", "Lil Yachty - 1 Night", "Young M.a. - Ooouuu", "Future - Wicked", "Young Thug - Wyclef Jean", "William Michael Morgan - I Met A Girl", "Luke Bryan - Move", "Kanye West - Famous", "Eric Church - Record Year", "Lil Uzi Vert - You Was Right", "DJ Khaled Featuring Nicki Minaj, Chris Brown &amp; August Alsina - Do You Mind", "DJ Drama Featuring Chris Brown, Skeme &amp; Lyquin - Wishing", "Alessia Cara - Scars To Your Beautiful", "French Montana Featuring Kodak Black - Lockjaw", "LoCash - I Know Somebody", "Enrique Iglesias Feat. Wisin or Tinashe &amp; Javada - Duele El Corazon", "Carrie Underwood - Church Bells", "Tove Lo - Cool Girl"};
        for (int i = 0; i < songs.length; i++) {
            this.mData.add(this.songs[i]);
        }
    }
}

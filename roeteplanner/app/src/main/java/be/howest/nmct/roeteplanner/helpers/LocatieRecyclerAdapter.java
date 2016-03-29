package be.howest.nmct.roeteplanner.helpers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import be.howest.nmct.roeteplanner.LocatiesFragment;
import be.howest.nmct.roeteplanner.R;

import be.howest.nmct.roeteplanner.classes.Locatie;
import be.howest.nmct.roeteplanner.classes.LocatieSituatie;
import be.howest.nmct.roeteplanner.repositories.LocatieRepo;
import butterknife.Bind;
import butterknife.ButterKnife;

public class LocatieRecyclerAdapter extends RecyclerView.Adapter<LocatieRecyclerAdapter.LocatieViewHolder>{

    private LocatieSituatie _situatie;
    private LocatiesFragment.OnLocatieGevondenListener _listener;

    public LocatieRecyclerAdapter(LocatiesFragment.OnLocatieGevondenListener listener, LocatieSituatie situatie){
        _listener = listener;
        _situatie = situatie;
    }

    public LocatieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LocatieViewHolder(LayoutInflater.from(parent.getContext())
                                                   .inflate(R.layout.row_locatie, parent, false));
    }

    @Override
    public void onBindViewHolder(LocatieViewHolder holder, int position) {
        Locatie locatie = LocatieRepo.getLocaties().get(position);
        holder.txvFormateerd.setText(locatie.toString());
    }

    @Override
    public int getItemCount() {
        return LocatieRepo.getLocaties().size();
    }

    public class LocatieViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.txvFormateerd) TextView txvFormateerd;
        @Bind(R.id.btnVerwijder) ImageButton btnVerwijder;

        public LocatieViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    _listener.onLocatieGevonden(LocatieRepo.getLocaties().get(getAdapterPosition()), _situatie);
                }
            });

            //itemView.setOnLongClickListener(new View.OnLongClickListener() {
            //    @Override
            //    public boolean onLongClick(View v) {
            //        btnVerwijder.setVisibility(View.VISIBLE);
            //        return true;
            //    }
            //});

            //btnVerwijder.setOnClickListener(new View.OnClickListener() {
            //    @Override
            //    public void onClick(View v) {
            //        Snackbar.make(v, "item is verwijderd", Snackbar.LENGTH_SHORT);
            //    }
            //});
        }
    }
}

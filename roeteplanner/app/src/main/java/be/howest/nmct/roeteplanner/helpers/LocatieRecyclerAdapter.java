package be.howest.nmct.roeteplanner.helpers;

import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import be.howest.nmct.roeteplanner.R;

import be.howest.nmct.roeteplanner.classes.Locatie;
import be.howest.nmct.roeteplanner.repositories.LocatieRepo;
import butterknife.Bind;
import butterknife.ButterKnife;

public class LocatieRecyclerAdapter extends RecyclerView.Adapter<LocatieRecyclerAdapter.LocatieViewHolder>{

    @Override
    public LocatieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LocatieViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_locatie, parent, false));
    }

    @Override
    public void onBindViewHolder(LocatieViewHolder holder, int position) {
        Locatie locatie = LocatieRepo.getLocaties().get(position);
        holder.txvGemeente.setText(locatie.getGemeente());
        holder.txvStraat.setText(locatie.getStraat());
    }

    @Override
    public int getItemCount() {
        return LocatieRepo.getLocaties().size();
    }

    public class LocatieViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.txvGemeente) TextView txvGemeente;
        @Bind(R.id.txvStraat) TextView txvStraat;

        public LocatieViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }

}

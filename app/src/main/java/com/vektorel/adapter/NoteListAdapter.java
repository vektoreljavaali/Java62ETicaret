package com.vektorel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vektorel.R;
import com.vektorel.dao.entity.Note;
import com.vektorel.model.Message;

import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.MyViewHolder> {

    private List<Note> notelist;
    private Context context;
    private ImageView profileimage;
    private TextView title;
    private TextView content;
    private TextView saat;

    /**
     * Bu adapter i kim çağıracak ise onun ilgili listeyi ve context i vermesini istersiniz.
     * bunu yapaağımız yer ise constructor dur.
     */
    public NoteListAdapter(Context context, List<Note> notelist){
        this.notelist = notelist;
        this.context = context;

    }

    /**
     * Bu adapter içinde hangi tasarım görüntüsünün kullanılacağı belirtilecek.
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public NoteListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.signle_note_theme,parent,false);

        return new NoteListAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteListAdapter.MyViewHolder holder, int position) {
        profileimage = holder.itemView.findViewById(R.id.note_list_profile_image);
        title = holder.itemView.findViewById(R.id.notetitle);
        content = holder.itemView.findViewById(R.id.notecontext);
        saat = holder.itemView.findViewById(R.id.note_list_time);
        Note note = notelist.get(position);
        //profileimage.setImageURI(Uri.parse(""));
        title.setText(note.getTitle());
        content.setText(note.getContent());
        saat.setText(note.getPublishAt()+"");
    }

    /**
     * Sizin vermiş olduğunuz istenin uzunluğu kadar bir alanda işlem yapacak. bu nedenle
     * buraya listenizin uzunluğunu vermelisiniz.
     * Liste uzunluğu kadar bir içerik listesi sunacak.
     * @return
     */
    @Override
    public int getItemCount() {
        return notelist.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        //View myview;
        public MyViewHolder(View view){
            super(view);
            // this.myview = view;

        }

    }

}

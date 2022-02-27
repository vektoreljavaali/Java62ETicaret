package com.vektorel.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vektorel.R;
import com.vektorel.dao.entity.Note;
import com.vektorel.model.UserProfile;

import java.net.URI;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private List<UserProfile> userProfileList;
    private Context context;
    private ImageView profileimage;
    private TextView username;
    private TextView email;
    private TextView saat;

    /**
     * Bu adapter i kim çağıracak ise onun ilgili listeyi ve context i vermesini istersiniz.
     * bunu yapaağımız yer ise constructor dur.
     */
    public UserAdapter(Context context, List<UserProfile> userProfileList){
        this.userProfileList = userProfileList;
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
    public UserAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_profile_chart,parent,false);

        return new UserAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.MyViewHolder holder, int position) {
        profileimage = holder.itemView.findViewById(R.id.userprofile_image);
        username = holder.itemView.findViewById(R.id.userprofile_name);
        email = holder.itemView.findViewById(R.id.userprofile_email);
        UserProfile userProfile = userProfileList.get(position);
        Glide.with(this.context).load(userProfile.picture.large).into(profileimage);
        //profileimage.setImageURI(Uri.parse(userProfile.picture.thumbnail));
        username.setText(userProfile.name.first+" "+ userProfile.name.last);
        email.setText(userProfile.email);
    }

    /**
     * Sizin vermiş olduğunuz istenin uzunluğu kadar bir alanda işlem yapacak. bu nedenle
     * buraya listenizin uzunluğunu vermelisiniz.
     * Liste uzunluğu kadar bir içerik listesi sunacak.
     * @return
     */
    @Override
    public int getItemCount() {
        return userProfileList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        //View myview;
        public MyViewHolder(View view){
            super(view);
            // this.myview = view;

        }

    }


}

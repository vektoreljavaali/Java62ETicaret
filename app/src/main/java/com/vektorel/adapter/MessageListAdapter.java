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

import com.vektorel.R;
import com.vektorel.model.Message;

import java.util.List;

/**
 * RecicylerView için adapter yazmak gereklidir. çünkü view içindeki alanların yönetilmesi
 * için bir adpter çerçevesiyle işlem yapmaktadır.
 */
public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.MyViewHolder> {
    /**
     * View -> tasarım layout una ihtiyaç var
     * List -> görüntülenmesi istenilen liste
     * Bileşenler-> bileşenlerin tanımlanması gerekli
     * Context -> bu üzerinde bulunduğumuz activitinin varlığını temsil edecek.
     */
    private List<Message> messageList;
    private Context context;
    private ImageView profileimage;
    private TextView kullaniciadi;
    private TextView txtmessage;
    private TextView saat;

    /**
     * Bu adapter i kim çağıracak ise onun ilgili listeyi ve context i vermesini istersiniz.
     * bunu yapaağımız yer ise constructor dur.
     */
    public MessageListAdapter(Context context, List<Message> messageList){
        this.messageList = messageList;
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
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.signle_message_theme,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            profileimage = holder.itemView.findViewById(R.id.message_list_profile_image);
            kullaniciadi = holder.itemView.findViewById(R.id.txtmessageusername);
            txtmessage = holder.itemView.findViewById(R.id.message_list_message);
            saat = holder.itemView.findViewById(R.id.messaj_list_time);
            Message message = messageList.get(position);
            //profileimage.setImageURI(Uri.parse(""));
            kullaniciadi.setText(message.getUser());
            txtmessage.setText(message.getMessage());
            saat.setText(message.getTime());
    }

    /**
     * Sizin vermiş olduğunuz istenin uzunluğu kadar bir alanda işlem yapacak. bu nedenle
     * buraya listenizin uzunluğunu vermelisiniz.
     * Liste uzunluğu kadar bir içerik listesi sunacak.
     * @return
     */
    @Override
    public int getItemCount() {
        return messageList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        //View myview;
        public MyViewHolder(View view){
            super(view);
           // this.myview = view;

        }

    }

}

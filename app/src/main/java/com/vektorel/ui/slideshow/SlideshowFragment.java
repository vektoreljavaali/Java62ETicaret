package com.vektorel.ui.slideshow;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vektorel.R;
import com.vektorel.databinding.FragmentSlideshowBinding;
import com.vektorel.model.Message;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");
    private DatabaseReference mDatabase = database.getReference();
    private final String TAG ="FireBase";
    private EditText txtMessages, txtNickname, txtMessage;
    private Button btnGonder;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        txtMessage = root.findViewById(R.id.txtmessage);
        txtMessages = root.findViewById(R.id.txtMessages);
        txtNickname = root.findViewById(R.id.txtnickname);
        btnGonder = root.findViewById(R.id.btngonder);
        btnGonder.setOnClickListener(v->
                sendMessage(txtNickname.getText().toString(),
                            txtMessage.getText().toString()));

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
               Iterable<DataSnapshot> mgun=  dataSnapshot.getChildren();
               mgun.forEach(x->{
                   Message message =  x.getValue(Message.class);
                   writeMessage(message);
               });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("TAG", "loadPost:onCancelled", databaseError.toException());
            }
        };
        mDatabase.child("20_02_2022").addValueEventListener(postListener);
        return root;
    }

    private void writeMessage(Message message) {
        txtMessages.setText(
                message.getUser()+" : "+
                message.getMessage()+" "+
                message.getTime()+"\n"+
                        txtMessages.getText().toString()
        );
    }

    private void sendMessage(String nickname, String message) {
        // Write a message to the database
        Message mymessage = new Message(nickname,message);
        String longid = System.currentTimeMillis()+"";
        mDatabase.child("20_02_2022").child(longid).setValue(mymessage);
        //myRef.setValue(message);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
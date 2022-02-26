package com.vektorel.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vektorel.R;
import com.vektorel.RegisterActivity;
import com.vektorel.adapter.MessageListAdapter;
import com.vektorel.adapter.NoteListAdapter;
import com.vektorel.dao.NoteRepository;
import com.vektorel.dao.entity.Note;
import com.vektorel.databinding.FragmentGalleryBinding;
import com.vektorel.utility.StaticValues;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    private EditText txttitle, txtcontent;
    Button btnkaydet;
    RecyclerView recyclerView;
    NoteRepository noteRepository;
    List<Note> noteList = new ArrayList<>();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        txttitle = root.findViewById(R.id.notePagetxttitle);
        txtcontent = root.findViewById(R.id.notePagetxtcontent);
        btnkaydet = root.findViewById(R.id.btnkaydet);
        recyclerView = root.findViewById(R.id.rcwNoteList);
        btnkaydet.setOnClickListener(v-> NoteKaydet());
        liste();
        return root;
    }

    private void NoteKaydet() {
        noteRepository = new NoteRepository(this.getContext());
        noteRepository.save(new Note(
                null, "",txttitle.getText().toString(),
                txtcontent.getText().toString(),
                new Date().getTime(),
                0,"HIGH"
        ));
        liste();
    }

    private void liste(){
        noteRepository = new NoteRepository(this.getContext());
        noteList = noteRepository.findAll();
        NoteListAdapter adapter = new NoteListAdapter(this.getContext(),noteList);
        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
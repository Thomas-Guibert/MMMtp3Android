package com.example.correctiontp1mmm;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.correctiontp1mmm.data.Client;
import com.example.correctiontp1mmm.data.ClientList;
import com.example.correctiontp1mmm.data.IRepository;
import com.example.correctiontp1mmm.ui.ClientListAdapter;
import com.example.correctiontp1mmm.viewmodel.ClientListViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FirstFragment extends Fragment {

    private ClientListViewModel viewModel;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionFirestore = FirebaseFirestore.getInstance().collection("client");;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.myclients);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        // on créée une instance d'adapter
        final ClientListAdapter adapter = new ClientListAdapter();
        recyclerView.setAdapter(adapter);

        // on crée une instance de notre ViewModel

        viewModel = new ViewModelProvider(requireActivity()).get(ClientListViewModel.class);



        // et on ajoute un observer sur les clients...
        viewModel.getAllUsers().observe(getViewLifecycleOwner(), new Observer<List<Client>>() {
            @Override
            public void onChanged(@Nullable List<Client> clients) {
                List<Client> res = new ArrayList<>();

                db.collection("client")
                        //.whereEqualTo("capital", true)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        Log.d("TAG", document.getId() + " => " + document.getData());
                                        Log.d("TAG", "NAME : " + document.getId());
                                        Client c = new Client(
                                                String.valueOf(document.get("lastName")),
                                                String.valueOf(document.get("firstName")),
                                                String.valueOf(document.getId()),
                                                String.valueOf(document.get("city")));

                                        res.add(c);
                                        //Client c = new Client(document.get("firstName"));
                                    }

                                } else {
                                    Log.d("TAG", "Error getting documents: ", task.getException());
                                }
                            }
                        });


                adapter.setClients(res);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {


                viewModel.delete(adapter.getClientAt(viewHolder.getAdapterPosition()));
                Log.d("TAG", "Suppression OK : "+adapter.getClientAt(viewHolder.getAdapterPosition()).getDate() );
                db.collection("client").document(adapter.getClientAt(viewHolder.getAdapterPosition()).getDate()).delete();

                // notification...
            }
        }).attachToRecyclerView(recyclerView);


        view.findViewById(R.id.addClient).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }
}
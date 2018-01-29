package com.appclientefissare.activity.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.appclientefissare.R;

import java.util.ArrayList;

public class ProveedorFragment extends Fragment {

    ListView listView;
    //ArrayList<String> lista;
    ArrayAdapter<String> adaptador;

    public ProveedorFragment(){
        //Requiered empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_proveedor, container, false);

        String[] proveedores = {"Prooveedor 1","Prooveedor 2","Prooveedor 3"};

        listView = (ListView)rootView.findViewById(R.id.list_proveedor);

        adaptador = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                proveedores
        );

        listView.setAdapter(adaptador);

        return rootView;
    }







    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(getActivity(), "Estas dentro de Proveedores", Toast.LENGTH_SHORT).show();
            }
        });

    }


}

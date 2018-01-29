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


public class PedidoFragment extends Fragment {

    ListView listView;
    ArrayAdapter<String> adaptador;
    ArrayList<String> lista = new ArrayList<>();
    String URL = "http://fissare.ayniwork.com/appServiHogar/srv/web/login";




    public PedidoFragment(){
        //Requiered empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_pedido, container, false);
        listView = (ListView)rootView.findViewById(R.id.list_proveedor);

        String[] proveedores = {"1 - 18/12/2017 Aceptado",
                "2 - 20/12/2017 Terminado",
                "3 - 05/01/2018 En proceso",
                "4 - 10/01/2018 Terminado",
                "5 - 11/01/2018 En proceso"};


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
        view.findViewById(R.id.button_pedidos).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(getActivity(), "Estas dentro de Pedidos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

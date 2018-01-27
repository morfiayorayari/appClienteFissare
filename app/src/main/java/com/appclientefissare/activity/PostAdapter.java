import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.appclientefissare.R;


public class PostAdapter extends ArrayAdapter {

    // Atributos
    private String URL_BASE = "http://servidorexterno.site90.com/datos";
    private static final String TAG= "PostAdapter";
    //List<Post> items;

    public PostAdapter(Context context) {
        super(context,0);

        // Gestionar petición del archivo JSON
    }
/*
    @Override
    public int getCount() {
        return items != null ? items.size() : 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());

        //Salvando la referencia del View de la fila
        View listItemView = convertView;

        //Comprobando si el View no existe
        if (null == convertView) {
            //Si no existe, entonces inflarlo
            listItemView = layoutInflater.inflate(
                    R.layout.post,
                    parent,
                    false);

            // Procesar item

            return listItemView;
        }


*/


    }
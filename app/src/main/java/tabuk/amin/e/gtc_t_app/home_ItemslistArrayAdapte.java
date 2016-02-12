package tabuk.amin.e.gtc_t_app;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pcland15.ismail.gtc_t_app.R;

import java.util.ArrayList;
import java.util.List;

import tabuk.amin.e.gtc_t_app.libs.cat_list;
import tabuk.amin.e.gtc_t_app.libs.ui;


class home_ItemslistArrayAdapte extends ArrayAdapter<cat_list> {




    public int resLayout = R.layout.home_items_list;

    Context context;
    List<cat_list> objects;
    List<cat_list> fobjects;

    public home_ItemslistArrayAdapte(Context context, int resource, List<cat_list> objects) {
        super(context, resource, objects);

        this.objects = new ArrayList<cat_list>();
        this.fobjects = new ArrayList<cat_list>();

        this.context = context;
        this.objects.addAll(objects) ;
        this.fobjects.addAll(objects);

    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(resLayout, null);


        cat_list cat = objects.get(position);


        TextView t = (TextView) view.findViewById(R.id.list_items_title);

        t.setText(cat.toString());

        ui.loadImage(context, (ImageView) view.findViewById(R.id.list_items_image), cat.getImage());


        return view;
    }






/*




    private itemsSearch filter;


    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new itemsSearch();
        }
        return filter;
    }


    private class itemsSearch extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            constraint = constraint.toString().toLowerCase();
            FilterResults result = new FilterResults();
            if (constraint != null && constraint.toString().length() > 0) {
                ArrayList<cat_list> filteredItems = new ArrayList<cat_list>();

                for (int i = 0, l =fobjects.size(); i < l; i++) {
                    cat_list country = fobjects.get(i);
                    if (country.toString().toLowerCase().contains(constraint))
                        filteredItems.add(country);
                }
                result.count = filteredItems.size();
                result.values = filteredItems;
            } else {
                synchronized (this) {
                    result.values = fobjects;
                    result.count = fobjects.size();
                }
            }
            return result;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {

            objects = (ArrayList<cat_list>) results.values;
            notifyDataSetChanged();
            clear();
            for (int i = 0, l = objects.size(); i < l; i++)
                add(objects.get(i));
            notifyDataSetInvalidated();
        }
    }

*/
}

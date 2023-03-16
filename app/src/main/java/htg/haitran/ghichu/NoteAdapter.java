package htg.haitran.ghichu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NoteAdapter extends BaseAdapter {
    private Context context;
    private int idLayout;
    private ArrayList<Note> notes;

    public NoteAdapter(Context context, int idLayout, ArrayList<Note> notes) {
        this.context = context;
        this.idLayout = idLayout;
        this.notes = notes;

    }

    @Override
    public int getCount() {
        if (notes.size() != 0 && !notes.isEmpty()) {
            return  notes.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(idLayout, null);

        // Ánh xạ và gán giá trị
        TextView txtTieuDe = (TextView) view.findViewById(R.id.txtTieude);
        txtTieuDe.setText((notes.get(i).getTieuDe().split("\\s").length > 4) ? (getFirstNStrings(notes.get(i).getTieuDe(), 4)+ "...") :notes.get(i).getTieuDe() ) ;
        TextView txtNoiDung = (TextView)  view.findViewById(R.id.txtNoidung);
        txtNoiDung.setText((notes.get(i).getNoiDung().split("\\s").length > 8) ? (getFirstNStrings(notes.get(i).getNoiDung(), 8)+ "...") :notes.get(i).getNoiDung() ) ;
        TextView txtCreateTime = (TextView) view.findViewById(R.id.txtCreatetime);
        txtCreateTime.setText(notes.get(i).getCreateTime());

        return view;
    }
    public String getFirstNStrings(String str, int n) {
        String[] sArr = str.split(" ");
        System.out.println("Độ dài: "+str);
        String firstStrs = "";
        for(int i = 0; i < n; i++)
            firstStrs += sArr[i] + " ";
        return firstStrs.trim();
    }

}

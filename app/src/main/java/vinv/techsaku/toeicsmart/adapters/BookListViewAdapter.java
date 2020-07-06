package vinv.techsaku.toeicsmart.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import vinv.techsaku.toeicsmart.R;
import vinv.techsaku.toeicsmart.models.Book;

public class BookListViewAdapter extends BaseAdapter {

    final ArrayList<Book> listBook;

    public BookListViewAdapter(ArrayList<Book> listBook) {
        this.listBook = listBook;
    }

    @Override
    public int getCount() {
        return listBook.size();
    }

    @Override
    public Object getItem(int i) {
        return listBook.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listBook.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View viewBook;

        if (view == null) {
            viewBook = View.inflate(viewGroup.getContext(), R.layout.book_view, null);
        }
        else viewBook = view;

        Book book = (Book)getItem(i);

        ((TextView)viewBook.findViewById(R.id.nameBook)).setText(book.getName());

        return viewBook;
    }
}

package com.example.ss04;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends BaseAdapter {

    private Context mContext;
    private List<ContactModel> listContact;
    private IOnchildItemClick iOnchildItemClick;

    public ContactAdapter(Context mContext, List<ContactModel> listContact) {
        this.mContext = mContext;
        this.listContact = listContact;
    }

    public void registerChildItemClick(IOnchildItemClick iOnchildItemClick) {
        this.iOnchildItemClick = iOnchildItemClick;

    }

    public void unRegisterChildItemClick() {
        this.iOnchildItemClick = null;
    }

    @Override
    public int getCount() {
        return listContact.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            rowView = inflater.inflate(R.layout.item_contact, null);
            ViewHolder holder = new ViewHolder();
            holder.tvName = (TextView) rowView.findViewById(R.id.tvName);
            holder.tvPhone = (TextView) rowView.findViewById(R.id.tvPhone);
            holder.ivAvatar = (ImageView) rowView.findViewById(R.id.ivAvatar);
            holder.btCall = (Button) rowView.findViewById(R.id.btCall);
            holder.btEdit = (Button) rowView.findViewById(R.id.btEdit);

        }

        ViewHolder holder = (ViewHolder) rowView.getTag();
        holder.tvName.setText(listContact.get(i).getName());
        holder.tvPhone.setText(listContact.get(i).getPhone());
        holder.ivAvatar.setImageResource(listContact.get(i).getImage());

        holder.btCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCall(i);
            }
        });

        holder.btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iOnchildItemClick.onItemChildClick(i);
            }
        });
        return rowView;
    }

    private void onCall(int position) {
        ContactModel contact = listContact.get(position);
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + contact.getPhone()));
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mContext.startActivity(intent);
    }


    private class ViewHolder {
        TextView tvName;
        TextView tvPhone;
        ImageView ivAvatar;
        Button btCall;
        Button btEdit;
    }
}

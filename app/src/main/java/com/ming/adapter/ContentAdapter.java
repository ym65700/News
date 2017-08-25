package com.ming.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import com.ming.jsonBean.NewsInfoBean;
import com.ming.news.R;
import com.ming.server.ApplicationController;

import java.util.List;

/**
 * Created by Administrator on 2016/9/23.
 */
public class ContentAdapter extends BaseAdapter {
    private static final int TYPE_BANNER = 0;
    private static final int TYPE_COMMON_LIST_ITEM = 1;
    private static final int TYPE_THREE_IMG_ITEM = 2;

    private Context context;
    private LayoutInflater inflater;
    private List<NewsInfoBean.ResultBean.DataBean> entryList;

    public ContentAdapter(Context context, List<NewsInfoBean.ResultBean.DataBean> entryList) {
        super();
        this.entryList = entryList;
        this.context = context;
    }


    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        int i = position;
        if (i == 0) {
            return TYPE_BANNER;
        } else if (i > 0 && 0 == i % 4) {
            return TYPE_THREE_IMG_ITEM;
        } else {
            return TYPE_COMMON_LIST_ITEM;
        }

    }

    @Override
    public int getCount() {

        if (entryList != null) {
            return entryList.size();
        } else {
            return 0;
        }
    }


    @Override
    public Object getItem(int position) {
        if (entryList != null) {
            return entryList.get(position);
        } else {
            return null;
        }

    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderBanner viewHolderBanner = null;
        ViewHolderItem viewHolderItem = null;
        ViewHolderImg viewHolderImg = null;
        int type = getItemViewType(position);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            switch (type) {
                case TYPE_BANNER:
                    convertView = inflater.inflate(R.layout.type_banner, parent, false);
                    viewHolderBanner = new ViewHolderBanner();
                    viewHolderBanner.bannerTitle = (TextView) convertView.findViewById(R.id.tv_banner);
                    viewHolderBanner.bannerImg = (NetworkImageView) convertView.findViewById(R.id.iv_banner);
                    convertView.setTag(viewHolderBanner);
                    break;
                case TYPE_COMMON_LIST_ITEM:
                    convertView = inflater.inflate(R.layout.type_list_item, parent, false);
                    viewHolderItem = new ViewHolderItem();
                    viewHolderItem.itemImg = (NetworkImageView) convertView.findViewById(R.id.img_item);
                    viewHolderItem.itemTitle = (TextView) convertView.findViewById(R.id.tv_item_title);
                    viewHolderItem.itemComment = (TextView) convertView.findViewById(R.id.tv_item_source);
                    convertView.setTag(viewHolderItem);
                    break;
                case TYPE_THREE_IMG_ITEM:
                    convertView = inflater.inflate(R.layout.type_img, parent, false);
                    viewHolderImg = new ViewHolderImg();
                    viewHolderImg.imgOne = (NetworkImageView) convertView.findViewById(R.id.news_list_item_img_one_iv);
                    viewHolderImg.imgTwo = (NetworkImageView) convertView.findViewById(R.id.news_list_item_img_two_iv);
                    viewHolderImg.imgThree = (NetworkImageView) convertView.findViewById(R.id.news_list_item_img_three_iv);
                    viewHolderImg.titleTV = (TextView) convertView.findViewById(R.id.news_list_item_text_iv);
                    convertView.setTag(viewHolderImg);
                    break;
            }
        } else {
            switch (type) {
                case TYPE_BANNER:
                    viewHolderBanner = (ViewHolderBanner) convertView.getTag();
                    break;
                case TYPE_COMMON_LIST_ITEM:
                    viewHolderItem = (ViewHolderItem) convertView.getTag();
                    break;
                case TYPE_THREE_IMG_ITEM:
                    viewHolderImg = (ViewHolderImg) convertView.getTag();
                    break;

            }
        }
        //填充数据到指定布局

        switch (type) {
            case TYPE_BANNER:
                viewHolderBanner.bannerTitle.setText(entryList.get(position).getTitle());
                viewHolderBanner.bannerImg.setImageUrl(entryList.get(position).getThumbnail_pic_s(), ApplicationController.getsInstance().getImageLoader());

                break;
            case TYPE_COMMON_LIST_ITEM:

                viewHolderItem.itemImg.setImageUrl(entryList.get(position).getThumbnail_pic_s(), ApplicationController.getsInstance().getImageLoader());
                viewHolderItem.itemTitle.setText(entryList.get(position).getTitle());
                viewHolderItem.itemComment.setText("来源：" + entryList.get(position).getAuthor_name());

                break;
            case TYPE_THREE_IMG_ITEM:

                    if(!TextUtils.isEmpty(entryList.get(position).getThumbnail_pic_s02())){
                        viewHolderImg.imgTwo.setImageUrl(entryList.get(position).getThumbnail_pic_s02(), ApplicationController.getsInstance().getImageLoader());
                        if(!TextUtils.isEmpty(entryList.get(position).getThumbnail_pic_s03())){
                            viewHolderImg.imgThree.setImageUrl(entryList.get(position).getThumbnail_pic_s03(), ApplicationController.getsInstance().getImageLoader());
                        }else {
                            viewHolderImg.imgThree.setImageUrl(entryList.get(position).getThumbnail_pic_s(), ApplicationController.getsInstance().getImageLoader());
                        }
                    }else {
                        viewHolderImg.imgTwo.setImageUrl(entryList.get(position).getThumbnail_pic_s(), ApplicationController.getsInstance().getImageLoader());
                        viewHolderImg.imgThree.setImageUrl(entryList.get(position).getThumbnail_pic_s(), ApplicationController.getsInstance().getImageLoader());
                    }



                viewHolderImg.imgOne.setImageUrl(entryList.get(position).getThumbnail_pic_s(), ApplicationController.getsInstance().getImageLoader());
               // viewHolderImg.imgTwo.setImageUrl(entryList.get(position).getThumbnail_pic_s(), ApplicationController.getsInstance().getImageLoader());
               // viewHolderImg.imgThree.setImageUrl(entryList.get(position).getThumbnail_pic_s(), ApplicationController.getsInstance().getImageLoader());
                viewHolderImg.titleTV.setText(entryList.get(position).getTitle());
                break;

            default:
                break;

        }

        return convertView;

    }


    static class ViewHolderBanner {
        NetworkImageView bannerImg;
        TextView bannerTitle;

    }

    static class ViewHolderItem {
        NetworkImageView itemImg;
        TextView itemTitle;
        TextView itemComment;

    }

    static class ViewHolderImg {
        NetworkImageView imgOne;
        NetworkImageView imgTwo;
        NetworkImageView imgThree;
        TextView titleTV;
    }
}

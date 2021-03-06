package com.example.toutiao.viewHolder.news;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.base.Constants;
import com.example.base.utils.ImageHelper;
import com.example.base.utils.TimeUtil;
import com.example.toutiao.R;
import com.example.toutiao.entity.news.MultiNewsArticleDataBean;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxPopupMenu;

import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import me.drakeet.multitype.ItemViewBinder;

/**
 * Author: Funny
 * Time: 2018/9/6
 * Description: This is NewsArticleVideoViewBinder
 */
public class NewsArticleVideoViewBinder extends ItemViewBinder<MultiNewsArticleDataBean, NewsArticleVideoViewBinder.ViewHolder> {
    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_news_article_video, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("CheckResult")
    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull MultiNewsArticleDataBean item) {
        Context context = holder.itemView.getContext();

        //设置用户头像
        MultiNewsArticleDataBean.UserInfoBean user_info = item.getUser_info();
        if (user_info != null) {
            String avatar_url = user_info.getAvatar_url();
            ImageHelper.loadCenterCrop(context, avatar_url, holder.mIvMedia);
        }

        //设置视频的图片
        String videoImgUrl = item.getVideo_detail_info().getDetail_video_large_image().getUrl();
        ImageHelper.loadCenterCrop(context, videoImgUrl, holder.mIvVideoImage);

        //新闻时间
        String tvDataTime = item.getBehot_time();
        if (!TextUtils.isEmpty(tvDataTime)) {
            tvDataTime = TimeUtil.getTimeStampAgo(tvDataTime);
        }
        holder.mTvExtra.setText(String.format("%s - %s - %s", item.getSource(), item.getComment_count(), tvDataTime));
        holder.mTvTitle.setText(item.getTitle());

        //视频时间
        int video_duration = item.getVideo_duration();
        String minute = String.valueOf(video_duration / 60);
        if (Integer.parseInt(minute) < 10) {
            minute = "0" + minute;
        }

        String second = String.valueOf(video_duration % 60);
        if (Integer.parseInt(second) < 10) {
            second = "0" + second;
        }
        holder.mTvVideoTime.setText(String.format("%s:%s", minute, second));

        RxView.clicks(holder.mIvDots)
                .throttleFirst(Constants.CLICK_INTERVAL, TimeUnit.SECONDS)
                .subscribe(o -> {
                    //弹出分享菜单
                    PopupMenu popupMenu = new PopupMenu(context, holder.mIvDots, Gravity.END);
                    popupMenu.inflate(R.menu.menu_share);
                    RxPopupMenu.itemClicks(popupMenu)
                            .throttleFirst(Constants.CLICK_INTERVAL, TimeUnit.SECONDS)
                            .subscribe(menuItem -> {
                                if (menuItem.getItemId() == R.id.action_share) {
                                    Intent shareIntent = new Intent()
                                            .setAction(Intent.ACTION_SEND)
                                            .setType("text/plain")
                                            .putExtra(Intent.EXTRA_TEXT, "分享");
                                    context.startActivity(Intent.createChooser(shareIntent, context.getString(R.string.share_to)));
                                }
                            });
                    popupMenu.show();
                });
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mIvMedia;
        TextView mTvExtra;
        ImageView mIvDots;
        LinearLayout mHeader;
        TextView mTvTitle;
        ImageView mIvVideoImage;
        TextView mTvVideoTime;
        LinearLayout mContent;

        ViewHolder(View view) {
            super(view);
            mIvMedia = view.findViewById(R.id.iv_media);
            mTvExtra = view.findViewById(R.id.tv_extra);
            mIvDots = view.findViewById(R.id.iv_dots);
            mHeader = view.findViewById(R.id.header);
            mTvTitle = view.findViewById(R.id.tv_title);
            mIvVideoImage = view.findViewById(R.id.iv_video_image);
            mTvVideoTime = view.findViewById(R.id.tv_video_time);
            mContent = view.findViewById(R.id.content);
        }
    }
}

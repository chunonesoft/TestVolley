package com.chunsoft.utils;


import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.chunsoft.Activity.ApplicationController;
import com.chunsoft.net.LruImageCache;
import com.chunsoft.testvolley.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder {

	private SparseArray<View> mViews;
	private int mPosition;
	private View mConvertView;
	private Context context;
	public ViewHolder(Context context,ViewGroup parent,int layoutId,int position)
	{
		this.context = context;
		this.mPosition = position;
		this.mViews = new SparseArray<View>();
		this.mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,false);
		mConvertView.setTag(this);
		
	}
	public static ViewHolder get(Context context,View convertView,
			ViewGroup parent,int layoutId,int position)
	{
		if(convertView == null)
		{
			return new ViewHolder(context, parent, layoutId, position);
		}
		else
		{
			ViewHolder holder = (ViewHolder) convertView.getTag();
			holder.mPosition = position;
			return holder;
		}
	}
	/**
	 * ͨ��viewId��ÿؼ�
	 * @param viewId
	 * @return
	 */
	public <T extends View> T getView(int viewId)
	{
		View view = mViews.get(viewId);
		if(view == null)
		{
			view = mConvertView.findViewById(viewId);
			mViews.put(viewId, view);
		}
		return (T)view;
		
	}
	
	public View getConvertView()
	{
		
		return mConvertView;	
	}
	public ViewHolder setText(int viewId,String text)
	{
		TextView tv = getView(viewId);
		tv.setText(text);
		return this;
	}
	public ViewHolder setIamgeResouce(int viewId,int resId)
	{
		ImageView view = getView(viewId);
		view.setImageResource(resId);
		return this;
	}
	
	public ViewHolder setImageBitmap(int viewId,Bitmap bitmap)
	{
		ImageView view = getView(viewId);
		view.setImageBitmap(bitmap);
		return this;
	}

	public ViewHolder setNetWorkImageView(int viewId,String uri)
	{
		 //NetWorkImageView	
		NetworkImageView networkImageView = getView(viewId);
		LruImageCache lruImageCache = LruImageCache.instance();
		RequestQueue mQueue = ApplicationController.getInstance().getRequestQueue();
		ImageLoader imageLoader = new ImageLoader(mQueue,lruImageCache);
		
		//设置默认的网络图片		
		networkImageView.setDefaultImageResId(android.R.drawable.ic_menu_rotate);
		//设置获取错误的网络图片
		networkImageView.setErrorImageResId(android.R.drawable.ic_delete);		
		networkImageView.setImageUrl(uri, imageLoader);
		return this;
	}
	public ViewHolder setImageURI(int viewId,String uri)
	{
		ImageView view = getView(viewId);
        RequestQueue mQueue = ApplicationController.getInstance().getRequestQueue();
        ImageLoader  mImageLoader = new ImageLoader(mQueue, new LruImageCache());
        ImageListener listener = ImageLoader.getImageListener(view, android.R.drawable.ic_menu_rotate, android.R.drawable.ic_delete);    
        mImageLoader.get(uri,listener); 
		return this;
	}
	public int getPosition() {
		return mPosition;
	}
	public void setPosition(int mPosition) {
		this.mPosition = mPosition;
	}
}

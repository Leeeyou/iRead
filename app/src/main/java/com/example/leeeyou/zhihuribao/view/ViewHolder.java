package com.example.leeeyou.zhihuribao.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder {
	private final SparseArray<View> mViews;

	private View mConvertView;

//	private RequestQueue mQueue;
//
//	private ImageLoader imageLoader;

	public ViewHolder(Context context, ViewGroup parent, int resLayoutId, int position) {
		this.mViews = new SparseArray<>();
		mConvertView = LayoutInflater.from(context).inflate(resLayoutId, parent, false);
		mConvertView.setTag(this);
//		mQueue = Volley.newRequestQueue(context.getApplicationContext());
//		imageLoader = new ImageLoader(mQueue, new BitmapCache());
	}

//	public class BitmapCache implements ImageCache {
//		private LruCache<String, Bitmap> mCache;
//
//		public BitmapCache() {
//			int maxSize = 5 * 1024 * 1024;
//			mCache = new LruCache<String, Bitmap>(maxSize) {
//				@Override
//				protected int sizeOf(String key, Bitmap bitmap) {
//					return bitmap.getRowBytes() * bitmap.getHeight();
//				}
//			};
//		}
//
//		@Override
//		public Bitmap getBitmap(String url) {
//			return mCache.get(url);
//		}
//
//		@Override
//		public void putBitmap(String url, Bitmap bitmap) {
//			mCache.put(url, bitmap);
//		}
//	}

	/**
	 * 获取ViewHolder对象
	 * 
	 * @param context
	 * @param convertView 
	 * @param parent 
	 * @param resLayoutId 
	 * @return 
	 */
	public static ViewHolder get(Context context, View convertView, ViewGroup parent, int resLayoutId, int position) {
		if (convertView == null) {
			return new ViewHolder(context, parent, resLayoutId, position);
		}

		ViewHolder vh = (ViewHolder) convertView.getTag();
		return vh;
	}

	public <T extends View> T getView(int viewId) {
		return retrieveView(viewId);
	}

	@SuppressWarnings("unchecked")
	private <T extends View> T retrieveView(int viewId) {
		View view = mViews.get(viewId);
		if (view == null) {
			view = mConvertView.findViewById(viewId);
			mViews.put(viewId, view);
		}
		return (T) view;
	}

	public View getConvertView() {
		return mConvertView;
	}

	public ViewHolder setText(int viewId, String text) {
		TextView tv = getView(viewId);
		tv.setText(text);
		return this;
	}

	public ViewHolder setTextColor(int viewId, int color) {
		TextView tv = getView(viewId);
		tv.setTextColor(color);
		return this;
	}

	public ViewHolder setImageResource(int viewId, int drawableId) {
		ImageView view = retrieveView(viewId);
		view.setImageResource(drawableId);
		return this;
	}

	public ViewHolder setImageBitmap(int viewId, Bitmap bm) {
		ImageView view = retrieveView(viewId);
		view.setImageBitmap(bm);
		return this;
	}

//	public ViewHolder setImageByUrl(int viewId, String url) {
////		return setImageByUrl(viewId, url, R.drawable.ic_launcher);
//	}

//	public ViewHolder setImageByUrl(int viewId, String url, int defaultImageRes) {
//		ImageListener listener = ImageLoader.getImageListener((ImageView) retrieveView(viewId), defaultImageRes, defaultImageRes);
//		imageLoader.get(url, listener);
//		return this;
//	}

	public ViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
		View view = retrieveView(viewId);
		view.setOnClickListener(listener);
		return this;
	}

}

package com.chunsoft.net;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;

// 共用失败回调
public class StrErrListener implements ErrorListener {

	private Context mContext;
	@Override
	public void onErrorResponse(VolleyError arg0) {
		Toast.makeText(mContext,
				VolleyErrorHelper.getMessage(arg0, mContext),
				Toast.LENGTH_LONG).show();
	}
}

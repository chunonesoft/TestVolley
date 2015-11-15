package com.chunsoft.Activity;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.R.string;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.chunsoft.bean.Constant;
import com.chunsoft.bean.LoginBean;
import com.chunsoft.bean.Weather;
import com.chunsoft.net.GsonRequest;
import com.chunsoft.net.JSONObjListener;
import com.chunsoft.net.LruImageCache;
import com.chunsoft.net.StrErrListener;
import com.chunsoft.testvolley.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.util.LruCache;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


public class MainActivity extends Activity {
//	private Button btn_send;
//	private ImageView iv_pic;
//	private NetworkImageView networkImageView;
//	private ImageLoader mImageLoader;

	private EditText e_phone;
	private EditText e_pwd;
	private Button btn_login;
	private Button btn_cancel;
	private String requestData;
	Map<String, String> map = new HashMap<String, String>();
	JSONObject jsondata = new JSONObject();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        e_phone = (EditText) findViewById(R.id.editText1);
        e_pwd = (EditText) findViewById(R.id.editText2);
        btn_login = (Button) findViewById(R.id.button1);
        
        btn_login.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				
					map.put("phonenum", e_phone.getText().toString());
					map.put("password", e_pwd.getText().toString());
				    map.put("rememberme", "true");
				    
				
				
				    //ApplicationController.getInstance().addToRequestQueue(getJsonObjRequest());
		        ApplicationController.getInstance().addToRequestQueue(getGsonRequest());
			}
		});
//        ApplicationController.getInstance().addToRequestQueue(getStringRequest());
//        ApplicationController.getInstance().addToRequestQueue(getStringRequest());
//        ApplicationController.getInstance().addToRequestQueue(getJsonObjRequest());

    }
    
    public GsonRequest getGsonRequest()
    {
    		String url = Constant.IP + Constant.VerifyUser;
        try {
        	
        	jsondata.put("phonenum", e_phone.getText().toString());
        	jsondata.put("password", e_pwd.getText().toString());
        	jsondata.put("rememberme", "true");
        	System.out.println("------jsondata"+jsondata.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
         
    		GsonRequest<LoginBean> request = new GsonRequest<LoginBean>(
    				url, jsondata.toString(),  
    				new Response.Listener<LoginBean>() {
						@Override
						public void onResponse(LoginBean responce) {
							System.out.println("hello........");
							System.out.println("responce....."+responce.getRetcode());	
						}
					},
					new Response.ErrorListener() {
						@Override
						public void onErrorResponse(VolleyError error) {
							System.out.println("");
						}
					},LoginBean.class
    				);
		return request;	
    }
    /**
     * @Title: getStringRequest   
     * @Description: 璇锋眰瀛楃涓�     * @param: @return      
     * @return: StringRequest      
     * @throws
     */
    private StringRequest getStringRequest()
    {
    		String url = "http://www.baidu.com";
    		StringRequest request = new StringRequest
    				(url, new Response.Listener<String>() {
						@Override
						public void onResponse(String resopnse) {
							System.out.println("浣犲ソ銆傘�銆傘�銆傘�銆傘�");
							System.out.println("data-----"+resopnse.toString());
						}
					}, new Response.ErrorListener() {

						@Override
						public void onErrorResponse(VolleyError arg0) {
							
						}
					});
		return request;
    }
    /**
     * @Title: getStringRequest   
     * @Description: 璇锋眰瀛楃涓�     * @param: @return      
     * @return: StringRequest      
     * @throws
     */
    
    

    private JsonObjectRequest getJsonObjRequest()
    {  
    	   JSONObject jsondata = new JSONObject();
    	    try {
    	            jsondata.put("phonenum", e_phone.getText().toString());
    	            jsondata.put("password", e_pwd.getText().toString());
    	            jsondata.put("rememberme", "true");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		String url = Constant.IP + Constant.VerifyUser;
    		JsonObjectRequest request = new JsonObjectRequest
    		(Method.POST, url,jsondata , new JSONObjListener(), new StrErrListener())
    		{
    			
				@Override
				protected Map<String, String> getParams()
						throws AuthFailureError {
//					Map<String ,String> map = new HashMap<String, String>();
//					map.put("phonenum", "15189727317");
//					map.put("password", "123456");
//					map.put("rememberme", "true");
					return map;
				}
    			
    		};
		return request;
    }
}

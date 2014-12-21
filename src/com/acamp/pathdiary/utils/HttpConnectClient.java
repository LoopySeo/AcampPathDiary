package com.acamp.pathdiary.utils;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class HttpConnectClient {
	
	private static AsyncHttpClient httpClient = new AsyncHttpClient();
	
	public static void get(String url, RequestParams params, JsonHttpResponseHandler responseHandler){
		httpClient.get( url, params, responseHandler);
	}
	
	public static void post(String url, RequestParams params, JsonHttpResponseHandler responseHandler){
		httpClient.post( url, params, responseHandler);
	}
}

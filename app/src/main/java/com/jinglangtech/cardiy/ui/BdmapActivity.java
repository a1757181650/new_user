package com.jinglangtech.cardiy.ui;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.jinglangtech.cardiy.App;
import com.jinglangtech.cardiy.R;
import com.jinglangtech.cardiy.entity.CarShop;
import com.jinglangtech.cardiy.location.LocationService;
import com.jinglangtech.cardiy.utils.base.BaseActivity;
import com.jinglangtech.cardiy.utils.OpenLocalMapUtil;
import com.jinglangtech.cardiy.view.BottomSheetDialog;

import java.util.Arrays;
import java.util.List;
import static com.jinglangtech.cardiy.utils.Logger.LOGD;


public class BdmapActivity extends BaseActivity {

	public static final String KEY_SHOP = "key_shop";
	// 百度地图控件
	private MapView mMapView = null;
	// 百度地图对象
	private BaiduMap bdMap;

	private LocationService locationService;
	private GeoCoder mGeoCoder;
	private LatLng point;
	private String addrCur = null;
	private double longitude;
	private double latitude;
	private String cityDest = null;
	private String addrDest = null;
	private CarShop mCarShop = null;

	private static String SRC = "com.jinglangtech.cardiy";
	private static String APP_NAME = "cardiy";
	private boolean isOpened;
	private TextView mMapGo;
	private BDLocationListener myListener = new MyLocationListener();
	private Button mBtnBack;
	private View aa;
	private PopupWindow pop;
	private Intent intent;

	private String[] mMapAppNames = new String[] {
			// 百度
			"com.baidu.BaiduMap",
			// 高德
			"com.autonavi.minimap",
			// 搜狗
			"com.sougou.map.anroid.maps",
			// 腾讯
			"com.tencent.map",

	};

	private LinearLayout baiduMapLinearLayout;
	private LinearLayout gaoDeMapLinearLayout;
	private LinearLayout sougouMapLinearLayout;
	private LinearLayout tencentMapLinearLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bdmap_view);
		mCarShop = getIntent().getParcelableExtra(KEY_SHOP);
		if (mCarShop == null) {
			mCarShop = new CarShop();
		}
		mCarShop.setAddress("黄浦区人民广场");
		mCarShop.setCity("上海");
		LOGD("mCarShop:" + mCarShop.toString());
		// 创建地理编码检索实例
		mGeoCoder = GeoCoder.newInstance();
		init();
	}

	/**
	 * 初始化方法
	 */
	private void init() {
		mMapGo = findViewById(R.id.head_go);
		mMapGo.setVisibility(View.VISIBLE);
		mMapGo.setOnClickListener(view -> {
		BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(BdmapActivity.this)
		.init()
		.setCancelable(false)    //设置手机返回按钮是否有效
		.setCanceledOnTouchOutside(false)  //设置 点击空白处是否取消 Dialog 显示
		//如果条目样式一样，可以直接设置默认样式
		.setDefaultItemStyle(new BottomSheetDialog.SheetItemTextStyle("#3477F6", 18))
		.setBottomBtnStyle(new BottomSheetDialog.SheetItemTextStyle("#3477F6", 18));
					bottomSheetDialog.addSheetItem("百度地图",
							//可以对一个条目单独设置字体样式
							new BottomSheetDialog.SheetItemTextStyle("#3477F6"),   //设置字体样式
							which -> openLocalMap());

			List<PackageInfo> installedPackages = getPackageManager().getInstalledPackages(0);
					for (PackageInfo packageInfo : installedPackages) {
						String packageName = packageInfo.packageName;

						if (Arrays.asList(mMapAppNames).contains(packageName)) {
							// 百度
							if (mMapAppNames[1].equals(packageName)) {
								bottomSheetDialog.addSheetItem("高德地图",
								//可以对一个条目单独设置字体样式
								new BottomSheetDialog.SheetItemTextStyle("#3477F6"),   //设置字体样式
										which -> gaodeMapLocation());
													} else if (mMapAppNames[2].equals(packageName)) {
								bottomSheetDialog.addSheetItem("搜狗地图",
								//可以对一个条目单独设置字体样式
								new BottomSheetDialog.SheetItemTextStyle("#3477F6"),   //设置字体样式
										which -> {

										});
													} else if (mMapAppNames[3].equals(packageName)) {
								bottomSheetDialog.addSheetItem("腾讯地图",
								//可以对一个条目单独设置字体样式
								new BottomSheetDialog.SheetItemTextStyle("#3477F6"),   //设置字体样式
										which -> tencentMapLocation());
							}
						}
					}

					bottomSheetDialog.show();
		});
		mMapView = (MapView) findViewById(R.id.bmapview);
		//MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(17.0f);
		bdMap = mMapView.getMap();
		//bdMap.setMapStatus(msu);
		// 开启定位图层
		bdMap.setMyLocationEnabled(true);
	}

	/**
	 * 地址信息得到编码
	 */
	private void geoCode(String city, String address) {
		OnGetGeoCoderResultListener listener = new OnGetGeoCoderResultListener() {
			// 反地理编码查询结果回调函数
			@Override
			public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
				if (result == null
						|| result.error != SearchResult.ERRORNO.NO_ERROR) {
					// 没有检测到结果
					Toast.makeText(BdmapActivity.this, "抱歉，未能找到结果",
							Toast.LENGTH_LONG).show();
				}
				Toast.makeText(BdmapActivity.this, "位置：" + result.getAddress(),
						Toast.LENGTH_LONG).show();
			}

			// 地理编码查询结果回调函数
			@Override
			public void onGetGeoCodeResult(GeoCodeResult result) {
				if (result == null
						|| result.error != SearchResult.ERRORNO.NO_ERROR) {
					// 没有检测到结果
				}
				point = result.getLocation();


				BitmapDescriptor icon = BitmapDescriptorFactory
						.fromResource(R.drawable.icon_marka);
				OverlayOptions options = new MarkerOptions().icon(icon)
						.position(point);
				bdMap.addOverlay(options);
				// 定义地图状态
				MapStatus mMapStatus = new MapStatus.Builder().target(point)
						.zoom(20).build();
				// 定义MapStatusUpdate对象，以便描述地图状态将要发生的变化

				MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory
						.newMapStatus(mMapStatus);
				// 改变地图状态
				bdMap.setMapStatus(mMapStatusUpdate);
			}
		};
		// 设置地理编码检索监听者
		mGeoCoder.setOnGetGeoCodeResultListener(listener);
		//
		mGeoCoder.geocode(new GeoCodeOption().city(city).address(address));

	}

	/**
	 * 反地理编码得到地址信息
	 */
	private void reverseGeoCode(LatLng latLng) {
		OnGetGeoCoderResultListener listener = new OnGetGeoCoderResultListener() {
			// 反地理编码查询结果回调函数
			@Override
			public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
				if (result == null
						|| result.error != SearchResult.ERRORNO.NO_ERROR) {
					// 没有检测到结果
					Toast.makeText(BdmapActivity.this, "抱歉，未能找到结果",
							Toast.LENGTH_LONG).show();
				}
				Toast.makeText(BdmapActivity.this, "位置：" + result.getAddress(),
						Toast.LENGTH_LONG).show();
			}

			// 地理编码查询结果回调函数
			@Override
			public void onGetGeoCodeResult(GeoCodeResult result) {
				if (result == null
						|| result.error != SearchResult.ERRORNO.NO_ERROR) {
					// 没有检测到结果
				}
			}
		};
		// 设置地理编码检索监听者
		mGeoCoder.setOnGetGeoCodeResultListener(listener);
		//
		mGeoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(latLng));
	}

    private void openLocalMap() {
    	openBaiduMap(latitude, longitude, addrCur, point.latitude, point.longitude, addrDest, cityDest);
    	if(!isOpened){
    		openWebMap(latitude, longitude, addrCur, point.latitude, point.longitude, addrDest, cityDest);
        }
    }


    /**
     *  打开百度地图
     */
    private void openBaiduMap(double slat, double slon, String sname, double dlat, double dlon, String dname, String city) {
        if(OpenLocalMapUtil.isBaiduMapInstalled()){
            try {
                String uri = OpenLocalMapUtil.getBaiduMapUri(String.valueOf(slat), String.valueOf(slon), sname,
                        String.valueOf(dlat), String.valueOf(dlon), dname, city, SRC);
                Intent intent = Intent.parseUri(uri, 0);
                startActivity(intent); //启动调用
                isOpened = true;
            } catch (Exception e) {
                isOpened = false;
                e.printStackTrace();
            }
        } else{
            isOpened = false;
        }
    }

    /**
     * 打开浏览器进行百度地图导航
     */
    private void openWebMap(double slat, double slon, String sname, double dlat, double dlon, String dname, String city){
        Uri mapUri = Uri.parse(OpenLocalMapUtil.getWebBaiduMapUri(String.valueOf(slat), String.valueOf(slon), sname,
                String.valueOf(dlat), String.valueOf(dlon),
                dname, city, APP_NAME));
        Intent loction = new Intent(Intent.ACTION_VIEW, mapUri);
        startActivity(loction);
    }

	public class MyLocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			if (location == null || mMapView == null ) {  
                return;  
            }  
            longitude = location.getLongitude();  
            latitude = location.getLatitude();  
			int locType = location.getLocType();  
            Log.i("mybaidumap", "当前定位的返回值是："+locType);  

            if (locType == BDLocation.TypeNetWorkLocation) {  
                addrCur = location.getAddrStr();// 获取反地理编码(文字描述的地址)  
            }

            LOGD("longitude:" + longitude + " latitude:" + latitude + " addrCur:" + addrCur);
		}
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		// -----------location config ------------
		final App ac = (App) getApplication();
		locationService = ac.getLocationService();
		// 获取locationservice实例，建议应用中只初始化1个location实例，
		//然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
		locationService.registerListener(myListener);
		// 注册监听
		locationService.setLocationOption(locationService
				.getDefaultLocationClientOption());
		locationService.start();// 定位SDK
		cityDest = mCarShop.getCity();
		addrDest = mCarShop.getAddress();
		geoCode(cityDest, addrDest);

		// start之后会默认发起一次定位请求，开发者无须判断isstart并主动调用request
	}

	@Override
	protected void onResume() {
		super.onResume();
		mMapView.onResume();
        isOpened = false;
	}

	@Override
	protected void onPause() {
		super.onPause();
		mMapView.onPause();
	}

	private void stopListener() {
		// TODO Auto-generated method stub
		if (locationService != null) {
			locationService.unregisterListener(myListener); // 注销掉监听
			locationService.stop(); // 停止定位服务
		}
	}

	@Override
	protected void onDestroy() {
		// 释放地理编码检索实例
		mGeoCoder.destroy();
		// 退出时销毁定位
		stopListener();// 停止监听
		bdMap.setMyLocationEnabled(false);
		mMapView.onDestroy();
		mMapView = null;
		super.onDestroy();
	}

    private void gaodeMapLocation() {
        String act = "android.intent.action.VIEW";
        String dat = "androidamap://keywordNavi?sourceApplication=softname&keyword=" + mCarShop.getAddress() + "&style=2";
        String pkg = "com.autonavi.minimap";
        Intent intent=new Intent(act, Uri.parse(dat));
        intent.setPackage(pkg);
        startActivity(intent);
    }

    private void tencentMapLocation() {
        String act = "android.intent.action.VIEW";
        String dat = "qqmap://map/routeplan?type=drive&fromcoord=CurrentLocation" + "&to=" + mCarShop.getAddress() + "&policy=0&referer=com.jinglangtech.cardiy";
        String pkg = "com.tencent.map";
        Intent intent=new Intent(act, Uri.parse(dat));
        intent.setPackage(pkg);
        startActivity(intent);
    }

}

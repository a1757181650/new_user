package com.jinglangtech.cardiy.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class CarShop implements Parcelable {
	private String icon;
	private int comments_num;
	private String jishu;
	private String jianjie;
	private String pinpai;
	private String city;
	private int id;
	private String pic1;
	private String address;
	private String name;
	private int recommend_num;
	private String sheshi;
	private String pic4;
	private String pic3;
	private String rongyu;
	private String firstc;
	private String pic2;
	private String distance;
	private String zhuangtai;
	
	public int getCommentsNum() {
		return comments_num;
	}

	public void setCommentsNum(int comments_num) {
		this.comments_num = comments_num;
	}

	public String getJiShu() {
		return jishu;
	}

	public void setJiShu(String jishu) {
		this.jishu = jishu;
	}

	public String getPinPai() {
		return pinpai;
	}

	public void setPinPai(String pinpai) {
		this.pinpai = pinpai;
	}

	public String getJianJie() {
		return jianjie;
	}

	public void setJianJie(String jianjie) {
		this.jianjie = jianjie;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPic1() {
		return pic1;
	}

	public void setPic1(String pic1) {
		this.pic1 = pic1;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRecommend_num() {
		return recommend_num;
	}

	public void setRecommend_num(int recommend_num) {
		this.recommend_num = recommend_num;
	}

	public String getSheshi() {
		return sheshi;
	}

	public void setSheshi(String sheshi) {
		this.sheshi = sheshi;
	}

	public String getPic4() {
		return pic4;
	}

	public void setPic4(String pic4) {
		this.pic4 = pic4;
	}

	public String getPic3() {
		return pic3;
	}

	public void setPic3(String pic3) {
		this.pic3 = pic3;
	}

	public String getRongyu() {
		return rongyu;
	}

	public void setRongyu(String rongyu) {
		this.rongyu = rongyu;
	}

	public String getFirstc() {
		return firstc;
	}

	public void setFirstc(String firstc) {
		this.firstc = firstc;
	}

	public String getPic2() {
		return pic2;
	}

	public void setPic2(String pic2) {
		this.pic2 = pic2;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.icon);
		dest.writeInt(this.comments_num);
		dest.writeString(this.jishu);
		dest.writeString(this.jianjie);
		dest.writeString(this.pinpai);
		dest.writeString(this.city);
		dest.writeInt(this.id);
		dest.writeString(this.pic1);
		dest.writeString(this.address);
		dest.writeString(this.name);
		dest.writeInt(this.recommend_num);
		dest.writeString(this.sheshi);
		dest.writeString(this.pic4);
		dest.writeString(this.pic3);
		dest.writeString(this.rongyu);
		dest.writeString(this.firstc);
		dest.writeString(this.pic2);
		dest.writeString(this.distance);
		dest.writeString(this.zhuangtai);
	}

	public CarShop() {
	}

	protected CarShop(Parcel in) {
		this.icon = in.readString();
		this.comments_num = in.readInt();
		this.jishu = in.readString();
		this.jianjie = in.readString();
		this.pinpai = in.readString();
		this.city = in.readString();
		this.id = in.readInt();
		this.pic1 = in.readString();
		this.address = in.readString();
		this.name = in.readString();
		this.recommend_num = in.readInt();
		this.sheshi = in.readString();
		this.pic4 = in.readString();
		this.pic3 = in.readString();
		this.rongyu = in.readString();
		this.firstc = in.readString();
		this.pic2 = in.readString();
		this.distance = in.readString();
		this.zhuangtai = in.readString();
	}

	@Override
	public String toString() {
		return "CarShop{" +
				"icon='" + icon + '\'' +
				", comments_num=" + comments_num +
				", jishu='" + jishu + '\'' +
				", jianjie='" + jianjie + '\'' +
				", pinpai='" + pinpai + '\'' +
				", city='" + city + '\'' +
				", id=" + id +
				", pic1='" + pic1 + '\'' +
				", address='" + address + '\'' +
				", name='" + name + '\'' +
				", recommend_num=" + recommend_num +
				", sheshi='" + sheshi + '\'' +
				", pic4='" + pic4 + '\'' +
				", pic3='" + pic3 + '\'' +
				", rongyu='" + rongyu + '\'' +
				", firstc='" + firstc + '\'' +
				", pic2='" + pic2 + '\'' +
				", distance='" + distance + '\'' +
				", zhuangtai='" + zhuangtai + '\'' +
				'}';
	}

	public String getZhuangtai() {
		return zhuangtai;
	}

	public void setZhuangtai(String zhuangtai) {
		this.zhuangtai = zhuangtai;
	}

	public static final Creator<CarShop> CREATOR = new Creator<CarShop>() {
		public CarShop createFromParcel(Parcel source) {
			return new CarShop(source);
		}

		public CarShop[] newArray(int size) {
			return new CarShop[size];
		}
	};
}

package com.aotuo.vegetable.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class ImgUrl {
	/**
	 * 1. 商品分类图片：

		{ROOT_URL}/data/upload/shop/common/category-pic-{分类id}.jpg
	 */
	public static String getGoodsClassify(String img){
		return FinalContent.FinalUrl + img;
	}
	public static String getGoodsClassify(int id){
		return FinalContent.FinalUrl + "/data/upload/shop/common/category-pic-" + id + ".jpg";
	}
	
	/**
	 * 2. 会员头像：

		{ROOT_URL}/data/upload/shop/avatar/avatar_{会员id}.png
	 */
	public static String getUserPhoto(String userId){
		return FinalContent.FinalUrl + "/data/upload/shop/avatar/avatar_" + userId + ".png";
	}
	public static String getUserPhoto(int userId){
		return FinalContent.FinalUrl + "/data/upload/shop/avatar/avatar_" + userId + ".png";
	}
	public static String getUserPhotoJpg(String userId){
		return FinalContent.FinalUrl + "/data/upload/shop/avatar/avatar_" + userId + ".jpg";
	}
	public static String getUserPhotoJpg(int userId){
		return FinalContent.FinalUrl + "/data/upload/shop/avatar/avatar_" + userId + ".jpg";
	}
	
	/**
	 * 3. 品牌图片：

		{ROOT_URL}/data/upload/shop/brand/brand-pic-{品牌id}_sm.jpg
	 */
	public static String getBrandImg(String id){
		return FinalContent.FinalUrl + "/data/upload/shop/brand/brand-pic-" + id + "_sm.png";
	}
	public static String getBrandImg(int id){
		return FinalContent.FinalUrl + "/data/upload/shop/brand/brand-pic-" + id + "_sm.png";
	}
	
	/**
	 * 4. 产品图片：

   针对产品图片会有相应的缩略图，_60,_240,_360 表示宽度（px）;

   例如原始图片名称为：1_04714811169874709.jpg 其对应的缩略图名称分别为：
			
	1_04714811169874709_60.jpg
	
	1_04714811169874709_240.jpg

	1_04714811169874709_360.jpg

	按照原始图片地址说明：

	{ROOT_URL}/data/upload/shop/store/goods/{店铺id}/{图片名称}
	 */
	public static String getGoodsImg(String storeId, String img){
		if(!StringUtils.isEmpty(img) && img.contains("http"))
			return img;
		return FinalContent.FinalUrl + "/data/upload/shop/store/goods/" + storeId + "/" + img;
	}
	public static String getGoodsImg(int storeId, String img){
		if(!StringUtils.isEmpty(img) && img.contains("http"))
			return img;
		return FinalContent.FinalUrl + "/data/upload/shop/store/goods/" + storeId + "/" + img;
	}
	/**
	 * 5. app 端广告：
 	{ROOT_URL}/data/upload/shop/adv_app/{图片名称}
	 */
	public static String getAdvImg(String img){
		if(!StringUtils.isEmpty(img) && img.contains("http"))
			return img;
		if(img.length() > 60){
			String s = "";
			s = img;
			s = img;
		}
		return FinalContent.FinalUrl + "/data/upload/shop/adv_app/" + img;
	}
	/**
	 * 6. 团购：
	
	团购图片存在缩略图，_small,_mid,_max 

	例如原始图片名称为：1_04714811169874709.jpg 其对应的缩略图名称分别为：
			
	1_04714811169874709_small.jpg
	
	1_04714811169874709_mid.jpg

	1_04714811169874709_max.jpg

	按照原始图片地址说明：

	{ROOT_URL}/data/upload/shop/groupbuy/{店铺id}/{图片名称}

	 */
	public static String getGroupbuyImg(String storeId, String img){
		if(!StringUtils.isEmpty(img) && img.contains("http"))
			return img;
		return FinalContent.FinalUrl + "/data/upload/shop/groupbuy/" + storeId + "/" + img;
	}
	public static String getGroupbuyImg(int storeId, String img){
		if(!StringUtils.isEmpty(img) && img.contains("http"))
			return img;
		return FinalContent.FinalUrl + "/data/upload/shop/groupbuy/" + storeId + "/" + img;
	}
	/**
	 * 
	7. (预定惠)：
	图片存在缩略图，_small,_mid,_max 

	例如原始图片名称为：1_04714811169874709.jpg 其对应的缩略图名称分别为：
			
	1_04714811169874709_small.jpg
	
	1_04714811169874709_mid.jpg

	1_04714811169874709_max.jpg

	按照原始图片地址说明：

	{ROOT_URL}/data/upload/shop/reserve/{店铺id}/{图片名称}
	 */
	public static String getReserveImg(String storeId, String img){
		if(!StringUtils.isEmpty(img) && img.contains("http"))
			return img;
		return FinalContent.FinalUrl + "/data/upload/shop/reserve/" + storeId + "/" + img;
	}
	public static String getReserveImg(int storeId, String img){
		if(!StringUtils.isEmpty(img) && img.contains("http"))
			return img;
		return FinalContent.FinalUrl + "/data/upload/shop/reserve/" + storeId + "/" + img;
	}
	/**
	 * 8. 店铺分类图片：
        {ROOT_URL}/data/upload/shop/store/store_class/{图片名称}
	 */
	public static String getStoreClass(String img){
		if(!StringUtils.isEmpty(img) && img.contains("http"))
			return img;
		return FinalContent.FinalUrl + "/data/upload/shop/store/store_class/" + img;
	}
	/**
	 * 9. 店铺图片（头像、logo）：
	{ROOT_URL}/data/upload/shop/store/{图片名称}
	 */
	public static String getShopStore(String img){
		if(!StringUtils.isEmpty(img) && img.contains("http"))
			return img;
		return FinalContent.FinalUrl + "/data/upload/shop/store/" + img;
	}
	/**
	 * "http:\/\/192.168.1.230:8091\/data\/upload\/shop\/store\/photos
	 * \/5
	 * \/05106855031121037.jpg"        图片地址
	 * @param img
	 * @return
	 */
	public static String getShopStore(String storeId, String img){
		if(!StringUtils.isEmpty(img) && img.contains("http"))
			return img;
		return FinalContent.FinalUrl + "/data/upload/shop/store/photos/"
						+ storeId + "/" + img;
	}
	/**
	 * 新鲜事
	图片存在缩略图，_60,_240,_360，_1280 表示宽度（px）;
	例如原始图片名称为：1_04714811169874709.jpg 其对应的缩略图名称分别为：
	1_04714811169874709_60.jpg
	1_04714811169874709_240.jpg
	1_04714811169874709_360.jpg
	1_04714811169874709_1280.jpg
	按照原始图片地址说明：
	{ROOT_URL}/data/upload/shop/store/goods/{店铺id}/{图片名称}
	 */
	public static String getStoreGoods(String storeId, String img){
		if(!StringUtils.isEmpty(img) && img.contains("http"))
			return img;
		return FinalContent.FinalUrl + "/data/upload/shop/store/goods/" + storeId + "/" + img;
	}
	public static String getStoreGoods(int storeId, String img){
		if(!StringUtils.isEmpty(img) && img.contains("http"))
			return img;
		return FinalContent.FinalUrl + "/data/upload/shop/store/goods/" + storeId + "/" + img;
	}
	
	public static String getMemeberIcon(String memberId){
		
		return FinalContent.FinalUrl
				+ "/data/upload/shop/avatar/avatar_"
				+ memberId + ".jpg";
	}
	
	public static String getMemeberName(Context context){
		SharedPreferences shared_key = context.getSharedPreferences("shared_key",
				Activity.MODE_PRIVATE);
		String memberId = shared_key.getString("memberId", "");
		String iconUrl = FinalContent.FinalUrl
				+ "/data/upload/shop/avatar/avatar_"
				+ memberId + ".jpg";
		return iconUrl;
	}
}

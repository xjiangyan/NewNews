package newnews.huiiuh.com.newnews.Util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class BitmapAndStringUtils {
	

	/**
	 * ΢����http://weibo.com/mcxiaobing
	 * ============================================================================
	 * Copyright (c) 2015-2016 QQ986945193 All rights reserved.
	 * ----------------------------------------------------------------------------
	 * ������Android����֮���ñر�������ͼƬbitmapת���ַ���string��String�ַ���ת��ΪbitmapͼƬ��ʽ
	 * ----------------------------------------------------------------------------
	 * ����������Android����֮���ñر�������ͼƬbitmapת���ַ���string��String�ַ���ת��ΪbitmapͼƬ��ʽ
	 * ----------------------------------------------------------------------------
	 */
	    /**
	     * ͼƬת��string
	     *
	     * @param bitmap
	     * @return
	     */
	    public static String convertIconToString(Bitmap bitmap)
	    {
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();// outputstream
	        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
	        byte[] appicon = baos.toByteArray();// תΪbyte����
	        return Base64.encodeToString(appicon, Base64.DEFAULT);

	    }

	    /**
	     * stringת��bitmap
	     *
	     * @param st
	     */
	    public static Bitmap convertStringToIcon(String st)
	    {
	        // OutputStream out;
	        Bitmap bitmap = null;
	        try
	        {
	            // out = new FileOutputStream("/sdcard/aa.jpg");
	            byte[] bitmapArray;
	            bitmapArray = Base64.decode(st, Base64.DEFAULT);
	            bitmap =BitmapFactory.decodeByteArray(bitmapArray, 0,
	                            bitmapArray.length);
	            // bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
	            return bitmap;
	        }
	        catch (Exception e)
	        {
	            return null;
	        }
	    }
	}

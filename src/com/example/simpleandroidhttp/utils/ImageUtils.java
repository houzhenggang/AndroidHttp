package com.example.simpleandroidhttp.utils;

import java.io.File;
import java.io.IOException;


import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.os.Environment;

public class ImageUtils {
	
	public static final String TAG = ImageUtils.class.getName();
	/**
	 * 用于设置camera的EXTRA_OUTPUT 防止目录为空，无法存储大图！
	 * 
	 * <pre>
	 * intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(getTempImagePathFile()));
	 * </pre>
	 * 
	 * @param directory
	 *            用于保存大图的目录。如“jumpton/kpi”、“jumpton”，单层、多层目录结构都可。
	 * @return
	 */
	public static File getTempImagePathFile(String directory) {
		File img = new File(Environment.getExternalStorageDirectory(), String.format("%s/%s.jpeg", directory, System.currentTimeMillis()));

		//小米3 Environment.getExternalStorageDirectory() 获取到的路径不存在 特殊处理
		if(!FileUtils.isFileExist(img.getAbsolutePath())){
			img = new File("/storage/emulated/legacy", String.format("%s/%s.jpeg", directory, System.currentTimeMillis()));
		}
		
		if (!img.exists()) {
			img.getParentFile().mkdirs();
		}

		if (!img.exists()) {
			try {
				img.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return img;
	}

	public static Bitmap jointBmpHorizontal(Bitmap first, Bitmap second) {
		int width = first.getWidth() + second.getWidth();
		int height = Math.max(first.getHeight(), second.getHeight());
		Bitmap result = Bitmap.createBitmap(width, height, Config.RGB_565);
		Canvas canvas = new Canvas(result);
		canvas.drawBitmap(first, 0, 0, null);
		canvas.drawBitmap(second, first.getWidth(), 0, null);
		return result;
	}

	public static Bitmap jointBmpVertical(Bitmap first, Bitmap second, int left) {
		int width = Math.max(first.getWidth(), second.getWidth());
		int height = first.getHeight() + second.getHeight();
		Bitmap result = Bitmap.createBitmap(width, height, Config.RGB_565);
		Canvas canvas = new Canvas(result);
		canvas.drawBitmap(first, 0, 0, null);
		canvas.drawBitmap(second, left, first.getHeight(), null);
		return result;
	}

}

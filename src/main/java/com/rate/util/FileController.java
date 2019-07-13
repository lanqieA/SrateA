package com.rate.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.sun.xml.internal.ws.developer.StreamingAttachment;

/**
 * 处理文件上传下载
 * @author Admin
 *
 */
public class FileController {
	/*
	 * 处理文件上传的方法
	 * uploadFile:要与表单上传组件的name属性值一致
	 * 				代表上传的临时文件
	 */
	public static String upload(MultipartFile uploadFile,HttpServletRequest request,int car_id) throws IllegalStateException, IOException{
		//从上传的临时文件中取出文件名
		String fileName = uploadFile.getOriginalFilename();
		//创建一个目录,将文件保存到指定目录下
		String dirName = request.getServletContext().getRealPath("d:/rate/car");
		File dir = new File(dirName);
		if (dir.isFile()) {
			dir.delete();
		}
		if (!dir.exists()) {
			dir.mkdirs();
		}
		//将文件名字命名为上传的卡车id
		String car_img = String.valueOf(car_id);
		String suffix = fileName.substring(fileName.lastIndexOf("."));
		String newfilename = car_img+suffix;
		File file = new File(dir,newfilename);
		//3 将临时文件数据存储道指定目标文件
		uploadFile.transferTo(file);
		return newfilename;
	}
	//处理文件下载的处理器
	public static boolean downLoad(String fileName,HttpServletRequest request) throws IOException{
		System.out.println("Dfilename:"+fileName);
		//根据文件名找到要下载的文件
		String dirName = request.getServletContext().getRealPath("d:/rate/car");
		//截取防止两个upload
		fileName = fileName.substring(fileName.lastIndexOf("/")+1);
		//文件名转码,防止下载的文件中文名称乱码
		String nfileName = new String(fileName.getBytes("utf-8"), "ISO-8859-1");
		File file = new File(dirName,fileName);
		//添加下载的mimeType媒体类型和下载响应消息头
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);//这个类型是通用的
		headers.setContentDispositionFormData("attachment", nfileName);
		//将要下载的文件压缩到字节数组,
		//存入到响应数据包的响应实体中,
		//然后以流的方式返回到前端浏览器
		//ByteArrayInputStream
		ResponseEntity<byte[]> data = new ResponseEntity(FileUtils.readFileToByteArray(file), headers,HttpStatus.OK);
		return true;
	}
}

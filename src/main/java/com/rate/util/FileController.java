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
 * �����ļ��ϴ�����
 * @author Admin
 *
 */
public class FileController {
	/*
	 * �����ļ��ϴ��ķ���
	 * uploadFile:Ҫ����ϴ������name����ֵһ��
	 * 				�����ϴ�����ʱ�ļ�
	 */
	public static String upload(MultipartFile uploadFile,HttpServletRequest request,int car_id) throws IllegalStateException, IOException{
		//���ϴ�����ʱ�ļ���ȡ���ļ���
		String fileName = uploadFile.getOriginalFilename();
		//����һ��Ŀ¼,���ļ����浽ָ��Ŀ¼��
		String dirName = request.getServletContext().getRealPath("d:/rate/car");
		File dir = new File(dirName);
		if (dir.isFile()) {
			dir.delete();
		}
		if (!dir.exists()) {
			dir.mkdirs();
		}
		//���ļ���������Ϊ�ϴ��Ŀ���id
		String car_img = String.valueOf(car_id);
		String suffix = fileName.substring(fileName.lastIndexOf("."));
		String newfilename = car_img+suffix;
		File file = new File(dir,newfilename);
		//3 ����ʱ�ļ����ݴ洢��ָ��Ŀ���ļ�
		uploadFile.transferTo(file);
		return newfilename;
	}
	//�����ļ����صĴ�����
	public static boolean downLoad(String fileName,HttpServletRequest request) throws IOException{
		System.out.println("Dfilename:"+fileName);
		//�����ļ����ҵ�Ҫ���ص��ļ�
		String dirName = request.getServletContext().getRealPath("d:/rate/car");
		//��ȡ��ֹ����upload
		fileName = fileName.substring(fileName.lastIndexOf("/")+1);
		//�ļ���ת��,��ֹ���ص��ļ�������������
		String nfileName = new String(fileName.getBytes("utf-8"), "ISO-8859-1");
		File file = new File(dirName,fileName);
		//������ص�mimeTypeý�����ͺ�������Ӧ��Ϣͷ
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);//���������ͨ�õ�
		headers.setContentDispositionFormData("attachment", nfileName);
		//��Ҫ���ص��ļ�ѹ�����ֽ�����,
		//���뵽��Ӧ���ݰ�����Ӧʵ����,
		//Ȼ�������ķ�ʽ���ص�ǰ�������
		//ByteArrayInputStream
		ResponseEntity<byte[]> data = new ResponseEntity(FileUtils.readFileToByteArray(file), headers,HttpStatus.OK);
		return true;
	}
}

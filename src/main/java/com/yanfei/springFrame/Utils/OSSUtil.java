package com.yanfei.springFrame.Utils;

import java.util.ArrayList;
import java.util.List;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;

public class OSSUtil {
	static String accessId = "***"; // 请填写您的AccessKeyId。
	static String accessKey = "***"; // 请填写您的AccessKeySecret。
	static String endpoint = "***"; // 请填写您的 endpoint。
	static String bucket = "***"; // 请填写您的 bucketname 。
	
	public static void deleteObject(String objectName) {
		// 创建OSSClient实例。
		OSS ossClient = new OSSClient(endpoint, accessId, accessKey);
		
		if (!ossClient.doesObjectExist(bucket, objectName)) {
			return;
		}
		// 删除文件。
		ossClient.deleteObject(bucket, objectName);
		// 关闭OSSClient。
		ossClient.shutdown();
	}
	
	public static List<String> getList(String KeyPrefix) {
		// 创建OSSClient实例。
		OSS ossClient = new OSSClient(endpoint, accessId, accessKey);
		List<String> result = new ArrayList<String>();
		
		// 列举文件。 如果不设置KeyPrefix，则列举存储空间下所有的文件。KeyPrefix，则列举包含指定前缀的文件。
		ObjectListing objectListing = ossClient.listObjects(bucket, KeyPrefix);
		for (OSSObjectSummary s : objectListing.getObjectSummaries()) {
			String fileName = s.getKey();
			result.add(fileName);
		}
		
		// 关闭OSSClient。
		ossClient.shutdown();
		return result;
	}
}

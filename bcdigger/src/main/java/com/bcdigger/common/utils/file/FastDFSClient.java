package com.bcdigger.common.utils.file;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.github.tobato.fastdfs.domain.MateData;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.exception.FdfsUnsupportStorePathException;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
/*import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
*/
/**
 * FastDFS分布式文件系统操作客户端.
 * 
 * @author yookien .
 * 
 */
@Component
public class FastDFSClient  {
	
	private static Logger logger = Logger.getLogger(FastDFSClient.class);
	
	private static String FDFSWebRoot;
	private static FastFileStorageClient storageClient;
	
	@Value("${fdfs.webRoot}")//系统属性配置
	private String FDFSWebRoot1;
	
	@Autowired
	private FastFileStorageClient storageClient1;
	@PostConstruct
    public void beforeInit() {
		storageClient = storageClient1;
		FDFSWebRoot = FDFSWebRoot1;
    }
	
	/**
     * 上传文件
     * @param file 文件对象
     * @return 文件访问地址
     * @throws IOException
     */
    public static String uploadFile(MultipartFile file) throws IOException {
        StorePath storePath = storageClient.uploadFile(file.getInputStream(),file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()),null);
        return getResAccessUrl(storePath);
    }
    
	/**
     * 上传文件
     * @param filePath 文件路径
     * @return 文件访问地址
     * @throws IOException
     */
    public static String uploadFile(String filePath)  {
    	if(StringUtils.isBlank(filePath)){  
            throw new NullPointerException();  
        }
    	File file = new File(filePath);
        return uploadFile(file);
    }
    
    
    /**
     * 上传文件
     * @param file 文件对象
     * @return 文件访问地址
     * @throws IOException
     */
    public static String uploadFile(File file){
    	
		try {
			FileInputStream inputStream = new FileInputStream(file);
			String fileName=file.getName();  
	         //获取文件后缀名  
	         String strs= FilenameUtils.getExtension(fileName);
	         if(StringUtils.isBlank(strs)){  
	             throw new NullPointerException();  
	         }  
	         //StorePath storePath = storageClient.uploadImageAndCrtThumbImage(inputStream,file.length(),strs,null);  
	         StorePath storePath = storageClient.uploadFile(inputStream,file.length(),strs,null);
	         return getResAccessUrl(storePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
        
    }

    /**
     * 将一段字符串生成一个文件上传
     * @param content 文件内容
     * @param fileExtension
     * @return
     */
    public static String uploadFile(String content, String fileExtension) {
        byte[] buff = content.getBytes(Charset.forName("UTF-8"));
        ByteArrayInputStream stream = new ByteArrayInputStream(buff);
        StorePath storePath = storageClient.uploadFile(stream,buff.length, fileExtension,null);
        return getResAccessUrl(storePath);
    }
    
    /**
     * 删除文件
     * @param fileUrl 文件访问地址
     * @return
     */
    public static void deleteFile(String fileUrl) {
        if (StringUtils.isEmpty(fileUrl)) {
            return;
        }
        try {
            StorePath storePath = StorePath.praseFromUrl(fileUrl);
            storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
        } catch (FdfsUnsupportStorePathException e) {
            logger.warn(e.getMessage());
        }
    }
	
    /**
     * 下载一个文件
     * @param groupName
     * @param filePath
     * @return
     */
	public static InputStream downloadFile(String groupName,String filePath) {
		try {
			if(StringUtils.isBlank(groupName))
				groupName = "group1";
			byte[] bytes = storageClient.downloadFile(groupName, filePath, null);
			InputStream inputStream = new ByteArrayInputStream(bytes);
			return inputStream;
		} catch (Exception ex) {
			logger.error(ex);
			return null;
		}
	}
	
	/**
	 * 上传图片并同时生成一个缩略图
	 * @param inputStream
	 * @param fileSize
	 * @param fileExtName
	 * @param metaDataSet
	 * @return
	 */
	public static String uploadImageAndCrtThumbImage(InputStream inputStream, long fileSize, String fileExtName,
	            Set<MateData> metaDataSet) {
		storageClient.uploadImageAndCrtThumbImage(inputStream, fileSize, fileExtName, metaDataSet);
		return null;
	}
	
	/**
	 * 上传图片并同时生成一个缩略图
	 * @param File file文件
	 */
	public static String uploadImageAndCrtThumbImage(File file) {
		try {
			FileInputStream inputStream = new FileInputStream(file);
			String fileName=file.getName();  
	         //获取文件后缀名  
	         String strs= FilenameUtils.getExtension(fileName);
	         if(StringUtils.isBlank(strs)){  
	             throw new NullPointerException();  
	         }  
	         StorePath storePath = storageClient.uploadImageAndCrtThumbImage(inputStream,file.length(),strs,null);
	         return getResAccessUrl(storePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 上传图片并同时生成一个缩略图
	 * @param File filePath文件路径
	 */
	public static String uploadImageAndCrtThumbImage(String filePath) {
		if(StringUtils.isBlank(filePath)){  
            throw new NullPointerException();  
        }
    	File file = new File(filePath);
        return uploadImageAndCrtThumbImage(file);
	}
	
	
    /**
     * 封装图片完整URL地址
     * @param storePath
     * @return
     */
    private static String getResAccessUrl(StorePath storePath) {
        String fileUrl = FDFSWebRoot + "/" + storePath.getFullPath();
        return fileUrl;
    }
    
	
	
}

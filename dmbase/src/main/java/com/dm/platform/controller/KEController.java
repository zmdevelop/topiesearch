package com.dm.platform.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dm.platform.model.FileEntity;
import com.dm.platform.service.FileService;
import com.dm.platform.util.ConfigUtil;
import com.dm.platform.util.DmDateUtil;
import com.dm.platform.util.UserAccountUtil;


@Controller @RequestMapping("/KE") public class KEController {
    public PrintWriter writer = null;
    @Resource FileService fileService;
    

      @Value("${htmlDir}")
      String htmlFolder;
      
      @Value("${uploadPath}")
      String uploadPath;
      
      @Value("${resource.basePath}")
      String resource;
      
      @Value("${projectName}")
      String projectName;
      

    @RequestMapping(value = "/file_upload", method = RequestMethod.POST)
    public void file_upload(HttpServletRequest request, HttpServletResponse response,
        @RequestParam(value = "imgFile", required = false) MultipartFile file) throws Exception {
        try {
    		String webappDir = request.getSession().getServletContext()
    				.getRealPath("/");
    		String projectName = request.getContextPath();
    		webappDir = webappDir.substring(0,
    				webappDir.indexOf(projectName.substring(1)));
            String savePath =
                webappDir + htmlFolder + resource + uploadPath+ "/" + UserAccountUtil
                    .getInstance().getCurrentUserId() + "/";
            // 文件保存目录URL
            String saveUrl =
                htmlFolder +resource +uploadPath+"/" + UserAccountUtil.getInstance()
                    .getCurrentUserId() + "/";

            // 定义允许上传的文件扩展名
            HashMap<String, String> extMap = new HashMap<String, String>();
            extMap.put("image", "gif,jpg,jpeg,png,bmp");
            extMap.put("flash", "swf,flv");
            extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
            extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

            // 最大文件大小
            long maxSize = 1000000;

            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html");
            writer = response.getWriter();
            if (!ServletFileUpload.isMultipartContent(request)) {
                writer.println(getError("请选择文件。"));
                return;

            }
            // 检查目录
            File uploadDir = new File(savePath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            if (!uploadDir.isDirectory()) {
                writer.println(getError("上传目录不存在。"));
                return;
            }
            // 检查目录写权限
            if (!uploadDir.canWrite()) {
                writer.println(getError("上传目录没有写权限。"));
                return;
            }

            String dirName = request.getParameter("dir");
            if (dirName == null) {
                dirName = "image";
            }
            if (!extMap.containsKey(dirName)) {
                writer.println(getError("目录名不正确。"));
                return;
            }
            // 创建文件夹
            savePath += dirName + "/";
            saveUrl += dirName + "/";
            File saveDirFile = new File(savePath);
            if (!saveDirFile.exists()) {
                saveDirFile.mkdirs();
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String ymd = sdf.format(new Date());
            savePath += ymd + "/";
            saveUrl += ymd + "/";
            File dirFile = new File(savePath);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }

            String fileName = file.getOriginalFilename();
            // 检查文件大小
            if (file.getSize() > maxSize) {
                writer.println(getError("上传文件大小超过限制。"));
                return;
            }
            // 检查扩展名
            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
            if (!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)) {
                writer.println(getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));
                return;
            }

            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            String newFileName =
                df.format(new Date()) + "_" + new Random().nextInt(1000) + "_" + fileName;
            try {
                SaveFileFromInputStream(file.getInputStream(), savePath, newFileName);
                FileEntity entity = new FileEntity();
                entity.setId(String.valueOf(System.currentTimeMillis()));
                entity.setcDate(DmDateUtil.Current());
                entity.setFilesize(String.valueOf(file.getSize()));
                entity.setName(fileName);
                entity.setRealPath(savePath + newFileName);
                entity.setSaveFlag("1");
                entity.setType(file.getContentType());
                entity.setUrl(saveUrl + newFileName);
                entity.setcUser(UserAccountUtil.getInstance().getCurrentUserId());
                fileService.insert(entity);
            } catch (Exception e) {
                writer.println(getError("上传文件失败。"));
            }
            JSONObject msg = new JSONObject();
            msg.put("error", 0);
            msg.put("url", saveUrl + newFileName);
            writer.println(msg.toString());
            return;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    private void SaveFileFromInputStream(InputStream stream, String path, String filename)
        throws IOException {
        FileOutputStream fs = new FileOutputStream(path + "/" + filename);
        byte[] buffer = new byte[1024 * 1024];
        int byteread = 0;
        while ((byteread = stream.read(buffer)) != -1) {
            fs.write(buffer, 0, byteread);
            fs.flush();
        }
        fs.close();
        stream.close();
    }

    @SuppressWarnings("unchecked") @RequestMapping(value = "/file_manager")
    public void file_manager(HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        //ServletOutputStream out = response.getOutputStream();
        String rootPath =
            ConfigUtil.getConfigContent("dm", "uploadPath") + "/" + UserAccountUtil.getInstance()
                .getCurrentUserId() + "/";
        // 文件保存目录URL
        String rootUrl = rootPath;
        // 图片扩展名
        String[] fileTypes = new String[] {"gif", "jpg", "jpeg", "png", "bmp"};

        String dirName = request.getParameter("dir");
        if (dirName != null) {
            if (!Arrays.<String>asList(new String[] {"image", "flash", "media", "file"})
                .contains(dirName)) {
                out.println("Invalid Directory name.");
                return;
            }
            rootPath += dirName + "/";
            rootUrl += dirName + "/";
            rootUrl="/"+projectName+"/"+rootUrl;
            File saveDirFile = new File(rootPath);
            if (!saveDirFile.exists()) {
                saveDirFile.mkdirs();
            }
        }
        // 根据path参数，设置各路径和URL
        String path = request.getParameter("path") != null ? request.getParameter("path") : "";
        String currentPath = rootPath + path;
        String currentUrl = rootUrl + path;
        String currentDirPath = path;
        String moveupDirPath = "";
        if (!"".equals(path)) {
            String str = currentDirPath.substring(0, currentDirPath.length() - 1);
            moveupDirPath =
                str.lastIndexOf("/") >= 0 ? str.substring(0, str.lastIndexOf("/") + 1) : "";
        }

        // 排序形式，name or size or type
        String order = request.getParameter("order") != null ?
            request.getParameter("order").toLowerCase() :
            "name";

        // 不允许使用..移动到上一级目录
        if (path.indexOf("..") >= 0) {
            out.println("Access is not allowed.");
            return;
        }
        // 最后一个字符不是/
        if (!"".equals(path) && !path.endsWith("/")) {
            out.println("Parameter is not valid.");
            return;
        }
        // 目录不存在或不是目录
        File currentPathFile = new File(currentPath);
        if (!currentPathFile.isDirectory()) {
            out.println("Directory does not exist.");
            return;
        }
        // 遍历目录取的文件信息
        List<Hashtable> fileList = new ArrayList<Hashtable>();
        if (currentPathFile.listFiles() != null) {
            for (File file : currentPathFile.listFiles()) {
                Hashtable<String, Object> hash = new Hashtable<String, Object>();
                String fileName = file.getName();
                if (file.isDirectory()) {
                    hash.put("is_dir", true);
                    hash.put("has_file", (file.listFiles() != null));
                    hash.put("filesize", 0L);
                    hash.put("is_photo", false);
                    hash.put("filetype", "");
                } else if (file.isFile()) {
                    String fileExt =
                        fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                    hash.put("is_dir", false);
                    hash.put("has_file", false);
                    hash.put("filesize", file.length());
                    hash.put("is_photo", Arrays.<String>asList(fileTypes).contains(fileExt));
                    hash.put("filetype", fileExt);
                }
                hash.put("filename", fileName);
                hash.put("datetime",
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file.lastModified()));
                fileList.add(hash);
            }
        }

        if ("size".equals(order)) {
            Collections.sort(fileList, new SizeComparator());
        } else if ("type".equals(order)) {

            Collections.sort(fileList, new TypeComparator());
        } else {
            Collections.sort(fileList, new NameComparator());

        }
        JSONObject msg = new JSONObject();
        msg.put("moveup_dir_path", moveupDirPath);
        msg.put("current_dir_path", currentDirPath);
        msg.put("current_url", currentUrl);
        msg.put("total_count", fileList.size());
        msg.put("file_list", fileList);

        String msgStr = msg.toString();
        out.println(msgStr);
    }

    private String getError(String message) {
        JSONObject obj = new JSONObject();
        obj.put("error", 1);
        obj.put("message", message);
        return obj.toString();
    }

    @SuppressWarnings("rawtypes") class NameComparator implements Comparator {
        public int compare(Object a, Object b) {
            Hashtable hashA = (Hashtable) a;
            Hashtable hashB = (Hashtable) b;
            if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
                return -1;
            } else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
                return 1;
            } else {
                return ((String) hashA.get("filename")).compareTo((String) hashB.get("filename"));
            }
        }
    }


    @SuppressWarnings("rawtypes") class SizeComparator implements Comparator {
        public int compare(Object a, Object b) {
            Hashtable hashA = (Hashtable) a;
            Hashtable hashB = (Hashtable) b;
            if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
                return -1;
            } else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
                return 1;
            } else {
                if (((Long) hashA.get("filesize")) > ((Long) hashB.get("filesize"))) {
                    return 1;
                } else if (((Long) hashA.get("filesize")) < ((Long) hashB.get("filesize"))) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
    }


    @SuppressWarnings("rawtypes") class TypeComparator implements Comparator {
        public int compare(Object a, Object b) {
            Hashtable hashA = (Hashtable) a;
            Hashtable hashB = (Hashtable) b;
            if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
                return -1;
            } else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
                return 1;
            } else {
                return ((String) hashA.get("filetype")).compareTo((String) hashB.get("filetype"));
            }
        }
    }
}

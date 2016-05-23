package com.dm.cms.controller;

import com.dm.cms.model.CmsAttachment;
import com.dm.cms.service.CmsAttachmentService;
import com.dm.platform.controller.DefaultController;
import com.dm.platform.util.FileUtil;
import com.dm.platform.util.ResponseUtil;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cgj on 2015/12/3.
 */
@Controller @RequestMapping("/cms/attachment") public class CmsAttachmentController
    extends DefaultController {
    @Value("${resource.basePath}") String resourceBasePath;
    @Autowired CmsAttachmentService cmsAttachmentService;
    @Value("${projectName}") String projectName;
    @Value("${htmlDir}") String htmlFolder;

    /**
     * 单个资源上传
     *
     * @return
     */
    @RequestMapping("/singleUpload") public void singleUpload(
        @RequestParam(value = "file") MultipartFile multipartFile,
        HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
        throws IOException {
        httpServletResponse.setContentType("text/html;charset=UTF-8");
        PrintWriter out = httpServletResponse.getWriter();
        if (multipartFile == null || multipartFile.isEmpty()) {
            JSONObject jsonList = JSONObject.fromObject(ResponseUtil.error("请先上传附件"));
            out.write(jsonList.toString());
            out.flush();
            out.close();
        }

        String baseDir = System.getProperty("web.root");
        String path =
            baseDir.substring(0, baseDir.indexOf(projectName) - 1) + htmlFolder + resourceBasePath;
        String newFileName = FileUtil.saveFileFromMultipartFileCreateNewName(multipartFile, path);
        String url =htmlFolder + resourceBasePath + "/" + newFileName;
        CmsAttachment cmsAttachment = new CmsAttachment();
        cmsAttachment.setAttachmentName(multipartFile.getOriginalFilename());
        cmsAttachment.setAttachmentUrl(url);
        cmsAttachment.setFileSize(multipartFile.getSize());
        cmsAttachment.setIsActive(true);
        cmsAttachmentService.insertCmsAttachment(cmsAttachment);
        Map map = ResponseUtil.success();
        map.put("attachment", cmsAttachment);
        JSONObject jsonList = JSONObject.fromObject(map);
        out.write(jsonList.toString());
        out.flush();
        out.close();
    }
    @RequestMapping("/multipleUpload")
    public void MultipleUpload(@RequestParam(value = "files[]") MultipartFile[] multipartFiles,
        HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
        throws IOException {
    	this.uploadFiles(multipartFiles, httpServletRequest, httpServletResponse, htmlFolder+resourceBasePath);
    }
    private void uploadFiles(MultipartFile[] multipartFiles,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,String folder)throws IOException{
    	if (multipartFiles != null && multipartFiles.length > 0) {
            httpServletResponse.setContentType("text/html;charset=UTF-8");
            PrintWriter out = httpServletResponse.getWriter();
            String baseDir = System.getProperty("web.root");
            String path = baseDir.substring(0, baseDir.indexOf(projectName) - 1) + folder;
            //                httpServletRequest.getSession().getServletContext().getRealPath(resourceBasePath);
            for (MultipartFile multipartFile : multipartFiles) {
                String newFileName = FileUtil.saveFileFromMultipartFileCreateNewName(multipartFile, path);
                String url = folder + "/"
                    + newFileName;
                CmsAttachment cmsAttachment = new CmsAttachment();
                cmsAttachment.setAttachmentName(multipartFile.getOriginalFilename());
                cmsAttachment.setAttachmentUrl(url);
                cmsAttachment.setFileSize(multipartFile.getSize());
                cmsAttachment.setIsActive(true);
                cmsAttachmentService.insertCmsAttachment(cmsAttachment);
                Map map = ResponseUtil.success();
                map.put("attachment", cmsAttachment);
                JSONObject jsonList = JSONObject.fromObject(map);
                out.write(jsonList.toString());
                out.flush();
                out.close();
            }
        }
    }
    @RequestMapping("/detail") public @ResponseBody Object detail(
        @RequestParam(value = "attachmentId", required = true) Integer attachmentId) {
        CmsAttachment cmsAttachment = cmsAttachmentService.findOneById(attachmentId);
        Map result = new HashMap();
        Map data = new HashMap();
        data.put("id", cmsAttachment.getId());
        data.put("name", cmsAttachment.getAttachmentName());
        data.put("size", cmsAttachment.getFileSize());
        data.put("fileUrl", cmsAttachment.getAttachmentUrl());
        result.put("result", data);
        result.put("status", "1");
        return result;
    }

    @RequestMapping("/download/{attachmentId}")
    public void downloadFile(@PathVariable Integer attachmentId, HttpServletRequest request,
        HttpServletResponse response) throws UnsupportedEncodingException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        CmsAttachment cmsAttachment = cmsAttachmentService.findOneById(attachmentId);
        response.setHeader("Content-Disposition",
            "attachment;fileName=" + new String(cmsAttachment.getAttachmentName().getBytes("gbk"),
                "iso-8859-1"));
        try {
        	
        	String baseDir = System.getProperty("web.root");
            String path = baseDir.substring(0, baseDir.indexOf(projectName) - 1)+htmlFolder
//        	String path=request.getSession().getServletContext().getRealPath(htmlFolder)
                    + cmsAttachment.getAttachmentUrl().split(htmlFolder)[1];
            File file = new File(path);
            InputStream inputStream = new FileInputStream(file);
            OutputStream os = response.getOutputStream();
            byte[] b = new byte[1024];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

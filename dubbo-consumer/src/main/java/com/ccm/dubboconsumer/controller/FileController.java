package com.ccm.dubboconsumer.controller;

import com.ccm.dubbobeans.enums.ResultEnum;
import com.ccm.dubbobeans.result.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.util.List;

/**
 * Created by chencm on 2018/12/7
 * 文件操作
 */
@Api("文件操作")
@RestController
@RequestMapping("/file")
@Configuration
@Slf4j
public class FileController {

    @Value("${file.upload.root.path}")
    private String uploadRootPath;

    @ApiOperation("单文件上传")
    @PostMapping("upload")
    public AjaxResult<?> upload(@ApiParam(value = "文件", required = true) @RequestParam("file") MultipartFile file){
        try {
            if(file==null)
                throw new Exception("文件为空");
            String origFileName = file.getOriginalFilename();

            String fileName = origFileName.substring(0,origFileName.lastIndexOf("."));
            String suffixName = origFileName.substring(origFileName.lastIndexOf("."));

            String rootPath = getRootPath();
            String relativePath = String.format("%s/%s%s%s",DateTime.now().toString("yyyy-MM-dd"),fileName, System.currentTimeMillis(),suffixName);
            String filepath=rootPath + relativePath;
            File destFile = new File(filepath);
            // 检测是否存在目录
            if (!destFile.getParentFile().exists()) {
                destFile.getParentFile().mkdirs();
            }
            destFile.createNewFile();
            file.transferTo(destFile);
            //destFile.delete();
            return new AjaxResult<>(ResultEnum.SUCCESS);
        }catch (Exception ex){
            log.error("upload():"+ex.getMessage());
            return new AjaxResult<>(ex.getMessage());
        }

    }
    @ApiOperation("多文件上传")
    @PostMapping("multiUpload")
    public AjaxResult<?> multiUpload(@ApiParam(value = "多文件", required = true) @RequestParam("file") List<MultipartFile> files){
        try {
            if(CollectionUtils.isEmpty(files))
                throw new Exception("文件为空");
            String rootPath = getRootPath();
            for (MultipartFile file:files) {
                String origFileName = file.getOriginalFilename();
                //获取文件名
                String fileName = origFileName.substring(0,origFileName.lastIndexOf("."));
                // 获取文件的后缀名
                String suffixName = origFileName.substring(origFileName.lastIndexOf("."));

                String relativePath = String.format("%s/%s%s%s",DateTime.now().toString("yyyy-MM-dd"),fileName, System.currentTimeMillis(),suffixName);
                String filepath=rootPath + relativePath;

                File destFile = new File(filepath);
                // 检测是否存在目录
                if (!destFile.getParentFile().exists()) {
                    destFile.getParentFile().mkdirs();
                }
                destFile.createNewFile();
                file.transferTo(destFile);
                //destFile.delete();
            }
            return new AjaxResult<>(ResultEnum.SUCCESS);
        }catch (Exception ex){
            log.error("multiUpload():"+ex.getMessage());
            return new AjaxResult<>(ex.getMessage());
        }
    }

    @ApiOperation("下载文件")
    @GetMapping("/download")
    public AjaxResult<?> downLoad(@ApiParam(value = "文件路径", required = true)String filePath, HttpServletResponse response, @ApiParam(value = "是否支持在线打开", required = true)boolean isOnLine) {
        BufferedInputStream br = null;
        OutputStream out = null;
        FileInputStream fileStream = null;
        try{
            filePath = getRootPath() + filePath;
            File file = new File(filePath);
            if (!file.exists()) {
                throw new Exception("文件不存在!");
            }

            fileStream = new FileInputStream(file);
            br = new BufferedInputStream(fileStream);

            response.reset(); // 非常重要

            if (isOnLine) { // 在线打开方式
                URL u = new URL("file:///" + filePath);
                response.setContentType(u.openConnection().getContentType());
                response.setHeader("Content-Disposition", "inline; filename=" + new String(file.getName().getBytes("gb2312"), "ISO8859-1"));
                // 文件名应该编码成UTF-8
            } else { // 纯下载方式
                response.setContentType("application/x-msdownload");
                response.setHeader("Content-Disposition", "attachment; filename=" + new String(file.getName().getBytes("gb2312"), "ISO8859-1"));
            }
            response.setCharacterEncoding("utf-8");
            out = response.getOutputStream();
            byte[] buf = new byte[1024];
            int len = 0;

            while ((len = br.read(buf)) > 0) {
                out.write(buf, 0, len);
            }

            return new AjaxResult<>(ResultEnum.SUCCESS);
        }catch (Exception ex){
            log.error("downLoad():"+ex.getMessage());
            return new AjaxResult<>(ex.getMessage());
        }finally {
            try {
                if(fileStream!=null)
                    fileStream.close();
                if(br!=null)
                    br.close();
                if(out!=null)
                    out.close();
            } catch (IOException ex) {
                log.error("downLoad():"+ex.getMessage());
            }
        }

    }

    public String getRootPath() throws Exception{
        File classPath = new File(ResourceUtils.getURL("classpath:").getPath());
        return classPath.getAbsolutePath() + uploadRootPath;
    }
}

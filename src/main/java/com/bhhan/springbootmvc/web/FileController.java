package com.bhhan.springbootmvc.web;

import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;

/**
 * Created by hbh5274@gmail.com on 2020-01-26
 * Github : http://github.com/bhhan5274
 */

@Controller
public class FileController {

    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping("/file")
    public String fileUploadForm(Model model) {
        return "/files/index";
    }

    @PostMapping("/file")
    public String fileUpload(@RequestParam MultipartFile file,
                             RedirectAttributes redirectAttributes) {
        String message = file.getOriginalFilename() + " is uploaded";
        redirectAttributes.addFlashAttribute("message", message);

        return "redirect:/file";
    }

    @GetMapping("/file/{fileName}")
    public ResponseEntity<Resource> fileDownload(@PathVariable String fileName) throws IOException {
        final Resource resource = resourceLoader.getResource("classpath:" + fileName);
        final File file = resource.getFile();

        final Tika tika = new Tika();
        final String mediaType = tika.detect(file);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachement; filename=\"" + resource.getFilename() + "\"")
                .header(HttpHeaders.CONTENT_TYPE, mediaType)
                .header(HttpHeaders.CONTENT_LENGTH, file.length() + "")
                .body(resource);
    }
}
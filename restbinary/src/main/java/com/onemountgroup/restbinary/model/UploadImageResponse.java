package com.onemountgroup.restbinary.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadImageResponse {
    private String fileName;
    private String fileDownloadUri;
    private long size;

    public UploadImageResponse(String fileName, String fileDownloadUri, long size) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.size = size;
    }
}
